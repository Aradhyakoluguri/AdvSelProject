package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPomPage {
//Declare
	@FindBy(xpath="//a[@class='hdrLink']")
	private WebElement ContactHeader;
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement ContPlucIcon;

//Initialization
	public ContactsPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization

	public String getContactHeader() {
	 return ContactHeader.getText();
	}

	public void getContPlucIcon() {
		 ContPlucIcon.click();
	}
	
}
