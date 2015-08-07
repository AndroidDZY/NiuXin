package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SearchuserActivity  extends Activity{
	Button cancle;
	//1
	private SuoluetuActivity suolue;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchuser);
		//2
		suolue = new SuoluetuActivity(this);
		//获取各种控件
		cancle = (Button)findViewById(R.id.btn_searchuser_cancle);
		
		//定义按钮事件
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
