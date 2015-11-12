package com.niuxin.util;

public enum ContractType {
	ContractType1("多开", 1), ContractType2("多平", 2), ContractType3("空开", 3), ContractType4("空平", 4);
	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ContractType(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (ContractType c : ContractType.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
