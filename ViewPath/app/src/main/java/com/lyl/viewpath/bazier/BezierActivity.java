package com.lyl.viewpath.bazier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lyl.viewpath.R;

public class BezierActivity extends AppCompatActivity {

    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private Bezier2View mIdBezier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier);
        initView();
        setView();
    }

    private void setView() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRadioButton1.getId()) {
                    mIdBezier2.setTouchPoint(Bezier2View.POINT_ONE);
                } else if (checkedId == mRadioButton2.getId()) {
                    mIdBezier2.setTouchPoint(Bezier2View.POINT_TWO);
                }
            }
        });
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.id_btn1);
        mRadioButton2 = (RadioButton) findViewById(R.id.id_btn2);
        mIdBezier2 = (Bezier2View) findViewById(R.id.id_bezier2);
        mIdBezier2.setTouchPoint(Bezier2View.POINT_ONE);
    }

}
