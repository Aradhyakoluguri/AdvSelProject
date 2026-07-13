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

public class CreateContactsTest {
@Test
public void contactslastname() throws InterruptedException {
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
	//identify save btn and click on it
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//identify contact info page header and verify last name
	WebElement verifycon = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
	if(verifycon.getText().contains("sree")) {
		System.out.println("contacts created with lastname");
	}else {
		System.out.println("contacts creating with lastname failed");
	}
	//click on contacts link
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
    //click on delete link
		driver.findElement(By.xpath("//a[text()='sree']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
    //handle alert popup
    Alert alert = driver.switchTo().alert();
    //String actualalertpopuptext = alert.getText();
    //System.out.println(actualalertpopuptext);
    alert.accept();
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
