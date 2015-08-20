package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class K_line_setActivity extends Activity{
	private Button btn_kset_cancle,btn_kset_finish;
	private ImageButton btn_k_add;
	private int flag = 0;
	ListView listView;
	private int k_flag = 0;
	private int k_flag_day = 0;
	private int k_flag_week = 0;
	private int k_flag_month = 0;
	//实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.k_line_set);
		//获得Button
		btn_kset_cancle = (Button)findViewById(R.id.btn_kset_cancle);
		btn_kset_finish = (Button)findViewById(R.id.btn_kset_finish);
		btn_k_add = (ImageButton)findViewById(R.id.btn_k_add);
		
		//点击取消返回上一个界面
		btn_kset_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				finish();
			}
		});
		
		//选中列表项后，点击设置跳转到相应k线图界面
		btn_kset_finish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				switch(flag)
				{
					case 1://日
						Intent intent = new Intent(K_line_setActivity.this,K_line_dayActivity.class);
						startActivity(intent);
						break;
					case 2://周
						Intent intent01 = new Intent(K_line_setActivity.this,K_lineActivity.class);
						startActivity(intent01);
						break;
					default:
						break;
				}
				flag = 0;
			}
		});
		
		//点击跳转到添加自选界面
		btn_k_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(K_line_setActivity.this,zixuan_addActivity.class);
				startActivity(intent);
				
			}
		});
		
		list = getData();// 填充list数据
		listView = (ListView)findViewById(R.id.klinelist);//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用klinelist.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用klinelist.xml文件中的哪些组件来填充列表项
		SimpleAdapter klineAdapter= new SimpleAdapter(this, list,R.layout.klinelistview, 
				new String[]{"zhouqi","img_k_flag"},
				new int[]{R.id.zhouqi,R.id.img_k_flag});
		listView.setAdapter(klineAdapter);//为listView设置适配器
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {
				switch (position)
				{
				case 0: //点击日
					flag = 1;
					break;
				case 1://点击周
					flag = 2;
					break;
				default:
					flag = 0;
					break;
				}
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);//找到被点击的列表项
	            if(Integer.valueOf(map.get("img_k_flag").toString())==0){
	            	k_flag = R.drawable.k_flag;
	            }else
	            	k_flag = 0;
	            //先清空
	            list.get(0).put("img_k_flag", 0);
	            list.get(1).put("img_k_flag", 0);
	            list.get(2).put("img_k_flag", 0);
	            list.get(position).put("img_k_flag", k_flag);//将更新过的k_flag值放入list中        
	            adapter.notifyDataSetInvalidated();//使更新过的list数据生效
			}
		});
		

}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = getList();
		return list;
	}
	private List<Map<String, Object>> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("id", 1);
		map.put("zhouqi", "日");
		map.put("img_k_flag", k_flag_day);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("id", 2);
		map.put("zhouqi", "周");
		map.put("img_k_flag", k_flag_week);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("id", 3);
		map.put("zhouqi", "月");
		map.put("img_k_flag", k_flag_month);
		list.add(map);

		return list;
	}

}