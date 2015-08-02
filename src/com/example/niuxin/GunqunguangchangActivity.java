package com.example.niuxin;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class GunqunguangchangActivity extends TabActivity{
	private TabHost tabHost;
	private Button fenlei,shoucang,paihang;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_guqunguangchang);
		init();
		initTab();
	}
	private void initTab() {
		// TODO Auto-generated method stub
		fenlei=(Button)findViewById(R.id.fenlei);
		shoucang=(Button)findViewById(R.id.shoucang);
		paihang=(Button)findViewById(R.id.paihang);
		
		fenlei.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
		       // fenlei.setTextColor(0xffde4557); // 设置字体颜色红
				tabHost.setCurrentTabByTag("fenlei");

			}
		});
		shoucang.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
		       // fenlei.setTextColor(0xffde4557); // 设置字体颜色红
				tabHost.setCurrentTabByTag("shoucang");

			}
		});
		paihang.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
		       // fenlei.setTextColor(0xffde4557); // 设置字体颜色红
				tabHost.setCurrentTabByTag("paihang");

			}
		});
		
	}
	private void init() {
		
		tabHost=getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("fenlei").setIndicator("fenlei")
				.setContent(new Intent(this, FenleiActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("shoucang").setIndicator("shoucang")
				.setContent(new Intent(this, ShoucangActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("paihang").setIndicator("paihang")
				.setContent(new Intent(this, PaihangActivity.class)));
		
	}

}
