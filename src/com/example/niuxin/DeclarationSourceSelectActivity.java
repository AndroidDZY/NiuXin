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
import android.widget.Toast;

public class DeclarationSourceSelectActivity extends Activity {

	private ListView listView;
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	private Set<Integer> useridSelectList = new HashSet<Integer>();
	private List<String> useridSelectListName = new LinkedList<String>();

	private Button btnBack, btnSave;
	private SuoluetuActivity suolue;
	private Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	int selectAllmark = 0;
	SimpleAdapter adapter = null;
	String contractlist = "-1";

	@Override
	protected void onResume() {
		super.onResume();

		Intent intent = getIntent();

		if (null != intent) {
			contractlist = intent.getStringExtra("contractlist");
			if (null == contractlist)
				contractlist = "-1";
		}

		SearchThread thread = new SearchThread();
		thread.start();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_declaration_source_select);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		suolue = new SuoluetuActivity(this, handler);

		initView();

		btnBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast toast = Toast.makeText(DeclarationSourceSelectActivity.this, "保存完毕", Toast.LENGTH_SHORT);
				toast.show();

				if ((Integer) (list.get(0).get("flag")) == R.drawable.ic_declaration_selected) {// 如果全选已经被选上的情况
					useridSelectList.add(-1);
					useridSelectListName.add("全选");
				} else {
					for (int i = 1; i < list.size(); i++) {
						if ((Integer) (list.get(i).get("flag")) == R.drawable.ic_declaration_selected) {
							useridSelectList.add((Integer) (list.get(i).get("id")));
							useridSelectListName.add((String) (list.get(i).get("name")));
						}
					}
				}
				String result = "";
				if (useridSelectList != null) {
					int b = useridSelectList.toString().length();
					result = useridSelectList.toString().substring(1, b - 1);
				}
				String resultName = "";
				if (useridSelectListName != null) {
					int c = useridSelectListName.toString().length();
					resultName = useridSelectListName.toString().substring(1, c - 1);
				}
				if (resultName.length() > 10) {
					resultName = resultName.substring(0, 9) + "...";
				}

				Intent intentType = new Intent(DeclarationSourceSelectActivity.this, DeclarationReceiveActivity.class);
				intentType.putExtra("sendtouserid", result);
				intentType.putExtra("sendtouseridName", resultName);
				setResult(13, intentType);
				finish();
			}
		});

		listView = (ListView) findViewById(R.id.lv_declaration_source_select);// 获取ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用listview_declaration_source_select.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用listview_declaration_source_select.xml文件中的哪些组件来填充列表项
		adapter = new SimpleAdapter(this, list, R.layout.listview_declaration_source_select,
				new String[] { "flag", "name" }, // 是否选中、报单者名字
				new int[] { R.id.iv_declaration_source_select_flag, R.id.tv_declaration_source_select_name });
		listView.setAdapter(adapter);// 为listView设置适配器

		// 实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();// 找到被点击的Adapter
				Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);// 找到被点击的列表项

				if ((Integer) (list.get(0).get("flag")) == R.drawable.ic_declaration_selected) {// 如果全选已经被选上的情况
					for (int i = 0; i < list.size(); i++) {// 将所有的设置选中
						list.get(i).put("flag", R.drawable.ic_declaration_selected);
					}
					if (position == 0) {// 这个时候点击全选，将全选设置为未选中
						list.get(0).put("flag", 0);//
					} else {
						Toast toast = Toast.makeText(DeclarationSourceSelectActivity.this, "请先取消全选勾选项",
								Toast.LENGTH_SHORT);
						toast.show();
					}
				} else {
					if (position == 0) {// 这个时候点击全选
						for (int i = 0; i < list.size(); i++) {// 将所有的设置选中
							list.get(i).put("flag", R.drawable.ic_declaration_selected);
						}
					} else {
						int flag = 0;
						if (Integer.valueOf(map.get("flag").toString()) == 0) {
							flag = R.drawable.ic_declaration_selected;
						} else {
							flag = 0;
						}
						list.get(position).put("flag", flag);
					}
				}
				adapter.notifyDataSetInvalidated();// 使更新过的list数据生效
			}
		});
	}

	private void initView() {
		// 控件初始化
		btnBack = (Button) findViewById(R.id.btn_declaration_source_select_back); // 返回按钮
		btnSave = (Button) findViewById(R.id.btn_declaration_source_select_save); // 保存按钮
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
			postUtil.setUrl("/user/action_selectByUserid.do");
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
			if (null != list)
				list.clear();

			String[] strsChecked = null;// 被选中的id
			if (!contractlist.equals("-1")) {
				strsChecked = contractlist.split(",");
			}

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("id", -1);
			map1.put("flag", 0);
			map1.put("name", "全选");
			list.add(map1);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("id", -2);
			map2.put("flag", 0);			
			if (null != strsChecked)
				for (String str : strsChecked) {								
					if (str.trim().equals("-2")) {
						map2.put("flag", R.drawable.ic_declaration_selected);
						break;
					} else
						map2.put("flag", 0);
				}
			map2.put("name", "已关注的报单者");
			list.add(map2);
			if (null != jsonArray) {
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
						Map<String, Object> map = new HashMap<String, Object>();
						// 获取每一个对象中的值
						int id = myjObject.getInt("id");
						String name = myjObject.getString("name");
						map.put("id", id);
						map.put("name", name);
						map.put("flag", 0);
						if (null != strsChecked)
							for (String str : strsChecked) {								
								if (Integer.valueOf(str.trim()) == id) {
									map.put("flag", R.drawable.ic_declaration_selected);
									break;
								} else
									map.put("flag", 0);
							}

						list.add(map);
					} catch (JSONException e) {
						e.printStackTrace();
					}
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
