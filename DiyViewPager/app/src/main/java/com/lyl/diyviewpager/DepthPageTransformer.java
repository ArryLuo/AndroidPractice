package com.lyl.diyviewpager;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Wing_Li
 * 2016/5/30.
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;

    /**
     * 将属性转换应用到给定的页。
     *
     * @param view     将转换应用到该页
     * @param position 给定界面的位置相对于屏幕中心的偏移量。
     *                 position ==  0 ：当前界面位于屏幕中心的时候
     *                 position ==  1 ：当前Page刚好滑出屏幕右侧
     *                 position == -1 ：当前Page刚好滑出屏幕左侧
     *                 在滑动过程中涉及到两个Page，当前页和下一页，而它们 的position值是相反的（因为是相对运动,一个滑入一个滑出）
     *                 比如，页面A向右滑动到屏幕一半，页面B也正好处于一半的位置，那么A和B的 position为：0.5 和 -0.5
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // 这个页面在屏幕的左边
            view.setAlpha( MIN_SCALE );

        } else if (position < 1) {

            // 使用默认幻灯片过渡到左页，（包括从左边滑回来）
            if (position < 0) {// [-1,0]
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs( position ));

                view.setAlpha( scaleFactor );
                view.setTranslationX( 0 );
                view.setScaleX( 1 );//水平方向缩放比例
                view.setScaleY( 1 );//竖直方向缩放比例

            } else { // (0,1]
                // 淡出页面。
                view.setAlpha( 1 - position );

                // 抵消默认滑动转换
                view.setTranslationX( pageWidth * -position );//水平方向的移动距离

                // 将页面降下 (在 MIN_SCALE 和 1 之间)
                float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs( position ));
                view.setScaleX( scaleFactor );
                view.setScaleY( scaleFactor );
            }
        } else { // (1,+Infinity]
            // 这个页面在屏幕的右边
            view.setAlpha( 0 );
        }
    }
}