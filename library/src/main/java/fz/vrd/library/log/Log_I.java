package fz.vrd.library.log;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/8 15:11 <br/>
 * <b>说明：{ } <br/>
 */
interface Log_I {


    boolean isDebugEnabled();

    boolean isTraceEnabled();

    /**
     * 返回 本地保存的错误日志主目录路径
     */
    String getLocalErrorFileDir();

    void error(String var1, Throwable var2);

    void e(String var1);

    void e(String tag,String var1);

    void d(String var1);

    void d(String tag,String var1);

    void w(String var1);

    void v(String var1);

    void i(String var1);

    void wtf(String var1);

    void json(String var1);

    void xml(String var1);
}
