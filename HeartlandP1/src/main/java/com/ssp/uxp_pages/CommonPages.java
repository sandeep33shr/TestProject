package com.ssp.uxp_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.Log;
import com.ssp.utils.WaitUtils;

public class CommonPages extends LoadableComponent<HomePage> {

	private final WebDriver _driver;
	private ExtentTest _extentedReport;
	private String _title, _pageName;

	/**
	 * Constructor to assign global variables
	 * 
	 * @param driver
	 * @param report
	 * @param title
	 * @param pageName
	 */
	public CommonPages(WebDriver driver, ExtentTest report, String title, String pageName) {
		this._driver = driver;
		this._extentedReport = report;
		this._title = title;
		this._pageName = pageName;
		PageFactory.initElements(driver, this);
		load();
	}

	@Override
	protected void isLoaded() {
		WaitUtils.waitForPageLoad(_driver, 100);
		System.out.println(_driver.getTitle());
		if (!_driver.getTitle().contains(_title)) {
			Log.fail(_pageName + " Page did not open up. Site might be down. Expected Page title is " + _title, _driver,
					_extentedReport);
		} else {
			Log.message("Successfully navigated to " + _pageName + " Page", _driver, _extentedReport);
		}
	}

	@Override
	protected void load() {
		WaitUtils.waitForPageLoad(_driver, 60);
		isLoaded();
	}
}
