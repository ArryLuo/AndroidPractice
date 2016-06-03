package com.lyl.diyviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int[] imgRes = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j};

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private int cunPos;

    private RotateUpPageTransformer rotateUpPageTransformer;
    private DepthPageTransformer depthPageTransformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mViewPager = (ViewPager) findViewById( R.id.id_viewpager );

        mViewPager.setPageMargin( 40 );//设置Page间间距
        mViewPager.setOffscreenPageLimit( 3 ); //设置缓存的页面数量
        mViewPager.setAdapter( mAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView view = new ImageView( MainActivity.this );
                view.setScaleType( ImageView.ScaleType.FIT_XY );
                view.setImageResource( imgRes[position] );
                container.addView( view );
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView( (View) object );
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }
        } );

        rotateUpPageTransformer = new RotateUpPageTransformer();
        depthPageTransformer = new DepthPageTransformer();
        mViewPager.setPageTransformer( true, rotateUpPageTransformer );
    }

    public void change(View view) {
        Button btn = (Button) view;
        cunPos++;

        mViewPager.setAdapter( mAdapter );
        switch (cunPos) {
            case 0:
                mViewPager.setPageTransformer( true, rotateUpPageTransformer );
                btn.setText( "RotateUp" );
                break;
            case 1:
                mViewPager.setPageTransformer( true, depthPageTransformer );
                btn.setText( "Depth" );
                cunPos = -1;
                break;
        }
    }
}
