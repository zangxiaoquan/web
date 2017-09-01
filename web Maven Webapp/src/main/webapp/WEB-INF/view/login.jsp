<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.9.1.min.js"  type="text/javascript"></script>
<script>
</script>
<title>首页</title>
</head>
<body> 
    <h1 align="center">欢迎登陆</h1>
    <iframe id="iframe_display" name="iframe_display" style="display: none;"></iframe>
    
    <!-- target="iframe_display" 指向隐藏的iframe 浏览器中的浏览器，使用于formdata不支持的情况 -->
    <form id= "loginForm" align="center">
    	<tr>
    		<td>用户名：</td>
    		<td><input id="userId" name="userId"></input></td>
    	</tr>
    	<tr>
    		<td>密码：</td>
    		<td><input id="password" name="password" type="password"></input></td>
    	</tr>
    </form>
</body>
</html>