package com.niuxin.bean;

public class ChatMsgEntity {
	private static final String TAG = ChatMsgEntity.class.getSimpleName();
    //名字
    private String name;
    //日期
    private String date;
    //聊天内容
    private String text;
    //是否为对方发来的信息
    private boolean isComMeg = true;
	private int img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getMsgType() {
        return isComMeg;
    }

    public void setMsgType(boolean isComMsg) {
    	isComMeg = isComMsg;
    }

    public ChatMsgEntity() {
    }
    
	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
	}

	public ChatMsgEntity(String name, String date, String text, int img,
			boolean isComMsg) {
		super();
		this.name = name;
		this.date = date;
		this.text = text;
		this.img = img;
		this.isComMeg = isComMsg;
	}

}
