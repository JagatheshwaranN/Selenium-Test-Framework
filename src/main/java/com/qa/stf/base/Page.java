package com.qa.stf.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Abstract base class for representing a webpage.
 * Provides common methods and utilities for interacting with web elements.
 */
public abstract class Page {

	/**
	 * Retrieves the title of the current page.
	 *
	 * @return the title of the page as a {@link String}
	 */
	public abstract String getPageTitle();

	/**
	 * Retrieves the URL of the current page.
	 *
	 * @return the URL of the page as a {@link String}
	 */
	public abstract String getPageUrl();

	/**
	 * Retrieves the header of the current page based on the specified element.
	 *
	 * @param element the {@link WebElement} representing the header
	 * @param label a descriptive label for the element (used for logging or reporting)
	 * @return the header text as a {@link String}
	 */
	public abstract String getPageHeader(WebElement element, String label);

	/**
	 * Waits until the specified web element is visible on the page.
	 *
	 * @param element the {@link WebElement} to wait for
	 * @param label a descriptive label for the element (used for logging or reporting)
	 */
	public abstract void waitForElementVisible(WebElement element, String label);

	/**
	 * Waits until the page title matches the specified title.
	 *
	 * @param title the expected title of the page
	 */
	public abstract void waitForPageTitle(String title);

	/**
	 * Generates a web element based on the specified locator.
	 *
	 * @param locator the {@link By} locator of the element
	 * @param label a descriptive label for the locator (used for logging or reporting)
	 * @return the generated {@link WebElement}
	 */
	public abstract WebElement generateElement(By locator, String label);

	/**
	 * Generates a web element based on the specified string locator.
	 *
	 * @param locator the string representation of the locator
	 * @param label a descriptive label for the locator (used for logging or reporting)
	 * @return the generated {@link WebElement}
	 */
	public abstract WebElement generateElement(String locator, String label);

}
