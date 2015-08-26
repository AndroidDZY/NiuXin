package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class yaoqingchengyuanActivity extends Activity{
	ListView listView;
	private int yq_flag = R.drawable.edit_flag01;
	private EditText mEditText;
	//实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	//1
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	Button cancle;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.yaoqingchengyuan);
		//2
		suolue = new SuoluetuActivity(this,handler);
		//获取各种控件
		cancle = (Button)findViewById(R.id.btn_yq_cancle);
		mEditText = (EditText)findViewById(R.id.yq_search);
		
		//定义按钮事件
		//取消按钮
		cancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//点击搜索框跳转到添加好友界面
		mEditText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(yaoqingchengyuanActivity.this,SearchFriendActivity.class));
			}
		});
		
		list = getData();// 填充list数据
		listView = (ListView)findViewById(R.id.yaoqinglist);//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用yaoqinglistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用yaoqinglistview.xml文件中的哪些组件来填充列表项
		SimpleAdapter yqAdapter= new SimpleAdapter(this, list,R.layout.yaoqinglistview, 
				new String[]{"img_yq_flag","img_yq_head","yq_name"},
				new int[]{R.id.img_yq_flag,R.id.img_yq_head,R.id.yq_name});
		listView.setAdapter(yqAdapter);//为listView设置适配器
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {		
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);//找到被点击的列表项
	            if(Integer.valueOf(map.get("img_yq_flag").toString())==R.drawable.edit_flag01){
	            	yq_flag = R.drawable.edit_flag02;
	            }else
	            	yq_flag = R.drawable.edit_flag01;
	         
	            list.get(position).put("img_yq_flag", yq_flag);//将更新过的add_flag值放入list中        
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
		map.put("img_yq_flag", yq_flag);
		map.put("img_yq_head", R.drawable.head_01);
		map.put("yq_name", "用户1");
		list.add(map);
		
		map = new HashMap<String, Object>();
		//map.put("id", 2);
		map.put("img_yq_flag", yq_flag);
		map.put("img_yq_head", R.drawable.head_01);
		map.put("yq_name", "用户2");
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 3);
		map.put("img_yq_flag", yq_flag);
		map.put("img_yq_head", R.drawable.head_01);
		map.put("yq_name", "用户3");
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 4);
		map.put("img_yq_flag", yq_flag);
		map.put("img_yq_head", R.drawable.head_01);
		map.put("yq_name", "用户4");
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 5);
		map.put("img_yq_flag", yq_flag);
		map.put("img_yq_head", R.drawable.head_01);
		map.put("yq_name", "用户5");
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 6);
		map.put("img_yq_flag", yq_flag);
		map.put("img_yq_head", R.drawable.head_01);
		map.put("yq_name", "用户6");
		list.add(map);

		return list;
	}

}

