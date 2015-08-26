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
import android.widget.Toast;

public class SearchuserActivity  extends Activity{
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
		//定义按钮事件
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
