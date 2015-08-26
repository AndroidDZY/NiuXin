package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SearchresultActivity extends Activity{
	//1
    private SuoluetuActivity suolue;
    public Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchresult);
		//2
		suolue = new SuoluetuActivity(this,handler);
	}

}
