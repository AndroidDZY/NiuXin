package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.niuxin.zixuan_addActivity.TestThread;
import com.niuxin.util.Constants;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TongxunluActivity extends Activity {
	ListView listView, listView_u;
	private Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	List<Map<String, Object>> list_friend_qun = new ArrayList<Map<String, Object>>();
	SimpleAdapter tongxunAdapter = null;
	SimpleAdapter zixuanAdapter = null;
	 GetSource getSource = new GetSource();
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
		// 719修改
		setContentView(R.layout.tongxunlu);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		// 获取第一个ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用zixuanlistview.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用zixuanlistview.xml文件中的哪些组件来填充列表项
		listView = (ListView) findViewById(R.id.zixuanlist);
		zixuanAdapter = new SimpleAdapter(this, getData(), R.layout.zixuanlistview, new String[] { "image", "title" },
				new int[] { R.id.image, R.id.title });
		listView.setAdapter(zixuanAdapter);

		// 根据点击不同item跳转到自选股添加界面、公众号、查找用户及群组界面
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0: // 点击第1个item
					startActivity(new Intent(TongxunluActivity.this, zixuan_addActivity.class));
					break;
				case 1: // 点击第2个item
					break;
				case 2: // 点击第3个item
					startActivity(new Intent(TongxunluActivity.this, SearchuserActivity.class));
					break;
				}
			}

		});

		// 获取第二个ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用tongxunlulistview.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用tongxunlulistview.xml文件中的哪些组件来填充列表项
		listView_u = (ListView) findViewById(R.id.tonglist);
		tongxunAdapter = new SimpleAdapter(this, list_friend_qun, R.layout.tongxunlulistview,
				new String[] { "image", "title" }, new int[] { R.id.image_u, R.id.title_u });
		listView_u.setAdapter(tongxunAdapter);

		// 跳转到聊天界面
		listView_u.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent();
				intent.putExtra("group_friend_type", list_friend_qun.get(position).get("chattype").toString());// 类型																							// 类型2代表个人聊天
				intent.putExtra("group_friend_id", list_friend_qun.get(position).get("id").toString());// 群id																										// 或者将要接收信息的人的id
				intent.putExtra("group_friend_name", list_friend_qun.get(position).get("title").toString());// 群名																											// 或者将要接受消息人的名字
				intent.setClass(TongxunluActivity.this, ChatActivity.class);
				startActivity(intent);
			}
		});
	}

	class TestThread extends Thread {
		private Dialog mDialog = null;

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			JSONObject jsonObject = new JSONObject();
			try {
				
				Integer id = util.getId();			
				jsonObject.put("id", id);			
			} catch (JSONException e) {
				e.printStackTrace();
			}		

			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/group/group_listTongxunlu.do");
			// 不向服务器发送数据
			//向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);

			// 从服务器获取数据
			String res = postUtil.run();
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list_friend_qun.clear();
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					String title = myjObject.getString("name");
					String img = myjObject.getString("img");
					Integer chattype = myjObject.getInt("chattype");
					map.put("id", id);
					map.put("title", title);
					map.put("image", getSource.getResourceByReflect(img));										
					map.put("chattype", chattype);

					list_friend_qun.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					tongxunAdapter.notifyDataSetChanged();

				}

			};
			handler.post(r);
		}
	}

	// 这里的数据不需要从数据库读//////////////////////////////////////
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("image", R.drawable.tongxunlu01);
		map.put("title", "添加自选股");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("image", R.drawable.tongxunlu02);
		map.put("title", "公众号");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("image", R.drawable.tongxunlu03);
		map.put("title", "查找用户及群组");
		list.add(map);

		return list;
	}
	
	// 手机back按键事件处理
/*	private long mExitTime = 0;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("KeyBack", "KeyBack");
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "再按一次退出",
						Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
//				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}*/

}
