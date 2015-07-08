package com.example.niuxin;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;


@SuppressWarnings("deprecation")
public class NiuxinActivity extends TabActivity {
	private TabHost tabHost;
	private Button liaotian,tongxunlu,pengyouquan;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_niuxin);
		init();
		initTab();
	}

	private void initTab() {
		// TODO Auto-generated method stub
		liaotian=(Button)findViewById(R.id.liaotian);
		tongxunlu=(Button)findViewById(R.id.tongxunlu);
		pengyouquan=(Button)findViewById(R.id.pengyouquan);
		//…Ë÷√º‡Ã˝
		liaotian.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("liaotian");

			}
		});
		tongxunlu.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("tongxunlu");

			}
		});
		pengyouquan.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("pengyouquan");

			}
		});
		
		
	}

	private void init() {
		// TODO Auto-generated method stub
		tabHost=getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("liaotian").setIndicator("liaotian")
				.setContent(new Intent(this, LiaotianActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("tongxunlu").setIndicator("tongxunlu")
				.setContent(new Intent(this, TongxunluActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("pengyouquan").setIndicator("pengyouquan")
				.setContent(new Intent(this, PengyouquanActivity.class)));
		
	}

}
