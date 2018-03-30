package com.airbnb.pageObject.components.airbnb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import com.airbnb.base.BaseTest;
import com.airbnb.utils.SeleniumHelper;



public class Homes extends SeleniumHelper{

	WebDriver driver;
	WebDriverWait wait;
	Actions action; 
	Logger LOGGER;
	public static final int TimeOut = 30;
	
	@FindBy(how = How.XPATH, 
			using = "//button[contains(@aria-controls,'menuItemComponent-dynamicMoreFilters')]")
	public WebElement btnMoreFilter;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@for,'amenities')]")
	public List<WebElement> chkBoxAmenities;
	
	@FindBy(how = How.XPATH, 
			using = "//button[contains(@aria-controls,'menuItemComponent-price_range')]")
	public WebElement btnPriceFilter;
	
	@FindBy(how = How.XPATH, 
			using = "//button[contains(@aria-label,'Minimum Price')]")
	public WebElement btnMinPrice;
	
	@FindBy(how = How.XPATH, 
			using = "//span[@data-action='save']/button")
	public WebElement btnApply;
	
	@FindBy(how = How.XPATH, 
			using = "//div[@class='_1yarz4r']")
	public List<WebElement> searchList;

	
	public Homes()
	{
		this.driver=BaseTest.driver;
		wait=new WebDriverWait(driver,TimeOut);
		action = new Actions(driver);
		LOGGER= Logger.getLogger(Homes.class);
		PageFactory.initElements(driver,this);
				
	}
	
	public void selectMoreFilter()
	{
		LOGGER.info("Apply more filters");
		clickElement(btnMoreFilter);
		WebElement aminity = chkBoxAmenities.stream().
				filter(am -> am.getText().equalsIgnoreCase("Kitchen")).collect(Collectors.toList()).get(0)
				.findElement(By.xpath("./div/span/span"));
		
		wait.until(ExpectedConditions.elementToBeClickable(aminity));

		action.moveToElement(aminity).click().perform();
		
		
	}
	public void selectPriceFilter()
	{
		LOGGER.info("select price filter values");
//		wait.until(ExpectedConditions.stalenessOf(btnPriceFilter));
		wait.until(ExpectedConditions.elementToBeClickable
				(btnPriceFilter));
		action.moveToElement(btnPriceFilter).click().perform();
		action.moveToElement(btnPriceFilter).doubleClick().perform();
		actionMoveElement(btnMinPrice,137);
	    clickElement(btnApply);
	}
	
	
	public List<Integer> getPriceList()
	{
		wait.until(ExpectedConditions.stalenessOf(searchList.get(0)));
        wait.until(ExpectedConditions.visibilityOfAllElements(searchList));
        waitForElement(searchList.get(0));
         
		List<Integer> priceList=new ArrayList<Integer>();
		IntStream.range(0, 5).forEach(
	    		 i -> {int startIndex=searchList.get(i).getText().indexOf("�")+1;
		        	
		String priceStr=searchList.get(i).getText().substring(startIndex, startIndex+3);
		priceList.add(Integer.parseInt(priceStr));});
	return priceList;
	}

public boolean isPriceGreaterThanMinPrice(List<Integer> priceList,int minPrice)
{
	LOGGER.info("validating search result with price filter");
	
	return( priceList.stream().allMatch(price->price.intValue()>=minPrice));
	
}
	
}
