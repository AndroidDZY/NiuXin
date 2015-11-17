package com.example.niuxin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
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

public class ContractTypeSelectActivity extends Activity {
	private ListView listView;
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	SimpleAdapter adapter = null;
	private Set<Integer> contractSelectList = new HashSet<Integer>();
	private Button btnBack;
	private Button btnSave;
	private Handler handler = new Handler();
	private SharePreferenceUtil util = null;

	@Override
	protected void onResume() {
		super.onResume();
		// 准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。
		SearchThread thread = new SearchThread();
		thread.start();

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_contract_type_select);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		initView();

		listView = (ListView) findViewById(R.id.lv_contract_type_select);// 获取ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用listview_contract_type_select.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用listview_contract_type_select.xml文件中的哪些组件来填充列表项

		adapter = new SimpleAdapter(this, list, R.layout.listview_contract_type_select, new String[] { "flag", "type" },
				new int[] { R.id.iv_contract_type_select_flag, R.id.tv_contract_type_select_name });
		listView.setAdapter(adapter);// 为listView设置适配器

		// 实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleAdapter adapterClick = (SimpleAdapter) parent.getAdapter();// 找到被点击的Adapter
				Map<String, Object> map = (Map<String, Object>) adapterClick.getItem(position);// 找到被点击的列表项
				int flag = 0;
				if (Integer.valueOf(map.get("flag").toString()) == 0) {
					flag = R.drawable.ic_declaration_selected;
					contractSelectList.add((Integer) (list.get(position).get("id")));
				} else {
					flag = 0;
					contractSelectList.remove((Integer) (list.get(position).get("id")));
				}

				list.get(position).put("flag", flag);// 将更新过的flag值放入list中

				adapterClick.notifyDataSetInvalidated();// 使更新过的list数据生效
			}
		});
	}

	private void initView() {
		// 控件初始化
		btnBack = (Button) findViewById(R.id.btn_contract_type_select_back);
		btnSave = (Button) findViewById(R.id.btn_contract_type_select_save);

		/*
		 * 按钮点击事件
		 * 
		 */
		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*
				 * String result = ""; if(contractSelectList!=null){ int b =
				 * contractSelectList.toString().length(); result =
				 * contractSelectList.toString().substring(1,b-1); } Intent
				 * intentType = new Intent(ContractTypeSelectActivity.this,
				 * DeclarationReceiveActivity.class);
				 * intentType.putExtra("contract",result);
				 * startActivity(intentType);
				 */
				finish();
			}
		});

		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String result = "";
				if (contractSelectList != null) {
					int b = contractSelectList.toString().length();
					result = contractSelectList.toString().substring(1, b - 1);
				}
				Intent intentType = new Intent(ContractTypeSelectActivity.this, DeclarationReceiveActivity.class);
				intentType.putExtra("contract", result);
				startActivity(intentType);
				finish();
			}
		});
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
				jsonObject.put("id", util.getId());

			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/contract/contract_selectAll.do");
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
			if ( null!= list)
				list.clear();
			
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", -1);
			map1.put("type", "全选");
			map1.put("flag", 0);
			list.add(map1);
			if (null != jsonArray)
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
						Map<String, Object> map = new HashMap<String, Object>();
						// 获取每一个对象中的值
						int id = myjObject.getInt("id");
						String type = myjObject.getString("type");
						map.put("id", id);
						map.put("type", type);
						map.put("flag", 0);
						list.add(map);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					adapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
		}
	}

}
