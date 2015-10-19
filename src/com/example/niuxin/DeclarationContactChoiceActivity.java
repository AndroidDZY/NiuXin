package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.niuxin.DeclarationModelChoiceActivity.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class DeclarationContactChoiceActivity extends Activity{
	
	private ListView listView;
	private List<HashMap<String, Object>> mData;  
    private Button buttonBack , saveButton ;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch_contact);
		//返回按钮
				buttonBack=(Button)findViewById(R.id.declaration_contact_button_back);
				//保存按钮
				saveButton=(Button)findViewById(R.id.declaration_contact_button_save);
				mData = getData();
				MyAdapter adapter = new MyAdapter(this);//创建一个适配器  
				listView=(ListView)findViewById(R.id.declaration_list_contactchoice);
				listView.setAdapter(adapter);
				
				//返回监听
				buttonBack.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						finish();
					}
				});
				//保存监听
				saveButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						//获取数据保存到数据库
					}
				});
			}
			
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
		  
		        @Override
				public View getView(int position, View convertView, ViewGroup arg2) {
					// TODO Auto-generated method stub
		        	convertView = mInflater.inflate(R.layout.decla_detail_contact, null);//根据布局文件实例化view 
		        	CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.decla_contact_checkbox);
		        	checkBox.setText(mData.get(position).get("checkBox").toString());
		            TextView contactText = (TextView) convertView.findViewById(R.id.decla_textview_contact);
		            contactText.setText(mData.get(position).get("contactText").toString());
		            return convertView;
				}  
		    }
			
			private List<HashMap<String, Object>> getData() {  
		        // 新建一个集合类，用于存放多条数据  
		        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
		        HashMap<String, Object> map = null; 
		            
		        for (int i = 1; i <= 3; i++) {  
		            map = new HashMap<String, Object>();  
		            map.put("checkBox",  i); //r.drawable 
		            map.put("contactText", "合约"+i);  
		            list.add(map);  
		        }  
		  
		        return list;  
		    }  

	
}
