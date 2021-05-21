package fz.vrd.library.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <b>类名称：动态代理  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/18 10:14 <br/>
 * <b>说明：{ } <br/>
 */
public class ProxyHandler implements InvocationHandler {

    Object object;

    public static ProxyHandler create() {
        return new ProxyHandler();
    }


    public Object newProxyInstance(Object o) {
        this.object = o;
        return Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.invoke(object, args);
        return null;
    }
}
