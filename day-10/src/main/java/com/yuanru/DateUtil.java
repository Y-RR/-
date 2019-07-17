package com.yuanru;

import java.util.Calendar;

public class DateUtil {

	private DateUtil(){}
	
	
	//根据传入的日期获取年龄
	public static int getAge (Calendar birthday) {
		if(birthday == null){
			throw new IllegalArgumentException("生日参数不能为空");
		}
		
		Calendar now = Calendar.getInstance();
		
		if(birthday.compareTo(now)>0){
			throw new IllegalArgumentException("生日参数不能超出当前日期");
		}
		
		int nowYear = now.get(Calendar.YEAR);
		int nowMonth = now.get(Calendar.MONTH);
		int nowDate = now.get(Calendar.DATE);
		
		int birthYear = birthday.get(Calendar.YEAR);
		int birthMonth = birthday.get(Calendar.MONTH);
		int birthDate = birthday.get(Calendar.DATE);
		
		int age = nowYear - birthYear;
		
		if(birthYear > nowYear){
			age --;
			if(birthMonth == nowMonth){
				if(birthDate > birthDate){
					age --;
				}
			}
		}
		return age;
	}

}
