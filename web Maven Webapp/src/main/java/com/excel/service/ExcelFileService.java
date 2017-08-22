package com.excel.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.springframework.stereotype.Service;

@Service
public class ExcelFileService {
	Map<String, String> rowmap ;
	Map<String, Map<String, String>> sheetmap = new HashMap<String, Map<String, String>>();
	InputStream is =  null;
	FileOutputStream out = null;
	
	public Map<String, Map<String, String>> excelFilePrase(String file,String dealSheetName) throws IOException{  
		is = new FileInputStream(file); 
		file=file.toLowerCase();
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		    // 循环工作表Sheet  
		    for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){  
		      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt( numSheet);  
		      if(xssfSheet == null||!xssfSheet.getSheetName().equals(dealSheetName)){  
		        continue;  
		      }
		      // 循环行Row   
		      for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++){  
		        XSSFRow xssfRow = xssfSheet.getRow( rowNum);  
		        if(xssfRow == null){  
		          continue;  
		        } 
		        rowmap = new HashMap<String, String>(); 
		        // 循环列Cell    
		        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
		          XSSFCell xssfCell = xssfRow.getCell(cellNum);  
		          if(xssfCell == null){  
		            continue;  
		          } 
		          rowmap.put(String.valueOf(xssfCell.getColumnIndex()),xssfCell.toString()==null?"":xssfCell.toString());
		        } 
		        sheetmap.put(String.valueOf(rowNum) , rowmap);
		      }  
		    }
		    System.out.print(sheetmap.get("46"));
		is.close();
		return sheetmap;  
	  }  
	  
	  
	public boolean excelFileGenerate(String file,Map<String, Map<String, String>> excelFileMap) throws IOException{  
			is = new FileInputStream(file); 
			file=file.toLowerCase();
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			    // 循环工作表Sheet  
			    for(int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++){  
			      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt( numSheet);
			      //设置打印横向
			      XSSFPrintSetup xssprint = xssfSheet.getPrintSetup();
			      xssprint.setLandscape(true);
			      //设置A4纸
			      xssprint.setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);

			      if(excelFileMap.get(String.valueOf(numSheet+2))!=null && !excelFileMap.get(String.valueOf(numSheet+2)).equals("")){
			    	  xssfWorkbook.cloneSheet(numSheet);
				      xssfWorkbook.setSheetName(numSheet+1,"Sheet"+String.valueOf(numSheet+2));
			      }
			      
			      if(xssfSheet == null||!xssfSheet.getSheetName().equals("Sheet"+String.valueOf(numSheet+1))){  
			        continue;  
			      }
			      // 循环行Row   
			      for(int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++){  
			        XSSFRow xssfRow = xssfSheet.getRow(rowNum);  
			        if(xssfRow == null||xssfRow.getRowNum()==0 || xssfRow.getRowNum()==2){  
			          continue;  
			        } 
			        	
			        // 循环列Cell    
			        for(int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++){  
			          XSSFCell xssfCell = xssfRow.getCell(cellNum);  
			          if(xssfCell == null){  
			            continue;  
			          } 
			          if((rowNum==3 || rowNum==4 || rowNum==5 || rowNum==6 || rowNum==7 || rowNum==8 || 
			        		  rowNum==9 || rowNum==10 || rowNum==11 || rowNum==12 || rowNum==14 ) && cellNum==3){
			        	  xssfCell.setCellValue("");
			        	  }
			          //需求名称
			          if(rowNum==1 && cellNum==0){
			        	  String Str = excelFileMap.get(String.valueOf(numSheet+1)).get("4");
			        	  xssfCell.setCellValue("需求名称："+(Str==null?"":Str));
			        	  continue;
				        }
			          //需求id
			          if(rowNum==1 && cellNum==2){
			        	  String Str = excelFileMap.get(String.valueOf(numSheet+1)).get("1");
			        	  xssfCell.setCellValue("需求ID："+(Str==null?"":Str));
			        	  continue;
				        }
			          //需求负责人
			          if((rowNum==3 || rowNum==10 || rowNum==11 || rowNum==12) && cellNum==3){
			        	  String finalStr = getCellValue(xssfCell,excelFileMap,numSheet);
			        	  xssfCell.setCellValue(finalStr);
				        }
			          //开发
			          if((rowNum==4 || rowNum==5 || rowNum==6 || rowNum==9) && cellNum==3){
			        	  String finalStr = getCellValue(xssfCell,excelFileMap,numSheet);
			        	  xssfCell.setCellValue(finalStr);
				        }
			          //测试
			          if((rowNum==6 || rowNum==7 || rowNum==8 || rowNum==9) && cellNum==3){
			        	  String finalStr = getCellValue(xssfCell,excelFileMap,numSheet);
			        	  xssfCell.setCellValue(finalStr);
				        }
			          //局方需求负责人
			          if((rowNum==12 || rowNum==13 || rowNum==14) && cellNum==3){
			        	  String finalStr = getCellValue(xssfCell,excelFileMap,numSheet);
			        	  xssfCell.setCellValue(finalStr);
				        }
			        } 
			      } 
			    }
			    out = new FileOutputStream(file);
			    xssfWorkbook.write(out);
                out.close();
                is.close();
                return true;
	}
	private String getCellValue(XSSFCell xssfCell,Map<String, Map<String, String>> excelFileMap,int numSheet){
	  String tmpStr = xssfCell.toString();
  	  String Str = excelFileMap.get(String.valueOf(numSheet+1)).get("9");
  	  String finalStr = "";
  	  if(tmpStr != null && !tmpStr.equals("")  && Str != null && !Str.equals("")){
  		  finalStr = tmpStr+"、"+Str;
  	  }
  	  else{
  		  finalStr = (tmpStr==null?"":tmpStr)+(Str==null?"":Str);
  	  }
  	  return finalStr;
	}
//	  @SuppressWarnings("static-access")  
//	  private String getValue(HSSFCell hssfCell){  
//	    if(hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN){  
//	      return String.valueOf( hssfCell.getBooleanCellValue()).trim();  
//	    }else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC){  
//	      return String.valueOf( hssfCell.getNumericCellValue()).trim();  
//	    }else{  
//	      return String.valueOf( hssfCell.getStringCellValue()).trim();  
//	    }  
//	  } 
//	  @SuppressWarnings("static-access")  
//	  private String getValue(XSSFCell xssfCell){  
//	    if(xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN){  
//	      return String.valueOf(xssfCell.getBooleanCellValue()).trim();  
//	    }else if(xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC){  
//	      return String.valueOf( xssfCell.getNumericCellValue()).trim();  
//	    }else{  
//	      return String.valueOf( xssfCell.getStringCellValue()).trim();  
//	    }  
//	  } 
}
