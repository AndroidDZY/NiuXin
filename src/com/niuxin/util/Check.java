package com.niuxin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
	
	public static boolean isEmpty(String res){//判断输入是否为空
		if(null==res||res.trim().equals(""))
			return false;	
		return true;
	}
	
	public static boolean positiveInteger(String res) {//判断输入是否是正整数
		  Pattern pattern = Pattern.compile( "^[0-9]*[1-9][0-9]*$");
	        Matcher isNum = pattern.matcher(res);
	
		return isNum.matches();
	}	
	public static boolean positive(String res) {//判断输入是否是正数
		  Pattern pattern = Pattern.compile( "^((\\d+(\\.\\d+)?)|(0+(\\.0+)?))$");
	       Matcher isNum = pattern.matcher(res);
	
		return isNum.matches();
	}
}
