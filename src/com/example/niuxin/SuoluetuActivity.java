package com.example.niuxin;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.HttpPostUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;


@SuppressLint("Instantiatable")
public class SuoluetuActivity {
	public Handler handler;
	
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

	private Button edit,btn01,btn02,btn03,btn04,btn05,btn06,btn07,btn08;
	private ImageButton zhankai;
	private Activity act;
	public SuoluetuActivity(final Activity act,Handler handler) {
		this.act = act;
		this.handler = handler;
		zhankai=(ImageButton)act.findViewById(R.id.btn_open);
		edit=(Button)act.findViewById(R.id.btn_edit);
		btn01=(Button)act.findViewById(R.id.btn01);
		btn02=(Button)act.findViewById(R.id.btn02);
		btn03=(Button)act.findViewById(R.id.btn03);
		btn04=(Button)act.findViewById(R.id.btn04);
		btn05=(Button)act.findViewById(R.id.btn05);
		btn06=(Button)act.findViewById(R.id.btn06);
		btn07=(Button)act.findViewById(R.id.btn07);
		btn08=(Button)act.findViewById(R.id.btn08);
		

		
		
		

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

	
	
	
	class GetDataThread extends Thread {
		private Dialog mDialog = null;
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil(handler);
			/*
			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("username", "huangwuyi");
				jsonObject.put("sex", "男");
				jsonObject.put("QQ", "413425430");
				jsonObject.put("Min.score", new Integer(99));
				jsonObject.put("nickname", "梦中心境");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			postUtil.setRequest(jsonObject);
			*/
			/*
			boolean isNetwork= postUtil.checkNetState(act);
			if(!isNetwork){
				mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
				mDialog.show();
				return;
			}*/
			
			//设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectAll.do");
			//不向服务器发送数据
			postUtil.setRequest(null);
			
			// 从服务器获取数据
			String res = postUtil.run();
			// 对从服务器获取数据进行解析
			JSONArray jsonArray = null;			
			try {
				jsonArray = new JSONArray(res);
			} catch (JSONException e) {
				e.printStackTrace();
			}	
			
			for (int i = 0; i < jsonArray.length(); i++) {				
				try {
					JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
					Map<String, Object> map = new HashMap<String, Object>();
					// 获取每一个对象中的值
					int id = myjObject.getInt("id");
					String number = myjObject.getString("number");
					String name = myjObject.getString("name");
					map.put("id", id);
					map.put("number", number);
					map.put("name", name);
				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			Runnable r = new Runnable() {
				@Override
				public void run() {
					// 这里可以写上更新UI的代码

					

				}

			};
			handler.post(r);
		}
	}
}

