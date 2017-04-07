<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
String dataid=String.valueOf(request.getParameter("d"));

//若id没有值 或者用户未登陆，均不允许访问此页面
if("".equals(dataid) || "null".equals(dataid) || session.getAttribute("loginid") ==null){
	response.sendRedirect("login.jsp");
	return;
}

SQL_User zy=new SQL_User();

if(zy.DelUserData(dataid)){
	response.sendRedirect("index.jsp");
	return;
//
}else{
	response.setHeader("refresh", "0;url=index.jsp");
	out.print("<script type=\"text/javascript\">alert('删除失败');</script>");

}

 %>


