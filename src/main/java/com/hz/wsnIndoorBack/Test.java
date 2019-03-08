package com.hz.wsnIndoorBack;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		String endTime = sdf.format(new Date());
		System.out.println(endTime);
	}
}
