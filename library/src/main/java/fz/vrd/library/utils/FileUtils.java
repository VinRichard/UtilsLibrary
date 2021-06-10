package fz.vrd.library.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 文件操作工具类
 */
public class FileUtils {

    static String TAG = FileUtils.class.getName();

    /**
     * 判断是否有sd卡
     */
    public static boolean existSDCard() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 外置存储卡的路径
     *
     * @return
     */
    public static String getExternalStorePath() {
        if (existSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }

    public static String getDownloadCacheDirectory() {

        return Environment.getDownloadCacheDirectory().getPath();
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String pathName) {
        if (StringUtils.isEmpty(pathName)) {
            return null;
        }
        int start = pathName.lastIndexOf("/");
        if (start != -1) {
            return pathName.substring(start + 1, pathName.length());
        }
        return pathName;
    }

    /**
     * 在SD卡上创建文件
     *
     * @param dir      文件夹
     * @param fileName 文件名
     */
    public static File createFile(String dir, String fileName) {
        try {
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdir();
            }
            if (!file.exists()) {
                file.mkdirs();
            }
            File file1 = new File(dir, fileName);
            if (!file1.exists()) {
                file1.createNewFile();
            }
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isFileExist(String fileName) {

        return StringUtils.isEmpty(fileName) ? false : new File(fileName).exists();
    }

    /**
     * 删除单个文件
     *
     * @param filePath 文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.exists() && file.isFile()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param dirName 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dirName) {
        if (StringUtils.isEmpty(dirName)) {
            return false;
        }
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }
        File dirFile = new File(dirName);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取asseat 目录下的json文件的数据
     *
     * @param mContext
     * @param fileName json文件名
     * @return
     */
    public static String getAssetsFile(Context mContext, String fileName) {

        StringBuilder builder = new StringBuilder();
        try {
            InputStreamReader isr = new InputStreamReader(mContext.getAssets().open(fileName), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
            br.close();
            isr.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 读取一个文本文件的内容
     */
    public static String readFile(File file) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"));
        StringBuffer sb = new StringBuffer();
        String line;
        if (bufferedReader != null) {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
        }
        return sb.toString();
    }

    /**
     * 把Assets的下面的文件写入到其他文件
     *
     * @param context
     * @param assetsFileName Assets下面的文件名称
     * @param filePath       需要写入的文件路径
     * @return
     */
    public static boolean writeAssetsFileToFile(Context context, String assetsFileName, String filePath) {
        if (context == null || TextUtils.isEmpty(assetsFileName) || TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath.substring(0, filePath.lastIndexOf("/") + 1));
        if (!file.exists()) {
            file.mkdir();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            InputStream open = context.getResources().getAssets().open(assetsFileName);
            FileOutputStream fos = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = open.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            open.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "=writeAssetsFileToFile====" + e.getMessage());
            return false;
        }
    }


}
