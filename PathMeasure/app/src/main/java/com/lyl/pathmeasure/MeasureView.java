package com.lyl.pathmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * http://www.gcssloop.com/customview/Path_PathMeasure
 * Wing_Li
 * 2016/11/30.
 */
public class MeasureView extends View {

    private int width, height;
    private Paint mPaint;

    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
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
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
        mPaint.setTextSize(21);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width / 2, height / 2);

        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        PathMeasure measure1 = new PathMeasure(path, false);
        PathMeasure measure2 = new PathMeasure(path, true);// forceClosed 为 true 时，测量结果可能会比 Path 实际长度稍长一点，获取到到是该 Path 闭合时的状态。

        Path dst = new Path();
        dst.lineTo(-200, -0);// 截取的 Path 将会添加到 cutSave 中	注意: 是添加，而不是替换

//         用于获取Path的一个片段 : true 表示截取成功，结果存入dst中，false 截取失败，不会改变dst中内容
        boolean isSuccsee =  measure1.getSegment(200, 600, dst, true); //如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状
//        measure1.getSegment(200, 600, cutSave, false); // 如果 startWithMoveTo 为 false，则会将截取出来的 Path 片段的起始点移动到 dst 的最后一个点，以保证 dst 的连续性。


//        nextContour 跳转到下一条曲线
//        不论是 getLength , getgetSegment 或者是其它方法，都只会在其中第一条线段上运行
        PathMeasure m = new PathMeasure(dst, false);
        float len1 = m.getLength();
        m.nextContour();
        float len2 = m.getLength();
        Log.i("LEN", "len1=" + len1);                              // 输出两条路径的长度
        Log.i("LEN", "len2=" + len2);

        canvas.drawPath(dst, mPaint);
    }
}
