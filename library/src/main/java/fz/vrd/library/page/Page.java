package fz.vrd.library.page;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import fz.vrd.library.toast.ToastCallBack;

/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/10 10:11 <br/>
 * <b>说明：{ } <br/>
 */
public interface Page {

    FragmentActivity getActivity();

    Context getContext();

    /**
     * 返回上一个页面(手机的物理返回键也需要调用这个方法)
     */
    void backAct();

    /**
     * 获取手机屏幕宽高
     */
    int getScreenWidth();
    int getScreenHeight();

    /**
     * 设置沉浸式状态栏
     */
    boolean isSetStatusBar();

    /**
     * 是否允许全屏
     */
    boolean isAllowFullScreen();

    /**
     * 是否禁止旋转屏幕
     */
    boolean isAllowScreenRoate ();


    void log(String msg);

    void log(String tag,String msg);

    void toast(String msg);

    void toast(String msg, ToastCallBack callBack);

    /**
     * 软键盘的隐藏显示
     */
    void showInputMethod(View view);
    void hideSoftInput();


    boolean isEmpty(CharSequence... arg);

    boolean isNum(String num);

}
