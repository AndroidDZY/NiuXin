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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DeclarationReceiveActivity extends Activity implements OnClickListener{
	private Button btnSetting, btnSource, btnType,btnBack;
	private ToggleButton togBtnCollect;
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
				new String[]{"declarationName"},
				new int[]{R.id.tv_declaration_name});
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
		btnSetting = (Button)findViewById(R.id.btn_declarationreceive_setting);
		btnSource = (Button)findViewById(R.id.btn_declarationreceive_source);
		btnType = (Button)findViewById(R.id.btn_declarationreceive_type);
		btnBack = (Button)findViewById(R.id.btn_declaration_receive_back);
		
		btnSetting.setOnClickListener(this);
		btnSource.setOnClickListener(this);
		btnType.setOnClickListener(this);
		btnBack.setOnClickListener(this);
		
		togBtnCollect = (ToggleButton) findViewById(R.id.tog_btn_declaration_collect);
		
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
		
//		map.put("image_chatlog_detailed", R.drawable.head004);
//		map.put("title_chatlog_detailed", "汪总");
		map.put("declarationName", "合约IF1510");
		list.add(map);
		
		map = new HashMap<String, Object>();
//		map.put("image_chatlog_detailed", R.drawable.head005);
//		map.put("title_chatlog_detailed", "豆粕商品讨论组");
		map.put("declarationName", "合约IF1511");
		list.add(map);	
		
		map = new HashMap<String, Object>();
//		map.put("image_chatlog_detailed", R.drawable.head006);
//		map.put("title_chatlog_detailed", "海螺水泥群组");
		map.put("declarationName", "合约IF1512");
		list.add(map);
		
		map = new HashMap<String, Object>();
//		map.put("image_chatlog_detailed", R.drawable.head006);
//		map.put("title_chatlog_detailed", "海螺水泥群组");
		map.put("declarationName", "合约IF1513");
		list.add(map);
		
		return list;
	}

}
