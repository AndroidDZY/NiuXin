package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeclarationQunzuselectActivity extends Activity {

	private Button backButton, allButton, saveButton;
	private ListView listView;
	public static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
	// private ArrayList<String> list;
	private List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
	private List<HashMap<String, Object>> beSelectedData = new ArrayList<HashMap<String, Object>>();
	private QunzuAdapter qunzuAdapter;
	GetSource getSource = new GetSource();
	private SharePreferenceUtil util = null;
	List<String> qunzuList = new ArrayList<String>();
	private int checkNum;
	MyAdapter adapter;
	private Handler handler = new Handler();
	List<String> oldlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.declaration_sendpurpose_qunzu);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		backButton = (Button) findViewById(R.id.declaration_sendpur_qunzuback);
		allButton = (Button) findViewById(R.id.declaration_sendpur_qunzuselectall);
		saveButton = (Button) findViewById(R.id.declaration_sendpur_qunzusave);
		listView = (ListView) findViewById(R.id.declaration_sendpurpose_qunzu_list);
		// list=getData();
		// 初始化isSelected的数据

		isSelected = new HashMap<Integer, Boolean>();
		adapter = new MyAdapter(this);// 创建一个适配器
		// qunzuAdapter = new QunzuAdapter(list,this);//创建一个适配器
		listView.setAdapter(adapter);
		// 返回
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (qunzuList != null) {
					Intent intent = new Intent();
					// intent.putStringArrayListExtra("qunzuList",
					// (ArrayList<String>) qunzuList);
					intent.setClass(DeclarationQunzuselectActivity.this, DeclarationDetailActivity.class);
					setResult(20, intent);
				}
				finish();
			}
		});
		// 保存监听
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (beSelectedData.size() != 0) {
					for (int i = 0; i < beSelectedData.size(); i++) {					
						String id = beSelectedData.get(i).get("id").toString();
						qunzuList.add(id);
					}
					MyApplication appQunzu = (MyApplication) getApplication();
					if (appQunzu.getQunzuList() != null) {
						appQunzu.getQunzuList().clear();
					}
					appQunzu.setQunzuList(qunzuList);
					if (appQunzu.getSendList() == null) {
						appQunzu.setSendList(qunzuList);
					} else {
						appQunzu.getSendList().addAll(qunzuList);
					}
				}
				Toast toast = Toast.makeText(DeclarationQunzuselectActivity.this, "保存成功", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		// 全选按钮的回调接口
		allButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 遍历list的长度，将MyAdapter中的map值全部设为true
				for (int i = 0; i < list.size(); i++) {
					isSelected.put(i, true);
				}
				adapter.notifyDataSetChanged();
				beSelectedData = list;
				// 数量设为list的长度
				checkNum = beSelectedData.size();
				// 刷新listview和TextView的显示
				adapter.notifyDataSetChanged();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				// 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
				ViewHolder holder = (ViewHolder) arg1.getTag();
				// 改变CheckBox的状态
				holder.cb.toggle();
				// 将CheckBox的选中状况记录下来
				isSelected.put(arg2, holder.cb.isChecked());
				if (holder.cb.isChecked()) {
					System.out.println(list.get(arg2));
					beSelectedData.add(list.get(arg2));
				}

				// 调整选定条目
				if (holder.cb.isChecked() == true) {
					checkNum++;
				} else {
					checkNum--;
				}
			}
		});
		TestThread t = new TestThread();
		t.start();
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;// 动态布局映射

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		// 决定ListView有几行可见
		@Override
		public int getCount() {
			if (list != null || list.size() != 0) {
				return list.size();// ListView的条目数
			} else {
				return 0;
			}
		}

		@Override
		public Object getItem(int arg0) {
			if (list != null) {
				return list.get(arg0);
			} else {
				return null;
			}
		}

		@Override
		public long getItemId(int argo) {
			return argo;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.listview_declaration_sendpurpose_haoyou, null);
				holder.im = (ImageView) convertView.findViewById(R.id.decla_haoyouitem_touxiang);
				holder.tv = (TextView) convertView.findViewById(R.id.decla_haoyouitem_name);
				holder.cb = (CheckBox) convertView.findViewById(R.id.haoyou_item_cb);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(list.get(position).get("qunzuname").toString());
			holder.im.setBackgroundResource(Integer.valueOf(list.get(position).get("touxiang").toString()));
			if (null != isSelected){
				try{
				//	if(!(isSelected.get(position)))
						holder.cb.setChecked(isSelected.get(position));
				}catch(Exception e){
					
				}
				
			}
			return convertView;
		}
	}

	// 获取数据
	private List<HashMap<String, Object>> getData() {
		// 新建一个集合类，用于存放多条数据 从数据库中获取数据
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = null;
		for (int i = 1; i <= 3; i++) {
			map = new HashMap<String, Object>();
			map.put("touxiang", R.drawable.detail_content_touxiang); // r.drawable
			map.put("qunzuname", "群组" + i);
			map.put("id", i);
			list.add(map);
		}

		return list;
	}

	public static class ViewHolder {
		TextView tv;
		ImageView im;
		CheckBox cb;
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
			// 向服务器发送数据
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
			list.clear();
			int j = 0;
			MyApplication ap = (MyApplication) getApplication();
			oldlist = ap.getQunzuList();
			isSelected.clear();
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					HashMap<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					String title = myjObject.getString("name");
					String img = myjObject.getString("img");
					Integer chattype = myjObject.getInt("chattype");

					map.put("touxiang", getSource.getResourceByReflect(img)); // r.drawable
					map.put("qunzuname", title);
					map.put("id", id);
					map.put("chattype", chattype);
					if (chattype == 1) {
						list.add(map);

						if (oldlist != null) {
							for (int m = 0; m < oldlist.size(); m++) {
								Integer idold = Integer.valueOf(oldlist.get(m));
								if (idold == id) {
									isSelected.put(j, true);
									break;
								} else {
									isSelected.put(j, false);
								}
							}
						} else{
							isSelected.put(j, false);
						}
						j++;
					}
					
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
