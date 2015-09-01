package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchresultActivity extends Activity {
	private LinearLayout layout1, layout2, layout3;
	ListView listView_contacts, listView_chatlog, listView_collect;
	private Button mButton, mmButton;
	// 1
	private SuoluetuActivity suolue;
	private SharePreferenceUtil util = null;
	private EditText sr_edittext;
	String searchtext = null;
	public Handler handler = new Handler();
	SimpleAdapter contactsAdapter = null;
	SimpleAdapter chatlogAdapter = null;
	SimpleAdapter collectAdapter = null;
	List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
	List<Map<String, Object>> list3 = new ArrayList<Map<String, Object>>();

	public void init() {
		suolue = new SuoluetuActivity(this, handler);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		// 获取控件
		mButton = (Button) findViewById(R.id.sr_searchcancel);
		mmButton = (Button) findViewById(R.id.sr_search);
		sr_edittext = (EditText) findViewById(R.id.sr_edittext);
		// 获取控件与监听
		layout1 = (LinearLayout) findViewById(R.id.layout1);
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		layout3 = (LinearLayout) findViewById(R.id.layout3);

		// 点击事件-取消搜索
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 点击事件-完成搜索
		mmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				searchtext = sr_edittext.getText().toString();
				if (searchtext == null || searchtext.trim() == null) {
					Toast.makeText(getApplicationContext(), "搜索条件不能为空!!!", 0).show();
					return;
				}

				ContactsThread t = new ContactsThread();
				t.start();
				ChatRecoedThread t1 = new ChatRecoedThread();
				t1.start();
			}
		});

		layout1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stu
				if(list1.size()==0){
					return;
				}
				
			//	Intent intent = new Intent(SearchresultActivity.this, Contacts_detailedActivity.class);
				Intent intent=new Intent();
				JSONArray js = new JSONArray();
				
				for (int i = 0; i < list1.size(); i++) {
					JSONObject jb = new JSONObject();
					try {
						jb.put("id", list1.get(i).get("id"));
						jb.put("title_contacts", list1.get(i).get("title_contacts"));
						jb.put("image_contacts", list1.get(i).get("image_contacts"));
						jb.put("chattype", list1.get(i).get("chattype"));
						
					} catch (JSONException e) {
						e.printStackTrace();
					}					
					js.put(jb);
										
				}

				intent.putExtra("list", js.toString());//
				intent.setClass(SearchresultActivity.this, Contacts_detailedActivity.class);
				startActivity(intent);
			}
		});

		layout2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	Intent intent = new Intent(SearchresultActivity.this, Chatlog_detailedActivity.class);
				//startActivity(intent);
				
				
				
				if(list2.size()==0){
					return;
				}
				
			//	Intent intent = new Intent(SearchresultActivity.this, Contacts_detailedActivity.class);
				Intent intent=new Intent();
				JSONArray js = new JSONArray();
				
				for (int i = 0; i < list2.size(); i++) {
					JSONObject jb = new JSONObject();
					try {
						jb.put("id", list2.get(i).get("id"));
						jb.put("title_chatlog", list2.get(i).get("title_chatlog"));
						jb.put("image_chatlog", list2.get(i).get("image_chatlog"));
						jb.put("chattype", list2.get(i).get("chattype"));
						jb.put("content_chatlog", list2.get(i).get("content_chatlog"));
				
						
					} catch (JSONException e) {
						e.printStackTrace();
					}					
					js.put(jb);
										
				}

				intent.putExtra("list", js.toString());//
				intent.setClass(SearchresultActivity.this, Chatlog_detailedActivity.class);
				startActivity(intent);
			}
		});

		layout3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchresultActivity.this, Collect_detailedActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.searchresult);

		init();
		// 2

		// 获取第一个ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用contacts_list.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用contacts_list.xml文件中的哪些组件来填充列表项
		listView_contacts = (ListView) findViewById(R.id.contacts_list);
		contactsAdapter = new SimpleAdapter(this, list1, R.layout.contacts_list,
				new String[] { "image_contacts", "title_contacts" },
				new int[] { R.id.image_contacts, R.id.title_contacts });
		listView_contacts.setAdapter(contactsAdapter);

		listView_contacts.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Intent intent = new Intent();
				intent.putExtra("group_friend_type", list1.get(position).get("chattype").toString());
				intent.putExtra("group_friend_id", list1.get(position).get("id").toString());
				intent.putExtra("group_friend_name", list1.get(position).get("title_contacts").toString());// 群名
				// 或者将要接受消息人的名字
				intent.setClass(SearchresultActivity.this, ChatActivity.class);
				startActivity(intent);
			}
		});

		// 获取第二个ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用chatlog_list.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用chatlog_list.xml文件中的哪些组件来填充列表项
		listView_chatlog = (ListView) findViewById(R.id.chatlog_list);
		 chatlogAdapter = new SimpleAdapter(this, list2, R.layout.chatlog_list,
				new String[] { "image_chatlog", "title_chatlog", "content_chatlog" },
				new int[] { R.id.image_chatlog, R.id.title_chatlog, R.id.content_chatlog });
		listView_chatlog.setAdapter(chatlogAdapter);

		// 获取第三个ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用collect_list.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用collect_list.xml文件中的哪些组件来填充列表项
		listView_collect = (ListView) findViewById(R.id.collect_list);
		collectAdapter = new SimpleAdapter(this, getData_collect(), R.layout.collect_list,
				new String[] { "image_collect", "title_collect", "content_collect" },
				new int[] { R.id.image_collect, R.id.title_collect, R.id.content_collect });
		listView_collect.setAdapter(collectAdapter);
	}


	// 聊天记录的数据
	private List<Map<String, Object>> getData_chatlog() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("image_chatlog", R.drawable.head004);
		map.put("title_chatlog", "汪总");
		map.put("content_chatlog", "今天又要涨停了");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("image_chatlog", R.drawable.head005);
		map.put("title_chatlog", "中信证券讨论组");
		map.put("content_chatlog", "一路飘红啊");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("image_chatlog", R.drawable.head006);
		map.put("title_chatlog", "海螺水泥群组");
		map.put("content_chatlog", "海螺水泥是一只自选股");
		list.add(map);

		return list;
	}

	// 收藏的数据
	private List<Map<String, Object>> getData_collect() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("image_collect", R.drawable.head007);
		map.put("title_collect", "军工板块暴涨近7%领涨两市");
		map.put("content_collect", "周五军工股大涨近7%，领涨两市，截至发稿，际华集团、拓尔思、长春一东、中航...");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("image_collect", R.drawable.head008);
		map.put("title_collect", "人社部：约2万亿元养老金可投资");
		map.put("content_collect", "周五上午10时，人力资源社会保障部副部长和财政部副部...");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("image_collect", R.drawable.head009);
		map.put("title_collect", "海螺水泥群组");
		map.put("content_collect", "海螺水泥是一只自选股");
		list.add(map);

		return list;
	}

	class 	ContactsThread extends Thread {

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			JSONObject jsonObject = new JSONObject();
			try {

				Integer id = util.getId();
				jsonObject.put("id", id);
				jsonObject.put("searchTest", searchtext);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/search/search_contacts.do");
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
			list1.clear();
		//	layout1.setVisibility(View.GONE);
			for (int i = 0; i < jsonArray.length(); i++) {
				if (i == 3)
					break;
		//		layout1.setVisibility(View.VISIBLE);
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					String title = myjObject.getString("name");
					Integer img = myjObject.getInt("img");
					Integer chattype = myjObject.getInt("chattype");
				
					map.put("id", id);
					map.put("title_contacts", title);
					map.put("image_contacts", img);
					map.put("chattype", chattype);
					
					list1.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					contactsAdapter.notifyDataSetChanged();

				}

			};
			handler.post(r);
		}
	}

	
	
	class ChatRecoedThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				
				Integer id = util.getId();			
				jsonObject.put("id", id);	
				jsonObject.put("searchTest", searchtext);	
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
			postUtil.setUrl("/search/search_listChatRecordBySearchText.do");
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
				return;
			}	
			list2.clear();

			for (int i = 0; i < jsonArray.length(); i++) {				
				try {
					if (i == 3)
						break;
					
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象					
					String chattype = myjObject.getString("chattype");
					// 获取每一个对象中的值
			
						HashMap<String, Object> map = new HashMap<String, Object>();
						//String chattype = myjObject.getString("chattype");
						int id = myjObject.getInt("id");
						String name = myjObject.getString("name");
						String lastmes = myjObject.getString("lastmes");
						String time = myjObject.getString("time");
						Integer img = myjObject.getInt("img");						
						map.put("image_chatlog", img);//R.drawable.head010
						map.put("id", id);
						map.put("title_chatlog", name);
						map.put("chattype", chattype);
						map.put("content_chatlog", lastmes);						
						list2.add(map);
					
				}catch (JSONException e) {
					e.printStackTrace();
				}
			}/////////////////////////////解析数据完成
			
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					chatlogAdapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
		}
	}
	
}
