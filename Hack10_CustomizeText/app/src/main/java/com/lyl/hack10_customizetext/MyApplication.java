package com.lyl.hack10_customizetext;

import android.app.Application;
import android.content.Context;

/**
 * Wing_Li
 * 2016/5/17 0017.
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
