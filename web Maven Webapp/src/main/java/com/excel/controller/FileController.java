package com.excel.controller;


import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.excel.bean.FileBean;
import com.excel.service.ExcelFileService;

@Controller
public class FileController {
//	@Autowired
//	private ExcelFileService excelFileService;
	
	@RequestMapping("/fileExcelPrase")
	public void resolveJsonObject(String file) throws IOException {  
        System.out.println(file);  
    } 
}
