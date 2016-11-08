package com.lyl.viewimagetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mCheck;
    private Button mUnCheck;
    private CheckView mCheckView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCheck = (Button) findViewById(R.id.check);
        mUnCheck = (Button) findViewById(R.id.uncheck);
        mCheckView = (CheckView) findViewById(R.id.id_check);


        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckView.check();
            }
        });

        mUnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckView.unCheck();
            }
        });

    }
}
