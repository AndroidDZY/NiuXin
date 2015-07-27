package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.app.Activity;

public class zixuan_addActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zixuan_add);
		
		// 获取EditText对象
		EditText search = (EditText) findViewById(R.id.search);
		//设置光标不显示,但不能设置光标颜色
		search.setCursorVisible(false);
		
        // 获取Button对象  
        Button add_cancle=(Button)findViewById(R.id.add_cancle);
        Button add_finish=(Button)findViewById(R.id.add_finish);
        // 取消按钮返回上一个窗口
        add_cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				finish();

			}
		});
        // 完成按钮返回上一个窗口
        add_finish.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				finish();

			}
		});
        
		//获取ListView
        ListView listView=(ListView)findViewById(R.id.addlist);
		SimpleAdapter addAdapter= new SimpleAdapter(this, getData(),R.layout.addlistview, 
				new String[]{"name","num"},
				new int[]{R.id.name,R.id.num});
		listView.setAdapter(addAdapter);
	}

	// 适配器将数据填入listview
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> 
		
		map = new HashMap<String, Object>();
		map.put("name","海螺水泥");
		map.put("num", "600585");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","大秦铁路");
		map.put("num", "601006");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("name","太钢不锈");
		map.put("num", "000825");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","沪深300 ");
		map.put("num", "399300");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","首创股份");
		map.put("num", "600008");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("name","百大集团");
		map.put("num", "600865");
		list.add(map);
		
		return list;
	}
}
