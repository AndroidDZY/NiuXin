package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class edit_zixuanActivity extends Activity{
	Button btn_edit_cancle;
	ImageButton btn_edit_add;
	ListView listView;
	private int edit_flag = R.drawable.edit_flag01;
	//实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_zixuan);
		
		//获取Button
		btn_edit_add = (ImageButton)findViewById(R.id.btn_edit_add);
		btn_edit_cancle = (Button)findViewById(R.id.btn_edit_cancle);
		//为Button绑定点击事件
		btn_edit_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(edit_zixuanActivity.this,zixuan_addActivity.class);
				startActivity(intent);
			}
		});
		btn_edit_cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});	
		
		list = getData();// 填充list数据
		listView = (ListView)findViewById(R.id.editlist);//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用editlistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用editlistview.xml文件中的哪些组件来填充列表项
		SimpleAdapter editAdapter= new SimpleAdapter(this, list,R.layout.editlistview, 
				new String[]{"img_edit_flag","edit_name","edit_num","img_edit_top","img_edit_move"},
				new int[]{R.id.img_edit_flag,R.id.edit_name,R.id.edit_num,R.id.img_edit_top,R.id.img_edit_move});
		listView.setAdapter(editAdapter);//为listView设置适配器
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {		
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);//找到被点击的列表项
	            if(Integer.valueOf(map.get("img_edit_flag").toString())==R.drawable.edit_flag01){
	            	edit_flag = R.drawable.edit_flag02;
	            }else
	            	edit_flag = R.drawable.edit_flag01;
	         
	            list.get(position).put("img_edit_flag", edit_flag);//将更新过的add_flag值放入list中        
	            adapter.notifyDataSetInvalidated();//使更新过的list数据生效
			}
		});
		

}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = getList();
		return list;
	}
	private List<Map<String, Object>> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//map.put("id", 1);
		map.put("img_edit_flag", edit_flag);
		map.put("edit_name", "海螺水泥");
		map.put("edit_num", "600585");
		map.put("img_edit_top", R.drawable.edit_top);
		map.put("img_edit_move", R.drawable.edit_move);
		list.add(map);
		
		map = new HashMap<String, Object>();
		//map.put("id", 2);
		map.put("img_edit_flag", edit_flag);
		map.put("edit_name", "大秦铁路");
		map.put("edit_num", "601006");
		map.put("img_edit_top", R.drawable.edit_top);
		map.put("img_edit_move", R.drawable.edit_move);
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 3);
		map.put("img_edit_flag", edit_flag);
		map.put("edit_name", "太钢不锈");
		map.put("edit_num", "000825");
		map.put("img_edit_top", R.drawable.edit_top);
		map.put("img_edit_move", R.drawable.edit_move);
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 4);
		map.put("img_edit_flag", edit_flag);
		map.put("edit_name", "沪深300 ");
		map.put("edit_num", "399300");
		map.put("img_edit_top", R.drawable.edit_top);
		map.put("img_edit_move", R.drawable.edit_move);
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 5);
		map.put("img_edit_flag", edit_flag);
		map.put("edit_name", "首创股份");
		map.put("edit_num", "600008");
		map.put("img_edit_top", R.drawable.edit_top);
		map.put("img_edit_move", R.drawable.edit_move);
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 6);
		map.put("img_edit_flag", edit_flag);
		map.put("edit_name", "百大集团");
		map.put("edit_num", "600865");
		map.put("img_edit_top", R.drawable.edit_top);
		map.put("img_edit_move", R.drawable.edit_move);
		list.add(map);

		return list;
	}
}
