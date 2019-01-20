package com.ssp.uxp_pages;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.BaseTest;
import com.ssp.support.EmailReport;
import com.ssp.support.Log;

/**
 * Get TestData class is used for test data variable declaration globally and
 * use it in synchronized for parallel execution
 */

@Listeners(EmailReport.class)
public class GetTestData extends BaseTest {
	static String column_value = null, Temp_value = null, input = null, col_value = null, path = null,
			iteration_value = null, iter_value = null, screen_value = null, Screen_value = null;
	static HashMap<String, String> testData = new HashMap<String, String>();

	public GetTestData(String featureId, String tcId) throws Exception {
		try {
			String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
			String configXmlName = featureId + env + "_" + "Config.xml";
			HashMap<String, String> testDataConfig = getXmlData(configXmlName, tcId, env);
			getTestData(testDataConfig);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public String description;
	public String xml_Location, iterationCount, mode;

	public synchronized void getTestData(HashMap<String, String> testData) {
		description = testData.get("Description");
		xml_Location = testData.get("XML_Location");
		iterationCount = testData.get("Iteration");
		mode = testData.get("Mode");
	}

	/**
	 * To get data from configure Xml
	 * 
	 * @param XMLLocation
	 * @param testCaseId
	 * @param env
	 * @return
	 */
	public static HashMap<String, String> getXmlData(String XMLLocation, String testCaseId, String env) {

		try {

			String basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
					+ File.separator + "resources" + File.separator + "testdata" + File.separator + "Config"
					+ File.separator + env + File.separator;
			String configXmlFilePath = basePath + XMLLocation;
			File file = new File(configXmlFilePath);
			if (!file.exists())
				throw new Exception("File does not found in the directory : " + file);

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printNote(doc.getElementsByTagName("Feature"), testCaseId);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return testData;
	}

	/**
	 * To print node values
	 * 
	 * @param nodeList
	 * @param testcaseID
	 * @throws Exception
	 */
	private static void printNote(NodeList nodeList, String testcaseID) throws Exception {
		try {

			for (int count = 0; count < nodeList.getLength(); count++) {

				Node tempNode = nodeList.item(count);

				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

					if (tempNode.hasAttributes()) {

						NamedNodeMap nodeMap = tempNode.getAttributes();

						if (nodeMap.getNamedItem("id").getNodeValue().equals(testcaseID)) {

							for (int i = 0; i < nodeMap.getLength(); i++) {

								testData.put(nodeMap.item(i).getNodeName(), nodeMap.item(i).getNodeValue());

							}
							break;

						}
					}

					if (tempNode.hasChildNodes()) {

						printNote(tempNode.getChildNodes(), testcaseID);

					}

					System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

				}

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	/**
	 * To locate Object repository Xml path
	 * 
	 * @param XMLLocation
	 * @param testCaseId
	 * @param driver
	 * @param extentedReport
	 */
	public static void getObjectXmlData(String XMLLocation, String mode, String testCaseId, int iterCount,
			WebDriver driver, ExtentTest extentedReport) throws Exception {

		try {
			String basePath = null;
			if (mode.equals("Broker")) {
				basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "testdata" + File.separator + "Testxml"
						+ File.separator + "Broker" + File.separator;
			} else {
				basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "testdata" + File.separator + "Testxml"
						+ File.separator + "Insurer" + File.separator;

			}

			String xmlFilePath = basePath + XMLLocation;
			String testcaseID = null;

			File file = new File(xmlFilePath);
			// Log.message("TestDate XML path : " + file);
			if (!file.exists())
				throw new Exception("FILE NOT FOUND " + file.getAbsolutePath());

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = dBuilder.parse(file);

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			if (doc.hasChildNodes()) {

				printnote(XMLLocation, iterCount, doc.getElementsByTagName("Screen"), testcaseID, driver,
						extentedReport);

			}

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * To read data from Object repository Xml
	 * 
	 * @param nodeList
	 * @param testcaseID
	 * @param driver
	 * @param extentedReport
	 * @throws Exception
	 */
	private static void printnote(String XMLLocation, int iterCount, NodeList nodeList, String testcaseID,
			WebDriver driver, ExtentTest extentedReport) throws Exception {
		try {
			String titleOfPage = null;
			for (int count = 0; count < nodeList.getLength(); count++) {

				Node tempNode = nodeList.item(count);

				// make sure it's element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

					if ((tempNode.getNodeName()).equals("Screen")) {
						column_value = tempNode.getAttributes().getNamedItem("id").getNodeValue();
						titleOfPage = tempNode.getAttributes().getNamedItem("title").getNodeValue();
						Temp_value = column_value;
					} else {
						column_value = column_value + "_"
								+ tempNode.getAttributes().getNamedItem("fieldLabel").getNodeValue();
					}

					if (tempNode.hasAttributes() && tempNode.getNodeName().equals("Screen")) {
						switch (Temp_value) {

						case "LoginScreen":
							new LoginPage(driver, extentedReport);
							break;

						case "Home":
							new HomePage(driver, extentedReport);
							break;

						case "PersonalClient":
							new PersonalClientPage(driver, extentedReport);
							break;

						case "CorporateClient":
							new CorporateClientPage(driver, extentedReport);
							break;

						case "ECLoginScreen":
							new ECLoginPage(driver, extentedReport);
							break;

						case "DashBoard":
							new AgentDashboardPage(driver, extentedReport);
							break;

						case "FindClient":
							new FindClientPage(driver, extentedReport);
							break;

						case "EditClientDetails":
							new EditClientDetails(driver, extentedReport);
							break;

						case "PolicyHeader":
							new PolicyHeader(driver, extentedReport);
							break;

						case "PremiumDisplay":
							new PremiumDisplayPage(driver, extentedReport);
							break;

						case "ImportantStatements":
							new ImportantStatementsPage(driver, extentedReport);
							break;

						case "TransactionConfirmation":
							new TransactionConfirmationPage(driver, extentedReport);
							break;

						case "MtaReason":
							new MtaReasonPage(driver, extentedReport);
							break;

						case "PayNow":
							new PayNowPage(driver, extentedReport);
							break;

						case "QuoteResult":
							new QuoteResultPage(driver, extentedReport);
							break;

						case "SearchTransactions":
							new SearchTransactionsPage(driver, extentedReport);
							break;

						case "CashList":
							new CashListPage(driver, extentedReport);
							break;

						case "ReceiptType":
							new ReceiptTypePage(driver, extentedReport);
							break;
						default:
							new CommonPages(driver, extentedReport, titleOfPage, Temp_value);
							break;
						}

					}

					String locatorType = null, id = null, path = null, input = null, attribute = null,
							fieldLabel = null, screenShot = null, pathType = null, validMessage = null,
							errorMessage = null;
					if (tempNode.hasAttributes() && tempNode.getNodeName().equals("Field")) {

						NamedNodeMap nodeMap = tempNode.getAttributes();

						for (int i = 0; i < nodeMap.getLength(); i++) {

							Node node = nodeMap.item(i);
							Log.message("Value of " + column_value + "_" + node.getNodeName() + ": ------->"
									+ node.getNodeValue());
							if (node.getNodeName().equals("type")) {
								locatorType = node.getNodeValue();
							}
							if (node.getNodeName().equals("id")) {
								id = node.getNodeValue();
							}
							if (node.getNodeName().equals("attribute")) {
								attribute = node.getNodeValue();
							}
							if (node.getNodeName().equals("fieldLabel")) {
								fieldLabel = node.getNodeValue();
							}
							if (node.getNodeName().equals("screenShot")) {
								screenShot = node.getNodeValue();
							}
							if (node.getNodeName().equals("pathType")) {
								pathType = node.getNodeValue();
							}
							if (node.getNodeName().equals("validMessage")) {
								validMessage = node.getNodeValue();
							}
							if (node.getNodeName().equals("errorMessage")) {
								errorMessage = node.getNodeValue();
							}
						}
						input = getTestXmlData(XMLLocation, testcaseID, fieldLabel, iterCount, driver, extentedReport);
						path = getLocatorData(Temp_value, id, driver, extentedReport);

						/*
						 * Log.message(locatorType + input + path + fieldLabel);
						 */

						if (fieldLabel.equalsIgnoreCase("Value of Bank Dropdown"))
							System.out.println("AccountBalance");

						ActionKeyword.performActionBasedOnFieldType(driver, pathType, locatorType, path, attribute,
								input, fieldLabel, validMessage, errorMessage, extentedReport, screenShot);
					}
					if (tempNode.hasChildNodes()) {

						printnote(XMLLocation, iterCount, tempNode.getChildNodes(), testcaseID, driver, extentedReport);
					}
					column_value = Temp_value;
				}
			}

		} catch (Exception e) {
			throw new Exception(e);

		}
	}

	/**
	 * To locate test data Xml path
	 * 
	 * @param XMLLocation
	 * @param testCaseId
	 * @param driver
	 * @param extentedReport
	 * @return
	 */
	public static String getTestXmlData(String XMLLocation, String testCaseId, String fieldName, int iterCount,
			WebDriver driver, ExtentTest extentedReport) throws Exception {

		try {

			{

				String basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "testdata" + File.separator + "TestDataXml"
						+ File.separator;
				String xmlFilePath = basePath + XMLLocation;

				File file = new File(xmlFilePath);
				// Log.message("TestDate XML path : " + file);

				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

				Document doc = dBuilder.parse(file);

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				if (doc.hasChildNodes()) {

					input = printNotes(doc.getElementsByTagName("Iteration"), fieldName, iterCount, driver,
							extentedReport);

				}

			}
			return input;

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * To read data from test data Xml
	 * 
	 * @param nodeList
	 * @param testcaseID
	 * @param driver
	 * @param extentedReport
	 * @return
	 * @throws Exception
	 */
	private static String printNotes(NodeList nodeList, String fieldName, int iterCount, WebDriver driver,
			ExtentTest extentedReport) throws Exception {
		try {
			for (int count = 0; count < nodeList.getLength(); count++) {

				Node tempNode = nodeList.item(count);

				// make sure it's element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

					if ((tempNode.getNodeName()).equals("Iteration")) {

						iter_value = tempNode.getAttributes().getNamedItem("id").getNodeValue();
						iteration_value = iter_value;

					} else {

						col_value = tempNode.getAttributes().getNamedItem("fieldLabel").getNodeValue();

					}

					if (tempNode.hasAttributes() && tempNode.getNodeName().equals("Field")
							&& iteration_value.equals("Iteration_" + Integer.toString(iterCount))
							&& col_value.equals(fieldName)) {

						NamedNodeMap nodeMap = tempNode.getAttributes();

						for (int i = 0; i < nodeMap.getLength(); i++) {

							Node node = nodeMap.item(i);
							System.out.println("Value of " + col_value + "_" + node.getNodeName() + ": ------->"
									+ node.getNodeValue());

							if (node.getNodeName().equals("input")) {
								input = node.getNodeValue();
							}

						}

					}

					if (tempNode.hasChildNodes()) {

						printNotes(tempNode.getChildNodes(), fieldName, iterCount, driver, extentedReport);

					}
					iter_value = iteration_value;
				}
			}
			return input;

		} catch (Exception e) {
			throw new Exception(e);

		}
	}

	/**
	 * To locate Locator Xml path
	 * 
	 * @param XMLLocation
	 * @param testCaseId
	 * @param driver
	 * @param extentedReport
	 * @return
	 */
	public static String getLocatorData(String screenName, String idName, WebDriver driver, ExtentTest extentedReport)
			throws Exception {

		try {

			{
				path = null;
				String basePath = new File(".").getCanonicalPath() + File.separator + "src" + File.separator + "main"
						+ File.separator + "resources" + File.separator + "testdata" + File.separator
						+ "CommonLocatorXml" + File.separator + "locator.xml";

				File file = new File(basePath);
				// Log.message("TestDate XML path : " + file);

				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

				Document doc = dBuilder.parse(file);

				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

				if (doc.hasChildNodes()) {

					path = printLocator(doc.getElementsByTagName("Screen"), screenName, idName, driver, extentedReport);

				}

			}
			return path;

		} catch (Exception e) {
			throw new Exception(e);
		}

	}

	/**
	 * To read Locator from Locator Xml
	 * 
	 * @param nodeList
	 * @param testcaseID
	 * @param driver
	 * @param extentedReport
	 * @return
	 * @throws Exception
	 */
	private static String printLocator(NodeList nodeList, String screenName, String idName, WebDriver driver,
			ExtentTest extentedReport) throws Exception {
		try {
			for (int count = 0; count < nodeList.getLength(); count++) {

				Node tempNode = nodeList.item(count);

				// make sure it's element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

					if ((tempNode.getNodeName()).equals("Screen")) {

						screen_value = tempNode.getAttributes().getNamedItem("id").getNodeValue();
						Screen_value = screen_value;

					} else {
						col_value = tempNode.getAttributes().getNamedItem("id").getNodeValue();
					}

					if (tempNode.hasAttributes() && tempNode.getNodeName().equals("Field")
							&& Screen_value.equals(screenName) && col_value.equals(idName)) {

						NamedNodeMap nodeMap = tempNode.getAttributes();

						for (int i = 0; i < nodeMap.getLength(); i++) {

							Node node = nodeMap.item(i);
							System.out.println("Value of " + col_value + "_" + node.getNodeName() + ": ------->"
									+ node.getNodeValue());

							if (node.getNodeName().equals("path")) {
								path = node.getNodeValue();
							}

						}

					}

					if (tempNode.hasChildNodes()) {

						printLocator(tempNode.getChildNodes(), screenName, idName, driver, extentedReport);

					}
					screen_value = Screen_value;
				}
			}
			return path;

		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
