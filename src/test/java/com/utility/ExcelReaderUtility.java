package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {
	public static Iterator<User> readExcelFile(String fileName) {
		File excelFile= new File(System.getProperty("user.dir")+File.separator+"testData"+File.separator+fileName);
		XSSFWorkbook xssfWorkBook = null;
		List<User> userList= new ArrayList<User>();
		//XLSX File
		try {
			 xssfWorkBook= new XSSFWorkbook(excelFile);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XSSFSheet xssfSheet=xssfWorkBook.getSheet("LoginTestData");
		Iterator<Row> rowIterator=xssfSheet.iterator();
		 rowIterator.next();
		 while(rowIterator.hasNext()) {
			 Row row=rowIterator.next();
			Cell firstCell=row.getCell(0);
			Cell secondCell=row.getCell(1);
			User user= new User(firstCell.toString(),secondCell.toString());
			userList.add(user);
		 }
		
		 return userList.iterator();
	}

}
