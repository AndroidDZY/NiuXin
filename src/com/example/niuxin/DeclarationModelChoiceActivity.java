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
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeclarationModelChoiceActivity extends Activity {

	private ListView listView;
	private List<HashMap<String, Object>> mData = new LinkedList<HashMap<String, Object>>();
	private Button buttonBack, saveButton;
//	private ImageButton addImageButton;
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	public static HashMap<Integer, Boolean> isSelected =new HashMap<Integer, Boolean>();;
	private List<HashMap<String, Object>> beSelectedData = new ArrayList<HashMap<String, Object>>();
	String modelChange = null;
	String oldModelText;
	EditText text = null;
	String text1 = null;
	MyAdapter adapter;
	Integer deleteid;
	Integer updateid;
	Integer selectedid = -1;

	@Override
	protected void onResume() {
		super.onResume();
		// Intent intent = getIntent();

		GetTemplateThread gt = new GetTemplateThread();
		gt.start();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.declaration_launch_model);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		suolue = new SuoluetuActivity(this, handler);
		// 返回按钮
		buttonBack = (Button) findViewById(R.id.declaration_button_back);
		// 保存按钮
		saveButton = (Button) findViewById(R.id.declaration_button_save);
		if (beSelectedData.size() > 0) {
			beSelectedData.clear();
		}
		init();
		adapter = new MyAdapter(this);// 创建一个适配器
		listView = (ListView) findViewById(R.id.declaration_list_modelchoice);
		listView.setAdapter(adapter);
		// 模板选中监听
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if(mData!=null)
					for (int i = 0; i < mData.size(); i++) {
						isSelected.put(i, false);
					}
				beSelectedData.clear();
				selectedid = -1;				
				ViewHolder holder = (ViewHolder) arg1.getTag();
				holder.checkBox.toggle();
				isSelected.put(arg2, holder.checkBox.isChecked());
				adapter.notifyDataSetChanged();
				if (holder.checkBox.isChecked()) {
					beSelectedData.add(mData.get(arg2));
					selectedid = (Integer) mData.get(arg2).get("id");
				}
			}
		});
		// 返回监听
		buttonBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (text1 != null) {
					Intent intent = new Intent();
					intent.putExtra("modelText", text1.toString());
					intent.putExtra("selectedid",selectedid);
					setResult(13, intent);
				}
				finish();
			}
		});
		// 保存监听
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 获取数据保存到数据库
				if (beSelectedData.size() != 0) {
					Map<String, Object> map = beSelectedData.get(0);
					Object contractType = map.get("modeltext");
					text1 = contractType.toString();
					
					if (text1 != null) {
						Intent intent = new Intent();
						intent.putExtra("modelText", text1.toString());
						intent.putExtra("selectedid",selectedid);
						setResult(13, intent);
					}
					finish();
				}
				else {
					Toast.makeText(getApplicationContext(), "你没有选择模板类型！请选择", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	// 初始化设置所有checkbox都为未选择
	public void init() {
		Intent intent = getIntent();
		String mText = intent.getStringExtra("modelText");
		if (null != mData) {
			for (int i = 0; i < mData.size(); i++) {// 对合约类型进行循环，获取选中的初始化
				Map<String, Object> map = new HashMap<String, Object>();
				map = mData.get(i);
				String contractText = map.get("modeltext").toString();
				if (mText.trim().equals(contractText)) {// 如果传来的数据与其中的一条数据符合则设置checkbox为选中状态，获取到相应的数据
					isSelected.put(i, true);
					// beSelectedData=mData;
					beSelectedData.add(mData.get(i));
				} else {// 不匹配则返回false
					isSelected.put(i, false);
				}
			}
		}
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;// 动态布局映射

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		// 决定ListView有几行可见
		@Override
		public int getCount() {
			if (null != mData)
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

		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				// 获得ViewHolder对象
				holder = new ViewHolder();
				// 导入布局并赋值给convertview
				convertView = mInflater.inflate(R.layout.listview_decla_detail_model, null);// 根据布局文件实例化view
				holder.editiImageView = (ImageView) convertView.findViewById(R.id.decla_button_edit);
				holder.checkBox = (CheckBox) convertView.findViewById(R.id.decla_checkbox);
				holder.delImageView = (ImageView) convertView.findViewById(R.id.decla_button_del);
				// 为view设置标签
				convertView.setTag(holder);
			} else {
				// 取出holder
				holder = (ViewHolder) convertView.getTag();
			}
			holder.checkBox.setText(mData.get(position).get("checkBox").toString());
			holder.checkBox.setChecked(isSelected.get(position));
			final TextView modelText = (TextView) convertView.findViewById(R.id.decla_textview_model);
			modelText.setText(mData.get(position).get("modeltext").toString());
			oldModelText = modelText.getText().toString();// 获取到之前的模板的名称
			holder.editiImageView
					.setBackgroundResource(Integer.valueOf(mData.get(position).get("modeledit").toString()));
			holder.delImageView
					.setBackgroundResource(Integer.valueOf(mData.get(position).get("modeldelete").toString()));

			// 重命名模板监听
			holder.editiImageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					editModel();					
				}

				private void editModel() {
					View myView = LayoutInflater.from(getApplication()).inflate(R.layout.edittextview, null);// 将layout对象转换为VIew对象
					AlertDialog.Builder builder = new AlertDialog.Builder(DeclarationModelChoiceActivity.this);
					builder.setTitle("模板重命名");
					builder.setView(myView);
					text = (EditText) myView.findViewById(R.id.et_tag_name);
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							// 这里添加点击确定后的逻辑
							// 这边要加改变模板的名字
							if(null==text.getText()||null==text.getText().toString()||text.getText().toString().trim().equals("")){
								Toast toast = Toast.makeText(DeclarationModelChoiceActivity.this, "未填写模版名称", Toast.LENGTH_SHORT);
								toast.show();
								return;
							}	
							if(text.getText().toString().trim().length()>9){
								Toast toast = Toast.makeText(DeclarationModelChoiceActivity.this, "模版名称长度不能大于10个字符", Toast.LENGTH_SHORT);
								toast.show();
								return;
							}
							
							String mText = text.getText().toString();// 获取编辑内容
							modelText.setText(mText);// 改编辑内容为编辑的内容
							modelChange = modelText.getText().toString();
							updateid = position;
							UpdateThread uThread = new UpdateThread();
							uThread.start();
							Toast.makeText(DeclarationModelChoiceActivity.this, "模板重命名成功", Toast.LENGTH_SHORT).show();
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

			// 删除模板监听
			holder.delImageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					deleteModel();				
				}

				private void deleteModel() {
					// TODO Auto-generated method stub
					// 创建一个对话框
					AlertDialog.Builder builder = new AlertDialog.Builder(DeclarationModelChoiceActivity.this);
					builder.setMessage("确定要删除该模板吗？");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							
							if(position==0){
								Toast toast = Toast.makeText(DeclarationModelChoiceActivity.this, "该选项无法删除",
										Toast.LENGTH_SHORT);
								toast.show();
								return;
							}
							
							mData.remove(position);
							deleteid = position;
							MyAdapter.this.notifyDataSetChanged();
							dialog.dismiss();// 对话框消失
							DelThread delThread = new DelThread();
							delThread.start();
							Toast toast = Toast.makeText(DeclarationModelChoiceActivity.this, "模板已删除",
									Toast.LENGTH_SHORT);
							toast.show();
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
			return convertView;
		}

		// 编辑更新数据库中的模板名称
		class UpdateThread extends Thread {
			@Override
			public void run() {
				// 新建工具类，向服务器发送Http请求
				HttpPostUtil postUtil = new HttpPostUtil();
				JSONArray jArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("id", updateid);
					jsonObject.put("type", 2);
					jsonObject.put("name", modelChange);// 新模板名称					
					System.out.println(jArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// 设置发送的url 和服务器端的struts.xml文件对应
				postUtil.setUrl("/form/form_update.do");
				// 向服务器发送数据
				jArray.put(jsonObject);
				postUtil.setRequest(jArray);
				// 从服务器获取数据
				postUtil.run();
				Runnable r = new Runnable() {
					@Override
					public void run() {
						finish();
					}

				};
				handler.post(r);
			}
		}

		// 删除数据库中的模板名称
		class DelThread extends Thread {
			@Override
			public void run() {
				// 新建工具类，向服务器发送Http请求
				HttpPostUtil postUtil = new HttpPostUtil();
				JSONArray jArray = new JSONArray();
				JSONObject jsonObject = new JSONObject();
				try {
					jsonObject.put("id", deleteid);// 用户的ID
					jsonObject.put("type", 2);// 用户的ID			
				} catch (JSONException e) {
					e.printStackTrace();
				}
				// 设置发送的url 和服务器端的struts.xml文件对应
				postUtil.setUrl("/form/form_delete.do");
				// 向服务器发送数据
				jArray.put(jsonObject);
				postUtil.setRequest(jArray);
				// 从服务器获取数据
				postUtil.run();
				Runnable r = new Runnable() {
					@Override
					public void run() {
						finish();
					}
				};
				handler.post(r);
			}
		}
	}
	public static class ViewHolder {
		EditText editText;
		ImageView editiImageView;
		ImageView delImageView;
		CheckBox checkBox;
	}

	// 启动线程从数据库中获取数据，获取模板的名称
	class GetTemplateThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				Integer id = util.getId();
				jsonObject.put("id", id);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			postUtil.setUrl("/form/form_selectAllBySendId.do");// 根据用户的ID查询自己的已经有的模板名称
			// 向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);
			// 从服务器获取数据
			String res = postUtil.run();
			if (res == null) {
				return;
			}
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (null != mData)
				mData.clear();
			HashMap<String, Object> map0 = null;
			map0 = new HashMap<String, Object>();
			map0.put("checkBox", ""); // r.drawable
			map0.put("modeltext", "无");
			map0.put("id", -1);
			map0.put("modeledit", R.drawable.modeledit);
			map0.put("modeldelete", R.drawable.modeldelete);
			isSelected.put(0, false);
			mData.add(map0);
			
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					// 获取每一个对象中的值
					HashMap<String, Object> map = new HashMap<String, Object>();
					int id = myjObject.getInt("id");
					String name = myjObject.getString("name");// 模板名称
					map.put("id", id);
					map.put("checkBox", ""); // r.drawable
					map.put("modeltext", name);
					map.put("modeledit", R.drawable.modeledit);
					map.put("modeldelete", R.drawable.modeldelete);
					isSelected.put(i+1, false);
					mData.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} ///////////////////////////// 解析数据完成
			Runnable r = new Runnable() {
				@Override
				public void run() {
					
					init();
					
					adapter.notifyDataSetChanged();
				}

			};
			handler.post(r);
		}
	}

	// 向数据库中插入一个新的模板
	class AddThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {

				Integer id = util.getId();
				jsonObject.put("id", id);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			postUtil.setUrl("/form/form_insertTemplateBySend.do");// 插入新的模板
			// 向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);
			// 从服务器获取数据
			String res = postUtil.run();
			if (res == null) {
				return;
			}
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			mData.clear();
			// typelist.clear();
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					// 获取每一个对象中的值
					HashMap<String, Object> map = new HashMap<String, Object>();
					int id = myjObject.getInt("id");
					String name = myjObject.getString("name");// 模板名称
					map.put("name", name);// R.drawable.head010
					map.put("id", id);
					// typelist.add(0);
					mData.add(map);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} ///////////////////////////// 解析数据完成
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
