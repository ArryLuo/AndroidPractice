package com.lyl.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Wing_Li
 * 2016/11/1.
 */
public class PieView extends View {

    private int width, height;

    private float mStartAngle = 0;

    private ArrayList<PieData> mData;

    private Paint mPaint = new Paint();

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        if (null == mData) return;
        float currentStartAngle = mStartAngle;          // 当前起始角度
        canvas.translate(width / 2, height / 2);        // 将画布的坐标原点移到屏幕中心
        float red = (float) (Math.min(width, height) / 2 * 0.9);// 饼状图半径
        RectF rectF = new RectF(-red, -red, red, red);

        for (int i = 0; i < mData.size(); i++) {
            PieData pieData = mData.get(i);
            int color = pieData.getColor();
            mPaint.setColor(color);
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, mPaint);
            currentStartAngle += pieData.getAngle();
        }
    }

    public float getmStartAngle() {
        return mStartAngle;
    }

    public void setmStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();   // 刷新
    }

    public ArrayList<PieData> getmData() {
        return mData;
    }

    public void setmData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate();   // 刷新
    }

    private void initData(ArrayList<PieData> mData) {
        if (null == mData || mData.size() == 0) return;

        int sumValue = 0;
        for (PieData pie : mData) {
            sumValue += pie.getValue();             //计算数值总和
        }

        float sumAngle = 0;
        for (PieData pie : mData) {
            float percentage = pie.getValue() / sumValue;// 机选百分比
            float angle = percentage * 360;             //计算角度

            pie.setPercentage(percentage);
            pie.setAngle(angle);
            sumAngle += angle;
        }


    }
}
