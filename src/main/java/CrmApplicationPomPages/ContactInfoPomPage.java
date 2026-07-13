 package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPomPage {
//Declare
	@FindBy(xpath="//span[contains(text(),'Contact Information')]")
	private WebElement ContactInfoHeader;
	@FindBy(id="mouseArea_Last Name")
	private WebElement VerifyLastname;
	@FindBy(id="mouseArea_Organization Name")
	private WebElement VerifyOrgname;
	@FindBy(id="dtlview_Support Start Date")
	private WebElement VerifySupportStartdate;
	@FindBy(id="mouseArea_Support End Date")
	private WebElement VerifySupportEnddate;
	
	//Initialization
		public ContactInfoPomPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		//Utilization
		public String getContactInfoHeader() {
			return ContactInfoHeader.getText();
		}
		public String getVerifyLastname() {
			return VerifyLastname.getText();
		}
		public String getVerifyOrgname() {
			return VerifyOrgname.getText();
		}
		public String getVerifySupportStartdate() {
			return VerifySupportStartdate.getText();
		}
		public String getVerifySupportEnddate() {
			return VerifySupportEnddate.getText();
		}
		
}
