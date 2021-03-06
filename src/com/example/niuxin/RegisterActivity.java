package com.example.niuxin;



import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.niuxin.bean.User;
import com.niuxin.client.Client;
import com.niuxin.client.ClientOutputThread;
import com.niuxin.util.DialogFactory;
import com.niuxin.util.Encode;
import com.niuxin.util.TranObject;
import com.niuxin.util.TranObjectType;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends MyActivity implements OnClickListener {
	private boolean registersuccessflag;//注册成功标记
	private Button mBtnRegister;
	private Button mRegBack;
	private EditText mEmailEt, mNameEt, mPasswdEt, mPasswdEt2;
	private List<String> imglist = new LinkedList<String>();
	private MyApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.register);
		application = (MyApplication) this.getApplicationContext();
		initView();

	}

	public void initView() {
		mBtnRegister = (Button) findViewById(R.id.register_btn);
		mRegBack = (Button) findViewById(R.id.reg_back_btn);
		mBtnRegister.setOnClickListener(this);
		mRegBack.setOnClickListener(this);

		mEmailEt = (EditText) findViewById(R.id.reg_email);
		mNameEt = (EditText) findViewById(R.id.reg_name);
		mPasswdEt = (EditText) findViewById(R.id.reg_password);
		mPasswdEt2 = (EditText) findViewById(R.id.reg_password2);

		imglist.add("head001.png");
		imglist.add("head002.png");
		imglist.add("head003.png");
		imglist.add("head004.png");
		imglist.add("head005.png");
		imglist.add("head006.png");
		imglist.add("head007.png");
		imglist.add("head008.png");
		imglist.add("head009.png");
		imglist.add("head010.png");
	}

	private Dialog mDialog = null;

	private void showRequestDialog() {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = DialogFactory.creatRequestDialog(this, "正在注册中...");
		mDialog.show();
	}

	@Override
	public void onBackPressed() {// 捕获返回键
		toast(RegisterActivity.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_btn:
			registersuccessflag = false;
			// showRequestDialog();
			estimate();
			break;
		case R.id.reg_back_btn:
			toast(RegisterActivity.this);
			break;
		default:
			break;
		}
	}

	private void toast(Context context) {
		new AlertDialog.Builder(context).setTitle("牛信注册")
				.setMessage("亲！您真的不注册了吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setNegativeButton("取消", null).create().show();
	}
	
	private void toastsuccess(Context context) {
		new AlertDialog.Builder(context).setTitle("牛信注册")
				.setMessage("亲！请牢记您的登录牛信号哦！")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				}).setNegativeButton("", null).create().show();
	}	

	


	private void estimate() {
		String email = mEmailEt.getText().toString();
		String name = mNameEt.getText().toString();
		String passwd = mPasswdEt.getText().toString();
		String passwd2 = mPasswdEt2.getText().toString();
		if (email.equals("") || name.equals("") || passwd.equals("")
				|| passwd2.equals("")) {
			DialogFactory.ToastDialog(RegisterActivity.this, "牛信注册",
					"亲！带*项是不能为空的哦");
		} else {
			if (passwd.equals(passwd2)) {
				showRequestDialog();//显示等待注册完成对话框
				// 提交注册信息
				if (application.isClientStart()) {// 如果已连接上服务器
					Client client = application.getClient();
//					Client client = GetMsgService.client;
					ClientOutputThread out = client.getClientOutputThread();
					TranObject<User> o = new TranObject<User>(
							TranObjectType.REGISTER);
					User u = new User();
					u.setEmail(email);
					u.setUserName(name);
					u.setPassWord(Encode.getEncode("MD5", passwd));
					
					Random rand = new Random();
					int i = rand.nextInt(10) + 1;

					u.setImg(imglist.get(i));
					
					
					o.setObject(u);
					out.setMsg(o);
				} else {
					if (mDialog.isShowing())
						mDialog.dismiss();
					DialogFactory.ToastDialog(this, "牛信注册", "亲！服务器暂未开放哦");
				}

			} else {
				DialogFactory.ToastDialog(RegisterActivity.this, "牛信注册",
						"亲！您两次输入的密码不同哦");
			}
		}
	}

	@Override
	//服务器返回信息后处理
	public void getMessage(TranObject msg) {
		// TODO Auto-generated method stub
		switch (msg.getType()) {
		case REGISTER:
			User u = (User) msg.getObject();
			int id = u.getId();
			if (id > 0) {
				if (mDialog != null) {
					mDialog.dismiss();
					mDialog = null;
				}

				//DialogFactory.ToastDialog(RegisterActivity.this, "牛信注册",
						//"亲！请牢记您的登录牛信号哦：" + id);
				toastsuccess(RegisterActivity.this);

				DialogFactory.ToastDialog(RegisterActivity.this, "牛信注册",
						"亲！请牢记您的登录牛信哦!");
			} else {
				if (mDialog != null) {
					mDialog.dismiss();
					mDialog = null;
				}
				DialogFactory.ToastDialog(RegisterActivity.this, "牛信注册",
						"亲！很抱歉！牛信号暂时缺货哦");
			}
			break;

		default:
			break;
		}
	}
}
