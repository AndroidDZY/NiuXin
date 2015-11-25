package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;










import java.util.Map;

import com.example.niuxin.DeclarationContactChoiceActivity.MyAdapter;
import com.example.niuxin.HaoyouAdapter.ViewHolder;
import com.niuxin.util.Constants;
import com.niuxin.util.SharePreferenceUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeclarationUserselectActivity extends Activity{
	private Button backButton,allButton,saveButton;
	private ListView listView;
	//private ArrayList<String> list;
	private List<HashMap<String, Object>> list;
	private HaoyouAdapter haoyouAdapter;
	public static HashMap<Integer, Boolean> isSelected;
	private List<HashMap<String, Object>> beSelectedData = new ArrayList<HashMap<String, Object>>();    
    private int checkNum;
	EditText text = null;
	List<String> haoyouList= new ArrayList<String>();
	@SuppressLint("UseSparseArrays")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.declaration_sendpurpose_haoyou);
		
		backButton=(Button)findViewById(R.id.declaration_sendpur_haoyouback);
		allButton=(Button)findViewById(R.id.declaration_sendpur_haoyouselectall);
		saveButton=(Button)findViewById(R.id.declaration_sendpur_haoyousave);
		listView=(ListView)findViewById(R.id.declaration_sendpurpose_haoyou_list);
		list=getData();
		// 初始化isSelected的数据  
		MyApplication	ap=(MyApplication)getApplication();
		List<String> oldlist=ap.getHaoyouList();
		isSelected = new HashMap<Integer, Boolean>();
		//for (int i = 0; i < oldlist.size(); i++) 
			//String name=oldlist.get(i);
			for (int j = 0; j < list.size(); j++) {  //循环匹配数据，如果有一样的数据则为选中状态
				String username=list.get(j).get("username").toString();
				isSelected.put(j, false);
				for (int i = 0; i < oldlist.size(); i++) {
					String name = oldlist.get(i);
					if (username.equals(name)) {
					  isSelected.put(j, true);
					}
				}
			}
		
	    	  
	    //haoyouAdapter = new HaoyouAdapter(list,this);//创建一个适配器  
		final MyAdapter adapter = new MyAdapter(this);//创建一个适配器  
		listView.setAdapter(adapter);
		//返回
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (haoyouList!=null) {
					Intent intent=new Intent();
					//intent.putStringArrayListExtra("haoyouList", (ArrayList<String>) haoyouList);
					//System.out.println(haoyouList);
					intent.setClass(DeclarationUserselectActivity.this, DeclarationDetailActivity.class);
					startActivity(intent);
				}
				finish();
			}
		});
		// 全选按钮的回调接口  
		allButton.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
                // 遍历list的长度，将MyAdapter中的map值全部设为true  
                for (int i = 0; i < list.size(); i++) {  
                    isSelected.put(i, true);  
                }  
                beSelectedData=list;
                // 数量设为list的长度  
                haoyouAdapter.notifyDataSetChanged();
                checkNum = list.size();  
                // 刷新listview和TextView的显示  
               
            }  
        }); 
		// 保存按钮，在保存按钮中把数据传到全局变量中
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (beSelectedData.size()!=0) {
					//点击保存获取到相关数据
					System.out.println(beSelectedData);
					for (int i = 0; i < beSelectedData.size(); i++) {
						String text=beSelectedData.get(i).get("username").toString();
						haoyouList.add(text);
					}
					MyApplication	appHaoyou=(MyApplication)getApplication();
					appHaoyou.setHaoyouList(haoyouList);
					appHaoyou.getSendList().addAll(haoyouList);
				}
				Toast toast = Toast.makeText(DeclarationUserselectActivity.this, "保存成功", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		// listview item点击监听
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				 // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤  
                ViewHolder holder = (ViewHolder) arg1.getTag();  
                // 改变CheckBox的状态  
                holder.cb.toggle();  
                // 将CheckBox的选中状况记录下来  
                isSelected.put(arg2, holder.cb.isChecked());  
                // 调整选定条目  
                if (holder.cb.isChecked()) {
					 System.out.println(list.get(arg2));
					 beSelectedData.add(list.get(arg2));
				}
                if (holder.cb.isChecked() == true) {  
                    checkNum++;  
                } else {  
                    checkNum--;  
                }  
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
        	if (list!=null||list.size()!=0) {
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
				convertView = mInflater.inflate(R.layout.listview_declaration_sendpurpose_haoyou, null);
	            holder.im = (ImageView)convertView.findViewById(R.id.decla_haoyouitem_touxiang); 
	            holder.tv = (TextView) convertView.findViewById(R.id.decla_haoyouitem_name);  
	            holder.cb = (CheckBox) convertView.findViewById(R.id.haoyou_item_cb);  
	            convertView.setTag(holder);
	        }else{
	        	holder = (ViewHolder) convertView.getTag();
	        }
			holder.tv.setText(list.get(position).get("username").toString());
	        holder.im.setBackgroundResource(Integer.valueOf(list.get(position).get("touxiang").toString()));
	        holder.cb.setChecked(isSelected.get(position));
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
	            map.put("touxiang", R.drawable.detail_content_touxiang ); //r.drawable 
	            map.put("username", "好友"+i);  
	            list.add(map);  
	        }  
	  
	        return list;  
	    } 
		public static class ViewHolder {  
	        TextView tv;
	        ImageView im;
	        CheckBox cb;  
	    }

}
