package DDTCrmApplicationExcel;

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
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.PropertyFileUtility;

public class CreateOrgWithIndustryAndTypeExcelTest {
	@Test
	public void createorgwithindustryandtype() throws InterruptedException, IOException {
		PropertyFileUtility putil = new PropertyFileUtility();
		String browser = putil.fetchdatafrompropertyfile("Browser");
		String url = putil.fetchdatafrompropertyfile("URL");
		String username = putil.fetchdatafrompropertyfile("UserName");
		String password = putil.fetchdatafrompropertyfile("Password");
		String timeouts = putil.fetchdatafrompropertyfile("timeouts");
		
		ExcelFileUtility exutil = new ExcelFileUtility();
		 String orgname = exutil.fetchDataFromExcel("Organization", 4, 2);
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
		//maximize the window
		driver.manage().window().maximize();
		//implicit wait
		long time = Long.parseLong(timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		//navigate to the application
		driver.get("http://localhost:8888/");
		//identify username TF and pass text into it'
		driver.findElement(By.name("user_name")).sendKeys(username);
		//identify password tf and pass the text
		driver.findElement(By.name("user_password")).sendKeys(password);
		//identify login button and click on it
		driver.findElement(By.id("submitButton")).click();
		//identify organizations link and click on it
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		//identify org plus icon and click on it
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		//identify org name TF and pass text in it
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		//identify industry dropdown and the option from it
		WebElement indDD = driver.findElement(By.name("industry"));
		Select inds = new Select(indDD);
		inds.selectByValue(industry);
		//identify type dropdown and select option from it
		WebElement typeDD = driver.findElement(By.name("accounttype"));
		Select types = new Select(typeDD);
		types.selectByValue(type);
		//identify save btn and click on it
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		//identify org info page header and verify org name
		WebElement verifyorg = driver.findElement(By.xpath("//span[contains(text(),' Organization Information')]"));
		if(verifyorg.getText().contains(orgname)) {
			System.out.println("orgname created successfully");
		}else {
			System.out.println("orgname test fail");
		}
		//verify industry
		WebElement verifyind = driver.findElement(By.id("dtlview_Industry"));
		if(verifyind.getText().contains(industry)) {
			System.out.println("org created with industry");
		}else {
			System.out.println("org creating with ind  failed");
		}
		//verify type
				WebElement verifytype = driver.findElement(By.id("dtlview_Type"));
				if(verifytype.getText().contains(type)) {
					System.out.println("org created with type");
				}else {
					System.out.println("org creating with type  failed");
				}
		//identify org link and click on it
		driver.findElement(By.linkText("Organizations")).click();
		//delete the org created by clicking on delete link
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//a[text()='del']")).click();
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
