
package com.niuxin.util;


import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

public class HttpPostUtilPic {

	// 服务器地址
	public String URL = "";
	// 一些参数
	private static int connectionTimeout = 60000;
	private static int socketTimeout = 60000;
	// 类静态变量
	private   HttpClient httpClient = new DefaultHttpClient();
	private static ConfigProperties con = new ConfigProperties();

	// 变量
	private String strResult;
	private byte[] strResultByte;
	private HttpPost httpPost;
	public HttpResponse httpResponse;


	public HttpPostUtilPic() {

		strResult = null;
		httpResponse = null;
		httpPost = new HttpPost();
	}

	
	public void setUrl(String str) {		
		this.URL = ConfigProperties.IP+str;
	}

	public void setRequest(JSONArray jsonObject) { //这边
		httpPost.addHeader("Content-Type", "text/json");
		httpPost.addHeader("charset", "UTF-8");

		httpPost.addHeader("Cache-Control", "no-cache");
		HttpParams httpParameters = httpPost.getParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters,
				connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParameters, socketTimeout);
		httpPost.setParams(httpParameters);
		try {
			// httpPost.setURI(new URI(URL + requestType));
			httpPost.setURI(new URI(URL));
			if(jsonObject!=null)
			httpPost.setEntity(new StringEntity(new String(jsonObject.toString().getBytes(), "UTF-8"),
					HTTP.UTF_8));
			else
				httpPost.setEntity(new StringEntity("".toString(),
						HTTP.UTF_8));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检测网络状况
	 * 
	 * @return true is available else false
	 */
	public static boolean checkNetState(Activity activity) {
		ConnectivityManager connManager = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connManager.getActiveNetworkInfo() != null) {
			return connManager.getActiveNetworkInfo().isAvailable();
		}
		return false;
	}

	public byte[] run() {
		httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpPost);
		//	strResult = EntityUtils.toString(httpResponse.getEntity());
			strResultByte =  EntityUtils.toByteArray(httpResponse.getEntity());
		}
		catch (Exception e1) {
			strResult = null;
			e1.printStackTrace();
		}		
		return strResultByte;
	}


	public byte[] getStrResultByte() {
		return strResultByte;
	}


	public void setStrResultByte(byte[] strResultByte) {
		this.strResultByte = strResultByte;
	}

}

/*
 * http://www.cnblogs.com/hrlnw/p/4118480.html
 */
