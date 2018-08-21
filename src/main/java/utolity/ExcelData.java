package utolity;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import au.com.bytecode.opencsv.CSVReader;

public class ExcelData {
	public static XSSFSheet sheet;
	public static Map<String,String> testInPUTdATA = getAllMapTestData();
	public static void setPath(String firepath,String sheetname){
		FileInputStream fs;
		try {
			fs = new FileInputStream(firepath);
			XSSFWorkbook work = new XSSFWorkbook(fs);
			sheet = work.getSheet(sheetname);
		} catch (IOException e) {
			log.info("读取Excel测试数失败");
			e.printStackTrace();
		}
//		XSSFworkbook专门来读取Excel表的		
	}
	
	/**
	 * 
	 * @param iFestCaseRow
	 * @param cliumn
	 * @return
	 */
	public static String getCellData(int iFestCaseRow, int cliumn){
		
		XSSFCell cell =sheet.getRow(iFestCaseRow).getCell(cliumn);
		String cellvalue = null;
		switch(cell.getCellType()){
		case XSSFCell.CELL_TYPE_NUMERIC:
		     cellvalue = cell.getRawValue();
		     break;
		
		case XSSFCell.CELL_TYPE_STRING:
		cellvalue = cell.getStringCellValue();
		break;
		case XSSFCell.CELL_TYPE_BLANK:
		cellvalue="";
		break;
		default:
			log.warn("Excel data type does not exist. Cell type is: " +cell.getCellType() + "可能存在风险" );		
	}
		return cellvalue;
		}
	/**
	 * 通过第一列的key值读取第二列的数据
	 * @param key
	 * @param column
	 * @return
	 */
	
	public static int getRowContains(String key,int column){
		int i;
		int rowcount  = sheet.getLastRowNum();
		for(i=1;i<rowcount;i++){
			if(ExcelData.getCellData(i,column).equalsIgnoreCase(key)){
				break;
			}
		}
		if(i>rowcount){
			log.error("[getRowContains]: Can not find " + key);		
		}
		return i;
	}
	public static Map<String,String> getAllMapTestData(){
		Map<String,String> mapData = new HashMap<String,String>();
		setPath(Contants.path+Contants.filename,Contants.sheetname);
		int rowNumber =sheet.getPhysicalNumberOfRows();
		XSSFCell cell1 = null;
		XSSFCell cell2 = null;
		for(int i=1;i<rowNumber;i++){
			cell1=sheet.getRow(i).getCell(Contants.keycolumn);
			if(cell1.getCellType()!=XSSFCell.CELL_TYPE_BLANK){
				String keyValue = cell1.getStringCellValue().toString().trim().toLowerCase();
				String value;
				cell2= sheet.getRow(i).getCell(Contants.column);
				switch(cell2.getCellType()){
				case XSSFCell.CELL_TYPE_NUMERIC:
					value = cell2.getRawValue().toString().trim().toLowerCase();
					mapData.put(keyValue, value);
				     break;
				
				case XSSFCell.CELL_TYPE_STRING:
//					trim() 空格 toLowerCase() 小写
					value = cell2.getStringCellValue().toString().trim().toLowerCase();
					mapData.put(keyValue, value);
				break;
				case XSSFCell.CELL_TYPE_BLANK:
					value="";
					mapData.put(keyValue, value);
				break;
				default:
					log.warn("Excel data type does not exist. Cell type is: " +cell2.getCellType() + "可能存在风险" );		
			}
				
			}
		}
		return mapData;
	}
	public static String getMapData (String skey){
//		Map<String,String> data = getAllMapTestData();
		return testInPUTdATA.get(skey);
		}
	
	public static List<String[]> getLocatorsFromObjectsFile(){
		CSVReader csvReader;
		List<String[]> lists =new ArrayList<>();
				
		try {
				csvReader = new CSVReader(new FileReader(Contants.path + Contants.csvFileName));
				lists = csvReader.readAll();
				csvReader.close();
			} catch (IOException e) {
				log.error("Fail to get the web locators from ObjectRepository file. ");
				e.printStackTrace();
			} 
				
		return lists;
	}
	

}
