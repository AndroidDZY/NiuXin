package com.example.niuxin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.niuxin.LiaotianActivity.GroupThread;
import com.niuxin.bean.ChatMsgEntity;
import com.niuxin.client.Client;
import com.niuxin.client.ClientOutputThread;
import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.MessageDB;
import com.niuxin.util.MyDate;
import com.niuxin.util.SharePreferenceUtil;
import com.niuxin.util.TextMessage;
import com.niuxin.util.TranObject;
import com.niuxin.util.TranObjectType;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class ChatActivity extends MyActivity implements OnClickListener {

	PopupMenu popupMenu;
	Menu menu;
	private Button mBtnSend, mButtonBiaoqing, mButtonBack, mButtonMore;
	private ImageButton btn_collect, btn_share;
	private EditText mEditText;
	private ListView mListView;
	private LinearLayout layout_more;
	private int count = 0;
	private SuoluetuActivity suolue;
	private TextView groupNameText;
	LinearLayout layout;
	// private User user;
	private MessageDB messageDB;
	private MyApplication application;
	// 定义适配器
	private ChatMsgViewAdapter mAdapter;
	private SharePreferenceUtil util =null;
	String groupId = null;//群组Id
	String groupName = null;//群组名称
	String sendtoUserId = null;
	String sendtoUserName = null;
	private Handler handler = new Handler();
	public static Activity act = null;
	
	// 聊天数据
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();
	
	@Override  
	protected void onResume() {
		 super.onResume(); 
		 suolue = new SuoluetuActivity(this);
		 
		 Intent intent = getIntent();		 	
		groupId = intent.getStringExtra("groupid");// 获取传过来的群组ID
		groupName = intent.getStringExtra("name");// 如果是群聊 就获取传过来的群组ID
		sendtoUserId = intent.getStringExtra("sendtoUserId");											
		sendtoUserName = intent.getStringExtra("sendtoUserName");
			// 获取用户，还没有验证是否可行
		messageDB = new MessageDB(this);
		initData();

		if(groupName!=null){
			groupNameText.setText(groupName);// 设置聊天的标题 如果是群聊 就设置成群名称
		}else
			groupNameText.setText(sendtoUserName);//如果是个人聊天，就设置成对方的用户名
		
	}
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.chat);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		initView();
		
		layout = (LinearLayout) findViewById(R.id.linear);
		
		final Button button = new Button(ChatActivity.this);
		button.setBackgroundResource(R.drawable.chat_audio);
		layout.addView(button);
		mEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				if (arg0.length() <= 0) {
					button.setBackgroundResource(R.drawable.chat_audio);
				} else {
					button.setBackgroundResource(R.drawable.chatsend);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}
		});

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				send();
			}
		});
	}

	// 初始化视图
	private void initView() {
		// TODO Auto-generated method stub
		// 获取各种控件
		mListView = (ListView) findViewById(R.id.listview);
		mButtonBack = (Button) findViewById(R.id.btn_back);
		// mBtnSend = (Button) findViewById(R.id.btn_send);
		mButtonBiaoqing = (Button) findViewById(R.id.emotion);
		mButtonMore = (Button) findViewById(R.id.gengduo);
		mEditText = (EditText) findViewById(R.id.sendmessage);
		layout_more = (LinearLayout) findViewById(R.id.layout_more);
		btn_collect = (ImageButton) findViewById(R.id.btn_collect);
		btn_share = (ImageButton) findViewById(R.id.btn_share);

		groupNameText = (TextView) findViewById(R.id.groupName);
		mButtonBack.setOnClickListener(this);
		// mBtnSend.setOnClickListener(this);
		mButtonMore.setOnClickListener(this);
		btn_collect.setOnClickListener(this);
		btn_share.setOnClickListener(this);
		// 设置EditText光标位置
		// mEditText.setSelection(5);
		mAdapter = new ChatMsgViewAdapter(ChatActivity.this, mDataArrays);
		mListView.setAdapter(mAdapter);
	}

	// 初始化数据
	private void initData() {
		InitDataThread thread = new InitDataThread();
		thread.start();
	}

	// 定义按钮事件
	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_back:
			back();
			break;
		// case R.id.linear:
		// send();
		// break;
		case R.id.gengduo:
			more();
			break;
		case R.id.btn_collect:
			collect();
			break;
		case R.id.btn_share:
			share();
			break;
		}
	}

	// 展开分享
	private void share() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(ChatActivity.this, ShareActivity.class);
		startActivity(intent);
	}

	// 展开收藏
	@SuppressLint("NewApi")
	private void collect() {
		// TODO Auto-generated method stub
		// 创建PopupMenu对象，按钮btn_collect为触发该弹出菜单的组件
		popupMenu = new PopupMenu(this, btn_collect);
		// 通过XML文件将R.menu.popupmenu资源填充到popup菜单中
		getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());
		// 监听事件
		popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.check:
					// 跳转到已收藏界面
					Intent intent = new Intent(ChatActivity.this, CollectActivity.class);
					startActivity(intent);
					break;
				case R.id.batch:
					// 暂无
					break;
				case R.id.manage:
					// 跳转到标签管理界面
					Intent intent1 = new Intent(ChatActivity.this, Tag_ManageActivity.class);
					startActivity(intent1);
					break;
				case R.id.back:
					// 隐藏该菜单
					popupMenu.dismiss();
					break;
				default:
					break;
				}
				return false;
			}
		});
		popupMenu.show();
	}

	// 展开更多
	private void more() {
		// TODO Auto-generated method stub
		count++;
		if (count % 2 == 1) {
			layout_more.setVisibility(View.VISIBLE);// 显示more
		} else {
			layout_more.setVisibility(View.GONE);// 隐藏more
		}
	}

	// 发送消息
	private void send() {
		// TODO Auto-generated method stub
		String contString = mEditText.getText().toString();
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			entity.setDate(getDate());
			entity.setName("");
			entity.setMsgType(false);
			entity.setText(contString);
			mDataArrays.add(entity);
			mAdapter.notifyDataSetChanged();
			mEditText.setText("");
			mListView.setSelection(mListView.getCount() - 1);

			MyApplication application = (MyApplication) this.getApplicationContext();
			Client client = application.getClient();
			ClientOutputThread out = client.getClientOutputThread();
			if (out != null) {
				TranObject<TextMessage> o = new TranObject<TextMessage>(TranObjectType.MESSAGE);
				TextMessage message = new TextMessage();
				message.setMessage(contString);
				o.setObject(message);
				o.setFromUser(util.getId());// 谁发送的
				if(groupId!=null){
					o.setIstoGroup(1);
					o.setToUser(Integer.valueOf(groupId));// 谁接收的 这边发送给群id
				}else{
					o.setIstoGroup(0);
					o.setToUser(Integer.valueOf(sendtoUserId));//  这边发送给用户id	
				}								
				out.setMsg(o);
			}
		}

	}

	private void back() {
		finish();// 返回结束事件
	}

	// 获取日期
	private String getDate() {
		Calendar c = Calendar.getInstance();
		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));
		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);
		return sbBuffer.toString();
	}

	@Override
	public void getMessage(TranObject msg) {

		// TODO Auto-generated method stub
		switch (msg.getType()) {
		case MESSAGE:
			TextMessage tm = (TextMessage) msg.getObject();
			String message = tm.getMessage();
			ChatMsgEntity entity = new ChatMsgEntity(util.getUserName(), MyDate.getDateEN(), message, util.getImg(),
					true);// 收到的消息
			if (msg.getFromUser() == util.getId() || msg.getFromUser() == 0) {// 如果是正在聊天的好友的消息，或者是服务器的消息

				messageDB.saveMsg(util.getId(), entity);

				mDataArrays.add(entity);
				mAdapter.notifyDataSetChanged();
				mListView.setSelection(mListView.getCount() - 1);
				MediaPlayer.create(this, R.raw.msg).start();
			} else {
				messageDB.saveMsg(msg.getFromUser(), entity);// 保存到数据库
				Toast.makeText(ChatActivity.this, "您有新的消息来自：" + msg.getFromUser() + ":" + message, 0).show();// 其他好友的消息，就先提示，并保存到数据库
				MediaPlayer.create(this, R.raw.msg).start();
			}
			break;
		/*
		 * case LOGIN: User loginUser = (User) msg.getObject();
		 * Toast.makeText(ChatActivity.this, loginUser.getId() + "上线了", 0)
		 * .show(); MediaPlayer.create(this, R.raw.msg).start(); break; case
		 * LOGOUT: User logoutUser = (User) msg.getObject();
		 * Toast.makeText(ChatActivity.this, logoutUser.getId() + "下线了", 0)
		 * .show(); MediaPlayer.create(this, R.raw.msg).start(); break;
		 */
		default:
			break;
		}

	}

	class InitDataThread extends Thread {
		private Dialog mDialog = null;

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil(handler);
			
			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("sendUserId", util.getId());//发送者的ID
				if(groupId!=null){
					jsonObject.put("groupId", groupId);	
					jsonObject.put("sendtoUserId", "-1");
				}else{
					jsonObject.put("sendtoUserId", sendtoUserId);	
					jsonObject.put("groupId", "-1");
				}
					
						
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
			postUtil.setUrl("/chatrecord/chatrecord_select.do");
			//不向服务器发送数据
			postUtil.setRequest(jsonObject);
			
			// 从服务器获取数据
			String res = postUtil.run();			
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;			
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
			
			
			try {
				if(jsonArray.getJSONObject(0).getString("hasdata").equals("nodata")){
					return;
				}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			mDataArrays.clear();
			for (int i = 0; i < jsonArray.length(); i++) {				
				try {
					ChatMsgEntity entity = new ChatMsgEntity();
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					int senduserid = myjObject.getInt("senduserid");
					String sendUsername = myjObject.getString("sendUsername");
			//		int receiveuserid = myjObject.getInt("receiveuserid");
			//		int receivegroupid = myjObject.getInt("receivegroupid");
					String message = myjObject.getString("message");
			//		String createtime =  myjObject.getString("createtime");	
					entity.setName(sendUsername);
					if (senduserid == util.getId()) {//判断发送的Id和当前用户的Id是否一致							
						entity.setMsgType(false);
					} else 
						entity.setMsgType(true);
					entity.setText(message);
					mDataArrays.add(entity);				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		Runnable r = new Runnable() {
				@Override
				public void run() {
					mAdapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
	}
}

	
	
}
