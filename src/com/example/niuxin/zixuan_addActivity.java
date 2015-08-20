
package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.DialogFactory;
import com.niuxin.util.HttpPostUtil;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;
import android.app.Dialog;


public class zixuan_addActivity extends Activity {
	private int add_flag = R.drawable.add_flag01;
	private Handler handler = new Handler();
	Button add_cancle, add_finish;
	ListView listView;
	SimpleAdapter addAdapter;
	public static Activity act = null;
	// 实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	EditText gupiao;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		act = this;
		setContentView(R.layout.zixuan_add);// 设置zixuan_addActivity采用zixuan_add.xml布局文件进行布局

		// 获取EditText对象
		gupiao = (EditText) findViewById(R.id.et_gupiao);
		// 获取Button对象
		add_cancle = (Button) findViewById(R.id.add_cancle);
		add_finish = (Button) findViewById(R.id.add_finish);
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
		listView = (ListView) findViewById(R.id.addlist);// 获取ListView
		
		//准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。
		TestThread thread = new TestThread();
		thread.start();

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
			HttpPostUtil postUtil = new HttpPostUtil(handler);
			/*
			// 向服务器发送数据，如果没有，可以不发送
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
			postUtil.setRequest(jsonObject);
			*/
			/*
			boolean isNetwork= postUtil.checkNetState(act);
			if(!isNetwork){
				mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
				mDialog.show();
				return;
			}*/
			
			//设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectAll.do");
			//不向服务器发送数据
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
			for (int i = 0; i < jsonArray.length(); i++) {				
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					int number = myjObject.getInt("number");
					String name = myjObject.getString("name");
					map.put("id", id);
					map.put("number", number);
					map.put("name", name);
					map.put("add_flag", add_flag);
					list.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}/////////////////////////////解析数据完成
			
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					// 这里可以写上更新UI的代码
					addAdapter = new SimpleAdapter(zixuan_addActivity.this, list, R.layout.addlistview,
							new String[] { "name", "num", "add_flag" },
							new int[] { R.id.name, R.id.num, R.id.add_flag });
					listView.setAdapter(addAdapter);// 为listView设置适配器
				}

			};
			handler.post(r);
		}
	}


}

