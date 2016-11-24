package com.lyl.viewpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 第1组: moveTo、 setLastPoint、 lineTo 和 close
 * <p/>
 * Wing_Li
 * 2016/11/23.
 */
public class Path1View extends View {

    private int width, height;
    private Paint mPaint;

    public Path1View(Context context) {
        super(context);
    }


    public Path1View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
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

        Path path = new Path();

        // 连接直线
        path.lineTo(400, 400);

        // 移动下一次操作的起点位置
        // path.moveTo(400, 200);

        // 设置之前操作的最后一个点位置
        path.setLastPoint(400, 200);

        path.lineTo(400, 0);

        // 连接直线
        path.close();

        // 基于当前坐标系的坐标(绝对位置)
        path.moveTo(500, 0);
        path.lineTo(500, 150);

        // rXxx方法:
        // 基于当前点的位移(相对位置)
        path.moveTo(600, 0);
        path.rLineTo(100, 150);// 即 (600+100, 0+150) 这个位置

        canvas.drawPath(path, mPaint);
    }

}
