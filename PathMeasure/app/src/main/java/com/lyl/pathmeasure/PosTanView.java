package com.lyl.pathmeasure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * http://www.gcssloop.com/customview/Path_PathMeasure
 * Wing_Li
 * 2016/11/30.
 */
public class PosTanView extends View {

    private int width, height;
    private Paint mPaint;

    private float currentValue = 0;     // 用于纪录当前的位置,取值范围[0,1]映射Path的整个长度

    private float[] pos;                // 当前点的实际位置
    private float[] tan;                // 当前点的tangent值,用于计算图片所需旋转的角度
    private Bitmap mBitmap;             // 箭头图片
    private Matrix mMatrix;             // 矩阵,用于对图片进行一些操作

    public PosTanView(Context context) {
        super(context);
    }

    public PosTanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    private void init(Context context) {
        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;       // 缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.arrow, options);
        mMatrix = new Matrix();

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(width / 2, height / 2);

        Path path = new Path();
        path.addCircle(0, 0, 200, Path.Direction.CW);

        PathMeasure measure = new PathMeasure(path, false);

        currentValue += 0.005;
        if (currentValue >= 1) {
            currentValue = 0;
        }

//        distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
//        pos	该点的坐标值	当前点在画布上的位置，有两个数值，分别为x，y坐标。
//        tan	该点的正切值	当前点在曲线上的方向，使用 Math.atan2(tan[1], tan[0]) 获取到正切角的弧度值。
//        // 获取当前位置的坐标以及趋势
//        measure.getPosTan(measure.getLength() * currentValue, pos, tan);
//
//        float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);// 计算图片旋转角度
//
//        mMatrix.reset();// 重置Matrix
//        mMatrix.postRotate(degrees, mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);// 旋转图片
//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth() / 2, pos[1] - mBitmap.getHeight() / 2);


        // getMatrix
        // 得到路径上某一长度的位置以及该位置的正切值的矩阵
//        distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
//        matrix	根据 falgs 封装好的matrix	会根据 flags 的设置而存入不同的内容
//        flags	规定哪些内容会存入到matrix中	可选择:POSITION_MATRIX_FLAG(位置) | ANGENT_MATRIX_FLAG(正切)
        measure.getMatrix(measure.getLength() * currentValue, mMatrix, PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2);   // <-- 将图片绘制中心调整到与当前点重合(注意:此处是前乘pre)

        canvas.drawPath(path, mPaint);
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);

        invalidate();
    }
}
