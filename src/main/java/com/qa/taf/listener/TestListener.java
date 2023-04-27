package com.qa.taf.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.taf.base.DriverManager;
import com.qa.taf.reuse.ReusableComponent;

public class TestListener extends DriverManager implements ITestListener, ISuiteListener {

	//private static String messageBody;

	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onStart(suite);
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		ISuiteListener.super.onFinish(suite);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
		test = report.createTest(context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test.log(Status.INFO, () -> result.getName().toUpperCase() + " Test Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		ReusableComponent.waitForSomeTime();
		var passTCBase64SnapShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		test.pass(result.getName().toUpperCase() + " Test Passed",
				MediaEntityBuilder.createScreenCaptureFromBase64String(passTCBase64SnapShot).build());
		var snapToAttach = captureScreenShot();
		testNGReporterUpdate(result.getName().toUpperCase() + " Test Passed", snapToAttach);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		ReusableComponent.waitForSomeTime();
		var failTCBase64SnapShot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
		test.fail(result.getName().toUpperCase() + " Test Failed",
				MediaEntityBuilder.createScreenCaptureFromBase64String(failTCBase64SnapShot).build());
		var snapToAttach = captureScreenShot();
		testNGReporterUpdate(result.getName().toUpperCase() + " Test Failed", snapToAttach);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, () -> result.getName().toUpperCase() + " Test Skipped. As the RUN MODE is set to N");
	}

	private String captureScreenShot() {
		// TODO Auto-generated method stub
		return null;
	}

	private void testNGReporterUpdate(String testStatus, String screenShot) {
		Reporter.log("<br>");
		Reporter.log(testStatus);
		Reporter.log("<br>");
		Reporter.log("<a target='_blank' href='" + screenShot + "'><img src='" + screenShot
				+ "' height='100' width='100' /></a>");
	}
}
