package fz.vrd.library.log;

/**
 * <b>类名称或说明：日志管理  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/4/29 18:29<br/>
 * <b>修改备注：{
 * 日志等级: 错误,警告,普通
 * <p>
 * } <br/>
 */
public class LogFactory  implements Log_I  {

    static LogFactory factory;

    LogImp logImp;

    Log log;

    private LogFactory(){
        logImp = new LogImp();
    }

    public static LogFactory getInstance(){
        if(factory == null){
            factory = new LogFactory();
        }
        return factory;
    }

    public void build(Log log){
        this.log = log;
    }

    @Override
    public boolean isDebugEnabled() {
        return getLog().isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return getLog().isTraceEnabled();
    }

    @Override
    public String getLocalErrorFileDir() {
        return   getLog().getLocalErrorFileDir();
    }

    @Override
    public void error(String var1, Throwable var2) {
        getLog().error(var1,var2);
    }

    @Override
    public void e(String var1) {
        getLog().e(var1);
    }

    @Override
    public void e(String tag, String var1) {
        getLog().e(tag,var1);
    }

    @Override
    public void d(String var1) {
        getLog().d(var1);
    }

    @Override
    public void d(String tag, String var1) {
        getLog().d(tag,var1);
    }

    @Override
    public void w(String var1) {
        getLog().w(var1);
    }

    @Override
    public void v(String var1) {
        getLog().v(var1);
    }

    @Override
    public void i(String var1) {
        getLog().i(var1);
    }

    @Override
    public void wtf(String var1) {
        getLog().wtf(var1);
    }

    @Override
    public void json(String var1) {
        getLog().json(var1);
    }

    @Override
    public void xml(String var1) {
        getLog().xml(var1);
    }

    Log getLog(){
        return log == null ? logImp : log;
    }
}
