package com.yuanru;

import java.util.Calendar;

public class DateUtil {

	private DateUtil(){}
	
	
	//���ݴ�������ڻ�ȡ����
	public static int getAge (Calendar birthday) {
		if(birthday == null){
			throw new IllegalArgumentException("���ղ�������Ϊ��");
		}
		
		Calendar now = Calendar.getInstance();
		
		if(birthday.compareTo(now)>0){
			throw new IllegalArgumentException("���ղ������ܳ�����ǰ����");
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
