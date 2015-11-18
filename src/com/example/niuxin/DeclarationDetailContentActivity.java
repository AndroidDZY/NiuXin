package com.example.niuxin;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.HttpPostUtil;

import android.app.Activity;
import android.content.Intent;
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
	String contract,operation,price,handnum,position,minnum,maxnum,remark;
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
	   /* //从数据库中获取数据绑定到相应的控件中 真数据的时候调用
		conactTextView.setText(contract);
		operateTextView.setText(operation);
		priceTextView.setText(price);
		shoushuTextView.setText(handnum);
		cangweiTextView.setText(position);
		//areaTextView.setText(minnum);
		beizhuTextView.setText(remark);
		conactTextView.setText(contract);*/
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
	//根据ID查看报单详细内容 ，真数据的时候调用下面的线程
	/*class GroupThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			//获取发送报单的id
			Intent intent=getIntent();
			Long id=Long.valueOf(intent.getStringExtra("id"));
			try {
				jsonObject.put("id", id);			
			} catch (JSONException e) {
				e.printStackTrace();
			}			
			//设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/form/form_selectById.do");
			//向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);
			// 从服务器获取数据
			String res = postUtil.run();	
			if(res==null){
				return;
			}
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;			
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
			//typelist.clear();
				try {
					JSONObject myjObject = jsonArray.getJSONObject(0);// 获取每一个JsonObject对象
					// 获取每一个对象中的值
						HashMap<String, Object> map = new HashMap<String, Object>();
						 contract = myjObject.getString("contract");//合约类型
						 operation = myjObject.getString("operation");//操作类型
						 price = myjObject.getString("price");//价格
						 handnum = myjObject.getString("handnum");//手数
						 position = myjObject.getString("position");//仓位
						 minnum = myjObject.getString("minnum");//范围小
						 maxnum = myjObject.getString("maxnum");//范围大
						 remark = myjObject.getString("remark");//备注
						//配图 。。。
						
					}
				 catch (JSONException e) {
					e.printStackTrace();
				}
			/////////////////////////////解析数据完成
			Runnable r = new Runnable() {
				@Override
				public void run() {
				}

			};
			handler.post(r);
		}
	}*/

}
