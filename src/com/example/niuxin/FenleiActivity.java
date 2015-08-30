package com.example.niuxin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class FenleiActivity extends Activity {

	private ViewPager mViewPager1, mViewPager2, mViewPager3;
	private ArrayList<View> mPageViews1, mPageViews2, mPageViews3;

	LayoutInflater mInflater;
	private View view1, view2, view3, view4, view5, view6;
	// 8月29号改动，增加相应按钮的事件监听
	TextView textView1, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9,
			textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18,
			textView19, textView20, textView21, textView22, textView23, textView24, textView25, textView26, textView27,
			textView28, textView29, textView30, textView31, textView32, textView33, textView34, textView35, textView36,
			textView37, textView38, textView39, textView40, textView41, textView42, textView43, textView44, textView45,
			textView46, textView47, textView48;
	CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9,
			checkBox10, checkBox11, checkBox12;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置窗口无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fenlei);
		// mainViewGroup = (ViewGroup) mInflater.inflate(R.layout.fenlei, null);
		// mViewPager = (ViewPager)
		// mainViewGroup.findViewById(R.id.myviewpager1);
		mViewPager1 = (ViewPager) findViewById(R.id.myviewpager1);
		mViewPager2 = (ViewPager) findViewById(R.id.myviewpager2);
		mViewPager3 = (ViewPager) findViewById(R.id.myviewpager3);

		// setContentView(mainViewGroup);
		// mViewPager.setAdapter(new MyPagerAdapter());
		view1 = findViewById(R.layout.item1);
		view2 = findViewById(R.layout.item2);
		view3 = findViewById(R.layout.item3);
		view4 = findViewById(R.layout.item4);
		view5 = findViewById(R.layout.item5);
		view6 = findViewById(R.layout.item6);
		getLayoutInflater();
		mInflater = LayoutInflater.from(this);
		view1 = mInflater.inflate(R.layout.item1, null);
		view2 = mInflater.inflate(R.layout.item2, null);
		view3 = mInflater.inflate(R.layout.item3, null);
		view4 = mInflater.inflate(R.layout.item4, null);
		view5 = mInflater.inflate(R.layout.item5, null);
		view6 = mInflater.inflate(R.layout.item6, null);
		/*
		 * 30号改动，添加滑动界面各个控件的监听。
		 */
		textView1 = (TextView) view1.findViewById(R.id.item1_title1);
		textView2 = (TextView) view1.findViewById(R.id.item1_summary1);
		textView3 = (TextView) view1.findViewById(R.id.item1_type1);
		textView4 = (TextView) view1.findViewById(R.id.item1_people1);
		
		textView5 = (TextView) view1.findViewById(R.id.item1_title2);
		textView6 = (TextView) view1.findViewById(R.id.item1_summary2);
		textView7 = (TextView) view1.findViewById(R.id.item1_type2);
		textView8 = (TextView) view1.findViewById(R.id.item1_people2);
		
		textView9 = (TextView) view2.findViewById(R.id.item2_title1);
		textView10 = (TextView) view2.findViewById(R.id.item2_summary1);
		textView11 = (TextView) view2.findViewById(R.id.item2_type1);
		textView12 = (TextView) view2.findViewById(R.id.item2_people1);
		
		textView13 = (TextView) view2.findViewById(R.id.item2_title2);
		textView14 = (TextView) view2.findViewById(R.id.item2_summary2);
		textView15 = (TextView) view2.findViewById(R.id.item2_type2);
		textView16 = (TextView) view2.findViewById(R.id.item2_people2);
		
		textView17 = (TextView) view3.findViewById(R.id.item3_title1);
		textView18 = (TextView) view3.findViewById(R.id.item3_summary1);
		textView19 = (TextView) view3.findViewById(R.id.item3_type1);
		textView20 = (TextView) view3.findViewById(R.id.item3_people1);
		
		textView21 = (TextView) view3.findViewById(R.id.item3_title2);
		textView22 = (TextView) view3.findViewById(R.id.item3_summary2);
		textView23 = (TextView) view3.findViewById(R.id.item3_type2);
		textView24 = (TextView) view3.findViewById(R.id.item3_people2);
		
		textView25 = (TextView) view4.findViewById(R.id.item4_title1);
		textView26 = (TextView) view4.findViewById(R.id.item4_summary1);
		textView27 = (TextView) view4.findViewById(R.id.item4_type1);
		textView28 = (TextView) view4.findViewById(R.id.item4_people1);
		
		textView29 = (TextView) view4.findViewById(R.id.item4_title2);
		textView30 = (TextView) view4.findViewById(R.id.item4_summary2);
		textView31 = (TextView) view4.findViewById(R.id.item4_type2);
		textView32 = (TextView) view4.findViewById(R.id.item4_people2);
		textView33 = (TextView) view5.findViewById(R.id.item5_title1);
		
		textView34 = (TextView) view5.findViewById(R.id.item5_summary1);
		textView35 = (TextView) view5.findViewById(R.id.item5_type1);
		textView36 = (TextView) view5.findViewById(R.id.item5_people1);
		textView37 = (TextView) view5.findViewById(R.id.item5_title2);
		textView38 = (TextView) view5.findViewById(R.id.item5_summary2);
		
		textView39 = (TextView) view5.findViewById(R.id.item5_type2);
		textView40 = (TextView) view5.findViewById(R.id.item5_people2);
		textView41 = (TextView) view6.findViewById(R.id.item6_title1);
		textView42 = (TextView) view6.findViewById(R.id.item6_summary1);
		textView43 = (TextView) view6.findViewById(R.id.item6_type1);
		
		textView44 = (TextView) view6.findViewById(R.id.item6_people1);
		textView45 = (TextView) view6.findViewById(R.id.item6_title2);
		textView46 = (TextView) view6.findViewById(R.id.item6_summary2);
		textView47 = (TextView) view6.findViewById(R.id.item6_type2);
		textView48 = (TextView) view6.findViewById(R.id.item6_people2);
		
		checkBox1 = (CheckBox) view1.findViewById(R.id.item1_star1);
		checkBox2 = (CheckBox) view1.findViewById(R.id.item1_star2);
		checkBox3 = (CheckBox) view2.findViewById(R.id.item2_star1);
		checkBox4 = (CheckBox) view2.findViewById(R.id.item2_star2);
		checkBox5 = (CheckBox) view3.findViewById(R.id.item3_star1);
		checkBox6 = (CheckBox) view3.findViewById(R.id.item3_star2);
		checkBox7 = (CheckBox) view4.findViewById(R.id.item4_star1);
		checkBox8 = (CheckBox) view4.findViewById(R.id.item4_star2);
		checkBox9 = (CheckBox) view5.findViewById(R.id.item5_star1);
		checkBox10 = (CheckBox) view5.findViewById(R.id.item5_star2);
		checkBox11 = (CheckBox) view6.findViewById(R.id.item6_star1);
		checkBox12 = (CheckBox) view6.findViewById(R.id.item6_star2);
		
		textView1.setText("中兴证券讨论组");
		textView2.setText("中兴证券综合讨论，分析买入卖出时机");
		textView3.setText("个股  ①");
		textView4.setText("12/25");

		textView5.setText("中兴证券讨论组");
		textView6.setText("中兴证券综合讨论，分析买入卖出时机");
		textView7.setText("个股  ①");
		textView8.setText("12/25");

		textView9.setText("中兴证券讨论组");
		textView10.setText("中兴证券综合讨论，分析买入卖出时机");
		textView11.setText("个股  ①");
		textView12.setText("12/25");

		textView13.setText("中兴证券讨论组");
		textView14.setText("中兴证券综合讨论，分析买入卖出时机");
		textView15.setText("个股  ①");
		textView16.setText("12/25");

		textView17.setText("中兴证券讨论组");
		textView18.setText("中兴证券综合讨论，分析买入卖出时机");
		textView19.setText("个股  ①");
		textView20.setText("12/25");

		textView21.setText("中兴证券讨论组");
		textView22.setText("中兴证券综合讨论，分析买入卖出时机");
		textView23.setText("个股  ①");
		textView24.setText("12/25");
		
		textView25.setText("中兴证券讨论组");
		textView26.setText("中兴证券综合讨论，分析买入卖出时机");
		textView27.setText("个股  ①");
		textView28.setText("12/25");

		textView29.setText("中兴证券讨论组");
		textView30.setText("中兴证券综合讨论，分析买入卖出时机");
		textView31.setText("个股  ①");
		textView32.setText("12/25");

		textView33.setText("中兴证券讨论组");
		textView34.setText("中兴证券综合讨论，分析买入卖出时机");
		textView35.setText("个股  ①");
		textView36.setText("12/25");

		textView37.setText("中兴证券讨论组");
		textView38.setText("中兴证券综合讨论，分析买入卖出时机");
		textView39.setText("个股  ①");
		textView40.setText("12/25");
		textView41.setText("中兴证券讨论组");
		textView42.setText("中兴证券综合讨论，分析买入卖出时机");
		textView43.setText("个股  ①");

		textView44.setText("12/25");
		textView45.setText("中兴证券讨论组");
		textView46.setText("中兴证券综合讨论，分析买入卖出时机");
		textView47.setText("个股  ①");
		textView48.setText("12/25");

		checkBox1.setBackgroundResource(R.drawable.star1);
		checkBox2.setBackgroundResource(R.drawable.star1);
		checkBox3.setBackgroundResource(R.drawable.star1);
		checkBox4.setBackgroundResource(R.drawable.star1);
		checkBox5.setBackgroundResource(R.drawable.star1);
		checkBox6.setBackgroundResource(R.drawable.star1);
		checkBox7.setBackgroundResource(R.drawable.star1);
		checkBox8.setBackgroundResource(R.drawable.star1);
		checkBox9.setBackgroundResource(R.drawable.star1);
		checkBox10.setBackgroundResource(R.drawable.star1);
		checkBox11.setBackgroundResource(R.drawable.star1);
		checkBox12.setBackgroundResource(R.drawable.star1);
		mPageViews1 = new ArrayList<View>();
		mPageViews1.add(view1);
		mPageViews1.add(view2);
		mPageViews2 = new ArrayList<View>();
		mPageViews2.add(view3);
		mPageViews2.add(view4);
		mPageViews3 = new ArrayList<View>();
		mPageViews3.add(view5);
		mPageViews3.add(view6);
		mViewPager1.setAdapter(new MyPagerAdapter(mPageViews1));
		mViewPager2.setAdapter(new MyPagerAdapter(mPageViews2));
		mViewPager3.setAdapter(new MyPagerAdapter(mPageViews3));

		// mPageViews = new ArrayList<View>();
		// mPageViews.add(mInflater.inflate(R.layout.item1, null));
		// mPageViews.add(mInflater.inflate(R.layout.item2, null));
		/*
		 * 点击星星变成黄色，总共有12个星星可以点击
		 */
		checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox1.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox1.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox2.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox2.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox3.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox3.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox4.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox4.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox5.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox5.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox5.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox6.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox6.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox7.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox7.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox8.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox8.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox8.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox8.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox9.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox9.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox9.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox10.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox10.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox10.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox11.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox11.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox11.setBackgroundResource(R.drawable.star1);
				}
			}
		});
		checkBox12.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox12.setBackgroundResource(R.drawable.star2);
				} else {
					checkBox12.setBackgroundResource(R.drawable.star1);
				}
			}
		});
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

	public class MyPagerAdapter extends PagerAdapter {
		private ArrayList<View> mPageViews = null;

		public MyPagerAdapter(ArrayList<View> mPageViews) {
			this.mPageViews = mPageViews;
		}

		@Override
		public int getCount() {
			return mPageViews.size();
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
			((ViewPager) arg0).removeView(mPageViews.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).addView(mPageViews.get(arg1));
			return mPageViews.get(arg1);
		}
	}

}
