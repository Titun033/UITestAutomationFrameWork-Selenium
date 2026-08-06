package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	private ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);  //Initialize the instance variable- driver
	}
	
	public BrowserUtility(String browserName) {
		if(browserName.equalsIgnoreCase("chrome")) {
			logger.info("Launching Browser for: "+browserName);
			driver.set(new ChromeDriver());
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			logger.info("Launching Browser for: "+browserName);
			driver.set(new EdgeDriver());
			
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			logger.info("Launching Browser for: "+browserName);
			driver.set(new FirefoxDriver());
		}
		
		else {
			System.err.print("Invalid Browser Name... Please select Chrome/Edge/Firefox");
		}
	}
	
	public BrowserUtility(Browser browserName) {
		if(browserName== Browser.CHROME) {
			logger.info("Launching Browser for: "+browserName);
			driver.set(new ChromeDriver());
		}
		
		else if(browserName==Browser.EDGE) {
			logger.info("Launching Browser for: "+browserName);
			driver.set(new EdgeDriver());
		}
		
		else if(browserName==Browser.FIREFOX) {
			logger.info("Launching Browser for: "+browserName);
			driver.set(new FirefoxDriver());
		}
		
	}
	
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if(browserName== Browser.CHROME) {
			if(isHeadless) {
				ChromeOptions options= new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				logger.info("Launching Browser for: "+browserName);
				driver.set(new ChromeDriver(options));
			}else {
				logger.info("Launching Browser for: "+browserName);
				driver.set(new ChromeDriver());
			}
			
		}
		
		else if(browserName==Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options= new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				logger.info("Launching Browser for: "+browserName);
				driver.set(new EdgeDriver(options));
			}else {
				logger.info("Launching Browser for: "+browserName);
				driver.set(new EdgeDriver());
			}
			
		}
		
		else if(browserName==Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options= new FirefoxOptions();
				options.addArguments("--headless=old");
				logger.info("Launching Browser for: "+browserName);
				driver.set(new FirefoxDriver(options));
			}else {
				logger.info("Launching Browser for: "+browserName);
				driver.set(new FirefoxDriver());
			}
			
		}
		
	}
	
	public void goToWebsite(String url) {
		logger.info("Visiting the Website: "+url);
		driver.get().get(url);
	}
	
	public void maximizeWindow() {
		logger.info("Maximize the Browser Window ");
		driver.get().manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		logger.info("Trying to find the element with locator: "+locator);
		WebElement element=driver.get().findElement(locator);
		logger.info("Clicking on the element: "+locator);
		element.click();
	}
	
	public void enterText(By locator,String text) {
		logger.info("Trying to find the element with locator: "+locator);
		WebElement element=driver.get().findElement(locator);
		logger.info("Feeding text  on the element: "+locator);
		element.sendKeys(text);
	}
	
	public String getVisibleText(By locator) {
		logger.info("Trying to find the element with locator: "+locator);
		WebElement element=driver.get().findElement(locator);
		logger.info("Returning text from the element: "+locator);
		return element.getText();
	}
	
	public String takeScreenshot(String testName) {
		
		if(driver.get()==null) {
			 throw new IllegalStateException(
			            "WebDriver is null while taking screenshot."
			        );
		}
	     
		TakesScreenshot screenshot=(TakesScreenshot)driver.get();
		File screenshotData=screenshot.getScreenshotAs(OutputType.FILE);
		Date date= new Date();
		SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
		String timestamp=format.format(date);
		String path= System.getProperty("user.dir")+"//screenshots//"+testName+" - "+timestamp+".png";
		File screenshotFile=new File(path);
		
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
	public void quit() {
		if(driver.get()!=null) {
			driver.get().quit();
			driver.remove();
		}
	}
	
	

}
