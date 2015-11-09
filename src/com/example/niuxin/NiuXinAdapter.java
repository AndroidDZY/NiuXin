package com.example.niuxin;

import java.util.HashMap;
import java.util.List;

import com.niuxin.util.GetSource;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NiuXinAdapter extends BaseAdapter{

	//数据源
	 private List<HashMap<String,Object>> list;
	 private Context context;
	 private List<Integer> type;
	 GetSource getSource = new GetSource();
	 //构造函数
	public NiuXinAdapter (Context context,List<HashMap<String,Object>> list, List<Integer> type){
		  this.context = context;
		  this.list = list;
		  this.type=type;
		 } 
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater mInflater = LayoutInflater.from(context);
		//产生一个View
		View view = null;
		//根据type不同的数据类型构造不同的View
		if(type.get(position)==0){
			view = mInflater.inflate(R.layout.listview_qun, null);//选择群聊布局显示
			//从适配器获取群聊数据
		//	String content=list.get(position).get("data").toString();
			//分离数据
		//	String []items=content.split(",");
			//设置群聊控件显示内容
			ImageView img=(ImageView)view.findViewById(R.id.img);
			img.setImageResource(getSource.getResourceByReflect(list.get(position).get("img").toString()));
			TextView qunname=(TextView)view.findViewById(R.id.qunname);	
			qunname.setText(list.get(position).get("name").toString());	
			TextView lastmes=(TextView)view.findViewById(R.id.lastmes);	
			lastmes.setText(list.get(position).get("lastmes").toString());
			TextView time=(TextView)view.findViewById(R.id.time);	
			time.setText(list.get(position).get("time").toString());	
			TextView quntag=(TextView)view.findViewById(R.id.quntag);	
			quntag.setText(list.get(position).get("type").toString());	
			TextView renshu=(TextView)view.findViewById(R.id.renshu);	
			renshu.setText(list.get(position).get("renshu").toString());
			TextView grade=(TextView)view.findViewById(R.id.grade);	
			grade.setText(list.get(position).get("grade").toString());	
			
	
		}else{
			view = mInflater.inflate(R.layout.listview_person, null);//选择个人聊天布局显示	
			//从适配器获取个人聊天数据
		//	String content=list.get(position).get("data").toString();
			//分离数据
		//	String []items=content.split(",");
			
			//设置个人聊天控件显示内容
			ImageView img_pers=(ImageView)view.findViewById(R.id.img_pers);
			img_pers.setImageResource(getSource.getResourceByReflect(list.get(position).get("img").toString()));
			TextView qunname_pers=(TextView)view.findViewById(R.id.qunname_pers);	
			qunname_pers.setText(list.get(position).get("name").toString());	
			TextView lastmes_pers=(TextView)view.findViewById(R.id.lastmes_pers);	
			lastmes_pers.setText(list.get(position).get("lastmes").toString());
			TextView time_pers=(TextView)view.findViewById(R.id.time_pers);	
			time_pers.setText(list.get(position).get("time").toString());	

		}
				
		return view;
	}

}

