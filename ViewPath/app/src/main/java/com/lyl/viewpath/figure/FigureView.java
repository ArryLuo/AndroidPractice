package com.lyl.viewpath.figure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/25.
 */
public class FigureView extends View {

    private int wiedth, height;

    private Paint mPaint;


    public FigureView(Context context) {
        super(context);
    }

    public FigureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wiedth = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(wiedth / 2, height / 2);

//        Android与填充模式相关的方法 , 这些都是Path中的方法。
//        setFillType	设置填充规则
//        getFillType	获取当前填充规则
//        isInverseFillType	判断是否是反向(INVERSE)规则
//        toggleInverseFillType	切换填充规则(即原有规则与反向规则之间相互切换)

//        Android中的填充模式有四种，是封装在Path中的一个枚举。
//        EVEN_ODD	奇偶规则
//        INVERSE_EVEN_ODD	反奇偶规则
//        WINDING	非零环绕数规则
//        INVERSE_WINDING	反非零环绕数规则


        Path path = new Path();

        // 添加大正方形
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        // 添加小正方形
        path.addRect(-100, -100, 100, 100, Path.Direction.CCW);


        path.setFillType(Path.FillType.EVEN_ODD);                      // 奇偶规则
//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);              // 反奇偶规则
//        path.setFillType(Path.FillType.WINDING);                       // 非零环绕数规则
//        path.setFillType(Path.FillType.INVERSE_WINDING);               // 反非零环绕数规则

        canvas.drawPath(path, mPaint);
    }
}
