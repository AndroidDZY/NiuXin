package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.niuxin.zixuan_addActivity.PostThread;
import com.example.niuxin.zixuan_addActivity.TestThread;
import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class edit_zixuanActivity extends Activity {
	Button btn_edit_cancle,edit_finish;
	ImageButton btn_edit_add,edit_delete;
	private Handler handler = new Handler();
	ListView listView;
	private int edit_flag = R.drawable.edit_flag01;
	public static Activity act = null;
	private SharePreferenceUtil util = null;
	SimpleAdapter editAdapter = null;
	// 实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();

	@Override
	protected void onResume() {
		super.onResume();
		TestThread thread = new TestThread();
		thread.start();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.edit_zixuan);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);

		// 获取Button
		btn_edit_add = (ImageButton) findViewById(R.id.btn_edit_add);
		edit_finish = (Button) findViewById(R.id.edit_finish);
		btn_edit_cancle = (Button) findViewById(R.id.btn_edit_cancle);
		edit_delete =  (ImageButton) findViewById(R.id.edit_delete);
		// 为Button绑定点击事件
		edit_finish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				PostThread pThread = new PostThread();
				pThread.start();				
			}
		});
		edit_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				PostThread pThread = new PostThread();
				pThread.start();				
			}
		});
		btn_edit_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//PostThread pThread = new PostThread();
				//pThread.start();
				Intent intent = new Intent(edit_zixuanActivity.this, zixuan_addActivity.class);
				startActivity(intent);
			}
		});
		btn_edit_cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		listView = (ListView) findViewById(R.id.editlist);// 获取ListView
		// 创建适配器
		editAdapter = new SimpleAdapter(this, list, R.layout.editlistview,
				new String[] { "img_edit_flag", "edit_name", "edit_num", "img_edit_top", "img_edit_move" },
				new int[] { R.id.img_edit_flag, R.id.edit_name, R.id.edit_num, R.id.img_edit_top, R.id.img_edit_move });
		listView.setAdapter(editAdapter);// 为listView设置适配器

		// 实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();// 找到被点击的Adapter
				Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);// 找到被点击的列表项
				if (Integer.valueOf(map.get("img_edit_flag").toString()) == R.drawable.edit_flag01) {
					edit_flag = R.drawable.edit_flag02;
				} else
					edit_flag = R.drawable.edit_flag01;

				list.get(position).put("img_edit_flag", edit_flag);// 将更新过的add_flag值放入list中
				adapter.notifyDataSetInvalidated();// 使更新过的list数据生效
			}
		});

	}

	class TestThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("id", util.getId());
				jArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectByUserId.do");
			// 不向服务器发送数据
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
					map.put("id", myjObject.getInt("id"));
					map.put("img_edit_flag", edit_flag);
					map.put("edit_name", myjObject.getString("name"));
					map.put("edit_num", myjObject.getString("type"));
					map.put("img_edit_top", R.drawable.edit_top);
					map.put("img_edit_move", R.drawable.edit_move);
					list.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					// 这里可以写上更新UI的代码
					editAdapter.notifyDataSetInvalidated();// 使更新过的list数据生效
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
				if (Integer.valueOf(list.get(i).get("img_edit_flag").toString()) == R.drawable.edit_flag02) {
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
			postUtil.setUrl("/share/share_deleteshareSelect.do");
			// 不向服务器发送数据
			postUtil.setRequest(jArray);
			// 从服务器获取数据
			postUtil.run();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(edit_zixuanActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}

			};
			handler.post(r);
		}
	}

}
