package github.graded_reader;
///**
// * 
// */
//package cronkd.tutorial.android.testing;
//
//import android.app.ActionBar;
//import android.app.ActionBar.Tab;
//import android.app.ActionBar.TabListener;
//import android.app.Activity;
//import android.app.Fragment;
//import android.app.FragmentTransaction;
//import android.os.Bundle;
//
///**
// * @author smokey
// *
// */
//public class MainTabActivity {
//	
//	@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        
//        //setup for tabs
//        ActionBar actionBar = getActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowTitleEnabled(false);
//        
//        //grammar tab
//        Tab tab = actionBar.newTab()
//        					.setText("Grammar")
//        					.setTabListener(new MyTabListener<DetailFragment>(
//        												this,"artist",DetailFragment.class));
//        actionBar.addTab(tab);
//        
//        //vocab tab
//        tab = actionBar.newTab()
//				.setText("Vocab")
//				.setTabListener(new MyTabListener<DetailFragment>(
//											this,"artist",DetailFragment.class));
//        actionBar.addTab(tab);
//        
//        //translate word tab
//        tab = actionBar.newTab()
//				.setText("Word")
//				.setTabListener(new MyTabListener<DetailFragment>(
//											this,"artist",DetailFragment.class));
//        actionBar.addTab(tab);
//        
//        //translate sentence tab
//        tab = actionBar.newTab()
//				.setText("Sentence")
//				.setTabListener(new MyTabListener<DetailFragment>(
//											this,"artist",DetailFragment.class));
//        actionBar.addTab(tab);
//    }
//    
//    public static class MyTabListener<T extends Fragment> implements TabListener {
//    	private Fragment mFragment;
//    	private final Activity mActivity;
//    	private final String mTag;
//    	private final Class<T> mClass;
//    	
//    	public MyTabListener(Activity activity, String tag, Class<T> clz) {
//    		mActivity = activity;
//    		mTag = tag;
//    		mClass = clz;
//    	}
//    	
//    	public void onTabSelected(Tab tab, FragmentTransaction ft) {
//    		if (mFragment == null) {
//    			mFragment = Fragment.instantiate(mActivity, mClass.getName());
//    			ft.add(android.R.id.content, mFragment, mTag);
//    		} else {
//    			ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
//    			ft.detach(mFragment);
//    		}
//    	}
//    	
//    	public void onTabReselected(Tab tab, FragmentTransaction ft) {
//    	}
//
//		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//			if (mFragment != null) {
//				ft.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
//				ft.detach(mFragment);
//			}
//		}
//    	
//    }
//}
