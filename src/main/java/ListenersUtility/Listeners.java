package ListenersUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import BaseClassUtilities.BaseClass;
import GenericUtilities.UtilityObjectClass;

public class Listeners implements ISuiteListener, ITestListener {
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite Excecution Started-Report Configuration", true);
		String timestamp = new Date().toString().replace("", "_").replace(":", "_");
	ExtentSparkReporter spark = new  ExtentSparkReporter("./AdvanceReports/Vtiger"+timestamp+".html");
	spark.config().setDocumentTitle("Vtiger_CRM");
	spark.config().setReportName("VtigerConOrg_Module");
	spark.config().setTheme(Theme.DARK);
	report = new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("OS","Windows-11");
	report.setSystemInfo("Browser", "Chrome-149");
	report.setSystemInfo("Browser", "Edge-150");
	report.setSystemInfo("Browser", "Firefox-152");
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace("", "_").replace(":", "_");
		test = report.createTest(testname+timestamp);
		UtilityObjectClass.setTest(test);
		Reporter.log(testname + ":Test Execution Started", true);
		test.log(Status.INFO,testname+":Test Execution Started ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + ":Test Execution Success", true);
	test.log(Status.INFO,testname+":Test Execution Success ");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace("", "_").replace(":", "_");
		test.log(Status.INFO,testname+":Test Execution Failed-ScreenShot ");
	Reporter.log(testname + ":Test Excecution Failed-ScreenShot", true);
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String("./Screenshot/"+testname+"_"+timestamp+".png",src);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + ":Test Execution Skipped", true);
		test.log(Status.INFO,testname+":Test Execution Skipped ");
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Suite Execution Finished-Report Backup", true);
		test.log(Status.INFO,":Suite Execution Finished-Report Backup");
		report.flush();
	}

}
