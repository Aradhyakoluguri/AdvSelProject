package POMCrmLogin;

import static org.testng.Assert.fail;

import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseClassUtilities.BaseClass;
import CrmApplicationPomPages.ContactInfoPomPage;
import CrmApplicationPomPages.ContactsPomPage;
import CrmApplicationPomPages.CreateContactPomPage;
import CrmApplicationPomPages.CreateOrganizationPomPage;
import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.OrgInfoPomPage;
import CrmApplicationPomPages.OrganizationPomPage;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtilities;
import GenericUtilities.UtilityObjectClass;
import ListenersUtility.Listeners;

@org.testng.annotations.Listeners(ListenersUtility.Listeners.class)
public class ContactsTest extends BaseClass {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyzer.class)
	public void contactslastname() throws InterruptedException, IOException {

		// Fetch the Random Int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		UtilityObjectClass.test.get().log(Status.INFO, "Fetched Random Number");

		// Fetching data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcel("Contacts", 1, 2) + randomint;
		UtilityObjectClass.test.get().log(Status.INFO, "Fetch Data From Excel File");

		CrmHomePomPage home = new CrmHomePomPage(driver);

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"), "validating the home page");
		UtilityObjectClass.test.get().log(Status.INFO, "Validating Home Page Using Soft Assert");

		// click on contacts link
		home.getcontactstab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Contact tab");

		// click on plus icon
		ContactsPomPage Plusicon = new ContactsPomPage(driver);
		Plusicon.getContPlucIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// identify last name TF and pass text into it
		CreateContactPomPage LN = new CreateContactPomPage(driver);
		LN.getConLastnameTF(contactname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Last Name");

		// identify save btn and click on it
		CreateContactPomPage save = new CreateContactPomPage(driver);
		save.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Contact");

		// identify contact info page header and verify last name
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String verifycon = coninfo.getVerifyLastname();
		Assert.assertTrue(verifycon.contains(contactname), "validating the conname");
		UtilityObjectClass.test.get().log(Status.INFO, "Contact LastName Verified");

		// click on contacts link
		home.getcontactstab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Contact tab");

		// click on delete link
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../..//a[text()='del']")).click();
		Thread.sleep(3000);
		UtilityObjectClass.test.get().log(Status.INFO, "Deleted Contact");
		// Assert.fail();
		// handle alert popup
		wutil.switchToAlertClickOk(driver);
		UtilityObjectClass.test.get().log(Status.INFO, "Handled Alert Popup");

		exutil.CloseTheExcel();
		UtilityObjectClass.test.get().log(Status.INFO, "Closed Excel");

		soft.assertAll();
	}

	@Test(groups = "regression", retryAnalyzer = ListenersUtility.RetryAnalyzer.class)
	public void CreateContactUsingOrganizationName() throws InterruptedException, IOException {

		// Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		UtilityObjectClass.test.get().log(Status.INFO, "Fetched Random Number");

		// Fetching data from Excel File
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcel("Contacts", 7, 2) + randomint;
		String contorgname = exutil.fetchDataFromExcel("Contacts", 7, 3) + randomint;
		UtilityObjectClass.test.get().log(Status.INFO, "Fetch Data From Excel File");

		CrmHomePomPage home = new CrmHomePomPage(driver);

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"), "validating the home page");
		UtilityObjectClass.test.get().log(Status.INFO, "Validating Home Page Using Soft Assert");

		// click on organizations link
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// click on plus icon
		OrganizationPomPage orgplusicon = new OrganizationPomPage(driver);
		orgplusicon.getOrgPlusIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// enter organization name in TF
		CreateOrganizationPomPage createorgname = new CreateOrganizationPomPage(driver);
		createorgname.getOrgnameTF(contorgname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Organization Name");

		// click on save button
		createorgname.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Organization");

		// identify org info page header and verify org name
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verifyorg = orginfo.getVerifyOrgName();
		Assert.assertTrue(verifyorg.contains(contorgname), "validaing the orgname for organization");
		UtilityObjectClass.test.get().log(Status.INFO, "Organization Name Verified");

		// click on contacts link
		home.getcontactstab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Contact tab");

		// click on plus icon
		ContactsPomPage conplusicon = new ContactsPomPage(driver);
		conplusicon.getContPlucIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// identify contact last name TF and pass text into it
		CreateContactPomPage createconname = new CreateContactPomPage(driver);
		createconname.getConLastnameTF(contactname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Contact Last  Name");

		// click on plus icon beside the organization name TF
		createconname.getConOrgNameTFPlusIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Org Plus Icon Child Window Opened");

		// switch the control to child window
		// store the parent window id
		String pwid = driver.getWindowHandle();
		wutil.switchToChildWindow_URL(driver, "module=Accounts&action");
		UtilityObjectClass.test.get().log(Status.INFO, "Switched Driver Control To Child Window");
//				   //get all window id's
//				   Set<String> allwindows = driver.getWindowHandles();
//				   //switch to child window
//				   for(String s : allwindows) {
//					  driver.switchTo().window(s);
//					   if(driver.getCurrentUrl().contains("module=Accounts&action")) {

		// identify search TF and enter org name
		createconname.getOrgSearchTF(contorgname);

		// identify search button and click on it
		createconname.getOrgSearchNowBtn();

		// select org name and click on it
		driver.findElement(By.xpath("//a[text()='" + contorgname + "']")).click();
		UtilityObjectClass.test.get().log(Status.INFO, "Selected And Clicked On Org Name");
//					   }

		// switch back the control to parent window
		wutil.switchToParentWindow(driver, pwid);
		UtilityObjectClass.test.get().log(Status.INFO, "Switched Back To Parent Window");

		// identify save btn and click on it
		createconname.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Contact With Org");

		// identify contact info page header and verify last name
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String verifycon = coninfo.getVerifyLastname();
		Assert.assertTrue(verifycon.contains(contactname), "validating the conname");
		UtilityObjectClass.test.get().log(Status.INFO, "Validate Contact Name");

		// identify contact info page header and verify organization name
		String verifyorgname = coninfo.getVerifyOrgname();
		Assert.assertTrue(verifyorgname.contains(verifyorgname), "validating the orgname for contact ");
		UtilityObjectClass.test.get().log(Status.INFO, "Validate Organization Name");

		// click on contacts link
		home.getcontactstab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Contact tab");

		// click on delete link
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		UtilityObjectClass.test.get().log(Status.INFO, "Deleted Contact");

		// handle alert popup
		wutil.switchToAlertClickOk(driver);
		UtilityObjectClass.test.get().log(Status.INFO, "Handled Alert Popup");

		// click on organizations link
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// click on delete link
		driver.findElement(By.xpath("//a[text()='" + contorgname + "']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		UtilityObjectClass.test.get().log(Status.INFO, "Deleted Contact");

		// handle alert popup
		wutil.switchToAlertClickOk(driver);
		UtilityObjectClass.test.get().log(Status.INFO, "Handled Alert Popup");

		exutil.CloseTheExcel();
		UtilityObjectClass.test.get().log(Status.INFO, "Closed Excel");

		soft.assertAll();
	}

	@Test(groups = "regression", retryAnalyzer = ListenersUtility.RetryAnalyzer.class)
	public void ContactSupportStartAndEndDate() throws InterruptedException, IOException {

		// Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		UtilityObjectClass.test.get().log(Status.INFO, "Fetched Random Number");

		// Fetching the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String contactname = exutil.fetchDataFromExcel("Contacts", 4, 2) + randomint;
		UtilityObjectClass.test.get().log(Status.INFO, "Fetch Data From Excel File");

		CrmHomePomPage home = new CrmHomePomPage(driver);

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"), "validating the home page");
		UtilityObjectClass.test.get().log(Status.INFO, "Validating Home Page Using Soft Assert");

		// click on contacts link
		home.getcontactstab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Contact tab");

		// click on plus icon
		ContactsPomPage conplusicon = new ContactsPomPage(driver);
		conplusicon.getContPlucIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// identify lastname TF and pass text into it
		CreateContactPomPage createcon = new CreateContactPomPage(driver);
		createcon.getConLastnameTF(contactname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Last Name");

		// Identify support start date TF and pass the start date
		String startdate = jutil.fetchCurrentDate();
		createcon.getSupportStartdateTF(startdate);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Support Start Date");

		// Identify support end date TF and pass the end date
		String enddate = jutil.fetchDateAfterGivenDays(30);
		createcon.getSupportEnddateTF(enddate);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Support End Date");

		// click on save
		createcon.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Contact");

		// identify contacts info header page and verify the last name
		ContactInfoPomPage coninfo = new ContactInfoPomPage(driver);
		String verifylastname = coninfo.getVerifyLastname();
		Assert.assertTrue(verifylastname.contains(contactname), "validating the con last name");
		UtilityObjectClass.test.get().log(Status.INFO, "Contact LastName Verified");

		// verify the support start date
		String verifysupportSD = coninfo.getVerifySupportStartdate();
		Assert.assertTrue(verifysupportSD.contains(startdate), "validating the support start date");
		UtilityObjectClass.test.get().log(Status.INFO, "Support Start Date Verified");

		// verify the support end date
		String verifysupportED = coninfo.getVerifySupportEnddate();
		Assert.assertTrue(verifysupportED.contains(enddate), "validating the support end date");
		UtilityObjectClass.test.get().log(Status.INFO, "Support End Date Verified");

		// click on contacts tab
		home.getcontactstab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Contact tab");

		// click on delete link
		driver.findElement(By.xpath("//a[text()='" + contactname + "']/../..//a[text()='del']")).click();
		Thread.sleep(2000);
		UtilityObjectClass.test.get().log(Status.INFO, "Deleted Contact");

		// handle alert popup
		wutil.switchToAlertClickOk(driver);
		UtilityObjectClass.test.get().log(Status.INFO, "Handled Alert Popup");

		exutil.CloseTheExcel();
		UtilityObjectClass.test.get().log(Status.INFO, "Closed Excel");

		soft.assertAll();

	}
}
