package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {
	private static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal= new ThreadLocal<WebDriver>();
	private static ThreadLocal<DesiredCapabilities> capabilitesLocal= new ThreadLocal<DesiredCapabilities>();
	
	public static WebDriver  initalizeLabdaTestSession(String browser,String testName) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", "latest");
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("user", "sdettitun03");
        ltOptions.put("accessKey", "LT_lglFEd5egGAdYOLg0CMtrywQKxkB31Galpy1L7I4nzo84ge");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("name", testName);
        ltOptions.put("platformName", "Windows 10");
        ltOptions.put("seCdp", true);
        ltOptions.put("selenium_version", "latest");
        capabilities.setCapability("LT:Options", ltOptions);
        capabilitesLocal.set(capabilities);
        try {
			driverLocal.set( new RemoteWebDriver(new URL(HUB_URL), capabilitesLocal.get()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return driverLocal.get();
		
	}
	
	public static void quitSession() {
		if(driverLocal.get()!=null) {
			driverLocal.get().quit();
			driverLocal.remove();
		}
	}
}
