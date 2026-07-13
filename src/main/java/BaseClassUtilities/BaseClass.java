package BaseClassUtilities;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.CrmLoginPomPage;
import GenericUtilities.DataBaseUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.UtilityObjectClass;
import GenericUtilities.WebDriverUtilities;

public class BaseClass {
	public DataBaseUtility dbutil = new DataBaseUtility();
	public PropertyFileUtility  putil = new PropertyFileUtility();
	public WebDriverUtilities wutil = new WebDriverUtilities();
	public WebDriver driver  = null;
	public static WebDriver sdriver = null;
	
	@BeforeSuite(alwaysRun=true)
	public void connectToDB() throws SQLException {
		Reporter.log("connecting to database",true);
		dbutil.connectToDB();
	}
	@BeforeTest(alwaysRun=true)
	public void ConfigParellelExe() {
		Reporter.log("configure Parellel Exe",true);
	}
	@Parameters("browser")
	@BeforeClass(alwaysRun=true)
	public void launchTheBrowser(String browser) throws IOException {
		Reporter.log("launching the Browser",true);
		//String browser = putil.fetchdatafrompropertyfile("Browser");
		
		if(browser.equals("chrome"))
			driver = new ChromeDriver();
		else if(browser.equals("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equals("edge"))
			driver = new EdgeDriver();
		else 
			driver = new ChromeDriver();
		sdriver = driver;
		UtilityObjectClass.setDriver(driver);
	}
	@BeforeMethod(alwaysRun=true)
	public void login() throws IOException {
		Reporter.log("login to VTIGER",true);
		
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		CrmLoginPomPage l = new CrmLoginPomPage(driver);
		wutil.maximizeTheWindow(driver);
		wutil.navigateToAnApp(driver, url);
		wutil.waitForAnElement(driver, timeouts);
		l.login(username, password);
	}
	@AfterMethod(alwaysRun=true)
	public void logout() {
		Reporter.log("logout from VTIGER",true);
		CrmHomePomPage home = new CrmHomePomPage(driver);
		wutil.mouseHoverOnElement(driver, home.getadminicon());
		home.getsignout();
	}
     @AfterClass(alwaysRun=true)
    public void closeTheBrowser() {
	Reporter.log("closing the browser",true);
	wutil.quitTheBrowser(driver);
  }
    @BeforeTest(alwaysRun=true)
   public void closeParellelExe() {
	Reporter.log("close config of parellel exe",true);
   }
   @AfterSuite(alwaysRun=true)
   public void disconnectWithDB() throws SQLException {
	   Reporter.log("disconnecting with database",true);
	   dbutil.DisconnectWithDB();
   }
}
