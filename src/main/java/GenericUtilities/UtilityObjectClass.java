package GenericUtilities;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityObjectClass {

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public static ExtentTest getTest() {
		return test.get();
	}
	public static void setTest(ExtentTest actualtest) {
		test.set(actualtest); 
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void setDriver(WebDriver actualdriver) {
		driver.set(actualdriver); 
	}
}
