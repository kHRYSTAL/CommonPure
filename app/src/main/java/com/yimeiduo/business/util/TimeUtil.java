package com.yimeiduo.business.util;

import android.text.TextUtils;

import com.yimeiduo.business.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * author：cq on 2015/5/12 14:21
 * email：2511525208@qq.com
 */

public class TimeUtil {

    public static final String TAG = TimeUtil.class.getSimpleName();

    public static String getTimeFormat(String time,String type) {//可根据需要自行截取数据显示

        if (TextUtils.isEmpty(time)){
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(Constant.YYYYMMDDHHMMSS);
        Date date;
        try {
            date = format.parse(time);
            SimpleDateFormat format1 = new SimpleDateFormat(type);
            String s = format1.format(date);
            return s;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTimeFormat(Date date,String type) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(type);
        return format.format(date);
    }

    // 获取系统时间戳
    public static long getCurTimeLong(){
        long time= System.currentTimeMillis();
        MyLog.i(TAG,"获取系统时间戳  time-->" + time);   //   1540609388326
        return time;
    }

    //  获取当前时间
    public static String getCurDate(String pattern){
//        String pattern = "2017-06-20" ;//对应的格式：String pattern = "yyyy-MM-dd";
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        String time = sDateFormat.format(new Date());
        MyLog.i(TAG,"获取当前时间  time-->" + time);
        return time;
    }

    //时间戳转换成字符窜
    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String time = format.format(date);
//        MyLog.i(TAG,"时间戳转换成字符窜  time-->" + time); // 2018-10-27
        return time;
    }


    //将字符串转为时间戳
    public static long getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try{
            date = dateFormat.parse(dateString);
        } catch(Exception e) {
            e.printStackTrace();
        }
        long time = date.getTime();
        MyLog.i(TAG,"将字符串转为时间戳  time-->" + time);// 1540569600000  1540655999000
        return time;
    }

    public static int getTimeCompareSize(String startTime, String endTime){
        int i=0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//年-月-日 时-分
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<date1.getTime()){
                i= 1;
            }else if (date2.getTime()==date1.getTime()){
                i= 2;
            }else if (date2.getTime()>date1.getTime()){
                //正常情况下的逻辑操作.
                i= 3;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  i;
    }

    /**
     * 当天的开始时间
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date=calendar.getTime();
        long time = date.getTime();
        MyLog.i(TAG,"当天的开始时间  time-->" + time+"--date--"+date);// 2018-10-27 00:00:00 1540569600000  Sat Oct 27 00:00:00 GMT+08:00 2018
        return time;
    }
    /**
     * 当天的结束时间
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);  // 1540655999999  加上它后面多 999
//        calendar.set(Calendar.MILLISECOND, 999);  // 1540655999999  加上它后面多 999
        Date date=calendar.getTime();
        long time = date.getTime();
        MyLog.i(TAG,"当天的结束时间  time-->" + time+"----date-----"+date);// 2018-10-27 00:00:00 1540655999000 1540655999999
        return time;
    }

    public static String getTime(int timeMillis) {
        String result = "";
        timeMillis = timeMillis / 1000;
        int minutes = timeMillis / 60;
        int seconds = timeMillis % 60;
        result = result + minutes + ":";
        if (seconds < 10)
            result = result + "0";
        result = result + seconds;
        return result;
    }


}
