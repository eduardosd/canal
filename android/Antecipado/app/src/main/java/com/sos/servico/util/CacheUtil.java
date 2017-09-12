package com.sos.servico.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by deivison on 5/2/15.
 */
public class CacheUtil {

    public static void store(Activity context,String key,String value){
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String read(Activity context,String key,String defaultValue){
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(key,defaultValue);
    }

    public static void drop(Activity context,String key){
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().remove(key).commit();
    }

    public static boolean isLogado(Activity context){
        return !CacheUtil.read(context, "name", "-").equals("-");
    }

}
