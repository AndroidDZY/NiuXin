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

public class Collect_detailedActivity extends Activity{
	private Button mButton;
	ListView listView_collect_detailed;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.collect_detailed);
		
		//获取控件及点击事件
		mButton = (Button)findViewById(R.id.btn_cancle_odl);
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
		//第三个参数：表明使用collect_detailed_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用collect_detailed_list.xml文件中的哪些组件来填充列表项
		listView_collect_detailed=(ListView)findViewById(R.id.collect_detailed_list);
		SimpleAdapter collect_detailedAdapter= new SimpleAdapter(this, getData_collect_detailed(),R.layout.collect_detailed_list, 
				new String[]{"image_collect_detailed","title_collect_detailed","content_collect_detailed"},
				new int[]{R.id.image_collect_detailed,R.id.title_collect_detailed,R.id.content_collect_detailed});
		listView_collect_detailed.setAdapter(collect_detailedAdapter);
	}
	
	// 收藏的数据
	private List<Map<String, Object>> getData_collect_detailed() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_collect_detailed", R.drawable.f1);
		map.put("title_collect_detailed", "军工板块暴涨近7%领涨两市");
		map.put("content_collect_detailed", "周五军工股大涨近7%，领涨两市，截至发稿，际华集团、拓尔思、长春一东、中航...");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_collect_detailed", R.drawable.f2);
		map.put("title_collect_detailed", "人社部：约2万亿元养老金可投资");
		map.put("content_collect_detailed", "周五上午10时，人力资源社会保障部副部长和财政部副部...");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_collect_detailed", R.drawable.f3);
		map.put("title_collect_detailed", "海螺水泥群组");
		map.put("content_collect_detailed", "海螺水泥是一只自选股");
		list.add(map);
		
		return list;
	}
	
}


