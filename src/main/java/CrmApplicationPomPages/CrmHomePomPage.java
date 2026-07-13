package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CrmHomePomPage {
//Declare
	@FindBy(partialLinkText="Home")
	private WebElement homeHeader;
	@FindBy(linkText="Organizations")
	private WebElement organizationsTab;
	@FindBy(linkText="Contacts")
	private WebElement contactsTab;
	@FindBy(xpath="//img[contains(@src,'user')]")
	private WebElement adminIcon;
	@FindBy(linkText="Sign Out")
	private  WebElement SignOut;
	
	//Initialization
	public CrmHomePomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public String gethomeheader() {
		return homeHeader.getText();
	}
	public void getorganizationtab() {
		organizationsTab.click();
	}
	public void getcontactstab() {
		contactsTab.click();
	}
	public WebElement getadminicon() {
		return adminIcon;
	}
	public void getsignout() {
		SignOut.click();
	}
	
	//Business Logic
	
}
