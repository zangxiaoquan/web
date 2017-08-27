<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery-1.9.1.min.js"  type="text/javascript"></script>
<script src="js/jquery-3.51.0.form.js" type="text/javascript"></script>
<script>
$(function(){
              $("#fileSubmit").click(function(){
              var str = $("#fileText").val();
                  $.post("/web/fileExcelPrase4Jq",{filePath:str},function(data){
                      alert(data);
                  });
              });
          });
          
function uploadForm() { // jquery 表单提交 
$("#uploadForm").ajaxSubmit({
	beforeSubmit: function () {
     var t = $("#fileMedia");
     if (t.val() == "") {alert("文件为空,请选择上传文件");
     return false;}
     },
     url:'/web/fileUpload',
     datatype:'text',
	success:function(message) {
			// 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容 
			if($('#noticeMessage').is(':hidden')){ $('#noticeMessage').show();$('#noticeMessage').html(message);}
			},
	error:function(message){if($('#errorMessage_1').is(':hidden')){ $('#errorMessage_1').show();$('#errorMessage_2').show();}}
}); 
return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转 
}     
</script>
<title>首页</title>
</head>
<body> 
    <h1 align="center">上线管理</h1>
    <iframe id="iframe_display" name="iframe_display" style="display: none;"></iframe>
    <!-- 第一版简陋版使用，已淘汰应急用 -->
    <!--<table  align="center">
    	<tr>
    		<td><lable>生成评审单：</lable></td>
    		<td><input id="fileText" type="text"></input></td>
    		<td><input id="fileSubmit" type="button" value="生成"></input></td>
    	</tr>
    </table> -->
    
    
    <!-- target="iframe_display" 指向隐藏的iframe 浏览器中的浏览器，使用于formdata不支持的情况 -->
    <form id= "uploadForm" method="post" align="center"  enctype="multipart/form-data">
    	<tr>
    		<td><lable>生成评审单：</lable></td>
    		<td><input id="fileMedia" name="fileMedia" type="file" value="上传文件"></input></td>
    		<td><input type="button" value="提交" onclick="uploadForm()"></td>
    		<!-- <td><input id="fileSubmit" type="button" value="生成" align="center"></td> -->
    	</tr>
    </form>
    <p id="noticeMessage" align="center" style="display:none"></p>
    <p id="errorMessage_1" align="center" style="display:none">失败：可参考一下方式</p>
    	<ol id="errorMessage_2" style="display:none">
  			<li>检查上传文件</li>
  			<li>生成条数和列表条数不一致：删除上线列表尾部多余行，或重建Excel把列表中需要数据粘贴过去</li>
  			<li>明显代码报错异常或其他不可知异常，可联系我</li>
		</ol>
</body>
</html>