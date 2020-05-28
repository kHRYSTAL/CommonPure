package com.yimeiduo.business.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import com.yimeiduo.business.MyApplication;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SpUtil {
    public static final String TAG = SpUtil.class.getSimpleName();
    private static SharedPreferences spUtil;
    private static final String PREF_NAME_DEFAULT = "SharedPreferencesData";

    private static SharedPreferences getSp() {
        if (spUtil == null)
            spUtil = MyApplication.getContext().getSharedPreferences(PREF_NAME_DEFAULT, Context.MODE_PRIVATE);
        return spUtil;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put( String key, Object object)
    {
        SharedPreferences.Editor editor = getSp().edit();

        if (object instanceof String)
        {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer)
        {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float)
        {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long)
        {
            editor.putLong(key, (Long) object);
        } else
        {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key, Object defaultObject)
    {
        if (defaultObject instanceof String)
        {
            return getSp().getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return getSp().getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return getSp().getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return getSp().getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return getSp().getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     *
     */
    private static class SharedPreferencesCompat
    {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod()
        {
            try
            {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e)
            {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try
            {
                if (sApplyMethod != null)
                {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e)
            {
            } catch (IllegalAccessException e)
            {
            } catch (InvocationTargetException e)
            {
            }
            editor.commit();
        }
    }

    /**
     * 清楚暂存数据
     *
     * @param
     */
    public static void clean() {
       /* SharedPreferences preferences = context.getSharedPreferences(PREF_NAME_DEFAULT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();*/

        SharedPreferences.Editor editor = getSp().edit();
        editor.clear().commit();
    }

    /**
     * 删除指定数据
     *
     * @param
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences.Editor editor = getSp().edit();
        editor.remove(key).commit();
    }


    public static <T> void setDataList(String key, List<T> dataList) {
        if (null == dataList || dataList.size() < 0) {
            return;
        }

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(dataList);
        SharedPreferences.Editor editor = getSp().edit();
        editor.putString(key, strJson);
        editor.commit();
    }

    public static <T> List<T> getDataList(String key, Class<T> cls) {
        List<T> dataList = new ArrayList<T>();
        String strJson =  getSp().getString(key, null);
        if (null == strJson) {
            return dataList;
        }

        Gson gson = new Gson();

        //使用泛型解析数据会出错，返回的数据类型是LinkedTreeMap
//        dataList = gson.fromJson(strJson, new TypeToken<List<T>>() {
//        }.getType());

        //这样写，太死
//        dataList = gson.fromJson(strJson, new TypeToken<List<UserModel>>() {
//        }.getType());

        JsonArray arry = new JsonParser().parse(strJson).getAsJsonArray();
        for (JsonElement jsonElement : arry) {
            dataList.add(gson.fromJson(jsonElement, cls));
        }

        return dataList;
    }

}
