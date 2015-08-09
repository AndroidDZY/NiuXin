package com.example.niuxin.test;

import org.json.JSONException;
import org.json.JSONObject;
import android.test.AndroidTestCase;
import com.util.HttpPostUtil;
import com.util.HttpPostUtil.OnReceiveDataListener;

class TestResult implements OnReceiveDataListener{
	public void onReceiveData(String strResult) {		
		System.out.println("调用回调函数》》》》》》》》》》》》》》》》》》");		
		System.out.println("调用回调函数》》》》》》》》》》》》》》》》》》");	
		System.out.println("调用回调函数》》》》》》》》》》》》》》》》》》");	
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
		postUtil.setUrl("http://192.168.1.112:8080/NiuXinServer/user/action_countAll.do");
		postUtil.setOnReceiveDataListener (new TestResult());
		//4.
		postUtil.setRequest(jsonObject);
		//5.
		postUtil.execute();	
	//	postUtil.run();
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
