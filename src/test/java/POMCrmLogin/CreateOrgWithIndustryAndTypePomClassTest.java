package POMCrmLogin;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import CrmApplicationPomPages.CreateOrganizationPomPage;
import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.CrmLoginPomPage;
import CrmApplicationPomPages.OrgInfoPomPage;
import CrmApplicationPomPages.OrganizationPomPage;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtilities;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtilities;

public class CreateOrgWithIndustryAndTypePomClassTest {
	@Test
	public void createorgwithindustryandtype() throws InterruptedException, IOException {
		//Feching data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchdatafrompropertyfile("Browser");
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		
		//Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		
		//Fetching data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		 String orgname = exutil.fetchDataFromExcel("Organization", 4, 2)+randomint;
		String industry = exutil.fetchDataFromExcel("Organization", 4, 3);
		String type = exutil.fetchDataFromExcel("Organization", 4, 4);
				
		//launch the browser
		WebDriver driver = null;
		
		if(browser.equals("chrome"))
			driver = new ChromeDriver();
		else if(browser.equals("firefox"))
			driver = new FirefoxDriver();
		else if(browser.equals("edge"))
			driver = new EdgeDriver();
		else 
			driver = new ChromeDriver();
		
		WebDriverUtilities wutil = new WebDriverUtilities();
		
		//maximize the window
		wutil.maximizeTheWindow(driver);
		
		//implicit wait
		wutil.waitForAnElement(driver, timeouts);
		
		//navigate to the application
		wutil.navigateToAnApp(driver, url);
		
		//Login
		CrmLoginPomPage l = new CrmLoginPomPage(driver);
		l.login(username, password);
		
		CrmHomePomPage home = new CrmHomePomPage(driver);
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"),"validating the home page");
		
		//identify organizations link and click on it
				home.getorganizationtab();
		
		//identify org plus icon and click on it
		OrganizationPomPage orgplucicon =  new OrganizationPomPage(driver);
		orgplucicon.getOrgPlusIcon();
		
		//identify org name TF and pass text in it
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgnameTF(orgname);
		
		//identify industry dropdown and the option from it
		WebElement indDD = createorg.getIndustryDD();
		wutil.selectDD_OptionUsingValue(indDD, industry);
		
		//identify type dropdown and select option from it
		WebElement typeDD = createorg.getTypeDD();
		wutil.selectDD_OptionUsingValue(typeDD, type);
		
		//identify save btn and click on it
		createorg.getSaveBtn();
		
		//identify org info page header and verify org name
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verifyorg = orginfo.getVerifyOrgName();
		Assert.assertTrue(verifyorg.contains(orgname),"validating the org name");
		
		//verify industry
		String verifyind = orginfo.getVerifyIndustry();
		Assert.assertTrue(verifyind.contains(industry),"validating the industry DD");
		
		//verify type
				String verifytype = orginfo.getVerifytype();
				Assert.assertTrue(verifytype.contains(type),"validating the type DD");
				
		//identify org link and click on it
				home.getorganizationtab();
		
		//delete the org created by clicking on delete link
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		
		//handle alert popup
	   wutil.switchToAlertClickOk(driver);
	    
	    //mouseover on admin icon
	     WebElement admin = home.getadminicon();
	     wutil.mouseHoverOnElement(driver, admin);
	     
	    //click on signout
	     home.getsignout();
	    Thread.sleep(5000);
	    
	    //close the window
	    wutil.quitTheBrowser(driver);
	    exutil.CloseTheExcel();
	    soft.assertAll();
}
}
