package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ContractTypeSelectActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_contract_type_select);
		
		//initView();
	}
}
