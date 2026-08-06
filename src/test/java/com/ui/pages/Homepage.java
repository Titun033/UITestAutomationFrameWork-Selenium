package com.ui.pages;

import static com.constants.Env.QA;
import static com.utility.JSONUtility.readJSON;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

//import static com.utility.PropertiesUtil.*;

public final class Homepage extends BrowserUtility {

	private static final By SIGN_IN_LINK_LOCATOR = By.linkText("Sign in");
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public Homepage(WebDriver driver) {
		super(driver); // To call the parent class constructor from child class
		goToWebsite(readJSON(QA).getUrl());
		maximizeWindow();
	}

	public Homepage(Browser browserName,boolean isHeadless) {
		super(browserName,isHeadless); // To call the parent class constructor from child class
		goToWebsite(readJSON(QA).getUrl());
		maximizeWindow();
	}

	public LoginPage goToLoginPage() {
		logger.info("Trying to perform Click on the SignIn Link to go to the LoginPage!");
		clickOn(SIGN_IN_LINK_LOCATOR);
		return  new LoginPage(getDriver());
		
	}
	
	public void quit() {
		if(getDriver()!=null) {
			getDriver().quit();
		}
		
	}

}
