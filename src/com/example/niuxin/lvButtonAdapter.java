package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.niuxin.util.HttpPostUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObservable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class lvButtonAdapter extends BaseAdapter {
    private class buttonViewHolder {
    	ImageButton appIcon;
        TextView appName;
        ImageButton buttonClose;
    }

    
    private ArrayList<HashMap<String, Object>> mAppList;
    public ArrayList<HashMap<String, Object>> getmAppList() {
		return mAppList;
	}

	public void setmAppList(ArrayList<HashMap<String, Object>> mAppList) {
		this.mAppList = mAppList;
	}

	private LayoutInflater mInflater;
    private Context mContext;
    private String[] keyString;
    private int[] valueViewID;
    private buttonViewHolder holder;
    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    private Activity act;
    private Integer itemid = null;
    private String labname = "";
    
    public lvButtonAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
            String[] from, int[] to,final Activity act) {
        mAppList = appList;
        mContext = c;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keyString = new String[from.length];
        valueViewID = new int[to.length];
        System.arraycopy(from, 0, keyString, 0, from.length);
        System.arraycopy(to, 0, valueViewID, 0, to.length);
        this.act = act;
    }
    
    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int position){
        mAppList.remove(position);
        this.notifyDataSetChanged();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (buttonViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.taglistview, null);
            holder = new buttonViewHolder();
            holder.appIcon = (ImageButton)convertView.findViewById(valueViewID[0]);
            holder.appName = (TextView)convertView.findViewById(valueViewID[1]);
            holder.buttonClose = (ImageButton)convertView.findViewById(valueViewID[2]);
            convertView.setTag(holder);
        }
        
        HashMap<String, Object> appInfo = mAppList.get(position);
        if (appInfo != null) {
            String aname = (String) appInfo.get(keyString[1]);
            int mid = (Integer)appInfo.get(keyString[0]);
            int bid = (Integer)appInfo.get(keyString[2]);
            holder.appName.setText(aname);
            holder.appIcon.setImageDrawable(holder.appIcon.getResources().getDrawable(mid));
            holder.buttonClose.setImageDrawable(holder.buttonClose.getResources().getDrawable(bid));
            holder.buttonClose.setOnClickListener(new lvButtonListener(position));
            
            holder.appIcon.setOnClickListener(new lvButtonListener(position));
        }        
        return convertView;
    }

    class lvButtonListener implements OnClickListener {
        private int position;
        EditText text = null;
        View myView = null;
        lvButtonListener(int pos) {
            position = pos;
        }
        
        @Override
        public void onClick(View v) {
            int vid=v.getId();         
            if(R.id.img_tag_flag == vid){
            	int tag_flag;
   
	            if(Integer.valueOf(mAppList.get(position).get("img_tag_flag").toString())==R.drawable.edit_flag01){
	            	tag_flag = R.drawable.edit_flag02;
	            }else
	            	tag_flag = R.drawable.edit_flag01;
	         
	            mAppList.get(position).put("img_tag_flag", tag_flag);//将更新过的add_flag值放入list中        
	            notifyDataSetChanged();//使更新过的list数据生效
            }
            else if(R.id.img_tag_edit == vid){
            	edit();
            }
        }

		

		private void edit() {
			// TODO Auto-generated method stub
			final View myView=LayoutInflater.from(mContext).inflate(R.layout.edittextview, null);//将layout对象转换为VIew对象
			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				 builder.setTitle("标签重命名");
				 //builder.setMessage("确定要删除这些标签吗？");
				 builder.setView(myView);
			       builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  
			           public void onClick(DialogInterface dialog, int whichButton) {  
			              
			        	   text =  (EditText)myView.findViewById(R.id.et_tag_name);			        	   
			        	   String str = text.getText().toString();
			        	   if(str==null||"".equals(str.trim())){
			        		   Toast.makeText(act.getApplicationContext(), "标签名称不能为空!!!", 0).show();
			        		  
								return;
			        	   }
			        	   labname = str;
			        	   itemid = position;
			        	   mAppList.get(position).put("tag_name",str);
			        	   UpdateThread thread = new UpdateThread();
			        	   thread.start();
			        	   dialog.dismiss();//对话框消失
			        	   notifyDataSetChanged();
			           }  
			       });  
			       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  
			           public void onClick(DialogInterface dialog, int whichButton) {  
			               
			        	   dialog.dismiss();//对话框消失
			           }  
			       });  
			       builder.create().show();  	
		}
		
		
    }

	
	
	class UpdateThread extends Thread {
		@Override
		public void run() {

			// 新建工具类，向服务器发送Http请求
			HttpPostUtil postUtil = new HttpPostUtil();
			JSONArray jArray = new JSONArray();

			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("id",  Integer.valueOf(mAppList.get(itemid).get("id").toString()));
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
			postUtil.setUrl("/lab/lab_update.do");
			// 向服务器发送数据
			postUtil.setRequest(jArray);
			postUtil.run();
			
	}
}
	

}