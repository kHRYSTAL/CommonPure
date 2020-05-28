package com.yimeiduo.business.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;

import com.yimeiduo.business.Constant;
import com.yimeiduo.business.MyApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static android.graphics.BitmapFactory.decodeByteArray;

/**
 * Created by panda825
 * email: dodan200@163.com
 * on 17/3/18.
 */

public class FileUtils {

    public static final String TAG=FileUtils.class.getSimpleName();

    private int PHOTO_SIZE = 800;

    private int mCurrentCameraId = 0;  //1是前置 0是后置

    public static FileUtils fileUtils;

    private FileUtils(){}

    public static FileUtils getInstance(){
        if(fileUtils == null){
            synchronized (FileUtils.class){
                if(fileUtils == null){
                    fileUtils = new FileUtils();
                }
            }
        }
        return fileUtils;
    }

    //删除文件夹和文件夹里面的文件
    public void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 计算图片的缩放值
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    //将byte转换成Bitmap
    public Bitmap byteChangeToBitmap(byte[] data)throws IOException {
        InputStream is = null;
        System.gc();
        Bitmap croppedImage = null;
        try {
            is = new ByteArrayInputStream(data);
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            decodeByteArray(data, 0, data.length, options);

            PHOTO_SIZE = options.outHeight > options.outWidth ? options.outWidth : options.outHeight;
            int height = options.outHeight > options.outWidth ? options.outHeight : options.outWidth;
            options.inJustDecodeBounds = false;

            Rect rect;
            if (mCurrentCameraId == 1) {
                rect = new Rect(height - PHOTO_SIZE, 0, height, PHOTO_SIZE);
            } else {
                rect = new Rect(0, 0, PHOTO_SIZE, PHOTO_SIZE);
            }

            try {
                croppedImage = decoder.decodeRegion(rect, new BitmapFactory.Options());
            } catch (IllegalArgumentException e) {
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {

            }
        }
        return croppedImage;
    }

    public static String getPath(){
        return Environment.getExternalStorageDirectory() + "/CompanySyswin/";
    }
    public static String getPhotoPath() {
        return Environment.getExternalStorageDirectory() + "/CompanySyswin/PhotoCache/";
    }

    private Bitmap decodeRegionCrop(byte[] data, Rect rect) {
        InputStream is = null;
        System.gc();
        Bitmap croppedImage = null;

        try {
            is = new ByteArrayInputStream(data);
            BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(is, false);
            try {
                croppedImage = decoder.decodeRegion(rect, new BitmapFactory.Options());
            } catch (IllegalArgumentException e) {
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {

            }
        }
        Matrix m = new Matrix();
        m.setRotate(90, PHOTO_SIZE / 2, PHOTO_SIZE / 2);
        if (mCurrentCameraId == 1) {
            m.postScale(1, -1);
        }
        Bitmap rotatedImage = Bitmap.createBitmap(croppedImage, 0, 0, PHOTO_SIZE, PHOTO_SIZE, m, true);
        if (rotatedImage != croppedImage)
            croppedImage.recycle();
        return rotatedImage;
    }

    //保存图片文件
    public static String saveToFile(String fileFolderStr, boolean isDir, Bitmap croppedImage) throws IOException {
        File jpgFile;
        if (isDir) {
            File fileFolder = new File(fileFolderStr);
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
//            String filename = format.format(date) + ".png";
            String filename = format.format(date)+".jpg";
            if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                getInstance().mkdir(fileFolder);
            }
            jpgFile = new File(fileFolder, filename);
        } else {
            jpgFile = new File(fileFolderStr);
            if (!jpgFile.getParentFile().exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                getInstance().mkdir(jpgFile.getParentFile());
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        croppedImage.compress(Bitmap.CompressFormat.JPEG, 70, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中

        int options = 70;//先压缩到60%
        while (baos.toByteArray().length / 1024 > 800) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
            if (options <= 0) {//有的图片过大，可能当options小于或者等于0时，它的大小还是大于目标大小，于是就会发生异常，异常的原因是options超过规定值。所以此处需要判断一下
                break;
            }
            baos.reset();// 重置baos即清空baos
            options -= 5;// 每次都减少10
            croppedImage.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }

        try {
            FileOutputStream fos = new FileOutputStream(jpgFile);
            fos.write(baos.toByteArray());
            fos.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jpgFile.getPath();
    }

    public boolean mkdir(File file) {
        while (!file.getParentFile().exists()) {
            mkdir(file.getParentFile());
        }
        return file.mkdir();
    }

    /* 保存bitmap到本地
    *
            * @param context
    * @param mBitmap
    * @return
            */
    public static String saveBitmap(Context context, Bitmap mBitmap) {
        String savePath =null;
        File filePic;
        if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) {
            savePath = FileUtils.getInstance().getPhotoPath();
        }
        try {
//            filePic = new File(savePath + generateFileName() + ".jpg");
            filePic = new File(savePath + generateFileName());
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            Bitmap bitmap = zoomImg(mBitmap,400,400);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 1, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return filePic.getAbsolutePath();
    }

    /**
     *  处理图片
     * @param bm 所要转换的bitmap
     * @return 指定宽高的bitmap
     */
    public static Bitmap zoomImg(Bitmap bm, int newWidth , int newHeight){
        // 获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }
    /**
     * 随机生产文件名
     *
     * @return
     */
    private static String generateFileName() {
        return UUID.randomUUID().toString();
    }


    public static String convertLength(long length) {
        if (length <= 0) {
            return "0kb";
        } else if (length < 1024 * 1024) {//1MB以下
            return length / 1024 + "KB";
        } else {
            return length / 1024 / 1024 + "MB";
        }
    }

    /**
     * 得到本地或者网络上的bitmap url - 网络或者本地图片的绝对路径,比如:
     *
     * A.网络路径: url=&quot;http://blog.foreverlove.us/girl2.png&quot; ;
     *
     * B.本地路径:url=&quot;file://mnt/sdcard/photo/image.png&quot;;
     *
     * C.支持的图片格式 ,png, jpg,bmp,gif等等
     *
     * @return
     */
    public static Bitmap pathToBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(MyApplication.getContext().getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }


}
