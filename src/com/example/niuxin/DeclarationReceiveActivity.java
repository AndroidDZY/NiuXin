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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DeclarationReceiveActivity extends Activity implements OnClickListener{
	private Button btnSetting, btnSource, btnType;
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
				new String[]{"name"},
				new int[]{R.id.tv_declarationreciive_name});
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
		
		btnSetting.setOnClickListener(this);
		btnSource.setOnClickListener(this);
		btnType.setOnClickListener(this);
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
		map.put("name", "合约1");
		list.add(map);
		
		map = new HashMap<String, Object>();
//		map.put("image_chatlog_detailed", R.drawable.head005);
//		map.put("title_chatlog_detailed", "豆粕商品讨论组");
		map.put("name", "合约2");
		list.add(map);	
		
		map = new HashMap<String, Object>();
//		map.put("image_chatlog_detailed", R.drawable.head006);
//		map.put("title_chatlog_detailed", "海螺水泥群组");
		map.put("name", "合约3");
		list.add(map);
		
		return list;
	}

}
