package com.example.niuxin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
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
		
		//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用chatlog_detailed_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用chatlog_detailed_list.xml文件中的哪些组件来填充列表项
		listView_chatlog_detailed=(ListView)findViewById(R.id.chatlog_detailed_list);
		SimpleAdapter chatlog_detailedAdapter= new SimpleAdapter(this, getData_chatlog_detailed(),R.layout.chatlog_detailed_list, 
				new String[]{"image_chatlog_detailed","title_chatlog_detailed","content_chatlog_detailed"},
				new int[]{R.id.image_chatlog_detailed,R.id.title_chatlog_detailed,R.id.content_chatlog_detailed});
		listView_chatlog_detailed.setAdapter(chatlog_detailedAdapter);
	}
	
	// 聊天记录的数据
	private List<Map<String, Object>> getData_chatlog_detailed() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_chatlog_detailed", R.drawable.f1);
		map.put("title_chatlog_detailed", "汪总");
		map.put("content_chatlog_detailed", "今天又要涨停了");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_chatlog_detailed", R.drawable.f2);
		map.put("title_chatlog_detailed", "中信证券讨论组");
		map.put("content_chatlog_detailed", "一路飘红啊");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_chatlog_detailed", R.drawable.f3);
		map.put("title_chatlog_detailed", "海螺水泥群组");
		map.put("content_chatlog_detailed", "海螺水泥是一只自选股");
		list.add(map);
		
		return list;
	}
	

}
