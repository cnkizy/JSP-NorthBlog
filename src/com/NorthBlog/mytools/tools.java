package com.NorthBlog.mytools;

import java.util.Calendar;

public class tools {
	private String changv(int value){
		String values=String.valueOf(value);
		if(value<10){
		return   "0"+values;
		}
		return values;
		}

	public String GetTimeStamp14(){//��ȡ14λ���ֵ�ʱ���  ���硰20160921200445��
		Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH); 
			int date = c.get(Calendar.DATE);  
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second = c.get(Calendar.SECOND);  
		return ""+year+changv(month) + changv(date) + changv(hour) + changv(minute) + changv(second);  
	}
}
