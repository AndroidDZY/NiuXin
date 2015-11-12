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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;

public class DeclarationLaunchActivity extends Activity{
	
	Button iDeclarationDetail ,backButton;
	private ListView listView;
	private Spinner spinner;
	private List<HashMap<String, Object>> mData;  
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	String[] declaOrder = {"按时间顺序查询", "按合约类型查询", "按接收者查询"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch);
		//mainActivity=new MainActivity();
		//初始化
		iDeclarationDetail=(Button)findViewById(R.id.declaration_launch_detail);
		backButton=(Button)findViewById(R.id.declaration_lanch_back);
		mData=getData();
		listView=(ListView)findViewById(R.id.declaration_tag);
		MyAdapter adapter = new MyAdapter(this);//创建一个适配器  
		listView.setAdapter(adapter);
		
		spinner = (Spinner)findViewById(R.id.declaration_launch_paixu);  
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.decla_spinner, declaOrder);   //此处加上自己的样式
		spinner.setAdapter(spinnerAdapter);
		
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
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
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
        	convertView = mInflater.inflate(R.layout.listview_set_declaration, null);//根据布局文件实例化view 
        	//合约
        	TextView tagText=(TextView) convertView.findViewById(R.id.tv_declaration_contract_set);
        	tagText.setText(mData.get(position).get("tagText").toString());
        	//日期
        	TextView dateText=(TextView) convertView.findViewById(R.id.tv_declaration_date_set);
        	//timeText.setText(mData.get(position).get("timeText").toString());
        	//星期
        	TextView weekText=(TextView) convertView.findViewById(R.id.tv_declaration_week_set);
        	//timeText.setText(mData.get(position).get("timeText").toString());
        	//时间
        	TextView timeText=(TextView) convertView.findViewById(R.id.tv_declaration_time_set);
        	//timeText.setText(mData.get(position).get("timeText").toString());
        	//操作类型
        	TextView typeButton=(TextView) convertView.findViewById(R.id.tv_declaration_operation_type_set);
        	typeButton.setText(mData.get(position).get("typeButton").toString());
        	//价格
        	TextView priceText=(TextView) convertView.findViewById(R.id.tv_declaration_cost_set);
        	priceText.setText(mData.get(position).get("priceText").toString());
        	//手数
        	TextView handText=(TextView) convertView.findViewById(R.id.tv_declaration_amount_set);
        	handText.setText(mData.get(position).get("handText").toString());
        	//盈利
        	TextView gainText=(TextView) convertView.findViewById(R.id.tv_declaration_profit_set);
        	gainText.setText(mData.get(position).get("gainText").toString());
        	//仓位
        	TextView spaceText=(TextView) convertView.findViewById(R.id.tv_declaration_position_set);
        	spaceText.setText(mData.get(position).get("spaceText").toString());
        	//对象
        	TextView sendtoText=(TextView)convertView.findViewById(R.id.tv_declaration_sendto_set);
//        	sendtoText.setText(mData.get(position).get("objectButton").toString());
        	sendtoText.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println("111111111111111111111111111111111111111111111");
					Intent intent =new Intent(DeclarationLaunchActivity.this ,DeclarationSendtargetchoicedActivity.class);
					startActivity(intent);
				}
			});
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
            list.add(map);  
        }  
  
        return list;  
    }  

}
