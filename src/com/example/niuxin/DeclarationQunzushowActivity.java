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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DeclarationQunzushowActivity extends Activity{
	
	private ListView listView;
	private List<HashMap<String, Object>> mData;  
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_targetshow_qunzu);
		//mainActivity=new MainActivity();
		//初始化
		mData=getData();
		listView=(ListView)findViewById(R.id.declaration_targetshow_qunzu_list);
		MyAdapter adapter = new MyAdapter(this);//创建一个适配器  
		listView.setAdapter(adapter);
		
		//事件监听
		
		
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
        	convertView = mInflater.inflate(R.layout.declaration_targetshow_qunzuitem, null);//根据布局文件实例化view 
        	//名称
        	TextView nameText=(TextView) convertView.findViewById(R.id.declatarget_qunzuitem_name);
        	nameText.setText(mData.get(position).get("nameText").toString());
        	ImageView touxiang=(ImageView) convertView.findViewById(R.id.declatarget_qunzuitem_touxiang);
        	touxiang.setBackgroundResource(Integer.valueOf(mData.get(position).get("touxiang").toString()));
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
            map.put("nameText", "群组"+i ); //r.drawable 
            map.put("touxiang", R.drawable.detail_content_touxiang);  
             list.add(map);  
        }  
  
        return list;  
    }  

}
