package com.ssp.uxp_pages;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.ssp.support.DateTimeUtility;
import com.ssp.support.GetQuote;
import com.ssp.support.Log;
import com.ssp.utils.GenericUtils;
import com.ssp.utils.WaitUtils;

public class ActionKeyword {

	private WebDriver driver;
	static WebDriverWait wait;
	public static String dynamicValue = null;
	public static String dynamicList = null;
	public static HashMap<String, String> dynamicHashMap = new HashMap<>();
	private static int index = -1;

	/**
	 * To locate webElement
	 * 
	 * @param locatorTpye
	 * @param path
	 * @return
	 */
	public static By locatorValue(String locatorTpye, String path) {
		By by;
		switch (locatorTpye) {
		case "id":
			by = By.id(path);
			break;
		case "name":
			by = By.name(path);
			break;
		case "xpath":
			by = By.xpath(path);
			break;
		case "css":
			by = By.cssSelector(path);
			break;
		case "linkText":
			by = By.linkText(path);
			break;
		case "partialLinkText":
			by = By.partialLinkText(path);
			break;
		default:
			by = null;
			break;
		}
		return by;
	}

	/**
	 * To perform Action Based On FieldType
	 * 
	 * @param locatorTpye
	 * @param path
	 * @param input
	 * @param fieldLabel
	 * 
	 */
	public static void performActionBasedOnFieldType(WebDriver driver, String pathType, String locatorType, String path,
			String attribute, String input, String fieldLabel, String validMessage, String errorMessage,
			ExtentTest extentedReport, String screenShot) throws Exception {
		Boolean screenshot = false;
		if (screenShot.equals("true"))
			screenshot = true;
		if (input.equals(""))
			input = dynamicValue;
		else if (input.equals("dynamicList"))
			input = dynamicList;
		else if (input.equalsIgnoreCase("dynamicHashMap"))
			for (String key : dynamicHashMap.keySet())
				if (key.equalsIgnoreCase(fieldLabel)) {
					input = dynamicHashMap.get(key);
					break;
				}

		switch (locatorType) {
		case "textfield":
			enterTextUsingActionsClass(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "textfieldElement":
			enterTextBasicApproach(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "cleartextfield":
			clearTextField(fieldLabel, pathType, path, input, screenShot, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "autoCompleteTextField":
			enterAutocompleteText(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "Button":
			clickOnButton(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "TableRowSelectRandom":
			clickOnTableRandomly(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;

		case "selectSpinner":
			WaitUtils.waitForSpinner(driver);
			break;

		case "switchIframe":
			try {
				driver.switchTo().frame(path);
			} catch (Exception e) {
				throw new Exception(e);
			}
			break;

		case "returnIframe":
			try {
				driver.switchTo().defaultContent();
			} catch (Exception e) {
				throw new Exception(e);
			}
			break;

		case "link":
			clickOnLink(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "checkBox":
			selectCheckbox(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "DeSelectCheckbox":
			unSelectCheckbox(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "select":
			selectDropDownOptionByVisibleText(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "selectByIndex":
			selectDropDownOptionByIndex(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "selectInput":
			inputDropDownOption(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "radioButton":
			selectRadioButton(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "mouseHover":
			performMouseHover(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "doubleClick":
			performDoubleClick(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "rightClick":
			performRightClick(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "buttonJS":
			clickButtonJS(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "inputJS":
			enterTextJS(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;
		case "assertDisplayPositive":
			isDisplayTrue(fieldLabel, pathType, path, true, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertElementNotFound":
			assertElementNotFoundOnThePage(pathType, path, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertDisplayNegative":
			isDisplayTrue(fieldLabel, pathType, path, false, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertNotDisplay":
			isDisplayFalse(fieldLabel, pathType, path, true, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertGetText":
			getText(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport, screenshot);
			break;

		case "assertGetTextofInput":
			getTextofAttribute(fieldLabel, pathType, path, attribute, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "assertGetTextofDisabledAttribute":
			getTextofDisabledAttribute(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "assertSelectedCheckBox":
			getCheckBoxCheckedAttribute(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "assertGetTextContain":
			getTextContain(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "dynamicvalue":
			getDynamicValue(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;

		case "dynamicList":
			getDynamicListValue(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;

		case "dynamicHashMap":
			getDynamicHashMap(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "dynamicHashMapAttribute":
			getDynamicHashMapAsPerAttribute(fieldLabel, pathType, path, attribute, driver, extentedReport, screenshot);
			break;
		case "dyanamicHashMapIndex":
			getDynamicHashMapAsPerRowIndex(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "dynamicHashMapAsperFieldLabel":
			getDynamicHashMapAsPerFieldLabel(fieldLabel, input);
			break;
		case "assertGetListOfMultiText":
			getListOfMultiText(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertGetListTextContain":
			getListTextContain(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertOrderedListTextEquals":
			validateOrderedListTextEquals(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertUnorderedListTextEquals":
			validateUnorderedListTextEquals(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertGetTableTextContain":
			getTableTextContain(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "alertAccept":
			driver.switchTo().alert().accept();
			break;

		case "alertMessages":
			String temp = driver.switchTo().alert().getText(); //
			Log.message(temp, driver, extentedReport, false);
			break;

		case "alertDismiss":
			driver.switchTo().alert().wait();
			driver.switchTo().alert().dismiss();
			break;

		case "isPresentThenClick":
			IsPresentthenClick(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertTableRowTextContain":
			verifyTableRowValue(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertEnabledPositive":
			isEnabledTrue(fieldLabel, pathType, path, true, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertEnabledNegative":
			isEnabledTrue(fieldLabel, pathType, path, false, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "clickDynamicPath":
			clickDynamicPath(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;

		case "assertReadOnlyAttribute":
			getReadOnlyAttribute(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertPresentNegative":
			isPresentNegative(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertClaimOpenStatus":
			verifyClaimsStatusCount(fieldLabel, pathType, path, input, "Open", validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "assertClaimClosedStatus":
			verifyClaimsStatusCount(fieldLabel, pathType, path, input, "Closed", validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "assertPolicyQuotesCount":
			verifyPolicyQuotesCount(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "assertDynamicValueList":
			verifyDynamicValueList(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "isCheckboxSelected":
			isSelected(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "verifyCurrentDate":
			verifyCurrentDate(fieldLabel, pathType, path, attribute, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "verifyStringContainIgnoreCase":
			getTextContainIgnoreCase(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "clickDynamicName":
			clickDynamicName(fieldLabel, pathType, path, input, driver, extentedReport, screenshot);
			break;

		case "getRowIndexAsPerInputData":
			getRowIndexAsPerTheTableData(pathType, path, input, driver, extentedReport, screenshot);
			break;

		case "clickButtonAsPerTableRowIndex":
			clickButtonAsPerRowIndex(pathType, path, driver, extentedReport, screenshot);
			break;
		case "asrtListofTextEmptyorNot":
			getListOfTextEmptyorNot(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "asrtQuoteStatus":
			asrtQuoteStatus(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport, screenshot);
			break;
		case "asrtSortedValueListingorNot":
			asrtSortedValueListingorNot(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertTableRowsCount":
			verifyPolicyQuotesCount(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertTransactionsTableAmountValue":
			verifySumAmountOfTransactions(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertRowTextFromTable":
			verifyTextFromTable(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertAmountsInTable":
			verifySumofAmountsInTable(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;

		case "verifyGetTextValueNull":
			verifyGetTextValueNull(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;

		case "assertOutstandingAmountWithAmountInSearchTransactions":
			verifyAmountWithOutstandingAmount(input, fieldLabel, pathType, path, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertJournalTransactionReferenceNumber":
			verifyJournalReferenceNumber(fieldLabel, validMessage, errorMessage, driver, extentedReport, screenshot);
			break;
		case "assertTableAccounts":
			verifyAccountsTableHeaderWithRows(input, fieldLabel, pathType, path, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertSearchTransactionTable":
			verifySearchTransactionsTableHeaderWithRows(input, fieldLabel, pathType, path, validMessage, errorMessage,
					driver, extentedReport, screenshot);
			break;
		case "assertTableExpandAccounts":
			verifyAccountsExpandTableHeaderWithRows(input, fieldLabel, pathType, path, validMessage, errorMessage,
					driver, extentedReport, screenshot);
			break;
		case "addDataInDynamicHashMapOfViewTransactionInAccounts":
			addViewTransactionDataInDynamicHashMap(input, fieldLabel, pathType, path, validMessage, errorMessage,
					driver, extentedReport, screenshot);
			break;
		case "assertTabClientInputDataForSNDReference":
			assertViewTransactionTabClientForSNDReference(validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertViewTransactionTabAmountsForSRPReference":
			assertViewTransactionTabAmountsForSRPReference(validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertTabInsurerInputDataForSNDReference":
			assertViewTransactionTabInsurerForSNDReference(validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertTabIncomeHistoryInputDataForSNDReference":
			assertViewTransactionTabIncomeHistoryForSNDReference(validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertTabDetailsInputDataForSNDReference":
			assertViewTransactionTabDetailsForSNDReference(validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertTabDetailsInputDataForSRPReference":
			assertViewTransactionTabDetailsForSRPReference(validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "assertInputDataForSNDReference":
			assertViewTransactionForSNDReference(validMessage, errorMessage, driver, extentedReport, screenshot);
			break;
		case "assertInputDataForSRPReference":
			assertViewTransactionForSRPReference(validMessage, errorMessage, driver, extentedReport, screenshot);
			break;
		case "assertInsurerPreviewPane":
			assertInsurerPreviewPane(pathType, path, validMessage, errorMessage, driver, extentedReport, screenshot);
			break;
		case "assertCommissionPreviewPaneForInvoice":
			assertCommissionPreviewPaneForInvoicePolicy(input, pathType, path, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertCommissionPreviewPaneForPayNow":
			assertCommissionPreviewPaneForPayNowPolicy(input, pathType, path, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "assertPrimarySettledColumnInSearchTransactionTable":
			assertPrimarySettledValueInSearchTransactionTable(pathType, path, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "tabOut":
			tabOut(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport, screenshot);
			break;
		case "assertGetDefaultTextOfDropdown":
			assertGetDefaultTextOfDropdown(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "replaceCurrencySymbolFromClientBalance":
			replaceCurrencySymbolFromClientBalance(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "assertTextOfDisabledField":
			assertTextOfDisabledField(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "getMaximumLengthOfTextbox":
			getMaximumLengthOfTextbox(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "getReceiptRefDynamicHashMap":
			getReceiptRefDynamicHashMap(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "assertAllocationScreenName":
			assertAllocationScreenName(fieldLabel, pathType, path, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;
		case "getDynamicHashMapDisabledField":
			getDynamicHashMapDisabledField(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "getDynamicHashMapDisabledAmountField":
			getDynamicHashMapDisabledAmountField(fieldLabel, pathType, path, driver, extentedReport, screenshot);
			break;
		case "SumOfGridDynamicHash":
			SumOfGridDynamicHash(fieldLabel, pathType, path, driver);
			break;
		case "assertAmountRemoveComma":
			assertAmountRemoveComma(fieldLabel, pathType, path, input, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			break;
		case "SumOfGridTotalMarkedDynamicHash":
			SumOfGridTotalMarkedDynamicHash(fieldLabel, pathType, path, driver);
			break;
		case "closingBalanceAmountDynamicHashMap":
			closingBalanceAmountDynamicHashMap(fieldLabel, pathType, path, input, driver, screenshot);
			break;
		case "unreconciledBalanceAmountDynamicHashMapBeforeReconciled":
			unreconciledBalanceAmountDynamicHashMapBeforeReconciled(fieldLabel, pathType, path, input, driver,
					screenshot);
			break;
		case "OpeningBalanceAmountDynamicHashMapBeforeReconciled":
			OpeningBalanceAmountDynamicHashMapBeforeReconciled(fieldLabel, pathType, path, input, driver, screenshot);
			break;
		/*case "assertValueInGrid":
			assertValueInGrid(fieldLabel, pathType, path, input, validMessage, errorMessage, driver, extentedReport,
					screenshot);
			break;*/

		default:
			Log.message("Unable to identify UIoperation type on respective field: " + fieldLabel, driver,
					extentedReport, screenshot);
			break;
		}
	}

	/*private static void assertValueInGrid(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			Boolean screenshot) throws Exception {
		try {
		  boolean status = false;
			
			String[] nameToVerify = input.split(",");
			int i=-1;
			By pagesLocator, TransactionRowsLocator;
			
			
			String[] paths = path.split(",");
			
			pagesLocator = ActionKeyword.locatorValue(pathType, paths[0]);
			List<WebElement> pages;
			TransactionRowsLocator = ActionKeyword.locatorValue(pathType, paths[1]);
			List<WebElement> transactionRows = driver.findElements(TransactionRowsLocator);
			
			
				transactionRows = driver.findElements(TransactionRowsLocator);
				pages = driver.findElements(pagesLocator);
				do {
					i++;
					
					transactionRows = driver.findElements(TransactionRowsLocator);
					if (transactionRows.get(i).getText().equalsIgnoreCase(nameToVerify[i]))
				    {

					status= true;
				}
				pages = driver.findElements(pagesLocator);
				if (i != pages.size())
					pages.get(i).click();
				}while (i < pages.size());
				
			} catch (NoSuchElementException e) {
				Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
				throw new Exception("No Element Found to assert" + e);
			}
		
			
			       	
	}
*/
	private static void OpeningBalanceAmountDynamicHashMapBeforeReconciled(String fieldLabel, String pathType,
			String path, String input, WebDriver driver, Boolean screenshot) {
		boolean status = false;
		By locator1, locator2;
		String[] paths = path.split(",");
		locator1 = ActionKeyword.locatorValue(pathType, paths[0]);
		WebElement element1 = driver.findElement(locator1);
		String openingBal = element1.getAttribute("value").replaceAll(",", "").trim();
		System.out.println(openingBal);
		Double openingBalVal = Double.valueOf(openingBal);
		locator2 = ActionKeyword.locatorValue(pathType, paths[1]);
		WebElement element2 = driver.findElement(locator2);
		String totleMarked = element2.getAttribute("value").replaceAll(",", "");
		Double totleMarkedVal = Double.valueOf(totleMarked);
		Double afterReconciled = openingBalVal + totleMarkedVal;
		DecimalFormat df = new DecimalFormat("#.##");
		String afterReconciledVal = df.format(afterReconciled);
		System.out.println(afterReconciledVal);
		dynamicHashMap.put(fieldLabel, afterReconciledVal);

	}

	private static void unreconciledBalanceAmountDynamicHashMapBeforeReconciled(String fieldLabel, String pathType,
			String path, String input, WebDriver driver, Boolean screenshot) {
		boolean status = false;
		By locator1, locator2;
		String[] paths = path.split(",");
		locator1 = ActionKeyword.locatorValue(pathType, paths[0]);
		WebElement element1 = driver.findElement(locator1);
		String beforeReconciled = element1.getAttribute("value").replaceAll(",", "").trim();
		System.out.println(beforeReconciled);

		Double beforeReconciledVal = Double.valueOf(beforeReconciled);
		locator2 = ActionKeyword.locatorValue(pathType, paths[1]);
		WebElement element2 = driver.findElement(locator2);
		String totleMarked = element2.getAttribute("value").replaceAll(",", "");
		Double totleMarkedVal = Double.valueOf(totleMarked);
		Double afterReconciled = beforeReconciledVal - totleMarkedVal;
		DecimalFormat df = new DecimalFormat("#.##");

		String afterReconciledVal = df.format(afterReconciled);

		System.out.println(afterReconciledVal);
		dynamicHashMap.put(fieldLabel, afterReconciledVal);

	}

	private static void closingBalanceAmountDynamicHashMap(String fieldLabel, String pathType, String path,
			String input, WebDriver driver, Boolean screenshot) throws Exception {

		boolean status = false;
		By locator1, locator2;
		String[] paths = path.split(",");
		locator1 = ActionKeyword.locatorValue(pathType, paths[0]);
		WebElement element1 = driver.findElement(locator1);
		String markedTotal = element1.getAttribute("value").replaceAll(",", "").trim();
		System.out.println(markedTotal);
		Double markedVal = Double.valueOf(markedTotal);
		locator2 = ActionKeyword.locatorValue(pathType, paths[1]);
		WebElement element2 = driver.findElement(locator2);
		String openingTotal = element2.getAttribute("value").replaceAll(",", "");
		Double openingBal = Double.valueOf(openingTotal);
		Double closingTotal = markedVal + openingBal;
		String closingVal = Double.toString(closingTotal);
		System.out.println(closingVal);
		dynamicHashMap.put(fieldLabel, closingVal);

	}

	private static void assertAmountRemoveComma(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			String amount = element.getAttribute("value").replaceAll(",", "");
			if (amount.equals(input)) {
				status = true;
			} else
				System.out.print("Actual->" + element.getText() + "Expected-->" + input);
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}

	}

	public static void SumOfGridDynamicHash(String fieldLabel, String pathType, String path, WebDriver driver)
			throws Exception {
		By pagesLocator, amountRowsLocator;
		String[] paths = path.split(",");
		pagesLocator = ActionKeyword.locatorValue(pathType, paths[0]);
		List<WebElement> pages;
		BigDecimal sum = new BigDecimal(0.00);
		amountRowsLocator = ActionKeyword.locatorValue(pathType, paths[1]);
		List<WebElement> amountRows = driver.findElements(amountRowsLocator);
		if (amountRows.isEmpty())
			dynamicHashMap.put(fieldLabel, "");
		else {
			int i = -1;
			do {
				i++;
				amountRows = driver.findElements(amountRowsLocator);
				for (WebElement amountRowWebElement : amountRows) {
					Double result = Double.parseDouble(amountRowWebElement.getText().replaceAll(",", ""));
					BigDecimal value = new BigDecimal(result);
					sum = sum.add(value);
					System.out.println(sum);
				}
				pages = driver.findElements(pagesLocator);
				if (i != pages.size())
					pages.get(i).click();
			} while (i < pages.size());
			sum = sum.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			dynamicHashMap.put(fieldLabel, String.valueOf(sum));
		}
	}

	public static void SumOfGridTotalMarkedDynamicHash(String fieldLabel, String pathType, String path,
			WebDriver driver) throws Exception {
		By pagesLocator, amountRowsLocator;
		String[] paths = path.split(",");
		pagesLocator = ActionKeyword.locatorValue(pathType, paths[0]);
		List<WebElement> pages;
		BigDecimal sum = new BigDecimal(0.00);
		amountRowsLocator = ActionKeyword.locatorValue(pathType, paths[1]);
		List<WebElement> amountRows = driver.findElements(amountRowsLocator);
		if (amountRows.isEmpty())
			dynamicHashMap.put(fieldLabel, "0.00");
		else {
			int i = -1;
			do {
				i++;
				amountRows = driver.findElements(amountRowsLocator);
				for (WebElement amountRowWebElement : amountRows) {
					Double result = Double.parseDouble(amountRowWebElement.getText().replaceAll(",", ""));
					BigDecimal value = new BigDecimal(result);
					sum = sum.add(value);
					System.out.println(sum);
				}
				pages = driver.findElements(pagesLocator);
				if (i != pages.size())
					pages.get(i).click();
			} while (i < pages.size());
			sum = sum.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			dynamicHashMap.put(fieldLabel, String.valueOf(sum));
		}
	}

	public static void getDynamicHashMapDisabledField(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {

			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getAttribute("value");
			dynamicHashMap.put(fieldLabel, temp);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	public static void getDynamicHashMapDisabledAmountField(String fieldLabel, String pathType, String path,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {

			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getAttribute("value");
			dynamicHashMap.put(fieldLabel, temp.replaceAll(",", ""));
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	private static void getMaximumLengthOfTextbox(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			// String attribute;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			System.out.println(element.isDisplayed());
			String temp = element.getAttribute("maxlength");

			if (temp.equals(input)) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}

	}

	public static void assertAllocationScreenName(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean status = false;
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getText();
			String[] parts = temp.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = part1.trim().replaceAll(" +", " ");
			System.out.println(part3);

			if (part3.contains("ALLOCATION")) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	public static void getReceiptRefDynamicHashMap(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getText();
			String[] parts = temp.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = part2.trim().replaceAll(" +", " ");
			System.out.println(part3);
			dynamicHashMap.put(fieldLabel, part3);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	private static void tabOut(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);

			Actions action = new Actions(driver);
			action.click(element).build().perform();
			action.sendKeys(Keys.TAB).build().perform();
		} catch (NoSuchElementException e) {
			Log.fail("Error while selecting dropdown in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to selecting dropdown list" + e);
		}
	}

	public static void assertGetDefaultTextOfDropdown(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Select drop = new Select(element);
			if ((drop.getFirstSelectedOption().getText().trim()).equalsIgnoreCase(input)) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	public static void replaceCurrencySymbolFromClientBalance(String fieldLabel, String pathType, String path,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getText();
			String temp1 = temp.replace("£", "");
			System.out.println(temp1);
			dynamicHashMap.put(fieldLabel, temp1);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	private static void assertTextOfDisabledField(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			// String attribute;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			System.out.println(element.isDisplayed());
			String temp = element.getAttribute("value");

			if (temp.equals(input)) {
				status = true;
			} else
				System.out.println(temp + "----" + input);
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	private static void inputDropDownOption(String fieldLabel, String pathType, String path, String input,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);
			for (WebElement element : elements) {
				if (element.getText().equalsIgnoreCase(input)) {
					element.click();
					break;
				}
			}
		} catch (NoSuchElementException e) {
			Log.fail("Error while selecting dropdown in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to selecting dropdown list" + e);
		}
	}

	/**
	 * Assert text in table and non table format
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getTableTextContain(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {
		String[] paths = path.split(",");
		String tableElementPath = paths[0], nonTableElementPath = paths[1];
		Boolean status = false;
		try {
			By locator;
			locator = locatorValue(pathType, tableElementPath);
			WebElement element = driver.findElement(locator);
			if (element.getText().contains(input)) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, errorMessage, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			By locator;
			locator = locatorValue(pathType, nonTableElementPath);
			WebElement element = driver.findElement(locator);
			if (element.getText().contains(input)) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, errorMessage, driver, extentedReport, screenshot);
		} catch (Exception e) {
			Log.message("Error while asserting text in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to assert text" + e);
		}

	}

	/**
	 * To enter the text
	 * 
	 * @param locatorType
	 * @param path
	 * @param input
	 * @throws Exception
	 * 
	 */
	public static void enterTextUsingActionsClass(String fieldLabel, String pathType, String path, String input,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			element.clear();
			actions.click();
			actions.sendKeys(input);
			actions.build().perform();
			Log.message("Entered text inside " + fieldLabel + " : " + input, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while entering text in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}
	}

	/**
	 * To enter the text
	 * 
	 * @param locatorType
	 * @param path
	 * @param input
	 * @throws Exception
	 * 
	 */
	private static void enterTextBasicApproach(String fieldLabel, String pathType, String path, String input,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			element.clear();
			element.sendKeys(input);
			Log.message("Entered text inside " + fieldLabel + " : " + input, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while entering text in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}
	}

	/**
	 * To enter the text for Autocomplete text box
	 * 
	 * @param locatorType
	 * @param path
	 * @param input
	 * @throws Exception
	 * 
	 */
	public static void enterAutocompleteText(String fieldLabel, String pathType, String path, String input,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			WaitUtils.waitForelementToBeClickable(driver, element, fieldLabel + " is not clickable");
			element.click();
			element.sendKeys(input);
			element.sendKeys(Keys.ENTER);
			Log.message("Entered text inside " + fieldLabel + " : " + input, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while entering text in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}
	}

	/**
	 * To click link
	 * 
	 * @param pathType
	 * @param path
	 * @param driver
	 * @throws Exception
	 */
	public static void clickOnLink(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			// element.click();
			GenericUtils.moveToElementJS(driver, element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			Log.message("Link clicked " + fieldLabel + " : ", driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking link " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to click" + e);
		}
	}

	/**
	 * To perform click operation
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void clickOnButton(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			// Log.message("Clicked on button " + fieldLabel, driver,
			// extentedReport, screenshot);
			WaitUtils.waitForSpinner(driver);
			element.click();
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on button " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking button in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to click button " + e);
		}
	}

	/**
	 * To perform table click operation
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void clickOnTableRandomly(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);
			element.get(new Random().nextInt(element.size())).click();

			/*
			 * for(int i=0;i<element.size();i++) {
			 * if(element.get(i).getText().equals(input)) { WaitUtils.waitForElement(driver,
			 * element.get(i)); element.get(i).click(); break; } }
			 */
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on button " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking button in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to click button " + e);
		}
	}

	/**
	 * To quit browser
	 * 
	 * 
	 */
	public void closeBrowser() {
		driver.quit();
	}

	/**
	 * To select drop down box
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	private static void selectDropDownOptionByVisibleText(String fieldLabel, String pathType, String path, String input,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Select dropDown = new Select(element);
			dropDown.selectByVisibleText(input);
			Log.message("selected the dropdown " + fieldLabel + " option : " + input, driver, extentedReport,
					screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while selecting dropdown in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to selecting dropdown list" + e);
		} catch (Exception e1) {

		}
	}

	/**
	 * To select drop down box
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	private static void selectDropDownOptionByIndex(String fieldLabel, String pathType, String path, String input,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Select dropDown = new Select(element);
			dropDown.selectByIndex(Integer.parseInt(input));
			Log.message("selected the dropdown " + fieldLabel + " option : " + input, driver, extentedReport,
					screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while selecting dropdown in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to selecting dropdown list" + e);
		}
	}

	/**
	 * To select check box
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void selectCheckbox(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			if (!element.isSelected()) {
				element.click();
			}
			Log.message("selected the checkbox : " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while selecting check box in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to select check box " + e);
		}
	}

	/**
	 * To select check box
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void unSelectCheckbox(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			if (element.isSelected()) {
				element.click();
			}
			Log.message("unselected the checkbox : " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while unselecting check box in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to unselect check box " + e);
		}
	}

	/**
	 * To select right button
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void selectRadioButton(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			element.click();
			Log.message("selected the Radio Button : " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while select radio button in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to select radio button " + e);
		}
	}

	/**
	 * To perform mouse hover
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void performMouseHover(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			Log.message("Mouse Hover is performed for " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while  perform Mouse hover in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to perform Mouse hover " + e);
		}
	}

	/**
	 * To perform double click
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void performDoubleClick(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {

		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Actions action = new Actions(driver);
			action.doubleClick(element).build().perform();
			Log.message("Double click is performed for " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while  perform double click in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to perform double click " + e);
		}
	}

	/**
	 * To perform right click
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void performRightClick(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Actions action = new Actions(driver);
			action.contextClick(element).build().perform();
			Log.message("Right click is performed for " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while  perform right click in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to perform right click " + e);
		}
	}

	/**
	 * To click button
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void clickButtonJS(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {

		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			GenericUtils.moveToElementJS(driver, element);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			Log.message("Click button is performed for " + fieldLabel, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while select perform  click button in " + fieldLabel, extentedReport);
			throw new Exception("No Element Found to perform  click button " + e);
		}
	}

	/**
	 * To enter given text
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void enterTextJS(String fieldLabel, String pathType, String path, String input, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			element.click();
			element.clear();
			((JavascriptExecutor) driver).executeScript("arguments[0].value='" + input + "';", element);
			Log.message("Entered text inside " + fieldLabel + " : " + input, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while entering text in" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}
	}

	/**
	 * To assert element is displayed in page
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void isDisplayTrue(String fieldLabel, String pathType, String path, boolean type, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			if (type) {
				By locator;
				locator = ActionKeyword.locatorValue(pathType, path);
				WebElement element = driver.findElement(locator);
				WaitUtils.waitForElement(driver, element);
				Log.softAssertThat(element.isDisplayed(), validMessage,
						"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);
			} else {
				By locator;
				locator = ActionKeyword.locatorValue(pathType, path);
				WebElement element = driver.findElement(locator);
				Log.softAssertThat(!element.isDisplayed(), validMessage,
						"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);

			}
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
		}
	}

	public static void assertElementNotFoundOnThePage(String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) {
		boolean status;
		By locator;
		locator = ActionKeyword.locatorValue(pathType, path);
		List<WebElement> elements = driver.findElements(locator);
		if (elements.size() > 0) {
			status = false;
		} else {
			status = true;
		}
		Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
				extentedReport, screenshot);
	}

	/**
	 * To assert element is not displayed in page
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static boolean isDisplayFalse(String fieldLabel, String pathType, String path, boolean type,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			if (type) {
				By locator;
				locator = ActionKeyword.locatorValue(pathType, path);
				WebElement element = driver.findElement(locator);
				WaitUtils.waitForElement(driver, element);
				Log.softAssertThat(element.isDisplayed(), errorMessage,
						"Fail to achieve expected result : " + validMessage, driver, extentedReport, screenshot);
			}
		} catch (NoSuchElementException e) {
			Log.softAssertThat(true, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (Exception e) {
			Log.fail("Exception", extentedReport);
		}
		return screenshot;
	}

	/**
	 * To assert Input text is matched with actual one
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getText(String fieldLabel, String pathType, String path, String input, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			if (element.getText().equals(input)) {
				status = true;
			} else
				System.out.print("Actual->" + element.getText() + "Expected-->" + input);
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert Input text contain with actual one
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getTextContain(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Log.message("Value displayed : " + element.getText(), extentedReport);
			Log.message("Given Value : " + input, extentedReport);
			if (element.getText().contains(input)) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert Input text that is entered in text box with value attribute
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getTextofAttribute(String fieldLabel, String pathType, String path, String attribute,
			String input, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			boolean screenshot) throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			if (element.getAttribute(attribute).equals(input)) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To verify Input fields are read only and are disabled
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getTextofDisabledAttribute(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			// String attribute;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			System.out.println(element.isDisplayed());
			if (element.getAttribute("disabled").equals("true")) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To clear the text in the input field
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */

	public static void clearTextField(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			// boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			element.clear();

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert selected check box with checked attribute
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getCheckBoxCheckedAttribute(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			String value = element.getAttribute("checked");
			if (value != null) {
				status = true;
			}

			/*
			 * if ((element.getAttribute("name")).contains(input) ||
			 * element.getAttribute("checked")) { status = true; }
			 */
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert Input text contain with actual one
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getListTextContain(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = true;
			int count = 0;
			String[] nameToVerify = input.split(",");
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);
			for (int i = 0; i < element.size(); i++) {
				for (int j = 0; j < nameToVerify.length; j++) {
					WaitUtils.waitForElement(driver, element.get(i));
					if (element.get(i).getText().contains(nameToVerify[j])) {
						count++;
					}
				}

				if (count == nameToVerify.length) {
					break;
				}
			}

			if (count != nameToVerify.length) {
				status = false;
			}
			if (count == 0) {
				status = true;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert ordered Input text equals with actual one
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void validateOrderedListTextEquals(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = true;
			int count = 0;
			String[] nameToVerify = input.split(",");
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);
			for (int i = 0; i < element.size(); i++) {
				WaitUtils.waitForElement(driver, element.get(i));
				if (element.get(i).getText().equalsIgnoreCase(nameToVerify[i])) {

					count++;
					System.out.print("Element->" + element.get(i).getText() + "Expected->" + nameToVerify[i]);
				} else
					System.out.print("Element->" + element.get(i).getText() + "Expected->" + nameToVerify[i]);

			}
			if (count != nameToVerify.length) {
				status = false;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert unordered Input text equals with actual one
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void validateUnorderedListTextEquals(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = true;
			int count = 0;
			String[] nameToVerify = input.split(",");
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);
			for (int i = 0; i < element.size(); i++) {
				WaitUtils.waitForElement(driver, element.get(i));
				// if
				// (element.get(i).getText().equalsIgnoreCase(nameToVerify[i]))
				// {
				if (element.get(i).getText().contains(nameToVerify[i])) {
					count++;
				} else
					System.out.print(element.get(i).getText() + "----" + nameToVerify[i]);
			}
			if (count != nameToVerify.length) {
				status = false;
			}
			if (count == 0) {
				status = true;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (

		NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * to get dynamic value from xml
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param extentedReport
	 * @param screenshot
	 */
	public static void getDynamicValue(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) {
		By locator;
		locator = ActionKeyword.locatorValue(pathType, path);
		WebElement element = driver.findElement(locator);
		Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
				screenshot);
		dynamicValue = element.getText();
	}

	/**
	 * to get dynamic value from xml
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param extentedReport
	 * @param screenshot
	 */
	public static void getDynamicListValue(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getText();
			if (dynamicList == null) {
				dynamicList = temp;
			} else {
				dynamicList = dynamicList + "," + temp;
			}
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * assert list of array
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getListOfMultiText(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			int temp = 0;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);
			String[] names = new String[element.size()];
			String[] name = input.split(",");
			for (int i = 0; i < element.size(); i++) {//
				Log.message(element.get(i).getText());
				names[i] = element.get(i).getText();
				// status = true;
			}

			if ((name.length) == (names.length)) {
				for (int i = 0; i < name.length; i++) {
					if (names[i].equals(name[i])) {
						temp = temp + 1;
					} else {

						Log.message(names[i] + " is not equal " + name[i]);
					}
				}

				if (temp == name.length) {
					status = true;
				} else
					status = false;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To check element is displayed in page if it is present then should click it
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void IsPresentthenClick(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {

			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Boolean elementstatus = element.isDisplayed();
			if (elementstatus) {
				element.click();
				Log.softAssertThat(elementstatus, validMessage, "Fail to achieve expected result : " + errorMessage,
						driver, extentedReport, screenshot);
			} else {
				Log.softAssertThat(true, errorMessage, "Fail to achieve expected result : " + errorMessage, driver,
						extentedReport, screenshot);
			}
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
		}
	}

	/**
	 * To get cell value
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver2
	 * @param extentedReport
	 * @param screenshot
	 */
	public static void verifyTableRowValue(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {

		try {
			String[] arrInput = input.split(",");
			By locator;
			String displayedvalue = null;
			int count = 0;
			boolean status = false;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);

			for (int i = 0; i < elements.size(); i++) {
				displayedvalue = elements.get(i).getText();
				for (int j = 0; j < arrInput.length; j++) {

					if (displayedvalue.contains(arrInput[j])) {
						count++;
					}

					if (count == arrInput.length) {
						status = true;
					}
				}
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while verifying Table row value" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}

	}

	/**
	 * To assert element is enabled in page
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void isEnabledTrue(String fieldLabel, String pathType, String path, boolean type, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			if (type) {
				By locator;
				locator = ActionKeyword.locatorValue(pathType, path);
				WebElement element = driver.findElement(locator);

				Log.softAssertThat(element.isEnabled(), validMessage,
						"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);
			} else {
				By locator;
				locator = ActionKeyword.locatorValue(pathType, path);
				WebElement element = driver.findElement(locator);
				Log.softAssertThat(!element.isEnabled(), validMessage,
						"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);

			}
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
		}
	}

	/**
	 * To click on link
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void clickDynamicPath(String fieldLabel, String pathType, String path, String input, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path + input + "')]");
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			element.click();
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on " + input, driver, extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking on " + input, driver, extentedReport, true);
		}
	}

	/**
	 * To assert Input text that is entered in text box with value attribute
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getReadOnlyAttribute(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {

			boolean status = false;
			int count = 0;
			By locator;
			String[] paths = path.split(",");

			for (int i = 0; i < paths.length; i++) {
				locator = ActionKeyword.locatorValue(pathType, path);
				WebElement element = driver.findElement(locator);
				// WaitUtils.waitForElement(driver, element);
				if (element.getAttribute("readonly").equals("true")) {
					count++;
				}
			}

			if (count == paths.length) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert element is displayed in page
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void isPresentNegative(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {

			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			try {
				WebElement element = driver.findElement(locator);
				if (WaitUtils.waitForElement(driver, element)) {
					Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);
				}
			} catch (NoSuchElementException e) {
				Log.pass(validMessage, driver, extentedReport, screenshot);
			}

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
		}
	}

	/**
	 * To verify Claims Status Count
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver2
	 * @param extentedReport
	 * @param screenshot
	 */
	public static void verifyClaimsStatusCount(String fieldLabel, String pathType, String path, String input,
			String status, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			Boolean screenshot) throws Exception {

		try {
			int inputCount = Integer.parseInt(input);
			By locator;
			String displayedvalue = null;
			int count = 0;
			boolean isMatched = false;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);

			if (status.equals("Open")) {
				for (int i = 0; i < elements.size(); i++) {
					displayedvalue = elements.get(i).getText();
					if (displayedvalue.contains("Open")) {
						count++;
					}
				}

			} else {
				for (int i = 0; i < elements.size(); i++) {
					displayedvalue = elements.get(i).getText();
					if (displayedvalue.contains("Closed")) {
						count++;
					}
				}

			}

			if (count == inputCount) {
				isMatched = true;
			}

			Log.softAssertThat(isMatched, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while verifying Claims value" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}

	}

	/**
	 * To verify Policy/Quotes Count displays same in overview and policy/Quotes tab
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver2
	 * @param extentedReport
	 * @param screenshot
	 */
	public static void verifyPolicyQuotesCount(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {

		try {
			int inputCount = Integer.parseInt(input);
			By locator;
			boolean isMatched = false;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);

			Log.message("Number of policy and quotes under policy navigator : " + elements.size(), extentedReport);
			if (elements.size() == inputCount) {
				isMatched = true;
			}

			Log.softAssertThat(isMatched, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while verifying Claims value" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to enter text" + e);
		}
	}

	/**
	 * To assert Input text that is entered in text box with value attribute
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyDynamicValueList(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {

			boolean status = false;
			By locator;
			List<String> myList = new ArrayList<String>(Arrays.asList(input.split(",")));

			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);

			for (int i = 0; i < myList.size(); i++) {
				if (myList.get(i).contains(element.getAttribute("value"))) {
					status = true;
					break;
				}
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert Input text that is entered in text box with value attribute
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void isSelected(String fieldLabel, String pathType, String path, String input, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {

			boolean status = false;
			By locator;

			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);

			if (element.isSelected()) {
				status = true;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To perform mouse hover on an element
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */

	public static void mouseHoverFunctioanality(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			// boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To compare Input text with value displayed
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param attribute
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyCurrentDate(String fieldLabel, String pathType, String path, String attribute,
			String input, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			boolean screenshot) throws Exception {
		try {

			// Create object of SimpleDateFormat class and decide the format
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");

			// get current date time with Date()
			Date date = new Date();

			// Now format the date
			String date1 = dateFormat.format(date);

			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			if (element.getAttribute(attribute).equals(date1.trim())) {
				status = true;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To compare web service response with UI value displayed
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param attribute
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */

	public static void verifyUIvalueswithRestAPIvaluesforQuote(String fieldLabel, String pathType, String path,
			String input, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			boolean screenshot) throws Exception {
		try {
			HashMap<String, String> ResponseResult = new HashMap<String, String>();
			ResponseResult = GetQuote.getQuoteResults();
			boolean status = false;
			System.out.println("");
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			ResponseResult = GetQuote.getQuoteResults();
			if (ResponseResult.get("lineOfBusiness").equals(element.getAttribute("lineOfBusiness"))) {
				status = true;

			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To assert Input text contain with actual one by ignoring the case
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getTextContainIgnoreCase(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			if (element.getText().toLowerCase().contains(input.toLowerCase())) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * To click on names link
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param type
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void clickDynamicName(String fieldLabel, String pathType, String path, String input, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			// input = "AGE";
			locator = ActionKeyword.locatorValue(pathType, path + input + "']");
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			// element.click();
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			WaitUtils.waitForSpinner(driver);
			Log.message("Clicked on " + input, driver, extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking on " + input, driver, extentedReport, true);
		}
	}

	/**
	 * To get the index of the data in a table
	 * 
	 * @param pathType
	 * @param path
	 * @param input
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getRowIndexAsPerTheTableData(String pathType, String path, String input, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			index = -1;
			By locator;
			locator = locatorValue(pathType, path);
			List<WebElement> tableRowElements = driver.findElements(locator);
			for (int i = 0; i < tableRowElements.size(); i++) {
				if (tableRowElements.get(i).getText().toUpperCase().contains(input.toUpperCase())) {
					index = i;
					break;
				}
			}
			if (index < 0)
				throw new Exception(input + " data is not found in the table.");
		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking on " + input, driver, extentedReport, true);
		}
	}

	/**
	 * To click on the button as per the index selected by method
	 * getRowIndexAsPerTheTableData
	 * 
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void clickButtonAsPerRowIndex(String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			By locator;
			locator = locatorValue(pathType, path);
			List<WebElement> tableButtonElements = driver.findElements(locator);
			if (index >= 0)
				tableButtonElements.get(index).click();
		} catch (NoSuchElementException e) {
			Log.fail("Error while clicking the button from a table", driver, extentedReport, true);
		}
	}

	/**
	 * assert list is empty or not
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getListOfTextEmptyorNot(String fieldLabel, String pathType, String path, String attribute,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);

			if (attribute.equals("Empty")) {
				if ((element.size() == 0)) {
					status = true;
				}

			} else if (attribute.equals("NotEmpty")) {
				if ((element.size() > 0)) {
					status = true;
				}
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * assert to find values are sorted and listed or not in insurer premium column
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void asrtSortedValueListingorNot(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String[] paths = path.split(",");
			boolean status = false;
			int Quotedvalue = 0;
			int Referredvalue = 0;
			int QuotedCount = 0;
			int ReferredCount = 0;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, paths[0]);
			List<WebElement> element = driver.findElements(locator);
			for (int i = 0; i < element.size(); i++) {
				if (element.get(i).getText().equals("Quoted")) {
					QuotedCount++;
				} else
					ReferredCount++;
			}
			for (int i = 0; i < element.size(); i++) {
				if (element.get(i).getText().equals("Quoted")) {
					locator = ActionKeyword.locatorValue(pathType, paths[1]);
					List<WebElement> premiumValue = driver.findElements(locator);

					for (int j = 0; j < QuotedCount; j++) {
						if (Integer.parseInt(premiumValue.get(j).getText()) < Integer
								.parseInt(premiumValue.get(j + 1).getText())) {
							Quotedvalue++;

						} else {
							break;
						}
					}

				} else if (element.get(i).getText().equals("Referred")) {
					locator = ActionKeyword.locatorValue(pathType, paths[2]);
					List<WebElement> premiumValue = driver.findElements(locator);
					// int j=(premiumValue.size())-QuotedCount;
					for (int j = QuotedCount; j < premiumValue.size(); j++) {
						if (Integer.parseInt(premiumValue.get(j).getText()) < Integer
								.parseInt(premiumValue.get(j + 1).getText())) {
							Referredvalue = Referredvalue++;

						} else {
							break;
						}
					}

				}
			}
			if (QuotedCount + ReferredCount == element.size()) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * verify the quote status available
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void asrtQuoteStatus(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> element = driver.findElements(locator);
			int count = 0;
			for (int i = 0; i < element.size(); i++) {

				if (element.get(i).getText().equals("Quoted") || element.get(i).getText().equals("Declined")
						|| element.get(i).getText().equals("Referred") || element.get(i).getText().equals("Processing")
						|| element.get(i).getText().equals("Error")) {
					count++;
				}

			}
			if (count == element.size()) {
				status = true;
			}

			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Verify the sum of amount from the finance - Transactions of a policy.
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifySumAmountOfTransactions(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {

		try {
			double inputCount = Double.parseDouble(input);
			By locator;
			boolean isMatched = false;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);
			double sumOfAmount = 0;
			for (WebElement element : elements) {
				String amountText = element.getText().replaceAll("£", "");
				sumOfAmount = sumOfAmount + Double.parseDouble(amountText);
			}
			if (inputCount == sumOfAmount) {
				isMatched = true;
			}

			Log.softAssertThat(isMatched, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while verifying amount value of a policy" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to validate the amount" + e);
		}
	}

	/**
	 * Validate the row field as per the index of another row field
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyTextFromTable(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {
		try {
			double inputAmount = Double.parseDouble(input.replaceAll("£", ""));
			By locator;
			boolean isMatched = false;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);
			String output;
			if (index >= 0)
				output = elements.get(index).getText().replaceAll("£", "");
			else
				throw new Exception("Row not found in the table");
			double amount = Double.parseDouble(output);
			if (inputAmount == amount) {
				isMatched = true;
			}
			Log.softAssertThat(isMatched, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while verifying amount value of a policy" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to validate the amount" + e);
		}
	}

	/**
	 * Validate the sum of original amount in view allocation in Transactions
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifySumofAmountsInTable(String fieldLabel, String pathType, String path, String input,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, Boolean screenshot)
			throws Exception {
		try {
			double inputAmount = Double.parseDouble(input);
			By locator;
			boolean isMatched = false;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);
			double outputAmount = 0.0;
			for (WebElement element : elements)
				outputAmount = outputAmount + Double.parseDouble(element.getText());
			if (inputAmount == outputAmount) {
				isMatched = true;
			}
			Log.softAssertThat(isMatched, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Error while verifying amount value of a policy" + fieldLabel, extentedReport);
			throw new Exception("No Element Found to validate the amount" + e);
		}
	}

	/**
	 * To assert Input text value is null
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param input
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyGetTextValueNull(String fieldLabel, String pathType, String path, String validMessage,
			String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			if (element.getText().equals("")) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * This method stores the run time values in a Hashmap<String, String> where Key
	 * and value is String. Here Key is a fieldlabel name and value is stored at run
	 * time.
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getDynamicHashMap(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getText();
			dynamicHashMap.put(fieldLabel, temp);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	public static void getDynamicHashMapAsPerAttribute(String fieldLabel, String pathType, String path,
			String attribute, WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			temp = element.getAttribute(attribute);
			Log.message("Value Displayed for " + fieldLabel + " : " + temp, driver, extentedReport, screenshot);
			dynamicHashMap.put(fieldLabel, temp);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Stores the data as per the index selected by row index
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void getDynamicHashMapAsPerRowIndex(String fieldLabel, String pathType, String path, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String temp = null;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elementList = driver.findElements(locator);
			WebElement element = elementList.get(index);
			Log.message("Value Displayed for " + fieldLabel + " : " + element.getText(), driver, extentedReport,
					screenshot);
			temp = element.getText();
			dynamicHashMap.put(fieldLabel, temp);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result ", driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Store the data in dynamic Hash map where field label is key and input is
	 * value of the key.
	 * 
	 * @param fieldLabel
	 * @param input
	 * @throws Exception
	 */
	public static void getDynamicHashMapAsPerFieldLabel(String fieldLabel, String input) throws Exception {
		dynamicHashMap.put(fieldLabel, input);
	}

	/**
	 * Validate the outstanding amount with the amount in the Search Transactions.
	 * Here field label should be added in the dynamic hash map which we used as an
	 * amount to validate. And capture outstanding amount at run time.
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyAmountWithOutstandingAmount(String input, String fieldLabel, String pathType, String path,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = false;
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			WebElement element = driver.findElement(locator);
			WaitUtils.waitForElement(driver, element);
			if (element.getText().trim().equals(dynamicHashMap.get(input).trim())) {
				status = true;
			}
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);

		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * This method validates the Journal transactions reference number starts with
	 * "JN".
	 * 
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyJournalReferenceNumber(String fieldLabel, String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean status = false;
			if (dynamicHashMap.get(fieldLabel).trim().startsWith("JN"))
				status = true;
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the table accounts with the row Header values to the row index
	 * values
	 * 
	 * @param input
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyAccountsTableHeaderWithRows(String input, String fieldLabel, String pathType, String path,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		try {
			boolean status = true, dateValidation = false, outstandingAmountValidation = false,
					policyNumberValidation = false, otherValidation = false;
			String[] nameToVerify = input.split(",");
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);
			HashMap<String, String> accountsTableRow = new HashMap<>();
			for (int i = 0; i < nameToVerify.length; i++)
				accountsTableRow.put(nameToVerify[i], elements.get(i + 1).getText());
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String todayDate = simpleDateFormat.format(new Date());
			if (accountsTableRow.get("Trans Date").equalsIgnoreCase(todayDate))
				if (accountsTableRow.get("Paid Date").equalsIgnoreCase(todayDate))
					if (accountsTableRow.get("Effective Date").equalsIgnoreCase(todayDate))
						dateValidation = true;
			if (accountsTableRow.get("O/S Amount").equalsIgnoreCase("£0.00"))
				outstandingAmountValidation = true;
			if (accountsTableRow.get("Policy Number").equals(dynamicHashMap.get("Policynumber")))
				policyNumberValidation = true;
			if (accountsTableRow.get("Trans Type").equals("NB Invoice")) {
				if (accountsTableRow.get("Trans Ref").startsWith("SND"))
					if (accountsTableRow.get("Branch").equalsIgnoreCase("HeadOff"))
						if (accountsTableRow.get("Amount Due")
								.equalsIgnoreCase(dynamicHashMap.get("Premium").replaceAll("£", "")))
							otherValidation = true;
			} else if (accountsTableRow.get("Trans Type").equals("Receipt")) {
				if (accountsTableRow.get("Trans Ref").startsWith("SRP"))
					if (accountsTableRow.get("Amount Due")
							.equalsIgnoreCase("-" + dynamicHashMap.get("Premium").replaceAll("£", "")))
						otherValidation = true;
			}
			if (true == dateValidation && true == outstandingAmountValidation && true == policyNumberValidation
					&& true == otherValidation)
				status = true;
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	public static void verifySearchTransactionsTableHeaderWithRows(String input, String fieldLabel, String pathType,
			String path, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			boolean screenshot) throws Exception {
		try {
			HashMap<String, String> tableData = GenericUtils.addTableHeaderWithTableDataInHashMap(pathType, path,
					driver);
			boolean status = true, nbstatus = false;
			if (!tableData.get("Branch").equalsIgnoreCase("HeadOff"))
				status = false;
			if (!tableData.get("Account").equalsIgnoreCase(dynamicHashMap.get("Policynumber")))
				status = false;
			String todayDate = DateTimeUtility.getTodayddMMyyyy();
			if (!tableData.get("Trans Date").equalsIgnoreCase(todayDate))
				status = false;
			if (!tableData.get("Effective Date").equalsIgnoreCase(todayDate))
				status = false;
			if (!tableData.get("O/S Amount").equalsIgnoreCase("£0.00"))
				status = false;
			if (!tableData.get("Policy Number").equalsIgnoreCase(dynamicHashMap.get("Policynumber")))
				status = false;
			if (tableData.get("Trans Type").equals("NB Debit")) {
				if (tableData.get("Trans Ref").startsWith("SND")) {
					if (tableData.get("Risk Code").startsWith("Tradesman"))
						if (tableData.get("Insured").startsWith("Tradesman"))
							nbstatus = true;
				}
			} else if ((tableData.get("Trans Type").equals("Receipt"))) {
				if (tableData.get("Trans Ref").startsWith("SRP")) {
					if (tableData.get("Paid Date").equalsIgnoreCase(todayDate))
						nbstatus = true;
				}
			}
			if (!(status = true && nbstatus == true)) {
				status = false;
			}
			assertPrimarySettledValueInSearchTransactionTable(pathType, path, validMessage, errorMessage, driver,
					extentedReport, screenshot);
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (

		NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the expand table of accounts Header and rows
	 * 
	 * @param input
	 * @param fieldLabel
	 * @param pathType
	 * @param path
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void verifyAccountsExpandTableHeaderWithRows(String input, String fieldLabel, String pathType,
			String path, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			boolean screenshot) throws Exception {
		try {
			boolean status = true, userValidation = false, accountingPeriodValidation = false;
			String[] nameToVerify = input.split(",");
			By locator;
			locator = ActionKeyword.locatorValue(pathType, path);
			List<WebElement> elements = driver.findElements(locator);
			HashMap<String, String> accountsTableRow = new HashMap<>();
			for (int i = 0; i < nameToVerify.length; i++)
				accountsTableRow.put(nameToVerify[i], elements.get(i).getText());
			if (accountsTableRow.get("Accounting Period").startsWith("Period"))
				accountingPeriodValidation = true;
			if (!accountsTableRow.get("User").trim().equalsIgnoreCase(""))
				userValidation = true;
			if (true == userValidation && true == accountingPeriodValidation)
				status = true;
			Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
					extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * This method adds the data in a dynamic Hash Map as per label and its input of
	 * View Transactions in Accounts tab.
	 * 
	 * @param input          adds as a prefix in a key
	 * @param fieldLabel
	 * @param pathType       should always be XPath
	 * @param path
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void addViewTransactionDataInDynamicHashMap(String input, String fieldLabel, String pathType,
			String path, String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport,
			boolean screenshot) throws Exception {
		try {
			int duplicateKeysIndex = 1;
			HashMap<String, String> dataHashMap = new HashMap<>();
			By labelLocator, inputLocator;
			if (pathType.equalsIgnoreCase("xpath"))
				labelLocator = ActionKeyword.locatorValue(pathType, path);
			else
				throw new Exception(
						"This method can work only with the Xpath path type. As Input field creates the dynamic xpath at run time");
			String pathofInputFieldBasedonLabel = path + "//parent::div//input";
			inputLocator = ActionKeyword.locatorValue(pathType, pathofInputFieldBasedonLabel);
			List<WebElement> labelList = driver.findElements(labelLocator);
			List<WebElement> inputList = driver.findElements(inputLocator);
			if (labelList.size() != inputList.size())
				throw new Exception(
						"There is an issue with the xpath. Please check the xpath of label and input. Xpath of labelList is "
								+ path + " and Xpath of inputList is " + pathofInputFieldBasedonLabel);
			for (int i = 0; i < labelList.size(); i++) {
				String key = input + labelList.get(i).getText();
				String value = inputList.get(i).getAttribute("value");
				if (dynamicHashMap.containsKey(key) || dataHashMap.containsKey(key)) {
					key = key + duplicateKeysIndex;
					duplicateKeysIndex++;
				}
				dataHashMap.put(key, value);
			}
			dynamicHashMap.putAll(dataHashMap);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the input fields for tab client in view transaction screen for SND
	 * transactions
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionTabClientForSNDReference(String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String todayDate = simpleDateFormat.format(new Date());
			boolean clientInputData = false;
			boolean premiumMatches = false;
			boolean amountDue = false;
			boolean amountPaid = false;
			boolean balance = false;
			boolean commission = false;
			boolean netIncome = false;
			boolean paidDate = false;
			double expectedPremium = Double.parseDouble(dynamicHashMap.get("Premium").replaceAll("£", ""));
			double actualPremium = Double.parseDouble(dynamicHashMap.get("TabClientLabel-Premium"));
			double expectedOutstandingAmount = Double
					.parseDouble(dynamicHashMap.get("outstandingAmount").replaceAll("£", ""));
			double actualAmountPaid = Double.parseDouble(dynamicHashMap.get("TabClientLabel-Amount Paid"));
			double actualAmountDue = Double.parseDouble(dynamicHashMap.get("TabClientLabel-Amount Due"));
			double actualCommission = Double.parseDouble(dynamicHashMap.get("TabClientLabel-Commission"));
			if (expectedPremium - actualPremium == 0)
				premiumMatches = true;
			if (actualAmountDue == actualPremium
					+ Double.parseDouble(dynamicHashMap.get("TabClientLabel-Add-ons Inc Tax"))
					+ Double.parseDouble(dynamicHashMap.get("TabClientLabel-Fees"))
					+ Double.parseDouble(dynamicHashMap.get("TabClientLabel-Discount")))
				amountDue = true;
			if (expectedOutstandingAmount == 0.00) {
				if (actualPremium == actualAmountPaid)
					amountPaid = true;
				if (todayDate.equalsIgnoreCase(dynamicHashMap.get("TabClientLabel-Paid Date")))
					paidDate = true;
			} else if (actualAmountPaid == 0.00) {
				amountPaid = true;
				if ("".equalsIgnoreCase(dynamicHashMap.get("TabClientLabel-Paid Date")))
					paidDate = true;
			}
			if ((actualAmountDue - actualAmountPaid) == Double
					.parseDouble(dynamicHashMap.get("TabClientLabel-Balance")))
				balance = true;
			if (actualCommission == Double.parseDouble(dynamicHashMap.get("commission").replaceAll("-£", "")))
				commission = true;
			if ((actualCommission + Double.parseDouble(dynamicHashMap.get("TabClientLabel-Fees1"))
					- (Double.parseDouble(dynamicHashMap.get("TabClientLabel-Discount")
							+ Double.parseDouble(dynamicHashMap.get("TabClientLabel-Disbursements"))))) == Double
									.parseDouble(dynamicHashMap.get("TabClientLabel-Net Income")))
				netIncome = true;

			if (true == premiumMatches && true == amountDue && true == amountPaid && true == balance
					&& true == commission && true == netIncome && true == paidDate)
				clientInputData = true;
			Log.softAssertThat(clientInputData, validMessage, "Fail to achieve expected result : " + errorMessage,
					driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * /** Validate the input fields for tab amounts in view transaction screen for
	 * SRP transactions
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionTabAmountsForSRPReference(String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean tabAmountsData = false;
			boolean receiptAmountData = false;
			boolean totalData = false;
			boolean amountAllocatedData = false;
			boolean balance = false;
			double expectedPremium = Double.parseDouble(dynamicHashMap.get("Premium").replaceAll("£", ""));
			double actualPremium = Double.parseDouble(dynamicHashMap.get("TabAmountsLabel-Receipt Amount"));
			double actualWriteOff = Double.parseDouble(dynamicHashMap.get("TabAmountsLabel-Write Off"));
			double actualTotal = Double.parseDouble(dynamicHashMap.get("TabAmountsLabel-Total"));
			double actualAmtAllocated = Double.parseDouble(dynamicHashMap.get("TabAmountsLabel-Amt. Allocated"));
			double actualBalance = Double.parseDouble(dynamicHashMap.get("TabAmountsLabel-Balance"));
			double expectedAllocatedAmount = Double.parseDouble(dynamicHashMap.get("AllocatedAmount").replace("-", ""));

			if (expectedPremium - actualPremium == 0)
				receiptAmountData = true;

			if ((actualPremium + actualWriteOff) == actualTotal)
				totalData = true;

			if ((actualTotal - actualAmtAllocated) == actualBalance)
				balance = true;

			if (expectedAllocatedAmount == actualAmtAllocated)
				amountAllocatedData = true;

			if (true == receiptAmountData && true == totalData && true == balance && true == amountAllocatedData)
				tabAmountsData = true;

			Log.softAssertThat(tabAmountsData, validMessage, "Fail to achieve expected result : " + errorMessage,
					driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * validate the input fields of tab Insurer in View transaction for SND
	 * transactions
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionTabInsurerForSNDReference(String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			// TODO Currently hard coded. Need to be fixed.
			dynamicHashMap.put("InsurerID / Name", "Wesley - Wesley");
			boolean insurerIdName = false;
			boolean amountDue = false;
			boolean balance = false;
			boolean insurerAddOnInputData = false;

			double expectedAmountDue = Double.parseDouble(dynamicHashMap.get("TabClientLabel-Amount Due"))
					- Double.parseDouble(dynamicHashMap.get("TabClientLabel-Commission"));
			double actualAmountDue = Double.parseDouble(dynamicHashMap.get("TabInsurerLabel-Amount Due"));
			if (expectedAmountDue - actualAmountDue == 0)
				amountDue = true;
			double expectedBalance = actualAmountDue
					- Double.parseDouble(dynamicHashMap.get("TabInsurerLabel-Amount Paid"));
			double actualBalance = Double.parseDouble(dynamicHashMap.get("TabInsurerLabel-Balance"));
			if (dynamicHashMap.get("TabInsurerLabel-InsurerID / Name").equals(dynamicHashMap.get("InsurerID / Name")))
				insurerIdName = true;
			if (expectedBalance - actualBalance == 0)
				balance = true;
			if (true == amountDue && true == balance && true == insurerIdName)
				insurerAddOnInputData = true;
			Log.softAssertThat(insurerAddOnInputData, validMessage, "Fail to achieve expected result : " + errorMessage,
					driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the input fields of the tab Income history in view transaction for
	 * SND reference.
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionTabIncomeHistoryForSNDReference(String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {

			boolean unearned = false;
			boolean earned = false;
			boolean incomeHistoryInputData = false;

			double actualUnearned = Double.parseDouble(dynamicHashMap.get("TabIncomeHistoryLabel-Unearned"));
			double actualEarned = Double.parseDouble(dynamicHashMap.get("TabIncomeHistoryLabel-Earned"));

			double outstandingAmount = Double.parseDouble(dynamicHashMap.get("outstandingAmount").replaceAll("£", ""));
			double commission = Double.parseDouble(dynamicHashMap.get("TabClientLabel-Commission"));
			if (outstandingAmount == 0.00) {
				if (actualUnearned == 0.00)
					unearned = true;
				if (actualEarned == commission)
					earned = true;
			} else {
				if (actualUnearned == commission)
					unearned = true;
				if (actualEarned == 0.00)
					earned = true;
			}
			if (true == earned && true == unearned)
				incomeHistoryInputData = true;
			Log.softAssertThat(incomeHistoryInputData, validMessage,
					"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the input fields of tab Details which are in View Transaction for
	 * the SND transactions
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionTabDetailsForSNDReference(String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			// TODO currently hard coded the branch
			dynamicHashMap.put("Branch", "Head Office");
			boolean transactionType = false;
			boolean transactionDate = false;
			boolean effectiveDate = false;
			boolean branch = false;
			boolean riskCode = false;
			boolean policyReferenceNumber = false;
			boolean insured = false;
			boolean user = false;

			boolean detailsInputData = false;

			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String todayDate = simpleDateFormat.format(new Date());

			if (todayDate.equalsIgnoreCase(dynamicHashMap.get("TabDetailsLabel-Trans. Date")))
				transactionDate = true;

			if (todayDate.equalsIgnoreCase(dynamicHashMap.get("TabDetailsLabel-Effective Date")))
				effectiveDate = true;

			if (dynamicHashMap.get("TabDetailsLabel-Trans. Type")
					.equalsIgnoreCase(dynamicHashMap.get("Transaction Type")))
				transactionType = true;

			if (dynamicHashMap.get("TabDetailsLabel-Branch").equalsIgnoreCase(dynamicHashMap.get("Branch")))
				branch = true;

			if (dynamicHashMap.get("TabDetailsLabel-Risk Code").equalsIgnoreCase(dynamicHashMap.get("SelectProduct")))
				riskCode = true;

			if (dynamicHashMap.get("TabDetailsLabel-Policy Ref").trim()
					.equalsIgnoreCase(dynamicHashMap.get("Policynumber")))
				policyReferenceNumber = true;

			if (dynamicHashMap.get("TabDetailsLabel-Insured").equalsIgnoreCase(dynamicHashMap.get("Company Name")))
				insured = true;

			if (dynamicHashMap.get("TabDetailsLabel-User").equalsIgnoreCase(dynamicHashMap.get("Username")))
				user = true;

			if (true == transactionDate && true == transactionType && true == effectiveDate && true == branch
					&& true == riskCode && true == policyReferenceNumber && true == insured && true == user)
				detailsInputData = true;

			Log.softAssertThat(detailsInputData, validMessage, "Fail to achieve expected result : " + errorMessage,
					driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}

	}

	/**
	 * Validate the input fields of tab Details which are in View Transaction for
	 * the SRP transactions
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionTabDetailsForSRPReference(String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			// TODO currently hard coded the branch
			dynamicHashMap.put("Branch", "Head Office");
			dynamicHashMap.put("Media Type", "Cash");
			dynamicHashMap.put("Bank Account", "BANKSUSPAC");
			boolean transactionType = false;
			boolean transactionDate = false;
			boolean effectiveDate = false;
			boolean branch = false;
			boolean user = false;
			boolean accountingPeriod = false;
			boolean detailsInputData = false;
			boolean mediaType = false;
			boolean bankAccount = false;

			String pattern = "dd/MM/yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String todayDate = simpleDateFormat.format(new Date());

			if (todayDate.equalsIgnoreCase(dynamicHashMap.get("TabDetailsLabel-Trans. Date")))
				transactionDate = true;

			if (todayDate.equalsIgnoreCase(dynamicHashMap.get("TabDetailsLabel-Effect. Date")))
				effectiveDate = true;

			if (dynamicHashMap.get("TabDetailsLabel-Trans. Type").trim().equalsIgnoreCase("Receipt"))
				transactionType = true;

			if (dynamicHashMap.get("TabDetailsLabel-A/c Period").trim() != "")
				accountingPeriod = true;

			if (dynamicHashMap.get("TabDetailsLabel-Branch").trim().equalsIgnoreCase(dynamicHashMap.get("Branch")))
				branch = true;

			if (dynamicHashMap.get("TabDetailsLabel-Media Type").trim()
					.equalsIgnoreCase(dynamicHashMap.get("Media Type")))
				mediaType = true;

			if (dynamicHashMap.get("TabDetailsLabel-Bank Account").trim()
					.equalsIgnoreCase(dynamicHashMap.get("Bank Account")))
				bankAccount = true;

			if (dynamicHashMap.get("TabDetailsLabel-User").equalsIgnoreCase(dynamicHashMap.get("Username")))
				user = true;

			if (true == transactionType && true == transactionDate && true == effectiveDate && true == accountingPeriod
					&& true == branch && true == mediaType && true == bankAccount && true == user)
				detailsInputData = true;

			Log.softAssertThat(detailsInputData, validMessage, "Fail to achieve expected result : " + errorMessage,
					driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the data of the View Transaction main input fields which are common
	 * in all 4 tabs.
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionForSNDReference(String validMessage, String errorMessage, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean transactionReference = false;
			boolean clientSAIDName = false;
			boolean viewTransactionInputData = false;

			if (dynamicHashMap.get("ViewTransactionsMain-Transaction Reference")
					.equalsIgnoreCase((dynamicHashMap.get("Transaction Reference").concat(" / GBP"))))
				transactionReference = true;

			if (dynamicHashMap.get("ViewTransactionsMain-Client/SA ID Name").trim().equalsIgnoreCase(
					(dynamicHashMap.get("Company Code").concat("-").concat(dynamicHashMap.get("Company Code")).trim())))
				clientSAIDName = true;

			if (true == transactionReference && true == clientSAIDName)
				viewTransactionInputData = true;

			Log.softAssertThat(viewTransactionInputData, validMessage,
					"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	/**
	 * Validate the data of the View Transaction SRP main input fields which are
	 * common in all 4 tabs.
	 * 
	 * @param validMessage
	 * @param errorMessage
	 * @param driver
	 * @param extentedReport
	 * @param screenshot
	 * @throws Exception
	 */
	public static void assertViewTransactionForSRPReference(String validMessage, String errorMessage, WebDriver driver,
			ExtentTest extentedReport, boolean screenshot) throws Exception {
		try {
			boolean transactionReference = false;
			boolean clientSAIDName = false;
			boolean viewTransactionInputData = false;

			if (dynamicHashMap.get("ViewTransactionsMain-Transaction Reference")
					.equalsIgnoreCase((dynamicHashMap.get("Transaction Reference").concat(" / GBP"))))
				transactionReference = true;

			if (dynamicHashMap.get("ViewTransactionsMain-Client/SA ID Name").replaceAll(" ", "").equalsIgnoreCase(
					(dynamicHashMap.get("Company Code").concat("-").concat(dynamicHashMap.get("Company Name")).trim())))
				clientSAIDName = true;

			if (true == transactionReference && true == clientSAIDName)
				viewTransactionInputData = true;

			Log.softAssertThat(viewTransactionInputData, validMessage,
					"Fail to achieve expected result : " + errorMessage, driver, extentedReport, screenshot);
		} catch (NoSuchElementException e) {
			Log.fail("Fail to achieve expected result : " + errorMessage, driver, extentedReport, true);
			throw new Exception("No Element Found to assert" + e);
		}
	}

	private static void assertInsurerPreviewPane(String pathType, String path, String validMessage, String errorMessage,
			WebDriver driver, ExtentTest extentedReport, boolean screenshot) throws Exception {
		boolean accountId = false, account = false, premium = false, comm = false, netAmount = false,
				sett_due_date = false, balance = false, status = false;
		String paths[] = path.split(",");
		By tableHeaderLocator, tableValuesLocator;
		tableHeaderLocator = ActionKeyword.locatorValue(pathType, paths[0]);
		tableValuesLocator = ActionKeyword.locatorValue(pathType, paths[1]);
		List<WebElement> listOfHeaderElements = driver.findElements(tableHeaderLocator);
		List<WebElement> listOfvaluesElements = driver.findElements(tableValuesLocator);
		if (listOfHeaderElements.size() != listOfvaluesElements.size())
			throw new Exception(
					"Table Header data and column Data size are not equal. Please validate your locator or contact developer");
		HashMap<String, String> previewPaneTableData = new HashMap<String, String>();
		for (int i = 0; i < listOfHeaderElements.size(); i++) {
			previewPaneTableData.put(listOfHeaderElements.get(i).getText(), listOfvaluesElements.get(i).getText());
		}
		String actualPremium = previewPaneTableData.get("Premium").trim();
		String actualComm = previewPaneTableData.get("Comm.").trim();
		String actualNetAmount = previewPaneTableData.get("Net Amount").trim();
		String actualBalance = previewPaneTableData.get("Balance").trim();
		String actualAmountPaid = previewPaneTableData.get("Amount Paid").trim();
		if (previewPaneTableData.get("Account ID").trim().equals(dynamicHashMap.get("Fetch Insurer")))
			accountId = true;
		if (previewPaneTableData.get("Account").trim().equals(dynamicHashMap.get("Fetch Insurer")))
			account = true;
		String expectedPremium = dynamicHashMap.get("TabClientLabel-Premium");
		if ((dynamicHashMap.get("ViewTransactionsMain-Transaction Reference").startsWith("SND"))) {
			if (actualPremium.equals("-" + expectedPremium)) {
				premium = true;
			}
		} else {
			if (actualPremium.equals(expectedPremium)) {
				premium = true;
			}
		}
		if (actualComm.equals(dynamicHashMap.get("TabClientLabel-Commission")))
			comm = true;
		double expectedNetAmount = Double.parseDouble(actualPremium) + Double.parseDouble(actualComm);
		if (expectedNetAmount == Double.parseDouble(actualNetAmount))
			netAmount = true;
		if (previewPaneTableData.get("Sett. Due Date").equals(DateTimeUtility.getTodayddMMyyyy())) {
			sett_due_date = true;
		}
		double expectedBalance = Double.parseDouble(actualNetAmount) - Double.parseDouble(actualAmountPaid);
		if (expectedBalance == Double.parseDouble(actualBalance))
			balance = true;
		if (balance && sett_due_date && netAmount && comm && premium && account && accountId) {
			status = true;
		}
		Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
				extentedReport, screenshot);
	}

	private static void assertCommissionPreviewPaneForInvoicePolicy(String input, String pathType, String path,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		boolean status = false;
		String paths[] = path.split(",");
		String expectedAccId = input.split(",")[0];
		String expectedAccount = input.split(",")[1];
		By tableHeaderLocator, tableValuesLocator, tableAllocationLink;
		tableHeaderLocator = ActionKeyword.locatorValue(pathType, paths[0]);
		tableValuesLocator = ActionKeyword.locatorValue(pathType, paths[1]);
		tableAllocationLink = ActionKeyword.locatorValue(pathType, paths[1] + ">a");
		List<WebElement> listOfHeaderElements = driver.findElements(tableHeaderLocator);
		List<WebElement> listOfvaluesElements = driver.findElements(tableValuesLocator);
		List<WebElement> listOfLinkAllocationElements = driver.findElements(tableAllocationLink);
		for (WebElement element : listOfLinkAllocationElements) {
			if (!element.getText().equals("Allocation")) {
				listOfLinkAllocationElements.remove(element);
			}
		}
		if (listOfLinkAllocationElements.size() < 1) {
			throw new Exception("Their is no link of Allocation in a commission preview pane table");
		}

		if (listOfHeaderElements.size() != listOfvaluesElements.size())
			throw new Exception(
					"Table Header data and column Data size are not equal. Please validate your locator or contact developer");

		HashMap<String, String> previewPaneTableData = new HashMap<String, String>();
		for (int i = 0; i < listOfHeaderElements.size(); i++) {
			previewPaneTableData.put(listOfHeaderElements.get(i).getText(), listOfvaluesElements.get(i).getText());
		}
		String actualAccId = previewPaneTableData.get("Acc. ID").trim();
		String actualDate = previewPaneTableData.get("Date").trim();
		String actualAccount = previewPaneTableData.get("Account").trim();
		String actualIncome = previewPaneTableData.get("Income").trim();
		String actualAmountPaid = previewPaneTableData.get("Amount Paid").trim();
		String actualBalance = previewPaneTableData.get("Balance").trim();
		status = validateCommissionPreviewPaneAsPerInputFields(actualAccId, expectedAccId, actualDate, actualAccount,
				expectedAccount, dynamicHashMap.get("ViewTransactionsMain-Transaction Reference"),
				dynamicHashMap.get("TabClientLabel-Commission"), actualIncome, actualAmountPaid, actualBalance);
		Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
				extentedReport, screenshot);
	}

	private static void assertCommissionPreviewPaneForPayNowPolicy(String input, String pathType, String path,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		boolean status = false;
		String expectedAccId = input.split(",")[0];
		String expectedAccount = input.split(",")[1];
		HashMap<String, String> previewPaneTableData = GenericUtils.addTableHeaderWithTableDataInHashMap(pathType, path,
				driver);
		String actualAccId = previewPaneTableData.get("Acc. ID").trim();
		String actualDate = previewPaneTableData.get("Date").trim();
		String actualAccount = previewPaneTableData.get("Account").trim();
		String actualIncome = previewPaneTableData.get("Income").trim();
		String actualAmountPaid = previewPaneTableData.get("Amount Paid").trim();
		String actualBalance = previewPaneTableData.get("Balance").trim();
		String actualJournalReference = previewPaneTableData.get("Journal Ref").trim();
		if (dynamicHashMap.containsKey("JournalTransactionReference")) {
			String expectedJournalReference = dynamicHashMap.get("JournalTransactionReference");
			status = validateCommissionPreviewPaneAsPerInputFields(actualAccId, expectedAccId, actualDate,
					actualAccount, expectedAccount, dynamicHashMap.get("ViewTransactionsMain-Transaction Reference"),
					dynamicHashMap.get("TabClientLabel-Commission"), actualIncome, actualAmountPaid, actualBalance,
					actualJournalReference, expectedJournalReference);
		} else {
			status = validateCommissionPreviewPaneAsPerInputFields(actualAccId, expectedAccId, actualDate,
					actualAccount, expectedAccount, dynamicHashMap.get("ViewTransactionsMain-Transaction Reference"),
					dynamicHashMap.get("TabClientLabel-Commission"), actualIncome, actualAmountPaid, actualBalance);
		}
		Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
				extentedReport, screenshot);
	}

	private static boolean validateCommissionPreviewPaneAsPerInputFields(String actualAccId, String expectedAccId,
			String actualDate, String actualAccount, String expectedAccount, String transactionReferenceNumber,
			String expectedCommission, String actualIncome, String actualAmountPaid, String actualBalance) {

		boolean status = true;

		if (!actualAccId.equals(expectedAccId))
			status = false;

		if (!actualDate.equals(DateTimeUtility.getTodayddMMyyyy()))
			status = false;

		if (!actualAccount.equals(expectedAccount))
			status = false;

		if (transactionReferenceNumber.startsWith("SND")) {
			if (!actualIncome.equals("-" + expectedCommission))
				status = false;
		} else {
			if (!actualIncome.equals(expectedCommission)) {
				status = false;
			}
		}

		double expectedBalance = Double.parseDouble(actualIncome) - Double.parseDouble(actualAmountPaid);
		if (expectedBalance != Double.parseDouble(actualBalance))
			status = false;

		return status;
	}

	private static boolean validateCommissionPreviewPaneAsPerInputFields(String actualAccId, String expectedAccId,
			String actualDate, String actualAccount, String expectedAccount, String transactionReferenceNumber,
			String expectedCommission, String actualIncome, String actualAmountPaid, String actualBalance,
			String actualJournalRefNumber, String expectedJournalRefNumber) {

		boolean status = true;

		if (!actualAccId.equals(expectedAccId))
			status = false;

		if (!actualDate.equals(DateTimeUtility.getTodayddMMyyyy()))
			status = false;

		if (!actualAccount.equals(expectedAccount))
			status = false;

		if (transactionReferenceNumber.startsWith("SND")) {
			if (!actualIncome.equals("-" + expectedCommission))
				status = false;
		} else {
			if (!actualIncome.equals(expectedCommission)) {
				status = false;
			}
		}

		double expectedBalance = Double.parseDouble(actualIncome) - Double.parseDouble(actualAmountPaid);
		if (expectedBalance != Double.parseDouble(actualBalance))
			status = false;

		if (!expectedJournalRefNumber.equals(actualJournalRefNumber))
			status = false;

		return status;
	}

	private static void assertPrimarySettledValueInSearchTransactionTable(String pathType, String path,
			String validMessage, String errorMessage, WebDriver driver, ExtentTest extentedReport, boolean screenshot)
			throws Exception {
		boolean status = false;
		HashMap<String, String> tableData = GenericUtils.addTableHeaderWithTableDataInHashMap(pathType, path, driver);
		String outstandingAmount = tableData.get("O/S Amount");
		String primarySettled = tableData.get("Primary Settled");
		if (!outstandingAmount.equals("0.00") && primarySettled.equalsIgnoreCase("NO")) {
			status = true;
		}
		if (outstandingAmount.equals("0.00") && primarySettled.equalsIgnoreCase("YES")) {
			status = true;
		}
		Log.softAssertThat(status, validMessage, "Fail to achieve expected result : " + errorMessage, driver,
				extentedReport, screenshot);
	}
}