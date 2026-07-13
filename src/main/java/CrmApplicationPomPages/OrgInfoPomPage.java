package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPomPage {
//Declare
	@FindBy(xpath="//span[contains(text(),'Organization Information')]")
	private WebElement OrgInfoHeader;
	@FindBy(id="mouseArea_Organization Name")
	private WebElement VerifyOrgName;
	@FindBy(id="mouseArea_Phone")
	private WebElement VerifyPhno; 
	@FindBy(id="mouseArea_Industry")
	private WebElement VerifyIndustry;
	@FindBy(id="mouseArea_Type")
	private WebElement Verifytype;
	
	//Initialization
	public OrgInfoPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization

	public String getOrgInfoHeader() {
		return OrgInfoHeader.getText();
	}

	public String getVerifyOrgName() {
		return VerifyOrgName.getText();
	}

	public String getVerifyPhno() {
		return VerifyPhno.getText();
	}

	public String getVerifyIndustry() {
		return VerifyIndustry.getText();
	}

	public String getVerifytype() {
		return Verifytype.getText();
	}
	
	
}
