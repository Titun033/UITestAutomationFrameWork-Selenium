package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	private static final By EMAIL_ADDRESS_LOCATOR= By.id("email");
	private static final By PASSWORD_LOCATOR= By.id("passwd");
	private static final By SUBMIT_SIGN_IN_LOCATOR= By.id("SubmitLogin");

	
	public MyAccountPage doLoginWith(String emailAddress, String password) {
		enterText(EMAIL_ADDRESS_LOCATOR, emailAddress);
		enterText(PASSWORD_LOCATOR, password);
		clickOn(SUBMIT_SIGN_IN_LOCATOR);
		MyAccountPage myaccountpage= new MyAccountPage(getDriver());
		return myaccountpage;
		
	}



}
