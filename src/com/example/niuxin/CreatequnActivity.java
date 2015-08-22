package com.example.niuxin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.niuxin.zixuan_addActivity.TestThread;
import com.niuxin.bean.Qun;
import com.niuxin.util.HttpPostUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class CreatequnActivity extends Activity {
	Button cancle, finish, tianjiamore;
	EditText qunzuname, gegutag, tianjiamiaoshu;
	Spinner qunzuleixing, ruquncondition, shoufeimodel;
	// 1
	private SuoluetuActivity suolue;
	private Handler handler = new Handler();
	public static Activity act = null;

	String name = null;// 群名称
	String mark = null;// 个股标签 可能多个
	String description = null;
	String type = null;
	String enter_grade = null;
	String isfree = null;
	int totalNumber = 200;// 群能拥有的最多人数
	int currentNumber = 1;// 群目前拥有的人数
	Date createTime = new Date();// 创建时间

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.createqun);

		// 2
		suolue = new SuoluetuActivity(this);
		// 获取各种控件
		cancle = (Button) findViewById(R.id.btn_cq_cancle);
		finish = (Button) findViewById(R.id.btn_cq_finish);
		tianjiamore = (Button) findViewById(R.id.cq_tianjiamore);
		qunzuname = (EditText) findViewById(R.id.cq_name);
		gegutag = (EditText) findViewById(R.id.cq_gegutag);
		tianjiamiaoshu = (EditText) findViewById(R.id.cq_qunmiaoshu);
		qunzuleixing = (Spinner) findViewById(R.id.cq_qunzuleixing);
		ruquncondition = (Spinner) findViewById(R.id.cq_condition);
		shoufeimodel = (Spinner) findViewById(R.id.cq_shoufeimoshi);

		// 定义按钮事件
		// 取消按钮
		cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// 完成按钮，跳转到邀请成员界面
		finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// TaskThread thread = new TaskThread();
				// thread.start();

				// 从控件中读取到相关的字符串
				name = qunzuname.getText().toString();// 群名称
				mark = gegutag.getText().toString();// 个股标签 可能多个
				description = tianjiamiaoshu.getText().toString();
				type = qunzuleixing.getSelectedItem().toString();
				enter_grade = ruquncondition.getSelectedItem().toString();
				isfree = shoufeimodel.getSelectedItem().toString();
				if (isfree.equals("收费")) {
					Toast.makeText(getApplicationContext(), "目前无法建立收费群!!!", 0).show();
					return;
				} else {
					TaskThread thread = new TaskThread();
					thread.start();
				}

			}
		});
	}

	class TaskThread extends Thread {
		private Dialog mDialog = null;

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil(handler);
			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				if (name != null)
					jsonObject.put("name", name);
				if (mark != null)
					jsonObject.put("mark", mark);
				if (description != null)
					jsonObject.put("description", description);
				if (type != null)
					jsonObject.put("type", type);
				if (enter_grade != null)
					jsonObject.put("enter_grade", enter_grade);
				if (isfree != null)
					jsonObject.put("isfree", isfree);
				jsonObject.put("totalNumber", totalNumber);
				jsonObject.put("currentNumber", currentNumber);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			postUtil.setRequest(jsonObject);

			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/qun/qun_insert.do");
			// 不向服务器发送数据

			// 从服务器获取数据
			String res = postUtil.run();

			// 对从服务器获取数据进行解析
			JSONObject myjObject = null;
			try {
				myjObject = new JSONObject(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			Boolean isSuccess = null;
			try {
				isSuccess = myjObject.getBoolean("success");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			///////////////////////////// 解析数据完成
			if (isSuccess == true) {
				// 如果成功了
				Intent intent = new Intent(CreatequnActivity.this, yaoqingchengyuanActivity.class);
				startActivity(intent);
				finish();
			} else {
				Runnable r = new Runnable() {
					@Override
					public void run() {
						Toast.makeText(getApplicationContext(), "创建群组失败!!!", 0).show();
						return;
					}
				};
				handler.post(r);
			}
		}
	}

}
