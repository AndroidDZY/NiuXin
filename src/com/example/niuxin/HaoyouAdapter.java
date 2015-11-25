package com.example.niuxin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class HaoyouAdapter extends BaseAdapter {  
    // 填充数据的list  
    private List<HashMap<String, Object>> list;  
   // 上下文  
    private Context context;  
    // 用来导入布局  
    private LayoutInflater inflater = null;  
  
    // 构造器  
    public HaoyouAdapter(List<HashMap<String, Object>> list2, Context context) {  
        this.context = context;  
        this.list = list2;  
        inflater = LayoutInflater.from(context);  
    }  
  
      
  
    @Override  
    public int getCount() {  
        return list.size();  
    }  
  
    @Override  
    public Object getItem(int position) {  
        return list.get(position);  
    }  
  
    @Override  
    public long getItemId(int position) {  
        return position;  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        ViewHolder holder = null;  
        if (convertView == null) {  
            // 获得ViewHolder对象  
            holder = new ViewHolder();  
            // 导入布局并赋值给convertview  
            convertView = inflater.inflate(R.layout.listview_declaration_sendpurpose_haoyou, null);
            holder.im = (ImageView)convertView.findViewById(R.id.decla_haoyouitem_touxiang); 
            holder.tv = (TextView) convertView.findViewById(R.id.decla_haoyouitem_name);  
            holder.cb = (CheckBox) convertView.findViewById(R.id.haoyou_item_cb);  
            // 为view设置标签  
            convertView.setTag(holder);  
        } else {  
            // 取出holder  
            holder = (ViewHolder) convertView.getTag();  
        }  
        // 设置list中TextView的显示  
        holder.tv.setText(list.get(position).get("username").toString());
        holder.im.setBackgroundResource(Integer.valueOf(list.get(position).get("touxiang").toString()));
        // 根据isSelected来设置checkbox的选中状况  
        holder.cb.setChecked(DeclarationUserselectActivity.isSelected .get(position));  
        return convertView;  
    }  
  
   
    public static class ViewHolder {  
        TextView tv;
        ImageView im;
        CheckBox cb;  
    }

	 
}  