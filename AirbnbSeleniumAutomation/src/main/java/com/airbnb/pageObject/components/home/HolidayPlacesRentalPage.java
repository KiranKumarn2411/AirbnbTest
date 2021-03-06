package com.airbnb.pageObject.components.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.airbnb.base.BaseTest;
import com.airbnb.utils.SeleniumHelper;

public class HolidayPlacesRentalPage extends SeleniumHelper{
	
WebDriver driver;
	
	WebDriverWait wait;
	Actions action; 
	Logger LOGGER;
	
	
	@FindBy(how = How.ID, using = "GeocompleteController-via-SearchBarV2-SearchBarV2")
	public WebElement editSearch;

	
	@FindBy(how = How.XPATH, using = "//button[contains(@data-veloute,'homes')]")
	public WebElement btnHomes;
	
	public static final int TimeOut = 30;
	public HolidayPlacesRentalPage()
	{
		this.driver=BaseTest.driver;
		wait=new WebDriverWait(driver,TimeOut);
		action = new Actions(driver);
		LOGGER= Logger.getLogger(SearchRegionPage.class);
		PageFactory.initElements(driver,this);
			
		
	}
	public void selctHome() throws InterruptedException
	{
		LOGGER.info("Select homes in holiday places rental page ");	
		clickElement(btnHomes);
	
	}

}
