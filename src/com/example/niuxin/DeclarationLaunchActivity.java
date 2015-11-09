package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.niuxin.DeclarationModelChoiceActivity.MyAdapter;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class DeclarationLaunchActivity extends Activity{
	
	Button iDeclarationDetail ;
	private ListView listView;
	private List<HashMap<String, Object>> mData;  
	private SuoluetuActivity suolue;
	private View view;
	public Handler handler = new Handler();
	private MainActivity mainActivity;
	private TabHost tabHost;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch);
		//mainActivity=new MainActivity();
		//初始化
		iDeclarationDetail=(Button)findViewById(R.id.declaration_launch_detail);
		mData=getData();
		listView=(ListView)findViewById(R.id.declaration_tag);
		MyAdapter adapter = new MyAdapter(this);//创建一个适配器  
		listView.setAdapter(adapter);
		
		view=(View)findViewById(R.id.include_mainbottom);
		Button button1=(Button)view.findViewById(R.id.main_tab_niuxin);
		Button button2=(Button)view.findViewById(R.id.main_tab_guqunguangchang);
		Button button3=(Button)view.findViewById(R.id.main_tab_gushirili);
		Button button4=(Button)view.findViewById(R.id.main_tab_more);
		button3.setTextColor(0xFFFFFFFF);
		button3.setTextSize(20);
		//事件监听
		//跳转到报单详细内容
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println("1111111111111");
				Intent intent =new Intent(DeclarationLaunchActivity.this ,DeclarationDetailContentActivity.class);
				startActivity(intent);
			}
		});
		//我要报单
		iDeclarationDetail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("1111111111111");
				Intent intent =new Intent(DeclarationLaunchActivity.this ,DeclarationDetailActivity.class);
				startActivity(intent);
			}
		});
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 =new Intent(DeclarationLaunchActivity.this ,MainActivity.class);
				startActivity(intent1);
			}
		});
        button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 =new Intent(DeclarationLaunchActivity.this ,MainActivity.class);
				
				startActivity(intent1);
			}
		});
        button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 =new Intent(DeclarationLaunchActivity.this ,MainActivity.class);
				startActivity(intent1);
				
			}
		});
        button4.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 =new Intent(DeclarationLaunchActivity.this ,MainActivity.class);
				startActivity(intent1);
				
			}
		});
	}
	public MainActivity getMainActivity() {
		return mainActivity;
	}
	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	@Override
	protected void onResume() {
		super.onResume();
		suolue = new SuoluetuActivity(this, handler);
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
            return mData.size();// ListView的条目数  
        }  
  
        @Override  
        public Object getItem(int arg0) {  
            return null;  
        }  
  
        @Override  
        public long getItemId(int arg0) {  
            return 0;  
        }  
  //获取listview视图对象 
        @Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
        	convertView = mInflater.inflate(R.layout.decla_baodan, null);//根据布局文件实例化view 
        	//标签
        	TextView tagText=(TextView) convertView.findViewById(R.id.decla_baodan_tag);
        	tagText.setText(mData.get(position).get("tagText").toString());
        	//时间
        	TextView timeText=(TextView) convertView.findViewById(R.id.decla_baodan_time);
        	//timeText.setText(mData.get(position).get("timeText").toString());
        	//类型
        	Button typeButton=(Button) convertView.findViewById(R.id.decla_baodan_type);
        	typeButton.setText(mData.get(position).get("typeButton").toString());
        	//价格
        	TextView priceText=(TextView) convertView.findViewById(R.id.decla_baodan_price);
        	priceText.setText(mData.get(position).get("priceText").toString());
        	//手数
        	TextView handText=(TextView) convertView.findViewById(R.id.decla_baodan_shoushu);
        	handText.setText(mData.get(position).get("handText").toString());
        	//盈利
        	TextView gainText=(TextView) convertView.findViewById(R.id.decla_baodan_gain);
        	gainText.setText(mData.get(position).get("gainText").toString());
        	//仓位
        	TextView spaceText=(TextView) convertView.findViewById(R.id.decla_baodan_space);
        	spaceText.setText(mData.get(position).get("spaceText").toString());
        	//对象
        	Button objectButton=(Button)convertView.findViewById(R.id.decla_baodan_object);
        	objectButton.setText(mData.get(position).get("objectButton").toString());
            return convertView;
		}  
    }
	//获取数据
	private List<HashMap<String, Object>> getData() {  
        // 新建一个集合类，用于存放多条数据  从数据库中获取数据
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
        HashMap<String, Object> map = null;  
        for (int i = 1; i <= 3; i++) {  
            map = new HashMap<String, Object>();  
            map.put("tagText", "合约IF1509" ); //r.drawable 
            map.put("timeText", "2015年10月");  
            map.put("typeButton", "多平");  
            map.put("priceText", "1234");  
            map.put("handText", "1111");  
            map.put("gainText", "250");
            map.put("spaceText", "15%");
            map.put("objectButton", "查看发送对象");
            list.add(map);  
        }  
  
        return list;  
    }  

}
