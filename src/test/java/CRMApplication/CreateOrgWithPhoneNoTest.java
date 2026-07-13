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
import org.testng.annotations.Test;

public class CreateOrgWithPhoneNoTest {
	@Test
	public void createorgwithphone() throws InterruptedException {
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
		//identify organizations link and click on it
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		//identify org plus icon and click on it
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//identify org name TF and pass text in it
		driver.findElement(By.name("accountname")).sendKeys("infosis");
		//identify phone TF and enter no into it
		driver.findElement(By.id("phone")).sendKeys("9876543211");
		//identify save btn and click on it
				driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
				//identify org info page header and verify org name
				WebElement verifyorg = driver.findElement(By.xpath("//span[contains(text(),' Organization Information')]"));
				if(verifyorg.getText().contains("infosis")) {
					System.out.println("orgname created successfully");
				}else {
					System.out.println("orgname test fail");
				}
				//verify phoneno
				WebElement verifyphone = driver.findElement(By.id("dtlview_Phone"));
				if(verifyphone.getText().contains("9876543211")) {
					System.out.println("org created with phone number");
				}else {
					System.out.println("org creating with phone number failed");
				}
				//identify org link and click on it
				driver.findElement(By.linkText("Organizations")).click();
				//delete the org created by clicking on delete link
				driver.findElement(By.xpath("//a[text()='infosis']/../..//a[text()='del']")).click();
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
