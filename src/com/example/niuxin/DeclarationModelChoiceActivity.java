package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.niuxin.HaoyouAdapter.ViewHolder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DeclarationModelChoiceActivity extends Activity{
	
	private ListView listView;
	private List<HashMap<String, Object>> mData;  
    private Button buttonBack , saveButton ;
	private ImageView addImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_launch_model);
		//返回按钮
		buttonBack=(Button)findViewById(R.id.declaration_button_back);
		//保存按钮
		saveButton=(Button)findViewById(R.id.declaration_button_save);
		addImageView=(ImageView)findViewById(R.id.declaration_imageview_add);
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);//创建一个适配器  
		listView=(ListView)findViewById(R.id.declaration_list_modelchoice);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ViewHolder viewHolder=(ViewHolder) arg1.getTag(); 
				viewHolder.checkBox.isChecked();
                viewHolder.checkBox.toggle();// 
                System.out.println("oooood");
			}
		});
		
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
		//添加按钮
		addImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
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
		public View getView(final int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
        	ViewHolder holder = null;  
            if (convertView == null) {  
                // 获得ViewHolder对象  
                holder = new ViewHolder();  
                // 导入布局并赋值给convertview  
                convertView = mInflater.inflate(R.layout.decla_detail_model, null);//根据布局文件实例化view 
                holder.editiImageView = (ImageView)convertView.findViewById(R.id.decla_button_edit); 
               // holder.editText =(EditText) convertView.findViewById(R.id.decla_textview_model);  
                holder.checkBox = (CheckBox) convertView.findViewById(R.id.decla_checkbox); 
                holder.delImageView = (ImageView)convertView.findViewById(R.id.decla_button_del);
                // 为view设置标签  
                convertView.setTag(holder);  
            } else {  
                // 取出holder  
                holder = (ViewHolder) convertView.getTag();  
            } 
        	//convertView = mInflater.inflate(R.layout.decla_detail_model, null);//根据布局文件实例化view 
           // CheckBox checkBox=(CheckBox)convertView.findViewById(R.id.decla_checkbox);
            holder.checkBox.setText(mData.get(position).get("checkBox").toString());
            final EditText modelText = (EditText) convertView.findViewById(R.id.decla_textview_model);
            modelText.setText(mData.get(position).get("modeltext").toString());
           // ImageView editButton=(ImageView)convertView.findViewById(R.id.decla_button_edit);
            holder.editiImageView.setBackgroundResource(Integer.valueOf(mData.get(position).get("modeledit").toString()));
           // ImageView delButton=(ImageView)convertView.findViewById(R.id.decla_button_del);
            holder.delImageView.setBackgroundResource(Integer.valueOf(mData.get(position).get("modeldelete").toString()));
            holder.editiImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println("111111");
					
					modelText.setFocusable(true);
					modelText.requestFocus();
				}
			});
            
            holder.delImageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println("iii");
					mData.remove(position);
					MyAdapter.this.notifyDataSetChanged();
				   // MyAdapter.notifyDataSetChanged();
				   // listView.invalidate();
				}
			});
            return convertView;
		}
       
    }
	 public static class ViewHolder {  
         EditText editText;
         ImageView editiImageView;
         ImageView delImageView;
         CheckBox checkBox;  
     }
	private List<HashMap<String, Object>> getData() {  
        // 新建一个集合类，用于存放多条数据  
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();  
        HashMap<String, Object> map = null;  
        for (int i = 1; i <= 3; i++) {  
            map = new HashMap<String, Object>();  
            map.put("checkBox",  ""); //r.drawable 
            map.put("modeltext", "模板"+i);
            map.put("modeledit", R.drawable.modeledit);
            map.put("modeldelete", R.drawable.modeldelete);
            list.add(map);  
        }  
  
        return list;  
    }  

}
