package com.qa.taf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage extends Page{

	public BasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getPageTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageHeader(WebElement element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void waitForElementVisible(WebElement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void waitForPageTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public WebElement generateElement(By locator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement generateElement(String locator) {
		// TODO Auto-generated method stub
		return null;
	}

}
