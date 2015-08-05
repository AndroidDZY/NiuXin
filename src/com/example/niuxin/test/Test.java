package com.example.niuxin.test;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;

import com.util.HttpPostUtil;
import com.util.HttpPostUtil.OnReceiveDataListener;

class TestResult implements OnReceiveDataListener{
	//6.
	@Override
	public void onReceiveData(String strResult, int StatusCode) {
		JSONArray jsonArray = null;
        // 初始化list数组对象
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
			jsonArray = new JSONArray(strResult);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject;
			try {
				jsonObject = jsonArray.getJSONObject(i);			
            // 初始化map数组对象
            HashMap<String, Object> map = new HashMap<String, Object>();
            //获取具体的数据          
				map.put("logo", jsonObject.getString("logo"));
				list.add(map);
			} catch (JSONException e) {			
				e.printStackTrace();
			}           
            
	}
	}
}

public class Test extends AndroidTestCase  {

	
	public static void test(){	
		//1.
		HttpPostUtil postUtil = new HttpPostUtil();
		//2.
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
		//3.
		postUtil.setUrl("http://172.20.1.120:8080/NiuXinServer/user/action_countAll.do");
		postUtil.setOnReceiveDataListener (new TestResult());
		//4.
		postUtil.setRequest(postUtil.ADD, jsonObject);
		//5.
		postUtil.execute();		
	}
	
	
	
	public static void main(String[] args) {
	}

}
