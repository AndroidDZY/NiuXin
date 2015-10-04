package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class DeclarationLaunchActivity extends Activity{
	
	Button iDeclarationDetail ;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch);
		
		//初始化
		iDeclarationDetail=(Button)findViewById(R.id.declaration_launch_detail);
		
		//事件监听
		//跳转到报单详情
		iDeclarationDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(DeclarationLaunchActivity.this ,DeclarationDetailActivity.class);
				startActivity(intent);
			}
		});
	}	

}
