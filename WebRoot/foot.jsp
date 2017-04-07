<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%SQL_User foot=new SQL_User(); %>
<p>当前在线人数:<a href="online.jsp"><%=foot.GetlinePeople()%></a></p>