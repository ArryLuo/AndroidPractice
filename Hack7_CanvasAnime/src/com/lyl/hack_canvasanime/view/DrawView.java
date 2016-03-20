package com.lyl.hack_canvasanime.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class DrawView extends View {
	/** 定义不同的小块 **/
	private Rectangle mRectangle1;
	private Rectangle mRectangle2;
	private Rectangle mRectangle3;
	/** 布局的宽高 **/
	public int width;
	public int height;
	/** 定义了一个开关 **/
	private boolean isRuning;

	private Context mContext;

	/** 程序内实例化时只会调这个方法，只传入Context即可。就是在代码中new的时候 **/
	public DrawView(Context context) {
		this(context, null);
	}

	/** 用于layout文件实例化，会把XML内的参数通过AttributeSet带入到View内。就是在xml布局中用的时候 **/
	public DrawView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/** 主题的style信息，也会从XML里带入 **/
	public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		isRuning = true;// 默认小球是运动的
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 得到控件的宽高，为了不让小球滚出边界
		width = getMeasuredWidth();
		height = getMeasuredHeight();

		// 在不同位置，创建了三个小球
		mRectangle1 = createF(mContext, 0, 0, 3, 3);
		mRectangle2 = createF(mContext, 0, 300, 7, 10);
		mRectangle3 = createF(mContext, 500, 0, 20, 20);
	}

	private Rectangle createF(Context context, int x, int y, int toX, int toY) {
		Rectangle mRectangle = new Rectangle(context, this);
		mRectangle.setARGB(255, 255, 0, 0);

		// 设置初始位置
		mRectangle.setX(x);
		mRectangle.setY(y);

		// 每次移动的距离，可以看做是速度
		mRectangle.setSpeedX(toX);
		mRectangle.setSpeedY(toY);
		return mRectangle;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// postInvalidate()子线程请求调用onDraw() ，系统自动调用，不允许手动调用。
		// invalidate();主线程请求调用onDraw()

		if (isRuning) {
			invalidate();// 不断的刷新界面

			mRectangle1.move();// 调用圆块，移动了位置
			mRectangle1.onDraw(canvas);// 重绘界面，即改变了圆块位置

			mRectangle2.move();
			mRectangle2.onDraw(canvas);

			mRectangle3.move();
			mRectangle3.onDraw(canvas);
		}
	}

	public boolean isRuning() {
		return isRuning;
	}

	public void setRuning(boolean isRuning) {
		this.isRuning = isRuning;
	}

}