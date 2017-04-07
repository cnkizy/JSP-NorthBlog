<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
if("null".equals(String.valueOf(session.getAttribute("loginid")))){
response.sendRedirect("login.jsp");
System.out.println("未登录不能使用本页 修改个人资料-do_reinfo");
return;
}

String id=String.valueOf(session.getAttribute("loginid")); 
 
 request.setCharacterEncoding("utf-8");
 
 
 String nickname=String.valueOf(request.getParameter("nickname"));
 String radiosex=String.valueOf(request.getParameter("radiosex"));
 String head=String.valueOf(request.getParameter("head"));
 String sign=String.valueOf(request.getParameter("sign"));
 
 if(!("男".equals(radiosex) || "女".equals(radiosex))){//如果传值不是男 或女 则数据被篡改过。
 	response.setHeader("refresh","0;url=reinfo.jsp");
 	out.print("<script type=\"text/javascript\">alert('表单中的性别被篡改,无法继续修改');</script>");
 	return;
 }
 
 //为防止恶意写入html标签。如js脚本，这里加入过滤
 nickname=nickname.replaceAll("<", "&lt;");
 nickname=nickname.replaceAll(">", "&gt;");
 nickname=nickname.replaceAll("\"", "&quot;");
  
 
 radiosex=radiosex.replaceAll("<", "&lt;");
 radiosex=radiosex.replaceAll(">", "&gt;");
 
 head=head.replaceAll("<", "&lt;");
 head=head.replaceAll(">", "&gt;");
 
 sign=sign.replaceAll("<script", "&lt;script");
 sign=sign.replaceAll("<iframe", "&lt;iframe");
 sign=sign.replaceAll("<frame", "&lt;frame");
 sign=sign.replaceAll("<di", "&lt;di");
 sign=sign.replaceAll("</di", "&lt;/di");
 //sign=sign.replaceAll(" ", "&nbsp;");


   SQL_User zy=new SQL_User();
   

   
   if(zy.ChangeUserSelfInfo(id, nickname, radiosex, head, sign)){
   		response.setHeader("refresh","0;url=reinfo.jsp");
   		out.println("<script type=\"text/javascript\">alert('修改成功');</script>");
   return;
   }else{  
  		 response.setHeader("refresh","0;url=reinfo.jsp");
   		out.println("<script type=\"text/javascript\">alert('修改失败');</script>");
   }







%>
