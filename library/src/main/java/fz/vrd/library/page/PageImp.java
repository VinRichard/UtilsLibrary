package fz.vrd.library.page;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import fz.vrd.library.log.LogFactory;
import fz.vrd.library.toast.ToastCallBack;
import fz.vrd.library.utils.AppUtils;
import fz.vrd.library.utils.StringUtils;

/**
 * <b>名称：  <br/>
 * <b>创建人： VinRichard <br/>
 * <b>时间： 2021/6/10 11:12 <br/>
 * <b>备注：{ } <br/>
 */
public class PageImp implements Page {

    FragmentActivity activity;

    public PageImp(FragmentActivity activity){
        if(activity == null){
            throw new NullPointerException("初始化activity为null了");
        }
        this.activity = activity;
    }

    public PageImp(Fragment fragment){
        if(fragment == null){
            throw new NullPointerException("初始化fragment为null了");
        }
        this.activity = fragment.getActivity();
    }

    @Override
    public FragmentActivity getActivity() {
        return activity;
    }

    @Override
    public Context getContext() {
        return activity;
    }

    @Override
    public void backAct() {
        if(isAlive()){
            activity.finish();
        }
    }

    @Override
    public int getScreenWidth() {
        return AppUtils.getPhoneW(getContext());
    }

    @Override
    public int getScreenHeight() {
        return AppUtils.getPhoneH(getContext());
    }

    @Override
    public boolean isSetStatusBar() {
        return false;
    }

    @Override
    public boolean isAllowFullScreen() {
        return false;
    }

    @Override
    public boolean isAllowScreenRoate() {
        return false;
    }

    @Override
    public void log(String msg) {
        LogFactory.getInstance().d(msg);
    }

    @Override
    public void log(String tag, String msg) {
        LogFactory.getInstance().d(tag,msg);
    }

    @Override
    public void toast(String msg) {

    }

    @Override
    public void toast(String msg, ToastCallBack callBack) {

    }

    @Override
    public void showInputMethod(View view) {
        if (activity.getCurrentFocus() != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
            if(view != null){
                imm.showSoftInput(view,0);
            }else{
                imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(),0);
            }
        }
    }

    @Override
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public boolean isEmpty(CharSequence... arg) {
        return StringUtils.isEmpty(arg);
    }

    @Override
    public boolean isNum(String num) {
        return StringUtils.isNum(num);
    }

     boolean isAlive(){
        if(activity != null && !activity.isDestroyed()){
             return true;
        }
        return false;
     }
}
