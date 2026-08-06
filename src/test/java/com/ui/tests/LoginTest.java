package com.ui.tests;

import static com.constants.Browser.CHROME;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;

import com.ui.pages.Homepage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase{
	 Logger logger= LoggerUtility.getLogger(this.getClass());
	@Test(description= "Verifies if the valid user is able to login into the application", groups= {"e2e","sanity"}, dataProviderClass=com.ui.dataProviders.LoginDataProvider.class,dataProvider="LoginTestDataProvider")
	public void loginTest(User user) {
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Titun Chakraborty");
		
	}

//	@Test(description= "Verifies if the valid user is able to login into the application", groups= {"e2e","sanity"}, dataProviderClass=com.ui.dataProviders.LoginDataProvider.class,dataProvider="LoginTestCSVDataProvider")
//	public void loginCSVTest(User user) {
//		
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Titun Chakraborty");
//
//	}
//	
//	@Test(description= "Verifies if the valid user is able to login into the application", groups= {"e2e","sanity"}, dataProviderClass=com.ui.dataProviders.LoginDataProvider.class,dataProvider="LoginTestExcelDataProvider")
//	public void loginExcelTest(User user) {
//		
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Titun Chakraborty");
//
//	}

}
