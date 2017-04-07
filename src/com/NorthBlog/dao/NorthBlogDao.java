package com.NorthBlog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import com.NorthBlog.dto.*;
import com.NorthBlog.mytools.*;


public class NorthBlogDao {
	private DB mydb=new DB();
	
	//�˶��û��������Ƿ���ȷ��������Ȩ��
	public int Login(users_password users) throws ClassNotFoundException, SQLException{
	    int pow=-1;
	    
	    mydb.openDB();//�������ݿ�    
	    String sql="select * from users_password where u_username=?;";//����SQL���
	    
	    Object[] params={users.getU_username()};
		ResultSet res=mydb.executeQuery(sql, params);
		 
	     while(res.next()){ //�Խ�������ж�	 
	    	 if(res.getString("u_password").equals(users.getU_password()) ){    			   
	    			 pow=res.getInt("u_power");	    		 	    	    		    		 
	    	 }
	   }
	   res.close();
	   mydb.close();//�ر����ݿ�
		
	return pow;//-1 ��ʾ ʧ�� û������û����������
	}
	
	// �Ƿ���ڸ��û�  
	public boolean CheckUsers(users_password users) throws ClassNotFoundException, SQLException{
		boolean check=false;
		 mydb.openDB();//�������ݿ�    
		 
		  String sql="select u_username from users_password where u_username=?;";	  
		  Object[] params={users.getU_username()};
		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  check=true;
		  }
		     
		   res.close();
		   mydb.close();//�ر����ݿ�
		  		
	return check;
	}
	
	//��id�Ƿ����
	public boolean CheckUsersid(users_password users) throws ClassNotFoundException, SQLException{
		boolean check=false;	
		 mydb.openDB();//�������ݿ�    
		
		  String sql="select u_id from users_password where u_id=?;";
		  Object[] params={users.getU_id()};		  	  
		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  check=true;
		  }
		     
		   res.close();
		   mydb.close();//�ر����ݿ�
		   
	return check;
	}
	
	//ע���û� 
	public boolean RegUsers(users_password users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		 mydb.openDB();//�������ݿ�    
			
		  String sql="insert users_password(u_username,u_password,u_power) values(?,?,0);";
		  Object[] params={users.getU_username(),users.getU_password()};		  	  
 
		  if(mydb.executeUpdate(sql, params)){//ִ��SQL���ɹ��� Ӱ�����������0ʱ����ע��ɹ�������true;
			  change= true;
		  }
		     
		  mydb.close();
			
	return change;
	}
	
	//�޸��û�Ȩ��  ��id,_pow��
	public boolean SetPow(users_password users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		 mydb.openDB();//�������ݿ�    
		 
		  String sql="update users_password set u_power=? where u_id=?;";
		  Object[] params={users.getU_power(),users.getU_id()};	
	  
		  if(mydb.executeUpdate(sql, params)){//ִ��SQL���ɹ��� Ӱ�����������0ʱ����ע��ɹ�������true;
			  change= true;
		  }
		  mydb.close();
		 	
	return change;
	}
	
	//�����û�����ȡ�û���ID  ��user��
	public int GetUserId(users_password users) throws ClassNotFoundException, SQLException{
		int id=-1;
		 mydb.openDB();//�������ݿ�    
		  
		  String sql="select u_id,u_username from users_password where u_username=?;";
		  Object[] params={users.getU_username()};		  
		  ResultSet res=mydb.executeQuery(sql, params);
		   
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  id=res.getInt("u_id");
		  }
		     
		   res.close();
		   mydb.close();//�ر����ݿ�
			
	return id;//��ȡʧ��
	}
	
	//����ID��ȡ�û��ĸ�����ϢInfo
	public users_info GetUser(users_info userinfo) throws ClassNotFoundException, SQLException{
		 mydb.openDB();//�������ݿ�    
		  
		  String sql="select * FROM users_info where u_id=?;";
  
		  Object[] params={userinfo.getU_id()};		  
		  ResultSet res=mydb.executeQuery(sql, params);
	  		  
		  
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  
			  userinfo.setU_nickname(res.getString("u_nickname"));
			  userinfo.setU_sex(res.getString("u_sex"));
			  userinfo.setU_ico(res.getString("u_ico"));
			  userinfo.setU_sign(res.getString("u_sign"));

		  }
		     
		   res.close();		 
		   mydb.close();//�ر����ݿ�
			
	return userinfo;//��ȡʧ��
	}
	
	//�޸����� 
	public boolean ChangePassword(users_password users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		mydb.openDB();//�������ݿ�  
		 
		  String sql="update users_password SET u_password=? where u_id=?;";
		  Object[] params={users.getU_password(), users.getU_id()};		 
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		 	   		 
		  mydb.close();//�ر����ݿ�
			
	return change;
	}
	
	//�����û�����
	public boolean SetOnline(users_online users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		tools time=new tools();
		mydb.openDB();//�������ݿ�  
		  
		  String sql="insert users_online(u_id,o_time) values(?,'"+time.GetTimeStamp14()+"');";
		 
		  Object[] params={users.getU_id()};	
  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		     	   		 
		
		  mydb.close();//�ر����ݿ�
			
	return change;
	}
	
	//�����û�����  ��id��
	public boolean SetOutline(users_online users) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		mydb.openDB();//�������ݿ�  
		 
		  String sql="DELETE FROM users_online WHERE (u_id=?);";		  
		  Object[] params={users.getU_id()};	

		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		  
		  mydb.close();//�ر����ݿ� 
		   		
	return change;
	}
	
	// ��ȡ��������  ����
	public int GetlinePeople() throws ClassNotFoundException, SQLException{
		int people=0;
		 mydb.openDB();//�������ݿ�
		 
		 
		  String sql="select count(u_id) FROM users_online ;";		
		  Object[] params={};	
		  ResultSet res=mydb.executeQuery(sql, params);
		  
		  if(res.next()){
			  people=res.getInt(1);
		  }
		   		 
		   res.close();
		   mydb.close();//�ر����ݿ�
			
	return people;
	}
	
	//��ѯ�û��Ƿ�����  ��user��
	public boolean GetUserIsLine(users_online users) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		 mydb.openDB();//�������ݿ�
		 
		  String sql="select u_id from users_online where u_id=?;";		  
	   
		  Object[] params={users.getU_id()};	
		  
		  ResultSet res=mydb.executeQuery(sql, params);
		  
		  
		  if(res.next()){		  
			  change=true;
		  }
		  
		   res.close();		 
		   mydb.close();//�ر����ݿ�
			
	return change;
	}
	
	//��ѯ�û�Ȩ�� ��user
	public int GetUserPow(users_online users) throws ClassNotFoundException, SQLException{
		int pow=-1;	
		 mydb.openDB();//�������ݿ�
		 
		  String sql="select u_power from users_password where u_id=?;";		  
		  Object[] params={users.getU_id()};	
	
		  ResultSet res=mydb.executeQuery(sql, params);
		  	  
		  if(res.next()){
			  pow=res.getInt(1);
		  }
		   res.close();		 
		   mydb.close();//�ر����ݿ�
			
	return pow;
	}
	
	//д��־  ��user��
	public boolean SetLog(admin_log log) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		mydb.openDB();//�������ݿ�
		  
		  String sql="insert admin_log(u_id,l_note,l_time,ip) values(?,?,?,?);";	  	  		  
		  
		  Object[] params={log.getU_id(),log.getL_note(),log.getL_time(),log.getIp()};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		  
		  mydb.close();//�ر����ݿ� 
			
	return change;
	}
	
	// �����־  ��user��
	public boolean ClearLog() throws ClassNotFoundException, SQLException{
		boolean change=false;
		mydb.openDB();//�������ݿ�
		 
		  String sql="delete from admin_log;";
		  		  
		  Object[] params={};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		  
		   mydb.close();//�ر����ݿ� 
			
	return change;
	}
	
	//�鿴��־  ��user��
	public ArrayList<admin_log> GetLog() throws ClassNotFoundException, SQLException{
		
		mydb.openDB();//�������ݿ�

		ArrayList<admin_log> arrlog=new ArrayList<admin_log>();
		String sql="select * from admin_log;";//�������־���ȡ����
		  
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
	
	//�鿴��������  ��id��
	public ArrayList<users_data> GetUserData(users_data data) throws ClassNotFoundException, SQLException{
		mydb.openDB();//�������ݿ�
	  
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
	
	//ɾ��ָ����������
	public boolean DelUserData(users_data data) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		mydb.openDB();//�������ݿ�
		  
		  String sql="DELETE FROM users_data WHERE (t_id=?);";		  
		  Object[] params={data.getT_id()};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;	 
		  }
		  mydb.close();
			
	return change;
	}
	
	//��Ӳ���
	public boolean AddUserData(users_data data) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		mydb.openDB();//�������ݿ�
		  
		  String sql="insert users_data(u_id,t_title,t_data,t_time) values(?,?,?,?);";	  
	  
		  Object[] params={data.getU_id(),data.getT_title(),data.getT_data(),data.getT_time()};	
		    
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }
		   		 	
		  mydb.close();
			
	return change;
	}

	//�޸Ĳ���
	public boolean ChangeUserData(users_data data) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		mydb.openDB();//�������ݿ�
		  
		  String sql="update users_data SET t_title=?,t_data=? where (t_id=?);";
		  
		  Object[] params={data.getT_title(),data.getT_data(),data.getT_id()};	
		  		  	  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }     
		   		 
		  mydb.close();
			
	return change;
	}
	
	//�޸��û�������Ϣ
	public boolean ChangeUserSelfInfo(users_info info) throws ClassNotFoundException, SQLException{		
		boolean change=false;
		
		mydb.openDB();//�������ݿ�
		  
		  String sql="UPDATE users_info SET u_nickname=?, u_sex=?, u_ico=?, u_sign=? WHERE (u_id=?)";
		  
		  Object[] params={info.getU_nickname(),info.getU_sex(),info.getU_ico(),info.getU_sign(),info.getU_id()};	
		  
		  if(mydb.executeUpdate(sql, params)){
			  change=true;
		  }     
		   		 
		  mydb.close();
		 
	return change;
	}
	
	//�����û�������Ϣ
	public boolean InsertUserSelfInfo(users_info info) throws ClassNotFoundException, SQLException{
		boolean change=false;

		mydb.openDB();//�������ݿ�
		  
		  String sql="INSERT INTO users_info (u_id,u_nickname,u_sex,u_ico,u_sign) VALUES (?,?,?,?,?)";
		  
		  Object[] params={info.getU_id(),info.getU_nickname(),info.getU_sex(),info.getU_ico(),info.getU_sign()};	
		    
          if(mydb.executeUpdate(sql, params)){       	  
        	  change=true;
          }
 
          mydb.close();
          
	return change;
	}
	
	
	
}
