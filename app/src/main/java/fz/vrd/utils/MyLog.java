package fz.vrd.utils;

import fz.vrd.library.log.Log;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/8 15:43 <br/>
 * <b>说明：{ } <br/>
 */
public class MyLog implements Log {

    @Override
    public boolean isDebugEnabled() {
        return false;
    }

    @Override
    public boolean isTraceEnabled() {
        return false;
    }

    @Override
    public String getLocalErrorFileDir() {
        return null;
    }

    @Override
    public void error(String var1, Throwable var2) {

    }

    @Override
    public void e(String var1) {
        System.out.println("自定义e打印:" + var1);
    }

    @Override
    public void d(String var1) {

    }

    @Override
    public void w(String var1) {

    }

    @Override
    public void v(String var1) {

    }

    @Override
    public void i(String var1) {

    }

    @Override
    public void wtf(String var1) {

    }

    @Override
    public void json(String var1) {
        System.out.println("自定义json打印:" + var1);
    }

    @Override
    public void xml(String var1) {

    }
}
