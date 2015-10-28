package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ContractDetailsActivity extends Activity {
	private ToggleButton togBtnCollect, togBtnShield;
	private Button btnBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_contract_details);
		
		initView();
	}
	
	/*
	 * 控件初始化
	 */
	private void initView() {
		/*
		 * 文本控件*/
		
		/*
		 * 按钮控件*/
		togBtnCollect = (ToggleButton) findViewById(R.id.tog_btn_collect);
		togBtnShield = (ToggleButton) findViewById(R.id.tog_btn_shield);
		btnBack = (Button) findViewById(R.id.btn_contract_details_back);
		// 收藏报单者切换按钮
		togBtnCollect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "收藏了该报单者", Toast.LENGTH_SHORT);
					toast.show();
				}else{
					//未选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "取消收藏", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		// 屏蔽报单者切换按钮
		togBtnShield.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "屏蔽了该报单者", Toast.LENGTH_SHORT);
					toast.show();
				}else{
					//未选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "取消屏蔽", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
		
		// 返回按钮
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
