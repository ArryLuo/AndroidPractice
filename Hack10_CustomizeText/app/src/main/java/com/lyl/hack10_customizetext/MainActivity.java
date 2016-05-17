package com.lyl.hack10_customizetext;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
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

        String name = "小可爱：";
        String msg = name + "小今天心情不错，买张彩票。";

        Spannable ss = new SpannableString(msg);

        //设置 名称，点击事件
        ss.setSpan(clickableSpan, 0, name.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        //设置 名称之后的 心情
        ss.setSpan(new ForegroundColorSpan(Color.RED), name.length(), msg.length() - 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTxt.setText(ss);
        //设置超链接为可点击状态
        mTxt.setMovementMethod(LinkMovementMethod.getInstance());
    }

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
        Toast toast = Toast.makeText(MyApplication.getContext(), str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }
//        1、BackgroundColorSpan 背景色
//        2、ClickableSpan 文本可点击，有点击事件
//        3、ForegroundColorSpan 文本颜色（前景色）
//        4、MaskFilterSpan 修饰效果，如模糊(BlurMaskFilter)、浮雕(EmbossMaskFilter)
//        5、MetricAffectingSpan 父类，一般不用
//        6、RasterizerSpan 光栅效果
//        7、StrikethroughSpan 删除线（中划线）
//        8、SuggestionSpan 相当于占位符
//        9、UnderlineSpan 下划线
//        10、AbsoluteSizeSpan 绝对大小（文本字体）
//        11、DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
//        12、ImageSpan 图片
//        13、RelativeSizeSpan 相对大小（文本字体）
//        14、ReplacementSpan 父类，一般不用
//        15、ScaleXSpan 基于x轴缩放
//        16、StyleSpan 字体样式：粗体、斜体等
//        17、SubscriptSpan 下标（数学公式会用到）
//        18、SuperscriptSpan 上标（数学公式会用到）
//        19、TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
//        20、TypefaceSpan 文本字体
//        21、URLSpan 文本超链接

//        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)、
//        Spanned.SPAN_INCLUSIVE_EXCLUSIVE(前面包括，后面不包括)、
//        Spanned.SPAN_EXCLUSIVE_INCLUSIVE(前面不包括，后面包括)、
//        Spanned.SPAN_INCLUSIVE_INCLUSIVE(前后都包括)


}
