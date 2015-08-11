package com.util;

import java.util.Properties;

public class ConfigProperties {
	private static Properties urlProps;
	private static String IP;

	static {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>ip>>>>>>>>>>");
		Properties props = new Properties();
		try {				
			props.load(ConfigProperties.class.getResourceAsStream("database.properties"));
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		urlProps = props;
		IP = urlProps.getProperty("ip");
		System.out.println(IP+">>>>>>>ip>>>>>>>>>>");
	}

	public static String getIP() {
		return IP;
	}

	public static void setIP(String iP) {
		IP = iP;
	}
}