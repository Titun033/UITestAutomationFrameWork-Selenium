package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.PropertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer{
	// private static final int MAX_NUMBER_ATTEMPTS=Integer.getInteger(PropertiesUtil.readProperty(Env.DEV, "MAX_NUMBER_ATTEMPTS"));
	 private static final int MAX_NUMBER_ATTEMPTS=JSONUtility.readJSON(Env.QA).getMAX_NUMBER_ATTEMPTS();
	 private  int currentAttempt=1;

	@Override
	public boolean retry(ITestResult result) {
		if(currentAttempt<MAX_NUMBER_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		return false;
	}

}
