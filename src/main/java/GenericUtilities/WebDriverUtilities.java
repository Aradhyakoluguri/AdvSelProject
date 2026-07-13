package GenericUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities {
	
/**
 * used to navigate to the application using get method
 * @param driver
 * @param url
 */
public void navigateToAnApp(WebDriver driver,String url) {
	driver.get(url);
}
/**
 * used to get the title using getTitle method
 * @param driver
 * @return
 */
public String fetchTheTitle(WebDriver driver) {
	return driver.getTitle();
}
/**
 * used to fetch the CURRENT URL
 * @param driver
 * @return
 */
public String fetchTheCurrentUrl(WebDriver driver) {
	return driver.getCurrentUrl();
}
/**
 * used to fetch the SOURCE CODE
 * @param driver
 * @return
 */
public String fetchTheSourceCode(WebDriver driver) {
	return driver.getPageSource();
}
/**
 * used to MAXIMIZE the window
 * @param driver
 */
public void maximizeTheWindow(WebDriver driver) {
	 driver.manage().window().maximize();
}
/**
 * used to MINIMIZE the window
 * @param driver
 */
public void minimizeTheWindow(WebDriver driver) {
	 driver.manage().window().minimize();
}
/**
 * used to make the Window FULLSCREEN
 * @param driver
 */
public void fullScreenTheWindow(WebDriver driver) {
	 driver.manage().window().fullscreen();
}
/**
 * used to GET THE WINDOW SIZE
 * @param driver
 * @return
 */
public Dimension getTheWindowSize(WebDriver driver) {
	return driver.manage().window().getSize();
}
/**
 * used to GET THE WINDOW POSITION
 * @param driver
 * @return
 */
public Point getTheWindowPosition(WebDriver driver) {
	return driver.manage().window().getPosition();
}
/**
 * used to SET THE WINDOW SIZE
 * @param driver
 * @param width
 * @param height
 */
public void setTheWindowSize(WebDriver driver,int width,int height) {
	driver.manage().window().setSize(new Dimension(width,height));
}
/**
 * used to SET THE WINDOW POSITION
 * @param driver
 * @param x
 * @param y
 */
public void setTheWindowPosition(WebDriver driver,int x,int y) {
	driver.manage().window().setPosition(new Point(x,y));
}
/**
 * used to Navigate to PREVIOUS WEB PAGE
 * @param driver
 */
public void navigateToPreviousWP(WebDriver driver) {
	driver.navigate().back();
}
/**
 * used to Navigate  to NEXT WEB PAGE
 * @param driver
 */
public void navigateToNextWP(WebDriver driver) {
	driver.navigate().forward();
}
/**
 * used to REFRESH the WEB PAGE
 * @param driver
 */
public void refreshTheWP(WebDriver driver) {
	driver.navigate().refresh();
}
/**
 * used to navigate to web page using using TO(String url)
 * @param driver
 * @param url
 */
public void navigateToWP_ToString(WebDriver driver,String url) {
	driver.navigate().to(url);
}
/**
 * used to navigate to web page using TO(URL url)
 * @param driver
 * @param url
 * @throws MalformedURLException 
 */
public void navigateToWP_ToURL(WebDriver driver,String url) throws MalformedURLException{
	driver.navigate().to(new URL(url));
}
/**
 * used to CLOSE the BROWSER
 * @param driver
 */
public void closeTheBrowser(WebDriver driver) {
	driver.close();
}
/**
 * used to QUIT the BROWSER
 * @param driver
 */
public void quitTheBrowser(WebDriver driver) {
	driver.quit();
}
/**
 * used to Fetch The Window ID
 * @param driver
 * @return
 */
public String fetchTheWindowID(WebDriver driver) {
	return driver.getWindowHandle();
}
/**
 * used to Fetch ALL Window IDs
 * @param driver
 * @return
 */
public Set<String> fetchAllWindowIDs(WebDriver driver) {
	return driver.getWindowHandles();
}
/**
 * used to switch the driver control to child window by validating using TITLE
 * @param driver
 * @param exptitle
 */
public void switchToChildWindow_Title(WebDriver driver,String exptitle) {
	Set<String> wids = driver.getWindowHandles();
	for(String s : wids) {
		driver.switchTo().window(s);
		if(driver.getTitle().contains(exptitle)) {
		break;	
		}
	}
}
/**
 * used to switch the driver control to child window by validating using URL
 * @param driver
 * @param expurl
 */
public void switchToChildWindow_URL(WebDriver driver,String expurl) {
	Set<String> wids = driver.getWindowHandles();
	for(String s:wids) {
		driver.switchTo().window(s);
		if(driver.getCurrentUrl().contains(expurl)) {
			break;
		}
	}
}
/**
 * used to switch the driver control to parent window
 * @param driver
 * @param pwid
 */
public void switchToParentWindow(WebDriver driver,String pwid) {
	driver.switchTo().window(pwid);
}
/**
 * used to click on ok button
 * @param driver
 */
public void switchToAlertClickOk(WebDriver driver) {
	driver.switchTo().alert().accept();
}
/**
 * used to click on cancel button
 * @param driver
 */
public void switchToAlertClickCancel(WebDriver driver) {
	driver.switchTo().alert().dismiss();
}
/**
 * used to fetch the text 
 * @param driver
 * @return
 */
public String switchToAlertFetchText(WebDriver driver) {
	return driver.switchTo().alert().getText();
}
/**
 * used to handle the prompt alert popup and pass the text in it
 * @param driver
 * @param text
 */
public void switchToAlertPassText(WebDriver driver,String text) {
	driver.switchTo().alert().sendKeys(text);
}
/**
 * used to switch the driver control to frame and validating using INDEX
 * @param driver
 * @param index
 */
public void switchToFrame_Index(WebDriver driver,int index) {
	driver.switchTo().frame(index);
}
/**
 * used to switch the driver control to frame and validating using ID or NAME
 * @param driver
 * @param id_name
 */
public void switchToFrame_IdorName(WebDriver driver,String id_name) {
	driver.switchTo().frame(id_name);
}
/**
 * used to switch the driver control to frame and validating using FRAME-ELEMENT
 * @param driver
 * @param frameEle
 */
public void switchToFrame_Element(WebDriver driver,WebElement frameEle) {
	
	driver.switchTo().frame(frameEle);
}
/**
 * used to wait for an ELEMENT 
 * @param driver
 * @param timeouts
 */
public void waitForAnElement(WebDriver driver,String timeouts) {
	long time = Long.parseLong(timeouts);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
}
/**
 * used to wait for an Element To Visible
 * @param driver
 * @param timeouts
 * @param ele
 */
public void waitForElementToBeVisible(WebDriver driver,String timeouts,WebElement ele) {
 long time = Long.parseLong(timeouts);
 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
 wait.until(ExpectedConditions.visibilityOf(ele));
}
/**
 * used to wait foe an Element to be Clickable
 * @param driver
 * @param timeouts
 * @param ele
 */
public void waitForElementToBeClickable(WebDriver driver,String timeouts,WebElement ele) {
	 long time = Long.parseLong(timeouts);
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	 wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
/**
 * used to wait for Title to Visible
 * @param driver
 * @param timeouts
 * @param title
 */
public void waitForTitleToBeVisible(WebDriver driver,String timeouts,String title) {
	 long time = Long.parseLong(timeouts);
	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
	 wait.until(ExpectedConditions.titleContains(title));
	}
/**
 * select the option from the DROPDOWN using INDEX
 * @param dropdown
 * @param index
 */
public void selectDD_OptionUsingIndex(WebElement dropdown,int index) {
	Select s = new Select(dropdown);
	s.selectByIndex(index);
}
/**
 * select the option from the DROPDOWN using VALUE
 * @param dropdown
 * @param value
 */
public void selectDD_OptionUsingValue(WebElement dropdown,String value) {
	Select s = new Select(dropdown);
	s.selectByValue(value);
}
/**
 * select the option from the DROPDOWN using TEXT
 * @param dropdown
 * @param text
 */
public void selectDD_OptionUsingText(WebElement dropdown,String text) {
	Select s = new Select(dropdown);
	s.selectByVisibleText(text);
}
/**
 * used to MOUSEHOVER ON THE ELEMENT
 * @param driver
 * @param ele
 */
public void mouseHoverOnElement(WebDriver driver,WebElement ele) {
	Actions act = new Actions(driver);
	act.moveToElement(ele).perform();
}

}


