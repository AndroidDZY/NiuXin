package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreatequnActivity extends Activity {
	Button cancle,finish;
	//1
	private SuoluetuActivity suolue;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createqun);
		
		//2
		suolue = new SuoluetuActivity(this);
		//获取各种控件
		cancle = (Button)findViewById(R.id.btn_cq_cancle);
		finish = (Button)findViewById(R.id.btn_cq_finish);
		//定义按钮事件
		//取消按钮
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//完成按钮，跳转到邀请成员界面
		finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(CreatequnActivity.this,yaoqingchengyuanActivity.class);
				startActivity(intent);
			}
		});
	}

}
