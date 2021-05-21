package fz.vrd.library.utils;


import android.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
  * <b>类名称或说明：数据解析  <br/>
  * <b>创建人： Administrator <br/>
  * <b>时间： 2021/4/29 18:27<br/>
  * <b>修改备注：{ } <br/>
  */
public class GsonUtils {



    /**
     * json解析成hashMap
     */
    public static HashMap<String, Object> jsonToMap(String jsonString) {
        HashMap<String, Object> hashmap = new HashMap<String, Object>();
        Gson gson = new Gson();
        try {
            hashmap = gson.fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {
            }.getType());
        } catch (Exception e) {

        }
        return hashmap;
    }

    /**
     * json解析成ArrayList
     */
    public static ArrayList<HashMap<String, Object>> listKeyMaps(
            String jsonString) {
        ArrayList<HashMap<String, Object>> ArrayList = new ArrayList<HashMap<String, Object>>();
        Gson gson = new Gson();
        try {
            ArrayList = gson.fromJson(jsonString,
                    new TypeToken<ArrayList<HashMap<String, Object>>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ArrayList;
    }

    /**
     * 把一个json字符串解析到一个对象中
     */
    public static <T> T jsonToClas(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            return null;
        }
        return t;
    }

    public static String objToJson(Object e) {
        Gson gson = new Gson();
        return gson.toJson(e);
    }

    /**
     * 把一个json解析到一个list中
     */
    public static <T> List<T> getObjectsToList1(String jsonsString, Class<T[]> cls) {
        List<T> list = new ArrayList<T>();
        T[] arr = new Gson().fromJson(jsonsString, cls);
        return arr == null ? list : Arrays.asList(arr);

    }


}
