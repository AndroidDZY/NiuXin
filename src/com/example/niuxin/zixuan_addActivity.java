
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
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class zixuan_addActivity extends Activity {
	private int add_flag = R.drawable.add_flag01;
	private Handler handler = new Handler();
	Button add_cancle, add_finish, add_search;
	ListView listView;
	SimpleAdapter addAdapter;
	public static Activity act = null;
	// 实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	EditText gupiao;
	private SharePreferenceUtil util = null;

	@Override
	protected void onResume() {
		super.onResume();
		// 准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。
		TestThread thread = new TestThread();
		thread.start();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		act = zixuan_addActivity.this;
		setContentView(R.layout.zixuan_add);// 设置zixuan_addActivity采用zixuan_add.xml布局文件进行布局
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		// 获取EditText对象
		gupiao = (EditText) findViewById(R.id.et_gupiao);
		// 获取Button对象
		add_cancle = (Button) findViewById(R.id.add_cancle);
		add_finish = (Button) findViewById(R.id.add_finish);
		add_search = (Button) findViewById(R.id.add_search);
		// 取消按钮返回上一个窗口
		add_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
		// 完成按钮返回上一个窗口
		add_finish.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				PostThread pThread = new PostThread();
				pThread.start();

			}
		});

		// 查找按钮完成查找
		add_search.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				SearchThread sthread = new SearchThread();
				sthread.start();
			}
		});

		listView = (ListView) findViewById(R.id.addlist);// 获取ListView
		addAdapter = new SimpleAdapter(zixuan_addActivity.this, list, R.layout.addlistview,
				new String[] { "name", "number", "add_flag" }, new int[] { R.id.name, R.id.number, R.id.add_flag });
		listView.setAdapter(addAdapter);// 为listView设置适配器

		// 实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();// 找到被点击的Adapter
				Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);// 找到被点击的列表项
				if (Integer.valueOf(map.get("add_flag").toString()) == R.drawable.add_flag01) {
					add_flag = R.drawable.add_flag02;
				} else
					add_flag = R.drawable.add_flag01;
				// map.put("add_flag", add_flag);
				if (list == null) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				list.get(position).put("add_flag", add_flag);// 将更新过的add_flag值放入list中
				adapter.notifyDataSetInvalidated();// 使更新过的list数据生效
			}
		});
	}

	class TestThread extends Thread {
		private Dialog mDialog = null;

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			/*
			 * // 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			 * try { jsonObject.put("username", "huangwuyi");
			 * jsonObject.put("sex", "男"); jsonObject.put("QQ", "413425430");
			 * jsonObject.put("Min.score", new Integer(99));
			 * jsonObject.put("nickname", "梦中心境"); } catch (JSONException e) {
			 * e.printStackTrace(); } postUtil.setRequest(jsonObject);
			 */
			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectAll.do");
			// 不向服务器发送数据
			postUtil.setRequest(null);

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
					String number = myjObject.getString("type");
					String name = myjObject.getString("name");
					map.put("id", id);
					map.put("number", number);
					map.put("name", name);
					map.put("add_flag", add_flag);
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
					addAdapter.notifyDataSetChanged();

				}

			};
			handler.post(r);
		}
	}

	class SearchThread extends Thread {
		@Override
		public void run() {
			String sharename = gupiao.getText().toString();
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			// 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("sharename", sharename);
				jsonObject.put("userid", util.getId());
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);
		

			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectAllByShareName.do");
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
					String number = myjObject.getString("type");
					String name = myjObject.getString("name");
					map.put("id", id);
					map.put("number", number);
					map.put("name", name);
					map.put("add_flag", add_flag);
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
					addAdapter.notifyDataSetChanged();

				}

			};
			handler.post(r);
		}
	}

	class PostThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			JSONArray jArray = new JSONArray();
			for (int i = 0; i < list.size(); i++) {
				if (Integer.valueOf(list.get(i).get("add_flag").toString()) == R.drawable.add_flag02) {
					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("ShareId", Integer.valueOf(list.get(i).get("id").toString()));
						jsonObject.put("UserId", util.getId());
						jArray.put(jsonObject);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_insert.do");
			// 不向服务器发送数据
			postUtil.setRequest(jArray);
			// 从服务器获取数据
			postUtil.run();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					finish();
				}

			};
			handler.post(r);
		}
	}
}
