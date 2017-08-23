package com.excel.util;

public class stringUtil {
	
	public static boolean isBlank(String str){
		if(str !=null && !str.equals("")){
			return false;
		}
		return true;
	}
}
