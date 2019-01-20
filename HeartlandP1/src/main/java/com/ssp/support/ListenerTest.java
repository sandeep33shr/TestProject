package com.ssp.support;

import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ListenerTest implements ITestListener {
	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult arg0) {
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		BaseTest baseTest = new BaseTest();
		String tcId = arg0.getName();
		String description = arg0.getMethod().getDescription();
		String methodsDependsOn = Arrays.toString(arg0.getMethod().getMethodsDependedUpon());
		ExtentTest test = baseTest.extent.startTest(tcId, description);
		test.log(LogStatus.SKIP, "Skip the test case because any of the method on which it depends " + methodsDependsOn
				+ " is/are fail");
		baseTest.extent.endTest(test);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}
}
