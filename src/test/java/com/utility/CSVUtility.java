package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVUtility {
	
	public static Iterator<User> readCSVFile(String fileName)  {
		
		File csvFilelocator= new File(System.getProperty("user.dir")+"\\testData\\"+fileName);
		FileReader csvFileReader=null;
		CSVReader csvReader;
		String[] line;
		User userData;
		List<User> userList= new ArrayList<User>();
		try {
			csvFileReader = new FileReader(csvFilelocator);
			csvReader=new CSVReader(csvFileReader);
			csvReader.readNext();
			
			
			while((line=csvReader.readNext())!=null) {
				userData= new User(line[0],line[1]);
				userList.add(userData);
			}
			
		} catch ( IOException | CsvValidationException e) {
			e.printStackTrace();
		} 
		return userList.iterator();
		
		
	}

}
