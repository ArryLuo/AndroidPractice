package com.lyl.viewcanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/3.
 */
public class CanvasView extends View {

    private Paint mPaint = new Paint();

    private int width, height;

    public CanvasView(Context context) {
        super(context);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // 位移(translate)——位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动。
        canvas.translate(100, 100);
        canvas.drawCircle(0, 0, 50, mPaint);
        // 在此基础上再向右 200
        canvas.translate(200, 0);
        canvas.drawCircle(0, 0, 50, mPaint);


        canvas.translate(-300, -100);
        mPaint.setStyle(Paint.Style.STROKE);
        // 缩放(scale)——缩放的中心默认为坐标原点,而缩放中心轴就是坐标轴。
        canvas.translate(width / 2, height / 4 * 1);
        Log.d("heightheight", "height:" + height);
        RectF rectF = new RectF(0, -400, 400, 0);
        canvas.drawRect(rectF, mPaint); // 画一个大正方形
        canvas.scale(0.5f, 0.5f);
        canvas.drawRect(rectF, mPaint);// 缩小一倍
        canvas.scale(-1f, -1f);
        canvas.drawRect(rectF, mPaint);// 正反都反转，第三象限
        canvas.scale(1f, 1f);   // 第二种方法比前一种多了两个参数，用来控制缩放中心位置的
        canvas.drawRect(rectF, mPaint);// 第四象限
        RectF rectF1 = new RectF(0, -400, 400, 0);
        canvas.scale(-1f, 1f, 0, -50);  //第二象限
        for (int i = 0; i < 20; i++) {
            canvas.scale(0.9f, 0.9f);
            canvas.drawRect(rectF1, mPaint);
        }
        canvas.translate(width / 2, height / 4 * 2);
        Log.d("heightheight", "height:" + height);


        // 旋转(rotate)
//        RectF rectF2 = new RectF(0, -400, 400, 0);
//        canvas.drawRect(rectF2, mPaint);
//        canvas.rotate(180); // 旋转180度
//        canvas.drawRect(rectF2, mPaint);
//        canvas.rotate(90, 50, 0);
//        canvas.scale(0.5f,0.5f);
//        canvas.drawRect(rectF2, mPaint);


//        // 保存全部状态
//        canvas.save();
//        // 根据saveFlags参数保存一部分状态
//        canvas.save (int saveFlags)

//        //回滚到之前的状态
//        canvas.restore();

    }
}
