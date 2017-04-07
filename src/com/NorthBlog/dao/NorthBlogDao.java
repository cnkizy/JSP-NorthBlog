package com.NorthBlog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import com.NorthBlog.dto.*;
import com.NorthBlog.mytools.*;


public class NorthBlogDao {
	private DB mydb=new DB();
	
	//核对用户和密码是否正确，并返回权限
	public int Login(users_password users) throws ClassNotFoundException, SQLException{
	    int pow=-1;
	    
	    mydb.openDB();//连接数据库    
	    String sql="select * from users_password where u_username=?;";//定义SQL语句
	    
	    Object[] params={users.getU_username()};
		ResultSet res=mydb.executeQuery(sql, params);
		 
	     while(res.next()){ //对结果进行判断	 
	    	 if(res.getString("u_password").equals(users.getU_password()) ){    			   
	    			 pow=res.getInt("u_power");	    		 	    	    		    		 
	    	 }
	   }
	   res.close();
	   mydb.close();//关闭数据库
		
	return pow;//-1 表示 失败 没有这个用户或密码错误
	}
	
	// 是否存在该用户  
	public boolean CheckUsers(users_password users) throws ClassNotFoundException, SQLException{
		boolean check=false;
		 mydb.openDB();//连接数据库    
		 
		  String sql="select u_username from users_password where u_username=?;";	  
		  Object[] params={users.getU_username()};
		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  check=true;
		  }
		     
		   res.close();
		   mydb.close();//关闭数据库
		  		
	return check;
	}
	
	//该id是否存在
	public boolean CheckUsersid(users_password users) throws ClassNotFoundException, SQLException{
		boolean check=false;	
		 mydb.openDB();//连接数据库    
		
		  String sql="select u_id from users_password where u_id=?;";
		  Object[] params={users.getU_id()};		  	  
		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  check=true;
		  }
		     
		   res.close();
		   mydb.close();//关闭数据库
		   
	return check;
	}
	
	//注册用户 
	public boolean RegUsers(users_password users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		 mydb.openDB();//连接数据库    
			
		  String sql="insert users_password(u_username,u_password,u_power) values(?,?,0);";
		  Object[] params={users.getU_username(),users.getU_password()};		  	  
 
		  if(mydb.executeUpdate(sql, params)){//执行SQL语句成功后 影响的条数大于0时，则注册成功，返回true;
			  change= true;
		  }
		     
		  mydb.close();
			
	return change;
	}
	
	//修改用户权限  （id,_pow）
	public boolean SetPow(users_password users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		 mydb.openDB();//连接数据库    
		 
		  String sql="update users_password set u_power=? where u_id=?;";
		  Object[] params={users.getU_power(),users.getU_id()};	
	  
		  if(mydb.executeUpdate(sql, params)){//执行SQL语句成功后 影响的条数大于0时，则注册成功，返回true;
			  change= true;
		  }
		  mydb.close();
		 	
	return change;
	}
	
	//根据用户名获取用户的ID  （user）
	public int GetUserId(users_password users) throws ClassNotFoundException, SQLException{
		int id=-1;
		 mydb.openDB();//连接数据库    
		  
		  String sql="select u_id,u_username from users_password where u_username=?;";
		  Object[] params={users.getU_username()};		  
		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  id=res.getInt("u_id");
		  }
		     
		   res.close();
		   mydb.close();//关闭数据库
			
	return id;//获取失败
	}
	
	//根据ID获取用户的个性信息Info
	public users_info GetUser(users_info userinfo) throws ClassNotFoundException, SQLException{
		 mydb.openDB();//连接数据库    
		  
		  String sql="select * FROM users_info where u_id=?;";
  
		  Object[] params={userinfo.getU_id()};		  
		  ResultSet res=mydb.executeQuery(sql, params);
	  		  
		  
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  
			  userinfo.setU_nickname(res.getString("u_nickname"));
			  userinfo.setU_sex(res.getString("u_sex"));
			  userinfo.setU_ico(res.getString("u_ico"));
			  userinfo.setU_sign(res.getString("u_sign"));

		  }
		     
		   res.close();		 
		   mydb.close();//关闭数据库
			
	return userinfo;//获取失败
	}
	
	//修改密码 
	public boolean ChangePassword(users_password users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		mydb.openDB();//连接数据库  
		 
		  String sql="update users_password SET u_password=? where u_id=?;";
		  Object[] params={users.getU_password(), users.getU_id()};		 
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		 	   		 
		  mydb.close();//关闭数据库
			
	return change;
	}
	
	//设置用户在线
	public boolean SetOnline(users_online users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		tools time=new tools();
		mydb.openDB();//连接数据库  
		  
		  String sql="insert users_online(u_id,o_time) values(?,'"+time.GetTimeStamp14()+"');";
		 
		  Object[] params={users.getU_id()};	
  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		     	   		 
		
		  mydb.close();//关闭数据库
			
	return change;
	}
	
	//设置用户下线  （id）
	public boolean SetOutline(users_online users) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		mydb.openDB();//连接数据库  
		 
		  String sql="DELETE FROM users_online WHERE (u_id=?);";		  
		  Object[] params={users.getU_id()};	

		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		  
		  mydb.close();//关闭数据库 
		   		
	return change;
	}
	
	// 获取在线人数  （）
	public int GetlinePeople() throws ClassNotFoundException, SQLException{
		int people=0;
		 mydb.openDB();//连接数据库
		 
		 
		  String sql="select count(u_id) FROM users_online ;";		
		  Object[] params={};	
		  ResultSet res=mydb.executeQuery(sql, params);
		  
		  if(res.next()){
			  people=res.getInt(1);
		  }
		   		 
		   res.close();
		   mydb.close();//关闭数据库
			
	return people;
	}
	
	//查询用户是否在线  （user）
	public boolean GetUserIsLine(users_online users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		 mydb.openDB();//连接数据库
		 
		  String sql="select u_id from users_online where u_id=?;";		  
	   
		  Object[] params={users.getU_id()};	
		  
		  ResultSet res=mydb.executeQuery(sql, params);
		  
		  
		  if(res.next()){		  
			  change=true;
		  }
		  
		   res.close();		 
		   mydb.close();//关闭数据库
			
	return change;
	}
	
	//查询用户权限 （user
	public int GetUserPow(users_online users) throws ClassNotFoundException, SQLException{
		int pow=-1;	
		 mydb.openDB();//连接数据库
		 
		  String sql="select u_power from users_password where u_id=?;";		  
		  Object[] params={users.getU_id()};	
	
		  ResultSet res=mydb.executeQuery(sql, params);
		  	  
		  if(res.next()){
			  pow=res.getInt(1);
		  }
		   res.close();		 
		   mydb.close();//关闭数据库
			
	return pow;
	}
	
	//写日志  （user）
	public boolean SetLog(admin_log log) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		mydb.openDB();//连接数据库
		  
		  String sql="insert admin_log(u_id,l_note,l_time,ip) values(?,?,?,?);";	  	  		  
		  
		  Object[] params={log.getU_id(),log.getL_note(),log.getL_time(),log.getIp()};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		  
		  mydb.close();//关闭数据库 
			
	return change;
	}
	
	// 清空日志  （user）
	public boolean ClearLog() throws ClassNotFoundException, SQLException{
		boolean change=false;
		mydb.openDB();//连接数据库
		 
		  String sql="delete from admin_log;";
		  		  
		  Object[] params={};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		  
		   mydb.close();//关闭数据库 
			
	return change;
	}
	
	//查看日志  （user）
	public ArrayList<admin_log> GetLog() throws ClassNotFoundException, SQLException{
		
		mydb.openDB();//连接数据库

		ArrayList<admin_log> arrlog=new ArrayList<admin_log>();
		String sql="select * from admin_log;";//如果有日志则读取所有
		  
		Object[] params={};	
		ResultSet res=mydb.executeQuery(sql, params);

		  while(res.next()){	  
			  admin_log log=new admin_log();
			  
			  log.setIp(res.getString("ip"));			  
			  log.setL_id(res.getInt("l_id"));			  
			  log.setU_id(res.getInt("u_id"));
			  
			  log.setL_note(res.getString("l_note"));			  
			  log.setL_time(res.getString("l_time"));
			  
			  arrlog.add(log);
		  }
 
		   res.close();
		   mydb.close();
	return arrlog;
	}
	
	//查看博客数据  （id）
	public ArrayList<users_data> GetUserData(users_data data) throws ClassNotFoundException, SQLException{
		mydb.openDB();//连接数据库
	  
		  String sql="select t_id,t_title,t_data,t_time from users_data where u_id=?;";
		  Object[] params={data.getU_id()};	

		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  ArrayList<users_data> arrdata=new ArrayList<users_data>();
		  
		  while(res.next()){	  
		    users_data datmp=new users_data();
			  
			datmp.setT_data(res.getString("t_data")); 			  
			datmp.setT_id(res.getInt("t_id"));
			datmp.setT_time(res.getString("t_time"));
			datmp.setT_title(res.getString("t_title"));
			
			arrdata.add(datmp);
			
		  }
 
		  res.close();
		  mydb.close();
		  
	return arrdata;
	}
	
	//删除指定博客数据
	public boolean DelUserData(users_data data) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		mydb.openDB();//连接数据库
		  
		  String sql="DELETE FROM users_data WHERE (t_id=?);";		  
		  Object[] params={data.getT_id()};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;	 
		  }
		  mydb.close();
			
	return change;
	}
	
	//添加博客
	public boolean AddUserData(users_data data) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		mydb.openDB();//连接数据库
		  
		  String sql="insert users_data(u_id,t_title,t_data,t_time) values(?,?,?,?);";	  
	  
		  Object[] params={data.getU_id(),data.getT_title(),data.getT_data(),data.getT_time()};	
		    
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		   		 	
		  mydb.close();
			
	return change;
	}

	//修改博客
	public boolean ChangeUserData(users_data data) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		mydb.openDB();//连接数据库
		  
		  String sql="update users_data SET t_title=?,t_data=? where (t_id=?);";
		  
		  Object[] params={data.getT_title(),data.getT_data(),data.getT_id()};	
		  		  	  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }     
		   		 
		  mydb.close();
			
	return change;
	}
	
	//修改用户个性信息
	public boolean ChangeUserSelfInfo(users_info info) throws ClassNotFoundException, SQLException{		
		boolean change=false;
		
		mydb.openDB();//连接数据库
		  
		  String sql="UPDATE users_info SET u_nickname=?, u_sex=?, u_ico=?, u_sign=? WHERE (u_id=?)";
		  
		  Object[] params={info.getU_nickname(),info.getU_sex(),info.getU_ico(),info.getU_sign(),info.getU_id()};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }     
		   		 
		  mydb.close();
		 
	return change;
	}
	
	//增加用户个性信息
	public boolean InsertUserSelfInfo(users_info info) throws ClassNotFoundException, SQLException{
		boolean change=false;

		mydb.openDB();//连接数据库
		  
		  String sql="INSERT INTO users_info (u_id,u_nickname,u_sex,u_ico,u_sign) VALUES (?,?,?,?,?)";
		  
		  Object[] params={info.getU_id(),info.getU_nickname(),info.getU_sex(),info.getU_ico(),info.getU_sign()};	
		    
          if(mydb.executeUpdate(sql, params)){       	  
        	  change=true;
          }
 
          mydb.close();
          
	return change;
	}
	
	
	
}
