package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Tag_ManageActivity extends Activity {
	Button btn_tag_cancle, btn_tag_add;
	ImageButton btn_tag_delete;
	ListView listView;
	private int tag_flag = R.drawable.edit_flag01;
	public static Activity act = null;

	private SharePreferenceUtil util = null;

	// 实例化一个LinkedList类(LinkedList集合中的对象是一个个Map对象,而这个Map对象的键是String类型,值是Object类型)的对象list
	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
	// 1
	private SuoluetuActivity suolue;
	lvButtonAdapter listItemAdapter = null;
	public Handler handler = new Handler();
	public String labname = "";
	 EditText text = null;

	@Override
	protected void onResume() {
		super.onResume();
		// 准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。
		GetAllThread thread = new GetAllThread();
		thread.start();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.tag_manage);
		// 2
		act = Tag_ManageActivity.this;
		suolue = new SuoluetuActivity(this, handler);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		// 获取Button
		btn_tag_cancle = (Button) findViewById(R.id.btn_tag_cancle);
		// btn_tag_finish = (Button)findViewById(R.id.btn_tag_finish);
		btn_tag_add = (Button) findViewById(R.id.btn_tag_add);
		btn_tag_delete = (ImageButton) findViewById(R.id.btn_tag_delete);

		// 为Button绑定点击事件
		// 取消按钮
		btn_tag_cancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		// 新建标签按钮
		btn_tag_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				add();
			}

			private void add() {
				// TODO Auto-generated method stub
				View myView = LayoutInflater.from(getApplication()).inflate(R.layout.edittextview, null);// 将layout对象转换为VIew对象
				AlertDialog.Builder builder = new AlertDialog.Builder(Tag_ManageActivity.this);
				builder.setTitle("新建标签");
				// builder.setMessage("确定要删除这些标签吗？");
				builder.setView(myView);
				 text =  (EditText)myView.findViewById(R.id.et_tag_name);
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 这里添加点击确定后的逻辑
						// showDialog("你选择了确定");
						 // EditText text =  (EditText)findViewById(R.id.et_tag_name);	
						String str = text.getText().toString();
			        	   if(str==null||"".equals(str.trim())){
			        		   Toast.makeText(act.getApplicationContext(), "标签名称不能为空!!!", 0).show();
			        		   return;
			        	   }
			        	   labname = str;
						AddThread add = new AddThread();
						add.start();
						dialog.dismiss();// 对话框消失
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 这里添加点击确定后的逻辑
						// showDialog("你选择了取消");
						dialog.dismiss();// 对话框消失
					}
				});
				builder.create().show();
			}
		});

		// 删除标签按钮
		btn_tag_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				delete();
			}

			private void delete() {
				// TODO Auto-generated method stub
				// 创建一个对话框
				AlertDialog.Builder builder = new AlertDialog.Builder(Tag_ManageActivity.this);
				// builder.setIcon(R.drawable.icon);

				// builder.setTitle("确定要删除这些标签吗？");
				builder.setMessage("确定要删除这些标签吗？");
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 准备删除数据
						DeleteThread d = new DeleteThread();
						d.start();
						dialog.dismiss();// 对话框消失
					}
				});
				builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// 这里添加点击确定后的逻辑
						// showDialog("你选择了取消");
						dialog.dismiss();// 对话框消失
					}
				});
				builder.create().show();
			}
		});

		// list = getData();// 填充list数据
		// listView = (ListView)findViewById(R.id.taglist);//获取ListView
		// 创建适配器
		// 第二个参数：list集合中的每一个Map对象对应生成一个列表项
		// 第三个参数：表明使用taglistview.xml文件作为列表项组件
		// 第四个参数：决定提取Map<String, Object>对象中的哪些key对应的value来生成列表项
		// 第五个参数：决定使用taglistview.xml文件中的哪些组件来填充列表项
		/*
		 * SimpleAdapter tagAdapter= new SimpleAdapter(this,
		 * list,R.layout.taglistview, new
		 * String[]{"img_tag_flag","tag_name","img_tag_edit"}, new
		 * int[]{R.id.img_tag_flag,R.id.tag_name,R.id.img_tag_edit});
		 * listView.setAdapter(tagAdapter);//为listView设置适配器
		 */
		 listView = (ListView)findViewById(R.id.taglist);//获取ListView
		listItemAdapter = new lvButtonAdapter(this, list, R.layout.taglistview,
				new String[] { "img_tag_flag", "tag_name", "img_tag_edit" },
				new int[] { R.id.img_tag_flag, R.id.tag_name, R.id.img_tag_edit }, this);
		listView.setAdapter(listItemAdapter);

		// 实现点击不同的item，奇数偶数次点击更换imageview显示
		// listView.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> parent , View view , int
		// position ,
		// long id ) {
		// SimpleAdapter
		// adapter=(SimpleAdapter)parent.getAdapter();//找到被点击的Adapter
		// Map<String,Object> map=(Map<String, Object>)
		// adapter.getItem(position);//找到被点击的列表项
		// if(Integer.valueOf(map.get("img_tag_flag").toString())==R.drawable.edit_flag01){
		// tag_flag = R.drawable.edit_flag02;
		// }else
		// tag_flag = R.drawable.edit_flag01;
		//
		// list.get(position).put("img_tag_flag",
		// tag_flag);//将更新过的add_flag值放入list中
		// adapter.notifyDataSetInvalidated();//使更新过的list数据生效
		// }
		// });
	}

	private ArrayList<HashMap<String, Object>> getData() {
		ArrayList<HashMap<String, Object>> list = getList();
		return list;
	}

	class GetAllThread extends Thread {
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

			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/lab/lab_select.do");
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
			list.clear();
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					HashMap<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					String name = myjObject.getString("name");
					map.put("id", id);
					map.put("img_tag_flag", tag_flag);
					map.put("tag_name", name);
					map.put("img_tag_edit", R.drawable.tag_edit);
					list.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					listItemAdapter.notifyDataSetChanged();

				}

			};
			handler.post(r);
		}
	}

	class DeleteThread extends Thread {
		@Override
		public void run() {

			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();

			if (list == null) {
				return;
			}
			ArrayList<HashMap<String, Object>> list1 = new ArrayList<HashMap<String, Object>>();
			for (int i = 0; i < list.size(); i++) {

				if (Integer.valueOf(list.get(i).get("img_tag_flag").toString()) == R.drawable.edit_flag02) {
					JSONObject jsonObject = new JSONObject();
					try {
						jsonObject.put("id", list.get(i).get("id").toString());// 标签的id

					} catch (JSONException e) {
						e.printStackTrace();
					}
					jArray.put(jsonObject);
				}else{
					list1.add(list.get(i));
				}
			}
			list = list1;
			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/lab/lab_delete.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			postUtil.run();
			Runnable r = new Runnable() {
				@Override
				public void run() {
					listItemAdapter.setmAppList(list);
					listItemAdapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
		}
	}

	class AddThread extends Thread {
		@Override
		public void run() {

			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();

			JSONObject jsonObject = new JSONObject();
			try {
				
				
				jsonObject.put("userid", util.getId());// 标签的id
				jsonObject.put("name", labname);// 标签的id

			} catch (JSONException e) {
				e.printStackTrace();
			}
			jArray.put(jsonObject);

			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/lab/lab_insert.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);
			// 从服务器获取数据
						String res = postUtil.run();
						JSONArray jsonArray = null;
						try {
							jsonArray = new JSONArray(res);
						} catch (JSONException e) {
							e.printStackTrace();
						}
						try {
							JSONObject myjObject = jsonArray.getJSONObject(0);// 获取每一个JsonObject对象
							HashMap<String, Object> map = new HashMap<String, Object>();
							// 获取每一个对象中的值
							int id = myjObject.getInt("id");
							map.put("id", id);
							map.put("tag_name", labname);
							map.put("img_tag_flag", tag_flag);
							map.put("img_tag_edit", R.drawable.tag_edit);
							list.add(map);
						} catch (JSONException e) {
							e.printStackTrace();
						}
	

		Runnable r = new Runnable() {
				@Override
				public void run() {					
					listItemAdapter.setmAppList(list);
					listItemAdapter.notifyDataSetChanged();
					//listItemAdapter.setSelection(list.size() - 1);
				}

			};
			handler.post(r);
	}
}

	private ArrayList<HashMap<String, Object>> getList() {
		HashMap<String, Object> map = new HashMap<String, Object>();

		// map.put("id", 1);
		map.put("img_tag_flag", tag_flag);
		map.put("tag_name", "标签1");
		map.put("img_tag_edit", R.drawable.tag_edit);
		list.add(map);

		map = new HashMap<String, Object>();
		// map.put("id", 2);
		map.put("img_tag_flag", tag_flag);
		map.put("tag_name", "标签2");
		map.put("img_tag_edit", R.drawable.tag_edit);
		list.add(map);

		map = new HashMap<String, Object>();
		// map.put("id", 3);
		map.put("img_tag_flag", tag_flag);
		map.put("tag_name", "标签3");
		map.put("img_tag_edit", R.drawable.tag_edit);
		list.add(map);

		return list;
	}
}
