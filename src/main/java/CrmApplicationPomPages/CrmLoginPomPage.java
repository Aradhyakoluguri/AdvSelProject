package CrmApplicationPomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CrmLoginPomPage {
	//Declare
	@FindBy(xpath="//img[@alt='logo']")
	private WebElement logo;
@FindBy(name="user_name")
private WebElement usernameTF;
@FindBy(name="user_password")
private WebElement passwordTF;
@FindBy(id="submitButton")
private WebElement loginbtn;

//Initilization
public  CrmLoginPomPage(WebDriver driver) {
	PageFactory.initElements(driver, this);
}

//Utilization
public WebElement getlogo() {
	return logo;
}
public void getusernameTF(String username) {
	 usernameTF.sendKeys(username);
}
public void getpasswordTF(String password) {
	 passwordTF.sendKeys(password);
}
public void getloginbtn() {
	 loginbtn.click();
}

//Business logic
public void login(String username,String password) {
	usernameTF.sendKeys(username);
	passwordTF.sendKeys(password);
	loginbtn.click();
}
}
