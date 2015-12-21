package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchFriendActivity extends Activity{
	private int flag_sfriend = R.drawable.add_flag01;
	private Button sButton,fButton,cButton;
	ListView listView_sfriend;
	//1
    private SuoluetuActivity suolue;
    public Handler handler = new Handler();
    SharePreferenceUtil util;
    List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
    SimpleAdapter searchfriendAdapter = null;
    private EditText text;
    String searchtext = null;
    private int mark = 0;
    GetSource getSource = new GetSource();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchfriend);
		util = new SharePreferenceUtil(SearchFriendActivity.this, Constants.SAVE_USER);
		//2
		suolue = new SuoluetuActivity(this,handler);
		
		//获取控件及点击事件
		sButton = (Button)findViewById(R.id.btn_sf_search);
		cButton = (Button)findViewById(R.id.btn_sfriend_cancle);
		fButton = (Button)findViewById(R.id.btn_sfriend_finish);
		text = (EditText) findViewById(R.id.sf_edittext);
		//查找按钮点击事件
		sButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				 searchtext = text.getText().toString(); 
				if (searchtext == null || searchtext.trim() == null) {
					Toast.makeText(getApplicationContext(), "搜索条件不能为空!!!", 0).show();
					return;
				}
				SerarchFriendThread s = new SerarchFriendThread();
				s.start();
			}
		});
		
		//取消按钮点击事件
		cButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		//添加好友完成按钮点击事件
		fButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {				
				if(mark==1){
						return;
				}
				mark = 1;

				PostThread po = new PostThread();
				po.start();
			}
		});
		
		//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用searchfriend_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用searchfriend_list.xml文件中的哪些组件来填充列表项
		listView_sfriend=(ListView)findViewById(R.id.searchfriend_list);
		 searchfriendAdapter= new SimpleAdapter(this, list,R.layout.searchfriend_list, 
				new String[]{"image_sfriend","title_sfriend","flag_sfriend"},
				new int[]{R.id.image_sfriend,R.id.title_sfriend,R.id.flag_sfriend});
		listView_sfriend.setAdapter(searchfriendAdapter);
		
		// 实现点击不同的item，奇数偶数次点击更换imageview显示
		listView_sfriend.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SimpleAdapter adapter = (SimpleAdapter) parent.getAdapter();// 找到被点击的Adapter
				Map<String, Object> map = (Map<String, Object>) adapter.getItem(position);// 找到被点击的列表项
				if (Integer.valueOf(map.get("flag_sfriend").toString()) == R.drawable.add_flag01) {
					flag_sfriend = R.drawable.add_flag02;
				} else
					flag_sfriend = R.drawable.add_flag01;
				map.put("flag_sfriend", flag_sfriend);
				adapter.notifyDataSetInvalidated();// 使更新过的list数据生效
			}
		});
	}
	
	
	class SerarchFriendThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				
				Integer id = util.getId();			
				jsonObject.put("id", id);	
				jsonObject.put("searchText", searchtext);	
				
			} catch (JSONException e) {
				e.printStackTrace();
			}			
			/*
			boolean isNetwork= postUtil.checkNetState(act);
			if(!isNetwork){
				mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
				mDialog.show();
				return;
			}*/
			
			//设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/search/search_serachUser.do");
			//向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);
			// 从服务器获取数据
			String res = postUtil.run();	
			if(res==null){
				return;
			}
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;			
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
			list.clear();
			if(jsonArray!=null)
			for (int i = 0; i < jsonArray.length(); i++) {				
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();				
						int id = myjObject.getInt("id");
						String name = myjObject.getString("name");
						String img = myjObject.getString("img");			
						map.put("id", id);
						map.put("image_sfriend", getSource.getResourceByReflect(img));
						map.put("title_sfriend", name);
						map.put("flag_sfriend", flag_sfriend);
						list.add(map);				
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}/////////////////////////////解析数据完成
			
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					searchfriendAdapter.notifyDataSetChanged();
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
				if (Integer.valueOf(list.get(i).get("flag_sfriend").toString()) == R.drawable.add_flag02) {
					JSONObject jsonObject = new JSONObject();
					try {
						
						jsonObject.put("friendid", Integer.valueOf(list.get(i).get("id").toString()));
						jsonObject.put("selfid", util.getId());
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
			postUtil.setUrl("/user/action_insertFriend.do");
			// 不向服务器发送数据
			postUtil.setRequest(jArray);
			// 从服务器获取数据
			postUtil.run();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(SearchFriendActivity.this, MainActivity.class);
					startActivity(intent);
					mark = 0;
					finish();
				}

			};
			handler.post(r);
		}
	}
	
	
}