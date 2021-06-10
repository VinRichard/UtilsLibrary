package fz.vrd.library.cache;

import android.content.Context;
import android.content.SharedPreferences;

import fz.vrd.library.log.LogFactory;
import fz.vrd.library.utils.StringUtils;

/**
 * <b>名称：物理缓存,只做字符串数据的缓存  <br/>
 * <b>创建人： VinRichard <br/>
 * <b>时间： 2021/6/10 17:11 <br/>
 * <b>备注：{ } <br/>
 */
public class SharedPreUtils {

    static String name = "";


    public static void putValue(Context context, String key, String value) {
        if (context != null) {
            getEditor(context).putString(key, value).commit();
            if(AppCache.isHasKey(key)){
                AppCache.put(key,value);
            }
        } else {
            throw new NullPointerException("SharedPreferencesUtils的putValue方法参数context是null!!! ");
        }
    }

    public static String getValue(Context context, String key) {

        return getValue(context,key,"");
    }

    public static String getValue(Context context, String key, String defaultStr) {
        if(context == null){
            throw new NullPointerException("SharedPreferencesUtils的getValue方法参数context是null!!! ");
        }

        if(AppCache.isHasKey(key)){
            return AppCache.get(key);
        }

        String value = getSharedPreferences(context).getString(key, "");

        if(!AppCache.isHasKey(key) && !StringUtils.isEmpty(value)){
              AppCache.put(key,value);
        }

        return StringUtils.getDefaultMsg(value,defaultStr);
    }


    static SharedPreferences getSharedPreferences(Context context){

        return context.getSharedPreferences(getName(context),Context.MODE_PRIVATE);
    }

    static SharedPreferences.Editor getEditor(Context context){

        return getSharedPreferences(context).edit();
    }

    static String getName(Context context){
        if(StringUtils.isEmpty(name)){
            name = context.getPackageName();
        }
        return name;
    }
}
