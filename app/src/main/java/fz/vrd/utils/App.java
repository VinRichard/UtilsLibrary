package fz.vrd.utils;

import android.app.Application;

import fz.vrd.library.bitmap.ImageLoaderFactory;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/2 11:43 <br/>
 * <b>说明：{ } <br/>
 */
public class App  extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderFactory.getInstance().build(new GlideImageLoad());

        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageLoaderFactory.getInstance().clearMemoryCache();
            }
        }).start();
    }


}
