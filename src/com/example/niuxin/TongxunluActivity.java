package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TongxunluActivity extends Activity {
	ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//719修改
		setContentView(R.layout.tongxunlu);
		listView=(ListView)findViewById(R.id.zixuanlist);
		SimpleAdapter zixuanAdapter= new SimpleAdapter(this, getData(),R.layout.zixuanlistview, 
				new String[]{"image","title"},
				new int[]{R.id.image,R.id.title});
		listView.setAdapter(zixuanAdapter);
		
		//根据点击不同item跳转到自选股添加界面、公众号、查找用户及群组界面
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch (position)
				{
				case 0: //点击第一个item
					startActivity(new Intent(TongxunluActivity.this,zixuan_addActivity.class));
					break;
				}
			}
	
		});
	}



	

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image", R.drawable.addresslist_01);
		map.put("title", "添加自选股");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.addresslist_02);
		map.put("title", "公众号");
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image", R.drawable.addresslist_03);
		map.put("title", "查找用户及群组");
		list.add(map);
		
		return list;
	}

}


