package com.example.niuxin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class DeclarationDetailActivity extends Activity{
	
	LinearLayout linearLayoutModelChoice,linearLayoutContactChoice,linearLayoutActiontype;
	private Button buttonBack ,buttonSend ;
	EditText editTextPrice, editTextShoushu,editTextCangwei,editTextArea1,editTextArea2,editTextBeizhu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch_detail);
		
		//数据初始化
		linearLayoutModelChoice=(LinearLayout)findViewById(R.id.detail_model_control);
		linearLayoutContactChoice=(LinearLayout)findViewById(R.id.detail_contact_type);
		linearLayoutActiontype=(LinearLayout)findViewById(R.id.detail_action_type);
		buttonBack=(Button)findViewById(R.id.detail_button_back);
		buttonSend=(Button)findViewById(R.id.detail_button_send);
		editTextPrice=(EditText)findViewById(R.id.detail_edit_price);
		editTextShoushu=(EditText)findViewById(R.id.detail_edit_shoushu);
		editTextCangwei=(EditText)findViewById(R.id.detail_edit_cangwei);
		editTextArea1=(EditText)findViewById(R.id.detail_edit_area1);
		editTextArea2=(EditText)findViewById(R.id.detail_edit_area2);
		editTextBeizhu=(EditText)findViewById(R.id.decla_lanch_detail_edit_beizhu);
		//设置监听事件
		//模板选择跳转
		String text="  ";
		editTextPrice.setText(text);
		editTextPrice.setSelection(text.length());
		editTextShoushu.setText(text);
		editTextShoushu.setSelection(text.length());
		editTextCangwei.setText(text);
		editTextCangwei.setSelection(text.length());
		editTextBeizhu.setText(text);
		editTextBeizhu.setSelection(text.length());
		editTextArea1.setText(text);
		editTextArea1.setSelection(text.length());
		editTextArea2.setText(text);
		editTextArea2.setSelection(text.length());
		//模板选择
		linearLayoutModelChoice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DeclarationDetailActivity.this,DeclarationModelChoiceActivity.class);
				startActivity(intent);
			}
		});
		//合约类型选择界面跳转
		linearLayoutContactChoice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DeclarationDetailActivity.this,DeclarationContactChoiceActivity.class);
				startActivity(intent);
			}
		});
		buttonBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		buttonSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//获取数据，发送
			}
		});
	}

}
