package fz.vrd.library.bitmap;

import android.app.Activity;
import android.widget.ImageView;

import fz.vrd.library.utils.StringUtils;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/21 9:29 <br/>
 * <b>说明：{ } <br/>
 */
public final class ImageLoaderFactory implements Factory {

    static ImageLoaderFactory factory;

    Factory imageLoaderImp;

    public static ImageLoaderFactory getInstance() {
        if (factory == null) {
            factory = new ImageLoaderFactory();
        }
        return factory;
    }

    /**
     * @param imageLoaderImp : 实现接口的自定义类
     */
    public ImageLoaderFactory build(Factory imageLoaderImp) {
        if (imageLoaderImp == null) {
            this.imageLoaderImp = null;
        } else if (!StringUtils.isEmpty(imageLoaderImp.getClass().getSimpleName())) {
            this.imageLoaderImp = imageLoaderImp;
        }
        return this;
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView) {
        if (imageLoaderImp != null)
            imageLoaderImp.displayImage(activity, path, imageView);
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        if (imageLoaderImp != null)
            imageLoaderImp.displayImage(activity, path, imageView, width, height);
    }

    @Override
    public void clearMemoryCache() {
        if (imageLoaderImp != null)
            imageLoaderImp.clearMemoryCache();
    }
}
