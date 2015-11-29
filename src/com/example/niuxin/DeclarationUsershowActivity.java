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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DeclarationUsershowActivity extends Activity{
	
	private ListView listView;
	private List<HashMap<String, Object>> mData = new LinkedList<HashMap<String, Object>>();  
	MyAdapter adapter = null;
	private Handler handler = new Handler();
	GetSource getSource = new GetSource();
	private SharePreferenceUtil util = null;
	String intentuserlist="";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_targetshow_haoyou);
		//mainActivity=new MainActivity();
		//初始化
		//mData=getData();
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		listView=(ListView)findViewById(R.id.declaration_targetshow_haoyou_list);
		 adapter = new MyAdapter(this);//创建一个适配器  
		listView.setAdapter(adapter);
		
		//事件监听
		Intent in = getIntent();
		intentuserlist = in.getStringExtra("intentuser");
		
		if(null!=intentuserlist&&(!"".equals(intentuserlist))){
			TestThread t = new TestThread();
			t.start();
		}
		
	}
	//适配器
	public class MyAdapter extends BaseAdapter {  
        private LayoutInflater mInflater;// 动态布局映射  
  
        public MyAdapter(Context context) {  
            this.mInflater = LayoutInflater.from(context);  
        }  
  
        // 决定ListView有几行可见  
        @Override  
        public int getCount() {  
        	if(null!=mData)
        		return mData.size();// ListView的条目数  
        	else 
        		return 0;
        }  
  
        @Override  
        public Object getItem(int arg0) {  
            return null;  
        }  
  
        @Override  
        public long getItemId(int arg0) {  
            return 0;  
        }  
  //获取listview视图对象 
        @Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
        	convertView = mInflater.inflate(R.layout.listview_declaration_targetshow_haoyou, null);//根据布局文件实例化view 
        	//名称
        	TextView nameText=(TextView) convertView.findViewById(R.id.declatarget_haoyouitem_name);
        	nameText.setText(mData.get(position).get("nameText").toString());
        	ImageView touxiang=(ImageView) convertView.findViewById(R.id.declatarget_haoyouitem_touxiang);
        	touxiang.setBackgroundResource(Integer.valueOf(mData.get(position).get("touxiang").toString()));
        	return convertView;
		}  
    }
	//获取数据
	private List<HashMap<String, Object>> getData() {  
        // 新建一个集合类，用于存放多条数据  从数据库中获取数据
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
        HashMap<String, Object> map = null;  
        for (int i = 1; i <= 3; i++) {  
            map = new HashMap<String, Object>();  
            map.put("nameText", "好友"+i ); //r.drawable 
            map.put("touxiang", R.drawable.detail_content_touxiang);  
             list.add(map);  
        }  
        return list;  
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
			if(null!=mData)
				mData.clear();
			String[] sts = intentuserlist.split(",");
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
					map.put("nameText", title);
					map.put("id", id);
					if(null!=sts){
						for(String s:sts){
							if (chattype == 2&&(Integer.valueOf(s)==id)) {
								mData.add(map);						
							}
						}
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
