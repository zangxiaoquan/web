package com.excel.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.excel.service.ExcelFileService;

@Controller
public class FileController {
	@Autowired
	private ExcelFileService excelFileService;
	
	@RequestMapping("/fileExcelPrase")
	public void resolveJsonObject(@RequestParam(value="name") String file) throws IOException { 
		
        System.out.println(file);
        //解析上线列表
        Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String, String>>();
        excelFileMap = excelFileService.excelFilePrase(file,"上线清单");
        //生成评审单 除去表头 第二行是第一个sheet 增加传参 行号-1
//        excelFileMap = excelFileService.excelFilePrase(file,"Sheet1");
        excelFileService.excelFileGenerate("C:\\Users\\zq\\Desktop\\456.xlsx",excelFileMap);
    } 
}
