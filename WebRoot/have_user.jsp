<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    <%
    
    String chkname=String.valueOf(request.getParameter("checkname"));
    
    if(!"".equals(chkname)){
    SQL_User zy=new SQL_User();
    
    if(zy.CheckUsers(chkname)){
    out.println("<script type=\"text/javascript\">alert('已存在该用户');</script>");
    response.setHeader("refresh","0;url=reg.html");
    }else{
    out.println("<script type=\"text/javascript\">alert('可以注册用户');</script>");
    response.setHeader("refresh","0;url=reg.html");
    }
    
    }else{
    out.println("<script type=\"text/javascript\">alert('用户名不能为空');</script>");
    response.setHeader("refresh","0;url=reg.html");
    }

     %>