package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class edit_zixuanActivity extends Activity{
	ImageButton ibtn_add;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_zixuan);
		
		//获取ImageButton
		ibtn_add = (ImageButton)this.findViewById(R.id.ibtn_edit_add);
		//为ImageButton绑定点击事件
		ibtn_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(edit_zixuanActivity.this,zixuan_addActivity.class));
			}
		});

	}

}
