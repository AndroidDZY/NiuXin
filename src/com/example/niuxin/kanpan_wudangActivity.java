package com.example.niuxin;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.app.Activity;
import android.content.Intent;


public class kanpan_wudangActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.kanpan_wudang);
		// 获取TabHost对象  
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        
        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
        tabHost.setup();
        // 设置标签和相应的内容
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("五档")  
                .setContent(R.id.wudang));  
  
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("明细")  
                .setContent(R.id.mingxi));  
  
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("成交")  
                .setContent(R.id.chengjiao));
        
        // 获取Button对象  
        Button btn_back=(Button)findViewById(R.id.btn_back);
        Button btn_K=(Button)findViewById(R.id.btn_K);
        // 返回按钮返回上一个窗口
        btn_back.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				finish();

			}
		});
        // K线按钮跳转到K线显示图
        btn_K.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				Intent intent = new Intent(kanpan_wudangActivity.this,K_lineActivity.class);
				startActivity(intent);
				//finish();

			}
		});
	}

}
