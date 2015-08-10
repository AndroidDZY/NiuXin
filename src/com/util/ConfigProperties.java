package com.util;



import java.util.Properties;

public class ConfigProperties {
    private static String propertiesName = "src/com/config/database.properties";

    private static String ip;
   

    static {
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(propertiesName));

            ip = prop.getProperty("ip");
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


	public static String getIp() {
		return ip;
	}


	public static void setIp(String ip) {
		ConfigProperties.ip = ip;
	}

   
}
