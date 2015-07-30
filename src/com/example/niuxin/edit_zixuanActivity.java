package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class edit_zixuanActivity extends Activity{
	Button btn_edit_add,btn_edit_cancle;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_zixuan);
		
		//获取Button
		btn_edit_add = (Button)findViewById(R.id.btn_edit_add11);
		btn_edit_cancle = (Button)findViewById(R.id.btn_edit_cancle);
		//为Button绑定点击事件
		btn_edit_add.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				finish();
				//startActivity(new Intent(edit_zixuanActivity.this,zixuan_addActivity.class));
			}
		});

		btn_edit_cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});	

				
				//startActivity(new Intent(edit_zixuanActivity.this,zixuan_addActivity.class));


	}

}
