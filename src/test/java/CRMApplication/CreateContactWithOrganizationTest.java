package CRMApplication;

import java.time.Duration;
import java.util.Set;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactWithOrganizationTest {
@Test
public void CreateContactUsingOrganizationName() throws InterruptedException {
	String browser = "chrome";
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
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//navigate to the application
	driver.get("http://localhost:8888/");
	//identify username TF and pass text into it'
	driver.findElement(By.name("user_name")).sendKeys("admin");
	//identify password tf and pass the text
	driver.findElement(By.name("user_password")).sendKeys("password");
	//identify login button and click on it
	driver.findElement(By.id("submitButton")).click();
	//click on organizations link
    driver.findElement(By.xpath("//a[text()='Organizations']")).click();
  //click on plus icon
  		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
  	//enter organization name in TF
	    driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("wipro");
	    //click on save button
	    driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
	  //identify org info page header and verify org name
		WebElement verifyorg = driver.findElement(By.xpath("//span[contains(text(),' Organization Information')]"));
		if(verifyorg.getText().contains("wipro")) {
			System.out.println("orgname created successfully");
		}else {
			System.out.println("orgname test fail");
		}	
		//click on contacts link
  		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
  	//click on plus icon
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
    //identify lastname TF and pass text into it
	driver.findElement(By.name("lastname")).sendKeys("pardhu");
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
				   driver.findElement(By.id("search_txt")).sendKeys("wipro");
				   //identify search button and click on it
				   driver.findElement(By.name("search")).click();
				   //select org name and click on it
				   driver.findElement(By.xpath("//a[text()='wipro']")).click();
			   }
		   }
		//switch back the control to parent window   
		 driver.switchTo().window(parentwindow);  
	//identify save btn and click on it
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//identify contact info page header and verify last name
	WebElement verifycon = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
	if(verifycon.getText().contains("pardhu")) {
		System.out.println("contacts created with lastname");
	}else {
		System.out.println("contacts creating with lastname failed");
	}
	//identify contact info page header and verify organization name
		WebElement verifyorgname = driver.findElement(By.id("mouseArea_Organization Name"));
		if(verifyorgname.getText().contains("wipro")) {
			System.out.println("contacts created with organizations name");
		}else {
			System.out.println("contacts creating with organization name failed");
		}
	//click on contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
    //click on delete link
		driver.findElement(By.xpath("//a[text()='pardhu']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
    //handle alert popup
    Alert alert = driver.switchTo().alert();
    //String actualalertpopuptext = alert.getText();
    //System.out.println(actualalertpopuptext);
    alert.accept();
    //click on organizations link
    driver.findElement(By.xpath("//a[text()='Organizations']")).click();
    //click on delete link
    driver.findElement(By.xpath("//a[text()='wipro']/../..//a[text()='del']")).click();
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

	    
	   
	  
		  
	   
			   
			   
			 
			   