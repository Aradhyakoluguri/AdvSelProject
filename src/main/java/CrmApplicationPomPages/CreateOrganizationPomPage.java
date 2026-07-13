package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPomPage {
    //Declare
	@FindBy(xpath="//span[text()='Creating New Organization']")
	private WebElement CreateOrg;
	@FindBy(name="accountname")
	private WebElement Orgname;
	@FindBy(id="phone")
	private WebElement phnoTF;
	@FindBy(name="industry")
	private WebElement IndustryDD;
	@FindBy(name="accounttype")
	private WebElement TypeDD;
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement SaveBtn;
	
	//Initialization
	public CreateOrganizationPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization
	public String getCreateOrg() {
		return CreateOrg.getText();
	}
	public void getOrgnameTF(String organizationname) {
		Orgname.sendKeys(organizationname);
	}

	public void getPhnoTF(String phoneno) {
	 phnoTF.sendKeys(phoneno);
	}

	public WebElement getIndustryDD() {
		return IndustryDD;
	}

	public WebElement getTypeDD() {
		 return TypeDD;
	}

	public void getSaveBtn() {
		 SaveBtn.click();
	}
	
	
	
}
