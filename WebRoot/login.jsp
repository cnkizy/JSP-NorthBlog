<%@ page language="java" import="java.util.*,zy.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

if(session.getAttribute("loginid") !=null){//已经登陆过就自动登录
response.sendRedirect("index.jsp?id="+String.valueOf(session.getAttribute("loginid")));
return;
}


/**SQL_User zy=new SQL_User();



//System.out.println(zy.ChangeUserData("6","newtitle","newdata/r/na\r\nsdasd<br/>ast\tddas"));


String[][] log=zy.GetUserData("1");


if(log==null){
return;
}else{

for(int i=0;i<log.length;i++){
	for(int y=0;y<4;y++){

	System.out.print(log[i][y]+" ");
}
System.out.println("");

}
} 
**/
//System.out.println(request.getRemoteAddr());



%>
<!doctype html>
<!--[if lt IE 7 ]><html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]><html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]><html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]><html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html lang="en" class="no-js"> <!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title></title>

<!-- main JS libs -->
<script src="js/libs/modernizr.min.js"></script>
<script src="js/libs/jquery-1.10.0.js"></script>
<script src="js/libs/jquery-ui.min.js"></script>
<script src="js/libs/bootstrap.min.js"></script>

<!-- Style CSS -->
<link href="css/bootstrap.css" media="screen" rel="stylesheet">
<link href="style.css" media="screen" rel="stylesheet">

<!-- scripts -->
<script src="js/general.js"></script>

<!-- Include all needed stylesheets and scripts here -->





<!--[if lt IE 9]><script src="js/respond.min.js"></script><![endif]-->
<!--[if gte IE 9]>
<style type="text/css">
    .gradient {filter: none !important;}
</style>
<![endif]-->





<style type="text/css">
div#div1{ 
position:fixed; 
top:0; 
left:0; 
bottom:0; 
right:0; 
z-index:-1; 
} 
div#div1 > img { 
height:100%; 
width:100%; 
border:0; 
} 

.bcc{

 margin-left: auto; margin-right: auto;
 margin:150px 20px auto auto;
 
 width:371px;
 height:461px;

background:url(img/login/boder.png);


}

.tabel_touming{
filter:alpha(opacity=60); 
-moz-opacity:0.6; 
opacity:0.6;

border-style:solid;
border-color:#af9f9f;

}

.ico{
margin:20px 10px 30px 0px;

}


.imgtitle{
margin:40px -20px 0px 0px;

}

</style>





<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></head>

<body>

    <!-- container -->
    <!--<div class="container">

        Start your work here.

    <!--</div>-->
    <!--/ container -->
	<div class="bcc">

<form id="form1" name="form1" method="post" action="do_login.jsp">

<table width="371" height="461" border="0">
  <tr>
    <td height="60" colspan="2" align="center"><img src="img/login/login.png" width="220" height="79" class="imgtitle"></td>
    </tr>
  <tr>
    <td width="100" height="20" align="right" valign="top"><img src="img/login/user.png" width="21" height="22" class="ico"></td>
    <td width="271" height="20" valign="top">
		<div class="field_text" style="width:200px;">
          <input class="tabel_touming" style="color:#F1692D; font-size:30px;background-color:#cbcbcb;" type="text" name="username"  maxlength="10"/>
        </div>	</td>
  </tr>
  <tr>
    <td width="100" height="20" align="right" valign="top"><img src="img/login/pass.png" width="23" height="24" class="ico"></td>
    <td width="271" height="20" valign="top">
	<div class="field_text" style="width:200px">
	  <input class="tabel_touming" style="color:#F1692D; font-size:30px;background-color:#cbcbcb;" type="password" name="password" />
	</div>	</td>
  </tr>
  <tr>
    <td width="100" height="20">&nbsp;</td>
    <td width="271" height="20" valign="top">
	<div class="field_text" style="width:110px">
		<input class="tabel_touming" style="color:#F1692D; font-size:30px;background-color:#cbcbcb; " name="code" type="text" size="4" maxlength="4"/>
		</div>
		<a href="#"><img src="RandCode.jsp" width="80" height="43" id="imgcode" onclick="reloadcode()"/></a>

		</td>
  </tr>
  <tr>
   <td width="100" height="20">&nbsp;</td>
    <td height="25" colspan="2" align="left">
	
	  <span class="btn btn-blue" style="margin: -20px auto auto auto;width:220px">
	  <input name="Submit" type="submit" style="width:200px;margin: 10px 0px 0px 0px;" value="登录">
	  </span>	
	  <a href="reg.jsp" class="btn btn-small" style="width:200px;margin:10px 0px 0px 0px;"><span>注册</span></a></td>
    </tr>
  <tr>
    <td width="100" height="24">&nbsp;</td>
    <td width="271" height="24" align="right" valign="top">  </td>
  </tr>
</table>




</form>

</div>
	
	
	<div id="div1"><img src="img/login/bg.jpg" width="640" height="425" /></div>

		
	<script language="JavaScript">
function reloadcode(){
var verify=document.getElementById('imgcode');
verify.setAttribute('src','RandCode.jsp?'+Math.random());
//这里必须加入随机数不然地址相同我发重新加载
}
</script>
</body>
</html>