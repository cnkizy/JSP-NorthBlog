<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

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
<%
String dataid=String.valueOf(request.getParameter("d"));

//若id没有值 或者用户未登陆，均不允许访问此页面
if("".equals(dataid) || "null".equals(dataid) || session.getAttribute("loginid") ==null){
	response.sendRedirect("login.jsp");
	return;
}


int pow=Integer.valueOf(String.valueOf(session.getAttribute("pow"))); 
int id=Integer.valueOf(String.valueOf(session.getAttribute("loginid")));

SQL_User zy=new SQL_User();

 %>



<div id="container">
  <div id="header">
<h1>北风个人博客</h1>
 </div>
 
  <div id="sidebar1">
  
<%@ include file="static_MyInfo.jsp"%>

</div>

<%
//SQL_User zy2=new SQL_User();

String [][]data=zy.GetUserData(String.valueOf(id));

if(data==null){
out.println("暂无数据");%>
<a href="index.jsp?id=<%=id%>"  class="btn btn-brown"><span>返回主页</span></a>
<%}else{

for(int i=0;i<data.length;i++){
if(!data[i][0].equals(dataid)){
continue;
}
 %>


  <div id="mainContent">
  <form id="form1" name="form1" method="post" action="do_updata.jsp">
	<h1>修改文章</h1>
	<input name="id" type="hidden" value="<%=data[i][0]%>"/>
	  <label>
	    <input name="title" type="text" maxlength="20" value="<%=data[i][1]%>"/>
      </label>
 
	<p>
	<textarea name="data" rows="15"><%=data[i][2]%></textarea>
	<br/><span>发表时间:<%=data[i][3]%></span>
	<%}%>
	</p>
	<hr><p>
	
	  <span class="btn btn-pink"><input type="submit" name="Submit" value="确认修改" /></span>
	 
	  &nbsp;&nbsp;<a href="index.jsp?id=<%=id%>"  class="btn btn-brown"><span>返回主页</span></a>
    </form>
    <%} %>
  </div>
  
    <br class="clearfloatmb" />
   <div id="footer">
   
<p>当前在线人数:<a href="">0</a></p>

<!-- end #footer --></div>
<!-- end #container --></div>
</body>
</html>
