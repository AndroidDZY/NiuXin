package com.example.niuxin;


import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
	private TabHost tabHost;
    private Button main_niuxin,main_guqunguangchang,
                   main_gushirili,main_more,zhankai,edit; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		initTab();
		
	}

	
	private void init() {
		// TODO Auto-generated method stub
		main_niuxin=(Button)findViewById(R.id.main_tab_niuxin);
		main_guqunguangchang=(Button)findViewById(R.id.main_tab_guqunguangchang);
		main_gushirili=(Button)findViewById(R.id.main_tab_gushirili);
		main_more=(Button)findViewById(R.id.main_tab_more);
		zhankai=(Button)findViewById(R.id.btn_open);
		edit=(Button)findViewById(R.id.btn_edit);
		//���ü����¼�
		main_niuxin.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("niuxin");

			}
		});
		main_guqunguangchang.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("guqunguangchang");

			}
		});
		main_gushirili.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("gushirili");

			}
		});
		main_more.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("more");

			}
		});
		
		/* 点击展开按钮跳转到看盘详情页五档 */
		zhankai.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				startActivity(new Intent (MainActivity.this, kanpan_wudangActivity.class) );
			}
		});
		
		/* 点击编辑自选按钮跳转到编辑自选界面 */
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				startActivity(new Intent (MainActivity.this, edit_zixuanActivity.class) );
			}
		});
	}
	
	private void initTab() {
		// TODO Auto-generated method stub
		tabHost=getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("niuxin").setIndicator("niuxin")
				.setContent(new Intent(this, NiuxinActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("guqunguangchang").setIndicator("guqunguangchang")
				.setContent(new Intent(this, GunqunguangchangActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("gushirili").setIndicator("gushirili")
				.setContent(new Intent(this, GushiriliActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more")
				.setContent(new Intent(this, MoreActivity.class)));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
