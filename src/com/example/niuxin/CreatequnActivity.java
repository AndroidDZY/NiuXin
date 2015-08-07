package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreatequnActivity extends Activity  {
	
	Button buttonCancel,buttonDone;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.createqun);
		buttonCancel=(Button)findViewById(R.id.createcancel);
		buttonDone=(Button)findViewById(R.id.createdone);
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	
}
