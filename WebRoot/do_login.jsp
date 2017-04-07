<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");

String name="";
String pass="";
String code="";
String rcode=String.valueOf(session.getAttribute("rCode"));
code=String.valueOf(request.getParameter("code"));

if(!rcode.equals(code) || "".equals(code)){
response.setHeader("refresh","0;url=login.jsp");
out.print("<script type=\"text/javascript\">alert('请输入正确的验证码');</script>");
return;
}

if(request.getParameter("username") !=null){
name=request.getParameter("username");
}
if(request.getParameter("password") !=null){
pass=request.getParameter("password");
}

//System.out.println(name);
//System.out.println(pass);
//System.out.println(code);
//System.out.println(rcode);

if( "".equals(name) || "".equals(pass)){
response.setHeader("refresh","0;url=login.jsp");
out.print("<script type=\"text/javascript\">alert('密码或用户名不能为空');</script>");
return;
}

   SQL_User zy=new SQL_User();
   int pow=zy.Login(name, pass);


if(pow != -1 ){//登录成功

int id=zy.GetUserId(name);//获取id

session.setAttribute("loginid",id);
session.setAttribute("pow",pow);
session.setAttribute("loginname",name);

response.sendRedirect("index.jsp?id="+id);

}else{//登录失败
response.setHeader("refresh","0;url=login.jsp");
out.print("<script type=\"text/javascript\">alert('密码或用户名有误,登录失败');</script>");
return;
}


 %>