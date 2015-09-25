package com.example.niuxin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Chatlog_detailedActivity extends Activity{
	private Button mButton;
	ListView listView_chatlog_detailed;
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	SimpleAdapter chatlog_detailedAdapter = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.chatlog_detailed);
		
		//获取控件及点击事件
		mButton = (Button)findViewById(R.id.btn_cancle_ldl);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
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
				String title = myjObject.getString("title_chatlog");
				Integer img = myjObject.getInt("image_chatlog");
				Integer chattype = myjObject.getInt("chattype");
				String content_chatlog = myjObject.getString("content_chatlog");
				
				
				
				
				map.put("id", id);
				map.put("title_chatlog_detailed", title);
				map.put("image_chatlog_detailed", img);
				map.put("content_chatlog_detailed", content_chatlog);
				map.put("chattype", chattype);
				
				list.add(map);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		

		//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用chatlog_detailed_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用chatlog_detailed_list.xml文件中的哪些组件来填充列表项
		listView_chatlog_detailed=(ListView)findViewById(R.id.chatlog_detailed_list);
		 chatlog_detailedAdapter= new SimpleAdapter(this, list,R.layout.chatlog_detailed_list, 
				new String[]{"image_chatlog_detailed","title_chatlog_detailed","content_chatlog_detailed"},
				new int[]{R.id.image_chatlog_detailed,R.id.title_chatlog_detailed,R.id.content_chatlog_detailed});
		listView_chatlog_detailed.setAdapter(chatlog_detailedAdapter);
	}
	
	// 聊天记录的数据
	private List<Map<String, Object>> getData_chatlog_detailed() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_chatlog_detailed", R.drawable.head004);
		map.put("title_chatlog_detailed", "汪总");
		map.put("content_chatlog_detailed", "今天又要涨停了");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_chatlog_detailed", R.drawable.head005);
		map.put("title_chatlog_detailed", "豆粕商品讨论组");
		map.put("content_chatlog_detailed", "一路飘红啊");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_chatlog_detailed", R.drawable.head006);
		map.put("title_chatlog_detailed", "海螺水泥群组");
		map.put("content_chatlog_detailed", "海螺水泥是一只自选股");
		list.add(map);
		
		return list;
	}
	

}

