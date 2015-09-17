package com.example.niuxin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.TabHost;
import android.widget.PopupMenu.OnMenuItemClickListener;


@SuppressWarnings("deprecation")
public class NiuxinActivity extends TabActivity {
	private TabHost tabHost;
	private Button liaotian,tongxunlu,pengyouquan;
	private ImageButton createQun;
	private EditText mEditText;
	PopupMenu popupMenu;
	Menu menu;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("NiuxinActivity>>>>>>>>>>>>>>>onCreate>>>>>>>>>");
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.main_niuxin);
		init();
		initTab();
		//定义+按钮事件
		createQun=(ImageButton)findViewById(R.id.createqun);
		createQun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				add();
			}
		});
		//定义搜索框点击事件
		mEditText=(EditText)findViewById(R.id.search);
		mEditText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//跳转到搜索结果界面
				startActivity(new Intent(NiuxinActivity.this,SearchresultActivity.class));
			}
		});
	}

	private void initTab() {
		// TODO Auto-generated method stub
		liaotian=(Button)findViewById(R.id.liaotian);
		tongxunlu=(Button)findViewById(R.id.tongxunlu);
		pengyouquan=(Button)findViewById(R.id.pengyouquan);
		liaotian.setTextColor(0xffde4557); // 设置字体颜色红
		liaotian.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
		tongxunlu.setTextColor(0xff000000); // 设置字体颜色黑
		tongxunlu.setBackgroundColor(0xfff7f7f7);// 设置无边框
		pengyouquan.setTextColor(0xff000000); // 设置字体颜色黑
		pengyouquan.setBackgroundColor(0xfff7f7f7);// 设置无边框
		liaotian.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
					liaotian.setTextColor(0xffde4557); // 设置字体颜色红
					liaotian.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
					tongxunlu.setTextColor(0xff000000); // 设置字体颜色黑
					tongxunlu.setBackgroundColor(0xfff7f7f7);// 设置无边框
					pengyouquan.setTextColor(0xff000000); // 设置字体颜色黑
					pengyouquan.setBackgroundColor(0xfff7f7f7);// 设置无边框
				tabHost.setCurrentTabByTag("liaotian");

			}
		});
		tongxunlu.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				liaotian.setTextColor(0xff000000); 
				liaotian.setBackgroundColor(0xfff7f7f7);// 设置无边框
				tongxunlu.setTextColor(0xffde4557);
				tongxunlu.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
				pengyouquan.setTextColor(0xff000000);
				pengyouquan.setBackgroundColor(0xfff7f7f7);// 设置无边框
				tabHost.setCurrentTabByTag("tongxunlu");

			}
		});
		pengyouquan.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				liaotian.setTextColor(0xff000000); 
				liaotian.setBackgroundColor(0xfff7f7f7);// 设置无边框
				tongxunlu.setTextColor(0xff000000); 
				tongxunlu.setBackgroundColor(0xfff7f7f7);// 设置无边框
				pengyouquan.setTextColor(0xffde4557);
				pengyouquan.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
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
					Intent intent = new Intent(NiuxinActivity.this,CreatequnActivity.class);
					startActivity(intent);
					break;
				case R.id.join:
					// 跳转到查找群组界面
					startActivity(new Intent(NiuxinActivity.this,SearchuserActivity.class));
					break;
				case R.id.add:
					// 跳转到添加好友界面
					startActivity(new Intent(NiuxinActivity.this,SearchFriendActivity.class));
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
