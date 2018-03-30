package com.airbnb.data;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
	
	static int price=100;
	static String city="London";
	
	@DataProvider(name="airBnbTest")
	public static Object[][] getAirBnbTestValue()
	{
		return new Object[][] {{price,city}};
	}

}
