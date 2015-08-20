package com.example.niuxin;

import com.niuxin.bean.Qun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreatequnActivity extends Activity {
	Button cancle,finish,tianjiamore;
	EditText qunzuname,gegutag,tianjiamiaoshu;
	Spinner qunzuleixing,ruquncondition,shoufeimodel;
	//1
	private SuoluetuActivity suolue;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.createqun);
		
		//2
		suolue = new SuoluetuActivity(this);
		//获取各种控件
		cancle = (Button)findViewById(R.id.btn_cq_cancle);
		finish = (Button)findViewById(R.id.btn_cq_finish);
		tianjiamore = (Button)findViewById(R.id.cq_tianjiamore);
		qunzuname=(EditText)findViewById(R.id.cq_name);
		gegutag=(EditText)findViewById(R.id.cq_gegutag);
		tianjiamiaoshu=(EditText)findViewById(R.id.cq_qunmiaoshu);
		qunzuleixing=(Spinner)findViewById(R.id.cq_qunzuleixing);
		ruquncondition=(Spinner)findViewById(R.id.cq_condition);
		shoufeimodel=(Spinner)findViewById(R.id.cq_shoufeimoshi);
		
		
		//定义按钮事件
		//取消按钮
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//完成按钮，跳转到邀请成员界面
		finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//从控件中读取到相关的字符串
				String qunname = qunzuname.getText().toString();
				String quntag = gegutag.getText().toString();
				String qunmiaoshu = tianjiamiaoshu.getText().toString();
				String qunleixing = qunzuleixing.getSelectedItem().toString();
				String quncondition = ruquncondition.getSelectedItem().toString();
				String qunmodel = shoufeimodel.getSelectedItem().toString();
				if (qunmodel.equals("收费")) {
					Toast.makeText(getApplicationContext(), "目前无法建立收费群!!!", 0).show();
					
				} else {
					// TODO Auto-generated method stub
					Qun qun= new Qun();
					qun.setQunname(qunname);
					qun.setQuntag(quntag);
					qun.setQunleixing(qunleixing);
					qun.setQunmiaoshu(qunmiaoshu);
					qun.setQunmodel(qunmodel);
					qun.setQuncondition(quncondition);
					Intent intent = new Intent(CreatequnActivity.this,yaoqingchengyuanActivity.class);
					startActivity(intent);
				}
				
			}
		});
	}
	
	
}
