package fz.vrd.library.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2017/4/26 0026.
 * activity的管理者
 */

public class ActivityManage {

    public Stack<Activity> stackActivity = new Stack<>();

    //创建一个BaseActivity的实例
    public static ActivityManage create() {
        return new ActivityManage();
    }


    //添加一个Activity
    public void addAct(Activity activity) {
        stackActivity.add(activity);
    }


    //删除当前Activity
    public void removeTopAct() {
        stackActivity.lastElement().finish();
        stackActivity.remove(stackActivity.lastElement());
    }

    //删除指定的Activity
    public void remove(Activity activity) {
        if (activity != null) {
            for (int i = 0; stackActivity.size() > i; i++) {
                if (activity.getClass() == stackActivity.get(i).getClass()) {
                    stackActivity.get(i).finish();
                    stackActivity.remove(i);
                }
            }
        }
    }

    //删除所有的Activity
    public void removeAll() {
        for (int i = 0; stackActivity.size() > i; i++) {
            stackActivity.get(i).finish();
            stackActivity.remove(i);
        }
    }

    //返回栈的大小
    public int getStackActivitySize() {
        return stackActivity.size();
    }
}
