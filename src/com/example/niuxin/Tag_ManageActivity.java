package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Tag_ManageActivity extends Activity{
	Button btn_tag_cancle,btn_tag_add;
	ImageButton btn_tag_delete;
	ListView listView;
	private int tag_flag = R.drawable.edit_flag01;
	//实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	//1
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.tag_manage);
		//2
		suolue = new SuoluetuActivity(this,handler);
		//获取Button
		btn_tag_cancle = (Button)findViewById(R.id.btn_tag_cancle);
//		btn_tag_finish = (Button)findViewById(R.id.btn_tag_finish);
		btn_tag_add = (Button)findViewById(R.id.btn_tag_add);
		btn_tag_delete = (ImageButton)findViewById(R.id.btn_tag_delete);
		
		//为Button绑定点击事件
		//取消按钮
		btn_tag_cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
		//新建标签按钮
		btn_tag_add.setOnClickListener(new OnClickListener() {
			View myView=LayoutInflater.from(getApplication()).inflate(R.layout.edittextview, null);//将layout对象转换为VIew对象
			AlertDialog.Builder builder = new AlertDialog.Builder(Tag_ManageActivity.this);
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 builder.setTitle("新建标签");
				 //builder.setMessage("确定要删除这些标签吗？");
				 builder.setView(myView);
			       builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			           public void onClick(DialogInterface dialog, int whichButton) {  
			               //这里添加点击确定后的逻辑  
			              // showDialog("你选择了确定");  
			           }  
			       });  
			       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
			           public void onClick(DialogInterface dialog, int whichButton) {  
			               //这里添加点击确定后的逻辑  
			               //showDialog("你选择了取消");  
			           }  
			       });  
			       builder.create().show();  	
			}
		});
		
		//删除标签按钮
		btn_tag_delete.setOnClickListener(new OnClickListener() {
			//创建一个对话框
			AlertDialog.Builder builder = new AlertDialog.Builder(Tag_ManageActivity.this); 
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//builder.setIcon(R.drawable.icon); 
				
			       //builder.setTitle("确定要删除这些标签吗？");
			       builder.setMessage("确定要删除这些标签吗？");
			       builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			           public void onClick(DialogInterface dialog, int whichButton) {  
			               //这里添加点击确定后的逻辑  
			              // showDialog("你选择了确定");  
			           }  
			       });  
			       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
			           public void onClick(DialogInterface dialog, int whichButton) {  
			               //这里添加点击确定后的逻辑  
			               //showDialog("你选择了取消");  
			           }  
			       });  
			       builder.create().show();  	
			}
		});
		

		
		list = getData();// 填充list数据
		listView = (ListView)findViewById(R.id.taglist);//获取ListView
		//创建适配器
		//第二个参数：list集合中的每一个Map对象对应生成一个列表项
		//第三个参数：表明使用taglistview.xml文件作为列表项组件
		//第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		//第五个参数：决定使用taglistview.xml文件中的哪些组件来填充列表项
		SimpleAdapter tagAdapter= new SimpleAdapter(this, list,R.layout.taglistview, 
				new String[]{"img_tag_flag","tag_name","img_tag_edit"},
				new int[]{R.id.img_tag_flag,R.id.tag_name,R.id.img_tag_edit});
		listView.setAdapter(tagAdapter);//为listView设置适配器
		
		//实现点击不同的item，奇数偶数次点击更换imageview显示
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent , View view , int position ,
					long id ) {		
				SimpleAdapter adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
	            Map<String,Object> map=(Map<String, Object>) adapter.getItem(position);//找到被点击的列表项
	            if(Integer.valueOf(map.get("img_tag_flag").toString())==R.drawable.edit_flag01){
	            	tag_flag = R.drawable.edit_flag02;
	            }else
	            	tag_flag = R.drawable.edit_flag01;
	         
	            list.get(position).put("img_tag_flag", tag_flag);//将更新过的add_flag值放入list中        
	            adapter.notifyDataSetInvalidated();//使更新过的list数据生效
			}
		});
	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = getList();
		return list;
	}
	private List<Map<String, Object>> getList() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//map.put("id", 1);
		map.put("img_tag_flag", tag_flag);
		map.put("tag_name", "标签1");
		map.put("img_tag_edit", R.drawable.tag_edit);
		list.add(map);
		
		map = new HashMap<String, Object>();
		//map.put("id", 2);
		map.put("img_tag_flag", tag_flag);
		map.put("tag_name", "标签2");
		map.put("img_tag_edit", R.drawable.tag_edit);
		list.add(map);

		map = new HashMap<String, Object>();
		//map.put("id", 3);
		map.put("img_tag_flag", tag_flag);
		map.put("tag_name", "标签3");
		map.put("img_tag_edit", R.drawable.tag_edit);
		list.add(map);

		return list;
	}
}

