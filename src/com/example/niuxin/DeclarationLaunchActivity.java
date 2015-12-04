package com.example.niuxin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class DeclarationLaunchActivity extends Activity{
	
	private Button iDeclarationDetail, backButton;
	private ListView listView;
	private Spinner spinner;
	private List<HashMap<String, Object>> mData = new LinkedList<HashMap<String, Object>>();  
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	MyAdapter adapter = null ;
	String orderseq = "按时间顺序查询";
	String[] declaOrder = {"按时间顺序查询", "按合约类型查询", "按接收者查询"};
	int p=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		//mainActivity=new MainActivity();
		
		//初始化
		iDeclarationDetail = (Button)findViewById(R.id.declaration_launch_detail);//"我要报单"按钮
		backButton = (Button)findViewById(R.id.declaration_lanch_back);//返回按钮
		
	//	mData = getData();
		listView = (ListView)findViewById(R.id.declaration_tag);
		 adapter = new MyAdapter(this);//创建一个适配器  
		listView.setAdapter(adapter);
		
		spinner = (Spinner)findViewById(R.id.declaration_launch_paixu);  
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.decla_spinner, declaOrder);   //此处加上自己的样式
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				 String order=declaOrder[arg2];
				 orderseq=order;
				 arg0.setVisibility(View.VISIBLE);
				 
				 GroupThread g = new GroupThread();
				 g.start();					
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//事件监听
		//跳转到报单详细内容
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println("1111111111111");
				//获取报单的id，通过id去显示报单详情，真数据的时候调用下面的代码
				/*Integer id=Integer.valueOf(mData.get(arg2).get("id").toString());
				Intent intent=new Intent();
				intent.putExtra("id", id);
				intent.setClass(DeclarationLaunchActivity.this ,DeclarationDetailContentActivity.class);
				startActivity(intent);*/
				// 这边不需要跳转详细信息
				/*Intent intent =new Intent(DeclarationLaunchActivity.this ,DeclarationDetailContentActivity.class);
				startActivity(intent);*/
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
		
		//返回按钮
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
		
		GroupThread g = new GroupThread();
		g.start();
		
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
        	if(null!=mData)
        		return mData.size();// ListView的条目数  
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
  //获取listview视图对象 
        @Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
      
        	convertView = mInflater.inflate(R.layout.listview_set_declaration, null);//根据布局文件实例化view 
        	//合约
        	TextView tagText=(TextView) convertView.findViewById(R.id.tv_declaration_contract_set);
        	tagText.setText(mData.get(position).get("contract").toString());
        	//日期
        	TextView dateText=(TextView) convertView.findViewById(R.id.tv_declaration_date_set);
        	dateText.setText(mData.get(position).get("date").toString());
        	//星期
        	//TextView weekText=(TextView) convertView.findViewById(R.id.tv_declaration_week_set);
        	//timeText.setText(mData.get(position).get("timeText").toString());
        	//时间
        	TextView timeText=(TextView) convertView.findViewById(R.id.tv_declaration_time_set);
        	timeText.setText(mData.get(position).get("time").toString());
        	//操作类型
        	TextView typeButton=(TextView) convertView.findViewById(R.id.tv_declaration_operation_type_set);
        	typeButton.setText(mData.get(position).get("operation").toString());
        	//价格
        	TextView priceText=(TextView) convertView.findViewById(R.id.tv_declaration_cost_set);
        	priceText.setText(mData.get(position).get("price").toString());
        	//手数
        	TextView handText=(TextView) convertView.findViewById(R.id.tv_declaration_amount_set);
        	handText.setText(mData.get(position).get("handnum").toString());
        	//盈利
        	TextView gainText=(TextView) convertView.findViewById(R.id.tv_declaration_profit_set);
        	gainText.setText(mData.get(position).get("gainText").toString());
        	//仓位
        	TextView spaceText=(TextView) convertView.findViewById(R.id.tv_declaration_position_set);
        	spaceText.setText(mData.get(position).get("position").toString());
        	//对象
        	TextView sendtoText=(TextView)convertView.findViewById(R.id.tv_declaration_sendto_set);
//        	sendtoText.setText(mData.get(position).get("objectButton").toString());
        	//点击查看发送对象
        	//对应的ID
        	//final Long cid=Long.valueOf(mData.get(position).get("id").toString());
			sendtoText.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View arg0) {
					Intent intent =new Intent(DeclarationLaunchActivity.this ,DeclarationSendtargetchoicedActivity.class);//查看发送目标

					intent.putExtra("intentuserlist", mData.get(position).get("sendtoUser").toString());
					intent.putExtra("intentgrouplist", mData.get(position).get("sendtoGroup").toString());
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
            map.put("contract", "合约IF1509" ); //r.drawable 
            map.put("date", "2015年10月周一");  
            map.put("time", "10:23");  
            map.put("operation", "多平");  
            map.put("price", "1234");  
            map.put("handnum", "1111");  
            map.put("gainText", "250");
            map.put("position", "15%");
            list.add(map);  
        }  
  
        return list;  
    }  
	//启动线程从数据库中获取数据
	class GroupThread extends Thread {
		@SuppressLint("SimpleDateFormat")
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				
				Integer id = util.getId();			
				jsonObject.put("id", id);	
				jsonObject.put("userid", util.getId());	
			} catch (JSONException e) {
				e.printStackTrace();
			}			
			//设置发送的url 和服务器端的struts.xml文件对应
			if (orderseq.equals("按时间顺序查询")) {
				postUtil.setUrl("/form/form_selectFormByTime.do");
			}else if (orderseq.equals("按合约类型查询")) {
				postUtil.setUrl("/form/form_selectFormByContract.do");
			}else if (orderseq.equals("按接收者查询")) {
				postUtil.setUrl("/form/form_selectFormBySend.do");
			}
			
			//向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);
			// 从服务器获取数据
			String res = postUtil.run();	
			if(res==null){
				return;
			}
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;			
			try {
				jsonArray = new JSONArray(res);
				
//				//假数据
//				HashMap<String, Object> map = new HashMap<String, Object>();
//				map.put("contract", "qqwea");//R.drawable.head010
//				map.put("id", 31);
//				map.put("operation", "多平");
//				map.put("price", 11);
//				map.put("handnum", "3");
//				map.put("position", "5");
//				map.put("date", "9月12日");
//				map.put("time", "9:12");
//				map.put("gainText", "1");
//				map.put("sendtoUser", 2);
//				map.put("sendtoGroup", 4);
//				//typelist.add(0);
//				mData.add(map);
				
			} catch (JSONException e) {
				e.printStackTrace();
			}	
			if(mData!=null)
				mData.clear();
			//typelist.clear();
			if(jsonArray!=null)
			for (int i = 0; i < jsonArray.length(); i++) {				
				try {				
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					// 获取每一个对象中的值
						HashMap<String, Object> map = new HashMap<String, Object>();
						int id = myjObject.getInt("id");
						String contract = myjObject.getString("contract");//合约类型
						String operation = myjObject.getString("operation");//操作类型
						String price = myjObject.getString("price");//价格
						String handnum = myjObject.getString("handnum");//手数
						String position = myjObject.getString("position");//仓位
						SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日周E HH时mm分");	
						String date = myjObject.getString("date");
						String week = myjObject.getString("week");
						String time = myjObject.getString("time");
						String sendtoUser = myjObject.getString("sendtoUser");
						String sendtoGroup = myjObject.getString("sendtoGroup");
						
						//String yingli = myjObject.getString("");//盈利
						map.put("contract", contract);//R.drawable.head010
						map.put("id", id);
						map.put("operation", operation);
						map.put("price", price);
						map.put("time",time);
						map.put("handnum", handnum);
						map.put("position", position);
						map.put("date", date);
						map.put("time", time);
						map.put("gainText", "1");
						map.put("sendtoUser", sendtoUser);
						map.put("sendtoGroup", sendtoGroup);
						//typelist.add(0);
						mData.add(map);
					}
				 catch (JSONException e) {
					e.printStackTrace();
				}
			}/////////////////////////////解析数据完成
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
