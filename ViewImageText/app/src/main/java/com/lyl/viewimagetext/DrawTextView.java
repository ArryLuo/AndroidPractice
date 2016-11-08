package com.lyl.viewimagetext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/8.
 */
public class DrawTextView extends View {

    private Paint mPaint;
    private int width, height;

    public DrawTextView(Context context) {
        super(context);
    }

    public DrawTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);        // 设置颜色
        mPaint.setStyle(Paint.Style.FILL);   // 设置样式
        mPaint.setTextSize(50);
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
        String str = "ABCDEFGHIJKMLI";

        // 参数分别为 (文本 基线x 基线y 画笔)
        canvas.drawText(str, 100, 50, mPaint);

        // 参数分别为 (字符串 开始截取位置 结束截取位置 基线x 基线y 画笔)
        canvas.drawText(str, 1, 6, 100, 100, mPaint);

        // 字符数组(要绘制的内容),截取字符串使用起始位置(index)和长度(count)来确定。
        char[] chars = str.toCharArray();
        // 参数为 (字符数组 起始坐标 截取长度 基线x 基线y 画笔)
        canvas.drawText(chars, 1, 9, 100, 150, mPaint);



    }
}
