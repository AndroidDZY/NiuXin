package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SearchresultActivity extends Activity {
	private LinearLayout layout1,layout2,layout3; 
	ListView listView_contacts,listView_chatlog,listView_collect;
	private Button mButton,mmButton;
	//1
    private SuoluetuActivity suolue;
    public Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchresult);
		//2
		suolue = new SuoluetuActivity(this,handler);
		
		//获取控件
		mButton = (Button)findViewById(R.id.sr_searchcancel);
		mmButton = (Button)findViewById(R.id.sr_search);
		
		//点击事件-取消搜索
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//点击事件-完成搜索
		mmButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		// 获取控件与监听
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchresultActivity.this,Contacts_detailedActivity.class);
				startActivity(intent);
			}
		});
		
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchresultActivity.this,Chatlog_detailedActivity.class);
				startActivity(intent);
			}
		});
		
		layout3 = (LinearLayout) findViewById(R.id.layout3);
		layout3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchresultActivity.this,Collect_detailedActivity.class);
				startActivity(intent);
			}
		});
		
		//获取第一个ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用contacts_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用contacts_list.xml文件中的哪些组件来填充列表项
		listView_contacts=(ListView)findViewById(R.id.contacts_list);
		SimpleAdapter contactsAdapter= new SimpleAdapter(this, getData_contacts(),R.layout.contacts_list, 
				new String[]{"image_contacts","title_contacts"},
				new int[]{R.id.image_contacts,R.id.title_contacts});
		listView_contacts.setAdapter(contactsAdapter);
		
		//获取第二个ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用chatlog_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用chatlog_list.xml文件中的哪些组件来填充列表项
		listView_chatlog=(ListView)findViewById(R.id.chatlog_list);
		SimpleAdapter chatlogAdapter= new SimpleAdapter(this, getData_chatlog(),R.layout.chatlog_list, 
				new String[]{"image_chatlog","title_chatlog","content_chatlog"},
				new int[]{R.id.image_chatlog,R.id.title_chatlog,R.id.content_chatlog});
		listView_chatlog.setAdapter(chatlogAdapter);
		
		//获取第三个ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用collect_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用collect_list.xml文件中的哪些组件来填充列表项
		listView_collect=(ListView)findViewById(R.id.collect_list);
		SimpleAdapter collectAdapter= new SimpleAdapter(this, getData_collect(),R.layout.collect_list, 
				new String[]{"image_collect","title_collect","content_collect"},
				new int[]{R.id.image_collect,R.id.title_collect,R.id.content_collect});
		listView_collect.setAdapter(collectAdapter);
	}
	
	// 群组及联系人listview的数据
	private List<Map<String, Object>> getData_contacts() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_contacts", R.drawable.head001);
		map.put("title_contacts", "汪总");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_contacts", R.drawable.head002);
		map.put("title_contacts", "中信证券讨论组");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_contacts", R.drawable.head003);
		map.put("title_contacts", "海螺水泥群组");
		list.add(map);
		
		return list;
	}
	
	// 聊天记录的数据
	private List<Map<String, Object>> getData_chatlog() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_chatlog", R.drawable.head004);
		map.put("title_chatlog", "汪总");
		map.put("content_chatlog", "今天又要涨停了");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_chatlog", R.drawable.head005);
		map.put("title_chatlog", "中信证券讨论组");
		map.put("content_chatlog", "一路飘红啊");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_chatlog", R.drawable.head006);
		map.put("title_chatlog", "海螺水泥群组");
		map.put("content_chatlog", "海螺水泥是一只自选股");
		list.add(map);
		
		return list;
	}
	
	// 收藏的数据
	private List<Map<String, Object>> getData_collect() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_collect", R.drawable.head007);
		map.put("title_collect", "军工板块暴涨近7%领涨两市");
		map.put("content_collect", "周五军工股大涨近7%，领涨两市，截至发稿，际华集团、拓尔思、长春一东、中航...");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_collect", R.drawable.head008);
		map.put("title_collect", "人社部：约2万亿元养老金可投资");
		map.put("content_collect", "周五上午10时，人力资源社会保障部副部长和财政部副部...");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_collect", R.drawable.head009);
		map.put("title_collect", "海螺水泥群组");
		map.put("content_collect", "海螺水泥是一只自选股");
		list.add(map);
		
		return list;
	}
}
