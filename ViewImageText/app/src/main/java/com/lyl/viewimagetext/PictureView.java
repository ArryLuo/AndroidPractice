package com.lyl.viewimagetext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/7.
 */
public class PictureView extends View {

    private int width, height;

    private Picture mPicture = new Picture();

    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        recording();
    }

    public PictureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void recording() {
        // 开始录制
        Canvas canvas = mPicture.beginRecording(500, 500);

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, paint);

        mPicture.endRecording();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), 200));

        // 将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
        // 并不是自动缩放的，也不是剪裁Picture，每次都从Picture的左上角开始绘制。
        pictureDrawable.setBounds(0, 0, mPicture.getWidth(), mPicture.getHeight());
        pictureDrawable.draw(canvas);
    }
}
