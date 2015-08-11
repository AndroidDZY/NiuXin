package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;



import com.util.HttpPostUtil;

import android.os.Bundle;
import android.os.Handler;
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
	private  Handler handler = new Handler();
	//实例化一个HashMap类(键值对，键和值都是Integer类型)的对象add_flags
	//private Map<Integer,Integer> add_flags = new HashMap<Integer,Integer>();
	//private boolean isadd = false;
	Button add_cancle, add_finish;
	ListView listView;
	SimpleAdapter addAdapter;
	//实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	EditText gupiao;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.zixuan_add);//设置zixuan_addActivity采用zixuan_add.xml布局文件进行布局
		
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
        
        listView = (ListView)findViewById(R.id.addlist);//获取ListView                
        TestThread thread = new TestThread();
        thread.start();
              		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {		
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);//找到被点击的列表项
	            if(Integer.valueOf(map.get("add_flag").toString())==R.drawable.add_flag01){
	            	add_flag = R.drawable.add_flag02;
	            }else
	            	add_flag = R.drawable.add_flag01;
	         //   map.put("add_flag", add_flag);
	            if(list==null){
	            	try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	            list.get(position).put("add_flag", add_flag);//将更新过的add_flag值放入list中        
	            adapter.notifyDataSetInvalidated();//使更新过的list数据生效
			}
		});
	}
	
	private List<Map<String, Object>> getList() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
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

	
	class TestThread extends Thread{
    	
    	@Override
    	public void run(){
    		//从网络上取回数据   		
    		HttpPostUtil postUtil = new HttpPostUtil(handler);
    		JSONObject jsonObject = new JSONObject();	
    		 try {
    			jsonObject.put("username", "huangwuyi");
    			  jsonObject.put("sex", "男");
    		        jsonObject.put("QQ", "413425430");
    		        jsonObject.put("Min.score", new Integer(99));
    		        jsonObject.put("nickname", "梦中心境");
    		} catch (JSONException e) {
    			e.printStackTrace();
    		}  
    		postUtil.setUrl("/user/action_countAll.do");
    		postUtil.setRequest(jsonObject);
    		String res = postUtil.run();	
			// 从数据库获取数据结束
    		
    		
    		//模拟从数据库获取的数据
    		final List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("id", 1);
    		map.put("name", "海螺水泥");
    		map.put("num", "600585");
    		map.put("add_flag", add_flag);
    		list1.add(map);
    		
    		map = new HashMap<String, Object>();
    		map.put("id", 2);
    		map.put("name", "大秦铁路");
    		map.put("num", "601006");
    		map.put("add_flag", add_flag);
    		list1.add(map);

    		map = new HashMap<String, Object>();
    		map.put("id", 3);
    		map.put("name", "太钢不锈");
    		map.put("num", "000825");
    		map.put("add_flag", add_flag);
    		list1.add(map);

    		map = new HashMap<String, Object>();
    		map.put("id", 4);
    		map.put("name", "沪深300 ");
    		map.put("num", "399300");
    		map.put("add_flag", add_flag);
    		list1.add(map);   
///////////////////////////////////////////////////////////
    		
    		
    		
    		list = list1;   		
    		Runnable r = new Runnable(){
				@Override
				public void run() {
					//这里可以写上更新UI的代码
					 addAdapter= new SimpleAdapter(zixuan_addActivity.this, list,R.layout.addlistview, 
		    				new String[]{"name","num","add_flag"},
		    				new int[]{R.id.name,R.id.num,R.id.add_flag});
		    		listView.setAdapter(addAdapter);//为listView设置适配器
				}
    			
    		};
    		    		
    		handler.post(r);
    	}
    }
	
	
}


