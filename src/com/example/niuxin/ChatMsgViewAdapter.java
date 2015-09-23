package com.example.niuxin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.bean.ChatMsgEntity;
import com.niuxin.util.GetSource;
import com.niuxin.util.HttpPostUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatMsgViewAdapter extends BaseAdapter {
	public  List<String> shareNameList = new LinkedList<String>();
	private Handler handler = null;

	// ListView视图的内容由IMsgViewType决定
	public static interface IMsgViewType {
		// 对方发来的信息
		int IMVT_COM_MSG = 0;
		// 自己发出的信息
		int IMVT_TO_MSG = 1;
	}

	private static final String TAG = ChatMsgViewAdapter.class.getSimpleName();
	private List<ChatMsgEntity> data;
	private Context context;
	private LayoutInflater mInflater;
	private Activity act;
	GetSource getSource = new GetSource();
	public ChatMsgViewAdapter(Context context, List<ChatMsgEntity> data, Handler handler, Activity act) {
		this.context = context;
		this.data = data;
		// LayoutInflater绫讳技findViewById锛屽彧鏄疞ayoutInflater鏄壘res/layout/涓嬬殑.xml鏂囦欢
		mInflater = LayoutInflater.from(context);
		this.handler = handler;
		ShareThread t = new ShareThread();
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.act = act;
	}

	// 获取ListView的项个数
	public int getCount() {
		return data.size();
	}

	// 获取项
	public Object getItem(int position) {
		return data.get(position);
	}

	// 获取项的ID
	public long getItemId(int position) {
		return position;
	}

	// 获取项的类型
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		ChatMsgEntity entity = data.get(position);

		if (entity.getMsgType()) {
			return IMsgViewType.IMVT_COM_MSG;
		} else {
			return IMsgViewType.IMVT_TO_MSG;
		}

	}

	// 获取项的类型数
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	// 获取View
	public View getView(int position, View convertView, ViewGroup parent) {

		ChatMsgEntity entity = data.get(position);
		boolean isComMsg = entity.getMsgType();

		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isComMsg) {
				// 如果是对方发来的消息，则显示的是左气泡
				convertView = mInflater.inflate(R.layout.chatting_item_msg_text_left, null);
			} else {
				// 如果是自己发出的消息，则显示的是右气泡
				convertView = mInflater.inflate(R.layout.chatting_item_msg_text_right, null);
			}

			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView.findViewById(R.id.tv_sendtime);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_chatcontent);
			viewHolder.isComMsg = isComMsg;
			viewHolder.img = (ImageView) convertView.findViewById(R.id.iv_userhead);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvSendTime.setText(entity.getDate());
		viewHolder.img.setBackgroundResource(getSource.getResourceByReflect(entity.getImg()));
		
		
		// 设置具体的聊天内容
		int mark = 0;
		String str = null;
		for (int i = 0; i < shareNameList.size(); i++) {
			if (entity.getText().contains(shareNameList.get(i))) {
				str = shareNameList.get(i);
				mark = 1;
				break;
			}
		}
		if (mark == 0) {
			viewHolder.tvContent.setText(entity.getText());
		} else {
			String []strs =  entity.getText().split(str);
			
			String stock = str;// 可点击，可变蓝的部分
			SpannableString spanstock = new SpannableString(stock);
			ClickableSpan clickstock = new ShuoMClickableSpan(stock, act);
			spanstock.setSpan(clickstock, 0, stock.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
			
			if(strs.length==0){
				viewHolder.tvContent.setText("");
				viewHolder.tvContent.append(spanstock);	
			}
			if(strs.length==1){
				viewHolder.tvContent.setText(strs[0]);
				viewHolder.tvContent.append(spanstock);	
			}
			if(strs.length==2){
				viewHolder.tvContent.setText(strs[0]);
				viewHolder.tvContent.append(spanstock);	
				viewHolder.tvContent.append(strs[1]);		
			}
			
			viewHolder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());// setMovementMethod方法附加点击响应
		
			/*
			String stock = "海螺水泥";// 可点击，可变蓝的部分
			SpannableString spanstock = new SpannableString(stock);
			ClickableSpan clickstock = new ShuoMClickableSpan(stock, act);
			spanstock.setSpan(clickstock, 0, stock.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
			viewHolder.tvContent.setText("听说今天");
			viewHolder.tvContent.append(spanstock);
			viewHolder.tvContent.append("会涨停哟!");
			viewHolder.tvContent.setMovementMethod(LinkMovementMethod.getInstance());// setMovementMethod方法附加点击响应
		*/
		}

		return convertView;
	}

	// 通过ViewHolder显示项的内容
	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvContent;
		public boolean isComMsg = true;
		public ImageView img = null;
	}

	
	class ShuoMClickableSpan extends ClickableSpan {
		String string;
		Context context;

		public ShuoMClickableSpan(String str, Context context) {
			super();
			this.string = str;
			this.context = context;
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setColor(Color.BLUE);// 设置变蓝
		}

		@Override
		public void onClick(View widget) {
			Intent intent = new Intent();
			intent.setClass(context, kanpan_wudangActivity.class);// 设置跳转，OtherActivity可替换，这边应该是跳转到kanpan_wudangActivity(没记错的话)
			context.startActivity(intent);

		}

	}
	
	

	class ShareThread extends Thread {// 获取所有股票的名字
		@Override
		public void run() {
			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			/*
			 * // 向服务器发送数据，如果没有，可以不发送 JSONObject jsonObject = new JSONObject();
			 * try { jsonObject.put("username", "huangwuyi");
			 * jsonObject.put("sex", "男"); jsonObject.put("QQ", "413425430");
			 * jsonObject.put("Min.score", new Integer(99));
			 * jsonObject.put("nickname", "梦中心境"); } catch (JSONException e) {
			 * e.printStackTrace(); } postUtil.setRequest(jsonObject);
			 */
			/*
			 * boolean isNetwork= postUtil.checkNetState(act); if(!isNetwork){
			 * mDialog = DialogFactory.creatRequestDialog(act, "请检查网络连接");
			 * mDialog.show(); return; }
			 */

			// 设置发送的url 和服务器端的struts.xml文件对应
			postUtil.setUrl("/share/share_selectAll.do");
			// 不向服务器发送数据
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
					String name = myjObject.getString("name");
					shareNameList.add(name);
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

}
