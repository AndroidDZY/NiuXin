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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ContactsDetailedActivity extends Activity{
	private Button mButton;
	ListView listView_contacts_detailed;
	SimpleAdapter contacts_detailedAdapter = null;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.contacts_detailed);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		
		Intent intent = getIntent();	
		 
		String searchtext  = intent.getStringExtra("list");// 聊天类型
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(searchtext);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
				Map<String, Object> map = new HashMap<String, Object>();
				// 获取每一个对象中的值
				int id = myjObject.getInt("id");
				String title = myjObject.getString("title_contacts");
				Integer img = myjObject.getInt("image_contacts");
				Integer chattype = myjObject.getInt("chattype");

				map.put("id", id);
				map.put("title_contacts_detailed", title);
				map.put("image_contacts_detailed", img);
				map.put("chattype", chattype);
				
				list.add(map);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		//获取控件及点击事件
		mButton = (Button)findViewById(R.id.btn_cancle_cdl);
		mButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {				
				finish();
			}
		});

		listView_contacts_detailed=(ListView)findViewById(R.id.contacts_detailed_list);
		 contacts_detailedAdapter= new SimpleAdapter(this, list,R.layout.contacts_detailed_list, 
				new String[]{"image_contacts_detailed","title_contacts_detailed"},
				new int[]{R.id.image_contacts_detailed,R.id.title_contacts_detailed});
		listView_contacts_detailed.setAdapter(contacts_detailedAdapter);
		
		listView_contacts_detailed.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent();
				intent.putExtra("group_friend_type", list.get(position).get("chattype").toString());// 类型																							// 类型2代表个人聊天
				intent.putExtra("group_friend_id", list.get(position).get("id").toString());// 群id																										// 或者将要接收信息的人的id
				intent.putExtra("group_friend_name", list.get(position).get("title_contacts_detailed").toString());// 群名																											// 或者将要接受消息人的名字
				intent.setClass(ContactsDetailedActivity.this, ChatActivity.class);
				startActivity(intent);
			}
		});
		

	}
	
	
	

	
	
	
	
	// 群组及联系人listview的数据
	private List<Map<String, Object>> getData_contacts_detailed() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_contacts_detailed", R.drawable.head001);
		map.put("title_contacts_detailed", "汪总");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_contacts_detailed", R.drawable.head002);
		map.put("title_contacts_detailed", "豆粕商品讨论组");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_contacts_detailed", R.drawable.head003);
		map.put("title_contacts_detailed", "海螺水泥群组");
		list.add(map);
		
		return list;
	}

}

