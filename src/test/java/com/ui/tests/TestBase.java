package com.ui.tests;

import com.constants.Browser;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ui.pages.Homepage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;


public class TestBase {
 
	protected Homepage homePage;
	protected Logger logger= LoggerUtility.getLogger(this.getClass());
	boolean isLambdaTest;
	
	@Parameters({"browser","isLambdaTest","isHeadless"})
	@BeforeMethod(description="Load the HomePage of the Website")
	public void setUp(
			@Optional("chrome")String browser,@Optional("false") boolean isLambdaTest, @Optional("true")boolean isHeadless, ITestResult result) {
		this.isLambdaTest=isLambdaTest;
		WebDriver lambdaDriver;
		if(isLambdaTest) {
			
			lambdaDriver=LambdaTestUtility.initalizeLabdaTestSession(browser, result.getMethod().getMethodName());
			homePage=new Homepage(lambdaDriver);
			
			
		}else {
			//Running the tests in local
			logger.info("Load the HomePage of the Website");
		    homePage= new Homepage(Browser.valueOf(browser.toUpperCase()),isHeadless);
			
		}
		
	}
	
	public BrowserUtility getInstance() {
		return homePage;
	}
	
	@AfterTest
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession(); //quit the session on lambdaTest
		}else {
			if(homePage.getDriver()!=null) {
				homePage.quit(); //local
			}
			
		}
	}
}
