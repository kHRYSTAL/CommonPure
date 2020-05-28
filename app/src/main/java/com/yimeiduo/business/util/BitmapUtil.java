package com.yimeiduo.business.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URI;

/**
 * author huanggang on 2016/3/15 15:50
 * email 553770617@qq.com
 * desc
 */
@SuppressWarnings({"ResultOfMethodCallIgnored", "unused"})
public class BitmapUtil {
    /**
     * 把bitmap转换成String
     */
    public static String bitmapToString(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return "";
        try {
            Bitmap bm = getSmallBitmap(filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } catch (Exception e) {
            return "";
        }
    }

    public static Bitmap getBitmap(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return null;
        try {
            return getSmallBitmap(filePath);
        } catch (Exception e) {
            return null;
        }
    }

    public static String bitmapToString(Bitmap bm) {
        if (bm == null)
            return "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.NO_WRAP);
        } catch (Exception e) {
            return "";
        }
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


    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     */
    public static Bitmap getSmallBitmap(String filePath) {
        filePath = url2path(filePath);
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(filePath, options));
        Bitmap bitmap = Bitmap.createScaledBitmap(weak.get(), 480, 800,true);
        return bitmap;

//        return BitmapFactory.decodeFile(filePath, options);
    }


    /**
     * 根据路径删除图片
     */
    public static void deleteTempFile(String filePath) {
        filePath = url2path(filePath);
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 添加到图库
     */
    public static void galleryAddPic(Context context, String filePath) {
        filePath = url2path(filePath);
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(filePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * 获取保存图片的目录
     */
    public static File getAlbumDir() {
        File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), getAlbumName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * 获取保存 隐患检查的图片文件夹名称
     */
    public static String getAlbumName() {
        return "sheguantong";
    }

    public static String url2path(String url) {
        if (url.startsWith("file:///")) {
            return new File(URI.create(url)).getAbsolutePath();
        }
        return url;
    }


}
