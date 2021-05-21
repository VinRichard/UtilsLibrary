package fz.vrd.library.log;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/5/18 16:33 <br/>
 * <b>说明：{ } <br/>
 */
public interface Log {

    boolean isDebugEnabled();

    boolean isTraceEnabled();

    void error(String var1, Throwable var2);

    void error(String var1);

    void debug(String var1);

    void trace(String var1);

    void warn(String var1);
}
