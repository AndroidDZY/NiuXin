package com.example.niuxin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class GunqunguangchangActivity extends TabActivity{
	private TabHost tabHost;
	private Button fenlei,shoucang,paihang,createQun;
	PopupMenu popupMenu;
	Menu menu;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_guqunguangchang);
		init();
		initTab();
		//定义+按钮事件
		createQun=(Button)findViewById(R.id.createqun);
		createQun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				add();
			}
		});
	}
	private void initTab() {
		// TODO Auto-generated method stub
		fenlei=(Button)findViewById(R.id.fenlei);
		shoucang=(Button)findViewById(R.id.shoucang);
		paihang=(Button)findViewById(R.id.paihang);
		
		fenlei.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				fenlei.setTextColor(0xffde4557); // 设置字体颜色红
				shoucang.setTextColor(0xff000000); // 设置字体颜色黑
				paihang.setTextColor(0xff000000); // 设置字体颜色黑
				tabHost.setCurrentTabByTag("fenlei");

			}
		});
		shoucang.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				fenlei.setTextColor(0xff000000); 
				shoucang.setTextColor(0xffde4557); 
				paihang.setTextColor(0xff000000); 
				tabHost.setCurrentTabByTag("shoucang");

			}
		});
		paihang.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				fenlei.setTextColor(0xff000000); 
				shoucang.setTextColor(0xff000000); 
				paihang.setTextColor(0xffde4557); 
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
	//展开+按钮
	@SuppressLint("NewApi")
	private void add() {
		//TODO Auto-generated method stub
		// 创建PopupMenu对象，按钮createQun为触发该弹出菜单的组件
		popupMenu = new PopupMenu(this, createQun);
		// 通过XML文件将R.menu.popupmenu_cq资源填充到popup菜单中
		getMenuInflater().inflate(R.menu.popupmenu_cq, popupMenu.getMenu());
		// 监听事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.create:
					// 跳转到创建群组界面
					Intent intent = new Intent(GunqunguangchangActivity.this,CreatequnActivity.class);
					startActivity(intent);
					break;
				case R.id.add:
					// 暂无
					break;
				default:
					break;
				}
				return false;
			}
		});
		popupMenu.show();
	}

}
