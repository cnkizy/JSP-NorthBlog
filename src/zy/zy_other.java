package zy;

import java.util.Calendar;

public class zy_other {
	private String changv(int value){
		String values=String.valueOf(value);
		if(value<10){
		return   "0"+values;
		}
		return values;
		}

	public String GetTimeStamp14(){//获取14位数字的时间戳  比如“20160921200445”
		Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH); 
			int date = c.get(Calendar.DATE);  
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second = c.get(Calendar.SECOND);  
		return ""+year+changv(month) + changv(date) + changv(hour) + changv(minute) + changv(second);  
	}
	
	
	public String GetTimeStamp14str(){//获取14位数字的时间戳  比如“20160921200445”
		Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH); 
			int date = c.get(Calendar.DATE);  
			int hour = c.get(Calendar.HOUR_OF_DAY); 
			int minute = c.get(Calendar.MINUTE); 
			int second = c.get(Calendar.SECOND);  
		return ""+year+"年"+changv(month)+"月" + changv(date)+"日" + changv(hour)+"时" + changv(minute)+"分" + changv(second)+"秒";  
	}
	
	
	
	
	
	
	
	
	
	
}

