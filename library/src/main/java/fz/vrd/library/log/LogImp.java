package fz.vrd.library.log;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import fz.vrd.library.utils.DateUtils;
import fz.vrd.library.utils.FileUtils;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/8 9:54 <br/>
 * <b>说明：{ } <br/>
 */
class LogImp implements Log {

    String errorFileDir = FileUtils.getDownloadCacheDirectory() + "/log/error/";


    public LogImp() {

        Logger.addLogAdapter(new AndroidLogAdapter() {

            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {

                return isDebugEnabled();
            }
        });

//        Logger.addLogAdapter(new DiskLogAdapter());
    }

    @Override
    public boolean isDebugEnabled() {
        return !BuildConfig.DEBUG;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public String getLocalErrorFileDir() {
        return null;
    }

    @Override
    public void error(String var1, Throwable var2) {
        if(isTraceEnabled()){
            saveErrorInfoFile(var1,var2);
        }
    }

    @Override
    public void e(String var1) {
        Logger.e(var1);
        if(isTraceEnabled()){
            saveErrorInfoFile(var1,null);
        }
    }

    @Override
    public void e(String tag, String var1) {
        Logger.e(tag,var1);
    }

    @Override
    public void d(String var1) {
        Logger.d(var1);
    }

    @Override
    public void d(String tag, String var1) {
        Logger.d(tag,var1);
    }

    @Override
    public void w(String var1) {
        Logger.w(var1);
    }

    @Override
    public void v(String var1) {
        Logger.v(var1);
    }

    @Override
    public void i(String var1) {
        Logger.i(var1);
    }

    @Override
    public void wtf(String var1) {
        Logger.wtf(var1);
    }

    @Override
    public void json(String var1) {
        Logger.json(var1);
    }

    @Override
    public void xml(String var1) {
        Logger.xml(var1);
    }

    void saveErrorInfoFile(String info,Throwable var2){
        String dir = errorFileDir + DateUtils.getCuurTime(DateUtils.FORMAT_YM) + "/";
        String fileName = DateUtils.getCurrDay() + "-error.text";
        File file =  FileUtils.createFile(dir,fileName);
        if(file != null){
            StringBuffer buffer = new StringBuffer();
            buffer.append("-----" + DateUtils.getCuurTime() + "-------------------------")
                    .append("信息:" + info)
                    .append(var2 == null ? "" : "错误信息:" + var2.toString())
                    .append("===================结束===============================");
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(buffer.toString().getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
