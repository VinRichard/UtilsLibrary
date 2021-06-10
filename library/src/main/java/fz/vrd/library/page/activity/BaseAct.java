package fz.vrd.library.page.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import fz.vrd.library.page.Page;
import fz.vrd.library.page.PageImp;


/**
 * <b>类名称：  <br/>
 * <b>创建人： Administrator <br/>
 * <b>时间： 2021/6/10 10:07 <br/>
 * <b>说明：{ } <br/>
 */


public abstract class BaseAct extends FragmentActivity {

    protected Page page;

    /**
     * 设置沉浸式状态栏
     */
    boolean isSetStatusBar;
    /**
     * 是否允许全屏
     */
    boolean isAllowFullScreen;

    /**
     * 是否允许旋转
     */
    boolean isAllowScreenRoate;

    /**
     * 是否竖屏显示
     */
    boolean isPortraitScreen = true;

    /**
     * 获取layout的id
     */
    public abstract int getLayoutId();

    /**
     * 初始化
     */
    public abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = new PageImp(this);
        if (isPortraitScreen()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    public boolean isSetStatusBar() {
        return isSetStatusBar;
    }

    public void setSetStatusBar(boolean setStatusBar) {
        isSetStatusBar = setStatusBar;
    }

    public boolean isAllowFullScreen() {
        return isAllowFullScreen;
    }

    public void setAllowFullScreen(boolean allowFullScreen) {
        isAllowFullScreen = allowFullScreen;
    }

    public boolean isAllowScreenRoate() {
        return isAllowScreenRoate;
    }

    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    public boolean isPortraitScreen() {
        return isPortraitScreen;
    }

    public void setPortraitScreen(boolean portraitScreen) {
        isPortraitScreen = portraitScreen;
    }
}
