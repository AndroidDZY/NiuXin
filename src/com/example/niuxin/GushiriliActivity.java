package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class GushiriliActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.main_gushirili);
	}

}
