package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
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
	private int add_flag;
	private boolean isadd = false;
	Button add_cancle,add_finish;
	ListView listView;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zixuan_add);
		
		// 获取EditText对象
		EditText gupiao = (EditText) findViewById(R.id.et_gupiao);
		//gupiao.setCursorVisible(false);//设置光标不显示,但不能设置光标颜色
		//gupiao.clearFocus(); //让EditText失去焦点
		//InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);  
		//imm.hideSoftInputFromWindow(gupiao.getWindowToken(), 0);  
		//gupiao.setInputType(InputType.TYPE_NULL); 
		
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
        
		//获取ListView
        listView = (ListView)findViewById(R.id.addlist);
		SimpleAdapter addAdapter= new SimpleAdapter(this, getData(),R.layout.addlistview, 
				new String[]{"name","num","add_flag"},
				new int[]{R.id.name,R.id.num,R.id.add_flag});
		listView.setAdapter(addAdapter);
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				isadd = !isadd;
				if (isadd == true) {
					add_flag=R.drawable.add_flag02;
				} else {
					add_flag=R.drawable.add_flag01;
				}
			}
		});

	

	}
	
	// 适配器将数据填入listview
	private List<Map<String, Object>> getData() {
		add_flag=R.drawable.add_flag01;
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> 
		
		map = new HashMap<String, Object>();
		map.put("name","海螺水泥");
		map.put("num", "600585");
		map.put("add_flag",add_flag);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","大秦铁路");
		map.put("num", "601006");
		map.put("add_flag", add_flag);
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("name","太钢不锈");
		map.put("num", "000825");
		map.put("add_flag", add_flag);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","沪深300 ");
		map.put("num", "399300");
		map.put("add_flag", add_flag);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("name","首创股份");
		map.put("num", "600008");
		map.put("add_flag", add_flag);
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("name","百大集团");
		map.put("num", "600865");
		map.put("add_flag", add_flag);
		list.add(map);
		
		return list;
	}
	
}
