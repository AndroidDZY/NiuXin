package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class lvButtonAdapter extends BaseAdapter {
    private class buttonViewHolder {
    	ImageButton appIcon;
        TextView appName;
        ImageButton buttonClose;
    }

    
    private ArrayList<HashMap<String, Object>> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] keyString;
    private int[] valueViewID;
    private buttonViewHolder holder;
    
    public lvButtonAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
            String[] from, int[] to) {
        mAppList = appList;
        mContext = c;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        keyString = new String[from.length];
        valueViewID = new int[to.length];
        System.arraycopy(from, 0, keyString, 0, from.length);
        System.arraycopy(to, 0, valueViewID, 0, to.length);
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

        lvButtonListener(int pos) {
            position = pos;
        }
        
        @Override
        public void onClick(View v) {
            int vid=v.getId();
            System.out.println(">>>>>>>>>position>>>>>>>>>>>>>"+position);//位置id
            System.out.println(">>>>>>>>>>vid>>>>>>>>>>>>"+vid);//不同按钮的id
           // if (vid == holder.buttonClose.getId())
           //     removeItem(position);
            if(R.id.img_tag_flag == vid){
            	
            }
            else if(R.id.img_tag_edit == vid){
            	edit();
            }
        }

		private void edit() {
			// TODO Auto-generated method stub
			View myView=LayoutInflater.from(mContext).inflate(R.layout.edittextview, null);//将layout对象转换为VIew对象
			AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				 builder.setTitle("标签重命名");
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
			        	   dialog.dismiss();//对话框消失
			           }  
			       });  
			       builder.create().show();  	
		}
		
		
    }

}