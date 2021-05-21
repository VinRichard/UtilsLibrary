package fz.vrd.library.bitmap;

import android.app.Activity;
import android.widget.ImageView;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/21 16:27 <br/>
 * <b>说明：{ } <br/>
 */
interface Factory  {

    /**
     *  下载图片,图片大小根据框架默认配置适配
     */
    void displayImage(Activity activity, String path, ImageView imageView);

    /**
     *  下载图片,图片大小手动设置
     */
    void displayImage(Activity activity, String path, ImageView imageView, int width, int height);

    /**
     * 清空下载图片的缓存
     */
    void clearMemoryCache();

}
