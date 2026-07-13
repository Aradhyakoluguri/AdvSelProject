package CRMApplication;

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


public class CreateContactWithSupportDateTest {
@Test
public void ContactSupportStartAndEndDate() throws InterruptedException {
	
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
		//click on contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		//click on plus icon
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	    //identify lastname TF and pass text into it
		driver.findElement(By.name("lastname")).sendKeys("sree");  
	    //enter date in support start date TF
	    WebElement startdate = driver.findElement(By.id("jscal_field_support_start_date"));
	   startdate.clear();
	    startdate.sendKeys("2027-07-26");
	    System.out.println(startdate.getAttribute("value"));
	    //enter the date in support end date TF
	     WebElement enddate = driver.findElement(By.id("jscal_field_support_end_date"));
	   enddate.clear();
	    enddate.sendKeys("2028-08-28");
	    System.out.println(enddate.getAttribute("value"));
	    Thread.sleep(5000);
	    //select support start date from calender icon 
	    //click on calendar icon
	    //driver.findElement(By.id("jscal_trigger_support_start_date")).click();
	    //click on save
	    driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
	  //identify contacts info header page and verify the last name
	    WebElement verifylastname = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
	    if(verifylastname.getText().contains("sree")) {
	    	System.out.println("contacts created with lastname");
	    }else {
	    	System.out.println("contacts created with last name is failed");
	    }
	    // verify the support start date
	    WebElement verifysupportSD = driver.findElement(By.id("dtlview_Support Start Date"));
	    if(verifysupportSD.getText().contains("2027-07-26")) {
	    	System.out.println("contacts created with support start date");
	    }else {
	    	System.out.println("contacts created with support start date failed");
	    }
	  //verify the support end date
	    WebElement verifysupportED = driver.findElement(By.id("dtlview_Support End Date"));
	    if(verifysupportED.getText().contains("2028-08-28")) {
	    	System.out.println("contacts created with support end date");
	    }else {
	    	System.out.println("contacts created with support end date failed");
	    }
	    //click on contacts tab
	    driver.findElement(By.xpath("//a[text()='Contacts']")).click();
	  //click on delete link
	    driver.findElement(By.xpath("//a[text()='sree']/../..//a[text()='del']")).click();
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


