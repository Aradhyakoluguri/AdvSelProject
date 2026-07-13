package POMCrmLogin;

import static org.testng.Assert.assertTrue;
import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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


public class CreateContactsPomClassTest {
	

	@Test
	public void contactslastname() throws InterruptedException, IOException {
		
		//Fetching the data from Property File
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchdatafrompropertyfile("Browser");
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		
		//Fetch the Random Int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		
	//Fetching data from Excel File	
	ExcelFileUtility exutil = new ExcelFileUtility();
	 String contactname = exutil.fetchDataFromExcel("Contacts", 1, 2)+randomint;
	 
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
		CrmLoginPomPage l= new CrmLoginPomPage(driver);
		l.login(username, password);
		
		CrmHomePomPage home = new CrmHomePomPage(driver);
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"),"validating the home page");
		
		//click on contacts link
		home.getcontactstab();
		
			//click on plus icon
		ContactsPomPage Plusicon = new ContactsPomPage(driver);
		Plusicon.getContPlucIcon();
		
	    //identify last name TF and pass text into it
		CreateContactPomPage LN =new CreateContactPomPage(driver);
		LN.getConLastnameTF(contactname);
		
		//identify save btn and click on it
		CreateContactPomPage save = new CreateContactPomPage(driver);
		save.getSaveBtn();
		
		//identify contact info page header and verify last name
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String verifycon = coninfo.getVerifyLastname();
		Assert.assertTrue(verifycon.contains(contactname),"validating the conname");
		
		//click on contacts link
			home.getcontactstab();
			
	    //click on delete link
			driver.findElement(By.xpath("//a[text()='"+contactname+"']/../..//a[text()='del']")).click();
			Thread.sleep(3000);
			
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