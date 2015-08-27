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
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TongxunluActivity extends Activity {
	ListView listView,listView_u;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		//719修改
		setContentView(R.layout.tongxunlu);
		//获取第一个ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用zixuanlistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用zixuanlistview.xml文件中的哪些组件来填充列表项
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
				case 0: //点击第1个item
					startActivity(new Intent(TongxunluActivity.this,zixuan_addActivity.class));
					break;
				case 1: //点击第2个item
					break;
				case 2: //点击第3个item
					startActivity(new Intent(TongxunluActivity.this,SearchuserActivity.class));
					break;
				}
			}
	
		});
		
		//获取第二个ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用tongxunlulistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用tongxunlulistview.xml文件中的哪些组件来填充列表项
		listView_u=(ListView)findViewById(R.id.tonglist);
		SimpleAdapter tongxunAdapter= new SimpleAdapter(this, getData_u(),R.layout.tongxunlulistview, 
				new String[]{"image","title"},
				new int[]{R.id.image_u,R.id.title_u});
		listView_u.setAdapter(tongxunAdapter);
		

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
	


private List<Map<String, Object>> getData_u() {
	// TODO Auto-generated method stub
	List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
	Map<String, Object> map = new HashMap<String, Object>();
	
	map.put("image", R.drawable.head_left);
	map.put("title", "中信证券讨论组");
	list.add(map);
	
	map = new HashMap<String, Object>();
	map.put("image", R.drawable.head_right);
	map.put("title", "汪总");
	list.add(map);	
	
	map = new HashMap<String, Object>();
	map.put("image", R.drawable.head_left);
	map.put("title", "海螺水泥群组");
	list.add(map);
	
	return list;
}

}


