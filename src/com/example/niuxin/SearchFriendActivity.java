package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SearchFriendActivity extends Activity{
	//1
    private SuoluetuActivity suolue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchfriend);
		//2
		suolue = new SuoluetuActivity(this);
	}

}