package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPomPage {

	//Declare
	@FindBy(xpath="//span[text()='Creating New Contact']")
	private WebElement CreateContactHeader;
	@FindBy(name="lastname")
	private WebElement ConLastnameTF;
	@FindBy(xpath="//img[contains(@onclick,'module')]")
	private WebElement ConOrgNameTFPlusIcon;
	@FindBy(id="search_txt")
	private WebElement OrgSearchTF;
	@FindBy(name="search")
	private WebElement OrgSearchNowBtn;
	@FindBy(name="support_start_date")
	private WebElement SupportStartdateTF;
	@FindBy(name="support_end_date")
	private WebElement SupportEnddateTF;
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
		
		//Initialization
		public  CreateContactPomPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		//Utilization
		public String getCreateContactHeader() {
			return CreateContactHeader.getText();
		}
		public void getConLastnameTF(String lastname) {
			 ConLastnameTF.sendKeys(lastname);
		}
		public void getSupportStartdateTF(String supportstartdate) {
			SupportStartdateTF.clear();
			SupportStartdateTF.sendKeys(supportstartdate);
		}
		public void getSupportEnddateTF(String supportenddate) {
			SupportEnddateTF.clear();
			SupportEnddateTF.sendKeys(supportenddate);
		}
		public void getConOrgNameTFPlusIcon() {
		 ConOrgNameTFPlusIcon.click();
		}
		
		public void getOrgSearchTF(String orgname) {
			 OrgSearchTF.sendKeys(orgname);
		}
		public void getOrgSearchNowBtn() {
			OrgSearchNowBtn.click();
		}
		public void getSaveBtn() {
			SaveBtn.click();
		}
		
		}
		

