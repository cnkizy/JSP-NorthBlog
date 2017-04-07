<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%


SQL_User myinfo=new SQL_User();
String []myinfouser=myinfo.GetUser(id);
%>
<h3>个人资料</h3>
<img src="img/user_icon/<%=myinfouser[2]%>.jpg" width="80" height="80" style="margin:0px 0px 20px 20px"/>
<br/>
&nbsp;&nbsp;<span style="color:#FF3333">昵称</span>:<%out.print(myinfouser[0]);
if(zy.GetUserPow(String.valueOf(id))>0){%>
<span style="color:#FF0000">(管理员)</span>
<%}else{%>
<span style="color:#FF3333">(普通用户)</span>
<%}%>
<br/>
&nbsp;&nbsp;<span style="color:#FF3333">性别</span>:<%=myinfouser[1] %><br/>
&nbsp;&nbsp;<span style="color:#FF3333">个性签名</span>:<%=myinfouser[3] %>
<%if(pow>-1){ %>

<hr />
&nbsp;&nbsp;<a href="reinfo.jsp">修改个人资料</a><br/>
&nbsp;&nbsp;<a href="repassword.jsp">修改密码</a><br/>
&nbsp;&nbsp;<a href="insert.jsp">写文章</a><br/>

<%if(pow>0){ %>
&nbsp;&nbsp;<a href="">普通用户升级管理员</a><br/>
<%}%>
&nbsp;&nbsp;<a href="logout.jsp">注销</a><br/>
<%}else{%>
<br/>
<br/>
你当前是<span style="color:#FF3333">游客访问</span>,若要修改文章请先<a href="login.jsp">登录</a>
<br/>
<br/>
<%}%>