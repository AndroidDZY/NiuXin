package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;


public class zixuan_addActivity extends Activity {
	private int add_flag = R.drawable.add_flag01;
	private Map<Integer,Integer> add_flags = new HashMap<Integer,Integer>();
	private boolean isadd = false;
	Button add_cancle, add_finish;
	ListView listView;
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	EditText gupiao;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zixuan_add);
		
		// 获取EditText对象
		gupiao = (EditText) findViewById(R.id.et_gupiao);
		
        // 获取Button对象  
        add_cancle=(Button)findViewById(R.id.add_cancle);
        add_finish=(Button)findViewById(R.id.add_finish);
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
        list = getData();
		//获取ListView
        listView = (ListView)findViewById(R.id.addlist);
		SimpleAdapter addAdapter= new SimpleAdapter(this, list,R.layout.addlistview, 
				new String[]{"name","num","add_flag"},
				new int[]{R.id.name,R.id.num,R.id.add_flag});
		listView.setAdapter(addAdapter);
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {		
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);
	            if(Integer.valueOf(map.get("add_flag").toString())==R.drawable.add_flag01){
	            	add_flag = R.drawable.add_flag02;
	            }else
	            	add_flag = R.drawable.add_flag01;
	         //   map.put("add_flag", add_flag);
	            list.get(position).put("add_flag", add_flag);        
	            adapter.notifyDataSetInvalidated(); 
			}
		});
	}
	
	private List<Map<String, Object>> getList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object>

		map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("name", "海螺水泥");
		map.put("num", "600585");
		map.put("add_flag", add_flag);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("name", "大秦铁路");
		map.put("num", "601006");
		map.put("add_flag", add_flag);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("name", "太钢不锈");
		map.put("num", "000825");
		map.put("add_flag", add_flag);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 4);
		map.put("name", "沪深300 ");
		map.put("num", "399300");
		map.put("add_flag", add_flag);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 5);
		map.put("name", "首创股份");
		map.put("num", "600008");
		map.put("add_flag", add_flag);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 6);
		map.put("name", "百大集团");
		map.put("num", "600865");
		map.put("add_flag", add_flag);
		list.add(map);

		return list;
	}

	// 适配器将数据填入listview
	private List<Map<String, Object>> getData() {		
		List<Map<String, Object>> list = getList();
		return list;
	}
}
