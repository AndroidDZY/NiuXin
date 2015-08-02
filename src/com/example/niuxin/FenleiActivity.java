package com.example.niuxin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class FenleiActivity extends Activity{
	
	private ViewPager mViewPager1,mViewPager2,mViewPager3;
	private ArrayList<View> mPageViews1,mPageViews2,mPageViews3;
	private ViewGroup mainViewGroup;
	LayoutInflater mInflater;
	private View view1,view2,view3,view4,view5,view6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//设置窗口无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fenlei);
        //mainViewGroup = (ViewGroup) mInflater.inflate(R.layout.fenlei, null);
        //mViewPager = (ViewPager) mainViewGroup.findViewById(R.id.myviewpager1);
		mViewPager1 = (ViewPager)findViewById(R.id.myviewpager1);
		mViewPager2 = (ViewPager)findViewById(R.id.myviewpager2);
		mViewPager3 = (ViewPager)findViewById(R.id.myviewpager3);
        //setContentView(mainViewGroup);
        //mViewPager.setAdapter(new MyPagerAdapter());
        view1=findViewById(R.layout.item1);
        view2=findViewById(R.layout.item2);
        view3=findViewById(R.layout.item3);
        view4=findViewById(R.layout.item4);
        view5=findViewById(R.layout.item5);
        view6=findViewById(R.layout.item6);
        getLayoutInflater();
		mInflater = LayoutInflater.from(this);
        view1=mInflater.inflate(R.layout.item1, null);
        view2=mInflater.inflate(R.layout.item2, null);
        view3=mInflater.inflate(R.layout.item3, null);
        view4=mInflater.inflate(R.layout.item4, null);
        view5=mInflater.inflate(R.layout.item5, null);
        view6=mInflater.inflate(R.layout.item6, null);
        mPageViews1= new ArrayList<View>();
        mPageViews1.add(view1);
        mPageViews1.add(view2);
        mPageViews2= new ArrayList<View>();
        mPageViews2.add(view3);
        mPageViews2.add(view4);
        mPageViews3= new ArrayList<View>();
        mPageViews3.add(view5);
        mPageViews3.add(view6);
//        mPageViews = new ArrayList<View>();
//        mPageViews.add(mInflater.inflate(R.layout.item1, null));
//        mPageViews.add(mInflater.inflate(R.layout.item2, null));
        mViewPager1.setAdapter(new MyPagerAdapter1());
        mViewPager2.setAdapter(new MyPagerAdapter2());
        mViewPager3.setAdapter(new MyPagerAdapter3());
        mViewPager1.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
           mViewPager2.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
           mViewPager3.setOnPageChangeListener(new OnPageChangeListener() {
   			
   			@Override
   			public void onPageSelected(int arg0) {
   				// TODO Auto-generated method stub
   				
   			}
   			
   			@Override
   			public void onPageScrolled(int arg0, float arg1, int arg2) {
   				// TODO Auto-generated method stub
   				
   			}
   			
   			@Override
   			public void onPageScrollStateChanged(int arg0) {
   				// TODO Auto-generated method stub
   				
   			}
   		});

	}
	public class MyPagerAdapter1 extends PagerAdapter {

	    	@Override  
	        public int getCount() {  
	            return mPageViews1.size();  
	        }  
	  
	        @Override  
	        public boolean isViewFromObject(View arg0, Object arg1) {  
	            return arg0 == arg1;  
	        }  
	  
	        @Override  
	        public int getItemPosition(Object object) {  
	            // TODO Auto-generated method stub  
	            return super.getItemPosition(object);  
	        }  
	  
	        @Override  
	        public void destroyItem(View arg0, int arg1, Object arg2) {  
	            // TODO Auto-generated method stub  
	            ((ViewPager) arg0).removeView(mPageViews1.get(arg1));  
	        }  
	  
	        @Override  
	        public Object instantiateItem(View arg0, int arg1) {  
	            // TODO Auto-generated method stub  
	            ((ViewPager) arg0).addView(mPageViews1.get(arg1));  
	            return mPageViews1.get(arg1);  
	        }  
	  
	        @Override  
	        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
	            // TODO Auto-generated method stub  
	  
	        }  
	  
	        @Override  
	        public Parcelable saveState() {  
	            // TODO Auto-generated method stub  
	            return null;  
	        }  
	  
	        @Override  
	        public void startUpdate(View arg0) {  
	            // TODO Auto-generated method stub  
	  
	        }  
	  
	        @Override  
	        public void finishUpdate(View arg0) {  
	            // TODO Auto-generated method stub  
	  
	        } 
	    	
	    }
	
	public class MyPagerAdapter2 extends PagerAdapter {

    	@Override  
        public int getCount() {  
            return mPageViews2.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(mPageViews2.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).addView(mPageViews2.get(arg1));  
            return mPageViews2.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        } 
    	
    }
	
	public class MyPagerAdapter3 extends PagerAdapter {

    	@Override  
        public int getCount() {  
            return mPageViews3.size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            // TODO Auto-generated method stub  
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).removeView(mPageViews3.get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, int arg1) {  
            // TODO Auto-generated method stub  
            ((ViewPager) arg0).addView(mPageViews3.get(arg1));  
            return mPageViews3.get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            // TODO Auto-generated method stub  
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            // TODO Auto-generated method stub  
  
        } 
    	
    }

}
