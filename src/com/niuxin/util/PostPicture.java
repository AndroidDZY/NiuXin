package com.niuxin.util;

import java.io.ByteArrayOutputStream;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

public class PostPicture {

	
	public static void reg(final Context cont, Bitmap photodata, String regData) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			// 将bitmap一字节流输出 Bitmap.CompressFormat.PNG 压缩格式，100：压缩率，baos：字节流
			photodata.compress(Bitmap.CompressFormat.PNG, 100, baos);
			baos.close();
			byte[] buffer = baos.toByteArray();
			System.out.println("图片的大小：" + buffer.length);

			// 将图片的字节流数据加密成base64字符输出
			String photo = Base64.encodeToString(buffer, 0, buffer.length, Base64.DEFAULT);

			// photo=URLEncoder.encode(photo,"UTF-8");
			RequestParams params = new RequestParams();
			params.put("photo", photo);
			params.put("name", "dingliang");// 传输的字符数据
			String url = Constants.SERVER_IP+":"+"8080/NiuXinServer/upload/upload_upload.do";

			AsyncHttpClient client = new AsyncHttpClient();
			client.post(url, params, new AsyncHttpResponseHandler() {				
				@Override
				public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				//	Toast.makeText(cont, "头像上传成功!" + content, 0).show();
				}

				@Override
				public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
