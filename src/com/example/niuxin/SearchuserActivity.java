package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SearchuserActivity  extends Activity implements OnClickListener{
	
	Button buttonCancel,buttonDone;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchuser);
		buttonCancel=(Button)findViewById(R.id.searchuser_cancel);
		buttonDone=(Button)findViewById(R.id.searchuser_done);
		
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch(view.getId()) {
		case R.id.searchuser_cancel:
			back();
			break;
		case R.id.searchuser_done:
			done();
			break;
		}
	}



	private void done() {
		// TODO Auto-generated method stub
		finish();
	}

	private void back() {
		// TODO Auto-generated method stub
		finish();
	}
}
