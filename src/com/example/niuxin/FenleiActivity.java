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
	TextView textView1_title1, textView1_summary1, textView1_type1, textView1_people1, //第一组数据
	         textView1_title2, textView1_summary2, textView1_type2, textView1_people2, //第二组数据
	         textView2_title1, textView2_summary1, textView2_type1, textView2_people1, //第三组数据
	         textView2_title2, textView2_summary2, textView2_type2, textView2_people2, //第四组数据
	         textView3_title1, textView3_summary1, textView3_type1, textView3_people1, //第五组数据
	         textView3_title2, textView3_summary2, textView3_type2, textView3_people2, //第六组数据
	         textView4_title1, textView4_summary1, textView4_type1, textView4_people1, //第七组数据
	         textView4_title2, textView4_summary2, textView4_type2, textView4_people2, //第八组数据
	         textView5_title1, textView5_summary1, textView5_type1, textView5_people1, //第九组数据
	         textView5_title2, textView5_summary2, textView5_type2, textView5_people2, //第十组数据
	         textView6_title1, textView6_summary1, textView6_type1, textView6_people1, //第十一组数据
	         textView6_title2, textView6_summary2, textView6_type2, textView6_people2 ;//第十二组数据
	        
	        
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
		textView1_title1 = (TextView) view1.findViewById(R.id.item1_title1);
		textView1_summary1 = (TextView) view1.findViewById(R.id.item1_summary1);
		textView1_type1 = (TextView) view1.findViewById(R.id.item1_type1);
		textView1_people1 = (TextView) view1.findViewById(R.id.item1_people1);
		
		textView1_title2 = (TextView) view1.findViewById(R.id.item1_title2);
		textView1_summary2 = (TextView) view1.findViewById(R.id.item1_summary2);
		textView1_type2 = (TextView) view1.findViewById(R.id.item1_type2);
		textView1_people2 = (TextView) view1.findViewById(R.id.item1_people2);
		
		textView2_title1 = (TextView) view2.findViewById(R.id.item2_title1);
		textView2_summary1 = (TextView) view2.findViewById(R.id.item2_summary1);
		textView2_type1 = (TextView) view2.findViewById(R.id.item2_type1);
		textView2_people1 = (TextView) view2.findViewById(R.id.item2_people1);
		
		textView2_title2 = (TextView) view2.findViewById(R.id.item2_title2);
		textView2_summary2 = (TextView) view2.findViewById(R.id.item2_summary2);
		textView2_type2 = (TextView) view2.findViewById(R.id.item2_type2);
		textView2_people2 = (TextView) view2.findViewById(R.id.item2_people2);
		
		textView3_title1 = (TextView) view3.findViewById(R.id.item3_title1);
		textView3_summary1 = (TextView) view3.findViewById(R.id.item3_summary1);
		textView3_type1 = (TextView) view3.findViewById(R.id.item3_type1);
		textView3_people1 = (TextView) view3.findViewById(R.id.item3_people1);
		
		textView3_title2 = (TextView) view3.findViewById(R.id.item3_title2);
		textView3_summary2 = (TextView) view3.findViewById(R.id.item3_summary2);
		textView3_type2 = (TextView) view3.findViewById(R.id.item3_type2);
		textView3_people2 = (TextView) view3.findViewById(R.id.item3_people2);
		
		textView4_title1 = (TextView) view4.findViewById(R.id.item4_title1);
		textView4_summary1 = (TextView) view4.findViewById(R.id.item4_summary1);
		textView4_type1 = (TextView) view4.findViewById(R.id.item4_type1);
		textView4_people1 = (TextView) view4.findViewById(R.id.item4_people1);
		
		textView4_title2 = (TextView) view4.findViewById(R.id.item4_title2);
		textView4_summary2 = (TextView)view4.findViewById(R.id.item4_summary2);
		textView4_type2 = (TextView)view4.findViewById(R.id.item4_type2);
		textView4_people2 = (TextView)view4.findViewById(R.id.item4_people2);
		
		textView5_title1 = (TextView)view5.findViewById(R.id.item5_title1);
		textView5_summary1 = (TextView)view5.findViewById(R.id.item5_summary1);
		textView5_type1 = (TextView)view5.findViewById(R.id.item5_type1);
		textView5_people1 = (TextView)view5.findViewById(R.id.item5_people1);
		
		textView5_title2 = (TextView)view5.findViewById(R.id.item5_title2);
		textView5_summary2 = (TextView)view5.findViewById(R.id.item5_summary2);
		textView5_type2 = (TextView)view5.findViewById(R.id.item5_type2);
		textView5_people2 = (TextView)view5.findViewById(R.id.item5_people2);
		
		textView6_title1 = (TextView)view6.findViewById(R.id.item6_title1);
		textView6_summary1 = (TextView)view6.findViewById(R.id.item6_summary1);
		textView6_type1 = (TextView)view6.findViewById(R.id.item6_type1);
		textView6_people1 = (TextView) view6.findViewById(R.id.item6_people1);
		
		textView6_title2 = (TextView) view6.findViewById(R.id.item6_title2);
		textView6_summary2 = (TextView)view6.findViewById(R.id.item6_summary2);
		textView6_type2 = (TextView)view6.findViewById(R.id.item6_type2);
		textView6_people2 = (TextView)view6.findViewById(R.id.item6_people2);
		
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
		//为您推荐，第一组数据
		textView1_title1.setText("中信证券讨论组");
		textView1_summary1.setText("中信证券综合讨论，分析买入卖出时机");
		textView1_type1.setText("个股");
		textView1_people1.setText("12/25");
        //为您推荐，第二组数据
		textView1_title2.setText("一带一路讨论组");
		textView1_summary2.setText("一带一路相关个股分析讨论");
		textView1_type2.setText("个股");
		textView1_people2.setText("28/40");
        //为您推荐，第三组数据
		textView2_title1.setText("中信证券讨论组");
		textView2_summary1.setText("中信证券综合讨论，分析买入卖出时机");
		textView2_type1.setText("个股");
		textView2_people1.setText(" 12/25");
        //为您推荐，第四组数据
		textView2_title2.setText("中信证券讨论组");
		textView2_summary2.setText("中信证券综合讨论，分析买入卖出时机");
		textView2_type2.setText("个股");
		textView2_people2.setText("12/25");
        //24小时最热，第一组数据
		textView3_title1.setText("创业板买什么");
		textView3_summary1.setText("教你创业板如何挣钱");
		textView3_type1.setText("板块");
		textView3_people1.setText("98/100");
        //24小时最热，第二组数据
		textView3_title2.setText("灌水扯淡专房1");
		textView3_summary2.setText("谈天说地，互相治愈");
		textView3_type2.setText("个股 ");
		textView3_people2.setText("68/200");
		//24小时最热，第三组数据
		textView4_title1.setText("中信证券讨论组");
		textView4_summary1.setText("中信证券综合讨论，分析买入卖出时机");
		textView4_type1.setText("个股 ");
		textView4_people1.setText("12/25");
        //24小时最热，第四组数据
		textView4_title2.setText("中信证券讨论组");
		textView4_summary2.setText("中信证券综合讨论，分析买入卖出时机");
		textView4_type2.setText("个股");
		textView4_people2.setText(" 12/25");
        //股市学堂，第一组数据
		textView5_title1.setText("汪老师课堂");
		textView5_summary1.setText("汪老师的炒股讲堂");
		textView5_type1.setText("板块");
		textView5_people1.setText("18/20");
        //股市学堂，第二组数据
		textView5_title2.setText("技术免费教学群");
		textView5_summary2.setText("教你如何用技术分析法挣钱");
		textView5_type2.setText("板块");
		textView5_people2.setText("32/40");
		//股市学堂，第三组数据
		textView6_title1.setText("中信证券讨论组");
		textView6_summary1.setText("中信证券综合讨论，分析买入卖出时机");
		textView6_type1.setText("个股 ");
        textView6_people1.setText("12/25");
        //股市学堂，第四组数据
		textView6_title2.setText("中信证券讨论组");
		textView6_summary2.setText("中信证券综合讨论，分析买入卖出时机");
		textView6_type2.setText("个股");
		textView6_people2.setText("12/25");
        //定义的12个复选框，初始化星星的颜色为无色
		checkBox1.setBackgroundResource(R.drawable.star_03);
		checkBox2.setBackgroundResource(R.drawable.star_03);
		checkBox3.setBackgroundResource(R.drawable.star_03);
		checkBox4.setBackgroundResource(R.drawable.star_03);
		checkBox5.setBackgroundResource(R.drawable.star_03);
		checkBox6.setBackgroundResource(R.drawable.star_03);
		checkBox7.setBackgroundResource(R.drawable.star_03);
		checkBox8.setBackgroundResource(R.drawable.star_03);
		checkBox9.setBackgroundResource(R.drawable.star_03);
		checkBox10.setBackgroundResource(R.drawable.star_03);
		checkBox11.setBackgroundResource(R.drawable.star_03);
		checkBox12.setBackgroundResource(R.drawable.star_03);
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
		 * 点击星星变成黄色，总共有12个星星可以点击，前四个为为您推荐对应的星星，中间的为24小时最热四组数据对应的星星，
		 * 最后四个为股市学堂四组数据对应的星星，可以在这里面点击以后星星变色，同时传递相应的数据。
		 */
		checkBox1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {  //布尔值判断复选框是否被选中，如果选中的话星星变成黄色
					checkBox1.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox1.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox2.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox2.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox3.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox3.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox4.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox4.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox5.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox5.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox5.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox6.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox6.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox6.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox7.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox7.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox8.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox8.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox8.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox8.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox9.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox9.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox9.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox10.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox10.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox10.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox11.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox11.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox11.setBackgroundResource(R.drawable.star_03);
				}
			}
		});
		checkBox12.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if (arg1) {
					checkBox12.setBackgroundResource(R.drawable.star_05);
				} else {
					checkBox12.setBackgroundResource(R.drawable.star_03);
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
