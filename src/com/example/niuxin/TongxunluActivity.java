package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.content.Intent;

public class TongxunluActivity extends Activity {
	ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tongxunlu);
		listView=(ListView)findViewById(R.id.zixuanlist);
		
		/*为ListView设置Adapter来绑定数据*/
		SimpleAdapter zixuanAdapter= new SimpleAdapter(this, getData(),
				R.layout.zixuanlistview, new String[]{"img","qunname","lastmes",
						"time","quntag","renshu"},new int[]{R.id.img,R.id.qunname,
			             R.id.lastmes,R.id.time,R.id.quntag,R.id.renshu});
		listView.setAdapter(zixuanAdapter);
		
		/* 跳转到添加自选股界面 */
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TongxunluActivity.this,ChatActivity.class));
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

