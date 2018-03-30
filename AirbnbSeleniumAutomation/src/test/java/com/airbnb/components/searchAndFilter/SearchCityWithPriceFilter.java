package com.airbnb.components.searchAndFilter;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.airbnb.base.BaseTest;
import com.airbnb.data.SearchDataProvider;
import com.airbnb.pageObject.components.airbnb.Homes;
import com.airbnb.pageObject.components.home.HolidayPlacesRentalPage;
import com.airbnb.pageObject.components.home.SearchRegionPage;
import com.airbnb.utils.PropertyReader;


public class SearchCityWithPriceFilter {
	WebDriver driver;
	PropertyReader property;
	SearchRegionPage searchRegionPageActions;
	HolidayPlacesRentalPage holidayPlacesRentalPageActions;
	Homes homesAction;
	Logger LOGGER;

	@BeforeSuite
	public void setup()
	{
		BaseTest.initializeDriver();
		property =new PropertyReader();
		driver= BaseTest.driver;
		driver.get(property.getProperty("URL"));
		searchRegionPageActions=new SearchRegionPage();
		holidayPlacesRentalPageActions= new HolidayPlacesRentalPage();
		homesAction=new Homes();
		LOGGER=Logger.getLogger(SearchCityWithPriceFilter.class);
	}
	
	
	@Test(dataProvider="airBnbTest", dataProviderClass = SearchDataProvider.class)
	public void searchCityHomeBasedOnPrice(int minPrice,String city) throws InterruptedException
	{
		LOGGER.info("Airbnb automation assigment scenario");
		searchRegionPageActions.searchCity(city);
		holidayPlacesRentalPageActions.selctHome();
		homesAction.selectMoreFilter();
		homesAction.selectPriceFilter();
		Assert.assertTrue(homesAction.isPriceGreaterThanMinPrice(homesAction.getPriceList(), minPrice));
	}
	
	@AfterSuite
	public void closeBrowser()
	{
		LOGGER.info("Closing the driver");
		driver.quit();
	}


}
