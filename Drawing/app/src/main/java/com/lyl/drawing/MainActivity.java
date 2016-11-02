package com.lyl.drawing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PieView mPieView;
    private LearnView mLearnView;
    private ArrayList<PieData> mData;

    private boolean isLearn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mLearnView = (LearnView) findViewById(R.id.id_learn);
        findViewById(R.id.id_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (isLearn) {
                    mLearnView.setVisibility(View.GONE);
                    mPieView.setVisibility(View.VISIBLE);
                    btn.setText(getString(R.string.main_change_pie));
                } else {
                    mPieView.setVisibility(View.GONE);
                    mLearnView.setVisibility(View.VISIBLE);
                    btn.setText(getString(R.string.main_change_learn));
                }
                isLearn = !isLearn;
            }
        });
    }

    private void initView() {
        mPieView = (PieView) findViewById(R.id.id_pieView);

        mData = new ArrayList<PieData>();
        mData.add(new PieData("微风1", Color.RED, 50));
        mData.add(new PieData("微风2", Color.BLACK, 20));
        mData.add(new PieData("微风3", Color.YELLOW, 40));
        mData.add(new PieData("微风4", Color.BLUE, 60));

        mPieView.setmData(mData);
    }
}
