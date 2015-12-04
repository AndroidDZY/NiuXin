package com.example.niuxin;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.Constants;
import com.niuxin.util.HttpPostUtil;
import com.niuxin.util.HttpPostUtilPic;
import com.niuxin.util.PostPicture;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/*
 * 填写报单内容activity
 */
public class DeclarationDetailActivity extends Activity {

	LinearLayout linearLayoutModelChoice, linearLayoutContactChoice, linearLayoutActiontype, detailsendchoice;
	private Button backButton, sendButton, saveButton;
	EditText editTextPrice, editTextShoushu, editTextCangwei, editTextArea1, editTextArea2, editTextBeizhu;
	TextView purposeChoiced, contractType, modelChioced, picchoice;
	ImageView imageView;
	Spinner spinnerOperateType;
	String[] operateTypeOrder = { "多开", "多平", "空开", "空平" };
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private SuoluetuActivity suolue;
	public Handler handler = new Handler();
	private SharePreferenceUtil util = null;
	String operateType = null;


	StringBuffer haoyouBuffer=new StringBuffer();//好友
	StringBuffer qunzuBuffer=new StringBuffer();//群组
	List<String> qunzuList=null;
	List<String> haoyouList=null;
	List<String> listAll=null;
	Integer Templateid =-1;
	String picturePath = "";
	MyApplication	constantStatic;
	String pictureurl = "";
	Integer selectContractId=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.declaration_launch_detail);
		suolue = new SuoluetuActivity(this, handler);
		util = new SharePreferenceUtil(this, Constants.SAVE_USER);
		// 数据初始化模板、合约类型、操作类型
		modelChioced = (TextView) findViewById(R.id.detail_model_choice);
		contractType = (TextView) findViewById(R.id.detail_contact_show);
		spinnerOperateType = (Spinner) findViewById(R.id.detail_control_show);
		picchoice = (TextView) findViewById(R.id.decla_picchoice);

		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.decla_spinner, operateTypeOrder); // 此处加上自己的样式
		spinnerOperateType.setAdapter(spinnerAdapter);

		spinnerOperateType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				operateType = operateTypeOrder[arg2];
				// 设置显示当前选择的项
				arg0.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}

		});

		linearLayoutModelChoice = (LinearLayout) findViewById(R.id.detail_model_control);
		linearLayoutContactChoice = (LinearLayout) findViewById(R.id.detail_contact_type);
		linearLayoutActiontype = (LinearLayout) findViewById(R.id.detail_action_type);
		detailsendchoice = (LinearLayout) findViewById(R.id.detail_send_desti);

		backButton = (Button) findViewById(R.id.detail_button_back);// 返回按钮
		sendButton = (Button) findViewById(R.id.detail_button_send);// 发送按钮
		saveButton = (Button) findViewById(R.id.detail_button_save);// 保存模板按钮

		// 价格手数仓位止盈止损范围备注
		editTextPrice = (EditText) findViewById(R.id.detail_edit_price);
		editTextShoushu = (EditText) findViewById(R.id.detail_edit_shoushu);
		editTextCangwei = (EditText) findViewById(R.id.detail_edit_cangwei);
		editTextArea1 = (EditText) findViewById(R.id.detail_edit_area1);
		editTextArea2 = (EditText) findViewById(R.id.detail_edit_area2);
		editTextBeizhu = (EditText) findViewById(R.id.decla_lanch_detail_edit_beizhu);
		// 备注图片
		imageView = (ImageView) findViewById(R.id.detail_image_beizhu);
		purposeChoiced = (TextView) findViewById(R.id.detail_purpose_choiced);
		//purposeChoiced.setText("未选");
		modelChioced.setText("未选");
		// 设置监听事件
		// 模板选择跳转
		String text = "  ";
		editTextPrice.setText(text);
		editTextPrice.setSelection(text.length());
		editTextShoushu.setText(text);
		editTextShoushu.setSelection(text.length());
		editTextCangwei.setText(text);
		editTextCangwei.setSelection(text.length());
		editTextBeizhu.setText(text);
		editTextBeizhu.setSelection(text.length());
		editTextArea1.setText(text);
		editTextArea1.setSelection(text.length());
		editTextArea2.setText(text);
		editTextArea2.setSelection(text.length());
		contractType.setText("未选");
		/*
		 * Map<String, Object> map=new HashMap<String, Object>();
		 * map.put("name", modelChioced);//模板名称 map.put("contract",
		 * contractType);//合约类型 map.put("operation", spinnerOperateType);//操作类型
		 * map.put("price", editTextPrice);//价格 map.put("handnum",
		 * editTextShoushu);//手数 map.put("position", editTextCangwei);//仓位
		 * map.put("minnum", editTextArea1);//范围小 map.put("maxnum",
		 * editTextArea2);//范围大 map.put("remark", editTextBeizhu);//备注
		 * list.add(map);
		 */
		// map.put("", imageView);//配图
		//\\
		// * 根据模板的名称查询数据库中的数据。11.28号改动

		 //if (null != constantStatic) {
		     constantStatic=(MyApplication)getApplication();
			 qunzuList=constantStatic.getQunzuList();//获取到选择的群组发送目标
			 haoyouList= constantStatic.getHaoyouList();//获取到选择的好友发送目标名称
			 System.out.println(haoyouList+"好友机会");
			 listAll=constantStatic.getSendList();//相加的名称
			//传过来的数据，转换成String，存入到数据库
			//循环获取好友ID
			if (haoyouList!=null) {
			    for (int i = 0; i < haoyouList.size(); i++) {
					if (i==0) {
						haoyouBuffer.append(haoyouList.get(i));
					} else {
						haoyouBuffer.append("," + haoyouList.get(i));
					}
				}
			}

			//循环获取群组id
			if (qunzuList!=null) {
			    for (int i = 0; i < qunzuList.size(); i++) {
					if (i==0) {
						qunzuBuffer.append(qunzuList.get(i));
					} else {
						qunzuBuffer.append("," + qunzuList.get(i));
					}
				}
			}

			System.out.println(listAll+"发送目标");
			
		
		 System.out.println(listAll+"12121212121212121212121");
			
		if (null==listAll) {
			purposeChoiced.setText("选择");
		}else {
			purposeChoiced.setText("已选");
		}
		
		linearLayoutModelChoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("modelText", modelChioced.getText());// 获取合约类型的名称，传递过去

				intent.setClass(DeclarationDetailActivity.this, DeclarationModelChoiceActivity.class);
				startActivityForResult(intent, 12);

				/*
				 * Intent intent=new Intent(DeclarationDetailActivity.this,
				 * DeclarationModelChoiceActivity.class); startActivity(intent);
				 */
			}
		});
		// 合约类型选择界面跳转
		linearLayoutContactChoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("contractText", contractType.getText());// 获取合约类型的名称，传递过去
				intent.setClass(DeclarationDetailActivity.this, DeclarationContactChoiceActivity.class);
				startActivityForResult(intent, 10);
				// startActivity(intent);
			}
		});
		// 发送目标选择
		detailsendchoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DeclarationDetailActivity.this, DeclarationSendpurposeChoiceActivity.class);
				startActivity(intent);
				//startActivityForResult(intent, 19);

			}
		});
		// 选择配图
		picchoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, 16);

			}
		});
		// 返回
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent();
				 * intent.setClass(DeclarationDetailActivity.this,
				 * DeclarationLaunchActivity.class); startActivity(intent);
				 */
				finish();
			}
		});
		// 发送
		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				// 获取数据，发送
				SaveThread saveThread = new SaveThread(1);
				saveThread.start();
				Toast toast = Toast.makeText(DeclarationDetailActivity.this, "发送成功", Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		// 保存模板
		saveButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(DeclarationDetailActivity.this, "模板已保存", Toast.LENGTH_SHORT);
				toast.show();
				SaveThread saveThread = new SaveThread(2);
				saveThread.start();
			}
		});

//		if (!contractType.getText().equals("未选")) {
//			SearchThread thread = new SearchThread();
//			thread.start();
//			GetPicThread t = new GetPicThread();
//			t.start();
//		}
		 
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 10 && resultCode == 11) {
			String result_value = data.getStringExtra("contractText");
			contractType.setText(result_value);
			selectContractId = Integer.valueOf(data.getStringExtra("selectContractId"));
		}
		if (requestCode == 12 && resultCode == 13) {
			String result_value = data.getStringExtra("modelText");
			Integer id = data.getIntExtra("selectedid", -2);//{modelText=报单223333, selectedid=1}
			if (null != id)
				Templateid = Integer.valueOf(id);
				modelChioced.setText(result_value);
				
				
				if (!result_value.equals("未选")) {
					SearchThread thread = new SearchThread();
					thread.start();
					GetPicThread t = new GetPicThread();
					t.start();
				}
		}
		if (requestCode == 16 && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			picturePath = cursor.getString(columnIndex);
			cursor.close();
			ImageView imageView = (ImageView) findViewById(R.id.detail_image_beizhu);
			// imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			imageView.setImageURI(selectedImage);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmmss");
			String date1 = format1.format(new Date(System.currentTimeMillis()));
			pictureurl = date1+util.getId();
			PostPicture.reg(this, lessenUriImage(selectedImage), "");

		}
	}

	public  Bitmap lessenUriImage(Uri path) {
		 Bitmap bitmap = null;
		 Bitmap resizeBmp = null;
		try {
			bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), path);
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
			float ws = (float)(320.0/w);
			float hs = (float)(320.0/h);
			 Matrix matrix = new Matrix(); 
			  matrix.postScale(ws,hs); //长和宽放大缩小的比例
			   resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		  return resizeBmp;
		
	/*
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回 bm 为空
		options.inJustDecodeBounds = false; // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = (int) (options.outHeight / (float) 320);
		if (be <= 0)
			be = 1;
		options.inSampleSize = be; // 重新读入图片，注意此时已经把 options.inJustDecodeBounds
									// 设回 false 了
		bitmap = BitmapFactory.decodeFile(path, options);
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		System.out.println(w + " " + h); // after zoom
		
		return bitmap;
		*/
		
	}

	class SaveThread extends Thread {
		private int type = 0;

		public SaveThread(int type) {
			this.type = type;
		}

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();

			JSONObject jsonObject = new JSONObject();
			try {

				jsonObject.put("name", "");// 模板名称
				jsonObject.put("contract", selectContractId);// 合约类型
				jsonObject.put("operation", operateType);// 操作类型
				jsonObject.put("price", editTextPrice.getText());// 价格
				jsonObject.put("handnum", editTextShoushu.getText());// 手数
				jsonObject.put("position", editTextCangwei.getText());// 仓位
				jsonObject.put("minnum", editTextArea1.getText());// 范围小
				jsonObject.put("maxnum", editTextArea2.getText());// 范围大
				jsonObject.put("remark",  editTextBeizhu.getText());// 备注
				jsonObject.put("sendfrom", util.getId());
				jsonObject.put("pictureurl", pictureurl);
				jsonObject.put("audiourl", "/pp/video.avi");
				jsonObject.put("type", type);
				jsonObject.put("sendtouser", "21,23");// 改成string把id存入到数据库中
				jsonObject.put("sendtogroup", "1,2");// 改成string存入到数据库中
				//jsonObject.put("sendtouser", haoyouBuffer);// 改成string把id存入到数据库中
				//jsonObject.put("sendtogroup", qunzuBuffer);// 改成string存入到数据库中
				jArray.put(jsonObject);			
			} catch (JSONException e) {
				e.printStackTrace();
			}
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/form/form_insert.do");
			// 不向服务器发送数据
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

	// 根据模板的名称获取模板的相关数据
	class SearchThread extends Thread {
		private Dialog mDialog = null;

		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();

			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("templateid", Templateid);
				jsonObject.put("userid", util.getId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/form/form_selectTemplateById.do");// 数据请求
			// 不向服务器发送数据
			// 向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);

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
			int j = 0;
			if (null != jsonArray)
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject myjObject = jsonArray.getJSONObject(i);// 获取每一个JsonObject对象
						// 获取每一个对象中的值
						// int id = myjObject.getInt("id");
						String contract = myjObject.getString("contract");// 合约类型
						String operation = myjObject.getString("operation");// 操作类型
						Integer price = myjObject.getInt("price");// 价格
						Integer handnum = myjObject.getInt("handnum");// 手数
						Integer position = myjObject.getInt("position");// 仓位
						Integer minnum = myjObject.getInt("minnum");// 最小范围
						Integer maxnum = myjObject.getInt("maxnum");// 最大范围
						String remark = myjObject.getString("remark");// 备注
						// String sendfrom = myjObject.getString("sendfrom");
						String sendtouser = myjObject.getString("sendtoUser");// 用户id
						String sendtogroup = myjObject.getString("sendtoGroup");// 群组id
						// String pictureurl =
						// myjObject.getString("pictureurl");
						// String audiourl = myjObject.getString("audiourl");
						contractType.setText(contract);
						operateType = operation;
						editTextPrice.setText(price);
						editTextShoushu.setText(handnum);
						editTextCangwei.setText(position);
						editTextArea1.setText(minnum);
						editTextArea2.setText(maxnum);
						editTextBeizhu.setText(remark);
						List<String> hlist = new ArrayList<String>();
						hlist = java.util.Arrays.asList(sendtouser);
						constantStatic.setHaoyouList(hlist);
						List<String> qList = java.util.Arrays.asList(sendtogroup);
						constantStatic.setQunzuList(qList);
						/*
						 * String[] haoText=sendtouser.split(","); for (String
						 * str:haoText) { hlist.add(str); }
						 */

					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			Runnable r = new Runnable() {
				@Override
				public void run() {
				}

			};
			handler.post(r);
		}
	}


	class GetPicThread extends Thread {
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtilPic postUtil = new HttpPostUtilPic();

			// 向服务器发送数据，如果没有，可以不发送
			JSONObject jsonObject = new JSONObject();		
			try {
				jsonObject.put("formid", Templateid);	
				jsonObject.put("type", 2);	
			} catch (JSONException e) {
				e.printStackTrace();
			}			
			//设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/upload/upload_download.do");
			//向服务器发送数据
			JSONArray js = new JSONArray();
			js.put(jsonObject);
			postUtil.setRequest(js);
			// 从服务器获取数据
			final byte[] pic = postUtil.run();	
			
			Runnable r = new Runnable() {
				@Override
				public void run() {										
				//	ivPictureUrl.setImageResource(getSource.getResourceByReflect(senduserimg));//头像
					Bitmap bp =BitmapFactory.decodeByteArray(pic, 0, pic.length);
					imageView.setImageBitmap(bp);
				}
			};
			handler.post(r);
		}
	}

}
