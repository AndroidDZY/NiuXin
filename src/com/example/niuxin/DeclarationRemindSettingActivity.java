package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DeclarationRemindSettingActivity extends Activity {
	private ListView listView;
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	private SharePreferenceUtil util = null;
	private int flag = 0;
	private int flagDeclaration = 0;
	private int flagDeclarationAll = 0;
	private int flagDeclarationOne = 0;
	private int flagDeclarationTwo = 0;
	private int flagDeclarationThree = 0;
	private Button btnBack, btnSelectAll, btnDelete;
	private Handler handler = new Handler();
	SimpleAdapter adapter = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_declaration_remind_setting);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		initView();
		
		/* 按钮点击事件
		 * 
		 */
		btnBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		list = getData();// 填充list数据
		listView = (ListView)findViewById(R.id.lv_declaration_remind_setting);//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用listview_declaration_remind_setting.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用listview_declaration_remind_setting.xml文件中的哪些组件来填充列表项
		 adapter= new SimpleAdapter(this, list,R.layout.listview_declaration_remind_setting, 
				new String[]{"flag","name"},
				new int[]{R.id.iv_declaration_remind_setting_flag,R.id.tv_declaration_remind_setting_name});
		 listView.setAdapter(adapter);//为listView设置适配器
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {
				switch (position)
				{
				case 0: //点击全选
					flag = 1;
					break;
				case 1://点击1
					flag = 2;
					break;
				case 2:
					flag = 3;
					break;
				case 3:
					flag = 4;
					break;
				default:
					flag = 0;
					break;
				}
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);//找到被点击的列表项
	            if(Integer.valueOf(map.get("flag").toString())==0){
	            	flagDeclaration = R.drawable.ic_declaration_selected;
	            }else
	            	flagDeclaration = 0;
	            //先清空
	            list.get(0).put("flag", 0);
	            list.get(1).put("flag", 0);
	            list.get(2).put("flag", 0);
	            list.get(3).put("flag", 0);
	            list.get(position).put("flag", flagDeclaration);//将更新过的flag值放入list中        
	            adapter.notifyDataSetInvalidated();//使更新过的list数据生效
			}
		});
	}
	
	private void initView() {
		// 控件初始化
		btnBack = (Button)findViewById(R.id.btn_declaration_remind_setting_back);
		btnSelectAll = (Button)findViewById(R.id.btn_declaration_remind_setting_all);
		btnDelete = (Button)findViewById(R.id.btn_declaration_remind_setting_delete);
	}

	class SearchThread extends Thread {
		@Override
		public void run() {			
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			// 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("userid", util.getId());
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);
		
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/user/action_serachAllUserExceptSelf.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			String res = postUtil.run();
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.clear();
			
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					String img = myjObject.getString("img");
					String name = myjObject.getString("name");
					map.put("id", id);
					map.put("img", img);
					map.put("name", name);	
						map.put("flag", flagDeclarationOne);
					list.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					// 这里可以写上更新UI的代码
					/*
					 * addAdapter = new SimpleAdapter(zixuan_addActivity.this,
					 * list, R.layout.addlistview, new String[] { "name",
					 * "number", "add_flag" }, new int[] { R.id.name, R.id.num,
					 * R.id.add_flag }); listView.setAdapter(addAdapter);//
					 * 为listView设置适配器
					 */
					adapter.notifyDataSetChanged();

				}

			};
			handler.post(r);
		}
	}

	
	
	
	
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = getList();
		return list;
	}
	private List<Map<String, Object>> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("flag", flagDeclarationAll);
		map.put("name", "用户1");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("flag", flagDeclarationOne);
		map.put("name", "用户2");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("flag", flagDeclarationTwo);
		map.put("name", "用户3");
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("id", 4);
		map.put("flag", flagDeclarationThree);
		map.put("name", "用户4");
		list.add(map);

		return list;
	}
}
