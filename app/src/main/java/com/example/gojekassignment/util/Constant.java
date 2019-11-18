package com.example.gojekassignment.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.gojekassignment.R;

public class Constant {

    public static String BASE_URL = "https://github-trending-api.now.sh/";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static void savePositionClicked(Activity context, int position) {


        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(
                context.getString(R.string.position), position);
        editor.commit();
    }

    public static int getPositionClicked(Activity activity){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        int pos = sharedPref.getInt(activity.getString(R.string.position), 0);
        return pos;
    }
}
