package fz.vrd.library.aes;

import android.os.Build;

/**
 * <b>类名称：Base64加密和解密  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/14 12:34 <br/>
 * <b>说明：{
 * Base64是网络上最常见的用于传输8Bit字节代码的编码方式之一，大家可以查看RFC2045～RFC2049，上面有MIME的详细规范。
 * Base64编码可用于在HTTP环境下传递较长的标识信息。
 * 例如，在Java Persistence系统Hibernate中，就采用了Base64来将一个较长的唯一标识符（一般为128-bit的UUID）编码为一个字符串，用作HTTP表单和HTTP GET URL中的参数。
 * 在其他应用程序中，也常常需要把二进制数据编码为适合放在URL（包括隐藏表单域）中的形式。
 * 此时，采用Base64编码具有不可读性，即所编码的数据不会被人用肉眼所直接看到。
 * } <br/>
 */
class Base64 implements Factory {

    private int flag = -1;


    public Base64() {

    }

    public Base64(int flag) {
        this.flag = flag;
    }


    @Override
    public String decryption(String key) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            return new String(java.util.Base64.getDecoder().decode(key));
//        }
        return new String(android.util.Base64.decode(key, getFlag()));
    }

    @Override
    public String encryption(String msg) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            return java.util.Base64.getEncoder().encodeToString(msg.getBytes());
//        }
        return android.util.Base64.encodeToString(msg.getBytes(), getFlag());
    }

    int getFlag() {
        if (flag == android.util.Base64.CRLF ||
                flag == android.util.Base64.NO_CLOSE ||
                flag == android.util.Base64.NO_PADDING ||
                flag == android.util.Base64.NO_WRAP ||
                flag == android.util.Base64.URL_SAFE) {

        } else {
            flag = android.util.Base64.DEFAULT;
        }

        return flag;
    }


}
