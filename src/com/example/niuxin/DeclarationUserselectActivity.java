package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;










import java.util.Map;

import com.example.niuxin.HaoyouAdapter.ViewHolder;
import com.niuxin.util.Constants;
import com.niuxin.util.SharePreferenceUtil;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeclarationUserselectActivity extends Activity{
	private Button backButton,allButton,saveButton;
	private ListView listView;
	//private ArrayList<String> list;
	private List<HashMap<String, Object>> list;
	private HaoyouAdapter haoyouAdapter;
	private List<HashMap<String, Object>> beSelectedData = new ArrayList<HashMap<String, Object>>();    
    private int checkNum;
	EditText text = null;
	List<String> haoyouList= new ArrayList<String>();
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
		haoyouAdapter = new HaoyouAdapter(list,this);//创建一个适配器  
		listView.setAdapter(haoyouAdapter);
		//返回
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (haoyouList!=null) {
					Intent intent=new Intent();
					intent.putStringArrayListExtra("haoyouList", (ArrayList<String>) haoyouList);
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
                    HaoyouAdapter.getIsSelected().put(i, true);  
                }  
                beSelectedData=list;
                // 数量设为list的长度  
                haoyouAdapter.notifyDataSetChanged();
                checkNum = list.size();  
                // 刷新listview和TextView的显示  
               
            }  
        }); 
		// 保存按钮
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
                HaoyouAdapter.getIsSelected().put(arg2, holder.cb.isChecked());  
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

}
