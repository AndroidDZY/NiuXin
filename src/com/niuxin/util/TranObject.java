package com.niuxin.util;



import java.io.Serializable;

/**
 * 传输的对象,直接通过Socket传输的最大对象
 * 
 * @author way
 */
public class TranObject<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TranObjectType type;// 发送的消息类型
	private int istoGroup;//1代表发给群组 0
	private int fromUser;// 来自哪个用户
	private int toUser;// 发往哪个用户
	private String img;// 这个字段传送群组或者 个人用户的img

	private T object;// 传输的对象，这个对象我们可以自定义任何
	
	public TranObject(TranObjectType type) {
		this.type = type;
	}

	public int getFromUser() {
		return fromUser;
	}

	public void setFromUser(int fromUser) {
		this.fromUser = fromUser;
	}

	public int getToUser() {
		return toUser;
	}

	public void setToUser(int toUser) {
		this.toUser = toUser;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public TranObjectType getType() {
		return type;
	}

	public int getIstoGroup() {
		return istoGroup;
	}

	public void setIstoGroup(int istoGroup) {
		this.istoGroup = istoGroup;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}
