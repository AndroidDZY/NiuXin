package com.example.niuxin;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.niuxin.client.Client;
import com.niuxin.util.Constants;
import com.niuxin.util.SharePreferenceUtil;

import android.app.Application;
import android.app.NotificationManager;

public class MyApplication extends Application {
	private Client client;// 客户端
	public void setClient(Client client) {
		this.client = client;
	}

	private boolean isClientStart;// 客户端连接是否启动
	private NotificationManager mNotificationManager;
	private int newMsgNum = 0;// 后台运行的消息
	private LinkedList<RecentChatEntity> mRecentList;
	private RecentChatAdapter mRecentAdapter;
	private int recentNum = 0;
    private  Set<Integer> haoyouList = new HashSet<Integer>();	
    private  Set<Integer> qunzuList = new HashSet<Integer>();
    private Boolean isSave = false;
	@Override
	public void onCreate() {
		super.onCreate();
		SharePreferenceUtil util = new SharePreferenceUtil(this,
				Constants.SAVE_USER);
		client = new Client(util.getIp(), util.getPort());// 从配置文件中读ip和地址
		mRecentList = new LinkedList<RecentChatEntity>();
		mRecentAdapter = new RecentChatAdapter(getApplicationContext(),
				mRecentList);
	}
   
	
	public Set<Integer> getHaoyouList() {
		return haoyouList;
	}


	public void setHaoyouList(Set<Integer> haoyouList) {
		this.haoyouList = haoyouList;
	}


	public Set<Integer> getQunzuList() {
		return qunzuList;
	}


	public void setQunzuList(Set<Integer> qunzuList) {
		this.qunzuList = qunzuList;
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


	public Boolean getIsSave() {
		return isSave;
	}


	public void setIsSave(Boolean isSave) {
		this.isSave = isSave;
	}
}
