package fz.vrd.library.utils;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentActivity;

import java.io.File;
import java.util.List;

import fz.vrd.utils.BuildConfig;

/**
 * app常用和系统有关的工具类
 */
public class AppUtils {

   static String TAG = AppUtils.class.getName();

    public static int getPhoneW(Context context) {

        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getPhoneH(Context context) {

        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getPhoneBarHeight(Context context) {
        float statusBarHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = context.getResources().getDimension(resourceId);
        }
        return (int) statusBarHeight;
    }

    /**
     * 是否是平板
     */
    public static boolean isSlab(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationRunToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取本地软件版本号
     */
    public static int getAppVersionCode(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getAppVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static int dp2px(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value * scale);
    }

    public static int px2dp(Context context, float value) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }

    /**
     * 安装apk
     *
     * @param apkFile            : apk文件
     * @param appId_fileprovider : com.xx.xx.fileprovider
     */
    public static void installApk(Activity activity, File apkFile, String appId_fileprovider) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri contentUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //fileprovider  是在清单文件中provider的配置项
            contentUri = FileProvider.getUriForFile(
                    activity.getApplicationContext(), appId_fileprovider, apkFile);
        } else {
            contentUri = Uri.fromFile(apkFile);
        }
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    /**
     * 验证是是否有安装apk权限
     * @param activity
     * @return 如果没有权限
     */
    public static boolean isInstallApkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return activity.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /**
     * 拨打电话
     * @param activity
     * @param tel
     */
    public static void callPhone(Activity activity, String tel) {
        if (StringUtils.isNum(tel)) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" + tel);
            intent.setData(data);
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "电话号码错误", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 打开系统照相机的方法
     * @param outPath
     * @param appId_fileprovider : com.xx.xx.fileprovider
     */
    public static void openCamera(FragmentActivity activity, String outPath,String appId_fileprovider, int requestcode) {
        if (FileUtils.existSDCard()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            Uri imageUri = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imageUri = Uri.fromFile(new File(outPath));
            } else {
                 //fileprovider  是在清单文件中provider的配置项
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(activity, appId_fileprovider, new File(outPath));

            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            activity.startActivityForResult(intent, requestcode);
        } else {
            Toast.makeText(activity,"请确认已经插入SD卡",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 调用系统有的图库*
     */
    public static void openPhoto(FragmentActivity activity, int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 调用系统有的图库*
     */
    public void openPhoto1(FragmentActivity activity, int requestCode) {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, requestCode);
    }

    /**
     * 调用手机系统裁剪功能(部分手机有问题)
     * @param act
     * @param uri
     * @param outputUri
     * @param outputX
     * @param outputY
     */
    public static void cropImageUri(FragmentActivity act, Uri uri, Uri outputUri, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        act.startActivityForResult(intent, 0x156);
    }

    /*
     * 调用手机安装的App打开文件
     */
    public static void openFileToIntent(FragmentActivity activity, File file) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(url));
//        MimeTypeMap map = MimeTypeMap.getSingleton();
//        String type = map.getMimeTypeFromExtension(MimeTypeMap
//                .getFileExtensionFromUrl(url));
//        intent.setDataAndType(uri, type);
//        context.startActivity(intent);

        try {
            Uri uri = null;
            if (Build.VERSION.SDK_INT >= 24) {
                uri = FileProvider.getUriForFile(activity, "xx", file);
            } else {
                uri = Uri.fromFile(file);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri, "application/pdf");
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG,"==openFileToIntent===" + e.getMessage());
        }
    }

}
