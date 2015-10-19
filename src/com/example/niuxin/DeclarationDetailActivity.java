package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DeclarationDetailActivity extends Activity{
	
	RelativeLayout relativeLayoutModelChoice,relativeLayoutContactChoice;
	private Button buttonBack ,buttonSend ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch_detail);
		
		//数据初始化
		relativeLayoutModelChoice=(RelativeLayout)findViewById(R.id.detail_model_control);
		relativeLayoutContactChoice=(RelativeLayout)findViewById(R.id.detail_contact_type);
		buttonBack=(Button)findViewById(R.id.detail_button_back);
		buttonSend=(Button)findViewById(R.id.detail_button_send);
		//设置监听事件
		//模板选择跳转
		
		relativeLayoutModelChoice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DeclarationDetailActivity.this,DeclarationModelChoiceActivity.class);
				startActivity(intent);
			}
		});
		//合约类型选择界面跳转
		relativeLayoutContactChoice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DeclarationDetailActivity.this,DeclarationContactChoiceActivity.class);
				startActivity(intent);
			}
		});
		buttonBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		buttonSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//获取数据，发送
			}
		});
	}

}
