package com.lyl.viewpath.bazier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/24.
 */
public class Bezier2View extends View {

    private int centerX, centerY;

    private Paint mPaint;
    private Paint mTouchPaint;

    private PointF start, end;
    private PointF contorl1, contorl2;
    private int poinTyle;

    public static final int POINT_ONE = 1;
    public static final int POINT_TWO = 2;

    public Bezier2View(Context context) {
        super(context);
    }

    public Bezier2View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        contorl1.x = centerX - 100;
        contorl1.y = centerY - 100;
        contorl2.x = centerX + 100;
        contorl2.y = centerY - 100;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);
        mPaint.setAntiAlias(true);

        mTouchPaint = new Paint();
        mTouchPaint.setColor(Color.BLACK);
        mTouchPaint.setStrokeWidth(20);
        mTouchPaint.setAntiAlias(true);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        contorl1 = new PointF(0, 0);
        contorl2 = new PointF(0, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (poinTyle) {
            case POINT_ONE:
                contorl1.x = event.getX();
                contorl1.y = event.getY();
                break;
            case POINT_TWO:
                contorl2.x = event.getX();
                contorl2.y = event.getY();
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mPaint);
        canvas.drawPoint(end.x, end.y, mPaint);
        switch (poinTyle) {
            case POINT_ONE:
                canvas.drawPoint(contorl1.x, contorl1.y, mTouchPaint);
                canvas.drawPoint(contorl2.x, contorl2.y, mPaint);
                break;
            case POINT_TWO:
                canvas.drawPoint(contorl1.x, contorl1.y, mPaint);
                canvas.drawPoint(contorl2.x, contorl2.y, mTouchPaint);
                break;
        }

        // 绘制辅助线
        mPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, contorl1.x, contorl1.y, mPaint);
        canvas.drawLine(contorl1.x, contorl1.y, contorl2.x, contorl2.y, mPaint);
        canvas.drawLine(contorl2.x, contorl2.y, end.x, end.y, mPaint);

        // 绘制曲线
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(8);

        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(contorl1.x, contorl1.y, contorl2.x, contorl2.y, end.x, end.y);

        canvas.drawPath(path, mPaint);
    }

    public void setTouchPoint(int poinTyle) {
        this.poinTyle = poinTyle;
        invalidate();
    }

}
