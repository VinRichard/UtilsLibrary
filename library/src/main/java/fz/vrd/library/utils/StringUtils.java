package fz.vrd.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * <b>类名称或说明：字符串工具栏  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/4/12 10:20<br/>
 * <b>修改备注：{ } <br/>
 */

public class StringUtils {

    /**
     * sdfsdfs
     * @param num
     * @return
     */
   public static double parseDouble(String num) {
       double d;
       try {
           d = Double.parseDouble(num);
       } catch (Exception e) {
           d = 0;
       }
       return d;
   }

   public static float parseFloat(String num) {
       float d;
       try {
           d = Float.parseFloat(num);
       } catch (Exception e) {
           d = 0;
       }
       return d;
   }

   public static long parseLong(String num) {
       long d;
       try {
           d = Long.parseLong(num);
       } catch (Exception e) {
           d = 0;
       }
       return d;
   }


   public static int parseInt(String num) {
       int d = 0;
       try {
           if (!TextUtils.isEmpty(num)) {
               d = Integer.parseInt(num);
           }
       } catch (Exception e) {
           d = 0;
       }
       return d;
   }

   public static boolean isEmpty(CharSequence... arr) {

       for (CharSequence cs : arr) {
           if (cs == null || cs.length() == 0 || "null".equals(cs.toString().toLowerCase())) {
               return true;
           }
       }
       return false;
   }


   public static String trim(String str) {
       return str == null ? "" : str.trim();
   }


   /**
    * 验证邮箱地址是否正确
    */
   public static boolean checkEmail(String email) {
       try {
           String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
           Pattern regex = Pattern.compile(check);
           Matcher matcher = regex.matcher(email);
           return matcher.matches();
       } catch (Exception e) {
       }
       return false;
   }


   /**
    * 验证手机号码
    * 13,14,15,18 开头
    */
   public static boolean isMobile(String phone) {
     return isMobile(phone,"^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
   }

    public static boolean isMobile(String phone,String regex) {
        try {
            Pattern p = Pattern
                    .compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        } catch (Exception e) {
        }
        return false;
    }

   /**
    * 判断是否是纯数字
    */
   public static boolean isNum(String num) {
       Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
       return TextUtils.isEmpty(num) ? false : pattern.matcher(num).matches();
   }

   /**
    * 取得arrays.xml中的数据以数组的形式返回
    *
    * @param context  上下文
    * @param arrayres arrays.xml文件中的 string-name的名字
    * @return
    */
   public static String[] getStringFormArrays(Context context, int arrayres) {
       Resources res = context.getResources();
       return res.getStringArray(arrayres);
   }

   public static String getDefaultMsg(String msg){
       return  getDefaultMsg(msg);
   }

   public static String getDefaultMsg(String msg,String defaultMsg){
       return isEmpty(msg) ? defaultMsg : msg;
   }

   /**
    * 数字转化:如  1.0转为1   1.5转为1.5
    */
   public static String float2num(String msg) {

       if (!TextUtils.isEmpty(msg)) {
           if (msg.contains(".")) {
               String num[] = msg.replace(".", "@").split("@");
               if (num != null && num.length == 2) {
                   if (isNum(num[0]) && isNum(num[1])) {
                       msg = num[0] + (Integer.parseInt(num[1]) > 0 ? "." + num[1] : "");
                   }
               }
           }
       }
       return msg;
   }

   public static Class stringToClass(String cls) {
       try {
           return Class.forName(cls);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       return null;
   }

   public static String countPercent(int num, int accout) {
       return countPercent(num, accout, 2);
   }

   /**
    * 计算百分比
    * @param num
    * @param accout
    * @param decimal 小数个数
    * @return
    */
   public static String countPercent(int num, int accout, int decimal) {
       NumberFormat numberFormat = NumberFormat.getInstance();
       numberFormat.setMaximumFractionDigits(decimal);
       String result = numberFormat.format((float) num / (float) accout * 100);
       return result + "%";
   }

   //根据年月日 返回时间:2018-01-05
   public static String getTimes(int year, int month, int dayOfMonth) {

       return year + "-" + (month > 8 ? month + 1 : "0" + (month + 1)) + "-" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth);
   }

   public static String getTimeHM(int h, int m) {

       return (h > 9 ? h : "0" + h) + ":" + (m > 9 ? m : "0" + m);
   }

   // 过滤特殊字符
   public static String StringFilter(String str) throws PatternSyntaxException {
       // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
       // 清除掉所有特殊字符
       String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
       Pattern p = Pattern.compile(regEx);
       Matcher m = p.matcher(str);
       return m.replaceAll("").trim();
   }

   /**
    * 改变部分字体颜色
    * @param msg      原始字
    * @param colorMsg 需要改变的字的颜色
    * @param colorValue 颜色值: ff0000
    * @return
    */
   public static Spanned changMsgColor(String msg, String colorMsg,String colorValue) {
       if (isEmpty(msg) || isEmpty(colorMsg)) {
           return Html.fromHtml(msg);
       }
       int index = msg.indexOf(colorMsg);
       if (index == -1) {
           return Html.fromHtml(msg);
       }
       String beforeInfo = msg.substring(0, index);
       String endInfo = msg.substring(index + colorMsg.length());
       return Html.fromHtml(beforeInfo + "<font color= '#" + colorValue + "'>" + colorMsg + "</font>" + endInfo);
   }

   /**
    * 首个数据是否是汉字
    */
   public static boolean msgFirstIsChinese(String string) {
       if (isEmpty(string)) {
           return false;
       }
       for (char c : string.toCharArray()) {
           return c >= 0x4E00 && c <= 0x9FA5;
       }
       return true;
   }

    /**
     * list的自字符集合转换为字符串直接用逗号隔开(a,2,3,4,5,r,ty,sdf)
     */
   public static String getListInfos(List<String> list) {
       String info = "";
       if (list != null) {
           for (String s : list) {
               info += TextUtils.isEmpty(info) ? s : "," + s;
           }
       }
       return info;
   }

}
