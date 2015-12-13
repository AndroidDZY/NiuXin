package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.annotation.SuppressLint;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeclarationUserselectActivity extends Activity {
	private Button backButton, allButton, saveButton;
	private ListView listView;
	// private ArrayList<String> list;
	private List<HashMap<String, Object>> list = new LinkedList<HashMap<String, Object>>();
	private HaoyouAdapter haoyouAdapter;
	public static HashMap<Integer, Boolean> isSelected = new HashMap<Integer, Boolean>();
	private Handler handler = new Handler();
	EditText text = null;
	Set<Integer> haoyouList = new HashSet<Integer>();
	GetSource getSource = new GetSource();
	private SharePreferenceUtil util = null;
	MyAdapter adapter;
	Set<Integer> oldlist = null;
	MyApplication appHaoyou = null;

	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.declaration_sendpurpose_haoyou);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		backButton = (Button) findViewById(R.id.declaration_sendpur_haoyouback);
		allButton = (Button) findViewById(R.id.declaration_sendpur_haoyouselectall);
		saveButton = (Button) findViewById(R.id.declaration_sendpur_haoyousave);
		listView = (ListView) findViewById(R.id.declaration_sendpurpose_haoyou_list);		
		adapter = new MyAdapter(this);// 创建一个适配器
		listView.setAdapter(adapter);
		appHaoyou = (MyApplication) getApplication();
		//haoyouList = appHaoyou.getHaoyouList();
		oldlist = appHaoyou.getHaoyouList();	
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {	
				
				if(!appHaoyou.getIsSave()){//如果状态没设置成true 就清空
					appHaoyou.getQunzuList().clear();
					appHaoyou.getHaoyouList().clear();
				}
				appHaoyou.setIsSave(false);
				
				Intent intent = new Intent();					
				intent.setClass(DeclarationUserselectActivity.this, DeclarationDetailActivity.class);
				setResult(20,intent);
			
				finish();
			}
		});
		// 全选按钮的回调接口
		allButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {			
				if(null!=haoyouList){
					haoyouList.clear();
					appHaoyou.setHaoyouList(haoyouList);
				}
				for(int i=0;i<list.size();i++){
					if (Integer.valueOf(list.get(i).get("chattype").toString()) == 2){
						haoyouList.add(Integer.valueOf(list.get(i).get("id").toString()));
						appHaoyou.setHaoyouList(haoyouList);
						isSelected.put(i, true);
					}
				}
				appHaoyou.setIsSave(false);
				adapter.notifyDataSetChanged();
			}
		});
		// 保存按钮，在保存按钮中把数据传到全局变量中
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {						
				appHaoyou.setIsSave(true);
				
				if(!appHaoyou.getIsSave()){//如果状态没设置成true 就清空
					appHaoyou.getQunzuList().clear();
					appHaoyou.getHaoyouList().clear();
				}
				appHaoyou.setIsSave(false);
				
				Intent intent = new Intent();					
				intent.setClass(DeclarationUserselectActivity.this, DeclarationDetailActivity.class);
				setResult(20,intent);
			
				finish();
				
			//	Toast toast = Toast.makeText(DeclarationUserselectActivity.this, "保存成功", Toast.LENGTH_SHORT);
			//	toast.show();
			}
		});
		// listview item点击监听
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {				
				// 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
				ViewHolder holder = (ViewHolder) arg1.getTag();
				// 改变CheckBox的状态
				holder.cb.toggle();
				// 将CheckBox的选中状况记录下来
				isSelected.put(arg2, holder.cb.isChecked());
				adapter.notifyDataSetChanged();
				// 调整选定条目
				if (holder.cb.isChecked()) {					
					haoyouList.add(Integer.valueOf((String)list.get(arg2).get("id").toString()));
					appHaoyou.setHaoyouList(haoyouList);
				}else{
					haoyouList.remove(Integer.valueOf((String)list.get(arg2).get("id").toString()));
					appHaoyou.setHaoyouList(haoyouList);
				}
				
				appHaoyou.setIsSave(false);
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
			holder.tv.setText(list.get(position).get("username").toString());
			holder.im.setBackgroundResource(Integer.valueOf(list.get(position).get("touxiang").toString()));
			if (null != isSelected){
				try{				
					holder.cb.setChecked(isSelected.get(position));
				}catch(Exception e){
					
				}
			}
				
			return convertView;
		}
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
			int j =0;
			
			 
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
					map.put("username", title);
					map.put("id", id);
					map.put("chattype", chattype);
					
					if (chattype == 2) {
						list.add(map);	
						isSelected.put(j, false);
						if (oldlist!=null) {
							Iterator<Integer> oldlistiter = oldlist.iterator();
							while (oldlistiter.hasNext()) {							
								Integer haoyouId= oldlistiter.next();  
								if (haoyouId==id) {
									isSelected.put(j, true);
									haoyouList.add(id);
									appHaoyou.setHaoyouList(haoyouList);
									appHaoyou.setIsSave(true);
									break;
								}
							}
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
