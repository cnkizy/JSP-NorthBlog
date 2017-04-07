<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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


<script src="js/jquery.customInput.js"></script>

<script src="js/jquery.powerful-placeholder.min.js"></script>
<script src="js/jquery.mousewheel.js"></script>
<script src="js/jquery.jscrollpane.min.js"></script>
<!-- Include all needed stylesheets and scripts here -->





<!--[if lt IE 9]><script src="js/respond.min.js"></script><![endif]-->
<!--[if gte IE 9]>
<!--<style type="text/css">-->
   <!-- .gradient {filter: none !important;}-->
<!--</style>-->
<!--[endif]-->
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

.STYLE1 {

font-size: 18px;
color:#FFFFFF;
}
</style>

</head>

<body>

    <!-- container -->
    <div class="container"></div>
    <!--/ container -->
	

	
	

    
    <center>
<form id="reg" name="reg" method="post" action="do_reg.jsp">
  <table width="371" border="0" align="center" style="margin:20px 0px 0px 0px">
  <tr>
        <td width="100" align="right" valign="middle" class="STYLE1">用户名:</td>
        <td><div class="field_text">
    <input type="text" name="name" id="name" placeholder="Name"  maxlength="10" style="margin:10px 0px -30px 10px;width:190px;color:#FFFFFF;">
	</div>    </td>
      </tr>
      <tr>
        <td width="100" align="right" valign="middle" class="STYLE1">密码:</td>
        <td><div class="field_text">
    <input name="Password" type="text" class="STYLE1" id="Password"  maxlength="10" style="margin:20px 0px -30px 10px;width:190px;color:#FFFFFF;" placeholder="Password">
	</div></td>
      </tr>
      <tr>
        <td width="100" align="right" valign="middle" class="STYLE1">确认密码:</td>
        <td><div class="field_text">
        <input name="Password2" type="text" class="STYLE1" id="Password2" maxlength="10" style="margin:20px 0px -30px 10px;color:#FFFFFF;width:190px" placeholder="Password">
        </div></td></tr>
      <tr>
        <td width="100" align="right" valign="top" class="STYLE1">性别:</td>
        <td align="left" valign="top">

	<div class="input_styled inlinelist" style="margin:20px 0px 0px 10px;">
    <div class="rowRadio" style="width:80px;float:left;margin:0px 0px -15px 0px;">
        <input type="radio" name="sex" value="男" id="radio_v1" checked>
        <label for="radio_v1">男</label>
    </div>
    <div class="rowRadio radio-red" style="width:80px;float:left;margin:0px 0px -15px 0px;">
        <input type="radio" name="sex" value="女" id="radio_v2">
        <label for="radio_v2">女</label>
    </div>
</div>		</td>
      </tr>
      <tr>
        <td width="100" align="right" valign="middle" class="STYLE1">昵称:</td>
        <td>
		<span class="field_text" style="margin:20px auto auto auto">
        <input name="nickname" type="text" class="STYLE1" id="nickname" maxlength="10" style="margin:-10px 0px -30px 10px;color:#FFFFFF;width:190px" placeholder="Nickname">
        </span>		</td>
      </tr>
      <tr>
        <td width="100" height="40" align="right" valign="middle" class="STYLE1">
		<br/>
		<br/>
		头像：</td>
        <td>
		<br class="clear"/>
		<div class="input_styled inlinelist" style="margin:20px 0px 10px 10px">
		
		<%	
		for(int i=0;i<5;i++){
		 %>		
		
    <div class="rowRadio" style="margin:10px 0px 10px 0px;float:left" >
        <input type="radio" name="head" value="<%=i %>" id="head<%=i %>" <%=i==0?"checked":""%>>	
		<label for="head<%=i %>"> <img src="img/user_icon/<%=i %>.jpg"  width="50" height="50" style="margin:-10px 10px "></label>
    </div>
	
	<%}%>
	 </div>		

</td>
      </tr>
      <tr>
        <td width="100" align="right" valign="top" class="STYLE1" >
        </td>
        <td><div class="field_text field_textarea" style="margin:20px 0px -30px 10px">
          <textarea name="sign" placeholder="个性签名" ></textarea>
        </div></td>
      </tr>
      <tr>
        <td width="100" align="right" valign="top" class="STYLE1">验证码:</td>
        <td align="left" valign="top">
		
		<span class="field_text" >
        <input name="code" type="text" class="STYLE1" id="Code" style="margin:45px 0px -10px 10px;width:70px;color:#FFFFFF;" size="4" maxlength="4" >
        </span>
		<img src="RandCode.jsp" style="margin:45px 0px -10px -20px" width="70" height="43"/></td>
      </tr>
      <tr>
        <td width="100" align="right" valign="middle" class="STYLE1">&nbsp;</td>
        <td>	
		
		<span class="btn" style="margin: 0px auto auto auto;width:220px">
	  <input name="Submit" type="submit" style="width:200px;margin: 10px 0px 0px 0px;" value="注册">
	  </span>			</td>
      </tr>
      <tr>
        <td width="100" align="right" valign="middle" class="STYLE1">&nbsp;</td>
        <td>
		<a href="login.jsp" class="btn btn-pink" style="width:200px;margin:10px 0px 0px 0px;"><span>返回登陆页</span></a>		</td>
      </tr>
    </table>
</form>
    </center>

      	<div id="div1"><img src="img/login/reg.png" width="0" height="0" /></div>
</body>
</html>