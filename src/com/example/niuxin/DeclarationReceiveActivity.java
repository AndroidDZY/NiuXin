package com.example.niuxin;

import java.math.BigDecimal;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DeclarationReceiveActivity extends Activity implements OnClickListener{
	private Button btnSetting, btnSendFrom, btnType,btnBack;
	private ToggleButton togBtnCollect;
	private TextView tvContract, tvSendFrom;
	private ListView lvDeclaration;
	SimpleAdapter declarationAdapter = null;
	private Handler handler = new Handler();
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private SharePreferenceUtil util = null;
	
	@Override
	protected void onResume() {
		super.onResume();
		// 准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。
		SearchAllThread thread = new SearchAllThread();
		thread.start();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_declaration_receive);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		initView();
		
		//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用listview_declaration.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用listview_declaration.xml文件中的哪些组件来填充列表项
		lvDeclaration=(ListView)findViewById(R.id.lv_declarationreceive);
		declarationAdapter= new SimpleAdapter(this, getData(),R.layout.listview_get_declaration, 
				new String[]{"contract", "date", "week", "time", "operation", "price", "handnum", "profit", "position", "senderHead", "senderName", "isCollect"},	
				// 合约类型、日期、星期、时间、操作类型、价格、手数、盈利、仓位、报单者头像、报单者名字、是否收藏标志
				new int[]{R.id.tv_declaration_contract, R.id.tv_declaration_date, R.id.tv_declaration_week, R.id.tv_declaration_time, 
			R.id.tv_declaration_operation_type, R.id.tv_declaration_cost ,R.id.tv_declaration_amount, R.id.tv_declaration_profit, 
			R.id.tv_declaration_position, R.id.iv_declaration_sender_head, R.id.tv_declaration_sender_name, R.id.iv_declaration_collect});
		lvDeclaration.setAdapter(declarationAdapter);
		
		
		//listview item点击事件
		lvDeclaration.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intentList = new Intent(DeclarationReceiveActivity.this, ContractDetailsActivity.class);
				startActivity(intentList);
			}
		});
	}

	private void initView() {
		// 获取各种控件
		btnSetting = (Button)findViewById(R.id.btn_declarationreceive_setting);	//“提醒设置”跳转按钮
		btnSendFrom = (Button)findViewById(R.id.btn_declarationreceive_source);	//“报单来源”跳转按钮
		btnType = (Button)findViewById(R.id.btn_declarationreceive_type);	//“合约类型”跳转按钮
		btnBack = (Button)findViewById(R.id.btn_declaration_receive_back);	//返回按钮
		
		btnSetting.setOnClickListener(this);
		btnSendFrom.setOnClickListener(this);
		btnType.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		
		tvContract = (TextView)findViewById(R.id.tv_declaration_receive_contract);	//“合约类型”显示文本
		tvSendFrom = (TextView)findViewById(R.id.tv_declaration_receive_source);	//“报单来源”显示文本
		
		togBtnCollect = (ToggleButton) findViewById(R.id.tog_btn_declaration_collect);	//“只展示收藏报单”切换按钮
		
		// 只展示收藏报单切换按钮
		togBtnCollect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked){
					//选中
					Toast toast = Toast.makeText(DeclarationReceiveActivity.this, "只展示收藏报单", Toast.LENGTH_SHORT);
					toast.show();
				}else{
					//未选中
					Toast toast = Toast.makeText(DeclarationReceiveActivity.this, "取消", Toast.LENGTH_SHORT);
					toast.show();
				}
			}
		});
	}

	// 定义按钮点击事件
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.btn_declarationreceive_setting:
				Intent intentRemind = new Intent(DeclarationReceiveActivity.this, DeclarationRemindSettingActivity.class);
				startActivity(intentRemind);
				break;
			case R.id.btn_declarationreceive_source:
				Intent intentSource = new Intent(DeclarationReceiveActivity.this, DeclarationSourceSelectActivity.class);
				startActivity(intentSource);
				break;
			case R.id.btn_declarationreceive_type:
				Intent intentType = new Intent(DeclarationReceiveActivity.this, ContractTypeSelectActivity.class);
				startActivity(intentType);
				break;
			case R.id.btn_declaration_receive_back:
				finish();
				break;
			default:
				break;
		}		
	}
	
	
	class SearchAllThread extends Thread {//默认情况下，什么也没选择，得到的搜索结果。
		@Override
		public void run() {			
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			// 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			try {
				//1 用json进行解析接收到的参数 a接收用户的id b报单来源（用户id，群组id 全选为-1 多个以逗号分隔） c合约类型（全选为-1  多个以逗号分隔）  d只展示收藏的报单（关闭为-1 开启为1）
				jsonObject.put("userid", util.getId()); //用户自己的id
				jsonObject.put("sendtouserid", ""); //发送给用户的id
				jsonObject.put("sendtogroupid", ""); //发送给群组的id
				jsonObject.put("contract", ""); //合约类型
				jsonObject.put("collection", ""); //是否只展示收藏 0表示没选择 1表示选择
				

				
				/////////////////////////////////////////////////
			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);
		
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/form/form_selectAll.do");
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
			
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					// 发送表单用户的信息
					String id = myjObject.getString("id");//表单id
					String contract = myjObject.getString("contract");
					String operation = myjObject.getString("operation");
					BigDecimal price = (BigDecimal) myjObject.get("price");
					int handnum = myjObject.getInt("handnum");
					Double position =  myjObject.getDouble("position");
					BigDecimal profit = (BigDecimal) myjObject.get("profit");
				//	Double minnum = myjObject.getDouble("minnum");
				//	Double maxnum = myjObject.getDouble("maxnum");
				//	String remark = myjObject.getString("remark");
				//	String pictureurl = myjObject.getString("pictureurl");
				//	String audiourl = myjObject.getString("audiourl");
					String date = myjObject.getString("date");
					String week = myjObject.getString("week");
					String time = myjObject.getString("time");
				//	String name = myjObject.getString("name");
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
					map.put("position", position+"%");
					map.put("senderHead", senduserimg);
					map.put("senderName", sendusername);
					map.put("senderId", sendfromid);//发送用户的id
					map.put("id", id);//表单id
					if(collection==0)
						map.put("isCollect", R.drawable.ic_declaration_star_unpressed);
					else
						map.put("isCollect", R.drawable.ic_declaration_star_pressed);
					list.add(map);
					
					list.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {				
					declarationAdapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
		}
	}

	

	// listview适配器数据加载
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("contract", "合约IF1510");
		map.put("date", "2015年10月30日");
		map.put("week", "周五");
		map.put("time", "10:59");
		map.put("operation", "多平");
		map.put("price", "3133");
		map.put("handnum", "1000");
		map.put("profit", "6340000");
		map.put("position", "15%");
		map.put("senderHead", R.drawable.head_declaration_sender);
		map.put("senderName", "张三");
		map.put("isCollect", R.drawable.ic_declaration_star_unpressed);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("contract", "IF1511");
		map.put("date", "2015年10月30日");
		map.put("week", "周五");
		map.put("time", "10:59");
		map.put("operation", "多平");
		map.put("price", "3133");
		map.put("handnum", "1000");
		map.put("profit", "6340000");
		map.put("position", "15%");
		map.put("senderHead", R.drawable.head_declaration_sender);
		map.put("senderName", "张三");
		map.put("isCollect", R.drawable.ic_declaration_star_unpressed);
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("contract", "IF1512");
		map.put("date", "2015年10月30日");
		map.put("week", "周五");
		map.put("time", "10:59");
		map.put("operation", "多平");
		map.put("price", "3133");
		map.put("handnum", "1000");
		map.put("profit", "6340000");
		map.put("position", "15%");
		map.put("senderHead", R.drawable.head001);
		map.put("senderName", "李四");
		map.put("isCollect", R.drawable.ic_declaration_star_unpressed);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("contract", "IF1513");
		map.put("date", "2015年10月30日");
		map.put("week", "周五");
		map.put("time", "10:59");
		map.put("operation", "多平");
		map.put("price", "3133");
		map.put("handnum", "1000");
		map.put("profit", "6340000");
		map.put("position", "15%");
		map.put("senderHead", R.drawable.head001);
		map.put("senderName", "李四");
		map.put("isCollect", R.drawable.ic_declaration_star_unpressed);
		list.add(map);
		
		return list;
	}

}
