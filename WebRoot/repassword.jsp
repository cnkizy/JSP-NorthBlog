<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改个人密码</title>

<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="style.css" media="screen" rel="stylesheet">



<!-- main JS libs -->
<script src="js/libs/modernizr.min.js"></script>
<script src="js/libs/jquery-1.10.0.js"></script>
<script src="js/libs/jquery-ui.min.js"></script>
<script src="js/libs/bootstrap.min.js"></script>


<!-- Style CSS -->
<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="style.css" media="screen" rel="stylesheet">
<link href="css/only_frame.css" media="screen" rel="stylesheet"><!-- 自己的统一风格化CSS -->


<!-- scripts -->
<script src="js/general.js"></script>


<script src="js/jquery.customInput.js"></script>

<script src="js/jquery.powerful-placeholder.min.js"></script>
<script src="js/jquery.mousewheel.js"></script>
<script src="js/jquery.jscrollpane.min.js"></script>
<!-- Include all needed stylesheets and scripts here -->

</head>

<body class="twoColElsLtHdr">

<div id="container">
  <div id="header">
<h1>北风个人博客</h1>
 </div>
 
 <% 
if("null".equals(String.valueOf(session.getAttribute("loginid")))){
response.sendRedirect("login.jsp");
System.out.println("未登录不能使用本页 修改个人密码");
return;
}

int id=Integer.valueOf(String.valueOf(session.getAttribute("loginid"))); 
 
int pow=0;
 
SQL_User zy=new SQL_User();

 %>
 
 
  <div id="sidebar1">
<%@ include file="static_MyInfo.jsp"%><!-- 个人信息 -->
</div>

<%
	int p=0;
	String[] myhead=zy.GetUser(id);

	%>


  <div id="mainContent">
	<h1>修改密码</h1>
    <form id="repssword" name="repsd" action="do_repsd.jsp" method="post">
<table width="100%" border="0">
  <tr>
    <td width="50" align="right" valign="middle">旧密码:</td>
    <td width="258">
      <input name="oldpsd" type="text" maxlength="15" style="width:200px;margin: -25px 0px 0px 10px" />    </td>
  </tr>
  <tr>
    <td width="50" align="right" valign="middle"><div style="margin:20px 0px 0px 0px">新密码:</div></td>
    <td><input name="newpsd" type="text" maxlength="15" style="width:200px;margin: -25px 0px 0px 10px"/>    </td>
    </td>
  </tr>
  <tr>
    <td width="50" height="100" align="right" valign="middle"><div style="margin:20px 0px 0px 0px">重复密码:</div></td>
    <td height="100" align="left" valign="middle">
    <input name="newpsd2" type="text" maxlength="15" style="width:200px;margin: -25px 0px 0px 10px" />    </td>   
	</td>
  </tr>
  <tr>
    <td width="50" height="50" align="right" valign="top"></td>
    <td height="50">
	</td>
  </tr>
  <tr>
    <td width="50" align="right" valign="middle">&nbsp;</td>
    <td><label>
      <span class="btn btn-pink"><input type="submit" name="Submit" value="确认修改" /></span>
<a href="index.jsp"  class="btn btn-brown"><span>返回主页</span></a>
    </label></td>
  </tr>
</table>

</form>
<br/>
  	</div>
	<br class="clearfloatmb" />
   <div id="footer">
	<%@ include file="foot.jsp"%><!-- 底部信息 -->
<!-- end #footer --></div>
<!-- end #container --></div>
</body>
</html>
