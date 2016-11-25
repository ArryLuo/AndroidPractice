package com.lyl.viewpath;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lyl.viewpath.bazier.BezierActivity;
import com.lyl.viewpath.figure.FigureActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toBezier(View view){
        Intent intent = new Intent(MainActivity.this,BezierActivity.class);
        startActivity(intent);
    }

    public void toFigure(View view){
        Intent intent = new Intent(MainActivity.this,FigureActivity.class);
        startActivity(intent);
    }

}
