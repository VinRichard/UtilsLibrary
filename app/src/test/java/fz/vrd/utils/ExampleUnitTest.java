package fz.vrd.utils;

import android.app.Activity;
import android.widget.ImageView;

import org.junit.Test;

import fz.vrd.library.aes.E_DncryptionFactory;
import fz.vrd.library.aes.Factory;
import fz.vrd.library.bitmap.ImageLoader;
import fz.vrd.library.bitmap.ImageLoaderFactory;
import fz.vrd.library.log.LogFactory;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
//        Factory factory = E_DncryptionFactory.createSha();

//        String d = factory.encryption("12qaZX@!沙发");
//        System.out.println("=======encryption:" + d);
//        String dd = factory.decryption(d);
//        System.out.println("=======decryption:" + dd);
        Factory ff = new Abc();
        Factory factory = E_DncryptionFactory.create(ff);
        factory.decryption("11111");
        factory.encryption("222222");

    }

    @Test
    public void imageLoad() {

        ImageLoaderFactory.getInstance().clearMemoryCache();
        out("=====未初始化下载=======");
        ImageLoaderFactory.getInstance().build(new GildeImageLoad());
        out("=====初始化完成了开始下载=======");
        ImageLoaderFactory.getInstance().clearMemoryCache();
        out("=====初始化设置null======");
        ImageLoaderFactory.getInstance().build(null);


    }


    void out(String msg) {
        System.out.println(msg);
    }


    @Test
    public void log(){
        LogFactory.getInstance().d("===========LogFactory============");
    }
}