package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.niuxin.DeclarationLaunchActivity.MyAdapter;
import com.niuxin.util.Constants;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DeclarationReceiveActivity extends Activity implements OnClickListener {
	private SuoluetuActivity suolue;
	private RelativeLayout rlSetting;
	private LinearLayout llSendFrom, llType;
	private Button btnBack;
	private ToggleButton togBtnCollect;
	private TextView tvContract, tvSendFrom;
	private ListView lvDeclaration;
	//MyAdapter declarationAdapter = null ;
	//private List<HashMap<String, Object>> mData = new LinkedList<HashMap<String, Object>>(); 
	SimpleAdapter declarationAdapter = null;
	private Handler handler = new Handler();
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private SharePreferenceUtil util = null;
	GetSource getSource = new GetSource();
	int isCollect = 0;// 是否收藏
	String contractlist = "-1";
	String contractNamelist = "全选";
	String sendtouseridlist = "-1";
	String sendtouseridlistName = "全选";
	private TextView contractNameText;
	private TextView sendtouserText;
	MyAdapter adapter = null ;
	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = getIntent();

		// 准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。
		getDate();
	}

	private void getDate() {
		SearchAllThread thread = new SearchAllThread();
		thread.start();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_declaration_receive);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		suolue = new SuoluetuActivity(this, handler);

		initView();
		
/*		lvDeclaration = (ListView)findViewById(R.id.lv_declarationreceive);
		declarationAdapter = new MyAdapter(this);//创建一个适配器  
		 lvDeclaration.setAdapter(declarationAdapter);
		 mData = getData();  */

		// 获取ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用listview_declaration.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用listview_declaration.xml文件中的哪些组件来填充列表项
		lvDeclaration = (ListView) findViewById(R.id.lv_declarationreceive);
		declarationAdapter = new SimpleAdapter(this, list, R.layout.listview_get_declaration,
				new String[] { "contract", "date", "week", "time", "operation", "price", "handnum", "profit",
						"position", "senderHead", "senderName", "isCollect" },
				// 合约类型、日期、星期、时间、操作类型、价格、手数、盈利、仓位、报单者头像、报单者名字、是否收藏标志
				new int[] { R.id.tv_declaration_contract, R.id.tv_declaration_date, R.id.tv_declaration_week,
						R.id.tv_declaration_time, R.id.tv_declaration_operation_type, R.id.tv_declaration_cost,
						R.id.tv_declaration_amount, R.id.tv_declaration_profit, R.id.tv_declaration_position,
						R.id.iv_declaration_sender_head, R.id.tv_declaration_sender_name,
						R.id.iv_declaration_collect });
		adapter = new MyAdapter(this);//创建一个适配器  
		lvDeclaration.setAdapter(adapter);

		// listview item点击事件
		lvDeclaration.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intentList = new Intent(DeclarationReceiveActivity.this, ContractDetailsActivity.class);
				intentList.putExtra("id", list.get(position).get("id").toString());
				intentList.putExtra("senduserid", list.get(position).get("senderId").toString());
				startActivity(intentList);
			}
		});
	}

	private void initView() {
		// 获取各种控件
		rlSetting = (RelativeLayout) findViewById(R.id.rl_declarationreceive_setting); // “提醒设置”跳转按钮
		llSendFrom = (LinearLayout) findViewById(R.id.ll_declarationreceive_source); // “报单来源”跳转按钮
		llType = (LinearLayout) findViewById(R.id.ll_declarationreceive_type); // “合约类型”跳转按钮
		btnBack = (Button) findViewById(R.id.btn_declaration_receive_back); // 返回按钮

		rlSetting.setOnClickListener(this);
		llSendFrom.setOnClickListener(this);
		llType.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		contractNameText = (TextView) findViewById(R.id.tv_declaration_receive_contract);
		sendtouserText = (TextView) findViewById(R.id.tv_declaration_receive_source);
		tvContract = (TextView) findViewById(R.id.tv_declaration_receive_contract); // “合约类型”显示文本
		tvSendFrom = (TextView) findViewById(R.id.tv_declaration_receive_source); // “报单来源”显示文本

		togBtnCollect = (ToggleButton) findViewById(R.id.tog_btn_declaration_collect); // “只展示收藏报单”切换按钮

		// 只展示收藏报单切换按钮
		togBtnCollect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// 选中
					isCollect = 1;
					getDate();
					Toast toast = Toast.makeText(DeclarationReceiveActivity.this, "只展示收藏报单", Toast.LENGTH_SHORT);
					toast.show();					
				} else {
					// 未选中
					isCollect = 0;
					getDate();
					Toast toast = Toast.makeText(DeclarationReceiveActivity.this, "取消", Toast.LENGTH_SHORT);
					toast.show();
					
				}
			}
		});
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
        	if(null!=list)
        		return list.size();// ListView的条目数  
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
        /*
         * adapter改动 ，点击adapter获取选中效果
         * */
        //获取listview视图对象 
        @Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
        	convertView = mInflater.inflate(R.layout.listview_get_declaration, null);//根据布局文件实例化view 
        	//合约
        	TextView tagText=(TextView) convertView.findViewById(R.id.tv_declaration_contract_get);
        	tagText.setText(list.get(position).get("contract").toString());
        	//日期
        	TextView dateText=(TextView) convertView.findViewById(R.id.tv_declaration_date_get);
        	dateText.setText(list.get(position).get("date").toString());
        	/*//星期
        	TextView weekdateText=(TextView) convertView.findViewById(R.id.tv_declaration_week);
        	weekdateText.setText(list.get(position).get("week").toString());
        	*///时间
        	TextView timeText=(TextView) convertView.findViewById(R.id.tv_declaration_time_get);
        	timeText.setText(list.get(position).get("time").toString());
        	//操作类型
        	TextView typeButton=(TextView) convertView.findViewById(R.id.tv_declaration_operation_type_get);
        	typeButton.setText(list.get(position).get("operation").toString());
        	//价格
        	TextView priceText=(TextView) convertView.findViewById(R.id.tv_declaration_cost_get);
        	priceText.setText(list.get(position).get("price").toString());
        	//手数
        	TextView handText=(TextView) convertView.findViewById(R.id.tv_declaration_amount_get);
        	handText.setText(list.get(position).get("handnum").toString());
        	//盈利
        	TextView gainText=(TextView) convertView.findViewById(R.id.tv_declaration_profit_get);
        	gainText.setText(list.get(position).get("profit").toString());
        	//仓位
        	TextView spaceText=(TextView) convertView.findViewById(R.id.tv_declaration_position_get);
        	spaceText.setText(list.get(position).get("position").toString());
        	//发送者名字
        	TextView senderText=(TextView)convertView.findViewById(R.id.tv_declaration_sender_name_get);
        	senderText.setText(list.get(position).get("senderName").toString());
        	//发送者头像
        	ImageView senderHead=(ImageView)convertView.findViewById(R.id.iv_declaration_sender_head_get);
        	senderHead.setBackgroundResource(Integer.valueOf(list.get(position).get("senderHead").toString()));
        	//是否收藏该报单标志
        	final CheckBox isCollect=(CheckBox)convertView.findViewById(R.id.iv_declaration_collect_ck);
        	isCollect.setBackgroundResource(Integer.valueOf(list.get(position).get("isCollect").toString()));
        	
        	isCollect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					int aa = Integer.valueOf(list.get(position).get("isCollect").toString());
					Boolean currentstatus =( Integer.valueOf(list.get(position).get("isCollect").toString())==R.drawable.ic_declaration_star_pressed);
					Boolean choicestatus = isCollect.isChecked();
					
					if (currentstatus==true&&choicestatus==true) {//如果现在是选择的状态并且将要选中的状态也是选中 则取消收藏
		        		isCollect.setBackgroundResource(R.drawable.ic_declaration_star_unpressed);
						CollectionThread c =  new CollectionThread(Integer.valueOf(list.get(position).get("id").toString()),0);
						c.start();
						list.get(position).put("isCollect",R.drawable.ic_declaration_star_unpressed) ;
						Toast.makeText(DeclarationReceiveActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
						return ;
					}
					if(currentstatus==false&&choicestatus==false) {//如果现在是未选择的状态并且将要选中的状态也是未选中 则收藏
		        		isCollect.setBackgroundResource(R.drawable.ic_declaration_star_pressed);
						CollectionThread c =  new CollectionThread(Integer.valueOf(list.get(position).get("id").toString()),1);
						c.start();
						list.get(position).put("isCollect",R.drawable.ic_declaration_star_pressed) ;
						Toast.makeText(DeclarationReceiveActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
						return ;
					}
					if(choicestatus){//如果已经收藏了，就取消收藏
						isCollect.setBackgroundResource(R.drawable.ic_declaration_star_pressed);
						//这里向数据库更新数据 ，选中状态收藏成功
						CollectionThread c =  new CollectionThread(Integer.valueOf(list.get(position).get("id").toString()),1);
						c.start();
						list.get(position).put("isCollect",R.drawable.ic_declaration_star_pressed) ;
						Toast.makeText(DeclarationReceiveActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
						return;
					}else{
						isCollect.setBackgroundResource(R.drawable.ic_declaration_star_unpressed);
						CollectionThread c =  new CollectionThread(Integer.valueOf(list.get(position).get("id").toString()),0);
						c.start();
						list.get(position).put("isCollect",R.drawable.ic_declaration_star_unpressed) ;
						Toast.makeText(DeclarationReceiveActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
						return;
					}
				}
			});
			return convertView;
		}  
    }  

	// 定义按钮点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_declarationreceive_setting:
			Intent intentRemind = new Intent(DeclarationReceiveActivity.this, DeclarationRemindSettingActivity.class);
			startActivity(intentRemind);
			break;
		case R.id.ll_declarationreceive_source:
			Intent intentSource = new Intent(DeclarationReceiveActivity.this, DeclarationSourceSelectActivity.class);
			intentSource.putExtra("contractlist",  sendtouseridlist);
			startActivityForResult(intentSource, 12);
			break;
		case R.id.ll_declarationreceive_type:
			Intent intentType = new Intent(DeclarationReceiveActivity.this, ContractTypeSelectActivity.class);
			intentType.putExtra("sendtouseridlist", contractlist);
			startActivityForResult(intentType, 10);
			break;
		case R.id.btn_declaration_receive_back:
			finish();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (null != intent) {
			String tempcontract = intent.getStringExtra("contract");
			if (tempcontract != null) {
				if (!contractlist.equals(tempcontract)) {// 看选择后的，和原来的是不是一样
					contractlist = tempcontract;//
				}
			}

			String tempcontractName = intent.getStringExtra("contractName");
			if (tempcontractName != null) {
				if (!contractNamelist.equals(tempcontractName)) {// 看选择后的，和原来的是不是一样
					contractNamelist = tempcontractName;//
				}
				contractNameText.setText(contractNamelist);
			}

			String tempsendtouserid = intent.getStringExtra("sendtouserid");
			if (tempsendtouserid != null) {
				if (!sendtouseridlist.equals(tempsendtouserid)) {// 看选择后的，和原来的是不是一样
					sendtouseridlist = tempsendtouserid;//
				}
			}

			String tempsendtouseridName = intent.getStringExtra("sendtouseridName");
			if (tempsendtouseridName != null) {
				if (!sendtouseridlistName.equals(tempsendtouseridName)) {// 看选择后的，和原来的是不是一样
					sendtouseridlistName = tempsendtouseridName;//
				}
				sendtouserText.setText(sendtouseridlistName);
			}
		}

	}
	// 是否收藏报单
		class CollectionThread extends Thread {
			private Integer formid;
			private Integer iscollection;

			public CollectionThread(Integer formid, Integer iscollection) {
				this.formid = formid;
				this.iscollection = iscollection;
			}

			@Override
			public void run() {
				// 新建工具类，向服务器发送Http请求
				HttpPostUtil postUtil = new HttpPostUtil();

				// 向服务器发送数据，如果没有，可以不发送
				JSONObject jsonObject = new JSONObject();
				// 获取发送报单的id

				try {
					jsonObject.put("formid", formid);
					jsonObject.put("iscollection", iscollection); // 1收藏 0不收藏

				} catch (JSONException e) {
					e.printStackTrace();
				}
				// 设置发送的url 和服务器端的struts.xml文件对应
				postUtil.setUrl("/form/form_collectionForm.do");
				// 向服务器发送数据
				JSONArray js = new JSONArray();
				js.put(jsonObject);
				postUtil.setRequest(js);
				// 从服务器获取数据
				String res = postUtil.run();

				Runnable r = new Runnable() {
					@Override
					public void run() {
					}
				};
				handler.post(r);
			}

		}

	class SearchAllThread extends Thread {// 默认情况下，什么也没选择，得到的搜索结果。
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			// 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			try {
				// 1 用json进行解析接收到的参数 a接收用户的id b报单来源（用户id，群组id 全选为-1 多个以逗号分隔）
				// c合约类型（全选为-1 多个以逗号分隔） d只展示收藏的报单（关闭为-1 开启为1）
				jsonObject.put("userid", util.getId()); // 用户自己的id
				jsonObject.put("sendtouserid", sendtouseridlist); // 发送给用户的id
				jsonObject.put("sendtogroupid", "-1"); // 发送给群组的id
				jsonObject.put("contract", contractlist); // 合约类型
				jsonObject.put("collection", isCollect); // 是否只展示收藏 0表示没选择 1表示选择

			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/form/form_receiveAll.do");
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
			if (null != jsonArray) {
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
						Map<String, Object> map = new HashMap<String, Object>();
						// 发送表单用户的信息
						String id = myjObject.getString("id");// 表单id
						String contract = myjObject.getString("contract");
						String operation = myjObject.getString("operation");
						String price = myjObject.getString("price");
						int handnum = myjObject.getInt("handnum");
						String position = myjObject.getString("position");
						String profit = myjObject.getString("profit");
						// Double minnum = myjObject.getDouble("minnum");
						// Double maxnum = myjObject.getDouble("maxnum");
						// String remark = myjObject.getString("remark");
						// String pictureurl =
						// myjObject.getString("pictureurl");
						// String audiourl = myjObject.getString("audiourl");
						String date = myjObject.getString("date");
						String week = myjObject.getString("week");
						String time = myjObject.getString("time");
						// String name = myjObject.getString("name");
						int sendfromid = myjObject.getInt("sendfrom");
						int collection = myjObject.getInt("collection");
						String senduserimg = myjObject.getString("img");
						String sendusername = myjObject.getString("sendusername");

						map.put("contract", contract);
						map.put("date", date);
						map.put("week", week);
						map.put("time", time);
						map.put("operation", operation);
						map.put("price", price);
						map.put("handnum", handnum);
						map.put("profit", profit);
						map.put("position", position + "%");

						// img.setImageResource(getSource.getResourceByReflect(list.get(position).get("img").toString()));

						map.put("senderHead", getSource.getResourceByReflect(senduserimg));
						map.put("senderName", sendusername);
						map.put("senderId", sendfromid);// 发送用户的id
						map.put("id", id);// 表单id
						if (collection == 0)
							map.put("isCollect", R.drawable.ic_declaration_star_unpressed);
						else
							map.put("isCollect", R.drawable.ic_declaration_star_pressed);
						list.add(map);

					} catch (JSONException e) {
						e.printStackTrace();
					}
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
