package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/*
 * 填写报单内容activity
 */
public class DeclarationDetailActivity extends Activity{
	
	LinearLayout linearLayoutModelChoice,linearLayoutContactChoice,linearLayoutActiontype,detailsendchoice;
	private Button backButton ,sendButton,saveButton;
	EditText editTextPrice, editTextShoushu,editTextCangwei,editTextArea1,editTextArea2,editTextBeizhu;
	TextView purposeChoiced,contractType,operateType,modelChioced;
	ImageView imageView;
	List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch_detail);
		suolue = new SuoluetuActivity(this, handler);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		//数据初始化模板、合约类型、操作类型
		modelChioced=(TextView)findViewById(R.id.detail_model_choice);
		contractType=(TextView)findViewById(R.id.detail_contact_show);
		operateType=(TextView)findViewById(R.id.detail_control_show);
		
		linearLayoutModelChoice=(LinearLayout)findViewById(R.id.detail_model_control);
		linearLayoutContactChoice=(LinearLayout)findViewById(R.id.detail_contact_type);
		linearLayoutActiontype=(LinearLayout)findViewById(R.id.detail_action_type);
		detailsendchoice=(LinearLayout)findViewById(R.id.detail_send_desti);
		
		backButton=(Button)findViewById(R.id.detail_button_back);//返回按钮
		sendButton=(Button)findViewById(R.id.detail_button_send);//发送按钮
		saveButton=(Button)findViewById(R.id.detail_button_save);//保存模板按钮
		
		//价格手数仓位止盈止损范围备注
		editTextPrice=(EditText)findViewById(R.id.detail_edit_price);
		editTextShoushu=(EditText)findViewById(R.id.detail_edit_shoushu);
		editTextCangwei=(EditText)findViewById(R.id.detail_edit_cangwei);
		editTextArea1=(EditText)findViewById(R.id.detail_edit_area1);
		editTextArea2=(EditText)findViewById(R.id.detail_edit_area2);
		editTextBeizhu=(EditText)findViewById(R.id.decla_lanch_detail_edit_beizhu);
		//备注图片
		imageView=(ImageView)findViewById(R.id.detail_image_beizhu);
		purposeChoiced=(TextView)findViewById(R.id.detail_purpose_choiced);
		purposeChoiced.setText("未选");
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
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", modelChioced);//模板名称
		map.put("contract", contractType);//合约类型
		map.put("operation", operateType);//操作类型
		map.put("price", editTextPrice);//价格
		map.put("handnum", editTextShoushu);//手数
		map.put("position", editTextCangwei);//仓位
		map.put("minnum", editTextArea1);//范围小
		map.put("maxnum", editTextArea2);//范围大
		map.put("remark", editTextBeizhu);//备注
		list.add(map);
		//map.put("", imageView);//配图
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
		//发送目标选择
		detailsendchoice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(DeclarationDetailActivity.this,DeclarationSendpurposeChoiceActivity.class);
				startActivity(intent);
				purposeChoiced.setText("已选");
			}
		});
		//返回
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//发送
		sendButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//获取数据，发送
				SaveThread saveThread = new SaveThread(1);
				saveThread.start();
				Toast toast = Toast.makeText(DeclarationDetailActivity.this, "发送成功", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		//保存模板
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(DeclarationDetailActivity.this, "模板已保存", Toast.LENGTH_SHORT);
				toast.show();
				SaveThread saveThread = new SaveThread(2);
				saveThread.start();
			}
		});
	}
	class SaveThread extends Thread {
		private int type = 0;
		public SaveThread(int type){
			this.type = type;
		}
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
	
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("name", modelChioced.getText());//模板名称
				jsonObject.put("contract", contractType.getText());//合约类型
				jsonObject.put("operation", operateType.getText());//操作类型
				jsonObject.put("price", editTextPrice.getText());//价格
				jsonObject.put("handnum", editTextShoushu.getText());//手数
				jsonObject.put("position", editTextCangwei.getText());//仓位
				jsonObject.put("minnum", editTextArea1.getText());//范围小
				jsonObject.put("maxnum", editTextArea2.getText());//范围大
				jsonObject.put("remark", editTextBeizhu.getText());//备注
				jsonObject.put("sendfrom", util.getId());
				jsonObject.put("type", type);
				jArray.put(jsonObject);
				//System.out.println(list);
				System.out.println(jArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/form/form_insert.do");
			// 不向服务器发送数据
			postUtil.setRequest(jArray);
			// 从服务器获取数据
			postUtil.run();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					finish();
				}

			};
			handler.post(r);
		}
	}

}
