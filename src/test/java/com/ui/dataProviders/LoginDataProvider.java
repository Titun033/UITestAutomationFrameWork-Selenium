package com.ui.dataProviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVUtility;
import com.utility.ExcelReaderUtility;
public class LoginDataProvider {
	
	
	@DataProvider(name="LoginTestDataProvider")
	 public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
		 Gson gson= new Gson();
		 File logintestDataFile= new File(System.getProperty("user.dir")+"\\testData\\loginData.json");
		  FileReader loginDataFileReader= new FileReader(logintestDataFile);
		  TestData loginTestData=gson.fromJson(loginDataFileReader, TestData.class);
		  List<Object[]> dataToReturn= new ArrayList<Object[]>();
		  for(User userData:loginTestData.getData()) {
			  dataToReturn.add(new Object[] {userData});
		  }
		  
		  return dataToReturn.iterator();
		  
	 }
	 
	 @DataProvider(name="LoginTestCSVDataProvider")
	 public Iterator<User> csvDataProvider() {
		 return CSVUtility.readCSVFile("loginData.csv");
	 }
	 
	 @DataProvider(name="LoginTestExcelDataProvider")
	 public Iterator<User> excelDataProvider() {
		 return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
	 }
	  

}
