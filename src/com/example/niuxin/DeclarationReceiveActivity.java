package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.activity_declaration_receive);
		
		initView();
		
		//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用listview_declaration.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用listview_declaration.xml文件中的哪些组件来填充列表项
		lvDeclaration=(ListView)findViewById(R.id.lv_declarationreceive);
		declarationAdapter= new SimpleAdapter(this, getData(),R.layout.listview_declaration, 
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
