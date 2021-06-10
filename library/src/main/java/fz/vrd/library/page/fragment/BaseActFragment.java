package fz.vrd.library.page.fragment;


import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * <b>
 * 类名称或说明： 新创建的fragment,这个新的父类fragment ,
 * 主要的目的是为了许多fragment在同一个activity上面使用,
 * 主要以碎片化使用为主
 * <p>
 * <br/>
 * <b>创建人： VinRichard <br/>
 * <b>时间： 2019/8/28 15:05<br/>
 * <b>修改备注：{ } <br/>
 */
public abstract class BaseActFragment extends BaseFragment {


    protected FragmentTransaction getFragmentManagerTransaction() {
        getFragmentManager().beginTransaction().add(0, null).addToBackStack("").commit();
        return getFragmentManager().beginTransaction();
    }

    protected void addAndCommit(Fragment fragment) {
        addAndCommit(fragment, fragment.getClass().getSimpleName());
    }

    protected void addAndCommit(Fragment fragment, String tag) {
        getFragmentManagerTransaction()
                .add(fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    protected void replace(@IdRes int containerViewId, Fragment fragment) {
        getFragmentManagerTransaction()
                .replace(containerViewId, fragment)
                .addToBackStack("")
                .commit();
    }


    protected void remove(Fragment fragment) {
        getFragmentManagerTransaction()
                .remove(fragment)
                .commit();
    }

    protected void backStack() {
        getFragmentManager().popBackStack();
        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

            }
        });
    }

}
