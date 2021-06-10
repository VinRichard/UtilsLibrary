package fz.vrd.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import fz.vrd.library.bitmap.ImageLoader;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/2 11:42 <br/>
 * <b>说明：{ } <br/>
 */
public class GlideImageLoad implements ImageLoader {



    @Override
    public void displayImage(Activity activity, String path, ImageView imageView) {

    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

    }

    @Override
    public void clearMemoryCache() {
        Log.e("GlideImageLoad","==============clearMemoryCache================");
    }
}
