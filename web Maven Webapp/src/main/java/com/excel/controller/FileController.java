package com.excel.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileController {
	  @RequestMapping("fileUpload")
	  private String fileUpload() throws IOException{  
	    InputStream is = new FileInputStream( "C:\\Users\\zq\\Desktop\\123.xlsx");  
	    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);   
	      
	    // 循环工作表Sheet  
	    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
	      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
	      if(hssfSheet == null){  
	        continue;  
	      }  
	        
	      // 循环行Row   
	      for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){  
	        HSSFRow hssfRow = hssfSheet.getRow( rowNum);  
	        if(hssfRow == null){  
	          continue;  
	        }  
	          
	        // 循环列Cell    
	        for(int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++){  
	          HSSFCell hssfCell = hssfRow.getCell( cellNum);  
	          if(hssfCell == null){  
	            continue;  
	          }  
	            
	          System.out.print("    " + getValue( hssfCell));  
	        }  
	        System.out.println();  
	      }  
	    }
		return "success";  
	  }  
	    
	  @SuppressWarnings("static-access")  
	  private String getValue(HSSFCell hssfCell){  
	    if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
	      return String.valueOf( hssfCell.getBooleanCellValue());  
	    }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
	      return String.valueOf( hssfCell.getNumericCellValue());  
	    }else{  
	      return String.valueOf( hssfCell.getStringCellValue());  
	    }  
	  } 
}
