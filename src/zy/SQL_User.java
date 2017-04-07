package zy;
import java.sql.*;
public class SQL_User {
	
	SQL_INFO SQL=new SQL_INFO();//SQL ������ ���ݿ��� �Լ��������Ϣ
/**
* @author 2015��һ��Խ
*=========================================================================================
*       						    ����ģ�������Ŀ¼��������
*=========================================================================================
*
*	����ֵ		������			����ֵ										ע��
*
*	_int       Login			(String l_user,String l_pass) 			  //�˶��û��������Ƿ���ȷ
*	boolean    CheckUsers		(String l_user)      		  			  //�Ƿ���ڸ��û�
*	boolean    CheckUsersid		(String l_id)  	    		  			  //�Ƿ���ڸ�id
*	boolean    RegUsers	     	(String l_user,String l_pass,String l_pow)//ע���û�
*	boolean    SetPow			(String l_id,String l_pow)			      //�޸�Ȩ��  
* 	_int       GetUserId		(String l_user)							  //�����û�����ȡ�û���ID 
* 	String[] GetUser(String l_id)��ȡ�û�������Ϣ
*  int GetUserPow(String l_id) ��ѯ�û�Ȩ�� �Ƿ�Ϊ����Ա
*	boolean    ChangePassword	(String l_id,String _newpassword)		  //�޸�����
* 	boolean    SetOnline		(String l_id)							  //�����û�����
*	boolean    SetOutline		(String l_id)							  //�����û�����
*	int 	   GetOutline()												  //��ȡ��������
*	boolean    GetUserIsLine	(String l_id)							  //��ѯ�û��Ƿ�����
*	boolean	   SetLog			(String u_id,String l_note,String l_time,String _ip) //д��־
* 	boolean	   ClearLog		    ()										  //�����־
*	String[][] GetLog			()										  //��ȡ��־[][˵��,ʱ���,_ip]
*	String[][] GetUserData		(String l_id)							  //�鿴��������[][����,����,ʱ���]
*	boolean	   DelUserData		(String t_id)							  //ɾ��ָ����������(t_id)
*	boolean    AddUserData(String l_id,String t_title,String t_data,String t_time)//д��������
*	boolean	   ChangeUserData	(String t_id,String t_title,String t_data)//�޸���������
*	boolean ChangeUserSelfInfo(String t_id,String t_nickname,String t_sex,String u_ico,String u_sign)�޸���ϸ��Ϣ
*	boolean InsertUserSelfInfo(String t_id,String t_nickname,String t_sex,String u_ico,String u_sign)������ϸ��Ϣ
*
*/
	
	
	
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *  ����MySql�����ļ� ���������ӹܵ����������ӹܵ�
	 *======================================
	 */
	private Connection LoadDriver() throws ClassNotFoundException, SQLException{
		//��������
	    Class.forName(SQL.MYSQL_DRIVERNAME);
	    //�������ݿ�
	    Connection conn=DriverManager.getConnection(SQL.MYSQL_URL,SQL.MYSQL_NAME,SQL.MYSQL_PASSWORD);    
	    return conn;
	}
	

	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *  �˶��û��������Ƿ���ȷ��user��pass����������Ȩ�� ����ֵ 
	 *======================================
	 *
	  -1 ��ʾ�˶�ʧ�� û������û����߸��û��������
	  
	  >= 0 ����ʾ��½�ɹ�
	  
	  0	Ȩ��Ϊ	��ͨ�û�
	  1	Ȩ��Ϊ	�����û�
	 */
	public int Login(String l_user,String l_pass) throws ClassNotFoundException, SQLException{
	    int pow=-1;
	    Connection conn=LoadDriver();//�������ݿ�    
	    Statement stmt=conn.createStatement();//��������
	    String sql="select * from users_password;";//����SQL���
	    ResultSet res=stmt.executeQuery(sql);//ִ��SQL��䣬�õ������
	   
	     while(res.next()){ //�Խ�������ж�
	  
	    	 if(res.getString("u_username").equals(l_user) && res.getString("u_password").equals(l_pass)){    			   
	    			 pow=res.getInt("u_power");	    		 	    	    		    		 
	    	 }
	   }
	   res.close();
	   stmt.close();
	   conn.close();
		
	return pow;//-1 ��ʾ ʧ�� û������û����������
	}
		
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �Ƿ���ڸ��û�  ��user,pass��
	 *======================================
	 *
	  false	Ϊ	�����ڸ��û�
	  true	Ϊ	���ڸ��û�
	 */ 

	public boolean CheckUsers(String l_user) throws ClassNotFoundException, SQLException{
		boolean check=false;
		  
		  Connection conn=LoadDriver();
	  
		  String sql="select u_username from users_password where u_username=?;";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setString(1, l_user);
		  
		  ResultSet res=prpstmt.executeQuery();
		   
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  check=true;
		  }
		     
		   res.close();
		   prpstmt.close();
		   conn.close();
			
	return check;
	}

	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �Ƿ���ڸ�id  ��user,pass��
	 *======================================
	 *
	  false	Ϊ	�����ڸ��û�
	  true	Ϊ	���ڸ��û�
	 */ 

	public boolean CheckUsersid(int l_id) throws ClassNotFoundException, SQLException{
		boolean check=false;
		
		  Connection conn=LoadDriver();
		 
		  String sql="select u_id from users_password where u_id=?;";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setInt(1, l_id);
		  
		  ResultSet res=prpstmt.executeQuery();
		   
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  check=true;
		  }
		     
		   res.close();
		   prpstmt.close();
		   conn.close();
			
	return check;
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      ע���û�  ��user��
	 *======================================
	 *

	  false	Ϊ	ע��ʧ��(�����ݿ������и��û���ʧ��)
	  true	Ϊ	ע��ɹ�
	 */
	public boolean RegUsers(String l_user,String l_pass,String l_pow) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		  String sql="insert users_password(u_username,u_password,u_power) values(?,?,?);";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setString(1, l_user);
		  prpstmt.setString(2, l_pass);		  
		  prpstmt.setString(3, l_pow);
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res>0){//ִ��SQL���ɹ��� Ӱ�����������0ʱ����ע��ɹ�������true;
			  change= true;
		  }
		     
		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �޸�Ȩ��  ��user,_pow��
	 *======================================
	 *

	  false	Ϊ	�޸�ʧ��
	  true	Ϊ	�޸ĳɹ�
	 */
	public boolean SetPow(String l_id,String l_pow) throws ClassNotFoundException, SQLException{
		boolean change=false;
		  Connection conn=LoadDriver();
		 
		  String sql="update users_password set u_power=? where u_id=?;";
		   
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	  
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res>0){//ִ��SQL���ɹ��� Ӱ�����������0ʱ����ע��ɹ�������true;
			  change= true;
		  }
		     
		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �����û�����ȡ�û���ID  ��user��
	 *======================================
	 *
	  ����_int �û���Id	  -1��ʾ��ѯʧ��
	 */
	public int GetUserId(String l_user) throws ClassNotFoundException, SQLException{
		int id=-1;
		
		  Connection conn=LoadDriver();
		  
		  String sql="select u_id,u_username from users_password where u_username=?;";
  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  
		  prpstmt.setString(1, l_user);
		  
		  ResultSet res=prpstmt.executeQuery();
		  
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  id=res.getInt("u_id");
		  }
		     
		   res.close();		 
		   prpstmt.close();
		   conn.close();
			
	return id;//��ȡʧ��
	}
	

	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      ����ID��ȡ�û��ĸ�����ϢInfo
	 *======================================
	 *
	  ����_int �û���Id	  -1��ʾ��ѯʧ��
	 */
	public String[] GetUser(int l_id) throws ClassNotFoundException, SQLException{
		String id[]=new String[4];
		
		  Connection conn=LoadDriver();
		  
		  String sql="select * FROM users_info where u_id=?;";
  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
	  
		  prpstmt.setInt(1, l_id);
		  
		  ResultSet res=prpstmt.executeQuery();
		  
		  if(res.next()){//��������ݿ��з���������û����򷵻�true;
			  id[0]=res.getString("u_nickname");
			  id[1]=res.getString("u_sex");
			  id[2]=res.getString("u_ico");
			  id[3]=res.getString("u_sign");
		  }
		     
		   res.close();		 
		   prpstmt.close();
		   conn.close();
			
	return id;//��ȡʧ��
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �޸�����  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean ChangePassword(String l_id,String newpassword) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		 
		  String sql="update users_password SET u_password=? where u_id=?;";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  
		  prpstmt.setString(1, newpassword);
		  prpstmt.setString(2, l_id);
		  
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }
		     
		   		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �޸�����  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean ChangePassword(int l_id,String newpassword) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		 
		  String sql="update users_password SET u_password=? where u_id=?;";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  
		  prpstmt.setString(1, newpassword);
		  prpstmt.setInt(2,l_id);		  
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }		     
		   		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �����û�����  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean SetOnline(int l_id) throws ClassNotFoundException, SQLException{
		boolean change=false;
		zy_other tim=new zy_other();
		  Connection conn=LoadDriver();
		  
		  String sql="insert users_online(u_id,o_time) values(?,'"+tim.GetTimeStamp14()+"');";
		 
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  prpstmt.setInt(1,l_id);
		  
		  
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }
		     
		   		 
		  prpstmt.close();
		   conn.close();
			
	return change;
	}
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �����û�����  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean SetOutline(String l_id) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		  Connection conn=LoadDriver();
		 
		  String sql="DELETE FROM users_online WHERE (u_id=?);";		  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  prpstmt.setInt(1, Integer.valueOf(l_id));
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }
		   		 
		   prpstmt.close();
		   conn.close();
			
	return change;
	}
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      ��ȡ��������  ����
	 *======================================
	 *
	  ����int   
	 */
	public int GetlinePeople() throws ClassNotFoundException, SQLException{
		int people=0;
	
		  Connection conn=LoadDriver();
		 
		  String sql="select count(u_id) FROM users_online ;";		  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  ResultSet res=prpstmt.executeQuery();
		  
		  if(res.next()){
			  people=res.getInt(1);
		  }
		   		 
		   prpstmt.close();
		   conn.close();
			
	return people;
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      ��ѯ�û��Ƿ�����  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ ���� 
	  false��ʾ ������
	 */
	public boolean GetUserIsLine(String l_id) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		 
		  String sql="select u_id from users_online where u_id=?;";		  
	   
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  
		  prpstmt.setInt(1, Integer.valueOf(l_id));
		  
		  ResultSet res=prpstmt.executeQuery();
		  
		  
		  if(res.next()){
			  change=true;
		  }
		   res.close();		 
		   prpstmt.close();
		   conn.close();
			
	return change;
	}
	

	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      ��ѯ�û�Ȩ�� ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ ���� 
	  false��ʾ ������
	 */
	public int GetUserPow(String l_id) throws ClassNotFoundException, SQLException{
		int pow=-1;
		
		  Connection conn=LoadDriver();
		 
		  String sql="select u_power from users_password where u_id=?;";		  
	   
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  
		  prpstmt.setInt(1, Integer.valueOf(l_id));
		  
		  ResultSet res=prpstmt.executeQuery();
		  
		  
		  if(res.next()){
			  pow=res.getInt(1);
		  }
		   res.close();		 
		   prpstmt.close();
		   conn.close();
			
	return pow;
	}
	
	
	

	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      д��־  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �ɹ�
	  false��ʾ ʧ��
	 */
	public boolean SetLog(String u_id,String l_note,String l_time,String ip) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		  
		  String sql="insert admin_log(u_id,l_note,l_time,ip) values(?,?,?,?);";	  	  		  
		  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  
		  prpstmt.setInt(1, Integer.valueOf(u_id));
		  prpstmt.setString(2, l_note);
		  prpstmt.setString(3, l_time);
		  prpstmt.setString(4, ip);
		  
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res>0){
			  change=true;
		  }		 	 
		  prpstmt.close();
		   conn.close();
			
	return change;
	}
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �����־  ��user��
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �ɹ�
	  false��ʾ ʧ��
	 */
	public boolean ClearLog() throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		  Statement stmt=conn.createStatement();
		  String sql="delete from admin_log;";
		  		  
		  int res=stmt.executeUpdate(sql);
		  
		  if(res>0){
			  change=true;
		  }		 	 
		   stmt.close();
		   conn.close();
			
	return change;
	}
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �鿴��־  ��user��
	 *======================================
	 *
	  ����һ����ά���飬���� String ��N����4��
	  
	  				    String	n	 0 Ϊ�û�id
	  				    String	n	 1 Ϊ˵��	
	  				    String	n	 2 Ϊ14λ��ʱ���
	  				    String	n	 3 Ϊ����_ip
	  								 
	����NULL��ʾ����־���ȡʧ��
	 */
	public String[][] GetLog() throws ClassNotFoundException, SQLException{
		
		String[][] rest=null;
		int nmb=0;
		
		
		  Connection conn=LoadDriver();
		  Statement stmt=conn.createStatement();
		  
		  String sql="select count(l_id) from admin_log;";
		  ResultSet res=stmt.executeQuery(sql);

		  while(res.next()){
			  
			  nmb=res.getInt(1);
			  System.out.println("��ǰ��־��"+nmb);
			  if(nmb<1){//���������û����־����ֱ�ӷ��ؿ�
				  return rest;
			  }
			  
		  }
		  res.close();
		  
		  
		  sql="select * from admin_log;";//�������־���ȡ����
		  ResultSet rs=stmt.executeQuery(sql);
		  
		  rest=new String[nmb][4];
		  
		  int i=0;	  
		  while(rs.next()){	  
			  rest[i][0]=String.valueOf(rs.getInt("u_id"));
			  rest[i][1]=rs.getString("l_note");
			  rest[i][2]=rs.getString("l_time");
			  rest[i][3]=rs.getString("ip");
			  i++;
		  }
 
		   rs.close();
		   stmt.close();
		   conn.close();		   
	return rest;
	}
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �鿴��������  ��user��
	 *======================================
	 *
	  ����һ����ά���飬���� String ��N����3��
	  					String	n	 0 Ϊ���ݵ�t_id
	  				    String	n	 1 Ϊ����
	  				    String	n	 2 Ϊ����	
	  				    String	n	 3 Ϊ14λʱ���
	  								 
	����NULL��ʾ�����Ի��ȡʧ��
	 */
	public String[][] GetUserData(String l_id) throws ClassNotFoundException, SQLException{
		
		String[][] rest=null;
		int nmb=0;
		
		
		  Connection conn=LoadDriver();		  
		  String sql="select count(u_id) from users_data where u_id=?;";
		  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);		  
		  prpstmt.setInt(1, Integer.valueOf(l_id));	  
		  
		  ResultSet res=prpstmt.executeQuery();

		  while(res.next()){			  
			  nmb=res.getInt(1);
			  if(nmb<1){//���������û����־����ֱ�ӷ��ؿ�
				  res.close();
				  prpstmt.close();
				  return rest;
			  }			  
		  }
		  res.close();
		  prpstmt.close();		   
		  
		  sql="select t_id,t_title,t_data,t_time from users_data where u_id=?  order by t_id;";//�������־���ȡ����
		  PreparedStatement prpstmt2=conn.prepareStatement(sql);	  
		  prpstmt2.setInt(1, Integer.valueOf(l_id));
		  
		  ResultSet res2=prpstmt2.executeQuery();
		  
		  rest=new String[nmb][4];
		  
		  int i=0;	  
		  while(res2.next()){	  
			  rest[i][0]=res2.getString("t_id");
			  rest[i][1]=res2.getString("t_title");
			  rest[i][2]=res2.getString("t_data");
			  rest[i][3]=res2.getString("t_time");
			  i++;
		  }
 
		  res2.close();
		  prpstmt2.close();
		  conn.close();		   
	return rest;
	}
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      ɾ��ָ����������(t_id)
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean DelUserData(String t_id) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		  Connection conn=LoadDriver();
		  
		  String sql="DELETE FROM users_data WHERE (t_id=?);";		  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setInt(1, Integer.valueOf(t_id));
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }
		   		 
		  prpstmt.close();
		   conn.close();
			
	return change;
	}
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �����������(�û�id,����,����,ʱ���)
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean AddUserData(String l_id,String t_title,String t_data,String t_time) throws ClassNotFoundException, SQLException{
		boolean change=false;
	
		  Connection conn=LoadDriver();
		  
		  String sql="insert users_data(u_id,t_title,t_data,t_time) values(?,?,?,?);";	  
	  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  
		  prpstmt.setInt(1, Integer.valueOf(l_id));
		  prpstmt.setString(2, t_title);
		  prpstmt.setString(3, t_data);
		  prpstmt.setString(4, t_time);
		   
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }
		   		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	

	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �޸���־  (t_id,t_title,t_data)
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean ChangeUserData(String t_id,String t_title,String t_data) throws ClassNotFoundException, SQLException{
		boolean change=false;
		
		  Connection conn=LoadDriver();
		  
		  String sql="update users_data SET t_title=?,t_data=? where (t_id=?);";
		  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setString(1, t_title);
		  prpstmt.setString(2, t_data);
		  prpstmt.setInt(3, Integer.valueOf(t_id));
		  		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res >0){
			  change=true;
		  }
		     
		   		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �޸��û�����ϸ��Ϣ  updata
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean ChangeUserSelfInfo(String t_id,String t_nickname,String u_sex,String u_ico,String u_sign) throws ClassNotFoundException, SQLException{		
		boolean change=false;
		
		  Connection conn=LoadDriver();
		  
		  String sql="select count(u_id) from users_info where u_id=?";
		  
		  PreparedStatement prpstmtIS=conn.prepareStatement(sql);
		  prpstmtIS.setInt(1, Integer.valueOf(t_id));
		  
          ResultSet resIS=prpstmtIS.executeQuery();        
          
          //�ж���������û� �͸������� updata
		  if(resIS.next()){
			  if(resIS.getInt(1)>0){
				  resIS.close();
				  prpstmtIS.close();
				  
				  sql="UPDATE users_info SET u_nickname=?, u_sex=?, u_ico=?, u_sign=? WHERE (u_id=?)";
							  
				  PreparedStatement insertIFO=conn.prepareStatement(sql);	
				  
				  insertIFO.setString(1, t_nickname);
				  insertIFO.setString(2, u_sex);
				  insertIFO.setString(3, u_ico);
				  insertIFO.setString(4, u_sign);
				  insertIFO.setInt(5, Integer.valueOf(t_id));
		  
				  if(insertIFO.executeUpdate()>0){
					  change=true;				  
				  }
				  
				  insertIFO.close();
				  conn.close();
				  return change;				  
				  
			  }	  
		 		  	  
		  }
		  
	resIS.close();
	prpstmtIS.close();
	return change;
	}
	
	
	
	/**
	 * @author 2015��һ��Խ
	 *======================================
	 *      �����û�����ϸ��Ϣ  insert
	 *======================================
	 *
	  ����boolean   
	  true ��ʾ �޸ĳɹ� 
	  false��ʾ �޸�ʧ��
	 */
	public boolean InsertUserSelfInfo(String t_id,String t_nickname,String t_sex,String u_ico,String u_sign) throws ClassNotFoundException, SQLException{
		boolean change=false;

		
		  Connection conn=LoadDriver();
		  
		  String sql="INSERT INTO users_info (u_id,u_nickname,u_sex,u_ico,u_sign) VALUES (?,?,?,?,?)";
		  
		  PreparedStatement prpstmtIS=conn.prepareStatement(sql);
		  
		  prpstmtIS.setInt(1, Integer.valueOf(t_id));
		  prpstmtIS.setString(2, t_nickname);
		  prpstmtIS.setString(3, t_sex);
		  prpstmtIS.setString(4, u_ico);
		  prpstmtIS.setString(5, u_sign);		
		  
          if(prpstmtIS.executeUpdate()>0){       	  
        	  change=true;
          }
  
    prpstmtIS.close();
    conn.close();
	return change;
	}
	
	
	
	
	
	
	
	
	
}
