package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

import android.widget.SimpleAdapter;

public class LiaotianActivity extends ListActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SimpleAdapter liaotianAdapter= new SimpleAdapter(this, getData(),
				R.layout.liaotian, new String[]{"img","qunname","lastmes",
						"time","quntag","renshu"},new int[]{R.id.img,R.id.qunname,
			             R.id.lastmes,R.id.time,R.id.quntag,R.id.renshu});
		setListAdapter(liaotianAdapter);
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
