/*******************************************************************************
 * Copyright (c) 2012 Manning
 * See the file license.txt for copying permission.
 ******************************************************************************/
package com.lyl.sectionlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private TextView mTopHeader;

    private int topVisiblePosition = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTopHeader = (TextView) findViewById(R.id.header);
        mListView = (ListView) findViewById(R.id.list);

        if (Countries.COUNTRIES == null || Countries.COUNTRIES.length == 0) {
            //数据为空时显示提示
            mTopHeader.setVisibility(View.GONE);
            ((ViewStub) findViewById(R.id.empty)).inflate();
            return;
        }

        mListView.setAdapter(new SectionAdapter(this, Countries.COUNTRIES));
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //页面顶部显示的 那一条 永远是第一个item的首字母
                if (firstVisibleItem != topVisiblePosition) {
                    topVisiblePosition = firstVisibleItem;
                    setmTopHeader(firstVisibleItem);
                }
            }
        });
        setmTopHeader(0);
    }

    private void setmTopHeader(int pos) {
        final String text = Countries.COUNTRIES[pos].substring(0, 1);
        mTopHeader.setText(text);
    }
}
