package com.excel.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.excel.service.ExcelFileService;

@Controller
public class FileController {
	@Autowired
	private ExcelFileService excelFileService;
	private List<Map<String, String>> excelFileList;
	private String destFilePath = "C:\\Users\\zq\\Desktop\\";//生成评审单路径
	
	@RequestMapping("/fileExcelPrase4Jq")
	@ResponseBody
	public String fileExcelPrase4Jq(HttpServletRequest request,@RequestParam(value="fileName") String fileName) throws IOException { 
		try{
			//获取上线列表文件在服务器位置
			String excelFilePath = request.getSession().getServletContext().getRealPath("/") + "fileUpload\\temp\\" + fileName;
	        //解析上线列表
	        excelFileList = excelFileService.excelFilePrase(excelFilePath,"上线清单");
	        //获取评审单模版路径
	        String orgFilePath = request.getSession().getServletContext().getRealPath("/")+"template\\lnyd_check4allplat.xlsx";
	        //从模版路径复制一个到桌面并使用其生成评审单
	        FileUtils.copyFileToDirectory(new File(orgFilePath), new File(destFilePath));
	        //生成评审单
	        String feedBack = excelFileService.excelFileGenerate(destFilePath+"lnyd_check4allplat.xlsx",excelFileList);
	        //增加返回值
	        return "成功！"+feedBack;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "失败！后台报错";
    } 
	
	@RequestMapping("/fileUpload")
	 public String resolveFileUpload(HttpServletRequest request,@RequestParam(value="fileMedia") MultipartFile file) throws IOException, Exception { 
	  String filePath =  null;
	  // 判断文件是否为空  
	        if (!file.isEmpty()) {  
	            try {  
	                // 文件保存路径  
	                filePath = request.getSession().getServletContext().getRealPath("/") + "fileUpload\\temp\\"  
	                        + file.getOriginalFilename();  
	                // 转存文件  
	                file.transferTo(new File(filePath));  
	            } catch (Exception e) {  
	                e.printStackTrace();
	            }  
	        }
	        //跳转
	        return "forward:/fileExcelPrase4Jq?fileName="+file.getOriginalFilename();
	 }
}
