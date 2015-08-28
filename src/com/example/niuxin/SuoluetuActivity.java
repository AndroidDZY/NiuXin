package com.example.niuxin;

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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;


@SuppressLint("Instantiatable")
public class SuoluetuActivity {
	public Handler handler;
	private SharePreferenceUtil util = null;
	List<Map<Integer, String>> list = new LinkedList<Map<Integer, String>>();
	private Button edit,btn01,btn02,btn03,btn04,btn05,btn06,btn07,btn08;
	private ImageButton zhankai;
	private Activity act;
	private List<Button> btList = new LinkedList<Button>();
	public SuoluetuActivity(final Activity act,Handler handler) {	
		this.act = act;
		util = new SharePreferenceUtil(act, Constants.SAVE_USER);
		this.handler = handler;
		initbind();
		
		TestThread thread = new TestThread();
		thread.start();
	}
	
	
	class TestThread extends Thread {
		@Override
		public void run() {
			
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("id", util.getId());
				jArray.put(jsonObject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectByUserId.do");
			// 不向服务器发送数据
			postUtil.setRequest(jArray);

			// 从服务器获取数据
			final String res = postUtil.run();
			
			Runnable r = new Runnable() {
				@Override
				public void run() {
					// 这里可以写上更新UI的代码
					// 对从服务器获取数据进行解析
					if(res==null||"".equals(res)){
						return;
					}
					JSONArray jsonArray = null;
					try {
						jsonArray = new JSONArray(res);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					for (int i = 0; i < jsonArray.length(); i++) {
						try {
							if(i>6){
								break;
							}
							JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
							Map<Integer, String> map = new HashMap<Integer, String>();
							// 获取每一个对象中的值
							int id = myjObject.getInt("id");
							String name = myjObject.getString("name");
							String info = "1.00%6.53"; //这个数据暂时先设置一个假的值
							String str = name+"\n"+info;
							map.put(id, "btn0"+i);
							list.add(map);
							btList.get(i).setText(str); //		
							btList.get(i).setLines(2);
							btList.get(i).setTextColor(Color.parseColor("#de4557"));
						
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}

			};
			handler.post(r);
		}
	}

	
	
	
    public ImageButton getZhankai() {
		return zhankai;
	}

	public void setZhankai(ImageButton zhankai) {
		this.zhankai = zhankai;
	}

	public Button getEdit() {
		return edit;
	}

	public void setEdit(Button edit) {
		this.edit = edit;
	}

	public Button getBtn01() {
		return btn01;
	}

	public void setBtn01(Button btn01) {
		this.btn01 = btn01;
	}

	public Button getBtn02() {
		return btn02;
	}

	public void setBtn02(Button btn02) {
		this.btn02 = btn02;
	}

	public Button getBtn03() {
		return btn03;
	}

	public void setBtn03(Button btn03) {
		this.btn03 = btn03;
	}

	public Button getBtn04() {
		return btn04;
	}

	public void setBtn04(Button btn04) {
		this.btn04 = btn04;
	}

	public Button getBtn05() {
		return btn05;
	}

	public void setBtn05(Button btn05) {
		this.btn05 = btn05;
	}

	public Button getBtn06() {
		return btn06;
	}

	public void setBtn06(Button btn06) {
		this.btn06 = btn06;
	}

	public Button getBtn07() {
		return btn07;
	}

	public void setBtn07(Button btn07) {
		this.btn07 = btn07;
	}

	public Button getBtn08() {
		return btn08;
	}

	public void setBtn08(Button btn08) {
		this.btn08 = btn08;
	}

	
	public void initbind(){
		
		zhankai=(ImageButton)act.findViewById(R.id.btn_open);
		edit=(Button)act.findViewById(R.id.btn_edit);
		btn01=(Button)act.findViewById(R.id.btn01);
		btList.add(0,btn01);
		btn02=(Button)act.findViewById(R.id.btn02);
		btList.add(1,btn02);
		btn03=(Button)act.findViewById(R.id.btn03);
		btList.add(2,btn03);
		btn04=(Button)act.findViewById(R.id.btn04);
		btList.add(3,btn04);
		btn05=(Button)act.findViewById(R.id.btn05);
		btList.add(4,btn05);
		btn06=(Button)act.findViewById(R.id.btn06);
		btList.add(5,btn06);
		btn07=(Button)act.findViewById(R.id.btn07);
		btList.add(6,btn07);
		btn08=(Button)act.findViewById(R.id.btn08);
		btList.add(7,btn08);
		

		
		
		

		// 点击展开按钮跳转到看盘详情页五档 
		zhankai.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				act.startActivity(new Intent (act, kanpan_wudangActivity.class) );
			}
		});
		
		// 点击编辑自选按钮跳转到编辑自选界面 
		edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				act.startActivity(new Intent (act, edit_zixuanActivity.class) );
			}
		});
		
		//点击btn01-btn08都跳转到添加自选界面
		btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
		btn08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				act.startActivity(new Intent (act, zixuan_addActivity.class) );
			}
		});
	}
}

