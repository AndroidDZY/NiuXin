package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class CollectActivity extends Activity{
	ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect);
		
		listView=(ListView)findViewById(R.id.collectlist);
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用collectlistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用collectlistview.xml文件中的哪些组件来填充列表项
		SimpleAdapter collectAdapter= new SimpleAdapter(this, getData(),
				R.layout.collectlistview, new String[]{"img_collect","xiaoxiyuan","collect_title",
						"collect_context","collect_date","collect_time"},new int[]{R.id.img_collect,R.id.xiaoxiyuan,
			             R.id.collect_title,R.id.collect_context,R.id.collect_date,R.id.collect_time});
		listView.setAdapter(collectAdapter);
	}
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listCollect= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("img_collect", R.drawable.collect_head);
		map.put("xiaoxiyuan", "所有");
		map.put("collect_title", "中信证券讨论组");
		map.put("collect_context","汪总：今天又要涨停");
		map.put("collect_date", "15/7/2");
		map.put("collect_time", "13:27");
		listCollect.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img_collect", R.drawable.collect_head);
		map.put("xiaoxiyuan", "所有");
		map.put("collect_title", "中信证券讨论组");
		map.put("collect_context","汪总：今天又要涨停");
		map.put("collect_date", "15/7/2");
		map.put("collect_time", "13:27");
		listCollect.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img_collect", R.drawable.collect_head);
		map.put("xiaoxiyuan", "所有");
		map.put("collect_title", "中信证券讨论组");
		map.put("collect_context","汪总：今天又要涨停");
		map.put("collect_date", "15/7/2");
		map.put("collect_time", "13:27");
		listCollect.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img_collect", R.drawable.collect_head);
		map.put("xiaoxiyuan", "所有");
		map.put("collect_title", "中信证券讨论组");
		map.put("collect_context","汪总：今天又要涨停");
		map.put("collect_date", "15/7/2");
		map.put("collect_time", "13:27");
		listCollect.add(map);
		
		return listCollect;
	}

}
