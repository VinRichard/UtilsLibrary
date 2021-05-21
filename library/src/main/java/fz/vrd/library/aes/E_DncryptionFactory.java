package fz.vrd.library.aes;

import java.lang.reflect.Proxy;

import fz.vrd.library.reflect.ProxyHandler;

/**
 * <b>类名称：加密和解密  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/17 11:05 <br/>
 * <b>说明：{ } <br/>
 */
public class E_DncryptionFactory {


    public static Factory createMd5() {

        return new MD5();
    }

    public static Factory createBase64() {

        return new Base64();
    }

    public static Factory createBase64(int flag) {

        return new Base64(flag);
    }

    public static Factory createSha() {

        return new Sha();
    }

    public static Factory  create(Object o) {

        return (Factory)ProxyHandler.create().newProxyInstance(o);

    }


}
