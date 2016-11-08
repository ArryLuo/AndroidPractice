package com.lyl.viewimagetext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/8.
 */
public class CheckView extends View {

    private Context mContext;
    private int width, height;
    private Handler mHandler;

    private Paint mPaint;
    private Bitmap okBitmap;

    private static final int ANIM_NULL = 0;// 动画状态-没有
    private static final int ANIM_CHECK = 1;//动画状态-开启
    private static final int ANIM_UNCHECK = 2;//动画状态-结束

    private int animCurrentPage = -1;   // 当前页码
    private int animMaxPage = 13;       // 总页数
    private int animDuration = 500;     // 动画时长
    private int animState = ANIM_NULL;  // 动画状态

    private boolean isCheck = false;    //是否选中状态


    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.checkmark);

        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL) return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }
                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }

        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width / 2, height / 2);

        canvas.drawCircle(0, 0, 180, mPaint);

        int sideLength = okBitmap.getHeight();

        Rect rect = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        Rect rect2 = new Rect(-150, -150, 150, 150);

        canvas.drawBitmap(okBitmap, rect, rect2, mPaint);
    }

    /**
     * 选择
     */
    public void check() {
        if (animState != ANIM_NULL || isCheck) return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    /**
     * 取消选择
     */
    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck)) return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    /**
     * 设置动画的时间
     */
    public void setDuration(int animDuration) {
        if (animDuration < 0) return;
        this.animDuration = animDuration;
    }

    /**
     * 设置背景的颜色
     */
    public void setBackGroundColor(int color) {
        mPaint.setColor(color);
    }


}
