package com.lyl.hack_canvasanime.view;

import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * 小圆球
 * 
 * @author Wing_Li
 */
public class Rectangle extends View {
	public static final int MAX_SIZE = 20;
	/** 透明度 **/
	private static final int ALPHA = 255;
	/** 原始位置 **/
	private int mCoordX = 0;
	private int mCoordY = 0;
	/** 方块大小 **/
	private int mRealSize = 20;
	/** 之后的位置 **/
	private int mSpeedX = 3;
	private int mSpeedY = 3;

	/** 方向 **/
	private boolean goRight = true;
	private boolean goDown = true;

	/** 用来装小球的View，用到了他的宽高，防止圆球滚出控件 **/
	private DrawView mDrawView;

	private Paint mInnerPaint;

	public Rectangle(Context context, DrawView drawView) {
		super(context);
		mDrawView = drawView;

		// 设置画笔的属性
		mInnerPaint = new Paint();
		mInnerPaint.setARGB(ALPHA, 255, 0, 0);// 设置成红色
		mInnerPaint.setAntiAlias(true);// 消除锯齿
	}

	private void moveTo(int goX, int goY) {

		// 检查的边界，如果到达边界，改变方向
		if (mCoordX > (mDrawView.width - MAX_SIZE)) {
			goRight = false;
		}

		if (mCoordX < 0) {
			goRight = true;
		}

		if (mCoordY > (mDrawView.height - MAX_SIZE)) {
			goDown = false;
		}
		if (mCoordY < 0) {
			goDown = true;
		}

		// 设置移动的位置
		if (goRight) {
			mCoordX += goX;
		} else {
			mCoordX -= goX;
		}
		if (goDown) {
			mCoordY += goY;
		} else {
			mCoordY -= goY;
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		float toX = mCoordX + mRealSize;
		float toY = mCoordY + mRealSize;

		// 绘制圆形图案
		canvas.drawCircle(toX, toY, mRealSize, mInnerPaint);
	}

	public void setARGB(int a, int r, int g, int b) {
		mInnerPaint.setARGB(a, r, g, b);
	}

	public void setX(int newValue) {
		mCoordX = newValue;
	}

	public float getX() {
		return mCoordX;
	}

	public void setY(int newValue) {
		mCoordY = newValue;
	}

	public float getY() {
		return mCoordY;
	}

	/**
	 * 在外部调用此方法即向指定位置移动
	 */
	public void move() {
		moveTo(mSpeedX, mSpeedY);
	}

	public int getSpeedX() {
		return mSpeedX;
	}

	public void setSpeedX(int speedX) {
		mSpeedX = speedX;
	}

	public int getmSpeedY() {
		return mSpeedY;
	}

	public void setSpeedY(int speedY) {
		mSpeedY = speedY;
	}

	public void setSize(int newSize) {
		mRealSize = newSize;
	}

	public int getSize() {
		return mRealSize;
	}
}