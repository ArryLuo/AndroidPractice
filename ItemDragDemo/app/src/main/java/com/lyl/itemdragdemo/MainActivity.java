package com.lyl.itemdragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.lyl.itemdragdemo.drag.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatat();
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mAdapter = new RecyclerAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //将拖动事件与RecyclerView联系起来
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mAdapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initDatat() {
        mData = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 1) {
                mData.add("瞅我干哈  " + i);
            } else {
                mData.add(" 瞅 你 咋 的   " + i);
            }
        }
    }
}
