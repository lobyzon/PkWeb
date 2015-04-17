package com.loris.utils;

import com.loris.bean.Success;

public class SuccessUtils {			
	
	public static Success setSuccessBean(String title, String message){
		return new Success(title, message);
	}
}