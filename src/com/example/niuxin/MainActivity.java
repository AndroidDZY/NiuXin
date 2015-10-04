package com.example.niuxin;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends TabActivity {
	private TabHost tabHost;
	private Button main_niuxin, main_guqunguangchang, main_gushirili, main_more;
	// 1
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();

	// 手机back按键事件处理
	private long mExitTime = 0;

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Log.d("KeyBack", "KeyBack");
		// TODO Auto-generated method stub
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
		//	if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
				} else {
					finish();
					// System.exit(0);
				}
		//	}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_main);
		init();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initTab();
		suolue = new SuoluetuActivity(this, handler);
	}

	private void init() {
		// 获取Button
		main_niuxin = (Button) findViewById(R.id.main_tab_niuxin);
		main_guqunguangchang = (Button) findViewById(R.id.main_tab_guqunguangchang);
		main_gushirili = (Button) findViewById(R.id.main_tab_gushirili);
		main_more = (Button) findViewById(R.id.main_tab_more);

		// 按钮文字颜色变化
		main_niuxin.setTextColor(0xFFFFFFFF);
		main_guqunguangchang.setTextColor(0xFFFFDAB9);
		main_gushirili.setTextColor(0xFFFFDAB9);
		main_more.setTextColor(0xFFFFDAB9);
		// 按钮文字大小变化
		main_niuxin.setTextSize(20);
		main_guqunguangchang.setTextSize(15);
		main_gushirili.setTextSize(15);
		main_more.setTextSize(15);

		main_niuxin.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("niuxin");
				// 按钮文字颜色变化
				main_niuxin.setTextColor(0xFFFFFFFF);
				main_guqunguangchang.setTextColor(0xFFFFDAB9);
				main_gushirili.setTextColor(0xFFFFDAB9);
				main_more.setTextColor(0xFFFFDAB9);
				// 按钮文字大小变化
				main_niuxin.setTextSize(20);
				main_guqunguangchang.setTextSize(15);
				main_gushirili.setTextSize(15);
				main_more.setTextSize(15);

			}
		});
		main_guqunguangchang.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("guqunguangchang");
				// 按钮文字颜色变化
				main_niuxin.setTextColor(0xFFFFDAB9);
				main_guqunguangchang.setTextColor(0xFFFFFFFF);
				main_gushirili.setTextColor(0xFFFFDAB9);
				main_more.setTextColor(0xFFFFDAB9);
				// 按钮文字大小变化
				main_niuxin.setTextSize(15);
				main_guqunguangchang.setTextSize(20);
				main_gushirili.setTextSize(15);
				main_more.setTextSize(15);
			}
		});
		main_gushirili.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("gushirili");
				// 按钮文字颜色变化
				main_niuxin.setTextColor(0xFFFFDAB9);
				main_guqunguangchang.setTextColor(0xFFFFDAB9);
				main_gushirili.setTextColor(0xFFFFFFFF);
				main_more.setTextColor(0xFFFFDAB9);
				// 按钮文字大小变化
				main_niuxin.setTextSize(15);
				main_guqunguangchang.setTextSize(15);
				main_gushirili.setTextSize(20);
				main_more.setTextSize(15);
			}
		});
		main_more.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				tabHost.setCurrentTabByTag("more");
				// 按钮文字颜色变化
				main_niuxin.setTextColor(0xFFFFDAB9);
				main_guqunguangchang.setTextColor(0xFFFFDAB9);
				main_gushirili.setTextColor(0xFFFFDAB9);
				main_more.setTextColor(0xFFFFFFFF);
				// 按钮文字大小变化
				main_niuxin.setTextSize(15);
				main_guqunguangchang.setTextSize(15);
				main_gushirili.setTextSize(15);
				main_more.setTextSize(20);
			}
		});

	}

	private void initTab() {
		// TODO Auto-generated method stub
		tabHost = getTabHost();
		tabHost.addTab(
				tabHost.newTabSpec("niuxin").setIndicator("niuxin").setContent(new Intent(this, NiuxinActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("guqunguangchang").setIndicator("guqunguangchang")
				.setContent(new Intent(this, GunqunguangchangActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("gushirili").setIndicator("gushirili")
				.setContent(new Intent(this, GushiriliActivity.class)));
		tabHost.addTab(
				tabHost.newTabSpec("more").setIndicator("more").setContent(new Intent(this, MoreActivity.class)));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
