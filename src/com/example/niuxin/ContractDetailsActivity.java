package com.example.niuxin;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.BinaryHttpResponseHandler;
import com.niuxin.util.Constants;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.HttpPostUtilPic;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ContractDetailsActivity extends Activity {
	private ToggleButton togBtnCollect, togBtnShield;
	GetSource getSource = new GetSource();
	private Button btnBack;
	private TextView tvContract ,tvOperation, tvPrice, tvHandnum, tvPosition, tvMinnum, tvMaxnum, tvRemark, tvSenderName;
	private ImageView ivPictureUrl, ivSenderHead;
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	String contract, operation, price, handnum, position, minnum, maxnum, remark, senduserimg;
	Integer isFollow,isShield;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_contract_details);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		suolue = new SuoluetuActivity(this, handler);
		
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		GroupThread gt = new GroupThread();
		gt.start();
		
		GetPicThread pic = new GetPicThread();
		pic.start();

	}
	
	/*
	 * 控件初始化
	 */
	private void initView() {
		/*
		 * 文本控件*/
		tvContract = (TextView)findViewById(R.id.tv_contract_details_contract);	//合约类型
		tvOperation = (TextView)findViewById(R.id.tv_contract_details_operation);	//操作类型
		tvPrice = (TextView)findViewById(R.id.tv_contract_details_price);	//价格
		tvHandnum = (TextView)findViewById(R.id.tv_contract_details_handnum);	//手数
		tvPosition = (TextView)findViewById(R.id.tv_contract_details_position);	//仓位
		tvMinnum = (TextView)findViewById(R.id.tv_contract_details_minnum);	//止盈止损最小值
		tvMaxnum = (TextView)findViewById(R.id.tv_contract_details_maxnum);	//止盈止损最大值
		tvRemark = (TextView)findViewById(R.id.tv_contract_details_remark);	//备注
		tvSenderName = (TextView)findViewById(R.id.tv_contract_details_sender_name);	//盈止损最大值
		
		/*
		 * 图像控件*/
		ivPictureUrl = (ImageView)findViewById(R.id.iv_contract_details_pictureurl);	//配图
		ivSenderHead = (ImageView)findViewById(R.id.iv_contract_details_sender_head);	//报单者头像
		
		/*
		 * 按钮控件*/
		togBtnCollect = (ToggleButton) findViewById(R.id.tog_btn_collect);	//“收藏该报单者”按钮
		togBtnShield = (ToggleButton) findViewById(R.id.tog_btn_shield);	//“屏蔽该报单者”按钮
		btnBack = (Button) findViewById(R.id.btn_contract_details_back);	//“返回”按钮
		// 收藏报单者切换按钮
		togBtnCollect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "收藏了该报单者", Toast.LENGTH_SHORT);
					toast.show();
					FollowThread t = new FollowThread(1);
					t.start();
				}else{
					//未选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "取消收藏", Toast.LENGTH_SHORT);
					toast.show();
					FollowThread t = new FollowThread(2);
					t.start();
				}
			}
		});
		
		// 屏蔽报单者切换按钮
		togBtnShield.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "屏蔽了该报单者", Toast.LENGTH_SHORT);
					toast.show();
					
					ShieldThread s = new ShieldThread(1);
					s.start();
				}else{
					//未选中
					Toast toast = Toast.makeText(ContractDetailsActivity.this, "取消屏蔽", Toast.LENGTH_SHORT);
					toast.show();
					
					ShieldThread s = new ShieldThread(2);
					s.start();
				}
			}
		});
		
		// 返回按钮
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	
	
	// 根据ID查看报单详细内容 ，真数据的时候调用下面的线程
			class FollowThread extends Thread {
				int type =0;
				public  FollowThread(int type){
					this.type = type;
				}
				@Override
				public void run() {
					
					// 新建工具类，向服务器发送Http请求
					HttpPostUtil postUtil = new HttpPostUtil();

					// 向服务器发送数据，如果没有，可以不发送
					JSONObject jsonObject = new JSONObject();
					//获取发送报单的id
					Intent intent=getIntent();
					Long id=Long.valueOf(intent.getStringExtra("senduserid"));
					try {
						jsonObject.put("senduserid", id);	
						jsonObject.put("userid", util.getId());	
						
					} catch (JSONException e) {
						e.printStackTrace();
					}			
					//设置发送的url 和服务器端的struts.xml文件对应
					if(type==1){
						postUtil.setUrl("/user/action_followUser.do");
					}else
						postUtil.setUrl("/user/action_unfollowUser.do");
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
						
					
		 
					Runnable r = new Runnable() {
						@Override
						public void run() {													
						}
					};
					handler.post(r);
				}
			}
		
	
	//屏蔽好友
			class ShieldThread extends Thread {
				int type =0;
				public  ShieldThread(int type){
					this.type = type;
				}
				@Override
				public void run() {
					
					// 新建工具类，向服务器发送Http请求
					HttpPostUtil postUtil = new HttpPostUtil();

					// 向服务器发送数据，如果没有，可以不发送
					JSONObject jsonObject = new JSONObject();
					//获取发送报单的id
					Intent intent=getIntent();
					Long id=Long.valueOf(intent.getStringExtra("senduserid"));
					try {
						jsonObject.put("senduserid", id);	
						jsonObject.put("userid", util.getId());	
						
					} catch (JSONException e) {
						e.printStackTrace();
					}			
					//设置发送的url 和服务器端的struts.xml文件对应
					if(type==1){
						postUtil.setUrl("/user/action_shieldUser.do");
					}else
						postUtil.setUrl("/user/action_unshieldUser.do");
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
						
					
		 
					Runnable r = new Runnable() {
						@Override
						public void run() {													
						}
					};
					handler.post(r);
				}
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
					jsonObject.put("formid", id);	
					jsonObject.put("userid", util.getId());	
					
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
							  remark = myjObject.getString("remark");
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

							isFollow = myjObject.getInt("isFollow");
							isShield = myjObject.getInt("isShield");
							
						}catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				
	 
				Runnable r = new Runnable() {
					@Override
					public void run() {						
						tvContract.setText(contract);//合约类型
						tvOperation.setText(operation);//操作类型
						tvPrice.setText(price);//价格
						tvHandnum.setText(handnum);//手数
						tvPosition.setText(position);//仓位
						tvMinnum.setText(minnum);
						tvMaxnum.setText(maxnum);
						tvRemark.setText(remark);//备注
					//	ivSenderHead.setImageResource(getSource.getResourceByReflect(senduserimg));//配图
						ivPictureUrl.setImageResource(getSource.getResourceByReflect(senduserimg));//头像
					
						if(isFollow==1){
							//选中
							Toast toast = Toast.makeText(ContractDetailsActivity.this, "", Toast.LENGTH_SHORT);
							toast.show();							
						}					
					}
				};
				handler.post(r);
			}
			

		}
		
		
		
		
		class GetPicThread extends Thread {
			@Override
			public void run() {
				// 新建工具类，向服务器发送Http请求
				HttpPostUtilPic postUtil = new HttpPostUtilPic();

				// 向服务器发送数据，如果没有，可以不发送
				JSONObject jsonObject = new JSONObject();
				//获取发送报单的id
				Intent intent=getIntent();
				Long id=Long.valueOf(intent.getStringExtra("id"));
				try {
					jsonObject.put("formid", id);	
					jsonObject.put("type", 1);	
				} catch (JSONException e) {
					e.printStackTrace();
				}			
				//设置发送的url 和服务器端的struts.xml文件对应
				postUtil.setUrl("/upload/upload_download.do");
				//向服务器发送数据
				JSONArray js = new JSONArray();
				js.put(jsonObject);
				postUtil.setRequest(js);
				// 从服务器获取数据
				final byte[] pic = postUtil.run();	
				
				Runnable r = new Runnable() {
					@Override
					public void run() {										
					//	ivPictureUrl.setImageResource(getSource.getResourceByReflect(senduserimg));//头像
						Bitmap bp =BitmapFactory.decodeByteArray(pic, 0, pic.length);
						ivPictureUrl.setImageBitmap(bp);
					}
				};
				handler.post(r);
			}
		}
	
}
