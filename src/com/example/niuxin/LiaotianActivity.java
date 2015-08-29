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
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LiaotianActivity extends Activity {
	ListView listView;
	private Handler handler = new Handler();
	public static Activity act = null;
	SimpleAdapter liaotianAdapter = null;
	List<Map<String, Object>> listLiaoTian= new ArrayList<Map<String,Object>>();
	SharePreferenceUtil util;
	@Override  
	protected void onResume() {
		 super.onResume();  
		 GroupThread thread = new GroupThread();
		 thread.start();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		act = this;
		 util = new SharePreferenceUtil(LiaotianActivity.this, Constants.SAVE_USER);
		//719修改
		setContentView(R.layout.liaotian);
		listView=(ListView)findViewById(R.id.qunlist);
		
		liaotianAdapter= new SimpleAdapter(LiaotianActivity.this,listLiaoTian,
					R.layout.qunlistview, new String[]{"img","name","lastmes",
							"time","type","renshu","grade"},new int[]{R.id.img,R.id.qunname,
				             R.id.lastmes,R.id.time,R.id.quntag,R.id.renshu,R.id.grade});
		listView.setAdapter(liaotianAdapter);
		//跳转到聊天界面
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {

				Intent intent=new Intent();
			//	intent.putExtra("groupid", listLiaoTian.get(position).get("id").toString());
			//	intent.putExtra("name", listLiaoTian.get(position).get("name").toString());
			//	intent.setClass(LiaotianActivity.this, ChatActivity.class);
				
				intent.putExtra("group_friend_type", listLiaoTian.get(position).get("chattype").toString());//类型 1代表群聊天  类型2代表个人聊天
				intent.putExtra("group_friend_id", listLiaoTian.get(position).get("id").toString());//群id 或者将要接收信息的人的id
				intent.putExtra("group_friend_name", listLiaoTian.get(position).get("name").toString());//群名  或者将要接受消息人的名字
				intent.setClass(LiaotianActivity.this, ChatActivity.class);
				
				startActivity(intent);		
			}
		});

	}

	class GroupThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				
				Integer id = util.getId();			
				jsonObject.put("id", id);			
			} catch (JSONException e) {
				e.printStackTrace();
			}			
			/*
			boolean isNetwork= postUtil.checkNetState(act);
			if(!isNetwork){
				mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
				mDialog.show();
				return;
			}*/
			
			//设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/group/group_listChatRecord.do");
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
			listLiaoTian.clear();
			for (int i = 0; i < jsonArray.length(); i++) {				
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					String chattype = myjObject.getString("chattype");
					// 获取每一个对象中的值
					if(Integer.valueOf(chattype)==1){
						//String chattype = myjObject.getString("chattype");
						int id = myjObject.getInt("id");
						String name = myjObject.getString("name");
						String type = myjObject.getString("type");
						String lastmes = myjObject.getString("lastmes");
						String time = myjObject.getString("time");
						Integer img = myjObject.getInt("img");
						
						String grade = myjObject.getString("grade");
						//currentNumber
						String currentNumber = myjObject.getString("currentNumber");
						String totalNumber = myjObject.getString("totalNumber");
						String renshu = currentNumber+"/"+ totalNumber;
						map.put("img", img);//R.drawable.head010
						map.put("id", id);
						map.put("name", name);
						map.put("chattype", chattype);
						map.put("lastmes", lastmes);
						map.put("time",time);
						map.put("type", type);
						map.put("renshu", renshu);
						map.put("grade", grade);   //入群等级，
						listLiaoTian.add(map);
					}else{
						
					}
					
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}/////////////////////////////解析数据完成
			
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					 liaotianAdapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
		}
	}
	
	// ListView假数据
	private List<Map<String, Object>> getData_u() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image", R.drawable.head001);
		map.put("title", "中信证券讨论组");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.head002);
		map.put("title", "汪总");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.head003);
		map.put("title", "海螺水泥群组");
		list.add(map);
		
		return list;
	}
}
