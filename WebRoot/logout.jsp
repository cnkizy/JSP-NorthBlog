<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
if(session.getAttribute("loginid") ==null){
	response.sendRedirect("login.jsp");
	return;
}else{
	session.invalidate();
	response.sendRedirect("login.jsp");
}


%>注销完成