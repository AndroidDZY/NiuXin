package com.example.niuxin;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.GetSource;
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

public class DeclarationDetailContentActivity extends Activity {
	GetSource getSource = new GetSource();
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private TextView conactTextView, operateTextView, priceTextView, shoushuTextView, cangweiTextView, areaTextView,
			beizhuTextView,minText,maxText;
	private ImageView peituImageView, touxiangImageView;
	private Button back;
	String contract, operation, price, handnum, position, minnum, maxnum, remark, senduserimg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.declaration_detail_content);
		suolue = new SuoluetuActivity(this, handler);
		conactTextView = (TextView) findViewById(R.id.detail_content_contacttype);// 合约类型
		operateTextView = (TextView) findViewById(R.id.detail_content_operatetype);// 操作类型
		priceTextView = (TextView) findViewById(R.id.detail_content_price);// 价格
		shoushuTextView = (TextView) findViewById(R.id.detail_content_shoushu);// 手数
		cangweiTextView = (TextView) findViewById(R.id.detail_content_cangwei);// 仓位
		areaTextView = (TextView) findViewById(R.id.detail_content_area);// 止盈止损范围
		
		minText = (TextView) findViewById(R.id.tv_contract_details_minnum);
		maxText = (TextView) findViewById(R.id.tv_contract_details_maxnum);
		
		beizhuTextView = (TextView) findViewById(R.id.detail_content_beizhu);// 备注
		peituImageView = (ImageView) findViewById(R.id.detail_content_image);// 配图
		touxiangImageView = (ImageView) findViewById(R.id.detail_content_touxiang);// 头像
		back = (Button) findViewById(R.id.detail_content_back);

		/*
		 * //从数据库中获取数据绑定到相应的控件中 真数据的时候调用
		 */

		// 返回键监听
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
		GroupThread gt = new GroupThread();
		gt.start();
	}

	// 根据ID查看报单详细内容 ，真数据的时候调用下面的线程
	class GroupThread extends Thread {
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
				if (null != jsonArray) {
					for (int i = 0; i < jsonArray.length(); i++) {
					try {					
						JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
						Map<String, Object> map = new HashMap<String, Object>();
						// 发送表单用户的信息
						String formid = myjObject.getString("id");// 表单id
						 contract = myjObject.getString("contract");
						 operation = myjObject.getString("operation");
						 price = myjObject.getString("price");
						 handnum = myjObject.getString("handnum");
						 position = myjObject.getString("position");
						String profit = myjObject.getString("profit");
						 minnum = myjObject.getString("minnum");
						 maxnum = myjObject.getString("maxnum");
						// String remark = myjObject.getString("remark");
						// String pictureurl =
						// myjObject.getString("pictureurl");
						// String audiourl = myjObject.getString("audiourl");
						String date = myjObject.getString("date");
						String week = myjObject.getString("week");
						String time = myjObject.getString("time");
						// String name = myjObject.getString("name");
						int sendfromid = myjObject.getInt("sendfrom");
						int collection = myjObject.getInt("collection");
						senduserimg = myjObject.getString("img");
						String sendusername = myjObject.getString("sendusername");
					}catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			
 
			Runnable r = new Runnable() {
				@Override
				public void run() {					
					conactTextView.setText(contract);//合约类型
					operateTextView.setText(operation);//操作类型
					priceTextView.setText(price);//价格
					shoushuTextView.setText(handnum);//手数
					cangweiTextView.setText(position);//仓位
					minText.setText(minnum);
					maxText.setText(maxnum);
					beizhuTextView.setText(remark);//备注
					peituImageView.setImageResource(getSource.getResourceByReflect(senduserimg));//配图
					touxiangImageView.setImageResource(getSource.getResourceByReflect(senduserimg));//头像
				}
			};
			handler.post(r);
		}
	}
}
