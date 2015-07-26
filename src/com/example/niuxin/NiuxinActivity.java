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
	private Button liaotian,tongxunlu,pengyouquan,createQun;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_niuxin);
		init();
		initTab();
		//创建群界面跳转
		createQun=(Button)findViewById(R.id.createqun);
		createQun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent (NiuxinActivity.this, CreatequnActivity.class) );
				//Intent intent=new Intent();
				//intent.setClass(NiuxinActivity.this, CreatequnActivity.class);
			}
		});
		
	}

	private void initTab() {
		// TODO Auto-generated method stub
		liaotian=(Button)findViewById(R.id.liaotian);
		tongxunlu=(Button)findViewById(R.id.tongxunlu);
		pengyouquan=(Button)findViewById(R.id.pengyouquan);
		
		liaotian.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
					liaotian.setTextColor(0xffde4557); // 设置字体颜色红
					tongxunlu.setTextColor(0xff000000); // 设置字体颜色黑
					pengyouquan.setTextColor(0xff000000); // 设置字体颜色黑
				tabHost.setCurrentTabByTag("liaotian");

			}
		});
		tongxunlu.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				liaotian.setTextColor(0xff000000); 
				tongxunlu.setTextColor(0xffde4557); 
				pengyouquan.setTextColor(0xff000000); 
				tabHost.setCurrentTabByTag("tongxunlu");

			}
		});
		pengyouquan.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				liaotian.setTextColor(0xff000000); 
				tongxunlu.setTextColor(0xff000000); 
				pengyouquan.setTextColor(0xffde4557); 
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
