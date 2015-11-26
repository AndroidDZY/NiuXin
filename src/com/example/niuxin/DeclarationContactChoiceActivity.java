package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeclarationContactChoiceActivity extends Activity{
	
	private ListView listView;
	private List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
    private Button buttonBack , saveButton ;
    public static HashMap<Integer, Boolean> isSelected;
    private List<Map<String, Object>> beSelectedData = new ArrayList<Map<String, Object>>();    
    String text1=null;
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	 MyAdapter adapter = null;
	private SharePreferenceUtil util = null;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch_contact);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		suolue = new SuoluetuActivity(this, handler);
		//返回按钮
				buttonBack=(Button)findViewById(R.id.declaration_contact_button_back);
				//保存按钮
				saveButton=(Button)findViewById(R.id.declaration_contact_button_save);
				// 清除已经选择的项  
		        if (beSelectedData.size() > 0) {  
		            beSelectedData.clear();  
		        } 
		   //     mData = getData();
		        init();
				 adapter = new MyAdapter(this);//创建一个适配器  
				listView=(ListView)findViewById(R.id.declaration_list_contactchoice);
				listView.setAdapter(adapter);
				listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				//返回监听
				buttonBack.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						//如果没有获取到数据直接返回
						if (text1!=null) {
							Intent intent=new Intent();
							intent.putExtra("contractText",text1.toString());
							System.out.println(text1);
							setResult(11,intent);
						}
						finish();
					}
				});
				//保存监听
				saveButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						if (beSelectedData.size()!=0) {
							//点击保存获取到相关数据
							System.out.println(beSelectedData);
							Map<String, Object> map=beSelectedData.get(0);
							Object contractType=map.get("contractText");
							text1=contractType.toString();
							/*Intent intent=new Intent();
							intent.putExtra("contractText",contractType.toString());
							System.out.println(contractType);
							setResult(11,intent);*/
							//finish();
							//intent.setClass(DeclarationContactChoiceActivity.this, DeclarationDetailActivity.class);
							//startActivity(intent);
						}else {
							Toast.makeText(getApplicationContext(), "你没有选择合约类型！请选择", Toast.LENGTH_SHORT).show();
						}
						//获取数据保存到数据库
					}
				});
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						 for (int i = 0; i < list.size(); i++) {
				              isSelected.put(i, false);
				          }
						 beSelectedData.clear();
						// beSelectedData.clear();
						 System.out.println(beSelectedData+"111111111111111");
						 ViewHolder holder = (ViewHolder) arg1.getTag();
						 holder.checkBox.toggle();
						 isSelected.put(arg2, holder.checkBox.isChecked());
						 System.out.println(isSelected+"hao123"+arg2);
						 adapter.notifyDataSetChanged();
						 if (holder.checkBox.isChecked()) {
							 System.out.println(list.get(arg2));
							 beSelectedData.add(list.get(arg2));
						}
					}
				});
			}
    
			// 初始化设置所有checkbox都为未选择
		    public void init() {
		    	isSelected = new HashMap<Integer, Boolean>();
		        Intent intent=getIntent();
		    	String cText=intent.getStringExtra("contractText");
		    	for (int i = 0; i < list.size(); i++) {//对合约类型进行循环，获取选中的初始化
					Map<String, Object> map=new HashMap<String, Object>();
					map=list.get(i);
					String contractText=map.get("contractText").toString();
					if (cText.equals(contractText)) {//如果传来的数据与其中的一条数据符合则设置checkbox为选中状态，获取到相应的数据
						isSelected.put(i, true);
						//beSelectedData=mData;
						beSelectedData.add(list.get(i));
					}else {//不匹配则返回false
						isSelected.put(i, false);
					}
				}
		    	 /* for (int i = 0; i < mData.size(); i++) {
		              isSelected.put(i, false);
		          }*/
		    	
		    	
		    	SearchThread thread = new SearchThread();
				thread.start();

		    }
            
			public class MyAdapter extends BaseAdapter {  
		        private LayoutInflater mInflater;// 动态布局映射  
		  
		        public MyAdapter(Context context) {  
		            this.mInflater = LayoutInflater.from(context);  
		        }  
		        // 决定ListView有几行可见  
		        @Override  
		        public int getCount() {
		        	if (null!= list||list.size()!=0) {
		        		return list.size();// ListView的条目数
					}else{
						return 0;
					}
		        }  
		  
		        @Override  
		        public Object getItem(int arg0) {  
		        	if (list!=null) {
		        		return list.get(arg0);  
					}else{
						return null;
					}
		        }  
		  
		        @Override  
		        public long getItemId(int argo) {  
		            return argo;  
		        }  
		  
		        @Override
				public View getView(int position, View convertView, ViewGroup arg2) {
		        	ViewHolder holder=null;
					if (convertView==null) {
						holder=new ViewHolder();
						convertView = mInflater.inflate(R.layout.listview_decla_detail_contact, null);//根据布局文件实例化view 
						holder.checkBox=(CheckBox)convertView.findViewById(R.id.decla_contact_checkbox);
						holder.contractText = (TextView) convertView.findViewById(R.id.decla_textview_contact);
						convertView.setTag(holder);
			        }else{
			        	holder = (ViewHolder) convertView.getTag();
			        }
//					holder.checkBox.setText(mData.get(position).get("checkBox").toString());
		            holder.contractText.setText(list.get(position).get("contractText").toString());
		            holder.checkBox.setChecked(isSelected.get(position));
		            return convertView;
				}  
		    }
			private List<HashMap<String, Object>> getData() {  
		        // 新建一个集合类，用于存放多条数据  
		        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
		        HashMap<String, Object> map = null; 
		            
		        for (int i = 1; i <= 3; i++) {  
		            map = new HashMap<String, Object>();  
//		            map.put("checkBox",  i); //r.drawable 
		            map.put("contractText", "合约"+i);  
		            map.put("id", "合约"+i); 
		            list.add(map);  
		        }  
		  
		        return list;  
		    }  

	public static class ViewHolder{
		TextView contractText;
		CheckBox checkBox;
	}
	
	
	class SearchThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			// 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("id", util.getId());

			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/contract/contract_selectAll.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			String res = postUtil.run();
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if ( null!= list)
				list.clear();
			
			if (null != jsonArray)
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
						Map<String, Object> map = new HashMap<String, Object>();
						// 获取每一个对象中的值
						int id = myjObject.getInt("id");
						String type = myjObject.getString("type");
						
						map.put("id", id);
						map.put("contractText", type);
						isSelected.put(i, false);
						list.add(map);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
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
