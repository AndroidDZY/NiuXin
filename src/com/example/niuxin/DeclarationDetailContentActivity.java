package com.example.niuxin;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DeclarationDetailContentActivity extends Activity{
	
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private TextView conactTextView, operateTextView, priceTextView, shoushuTextView,
	                 cangweiTextView,areaTextView,beizhuTextView;
	private ImageView peituImageView ,touxiangImageView;
	private Button back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_detail_content);
		conactTextView=(TextView)findViewById(R.id.detail_content_contacttype);//合约类型
		operateTextView=(TextView)findViewById(R.id.detail_content_operatetype);//操作类型
		priceTextView=(TextView)findViewById(R.id.detail_content_price);//价格
		shoushuTextView=(TextView)findViewById(R.id.detail_content_shoushu);//手数
		cangweiTextView=(TextView)findViewById(R.id.detail_content_cangwei);//仓位
		areaTextView=(TextView)findViewById(R.id.detail_content_area);//止盈止损范围
		beizhuTextView=(TextView)findViewById(R.id.detail_content_beizhu);//备注
		peituImageView=(ImageView)findViewById(R.id.detail_content_image);//配图
		touxiangImageView=(ImageView)findViewById(R.id.detail_content_touxiang);//头像
		back=(Button)findViewById(R.id.detail_content_back);
	    /*从数据库中获取数据绑定到相应的控件中
		conactTextView.setText(text);
		operateTextView.setText(text);
		priceTextView.setText(text);
		shoushuTextView.setText(text);
		cangweiTextView.setText(text);
		areaTextView.setText(text);
		beizhuTextView.setText(text);
		peituImageView.setBackground(background);
		touxiangImageView.setBackground(background);*/
		//返回键监听
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	@Override
	protected void onResume() {
		super.onResume();
		suolue = new SuoluetuActivity(this, handler);
	}
	

}
