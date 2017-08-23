package com.excel.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import com.excel.util.stringUtil;

@Service
public class ExcelFileService {
	Map<String, String> rowmap ;
	List<Map<String, String>> sheetList = new ArrayList<Map<String,String>>();
	InputStream is =  null;
	FileOutputStream out = null;
	
	public List<Map<String, String>> excelFilePrase(String file,String dealSheetName) throws IOException{  
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
		        sheetList.add(rowmap);
		      }  
		    }
		    System.out.print(sheetList.size());
		is.close();
		return sheetList;  
	  }  
	  
	  
	public boolean excelFileGenerate(String file,List<Map<String, String>> excelFileList) throws IOException{  
			//
			file=file.toLowerCase();
			is = new FileInputStream(file); 
			XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
			
			Iterator<Map<String, String>> iterator = excelFileList.iterator();
			int i = 0;
			while(iterator.hasNext()){
				//跳过第一行表头
				if(i==0){
					iterator.next();
					i=++i;
					continue;
				}
				XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i-1);
				//设置打印横向
			    XSSFPrintSetup xssprint = xssfSheet.getPrintSetup();
			    xssprint.setLandscape(true);
			    //设置A4纸
			    xssprint.setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
				//如果还有就继续clone
				xssfWorkbook.cloneSheet(i-1);
			    xssfWorkbook.setSheetName(i,"Sheet"+String.valueOf(i+1));
			    
				Map<String, String> tmpMap = iterator.next();
				//需求名称
				String demandName = tmpMap.get("4");
				xssfSheet.getRow(1).getCell(0).setCellValue("需求名称："+(demandName==null?"":demandName));
				//需求id
				String demandId = tmpMap.get("1");
				xssfSheet.getRow(1).getCell(2).setCellValue("需求ID："+(demandId==null?"":demandId));
				//需求负责人
				String demandSonname = stringUtil.isBlank(tmpMap.get("9"))?"":tmpMap.get("9");
				//开发
				String developName = stringUtil.isBlank(tmpMap.get("10"))?"":tmpMap.get("10");
				//测试
				String testName = stringUtil.isBlank(tmpMap.get("11"))?"":tmpMap.get("11");
				//局方需求负责人
				String demandGodname = stringUtil.isBlank(tmpMap.get("7"))?"":tmpMap.get("7");
				
				//设置第三列
				xssfSheet.getRow(3).getCell(3).setCellValue(demandSonname);
				xssfSheet.getRow(4).getCell(3).setCellValue(developName);
				xssfSheet.getRow(5).getCell(3).setCellValue(developName);
				xssfSheet.getRow(6).getCell(3).setCellValue(getCellValue(developName, testName));
				xssfSheet.getRow(7).getCell(3).setCellValue(testName);
				xssfSheet.getRow(8).getCell(3).setCellValue(testName);
				xssfSheet.getRow(9).getCell(3).setCellValue(getCellValue(developName, testName));
				xssfSheet.getRow(10).getCell(3).setCellValue(demandSonname);
				xssfSheet.getRow(11).getCell(3).setCellValue(demandSonname);
				xssfSheet.getRow(12).getCell(3).setCellValue(getCellValue(demandSonname, demandGodname));
				xssfSheet.getRow(13).getCell(3).setCellValue(demandGodname);
				xssfSheet.getRow(14).getCell(3).setCellValue(demandGodname);
				
				i=++i;
			}
			    out = new FileOutputStream(file);
			    xssfWorkbook.write(out);
                out.close();
                is.close();
                return true;
	}
	private String getCellValue(String str1,String str2){
  	  String finalStr = "";
  	  if(!stringUtil.isBlank(str1)  && !stringUtil.isBlank(str2)){
  		  finalStr = str1+"、"+str2;
  	  }
  	  else{
  		  finalStr = (str1==null?"":str1)+(str2==null?"":str2);
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
