package fz.vrd.library.page.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fz.vrd.library.page.Page;
import fz.vrd.library.page.PageImp;
import fz.vrd.utils.R;

/**
 * <b>名称：  <br/>
 * <b>创建人： VinRichard <br/>
 * <b>时间： 2021/6/10 14:26 <br/>
 * <b>备注：{ } <br/>
 */
public abstract class BaseFragment extends Fragment {

   protected Page page;

    /**
     * 获取layoutid
     */
    protected abstract  int getContentLayoutId();

    /**
     * 开始初始化 在onActivityCreated 方法里面执行
     */
    protected abstract void initView();



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        page = new PageImp(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getContentLayoutId() > 0){
            return inflater.inflate(getContentLayoutId(), container, false);
        }
        return super.onCreateView(inflater,container,savedInstanceState);
    }
}
