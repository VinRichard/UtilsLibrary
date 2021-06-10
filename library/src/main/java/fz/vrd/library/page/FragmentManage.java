package fz.vrd.library.page;


import android.util.Log;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

 
  /**
   * <b>类名称或说明：  <br/>
   * <b>创建人： Administrator <br/>
   * <b>时间： 2021/6/10 14:26<br/>
   * <b>说明：{ } <br/>
   */
public class FragmentManage {

	private String TAG = FragmentManage.class.getName();

	int fragmentId = 0;

	List<Fragment> list_frl = new ArrayList<Fragment>();

	private int cuurent = -1;

	int animationsEnter,animationsExit;

	FragmentActivity activity;

	public FragmentManage(FragmentActivity activity){
		this.activity = activity;
	}

	/**
	 * 设置fragment 的资源id
	 *
	 */
	public void setFragmentResId(int resId) {
		this.fragmentId = resId;
	}

	/**
	 * 添加需要显示fragment,需要要按照顺序添加,否则会和你顺序不对于
	 */
	public void addFragment(Fragment fragment) {
		list_frl.add(fragment);
	}

	/**
	 * 提前加载fragment
	 */
	public void beforeLoadFrg(int index){
		FragmentManager manage = activity.getSupportFragmentManager();
		FragmentTransaction transaction = manage.beginTransaction();
		Fragment frl = list_frl.get(index);
		if(!frl.isAdded() && fragmentId > 0){
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.add(fragmentId, frl);
			transaction.commit();
		}
	}

	public void setCustomAnimations(@AnimatorRes @AnimRes int enter,@AnimatorRes @AnimRes int exit){
		this.animationsEnter = enter;
		this.animationsExit = exit;
	}

	/**
	 * 需要显示fragment
	 * 
	 * @param index
	 */
	public void showFrl(int index) {
		if (fragmentId == 0 || activity == null) {
			Log.e(TAG,"======ParentFragment 的资源id  is  null or activity is null=============");
			return;
		}

		if (cuurent != -1  && index < list_frl.size() && getCurrentFrl() == list_frl.get(index)) {
			return;
		}
		
		FragmentManager manage = activity.getSupportFragmentManager();
		FragmentTransaction transaction = manage.beginTransaction();
		Fragment frl = list_frl.get(index);

		if(!frl.isAdded()){
			transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			if(animationsEnter > 0 && animationsExit > 0){
				transaction.setCustomAnimations(animationsEnter,animationsExit);
			}
			transaction.add(fragmentId, frl);
		}else if(frl.isVisible()){
			return;
		}else{
			frl.onResume();
		}

		for (int i = 0; i < list_frl.size(); i++) {
			FragmentTransaction ft = activity.getSupportFragmentManager()
					.beginTransaction();
			if (index == i) {
				ft.show(list_frl.get(i));
			} else {
				ft.hide(list_frl.get(i));
			}
			ft.commit();
		}
		transaction.commit();
		cuurent = index;
	}

	/**
	 * 获取当前前显示分fragment
	 * @return
	 */
	public Fragment getCurrentFrl() {

		return (cuurent == -1 || cuurent > list_frl.size() - 1) ? null : list_frl.get(cuurent);
	}

	/**
	 * 获取当前前显示分fragmen的下标
	 */
	public int getCurrentFrlIndex() {

		return cuurent;
	}

	public void onDestroy(){
		list_frl.clear();
	}
}
