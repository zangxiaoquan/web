package com.excel.controller;


import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excel.service.ExcelFileService;

@Controller
public class FileController {
	@Autowired
	private ExcelFileService excelFileService;
	
	@RequestMapping("/fileExcelPrase")
	@ResponseBody
	public String dealFile(@RequestParam(value="fileExcel2") String fileExcel) throws IOException{
		System.out.print(fileExcel);
		try {
			excelFileService.excelFilePrase(fileExcel,fileExcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return "success";
	}
	  
}
