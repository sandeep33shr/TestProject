package com.ssp.uxp_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

/**
 * Login page consists login as a User with the their credentials (username,
 * password) *
 */
public class LoginPage extends LoadableComponent<LoginPage> {

	static String pathType = "css";

	private final WebDriver driver;
	private ExtentTest extentedReport;
	private boolean isPageLoaded;

	/**
	 * 
	 * Constructor class for Login page Here we initializing the driver for page
	 * factory objects. For ajax element waiting time has added while
	 * initialization
	 * 
	 * @param driver
	 *            : Webdriver
	 */
	public LoginPage(WebDriver driver, ExtentTest report) {

		this.driver = driver;
		this.extentedReport = report;
		PageFactory.initElements(driver, this);
		load();
	}

	@Override
	protected void isLoaded() {

		WaitUtils.waitForPageLoad(driver);

		if (!isPageLoaded) {
			Assert.fail();
		}

		if (isPageLoaded && !driver.getTitle().contains("SSP Identity Server Login")) {
			Log.fail("SSP Login Page did not open up. Site might be down.", driver, extentedReport);

		} else {
			Log.message("Navigated to Login page", driver, extentedReport, true);
		}

	}

	@Override
	protected void load() {

		isPageLoaded = true;
		WaitUtils.waitForPageLoad(driver);
		isLoaded();

	}
}