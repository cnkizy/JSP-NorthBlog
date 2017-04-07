<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'do_reg.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   

<%



request.setCharacterEncoding("utf-8");
String rcode=String.valueOf(session.getAttribute("rCode"));
String code=String.valueOf(request.getParameter("code"));
//session.getAttribute("rCode","");//清空session的验证码 防止恶意注册

//if(!rcode.equals(code) || "".equals(code)){
//response.setHeader("refresh", "0;url=reg.html");
//out.print("<script type=\"text/javascript\">alert('请输入正确的验证码');</script>");
//return;
//}


String name=String.valueOf(request.getParameter("name"));


if("".equals(name)){
response.setHeader("refresh", "0;url=reg.html");
out.print("<script type=\"text/javascript\">alert('姓名不能为空');</script>");
return;
}


String password=String.valueOf(request.getParameter("Password"));
String password2=String.valueOf(request.getParameter("Password2"));

if("".equals(password2) || "".equals(password)){
response.setHeader("refresh", "0;url=reg.html");
out.print("<script type=\"text/javascript\">alert('两次密码不能为空');</script>");
return;
}

if(!password.equals(password2)){
response.setHeader("refresh", "0;url=reg.html");
out.print("<script type=\"text/javascript\">alert('两次密码不一致,请重新输入密码');</script>");
return;
}



SQL_User zy =new SQL_User();

if(zy.CheckUsers(name)){
response.setHeader("refresh", "0;url=reg.html");
out.print("<script type=\"text/javascript\">alert('该用户已被注册');</script>");
return;
}






String sex=String.valueOf(request.getParameter("sex"));
String nickname=String.valueOf(request.getParameter("nickname"));
String head=String.valueOf(request.getParameter("head"));
String sign=String.valueOf(request.getParameter("sign"));



 //为防止恶意写入html标签。如js脚本，这里加入过滤
 nickname=nickname.replaceAll("<", "&lt;");
 nickname=nickname.replaceAll(">", "&gt;");
 nickname=nickname.replaceAll("\"", "&quot;");
  
 
 sex=sex.replaceAll("<", "&lt;");
 sex=sex.replaceAll(">", "&gt;");
 
 head=head.replaceAll("<", "&lt;");
 head=head.replaceAll(">", "&gt;");
 
 sign=sign.replaceAll("<script", "&lt;script");
 sign=sign.replaceAll("<iframe", "&lt;iframe");
 sign=sign.replaceAll("<frame", "&lt;frame");
 sign=sign.replaceAll("<di", "&lt;di");
 sign=sign.replaceAll("</di", "&lt;/di");

%>
<h1>注册详细信息</h1>
性别：<%=sex %><br/>
昵称：<%=nickname %><br/>
头像：<img src="img/user_icon/<%=head %>.jpg"><br/>
签名：<%=sign %><br/>
<% 

if(zy.RegUsers(name,password,"0")){//注册用户

	if(zy.InsertUserSelfInfo(String.valueOf(zy.GetUserId(name)),nickname,sex,head,sign)){//更新该用户详细信息

		response.setHeader("refresh", "0;url=login.jsp");
		out.print("<script type=\"text/javascript\">alert('恭喜，注册成功');</script>");

	}


}else{

response.setHeader("refresh", "0;url=reg.jsp");
out.print("<script type=\"text/javascript\">alert('数据库发生了一个错误,注册失败');</script>");

}

//out.println(password);
//out.println(password2);
//out.println(sex);
//out.println(name);

 %>


  </body>
</html>
