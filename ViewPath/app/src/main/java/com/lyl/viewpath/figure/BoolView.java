package com.lyl.viewpath.figure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Path之完结篇
 * http://www.gcssloop.com/customview/Path_Over
 * Created by lyl on 2016/11/25.
 */

public class BoolView extends View {

    private int width, height;
    private Paint mPaint;

    public BoolView(Context context) {
        super(context);
    }

    public BoolView(Context context, AttributeSet attrs) {
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
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(width / 2, height / 2);

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CCW);

        // 布尔运算方法

        // DIFFERENCE	差集	Path1中减去Path2后剩下的部分
        // REVERSE_DIFFERENCE	差集	Path2中减去Path1后剩下的部分
        // INTERSECT	交集	Path1与Path2相交的部分
        // UNION	并集	包含全部Path1和Path2
        // XOR	异或	包含Path1与Path2但不包括两者相交的部分
        path1.op(path2, Path.Op.DIFFERENCE);
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);


        // 计算边界
        // computeBounds (RectF bounds, boolean exact)
        RectF rect1 = new RectF();
        path1.computeBounds(rect1, true);
        path1.addRect(rect1, Path.Direction.CW);


        // 重置路径
        // 方法	      是否保留FillType设置	是否保留原有数据结构
        // reset	      是	                     否
        // rewind	      否	                     是

        // 这个两个方法应该何时选择呢？
        // 选择权重: FillType > 数据结构
        // 因为“FillType”影响的是显示效果，而“数据结构”影响的是重建速度。

        canvas.drawPath(path1, mPaint);
    }
}
