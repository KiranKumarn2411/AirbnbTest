package com.airbnb.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

public static WebDriver driver;
	
	public static void initializeDriver()
	{
		String dir=System.getProperty("user.dir");
		String path=dir+"\\driver\\chromedriver.exe";
	
		System.setProperty("webdriver.chrome.driver", path);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		
	}
}
