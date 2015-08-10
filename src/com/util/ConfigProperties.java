package com.util;



import java.util.Properties;

public class ConfigProperties {
    private static String propertiesName = "src/com/config/database.properties";

    private static String IP;
   private static String PORT;


    static {
        try {
        	/*
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(propertiesName));

            ip = prop.getProperty("ip");
          */
        	IP = "172.20.1.120:";
        	PORT = "8080";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public static String getIP() {
		return IP;
	}


	public static void setIP(String iP) {
		IP = iP;
	}


	public static String getPORT() {
		return PORT;
	}


	public static void setPORT(String pORT) {
		PORT = pORT;
	}


	
}
