<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");


if(session.getAttribute("loginid")==null){
response.sendRedirect("login.jsp");
return;
}

int loginid=Integer.valueOf(String.valueOf(session.getAttribute("loginid"))); 

String id=String.valueOf(request.getParameter("id"));
String title=String.valueOf(request.getParameter("title"));
String data=String.valueOf(request.getParameter("data"));

 //为防止恶意写入html标签。如js脚本，这里加入过滤
 id=id.replaceAll("<", "&lt;");
 id=id.replaceAll(">", "&gt;");
 
 
 //人性化过滤html标签
 title=title.replaceAll("<script", "&lt;script");
  title=title.replaceAll("<iframe", "&lt;iframe");
  title=title.replaceAll("<frame", "&lt;frame");
  
 data=data.replaceAll("<iframe", "&lt;iframe");
 data=data.replaceAll("<frame", "&lt;frame");
 data=data.replaceAll("<script", "&lt;script");
 data=data.replaceAll("</script", "&lt;/script");

SQL_User zy=new SQL_User();

if(zy.ChangeUserData(id, title, data)){
response.setHeader("refresh","0;url=index.jsp?id="+loginid);
return;
}else{
response.setHeader("refresh","0;url=index.jsp?id="+loginid);
out.print("<script type=\"text/javascript\">alert('修改失败');</script>");
}



%>