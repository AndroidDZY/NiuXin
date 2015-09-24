package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class DeclarationReceiveActivity extends Activity{
	private Button btnSetting, btnSource, btnType;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_receive);
		
		initView();
	}

	private void initView() {
		//获取各种控件
		btnSetting = (Button)findViewById(R.id.btn_setting);
		
	}

}
