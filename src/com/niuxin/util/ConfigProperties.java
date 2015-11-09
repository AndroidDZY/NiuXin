package com.niuxin.util;

import java.util.Properties;

public class ConfigProperties {
	public static Properties urlProps;
	public static String IP;

	static {		
		Properties props = new Properties();
		try {				
			props.load(ConfigProperties.class.getResourceAsStream("database.properties"));
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		urlProps = props;
	//	IP = urlProps.getProperty("ip");
		
		IP = "http://"+Constants.SERVER_IP+":8080/NiuXinServer";
	}	
}