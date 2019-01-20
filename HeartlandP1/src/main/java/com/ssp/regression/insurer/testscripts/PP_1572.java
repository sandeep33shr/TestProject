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
public class PP_1572 extends BaseTest {

	private String webSite, mode;
	public String PCLwebSite;
	String featureId = "PP_1572_";

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


	@Test(description = "Verify whether the search result contains client details, when searched with client code for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2746(String browser) throws Exception {

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

	@Test(description = "Verify whether error message is displayed when searched with invalid client code  for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2748(String browser) throws Exception {

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


	@Test(description = "Verify whether the search result contains client details, when searched with client name for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2750(String browser) throws Exception {

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

	
	@Test(description = "Verify whether error message is displayed when the search is done with invalid client name for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2752(String browser) throws Exception {

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

	
	@Test(description = "Verify whether the search result contains client details, when searched with postcode for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2754(String browser) throws Exception {

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

	
	@Test(description = "Verify whether error message is displayed when searched with invalid postcode  for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2756(String browser) throws Exception {

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

	@Test(description = "Verify whether error message is displayed when the search is done with invalid Email  for corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2758(String browser) throws Exception {

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


	@Test(description = "Verify Error message is displayed when invalid email address id entered while creating corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2760(String browser) throws Exception {

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


	@Test(description = "Verify the wild card search in client name field for corporate client in find client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2762(String browser) throws Exception {

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


	@Test(description = "Verify the wild card search in client code field for corporate client in find client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2764(String browser) throws Exception {

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
	

	@Test(description = "Create a new corporate client with Main Email Contact and search with same Main Email Contact.  Verify created client is displayed in search result", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2766(String browser) throws Exception {

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


	@Test(description = "Create a new corporate client with alternate email and search with same Internet Electronic Mail. Verify created client is displayed in search result", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2768(String browser) throws Exception {

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

	
	@Test(description = "Create a new corporate client with Main Email Contact and search with correct Main Email Contact and correct client code. Verify whether the created client is displayed in search result", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2770(String browser) throws Exception {

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

	
	@Test(description = "Create a new corporate client with alternate email and search with correct Internet Electronic Mail and correct client code. Verify whether the created client is displayed in search result", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2772(String browser) throws Exception {

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


	@Test(description = "Create a new corporate client with Main Email Contact and Verify whether an error message is displayed when the search is done with same Main Email Contact and incorrect client code", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2774(String browser) throws Exception {

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


	@Test(description = "Create a new corporate client with alternate email and Verify whether an error message is displayed when the search is done with same Internet Electronic Mail and incorrect client code", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2776(String browser) throws Exception {

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

	@Test(description = "Verify Error message is displayed when invalid email address id entered while creating corporate client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2778(String browser) throws Exception {

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

	@Test(description = "Create a new corporate client with Main Email Contact and Verify whether error message is displayed when the search is done with incorrect Main Email Contact and correct client code", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2779(String browser) throws Exception {

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

	

	@Test(description = "Create a new corporate client with alternate email and Verify whether error message is displayed when the search is done with incorrect Internet Electronic Mail and correct client code", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2780(String browser) throws Exception {

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

	@Test(description = "Verify whether the search result displays correct client code details, when searched with exisiting alt identifier", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2781(String browser) throws Exception {

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

	@Test(description = "Verify the wild card search in email address field for find client", dataProviderClass = DataProviderUtils.class, dataProvider = "ssBVTDataProvider")
	public void TC1572_2782(String browser) throws Exception {

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