package com.lyl.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Wing_Li
 * 2016/10/14.
 */
public class LearnView extends View {

    private Context mContext;
    private Paint mPaint = new Paint();

    public LearnView(Context context) {
        super(context);
    }

    public LearnView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public LearnView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initPait();
    }


    private void initPait() {
        mPaint.setColor(Color.RED);

        mPaint.setStyle(Paint.Style.FILL);//设置画笔模式为填充
        //STROKE                //描边
        //FILL                  //填充
        //FILL_AND_STROKE       //描边加填充

        mPaint.setStrokeWidth(10f);
    }


    /**
     * 但它们其实不是宽和高， 而是由宽、高和各自方向上对应的测量模式来合成的一个值：
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);      //取出宽度的确切数值
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);      //取出宽度的测量模式

        int heightsize = MeasureSpec.getSize(heightMeasureSpec);    //取出高度的确切数值
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);    //取出高度的测量模式

        setMeasuredDimension(widthsize, heightsize);
    }


    /**
     * 实际绘制的部分，也就是我们真正关心的部分，使用的是Canvas绘图。
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制点：
        canvas.drawPoint(50, 50, mPaint);// 在坐标(50,50)位置绘制一个点
        canvas.drawPoints(new float[]{  //绘制一组点，坐标位置由float数组指定
                50, 100,//
                100, 100,//
                150, 100//
        }, mPaint);

        // 绘制线：
        canvas.drawLine(50, 200, 100, 250, mPaint);// 在坐标(50,200)(200,200)之间绘制一条直线
        canvas.drawLines(new float[]{ // 绘制一组线 每四数字(两个点的坐标)确定一条线
                50, 300, 300, 300,//
                50, 350, 300, 350//
        }, mPaint);

        // 绘制矩形：
        // 第一种
        canvas.drawRect(50, 400, 200, 500, mPaint);
        // 第二种
        Rect rect = new Rect(250, 400, 400, 450);
        canvas.drawRect(rect, mPaint);
        // 第三种
        RectF rectF = new RectF(500, 400, 550, 500);
        canvas.drawRect(rectF, mPaint);

        //绘制圆角矩形：
        RectF rectF1 = new RectF(50, 550, 250, 650);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(rectF1, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRoundRect(rectF1, 50, 50, mPaint);

        //绘制圆：
        mPaint.setStrokeWidth(20f);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(50, 750, 50, mPaint);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(200, 750, 50, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(450, 750, 50, mPaint);

        //绘制圆弧：
        RectF rectF2 = new RectF(300, 100, 400, 300);
        canvas.drawArc(rectF2, 0, 90, false, mPaint);
        RectF rectF3 = new RectF(450, 100, 550, 300);
        canvas.drawArc(rectF3, 0, 90, true, mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //我们只需关注 宽度(w), 高度(h) 即可，这两个参数就是View最终的大小。
    }

    /**
     * 它用于确定子View的位置，在自定义ViewGroup中会用到，他调用的是子View的layout函数。
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
}
