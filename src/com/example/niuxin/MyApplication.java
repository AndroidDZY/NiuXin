package com.example.niuxin;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.niuxin.client.Client;
import com.niuxin.util.Constants;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Application;
import android.app.NotificationManager;

public class MyApplication extends Application {
	private Client client;// 客户端
	private boolean isClientStart;// 客户端连接是否启动
	private NotificationManager mNotificationManager;
	private int newMsgNum = 0;// 后台运行的消息
	private LinkedList<RecentChatEntity> mRecentList;
	private RecentChatAdapter mRecentAdapter;
	private int recentNum = 0;
    private  List<String> haoyouList;
	
    private  List<String> qunzuList;
	
	private List<String> sendList;
	
	
	
	@Override
	public void onCreate() {
		SharePreferenceUtil util = new SharePreferenceUtil(this,
				Constants.SAVE_USER);
		System.out.println(util.getIp() + " " + util.getPort());
		client = new Client(util.getIp(), util.getPort());// 从配置文件中读ip和地址
		mRecentList = new LinkedList<RecentChatEntity>();
		mRecentAdapter = new RecentChatAdapter(getApplicationContext(),
				mRecentList);
		haoyouList=new ArrayList<String>();
		qunzuList=new ArrayList<String>();
		sendList=new ArrayList<String>();
		sendList.addAll(haoyouList);
		sendList.addAll(qunzuList);
		super.onCreate();
	}
    public List<String> getHaoyouList() {
		return haoyouList;
	}

	public void setHaoyouList(List<String> haoyouList) {
		this.haoyouList = haoyouList;
	}

	public List<String> getQunzuList() {
		return qunzuList;
	}

	public void setQunzuList(List<String> qunzuList) {
		this.qunzuList = qunzuList;
	}

	public List<String> getSendList() {
		return sendList;
	}

	public void setSendList(List<String> sendList) {
		this.sendList = sendList;
	}

	public Client getClient() {
		return client;
	}

	public boolean isClientStart() {
		return isClientStart;
	}

	public void setClientStart(boolean isClientStart) {
		this.isClientStart = isClientStart;
	}

	public NotificationManager getmNotificationManager() {
		return mNotificationManager;
	}

	public void setmNotificationManager(NotificationManager mNotificationManager) {
		this.mNotificationManager = mNotificationManager;
	}

	public int getNewMsgNum() {
		return newMsgNum;
	}

	public void setNewMsgNum(int newMsgNum) {
		this.newMsgNum = newMsgNum;
	}

	public LinkedList<RecentChatEntity> getmRecentList() {
		return mRecentList;
	}

	public void setmRecentList(LinkedList<RecentChatEntity> mRecentList) {
		this.mRecentList = mRecentList;
	}

	public RecentChatAdapter getmRecentAdapter() {
		return mRecentAdapter;
	}

	public void setmRecentAdapter(RecentChatAdapter mRecentAdapter) {
		this.mRecentAdapter = mRecentAdapter;
	}

	public int getRecentNum() {
		return recentNum;
	}

	public void setRecentNum(int recentNum) {
		this.recentNum = recentNum;
	}
}
