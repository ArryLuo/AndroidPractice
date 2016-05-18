package com.lyl.hack10_customizetext;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxt = (TextView) findViewById(R.id.txt);
        initView();
    }

    private void initView() {
        String name = "小可爱：";//模拟名称
        String msg = name + "今天心情不错，去买张彩票。";//模拟说说

        SpannableString ss = new SpannableString(msg);
        //名称的点击事件
        ss.setSpan(clickableSpan, 0, name.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //说说的字体样式
        ss.setSpan(new ForegroundColorSpan(Color.RED), name.length(), msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTxt.setText(ss);
        mTxt.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接为可点击状态
    }

    /**
     * 名称的点击事件
     */
    private ClickableSpan clickableSpan = new ClickableSpan() {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false); //去掉下划线
            ds.setColor(Color.BLUE);//设置点击前的颜色 
        }

        @Override
        public void onClick(View widget) {
            toast(((TextView) widget).getText());
        }
    };

    private void toast(CharSequence str) {
        //如果这里加了 String.valueOf(str)，弹出的提示，就会没有样式
        Toast toast = Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);//改变Toast弹出的位置
        toast.show();
    }
}
