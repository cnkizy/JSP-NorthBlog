<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%="*"+basePath+"*" %>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>

	<link rel="stylesheet" type="text/css" href="js/eazy_ui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="js/eazy_ui/themes/icon.css">
	<script type="text/javascript" src="js/eazy_ui/jquery.min.js"></script>
	<script type="text/javascript" src="js/eazy_ui/jquery.easyui.min.js"></script>

</head>

<body>
<div class="easyui-panel" title="用户登录" style="width:400px">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" method="post">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>账户:</td>
	    			<td><input class="easyui-validatebox textbox" type="text" name="name" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input class="easyui-validatebox textbox" type="text" name="email" ></input></td>
	    		</tr>
	    		<tr>
	    			<td>验证码:</td>
	    			<td><input name="message" class="textbox" size="4" maxlength="4" /><img src="RandCode.jsp"/></td>
	    		</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
	    </div>
	    </div>
	</div>
	<style scoped="scoped">
		.textbox{
			height:20px;
			margin:0;
			padding:0 2px;
			box-sizing:content-box;
		}
	</style>
	<script>
		function submitForm(){
			$('#ff').form('submit');
		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>

</body>
</html>
