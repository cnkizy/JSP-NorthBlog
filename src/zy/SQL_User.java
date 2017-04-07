package zy;
import java.sql.*;
public class SQL_User {
	
	SQL_INFO SQL=new SQL_INFO();//SQL 驱动名 数据库名 以及密码等信息
/**
* @author 2015软一钟越
*=========================================================================================
*       						    本类模块的命令目录（公开）
*=========================================================================================
*
*	返回值		函数名			参数值										注释
*
*	_int       Login			(String l_user,String l_pass) 			  //核对用户和密码是否正确
*	boolean    CheckUsers		(String l_user)      		  			  //是否存在该用户
*	boolean    CheckUsersid		(String l_id)  	    		  			  //是否存在该id
*	boolean    RegUsers	     	(String l_user,String l_pass,String l_pow)//注册用户
*	boolean    SetPow			(String l_id,String l_pow)			      //修改权限  
* 	_int       GetUserId		(String l_user)							  //根据用户名获取用户的ID 
* 	String[] GetUser(String l_id)获取用户个性信息
*  int GetUserPow(String l_id) 查询用户权限 是否为管理员
*	boolean    ChangePassword	(String l_id,String _newpassword)		  //修改密码
* 	boolean    SetOnline		(String l_id)							  //设置用户在线
*	boolean    SetOutline		(String l_id)							  //设置用户下线
*	int 	   GetOutline()												  //获取在线人数
*	boolean    GetUserIsLine	(String l_id)							  //查询用户是否在线
*	boolean	   SetLog			(String u_id,String l_note,String l_time,String _ip) //写日志
* 	boolean	   ClearLog		    ()										  //清空日志
*	String[][] GetLog			()										  //获取日志[][说明,时间戳,_ip]
*	String[][] GetUserData		(String l_id)							  //查看留言数据[][标题,内容,时间戳]
*	boolean	   DelUserData		(String t_id)							  //删除指定留言数据(t_id)
*	boolean    AddUserData(String l_id,String t_title,String t_data,String t_time)//写留言数据
*	boolean	   ChangeUserData	(String t_id,String t_title,String t_data)//修改留言数据
*	boolean ChangeUserSelfInfo(String t_id,String t_nickname,String t_sex,String u_ico,String u_sign)修改详细信息
*	boolean InsertUserSelfInfo(String t_id,String t_nickname,String t_sex,String u_ico,String u_sign)插入详细信息
*
*/
	
	
	
	
	
	
	/**
	 * @author 2015软一钟越
	 *======================================
	 *  加载MySql驱动文件 并创建连接管道，返回连接管道
	 *======================================
	 */
	private Connection LoadDriver() throws ClassNotFoundException, SQLException{
		//加载驱动
	    Class.forName(SQL.MYSQL_DRIVERNAME);
	    //连接数据库
	    Connection conn=DriverManager.getConnection(SQL.MYSQL_URL,SQL.MYSQL_NAME,SQL.MYSQL_PASSWORD);    
	    return conn;
	}
	

	/**
	 * @author 2015软一钟越
	 *======================================
	 *  核对用户和密码是否正确（user，pass），并返回权限 整数值 
	 *======================================
	 *
	  -1 表示核对失败 没有这个用户或者该用户密码错误
	  
	  >= 0 均表示登陆成功
	  
	  0	权限为	普通用户
	  1	权限为	超级用户
	 */
	public int Login(String l_user,String l_pass) throws ClassNotFoundException, SQLException{
	    int pow=-1;
	    Connection conn=LoadDriver();//连接数据库    
	    Statement stmt=conn.createStatement();//创建容器
	    String sql="select * from users_password;";//定义SQL语句
	    ResultSet res=stmt.executeQuery(sql);//执行SQL语句，得到结果集
	   
	     while(res.next()){ //对结果进行判断
	  
	    	 if(res.getString("u_username").equals(l_user) && res.getString("u_password").equals(l_pass)){    			   
	    			 pow=res.getInt("u_power");	    		 	    	    		    		 
	    	 }
	   }
	   res.close();
	   stmt.close();
	   conn.close();
		
	return pow;//-1 表示 失败 没有这个用户或密码错误
	}
		
	
	/**
	 * @author 2015软一钟越
	 *======================================
	 *      是否存在该用户  （user,pass）
	 *======================================
	 *
	  false	为	不存在该用户
	  true	为	存在该用户
	 */ 

	public boolean CheckUsers(String l_user) throws ClassNotFoundException, SQLException{
		boolean check=false;
		  
		  Connection conn=LoadDriver();
	  
		  String sql="select u_username from users_password where u_username=?;";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setString(1, l_user);
		  
		  ResultSet res=prpstmt.executeQuery();
		   
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  check=true;
		  }
		     
		   res.close();
		   prpstmt.close();
		   conn.close();
			
	return check;
	}

	/**
	 * @author 2015软一钟越
	 *======================================
	 *      是否存在该id  （user,pass）
	 *======================================
	 *
	  false	为	不存在该用户
	  true	为	存在该用户
	 */ 

	public boolean CheckUsersid(int l_id) throws ClassNotFoundException, SQLException{
		boolean check=false;
		
		  Connection conn=LoadDriver();
		 
		  String sql="select u_id from users_password where u_id=?;";
		  PreparedStatement prpstmt=conn.prepareStatement(sql);
		  prpstmt.setInt(1, l_id);
		  
		  ResultSet res=prpstmt.executeQuery();
		   
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  check=true;
		  }
		     
		   res.close();
		   prpstmt.close();
		   conn.close();
			
	return check;
	}
	
	
	
	/**
	 * @author 2015软一钟越
	 *======================================
	 *      注册用户  （user）
	 *======================================
	 *

	  false	为	注册失败(若数据库中已有该用户则失败)
	  true	为	注册成功
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
		  
		  if(res>0){//执行SQL语句成功后 影响的条数大于0时，则注册成功，返回true;
			  change= true;
		  }
		     
		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	
	
	/**
	 * @author 2015软一钟越
	 *======================================
	 *      修改权限  （user,_pow）
	 *======================================
	 *

	  false	为	修改失败
	  true	为	修改成功
	 */
	public boolean SetPow(String l_id,String l_pow) throws ClassNotFoundException, SQLException{
		boolean change=false;
		  Connection conn=LoadDriver();
		 
		  String sql="update users_password set u_power=? where u_id=?;";
		   
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	  
		  
		  int res=prpstmt.executeUpdate();
		  
		  if(res>0){//执行SQL语句成功后 影响的条数大于0时，则注册成功，返回true;
			  change= true;
		  }
		     
		 
		  prpstmt.close();
		  conn.close();
			
	return change;
	}
	
	
	/**
	 * @author 2015软一钟越
	 *======================================
	 *      根据用户名获取用户的ID  （user）
	 *======================================
	 *
	  返回_int 用户的Id	  -1表示查询失败
	 */
	public int GetUserId(String l_user) throws ClassNotFoundException, SQLException{
		int id=-1;
		
		  Connection conn=LoadDriver();
		  
		  String sql="select u_id,u_username from users_password where u_username=?;";
  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
		  
		  prpstmt.setString(1, l_user);
		  
		  ResultSet res=prpstmt.executeQuery();
		  
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  id=res.getInt("u_id");
		  }
		     
		   res.close();		 
		   prpstmt.close();
		   conn.close();
			
	return id;//获取失败
	}
	

	/**
	 * @author 2015软一钟越
	 *======================================
	 *      根据ID获取用户的个性信息Info
	 *======================================
	 *
	  返回_int 用户的Id	  -1表示查询失败
	 */
	public String[] GetUser(int l_id) throws ClassNotFoundException, SQLException{
		String id[]=new String[4];
		
		  Connection conn=LoadDriver();
		  
		  String sql="select * FROM users_info where u_id=?;";
  
		  PreparedStatement prpstmt=conn.prepareStatement(sql);	 
	  
		  prpstmt.setInt(1, l_id);
		  
		  ResultSet res=prpstmt.executeQuery();
		  
		  if(res.next()){//如果在数据库中发现了这个用户，则返回true;
			  id[0]=res.getString("u_nickname");
			  id[1]=res.getString("u_sex");
			  id[2]=res.getString("u_ico");
			  id[3]=res.getString("u_sign");
		  }
		     
		   res.close();		 
		   prpstmt.close();
		   conn.close();
			
	return id;//获取失败
	}
	
	
	
	/**
	 * @author 2015软一钟越
	 *======================================
	 *      修改密码  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      修改密码  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      设置用户在线  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      设置用户下线  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      获取在线人数  （）
	 *======================================
	 *
	  返回int   
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
	 * @author 2015软一钟越
	 *======================================
	 *      查询用户是否在线  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 在线 
	  false表示 不在线
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
	 * @author 2015软一钟越
	 *======================================
	 *      查询用户权限 （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 在线 
	  false表示 不在线
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
	 * @author 2015软一钟越
	 *======================================
	 *      写日志  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 成功
	  false表示 失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      清空日志  （user）
	 *======================================
	 *
	  返回boolean   
	  true 表示 成功
	  false表示 失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      查看日志  （user）
	 *======================================
	 *
	  返回一个二维数组，其中 String 【N】【4】
	  
	  				    String	n	 0 为用户id
	  				    String	n	 1 为说明	
	  				    String	n	 2 为14位的时间戳
	  				    String	n	 3 为操作_ip
	  								 
	返回NULL表示无日志或获取失败
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
			  System.out.println("当前日志："+nmb);
			  if(nmb<1){//如果服务器没有日志，则直接返回空
				  return rest;
			  }
			  
		  }
		  res.close();
		  
		  
		  sql="select * from admin_log;";//如果有日志则读取所有
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
	 * @author 2015软一钟越
	 *======================================
	 *      查看留言数据  （user）
	 *======================================
	 *
	  返回一个二维数组，其中 String 【N】【3】
	  					String	n	 0 为数据的t_id
	  				    String	n	 1 为标题
	  				    String	n	 2 为内容	
	  				    String	n	 3 为14位时间戳
	  								 
	返回NULL表示无留言或获取失败
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
			  if(nmb<1){//如果服务器没有日志，则直接返回空
				  res.close();
				  prpstmt.close();
				  return rest;
			  }			  
		  }
		  res.close();
		  prpstmt.close();		   
		  
		  sql="select t_id,t_title,t_data,t_time from users_data where u_id=?  order by t_id;";//如果有日志则读取所有
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
	 * @author 2015软一钟越
	 *======================================
	 *      删除指定留言数据(t_id)
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      添加留言数据(用户id,标题,数据,时间戳)
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      修改日志  (t_id,t_title,t_data)
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
	 * @author 2015软一钟越
	 *======================================
	 *      修改用户的详细信息  updata
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
	 */
	public boolean ChangeUserSelfInfo(String t_id,String t_nickname,String u_sex,String u_ico,String u_sign) throws ClassNotFoundException, SQLException{		
		boolean change=false;
		
		  Connection conn=LoadDriver();
		  
		  String sql="select count(u_id) from users_info where u_id=?";
		  
		  PreparedStatement prpstmtIS=conn.prepareStatement(sql);
		  prpstmtIS.setInt(1, Integer.valueOf(t_id));
		  
          ResultSet resIS=prpstmtIS.executeQuery();        
          
          //判断如果存在用户 就更新数据 updata
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
	 * @author 2015软一钟越
	 *======================================
	 *      插入用户的详细信息  insert
	 *======================================
	 *
	  返回boolean   
	  true 表示 修改成功 
	  false表示 修改失败
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
