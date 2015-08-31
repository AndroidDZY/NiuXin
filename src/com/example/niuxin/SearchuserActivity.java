package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchuserActivity extends Activity {
	RelativeLayout relativeLayout1, relativeLayout2;
	LinearLayout linearLayout1, linearLayout2;
	TextView title1, title2, summary1, summary2, type1, type2, people1, people2;
	Button cancle, done, search, searchcancel;
	EditText editText;
	CheckBox checkBox3, checkBox4;
	Spinner su_qunzuleixing,su_paihangleixing,su_shoufeileixing,su_qunzurenshu,su_qunzupingfen;
	// 1
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	String recomandres = null;
	List<Integer> idlist = new ArrayList<Integer>();
	List<Boolean> marklist =  new ArrayList<Boolean>();
	int mark = 0;
	@Override
	protected void onResume() {
		super.onResume();
		// 准备从服务器端获取数据，显示listView。因为从服务器获取数据是一个耗时的操作，所以需要在线程中进行。下面代码新建了一个线程对象。

	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.searchuser);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		// 2
	
		suolue = new SuoluetuActivity(this, handler);
		init();
		GetThread thread = new GetThread();
		thread.start();

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
				
				su_qunzuleixing.getSelectedItem().toString();
				
				jsonObject.put("id", util.getId());
				jsonObject.put("searchText", editText.getText().toString());				
				jsonObject.put("type", su_qunzuleixing.getSelectedItem().toString());
				jsonObject.put("isfree", su_shoufeileixing.getSelectedItem().toString());
				jsonObject.put("score", su_qunzupingfen.getSelectedItem().toString());	
				if(su_paihangleixing.getSelectedItem()!=null){
					jsonObject.put("paimingtype", su_paihangleixing.getSelectedItem().toString());
				}
				if(su_qunzurenshu.getSelectedItem()!=null){
					jsonObject.put("total_number", su_qunzurenshu.getSelectedItem().toString());
				}
						
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
			postUtil.setUrl("/search/search_serachGroup.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			recomandres = postUtil.run();

			Runnable r = new Runnable() {
				@Override
				public void run() {					
					for(int i=0;i<marklist.size();i++){
						marklist.set(i, false);
						relativeLayout1.setVisibility(View.INVISIBLE);
						title1.setText(null);
						summary1.setText(null);
						type1.setText(null);
						people1.setText(null);						
						checkBox3.setVisibility(View.INVISIBLE);
						relativeLayout2.setVisibility(View.INVISIBLE);
						title2.setText(null);							
						summary2.setText(null);
						type2.setText(null);
						people2.setText(null);
						checkBox4.setVisibility(View.INVISIBLE);
						
					}
					if (recomandres == null || "".equals(recomandres)) {
						return;
					}
					// 对从服务器获取数据进行解析
					JSONArray jsonArray = null;
					try {
						jsonArray = new JSONArray(recomandres);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					idlist .clear();
					if (jsonArray.length() == 1) {
						try {
							JSONObject myjObject = jsonArray.getJSONObject(0);// 获取每一个JsonObject对象
							Map<String, Object> map = new HashMap<String, Object>();
							// 获取每一个对象中的值

							Integer id = myjObject.getInt("id");
							String name = myjObject.getString("name");
							String mark = myjObject.getString("mark");
							String type = myjObject.getString("type") + "①";
							String peoplenum = myjObject.getString("currentNumber") + "/"
									+ myjObject.getString("totalNumber");
							relativeLayout1.setVisibility(View.VISIBLE);
							title1.setText(name);
							summary1.setText(mark);
							type1.setText(type);
							people1.setText(peoplenum);
							checkBox3.setVisibility(View.VISIBLE);
							checkBox3.setBackgroundResource(R.drawable.star1);
							idlist.add(id);
						}catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					else{
						try {
							JSONObject myjObject = jsonArray.getJSONObject(0);// 获取每一个JsonObject对象						
							// 获取每一个对象中的值
							Integer id = myjObject.getInt("id");
							String name = myjObject.getString("name");
							String mark = myjObject.getString("mark");
							String type = myjObject.getString("type") + "①";
							String peoplenum = myjObject.getString("currentNumber") + "/"
									+ myjObject.getString("totalNumber");
							relativeLayout1.setVisibility(View.VISIBLE);
							title1.setText(name);
							summary1.setText(mark);
							type1.setText(type);
							people1.setText(peoplenum);	
							checkBox3.setVisibility(View.VISIBLE);
							checkBox3.setBackgroundResource(R.drawable.star1);
							idlist.add(id);
							JSONObject myjObject1 = jsonArray.getJSONObject(1);// 获取每一个JsonObject对象
					
							// 获取每一个对象中的值

							Integer id1 = myjObject1.getInt("id");
							String name1 = myjObject1.getString("name");
							String mark1 = myjObject1.getString("mark");
							String type1 = myjObject1.getString("type") + "①";
							String peoplenum1 = myjObject1.getString("currentNumber") + "/"
									+ myjObject.getString("totalNumber");
							relativeLayout2.setVisibility(View.VISIBLE);
							title2.setText(name1);							
							summary2.setText(mark1);
							type2.setText(type1);
							people2.setText(peoplenum1);
							checkBox4.setVisibility(View.VISIBLE);
							checkBox4.setBackgroundResource(R.drawable.star1);
							idlist.add(id1);
						}catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					marklist.clear();
					for(int i=0;i<idlist.size();i++){
						marklist.add(false);
					}
				}

			};
			handler.post(r);
		}
	}


	class PostThread extends Thread {
		@Override
		public void run() {

			if(	idlist.size()==0){
				finish();
				return;
			}
			if(mark==1)
				return;
			mark=1;
			
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			// 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			try {
				
				
				String grouplist = "";
				for(int i=0;i<idlist.size();i++){
					if(marklist.get(i)==true){
						grouplist += idlist.get(i).toString()+",";
					}
				}
				if("".equals(grouplist)){
					mark=0;
					return;
				}
				jsonObject.put("id", util.getId());
				jsonObject.put("grouplist", grouplist);
				
						
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
			postUtil.setUrl("/qun/qun_insertGroupList.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			 postUtil.run();

			Runnable r = new Runnable() {
				@Override
				public void run() {		
					mark = 0;
					finish();
				}

			};
			handler.post(r);
		}
	}

	
	class GetThread extends Thread {
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
			postUtil.setUrl("/search/search_recommendGroup.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			recomandres = postUtil.run();

			Runnable r = new Runnable() {
				@Override
				public void run() {
					if (recomandres == null || "".equals(recomandres)) {
						relativeLayout1.setVisibility(View.INVISIBLE);
						relativeLayout2.setVisibility(View.INVISIBLE);
						return;
					}
					// 对从服务器获取数据进行解析
					JSONArray jsonArray = null;
					try {
						jsonArray = new JSONArray(recomandres);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					idlist .clear();
					if (jsonArray.length() == 1) {
						try {
							JSONObject myjObject = jsonArray.getJSONObject(0);// 获取每一个JsonObject对象
							Map<String, Object> map = new HashMap<String, Object>();
							// 获取每一个对象中的值
							relativeLayout1.setVisibility(View.VISIBLE);
							Integer id = myjObject.getInt("id");
							String name = myjObject.getString("name");
							String mark = myjObject.getString("mark");
							String type = myjObject.getString("type") + "①";
							String peoplenum = myjObject.getString("currentNumber") + "/"
									+ myjObject.getString("totalNumber");
							title1.setText(name);
							summary1.setText(mark);
							type1.setText(type);
							people1.setText(peoplenum);
							checkBox3.setBackgroundResource(R.drawable.star1);
							idlist.add(id);
						}catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					else{
						try {
							JSONObject myjObject = jsonArray.getJSONObject(0);// 获取每一个JsonObject对象						
							// 获取每一个对象中的值
							relativeLayout1.setVisibility(View.VISIBLE);
							relativeLayout2.setVisibility(View.VISIBLE);
							Integer id = myjObject.getInt("id");
							String name = myjObject.getString("name");
							String mark = myjObject.getString("mark");
							String type = myjObject.getString("type") + "①";
							String peoplenum = myjObject.getString("currentNumber") + "/"
									+ myjObject.getString("totalNumber");
							title1.setText(name);
							summary1.setText(mark);
							type1.setText(type);
							people1.setText(peoplenum);						
							checkBox3.setBackgroundResource(R.drawable.star1);
							idlist.add(id);
							JSONObject myjObject1 = jsonArray.getJSONObject(1);// 获取每一个JsonObject对象
					
							// 获取每一个对象中的值

							Integer id1 = myjObject1.getInt("id");
							String name1 = myjObject1.getString("name");
							String mark1 = myjObject1.getString("mark");
							String type1 = myjObject1.getString("type") + "①";
							String peoplenum1 = myjObject1.getString("currentNumber") + "/"
									+ myjObject.getString("totalNumber");
							title2.setText(name1);
							
							summary2.setText(mark1);
							type2.setText(type1);
							people2.setText(peoplenum1);
							checkBox4.setBackgroundResource(R.drawable.star1);
							idlist.add(id1);
						}catch (JSONException e) {
							e.printStackTrace();
						}
						
					}
					marklist.clear();
					for(int i=0;i<idlist.size();i++){
						marklist.add(false);
					}
				}

			};
			handler.post(r);
		}
	}

	public void init() {

		// 获取各种控件
		cancle = (Button) findViewById(R.id.btn_searchuser_cancle);
		done = (Button) findViewById(R.id.btn_searchuser_finish);
		search = (Button) findViewById(R.id.su_search);
		searchcancel = (Button) findViewById(R.id.su_searchcancel);
		editText = (EditText) findViewById(R.id.su_edittext);
		// 8月28号改动 增加群组推荐的各个部分的监听
		relativeLayout1 = (RelativeLayout) findViewById(R.id.searchuser_relativelayout1);
		relativeLayout2 = (RelativeLayout) findViewById(R.id.searchuser_relativelayout2);
		checkBox3 = (CheckBox) findViewById(R.id.searchuser_relativelayout1_star1);
		checkBox4 = (CheckBox) findViewById(R.id.searchuser_relativelayout2_star2);
		title1 = (TextView) findViewById(R.id.searchuser_relativelayout1_title1);
		title2 = (TextView) findViewById(R.id.searchuser_relativelayout2_title2);
		summary1 = (TextView) findViewById(R.id.searchuser_relativelayout1_summary1);
		summary2 = (TextView) findViewById(R.id.searchuser_relativelayout2_summary2);
		people1 = (TextView) findViewById(R.id.searchuser_relativelayout1_people1);
		people2 = (TextView) findViewById(R.id.searchuser_relativelayout2_people2);
		type1 = (TextView) findViewById(R.id.searchuser_relativelayout1_type1);
		type2 = (TextView) findViewById(R.id.searchuser_relativelayout2_type2);

	//	 su_qunzuleixing,su_paihangleixing,su_shoufeileixing,su_qunzurenshu,su_qunzupingfen;
		
		su_qunzuleixing = (Spinner)findViewById(R.id.su_qunzuleixing); 
		su_paihangleixing = (Spinner)findViewById(R.id.su_paihangleixing); 
		su_shoufeileixing = (Spinner)findViewById(R.id.su_shoufeileixing); 
		su_qunzurenshu = (Spinner)findViewById(R.id.su_qunzurenshu); 
		su_qunzupingfen = (Spinner)findViewById(R.id.su_qunzupingfen); 
		
		
		// 监听checkbox，当点击的时候加载黄色星星
		checkBox3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					checkBox3.setBackgroundResource(R.drawable.star2);
					marklist.set(0,true);
				} else {
					checkBox3.setBackgroundResource(R.drawable.star1);
					marklist.set(0,false);
				}
			}
		});
		checkBox4.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1) {
					checkBox4.setBackgroundResource(R.drawable.star2);
					marklist.set(1,true);
				} else {
					checkBox4.setBackgroundResource(R.drawable.star1);
					marklist.set(1,false);
				}
			}
		});
		cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				PostThread t = new PostThread();
				t.start();

			}
		});
		searchcancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
			}
		});
		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// 判断编辑框中输入的内容是否为空
				if (editText.getText().toString().length() == 0) {
					Toast.makeText(getApplicationContext(), "请输入名称关键字或股票代码", 0).show();

				} else {
					//Intent intent = new Intent(SearchuserActivity.this, SearchresultActivity.class);
					//startActivity(intent);
					
					SearchThread st = new SearchThread();
					st.start();
				}
			}
		});
	}
}
