package DDTCrmApplicationExcel;

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
import org.testng.annotations.Test;

import CrmApplicationPomPages.ContactInfoPomPage;
import CrmApplicationPomPages.ContactsPomPage;
import CrmApplicationPomPages.CreateContactPomPage;
import CrmApplicationPomPages.CreateOrganizationPomPage;
import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.CrmLoginPomPage;
import CrmApplicationPomPages.OrgInfoPomPage;
import CrmApplicationPomPages.OrganizationPomPage;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyFileUtility;

public class CreateContactWithOrgExcelTest {
	@Test
	public void CreateContactUsingOrgName() throws InterruptedException, IOException {
		//Fetching data from property file
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchdatafrompropertyfile("Browser");
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		
		//Fetching the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		 
		 String contactname = exutil.fetchDataFromExcel("Contacts", 7, 2);
		  String conorgname = exutil.fetchDataFromExcel("Contacts", 7, 3);
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
		//maximize the window
		driver.manage().window().maximize();
		//implicit wait
		long time = Long.parseLong(timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		//navigate to the application
		driver.get(url);
		//identify username TF and pass text into it'
		driver.findElement(By.name("user_name")).sendKeys(username);
		//identify password tf and pass the text
		driver.findElement(By.name("user_password")).sendKeys(password);
		//identify login button and click on it
		driver.findElement(By.id("submitButton")).click();
		//click on organizations link
	    driver.findElement(By.xpath("//a[text()='Organizations']")).click();
	  //click on plus icon
	  		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	  	//enter organization name in TF
		    driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(conorgname);
		    //click on save button
		    driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		  //identify org info page header and verify org name
			WebElement verifyorg = driver.findElement(By.xpath("//span[contains(text(),' Organization Information')]"));
			if(verifyorg.getText().contains(conorgname)) {
				System.out.println("orgname created successfully");
			}else {
				System.out.println("orgname test fail");
			}	
			//click on contacts link
	  		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	  	//click on plus icon
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    //identify lastname TF and pass text into it
		driver.findElement(By.name("lastname")).sendKeys(contactname);
			//click on plus icon beside the organization name TF
			   driver.findElement(By.xpath("//img[contains(@onclick,'return')]")).click();
			   //switch the control to child window
			 //store the parent window id
			   String parentwindow = driver.getWindowHandle();
			   //get all window id's
			   Set<String> allwindows = driver.getWindowHandles();
			   //switch to child window
			   for(String s : allwindows) {
				   driver.switchTo().window(s);
				   if(driver.getCurrentUrl().contains("module=Accounts&action")) {
					   //identify search TF and enter org name
					   driver.findElement(By.id("search_txt")).sendKeys(conorgname);
					   //identify search button and click on it
					   driver.findElement(By.name("search")).click();
					   //select org name and click on it
					   driver.findElement(By.xpath("//a[text()='"+conorgname+"']")).click();
				   }
			   }
			//switch back the control to parent window   
			 driver.switchTo().window(parentwindow);  
		//identify save btn and click on it
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//identify contact info page header and verify last name
		WebElement verifycon = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
		if(verifycon.getText().contains(contactname)) {
			System.out.println("contacts created with lastname");
		}else {
			System.out.println("contacts creating with lastname failed");
		}
		//identify contact info page header and verify organization name
			WebElement verifyorgname = driver.findElement(By.id("mouseArea_Organization Name"));
			if(verifyorgname.getText().contains(conorgname)) {
				System.out.println("contacts created with organizations name");
			}else {
				System.out.println("contacts creating with organization name failed");
			}
		//click on contacts link
			driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	    //click on delete link
			driver.findElement(By.xpath("//a[text()='"+contactname+"']/../..//a[text()='del']")).click();
			Thread.sleep(2000);
	    //handle alert popup
	    Alert alert = driver.switchTo().alert();
	    //String actualalertpopuptext = alert.getText();
	    //System.out.println(actualalertpopuptext);
	    alert.accept();
	    //click on organizations link
	    driver.findElement(By.xpath("//a[text()='Organizations']")).click();
	    //click on delete link
	    driver.findElement(By.xpath("//a[text()='"+conorgname+"']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		//handle alert popup
	    Alert alert1 = driver.switchTo().alert();
	  //String actualalertpopuptext = alert.getText();
	    //System.out.println(actualalertpopuptext);
	    alert1.accept();
	    //mouseover on admin icon
	     WebElement admin = driver.findElement(By.xpath("//img[contains(@src,'user.PNG')]"));
	     Actions act = new Actions(driver);
	     act.moveToElement(admin).perform();
	    //click on signout 
	    driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	    Thread.sleep(5000);
	    //close the window
	    driver.quit();
	    
	    
	    
	}	
}