package com.lyl.viewgroup.view;

import com.lyl.viewgroup.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

//Android中所有界面都是由View和ViewGroup组成，
// View是 所有基本组件的基类，
// ViewGroup是 拼装这些组件的容器。

//这个用的是 ViewGroup
//绘制布局由两个遍历的过程组成：测量过程 和 布局过程
//onMeasure() 完成测量过程：测量所有视图的宽度和高度
//onLayout() 完成布局过程：利用上步计算出的测量信息，布局所有子视图

public class CascadeLayout extends ViewGroup {

	// 子布局位于 左边 和 上面 的距离
	private int mHorizontalSpacing;
	private int mVerticalSpacing;

	/**
	 * 当通过XML文件创建该视图会调用这个
	 */
	public CascadeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 从自定义属性中获取值
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CascadeLayout);

		try {
			// 获取用户指定的大小，就是我们刚开始指定的那个40dp
			// 如果没有指定，就使用默认值
			mHorizontalSpacing = ta.getDimensionPixelSize(R.styleable.CascadeLayout_horizontal_spacing, getResources()
					.getDimensionPixelSize(R.dimen.cascade_horizontal_spacing));
			mVerticalSpacing = ta.getDimensionPixelSize(R.styleable.CascadeLayout_vertical_spacing, getResources()
					.getDimensionPixelSize(R.dimen.cascade_vertical_spacing));

		} finally {
			ta.recycle();
		}
	}

	/**
	 * 测量所有视图的宽度和高度
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// ！！！在编写onMeasure方法之前，先创建自定义LayoutParams类，保存每个子视图的x、y轴的位置。

		// 使用宽和高 计算布局的最终大小，以及子视图的x、y轴的位置
		int width = getPaddingLeft();
		int height = getPaddingTop();

		// 1. 获取子视图的数量
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {

			View child = getChildAt(i);

			// 2. 让 每个子视图 测量自己
			measureChild(child, widthMeasureSpec, heightMeasureSpec);

			LayoutParams lp = (LayoutParams) child.getLayoutParams();

			// 3. 在LayoutParams中保存每个子视图的x、y坐标，以便在onLayout()中布局
			if (lp.horizontalSpacing >= 0) {
				lp.x = width + lp.horizontalSpacing;
				width += mHorizontalSpacing + lp.horizontalSpacing;
			} else {
				lp.x = width;
				width += mHorizontalSpacing;
			}

			if (lp.verticalSpacing >= 0) {
				lp.y = height + lp.verticalSpacing;
				height += mVerticalSpacing + lp.verticalSpacing;
			} else {
				lp.y = height;
				height += mVerticalSpacing;
			}
		}

		// 4. 计算整体的宽、高
		width += getChildAt(getChildCount() - 1).getMeasuredWidth() + getPaddingRight();
		height += getChildAt(getChildCount() - 1).getMeasuredHeight() + getPaddingBottom();

		// 5. 使用计算的 宽、高 设置整个布局的测量尺寸
		setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
	}

	/**
	 * 利用上步计算出的测量信息，布局所有子视图
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			LayoutParams lp = (LayoutParams) child.getLayoutParams();
			child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y + child.getMeasuredHeight());
		}
	}

	// =======要使用LayoutParams，通常还需要重写以下方法，==============================
	// 这些方法通常都是相同的，可参考 LinearLayout 源码

	@Override
	protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
		return p instanceof LayoutParams;
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutParams(getContext(), attrs);
	}

	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new LayoutParams(p.width, p.height);
	}

	/** 保存每个子视图的x、y轴的位置 **/
	public static class LayoutParams extends ViewGroup.LayoutParams {
		int x;
		int y;
		// 子布局距离 左边 和 上面的距离
		public int verticalSpacing;
		public int horizontalSpacing;

		public LayoutParams(Context c, AttributeSet attrs) {
			super(c, attrs);

			// 1. 从自定义属性中获取值
			TypedArray ta = c.obtainStyledAttributes(attrs, R.styleable.CascadeLayout_LayoutParams);

			// 2.将获取的值拿到，以便onMeasure()使用
			try {
				horizontalSpacing = ta.getDimensionPixelSize(
						R.styleable.CascadeLayout_LayoutParams_layout_horizontal_spacing, -1);
				verticalSpacing = ta.getDimensionPixelSize(
						R.styleable.CascadeLayout_LayoutParams_layout_vertical_spacing, -1);
			} finally {
				ta.recycle();
			}
		}

		public LayoutParams(int width, int height) {
			super(width, height);
		}
	}

}
