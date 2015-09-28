package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;

public class GushiriliActivity extends Activity{
	Button buttonChangerole, buttonRolelaunch,buttonRolereceive;
//	Spinner spinnerChangerole;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.main_gushirili);
		//获取角色改变，发布操盘，接收操盘按钮监听
		//buttonChangerole=(Button)findViewById(R.id.declaration_changerole);
		buttonRolelaunch=(Button)findViewById(R.id.declaration_rolelaunch);
		buttonRolereceive=(Button)findViewById(R.id.declaration_rolereceive);
//		spinnerChangerole=(Spinner)findViewById(R.id.declaration_changerole);
		//跳转到我要报单界面
		buttonRolelaunch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(GushiriliActivity.this,DeclarationLaunchActivity.class);
				startActivity(intent);
			}
		});
		//跳转到接收报单界面
		buttonRolereceive.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(GushiriliActivity.this,DeclarationReceiveActivity.class);
				startActivity(intent);
			}
		});
		//下拉事件监听
		/*spinnerChangerole.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});*/
	}

}
