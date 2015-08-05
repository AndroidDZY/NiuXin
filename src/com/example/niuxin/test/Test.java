package com.example.niuxin.test;

import org.json.JSONException;
import org.json.JSONObject;
import android.test.AndroidTestCase;
import com.util.HttpPostUtil;
import com.util.HttpPostUtil.OnReceiveDataListener;

class TestResult implements OnReceiveDataListener{
	//6.
	@Override
	public void onReceiveData(String strResult) {		
		System.out.println("调用回调函数》》》》》》》》》》》》》》》》》》");			
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
		postUtil.setRequest(jsonObject);
		//5.
		postUtil.execute();	
		
	}
}
