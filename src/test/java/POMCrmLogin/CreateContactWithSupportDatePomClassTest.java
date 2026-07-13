package POMCrmLogin;

import static org.testng.Assert.assertTrue;
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
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import CrmApplicationPomPages.ContactInfoPomPage;
import CrmApplicationPomPages.ContactsPomPage;
import CrmApplicationPomPages.CreateContactPomPage;
import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.CrmLoginPomPage;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtilities;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtilities;

public class CreateContactWithSupportDatePomClassTest {
	@Test
	public void ContactSupportStartAndEndDate() throws InterruptedException, IOException {
		//Fetching data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchdatafrompropertyfile("Browser");
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		
		//Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		 int randomint = jutil.fetchRandomNumber();
		 
		//Fetching the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		 String contactname = exutil.fetchDataFromExcel("Contacts", 4, 2)+randomint;
		 
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
			CrmLoginPomPage l=new CrmLoginPomPage(driver);
			l.login(username, password);
			
			CrmHomePomPage home = new CrmHomePomPage(driver);
			
			SoftAssert soft = new SoftAssert();
			soft.assertTrue(home.gethomeheader().contains("Home"),"validating the home page");
			
			//click on contacts link
			home.getcontactstab();
			
			//click on plus icon
			ContactsPomPage conplusicon = new ContactsPomPage(driver);
			conplusicon.getContPlucIcon();
			
		    //identify lastname TF and pass text into it
			CreateContactPomPage createcon = new CreateContactPomPage(driver);
			createcon.getConLastnameTF(contactname);
			
		    //Identify support start date TF and pass the start date
			String startdate = jutil.fetchCurrentDate();
			createcon.getSupportStartdateTF(startdate);
		  
			//Identify support end date TF and pass the end date
			String enddate = jutil.fetchDateAfterGivenDays(30);
			createcon.getSupportEnddateTF(enddate);
				
		    //click on save
			createcon.getSaveBtn();
		    
		  //identify contacts info header page and verify the last name
			ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		    String verifylastname = coninfo.getVerifyLastname();
		    Assert.assertTrue(verifylastname.contains(contactname),"validating the con last name");
		    
		    // verify the support start date
		    String verifysupportSD =coninfo.getVerifySupportStartdate();
		    Assert.assertTrue(verifysupportSD.contains(startdate),"validating the support start date");
		    	    
		  //verify the support end date
		    String verifysupportED = coninfo.getVerifySupportEnddate();
		    Assert.assertTrue(verifysupportED.contains(enddate),"validating the support end date");
		    
		    //click on contacts tab
		    home.getcontactstab();
		    
		  //click on delete link
		    driver.findElement(By.xpath("//a[text()='"+contactname+"']/../..//a[text()='del']")).click();
			Thread.sleep(2000);
			
			//handle alert popup
		   wutil.switchToAlertClickOk(driver);
		 		    
		  //mouseover on admin icon
		     WebElement adminicon = home.getadminicon();
		     wutil.mouseHoverOnElement(driver, adminicon);
		     
		    //click on signout 
		    home.getsignout();
		    Thread.sleep(5000);
		    
		    //close the window
		    wutil.quitTheBrowser(driver);
		    exutil.CloseTheExcel();
		    soft.assertAll();
	}
}
