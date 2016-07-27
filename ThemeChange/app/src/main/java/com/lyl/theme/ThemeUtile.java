package com.lyl.theme;

import android.app.Activity;

/**
 * Wing_Li
 * 2016/7/27.
 */
public class ThemeUtile {
    public static boolean night = false;
    public static void changeTheme(Activity activity){
        if (ThemeUtile.night){
            activity.setTheme(R.style.AppThemeNight);
        }else{
            activity.setTheme(R.style.AppTheme);
        }
    }
}