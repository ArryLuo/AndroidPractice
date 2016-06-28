package com.lyl.viewanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;


public class MainActivity extends Activity {

    private TextView mTextDots;
    private ViewFlipper mViewFlipper;

    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewFlipper();

        setTextSwitcher();

        setImageSwitcher();
    }

    /**
     * 设置图片切换
     */
    private void setImageSwitcher() {
        mImageSwitcher = (ImageSwitcher) findViewById(R.id.image_switcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.drawable.abcde_b);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
    }

    public void changeImage(View view) {
        mImageSwitcher.setImageResource(R.drawable.abcde_c);
    }

    /**
     * 设置文字切换
     */
    private void setTextSwitcher() {
        mTextSwitcher = (TextSwitcher) findViewById(R.id.text_switcher);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(MainActivity.this);
                tv.setText("11111111111111111");
                return tv;
            }
        });
    }

    public void changeText(View view) {
        mTextSwitcher.setText("哼哼哈哈，闪亮 登场 ...");
    }

    /**
     * 设置图片轮播
     */
    private void setViewFlipper() {
        mTextDots = (TextView) findViewById(R.id.text);
        mViewFlipper = (ViewFlipper) findViewById(R.id.flipper);

        mViewFlipper.addView(getImageView(R.drawable.abcde_a));
        mViewFlipper.addView(getImageView(R.drawable.abcde_b));
        mViewFlipper.addView(getImageView(R.drawable.abcde_c));

        mViewFlipper.setInAnimation(this, R.anim.push_up_in);
        mViewFlipper.setOutAnimation(this, R.anim.push_up_out);

        mViewFlipper.setFlipInterval(3000);
        mViewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mTextDots.setText((mViewFlipper.getDisplayedChild() + 1) + "/" + mViewFlipper.getChildCount());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mTextDots.setText((mViewFlipper.getDisplayedChild() + 1) + "/" + mViewFlipper.getChildCount());
        mViewFlipper.startFlipping();
    }
/*
    ViewFlipper 常用方法

    setInAnimation      设置View进入屏幕时候使用的动画
    setOutAnimation     设置View退出屏幕时候使用的动画
    showPrevious        显示ViewFlipper里面的上一个View
    showNext            显示ViewFlipper里面的下一个View
    setFlipInterval     设置View之间切换的时间间隔
    startFlipping       使用setFlipInterval方法设置的时间间隔来开始切换所有的View,切换会循环进行
    stopFlipping        停止View切换
    isFlipping          用来判断View切换是否正在进行
    setDisplayedChild   切换到指定子View
*/

    public ImageView getImageView(int res) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(res);
        return imageView;
    }

    public TextView getTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
