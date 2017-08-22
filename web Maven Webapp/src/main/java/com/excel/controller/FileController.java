package com.excel.controller;

import java.io.IOException;
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
	private Map<String, Map<String, String>> excelFileMap;
	@RequestMapping("/fileExcelPrase")
	public void resolveJsonObject(@RequestParam(value="name") String file) throws IOException { 
		
        System.out.println(file);
        //解析上线列表
        excelFileMap = excelFileService.excelFilePrase(file,"上线清单");
        //生成评审单 除去表头 第二行是第一个sheet 增加传参 行号-1
        String filePath = file.substring(0,file.lastIndexOf("\\")+1);
        filePath = filePath.replace("\\", "\\\\")+"456.xlsx";
        System.out.println(filePath);
        excelFileService.excelFileGenerate(filePath,excelFileMap);
    } 
}
