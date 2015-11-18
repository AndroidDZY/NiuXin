package com.example.niuxin;

import com.example.niuxin.R.attr;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;
/*
 * 查看发送对象activity
 */
@SuppressWarnings("deprecation")
public class DeclarationSendtargetchoicedActivity extends TabActivity{
	private TabHost tabHost;
	Button haoyouButton, qunzuButton,backButton;
	public Handler handler = new Handler();
	private SuoluetuActivity suolue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_sendpurpose_choiced);
		init();//好友、群组界面切换
		initTab();//好友、群组字体变化	    
	}
	
	private void initTab() {
		// TODO Auto-generated method stub
		haoyouButton=(Button)findViewById(R.id.declartion_target_haoyou);//好友按钮
		qunzuButton=(Button)findViewById(R.id.declartion_target_qunzu);//群组按钮
		backButton=(Button)findViewById(R.id.declaration_sendtarget_back);//返回按钮
		
		haoyouButton.setTextColor(0xffde4557); // 设置字体颜色红
		haoyouButton.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
		qunzuButton.setTextColor(0xff000000); // 设置字体颜色黑
		qunzuButton.setBackgroundColor(0xfff7f7f7);// 设置无边框
		
		//点击好友标签字体变化
		haoyouButton.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				haoyouButton.setTextColor(0xffde4557); // 设置字体颜色红
				haoyouButton.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
				qunzuButton.setTextColor(0xff000000); // 设置字体颜色黑
				qunzuButton.setBackgroundColor(0xfff7f7f7);// 设置无边框
				tabHost.setCurrentTabByTag("haoyou");
			}
		});
		
		//点击群组标签字体变化
		qunzuButton.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				haoyouButton.setTextColor(0xff000000); 
				haoyouButton.setBackgroundColor(0xfff7f7f7);// 设置无边框
				qunzuButton.setTextColor(0xffde4557);
				qunzuButton.setBackgroundResource(R.drawable.buttonstyle02);// 设置有边框
				tabHost.setCurrentTabByTag("qunzu");
			}
		});
		
		//返回按钮点击
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	//界面切换
	private void init() {
		// TODO Auto-generated method stub
		tabHost=getTabHost();
    	tabHost.addTab(tabHost.newTabSpec("haoyou").setIndicator("haoyou")
				.setContent(new Intent(this, DeclarationUsershowActivity.class)));
    	tabHost.addTab(tabHost.newTabSpec("qunzu").setIndicator("qunzu")
				.setContent(new Intent(this, DeclarationQunzushowActivity.class)));
    	/*//真数据的时候调用下面的，获取报单的id 传给查看发送目标界面
    	Intent intent=getIntent();
		Long id=Long.valueOf(intent.getStringExtra("id"));
		Intent haoyouIntent=new Intent();
		haoyouIntent.putExtra("id", id);
		haoyouIntent.setClass(DeclarationSendtargetchoicedActivity.this ,DeclarationUsershowActivity.class);
		Intent qunzuIntent=new Intent();
		qunzuIntent.putExtra("id", id);
		qunzuIntent.setClass(DeclarationSendtargetchoicedActivity.this ,DeclarationQunzushowActivity.class);
		tabHost.addTab(tabHost.newTabSpec("haoyou").setIndicator("haoyou")
				.setContent(haoyouIntent));
    	tabHost.addTab(tabHost.newTabSpec("qunzu").setIndicator("qunzu")
				.setContent(qunzuIntent));*/
    }
	@Override
	protected void onResume() {
		super.onResume();
		suolue = new SuoluetuActivity(this, handler);
	}

}
