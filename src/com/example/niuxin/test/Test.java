package com.example.niuxin.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;

import com.util.HttpPostUtil;
import com.util.HttpPostUtil.OnReceiveDataListener;

class Result implements OnReceiveDataListener{

	@Override
	public void onReceiveData(String strResult, int StatusCode) {
		System.out.println(strResult);
	}
	
}

public class Test extends AndroidTestCase  {

	
	public static void test(){
		System.out.println("test>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		HttpPostUtil postUtil = new HttpPostUtil();
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
		postUtil.iniRequest(postUtil.ADD, jsonObject);
		postUtil.execute();
	}
	
	
	
	public static void main(String[] args) {
	}

}
