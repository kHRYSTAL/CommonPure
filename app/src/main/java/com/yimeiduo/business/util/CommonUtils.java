package com.yimeiduo.business.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.yimeiduo.business.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by cq on 2018/12/04.
 */

public class CommonUtils {

    public static final String TAG = CommonUtils.class.getSimpleName();

    public static Context getContext() {
        return MyApplication.getContext();
    }
    // /////////////////加载布局文件//////////////////////////
    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    // 获取手机android系统版本
    public static String getAndroidVersion() {
        String phoneversion = null;
        try {
            phoneversion = Build.VERSION.RELEASE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyLog.i(TAG,"获取手机系统版本-->"+phoneversion);
        return phoneversion + "";
    }

    // 获取手机型号
    public static String getModel() {
        String model = null;
        try {
            model = Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyLog.i(TAG,"获取手机型号-->"+model);
        return model;
    }

    /**
     * 返回版本号
     * @return
     * 			非0 则代表获取成功
     */
    public static int getVersionCode() {
        //1,包管理者对象packageManager
        PackageManager pm = getContext().getPackageManager();
        //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getContext().getPackageName(), 0);
            //3,获取版本名称
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取版本名称:清单文件中
     * @return	应用版本名称	返回null代表异常
     */
    public static String getVersionName() {
        //1,包管理者对象packageManager
        PackageManager pm = getContext().getPackageManager();
        //2,从包的管理者对象中,获取指定包名的基本信息(版本名称,版本号),传0代表获取基本信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getContext().getPackageName(), 0);
            //3,获取版本名称
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽，有多少列
        int col = 3;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
            // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
//        ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }


    /**
     * 获取状态栏高度
     * @return
     */
    public static int getStatusBarHeight( ) {
        Resources resources = getContext().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight(){
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp( float pxValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static boolean isNumberLegal(String str)throws PatternSyntaxException {
        return checkCellphone(str) || checkTelephone(str)||isHKPhoneLegal(str);
    }

    /**
     * 验证手机号码
     * @param num
     * @return
     */
    public static boolean checkCellphone(String num){
//        String numStr = String.valueOf(num);

        String pattern = "^1[\\d]{10}$";
//        String pattern = "^(1[3,4,5,7,8][0-9])\\d{8}$";
        boolean isMatch = Pattern.matches(pattern, num);
        return isMatch;
    }

    /**
     *  验证固话号码
     *  @param num
     *  @return
     */

//    匹配国内电话号码：/d{3}-/d{8}|/d{4}-/d{7}
    //      ^(0\d{2}-\d{8}(-\d{1,4})?)|(0\d{3}-\d{7,8}(-\d{1,4})?)$
    //      ^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$
//    匹配形式如 0511-4405222 或 021-87888822或0511-44052222 或 021-8788882

    public static boolean checkTelephone(String num) {
        String pattern = "^((0\\d{2,3}))(\\d{7,8})?$";
        boolean isMatch = Pattern.matches(pattern, num);
        return isMatch;
//        return check(telephone, regex);
    }


    /**
     * @param num
     * @return
     */
//    public static boolean isMobileNum(String num) {
////        Pattern.compile("^((147)|(17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
//         String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
//        Pattern p = Pattern.compile("^(1[3,4,5,7,8][0-9])\\d{8}$");
//        Matcher m = p.matcher(num);
//        // return m.matches();
//        return true;
//    }



    //---------------------------------
    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void keep2Decimal (Editable s){
        String temp = s.toString();

        int posDot = temp.indexOf(".");//返回指定字符在此字符串中第一次出现处的索引
        if (posDot <= 0) {  //不包含小数点
            if (temp.length() <= 9) {

                return;//小于五位数直接返回
            } else {
                s.delete(9, 10);//大于五位数就删掉第六位（只会保留五位）
                return;
            }
        }
        if (temp.length() - posDot - 1 > 2)//如果包含小数点
        {
            s.delete(posDot + 3, posDot + 4);//删除小数点后的第三位
        }
    }

    // 退出界面隐藏键盘

    public static void hideKeyBoard (){
//        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow( getWindow().getDecorView().getWindowToken(), 0);

//        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        mInputMethodManager.hideSoftInputFromWindow(BaseActivity.this.getCurrentFocus().getWindowToken(), 0);

    }

    //  Java float保留两位小数或多位小数
    public static String keep2(String s){

//        float price=1.2f;
        float price=Float.valueOf(s);
//        MyLog.i(TAG,"CommonUtil-->"+price);
        DecimalFormat decimalFormat=new DecimalFormat("##0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.  ".00"  "##.00"
        String p=decimalFormat.format(price);//format 返回的是字符串
        return p;
    }

    //把String转化为float
    public static float convertToFloat(String number) {
        if (TextUtils.isEmpty(number)) {
            return 0.00f;
        }
        try {
            return Float.parseFloat(number);
        } catch (Exception e) {
            return 0.00f;
        }

    }


    /**
     * 获取手机的宽高
     * 0  宽
     * 1 高
     * */
    public static int [] ScreenWh(Context ct){
        int screen [] = new int [2];
        WindowManager wm = (WindowManager) ct
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        screen[0] = width;
        screen[1] = height;
        return screen;
    }

    //解析本地json
    public static String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static boolean isEmpty(List list){
        if (list == null){
            return true;
        }
        return list.size() == 0;
    }


}
