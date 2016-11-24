package com.lyl.viewpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Wing_Li
 * 2016/11/23.
 */
public class Path2View extends View {

    private int width, height;
    private Paint mPaint;

    public Path2View(Context context) {
        super(context);
    }

    public Path2View(Context context, AttributeSet attrs) {
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
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        path.addRect(200, 0, 500, 300, Path.Direction.CW);// 顺时针
        path.setLastPoint(200, 200);

        path.moveTo(600, 0);
        path.addRect(600, 0, 900, 300, Path.Direction.CCW);// 逆时针
        path.setLastPoint(850, 150);

        path.moveTo(0, 350);
        RectF ovalF1 = new RectF(50, 350, 250, 550);
        path.addArc(ovalF1, 0, 270);// 直接添加一个圆弧到path中

        path.moveTo(300, 350);
        RectF ovalF2 = new RectF(300, 350, 500, 550);
        path.arcTo(ovalF2, 0, 270);// 添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点

        path.moveTo(550, 350);
        RectF ovalF3 = new RectF(550, 350, 750, 550);
        path.arcTo(ovalF3, 0, 270, true);// 添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点


        // 判断path中是否包含内容。
        path.isEmpty();


        // 判断path是否是一个矩形，如果是一个矩形的话，会将矩形的信息存放进参数rect中。
        RectF rect = new RectF();
        boolean b = path.isRect(rect);
        Log.e("Rect", "isRect:" + b + "| left:" + rect.left + "| top:" + rect.top + "| right:" + rect.right + "| bottom:" + rect.bottom);
        // log 输出结果: com.sloop.canvas E/Rect: isRect:true| left:0.0| top:0.0| right:400.0| bottom:400.0


        path.moveTo(0, 700);
        // offset (float dx, float dy, Path dst)
        // 就是对path进行一段平移，它和Canvas中的translate作用很像，但Canvas作用于整个画布，而path的offset只作用于当前path。
        Path p = new Path();                     // path中添加一个圆形(圆心在坐标原点)
        p.addCircle(200, 700, 100, Path.Direction.CW);

        Path d = new Path();                      // dst中添加一个矩形
        d.addRect(200, 700, 400, 900, Path.Direction.CW);

        p.offset(1000, 500, d);
        // dst不为空	将当前path平移后的状态存入dst中，不会影响当前path
        // 当dst中存在内容时，dst中原有的内容会被清空，而存放平移后的path。

        path.addPath(p);
        canvas.drawPath(path, mPaint);
    }

}
