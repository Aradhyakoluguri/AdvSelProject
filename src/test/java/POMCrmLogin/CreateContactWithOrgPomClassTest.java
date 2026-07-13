package POMCrmLogin;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import CrmApplicationPomPages.ContactInfoPomPage;
import CrmApplicationPomPages.ContactsPomPage;
import CrmApplicationPomPages.CreateContactPomPage;
import CrmApplicationPomPages.CreateOrganizationPomPage;
import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.CrmLoginPomPage;
import CrmApplicationPomPages.OrgInfoPomPage;
import CrmApplicationPomPages.OrganizationPomPage;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtilities;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtilities;

public class CreateContactWithOrgPomClassTest {
	@Test
	public void CreateContactUsingOrganizationName() throws InterruptedException, IOException {
		
		//Fetching data from Property File
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchdatafrompropertyfile("Browser");
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		
		//Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		
		//Fetching data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		 String contactname = exutil.fetchDataFromExcel("Contacts", 7, 2)+randomint;
		 String contorgname = exutil.fetchDataFromExcel("Contacts", 7, 3)+randomint;
		 
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
		wutil.waitForAnElement(driver,timeouts);
		
		
		//navigate to the application
		wutil.navigateToAnApp(driver, url);
		
		//Login
		CrmLoginPomPage login = new CrmLoginPomPage(driver);
		login.login(username, password);
		
		CrmHomePomPage home = new CrmHomePomPage(driver);
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"),"validating the home page");
		
		//click on organizations link
		home.getorganizationtab();
	    
	  //click on plus icon
		OrganizationPomPage orgplusicon = new OrganizationPomPage(driver);
		orgplusicon.getOrgPlusIcon();
	  		
	  	//enter organization name in TF
		CreateOrganizationPomPage createorgname = new CreateOrganizationPomPage(driver);
		createorgname.getOrgnameTF(contorgname);
		    
		    //click on save button
		createorgname.getSaveBtn();
		    
		  //identify org info page header and verify org name
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
			String verifyorg = orginfo.getVerifyOrgName();
			Assert.assertTrue(verifyorg.contains(contorgname),"validaing the orgname for organization");
			
			//click on contacts link
			home.getcontactstab();
	  		
	  	//click on plus icon
			ContactsPomPage conplusicon = new ContactsPomPage(driver);
			conplusicon.getContPlucIcon();
			
	    //identify contact last name TF and pass text into it
			CreateContactPomPage createconname = new CreateContactPomPage(driver);
			createconname.getConLastnameTF(contactname);
		
			//click on plus icon beside the organization name TF
			createconname.getConOrgNameTFPlusIcon();
			   
			   //switch the control to child window
			 //store the parent window id
			   String pwid = driver.getWindowHandle();
			   wutil.switchToChildWindow_URL(driver, "module=Accounts&action");
//			   //get all window id's
//			   Set<String> allwindows = driver.getWindowHandles();
//			   //switch to child window
//			   for(String s : allwindows) {
//				  driver.switchTo().window(s);
//				   if(driver.getCurrentUrl().contains("module=Accounts&action")) {
					   
					   //identify search TF and enter org name
					   createconname.getOrgSearchTF(contorgname);
					   
					   //identify search button and click on it
					   createconname.getOrgSearchNowBtn();
					   
					   //select org name and click on it
					   driver.findElement(By.xpath("//a[text()='"+contorgname+"']")).click();
//				   }
			   
			//switch back the control to parent window  
          wutil.switchToParentWindow(driver, pwid);
			 
		//identify save btn and click on it
			 createconname.getSaveBtn();
		
		//identify contact info page header and verify last name
			 ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String verifycon = coninfo.getVerifyLastname();
		Assert.assertTrue(verifycon.contains(contactname),"validating the conname");
		
		//identify contact info page header and verify organization name
			String verifyorgname = coninfo.getVerifyOrgname();
			Assert.assertTrue(verifyorgname.contains(verifyorgname),"validating the orgname for contact ");
			
		//click on contacts link
		home.getcontactstab();
			
	    //click on delete link
			driver.findElement(By.xpath("//a[text()='"+contactname+"']/../..//a[text()='del']")).click();
			Thread.sleep(2000);
			
	    //handle alert popup
			wutil.switchToAlertClickOk(driver);
	    
	    //click on organizations link
	    home.getorganizationtab();
	    
	    //click on delete link
	    driver.findElement(By.xpath("//a[text()='"+contorgname+"']/../..//a[text()='del']")).click();
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

