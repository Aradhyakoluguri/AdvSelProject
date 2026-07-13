package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPomPage {
//Declare
	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement OrgHeader;
	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement orgPlusIcon;
	
	//Initialization
	public OrganizationPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public String getOrgHeader() {
		return OrgHeader.getText();
	}

	public void getOrgPlusIcon() {
		orgPlusIcon.click();
	}
	
}
