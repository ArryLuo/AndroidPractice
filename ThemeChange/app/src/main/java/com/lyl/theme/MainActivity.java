package com.lyl.theme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemeUtile.changeTheme(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.email_sign_in_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTheme();
            }
        });

    }


    private void changeTheme() {
        ThemeUtile.night = !ThemeUtile.night;
        ThemeUtile.changeTheme(this);
        recreate();
    }

}

