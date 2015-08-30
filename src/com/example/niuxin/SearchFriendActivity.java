package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class SearchFriendActivity extends Activity{
	private int flag_sfriend = R.drawable.add_flag01;
	private Button sButton,fButton,cButton;
	ListView listView_sfriend;
	//1
    private SuoluetuActivity suolue;
    public Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.searchfriend);
		//2
		suolue = new SuoluetuActivity(this,handler);
		
		//获取控件及点击事件
		sButton = (Button)findViewById(R.id.btn_sf_search);
		cButton = (Button)findViewById(R.id.btn_sfriend_cancle);
		fButton = (Button)findViewById(R.id.btn_sfriend_finish);
		//查找按钮点击事件
		sButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
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
				// TODO Auto-generated method stub
				
			}
		});
		
		//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用searchfriend_list.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用searchfriend_list.xml文件中的哪些组件来填充列表项
		listView_sfriend=(ListView)findViewById(R.id.searchfriend_list);
		SimpleAdapter searchfriendAdapter= new SimpleAdapter(this, getData_searchfriend(),R.layout.searchfriend_list, 
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
	
	// 查找好友listview的数据
	private List<Map<String, Object>> getData_searchfriend() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("image_sfriend", R.drawable.head001);
		map.put("title_sfriend", "汪总");
		map.put("flag_sfriend", flag_sfriend);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("image_sfriend", R.drawable.head002);
		map.put("title_sfriend", "许总");
		map.put("flag_sfriend", flag_sfriend);
		list.add(map);	
		
		map = new HashMap<String, Object>();
		map.put("image_sfriend", R.drawable.head003);
		map.put("title_sfriend", "江总");
		map.put("flag_sfriend", flag_sfriend);
		list.add(map);
		
		return list;
	}
}