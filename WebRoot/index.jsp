<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link href="css/bootstrap.css" media="screen" rel="stylesheet"><!-- 必须要的CSS -->
<link href="style.css" media="screen" rel="stylesheet"><!-- 模板CSS -->
<link href="css/only_frame.css" media="screen" rel="stylesheet"><!-- 自己的统一风格化CSS -->
	
</head>
  
  <body class="twoColElsLtHdr">
  <%
   
   String strid=String.valueOf(request.getParameter("id"));
 
     
if(!strid.matches("[0-9]+")){
 	response.sendRedirect("login.jsp");
 	System.out.println("id"+strid);
 	System.out.println("传值非正常id");
 return;
}
   
 if("".equals(strid) || "null".equals(strid)){//如果没有该id 或者 id没有将值传过来时 则转到登陆页
 	response.sendRedirect("login.jsp");
	return;
 }
  
  
    SQL_User zy=new SQL_User();

  
 if(!zy.CheckUsersid(Integer.valueOf(strid))){//如果没有该id 或者 id没有将值传过来时 则转到登陆页
 	response.sendRedirect("login.jsp");
 	System.out.println("无此用户");
 return;
 }

 int pow=-1;
 int loginid=-1;
 int id=Integer.valueOf(strid);
 
 if(session.getAttribute("pow") != null || session.getAttribute("loginid") !=null){//当前是通过登录，登录进入的页面
 pow=Integer.valueOf(String.valueOf(session.getAttribute("pow"))); 
 loginid=Integer.valueOf(String.valueOf(session.getAttribute("loginid")));
 }else{//当前没有登录过，只是游客浏览进入页面
 loginid=id;
 pow=-1;
 }
 
 

 
if(loginid != id){//已经登录后，如果访问的不是自己的空间，那么权限为游客
pow=-1;

}
 
String []usertt=zy.GetUser(id);

  %>
  
<div id="container">

  	<div id="header">
		<h1>北风个人博客</h1><!-- 网站标题 -->
	</div>
 
 	<div id="sidebar1">
		<%@ include file="static_MyInfo.jsp"%><!-- 个人信息 -->
	</div>

  <div id="mainContent"><!-- 数据信息 -->
  
	<br/><!-- 横幅信息 -->
	  <h1><img src="img/008ah.jpg" width="100%" height="80" /></h1>
	<br/>
<% 
String [][]data=zy.GetUserData(String.valueOf(id));
if(data==null){
out.println("暂无数据");

}else{


for(int i=0;i<data.length;i++){
//查看留言数据[标题,内容,时间戳]

%>
	
 <div class="comment-list styled clearfix">
    <ol>
        <li class="comment">
            <div class="comment-body boxed">
                <div class="comment-arrow"></div>
                <div class="comment-avatar">
                    <div class="avatar">
                        <img src="<%=basePath%>img/user_icon/<%=usertt[2]%>.jpg" width="80" height="80" alt="" />
                    </div>
                </div>
                <div class="comment-text">
                <%//if(i>0){ %>
                   <div class="comment-author clearfix">
                 <%//}%>
                        <a href="#" class="link-author"><%=data[i][1]%></a><!-- 这里是文章的标题 -->
                <%//if(i>0){ %>
                    </div>
                <%//}%>
                    <div class="comment-entry">
                      <% out.print(data[i][2]);
                     if(pow != -1){%>
                     <hr><p><span style="color:FF3333"><a href="updata.jsp?d=<%=data[i][0]%>">修改文章</a></span>&nbsp;&nbsp;<span style="color:FF3333"><a href="delete.jsp?d=<%=data[i][0]%>">删除文章</a></span></p>
					<%} %>
					</div>
                </div>
                <div class="clear"></div>
            </div>
        </li>
    </ol>
</div>
    
    <% }
    
    }%>

  </div>
	<br class="clearfloatmb" />
   <div id="footer">
	<%@ include file="foot.jsp"%><!-- 底部信息 -->
<!-- end #footer --></div>
<!-- end #container --></div>
</body>
</html>
