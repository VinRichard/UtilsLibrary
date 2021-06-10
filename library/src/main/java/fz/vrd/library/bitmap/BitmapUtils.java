package fz.vrd.library.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <b>类名称或说明：图片管理  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/4/29 17:15<br/>
 * <b>修改备注：{ } <br/>
 */
public class BitmapUtils {


    /**
     * 保存原始图片至sd卡上
     *
     * @param bitmap
     * @param file
     * @param scale  : 百分比 :  0 - 100
     */
    public static void saveImage(Bitmap bitmap, File file, int scale) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, scale, os);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * bitmap转为base64 (大图片不建议这样做)
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (null == drawable) {
            return null;
        }
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = (drawable.getOpacity() != PixelFormat.OPAQUE) ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        if (null != bitmap) {
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            drawable.draw(canvas);
        }
        return bitmap;
    }


    /**
     * 判断照片角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();
            return degree;
        }

        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (orientation) {

            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;

            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;

            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
        }
        return degree;
    }

    /**
     * 旋转照片
     *
     * @param degress : 图片需要纠正角度
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 压缩图片
     */
    public static Bitmap compress(String path, int width_n, int height_n) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inSampleSize = calculateInSampleSize(options, width_n, height_n);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    static int calculateInSampleSize(BitmapFactory.Options options, int width_n, int height_n) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > height_n || width > width_n) {
            final int h = height / 2;
            final int w = width / 2;
            while ((h / inSampleSize) >= height_n && (w / inSampleSize) >= width_n) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

}
