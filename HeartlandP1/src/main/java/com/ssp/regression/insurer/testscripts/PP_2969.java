
package com.ssp.regression.insurer.testscripts;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ssp.uxp_pages.GetTestData;
import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.EmailReport;
import com.ssp.support.Log;
import com.ssp.support.WebDriverFactory;
import com.ssp.utils.DataProviderUtils;

@Listeners(EmailReport.class)
public class PP_2969 extends BaseTest {

	private String webSite, mode;
	public String PCLwebSite;
	String featureId = "PP_2969_";

	@BeforeMethod(alwaysRun = true)
	public void init(ITestContext context) throws IOException {
		webSite = System.getProperty("webSite") != null ? System.getProperty("webSite")
				: context.getCurrentXmlTest().getParameter("webSite");
		PCLwebSite = System.getProperty("PCLwebSite") != null ? System.getProperty("PCLwebSite")
				: context.getCurrentXmlTest().getParameter("PCLwebSite");
		mode = context.getCurrentXmlTest().getParameter("mode");
	}

	public ExtentTest addTestInfo(String testCaseId, String testDesc) {

		String browserwithos = null;
		String test = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();

		String browsername = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("browser");
		String browserversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("browser_version");
		String os = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("os").substring(0,
				1);
		String osversion = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
				.getParameter("os_version");
		browserwithos = os + osversion + "_" + browsername + browserversion;

		return Log.testCaseInfo(testCaseId + " [" + test + "]",
				testCaseId + " - " + testDesc + " [" + browserwithos + "]", test, "UXP-EC-Purebroker");
	}

	@Test(description = "To Verify the presence of Renewal tab in Policy Header", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3062(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the fields available in Renewal tab", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3063(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3064(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3074(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3075(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3076(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3077(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3078(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3079(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3080(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3081(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3082(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3083(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "To verify the value of renewal date field should duplicate the value in Basic details", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3084(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Main Tab' in policy header section page in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3096(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the listed 29 fields are Available in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3105(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Traded Type' field is in read only in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3097(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Lead Insurer (populated from quote data)' field is mandatory during Buy process in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3098(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify 'Policy Balance','Balance inc. unsettled PF/SA' fields are in Read only mode in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3099(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify 'Risk Transfer' and 'Cascading Risk Transfer' fields are show/hide based on Insurer configuration(Either Yes or no)and when policy is purchased, the field will be set based on Insurer maintenance rules in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3100(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Business Type' Field is Mandatory in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3101(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Business Source' Field is prepopulated in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3102(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify that 'department' and 'sub team' fields are manually populated by broker in Broker mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3103(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Main Tab' in policy header section page in Insurer mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3106(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the listed 29 fields are Available in Insurer mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3108(String browser) throws Exception {
		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

	@Test(description = "Verify the 'Analysis Code' Field is prepopulated in Insurer mode", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC3107(String browser) throws Exception {

		String tcId = new Object() {
		}.getClass().getEnclosingMethod().getName();
		final WebDriver driver = WebDriverFactory.get(browser);
		GetTestData testDataConfig = new GetTestData(featureId, tcId);

		Log.testCaseInfo(testDataConfig.description + "<small><b><i>[" + browser + "]</b></i></small>");

		ExtentTest extentedReport = addTestInfo(tcId, testDataConfig.description);

		try {
			for (int iterCount = 0; iterCount < Integer.parseInt(testDataConfig.iterationCount); iterCount++) {
				Log.message("<--------------------- Iteration " + iterCount + " is started ----------------------->",
						extentedReport);
				driver.get(webSite);
				GetTestData.getObjectXmlData(testDataConfig.xml_Location, mode, tcId, iterCount, driver,
						extentedReport);
				Log.message("<--------------------- Iteration " + iterCount + " is ended ----------------------->",
						extentedReport);
			}
		} catch (Exception e) {
			Log.exception(e, driver, extentedReport);
		} finally {
			driver.quit();
			Log.testCaseResult(extentedReport);
			Log.endTestCase(extentedReport);

		}
	}

}
