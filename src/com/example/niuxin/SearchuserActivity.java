package com.example.niuxin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchuserActivity  extends Activity{
	RelativeLayout relativeLayout1,relativeLayout2;
    LinearLayout linearLayout1, linearLayout2;
    TextView title1,title2,summary1,summary2,type1,type2,people1,people2;
    Button cancle,done,search,searchcancel;
	EditText editText;
	//1
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchuser);
		//2
		suolue = new SuoluetuActivity(this,handler);
		//获取各种控件
		cancle = (Button)findViewById(R.id.btn_searchuser_cancle);
		done = (Button)findViewById(R.id.btn_searchuser_finish);
		search = (Button)findViewById(R.id.su_search);
		searchcancel = (Button)findViewById(R.id.su_searchcancel);
		editText=(EditText)findViewById(R.id.su_edittext);
		//8月28号改动 增加群组推荐的各个部分的监听
		relativeLayout1=(RelativeLayout)findViewById(R.id.searchuser_relativelayout1);
		relativeLayout2=(RelativeLayout)findViewById(R.id.searchuser_relativelayout2);
		linearLayout1=(LinearLayout)findViewById(R.id.searchuser_relativelayout1_star1);
		linearLayout2=(LinearLayout)findViewById(R.id.searchuser_relativelayout2_star2);
        title1=(TextView)findViewById(R.id.searchuser_relativelayout1_title1);
        title2=(TextView)findViewById(R.id.searchuser_relativelayout2_title2);
        summary1=(TextView)findViewById(R.id.searchuser_relativelayout1_summary1);
        summary2=(TextView)findViewById(R.id.searchuser_relativelayout2_summary2);
        people1=(TextView)findViewById(R.id.searchuser_relativelayout1_people1);
        people2=(TextView)findViewById(R.id.searchuser_relativelayout2_people2);
        type1=(TextView)findViewById(R.id.searchuser_relativelayout1_type1);
        type2=(TextView)findViewById(R.id.searchuser_relativelayout2_type2);
        title1.setText("中兴证券讨论组");
        title2.setText("中心证券讨论组");
        summary1.setText("中兴证券综合讨论，分析买入卖出时机");
        summary2.setText("中兴证券综合讨论，分析买入卖出时机");
        type1.setText("个股  ①");
        type2.setText("个股  ①");
        people1.setText("12/25");
        people2.setText("12/25");
        //动态添加button按钮到线性布局中
        final Button button1 = new Button(SearchuserActivity.this);
        final Button button2 = new Button(SearchuserActivity.this);
		button1.setBackgroundResource(R.drawable.star1);
		button2.setBackgroundResource(R.drawable.star2);
		linearLayout1.addView(button1);
		linearLayout2.addView(button2);
		//定义按钮事件
        //监听整个相对布局，当点击的时候加载黄色星星
		relativeLayout1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				button1.setBackgroundResource(R.drawable.star2);
				
			}
		});
        relativeLayout2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				button2.setBackgroundResource(R.drawable.star2);
			}
		});
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		searchcancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
		           imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
			}
		});
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//判断编辑框中输入的内容是否为空
				if (editText.getText().toString().length()==0) {
					Toast.makeText(getApplicationContext(), "请输入名称关键字或股票代码", 0).show();
					
				} else {
					Intent intent = new Intent(SearchuserActivity.this,SearchresultActivity.class);
					startActivity(intent);
				}
			}
		});
	}

}
