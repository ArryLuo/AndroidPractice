package com.lyl.diyviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Wing_Li
 * 2016/6/2 0002.
 */
public class RotateUpPageTransformer implements ViewPager.PageTransformer {
    private static final float DEFAULT_MAX_ROTATE = 15.0f;
    private float mMaxRotate = DEFAULT_MAX_ROTATE;

    @Override
    public void transformPage(View view, float position) {
        if (position < -1)//左边
        {
            view.setRotation( mMaxRotate * -1 ); //逆时针旋转
            view.setPivotX( view.getWidth() );//设定旋转轴的位置
            view.setPivotY( view.getHeight() );
        } else if (position <= 1) {
            if (position < 0)//往左划（包括滑回来）
            {
                //绕 底部中心 开始旋转，旋转点 不断向左偏移
                view.setPivotX( view.getWidth() * (0.5f + 0.5f * (-position)) );
                view.setPivotY( view.getHeight() );
                view.setRotation( mMaxRotate * position );
            } else//往右划（包括滑回来）
            {
                //绕 左下角的点 开始旋转，旋转点 不断向右偏移，直到中心
                view.setPivotX( view.getWidth() * 0.5f * (1 - position) );
                view.setPivotY( view.getHeight() );
                view.setRotation( mMaxRotate * position );
            }
        } else { // 在右边
            view.setRotation( mMaxRotate );//顺时针
            view.setPivotX( view.getWidth() * 0 );
            view.setPivotY( view.getHeight() );
        }
    }
}
