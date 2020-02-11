package cn.gdxhlm.mobilemedia.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CacheUtil {
    public static void save(Context context,String key, String data){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,data);
        editor.apply();
    }

    public static String get(Context context,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences("cache",Context.MODE_PRIVATE);
        String data=sharedPreferences.getString(key,"");
        return data;
    }
}
