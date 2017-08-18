<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.9.1.min.js"></script>
<script>
$(function(){
              $("#fileSubmit").click(function(){
                  $.post("/web/fileExcelPrase",{name:$("#fileText").val()},function(data){
                      alert(data);
                  });
              });
          });
</script>
<title>首页</title>
</head>
<body> 
    <h1 align="center">中国移动-上线管理系统</h1>
    
    <!--<form name="fileForm" action="/web/fileExcelPrase" method="post">-->
    <!--  enctype="multipart/form-data"> -->
    <table  align="center">
    	<tr>
    		<td><lable>生成评审单：</lable></td>
    		<td><input id="fileText" type="text"></input></td>
    		<!--<td><input id="fileExcel1" name="fileExcel2" type="file" value="上传"></input></td> -->
    		<td><input id="fileSubmit" type="button" value="生成"></input></td>
    	</tr>
    	<!--<tr>
    		<td><input id="fileSubmit" type="submit"></input></td>
    	</tr>-->
    </table>
    <!--</form>-->
</body>
</html>