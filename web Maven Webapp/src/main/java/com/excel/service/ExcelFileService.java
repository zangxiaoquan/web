package com.excel.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
@Service
public class ExcelFileService {
	
	public boolean excelFilePrase(String filePath,String fileName) throws IOException{  
		InputStream is = new FileInputStream( filePath+fileName); 
		fileName=fileName.toLowerCase();
		if(fileName.indexOf("xlsx")>0){
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		    // 循环工作表Sheet  
		    for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){  
		      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt( numSheet);  
		      if(xssfSheet == null||xssfSheet.getSheetName()!="上线清单"){  
		        continue;  
		      }
		        
		      // 循环行Row   
		      for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++){  
		        XSSFRow xssfRow = xssfSheet.getRow( rowNum);  
		        if(xssfRow == null){  
		          continue;  
		        }  
		          
		        // 循环列Cell    
		        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
		          XSSFCell hssfCell = xssfRow.getCell( cellNum);  
		          if(hssfCell == null){  
		            continue;  
		          }  
		            
		          System.out.print("    " + getValue( hssfCell));  
		        }  
		        System.out.println();  
		      }  
		    }
	    }
	    else{
		    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		    // 循环工作表Sheet  
		    for(int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++){  
		      HSSFSheet hssfSheet = hssfWorkbook.getSheetAt( numSheet);  
		      if(hssfSheet == null||hssfSheet.getSheetName()!="上线清单"){  
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
	    }
		
		return true;  
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
	  @SuppressWarnings("static-access")  
	  private String getValue(XSSFCell xssfCell){  
	    if(xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN){  
	      return String.valueOf( xssfCell.getBooleanCellValue());  
	    }else if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){  
	      return String.valueOf( xssfCell.getNumericCellValue());  
	    }else{  
	      return String.valueOf( xssfCell.getStringCellValue());  
	    }  
	  } 
}
