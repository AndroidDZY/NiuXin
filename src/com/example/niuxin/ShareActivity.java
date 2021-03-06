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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ShareActivity extends Activity{
	ListView listView;
	Button cancle;
    //1
    private SuoluetuActivity suolue;
    public Handler handler = new Handler();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.share);
		
		//2
		suolue = new SuoluetuActivity(this,handler);
		
		//获取各种控件
		cancle = (Button)findViewById(R.id.btn_collect_cancle);
		listView=(ListView)findViewById(R.id.sharelist);
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用sharelistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用sharelistview.xml文件中的哪些组件来填充列表项
		SimpleAdapter shareAdapter= new SimpleAdapter(this, getData(),
				R.layout.sharelistview, new String[]{"img_share","xiaoxiyuan01","share_title",
						"share_context","share_date","share_time"},new int[]{R.id.img_share,R.id.xiaoxiyuan01,
			             R.id.share_title,R.id.share_context,R.id.share_date,R.id.share_time});
		listView.setAdapter(shareAdapter);
		
		//定义按钮事件
		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				finish();
			}
		});	
	}
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listShare= new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("img_share", R.drawable.collect_head);
		map.put("xiaoxiyuan01", "聊天记录");
		map.put("share_title", "豆粕商品讨论组");
		map.put("share_context","汪总：今天又要涨停");
		map.put("share_date", "15/7/2");
		map.put("share_time", "13:27");
		listShare.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img_share", R.drawable.collect_head);
		map.put("xiaoxiyuan01", "投资圈");
		map.put("share_title", "豆粕商品讨论组");
		map.put("share_context","汪总：今天又要涨停");
		map.put("share_date", "15/7/2");
		map.put("share_time", "13:27");
		listShare.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img_share", R.drawable.collect_head);
		map.put("xiaoxiyuan01", "推送消息");
		map.put("share_title", "豆粕商品讨论组");
		map.put("share_context","汪总：今天又要涨停");
		map.put("share_date", "15/7/2");
		map.put("share_time", "13:27");
		listShare.add(map);
		
		map = new HashMap<String, Object>();
		map.put("img_share", R.drawable.collect_head);
		map.put("xiaoxiyuan01", "所有");
		map.put("share_title", "豆粕商品讨论组");
		map.put("share_context","汪总：今天又要涨停");
		map.put("share_date", "15/7/2");
		map.put("share_time", "13:27");
		listShare.add(map);
		
		return listShare;
	}

}

