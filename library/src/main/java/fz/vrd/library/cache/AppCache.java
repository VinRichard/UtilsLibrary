package fz.vrd.library.cache;

import android.text.TextUtils;

import java.util.HashMap;

/**
 * <b>名称：内存缓存数据 <br/>
 * <b>创建人： VinRichard <br/>
 * <b>时间： 2021/6/10 16:00 <br/>
 * <b>备注：{ } <br/>
 */
public class AppCache {

    private final static HashMap<String, String> cacheMap = new HashMap<>();

    public final static HashMap<String, Object> cacheObj = new HashMap<>();


    public static void putObject(String key, Object o) {
        cacheObj.put(key, o);
    }

    public static Object getObject(String key) {
        return cacheObj.get(key);
    }

    public static void put(String key, String value) {
        cacheMap.put(key, value);
    }

    public static String get(String key) {

        return get(key, "");
    }

    private static String get(String key, String defaultValue) {

        return TextUtils.isEmpty(cacheMap.get(key)) ? defaultValue : cacheMap.get(key);
    }

    public static boolean isHasKey(String key) {
        return cacheMap.containsKey(key);
    }

    public static void remove(String key) {
        cacheMap.remove(key);
    }

    public static void clearAll() {
        cacheMap.clear();
    }

    public static void removerObject(String key) {
        cacheObj.remove(key);
    }

    public static void clearObject() {
        cacheObj.clear();
    }


}
