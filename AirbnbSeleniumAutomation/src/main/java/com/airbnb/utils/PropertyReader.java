package com.airbnb.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	Properties prop = new Properties();
	InputStream input;

	public String getProperty(String key) {
		try {

			String dir=System.getProperty("user.dir");
			String path=dir+"\\src\\main\\resources\\SeleniumConfig.properties";
			//input = new FileInputStream("D:\\Selenium\\Workspace\\AirbnbSeleniumAutomation\\src\\main\\resources\\SeleniumConfig.properties");
			input = new FileInputStream(path);
			// load a properties file
			prop.load(input);

			// get the property value and print it out
		

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop.getProperty(key);
		
	}
}
