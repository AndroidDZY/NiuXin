package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;

public class HttpPostUtil implements Runnable{
    
    private static final int NO_SERVER_ERROR=1000;
    //服务器地址
    public String URL = "";
    //一些参数
    private static int connectionTimeout = 60000;
    private static int socketTimeout = 60000;
    //类静态变量
    private static HttpClient httpClient=new DefaultHttpClient();
    private static ExecutorService executorService=Executors.newCachedThreadPool();
    private static Handler handler = new Handler();
    //变量
    private String strResult;
    private HttpPost httpPost;
    private HttpResponse httpResponse;
    private OnReceiveDataListener onReceiveDataListener;
    public OnReceiveDataListener getOnReceiveDataListener() {
		return onReceiveDataListener;
	}

	private int statusCode;

    /**
     * 构造函数，初始化一些可以重复使用的变量
     */
    public HttpPostUtil() {
        strResult = null;
        httpResponse = null;
        httpPost = new HttpPost();
    }
    
    /**
     * 注册接收数据监听器
     * @param listener
     */
    public void setOnReceiveDataListener(OnReceiveDataListener listener) {
        onReceiveDataListener = listener;
    }

    /**
     * @param nameValuePairs
     *            需要传递的参数
     */
    public void setUrl(String str){
    	this.URL = str;
    }
    public void setRequest(JSONObject jsonObject) {
        httpPost.addHeader("Content-Type", "text/json");
        httpPost.addHeader("charset", "UTF-8");

        httpPost.addHeader("Cache-Control", "no-cache");
        HttpParams httpParameters = httpPost.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters,connectionTimeout);
        HttpConnectionParams.setSoTimeout(httpParameters, socketTimeout);
        httpPost.setParams(httpParameters);
        try {
          //  httpPost.setURI(new URI(URL + requestType));
            httpPost.setURI(new URI(URL));
            httpPost.setEntity(new StringEntity(jsonObject.toString(),HTTP.UTF_8));
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新开一个线程发送http请求
     */
    public void execute() {
        executorService.execute(this);
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

    /**
     * 发送http请求的具体执行代码
     */
    @Override
    public void run() {
        httpResponse = null;
        StringBuffer sb = new StringBuffer();
        try {      	
            httpResponse = httpClient.execute(httpPost);
            strResult = EntityUtils.toString(httpResponse.getEntity());
        } catch (ClientProtocolException e1) {
            strResult = null;
            e1.printStackTrace();
        } catch (IOException e1) {
            strResult = null;
            e1.printStackTrace();
        }catch (Exception e1) {
            strResult = null;
            e1.printStackTrace();
        }  finally {
            if (httpResponse != null) {
                statusCode = httpResponse.getStatusLine().getStatusCode();
            }
            else
            {
                statusCode=NO_SERVER_ERROR;
            }
            if(onReceiveDataListener!=null)
            {
            	//将注册的监听器的onReceiveData方法加入到消息队列中去执行
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    		onReceiveDataListener.onReceiveData(strResult);                    	
                    }
                });
                
            }
        }
    }
    
    private static ArrayList<HashMap<String, Object>> Analysis(String jsonStr) throws JSONException {
        /******************* 解析 ***********************/
        JSONArray jsonArray = null;
        // 初始化list数组对象
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        jsonArray = new JSONArray(jsonStr);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 初始化map数组对象
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("username", jsonObject.getString("username"));          
            list.add(map);
        }
        return list;
    }

    /**
     * 用于接收并处理http请求结果的监听器
     *
     */
    public interface OnReceiveDataListener {       
        public abstract void onReceiveData(String strResult);
    }

}



/*
http://www.cnblogs.com/hrlnw/p/4118480.html
*/