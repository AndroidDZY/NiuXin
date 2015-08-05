package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SuoluetuActivity extends Activity{
    private Button zhankai,edit,btn01,btn02,btn03,btn04,btn05,btn06,btn07,btn08;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.suolue);
		//获取button
		zhankai=(Button)findViewById(R.id.btn_open);
		edit=(Button)findViewById(R.id.btn_edit);
		btn01=(Button)findViewById(R.id.btn01);
		btn02=(Button)findViewById(R.id.btn02);
		btn03=(Button)findViewById(R.id.btn03);
		btn04=(Button)findViewById(R.id.btn04);
		btn05=(Button)findViewById(R.id.btn05);
		btn06=(Button)findViewById(R.id.btn06);
		btn07=(Button)findViewById(R.id.btn07);
		btn08=(Button)findViewById(R.id.btn08);
		
		/* 点击展开按钮跳转到看盘详情页五档 */
		zhankai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent (SuoluetuActivity.this, kanpan_wudangActivity.class) );
			}
		});
		
		/* 点击编辑自选按钮跳转到编辑自选界面 */
		edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent (SuoluetuActivity.this, edit_zixuanActivity.class) );
			}
		});
		
		//点击btn01-btn08都跳转到添加自选界面
		btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
		btn08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent (SuoluetuActivity.this, zixuan_addActivity.class) );
			}
		});
	}

}

