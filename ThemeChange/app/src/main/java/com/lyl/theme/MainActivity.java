package com.lyl.theme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    /**
     * recreate() 会使 Activity 重新刷新，所以 这个标志必须是静态的。
     */
    private static boolean night = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeTheme();
        setContentView(R.layout.activity_main);

        findViewById(R.id.email_sign_in_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
    }

    private void change() {
        night = !night;
        changeTheme();
        recreate();
    }

    /**
     * 根据标志改变主题
     */
    private void changeTheme() {
        if (night) {
            setTheme(R.style.AppThemeNight);
        } else {
            setTheme(R.style.AppTheme);
        }
    }
}
