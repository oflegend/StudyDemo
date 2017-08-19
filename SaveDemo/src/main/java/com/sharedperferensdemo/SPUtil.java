package com.sharedperferensdemo;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * SharedPreferences存储类用于存储简单的数据
 * <p>
 * Created by D&LL on 2017/3/24.
 */

public class SPUtil {
    private static String FILE_NAME = "sp_data";
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    /**
     * 保存数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void toWrite(Context context, String key, Object value) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, (value + "").toString());
        }
        editor.commit();
    }

    /**
     * 获取数据的方法
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static Object toRead(Context context, String key, Object defaultValue) {
        sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defaultValue instanceof String) {
            return sp.getString(key, (String) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return sp.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            return sp.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return sp.getLong(key, (Long) defaultValue);
        } else {
            return null;
        }
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void toRemove(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    public static void toClear(Context context) {
        sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean isContains(Context context, String key) {
        sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
