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
		init();
		haoyouButton=(Button)findViewById(R.id.declartion_target_haoyou);
		qunzuButton=(Button)findViewById(R.id.declartion_target_qunzu);
		backButton=(Button)findViewById(R.id.declaration_sendtarget_back);
		/*backButton=(Button)findViewById(R.id.declaration_sendpur_back);
		selectallButton=(Button)findViewById(R.id.declaration_sendpur_selectall);
		saveButton=(Button)findViewById(R.id.declaration_sendpur_save);
		*/haoyouButton.setBackgroundResource(R.drawable.declaration_sendpurpos_haoyou);
		qunzuButton.setText("群组");
		
		//好友界面
		haoyouButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				haoyouButton.setBackgroundResource(R.drawable.declaration_sendpurpos_haoyou);
				haoyouButton.setText(null);
				qunzuButton.setText("群组");
				qunzuButton.setBackgroundResource(0);
				tabHost.setCurrentTabByTag("haoyou");
			}
		});
		//群组界面
		qunzuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				qunzuButton.setBackgroundResource(R.drawable.declaration_sendpurpos_qunzu);
				haoyouButton.setText("好友");
				haoyouButton.setBackgroundResource(0);
				qunzuButton.setText(null);
				tabHost.setCurrentTabByTag("qunzu");
			}
		});
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
    }
	@Override
	protected void onResume() {
		super.onResume();
		suolue = new SuoluetuActivity(this, handler);
	}

}
