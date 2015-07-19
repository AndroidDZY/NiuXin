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

public class LiaotianActivity extends Activity {
	ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//719修改
		setContentView(R.layout.liaotian);
		listView=(ListView)findViewById(R.id.qunlist);
		SimpleAdapter liaotianAdapter= new SimpleAdapter(this, getData(),
				R.layout.qunlistview, new String[]{"img","qunname","lastmes",
						"time","quntag","renshu"},new int[]{R.id.img,R.id.qunname,
			             R.id.lastmes,R.id.time,R.id.quntag,R.id.renshu});
		listView.setAdapter(liaotianAdapter);
		//跳转到聊天界面
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LiaotianActivity.this,ChatActivity.class));
				
			}
		});

	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listLiaoTian= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("img", R.drawable.i1);
		map.put("qunname", "中兴证券讨论组");
		map.put("lastmes", "听说杨总又换女友了");
		map.put("time","12:30");
		map.put("quntag", "个股");
		map.put("renshu", "12/20");
		listLiaoTian.add(map);
		return listLiaoTian;
	}

}
