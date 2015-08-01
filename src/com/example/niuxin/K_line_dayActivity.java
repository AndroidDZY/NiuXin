package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class K_line_dayActivity extends Activity{
	private Button btn_k_cancle,btn_k_set;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.k_line_day);
		//获得Button
		btn_k_cancle = (Button)findViewById(R.id.btn_k_cancle01);
		btn_k_set = (Button)findViewById(R.id.btn_k_set01);
		
		//点击取消返回上一个界面
		btn_k_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
				
			}
		});
		
		//点击设置跳转到k线设置界面
		btn_k_set.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(K_line_dayActivity.this,K_line_setActivity.class);
				startActivity(intent);
				
			}
		});
	}

}