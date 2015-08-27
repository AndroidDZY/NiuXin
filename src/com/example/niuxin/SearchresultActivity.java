package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class SearchresultActivity extends Activity {
	private Button mButton;
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
		
		//获取控件
		mButton = (Button)findViewById(R.id.sr_searchcancel);
		
		//点击事件
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	

	

}
