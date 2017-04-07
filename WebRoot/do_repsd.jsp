<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
if("null".equals(String.valueOf(session.getAttribute("loginid")))){
response.sendRedirect("login.jsp");
System.out.println("未登录不能使用本页 修改个人密码-do_repsd");
return;
}

 String id=String.valueOf(session.getAttribute("loginid")); 
 
 request.setCharacterEncoding("utf-8");
 
 String oldpsd=String.valueOf(request.getParameter("oldpsd"));
 String newpsd=String.valueOf(request.getParameter("newpsd"));
 String newpsd2=String.valueOf(request.getParameter("newpsd2"));
 
 if(("".equals(oldpsd) || "".equals(newpsd) || "".equals(newpsd2))){
 	response.setHeader("refresh","0;url=repassword.jsp");
 	out.print("<script type=\"text/javascript\">alert('密码不能为空');</script>");
 	return;
 }
 
  if(!newpsd.equals(newpsd2)){
 	response.setHeader("refresh","0;url=repassword.jsp");
 	out.print("<script type=\"text/javascript\">alert('新密码和重复密码不一致');</script>");
 	return;
 }
 
  SQL_User zy=new SQL_User();


String lname=(String)(session.getAttribute("loginname"));


 if(zy.Login(lname,oldpsd)<0){
  	response.setHeader("refresh","0;url=repassword.jsp");
 	out.print("<script type=\"text/javascript\">alert('旧密码错误,无法修改成新密码');</script>");
 	return;
 }
   
   if(zy.ChangePassword(id,newpsd)){
   		response.setHeader("refresh","0;url=repassword.jsp");
   		out.println("<script type=\"text/javascript\">alert('修改成功');</script>");
   return;
   }else{  
  		 response.setHeader("refresh","0;url=repassword.jsp");
   		out.println("<script type=\"text/javascript\">alert('修改失败');</script>");
   }
   
%>
