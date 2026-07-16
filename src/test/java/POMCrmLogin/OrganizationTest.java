package POMCrmLogin;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseClassUtilities.BaseClass;
import CrmApplicationPomPages.CreateOrganizationPomPage;
import CrmApplicationPomPages.CrmHomePomPage;
import CrmApplicationPomPages.OrgInfoPomPage;
import CrmApplicationPomPages.OrganizationPomPage;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtilities;
import GenericUtilities.UtilityObjectClass;

@Listeners(ListenersUtility.ListenersUtil.class)
public class OrganizationTest extends BaseClass {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyzer.class)
	public void createorgwithorgname() throws InterruptedException, IOException {

		// Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		UtilityObjectClass.test.get().log(Status.INFO, "Fetched Random Number");

		// Fetching data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("Organization", 1, 2) + randomint;
		UtilityObjectClass.test.get().log(Status.INFO, "Fetch Data From Excel File");

		CrmHomePomPage home = new CrmHomePomPage(driver);

		// Validate home page
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"), "validating the home page");
		UtilityObjectClass.test.get().log(Status.INFO, "Validating Home Page Using Soft Assert");

		// identify organizations link and click on it
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// identify org plus icon and click on it
		OrganizationPomPage orgplusicon = new OrganizationPomPage(driver);
		orgplusicon.getOrgPlusIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// identify org name TF and pass text in it
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgnameTF(orgname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Organization Name");

		// identify save btn and click on it
		createorg.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Contact");

		// identify org info page header and verify org name
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verifyorg = orginfo.getVerifyOrgName();
		Assert.assertTrue(verifyorg.contains(orgname), "validating org header");
		UtilityObjectClass.test.get().log(Status.INFO, "Organization Name Verified");

		// identify org link and click on it
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// delete the org created by clicking on delete link
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/../..//a[text()='del']")).click();
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
	public void createorgwithindustryandtype() throws InterruptedException, IOException {

		// Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		UtilityObjectClass.test.get().log(Status.INFO, "Fetched Random Number");

		// Fetching data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("Organization", 4, 2) + randomint;
		String industry = exutil.fetchDataFromExcel("Organization", 4, 3);
		String type = exutil.fetchDataFromExcel("Organization", 4, 4);
		UtilityObjectClass.test.get().log(Status.INFO, "Fetch Data From Excel File");

		CrmHomePomPage home = new CrmHomePomPage(driver);

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"), "validating the home page");
		UtilityObjectClass.test.get().log(Status.INFO, "Validating Home Page Using Soft Assert");

		// identify organizations link and click on it
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// identify org plus icon and click on it
		OrganizationPomPage orgplucicon = new OrganizationPomPage(driver);
		orgplucicon.getOrgPlusIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// identify org name TF and pass text in it
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgnameTF(orgname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Organization Name");

		// identify industry dropdown and the option from it
		WebElement indDD = createorg.getIndustryDD();
		wutil.selectDD_OptionUsingValue(indDD, industry);
		UtilityObjectClass.test.get().log(Status.INFO, "Selected Option From Industry DD");

		// identify type dropdown and select option from it
		WebElement typeDD = createorg.getTypeDD();
		wutil.selectDD_OptionUsingValue(typeDD, type);
		UtilityObjectClass.test.get().log(Status.INFO, "Selected Option From Type DD");

		// identify save btn and click on it
		createorg.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Organization");

		// identify org info page header and verify org name
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verifyorg = orginfo.getVerifyOrgName();
		Assert.assertTrue(verifyorg.contains(orgname), "validating the org name");
		UtilityObjectClass.test.get().log(Status.INFO, "Organization Name Verified");

		// verify industry
		String verifyind = orginfo.getVerifyIndustry();
		Assert.assertTrue(verifyind.contains(industry), "validating the industry DD");
		UtilityObjectClass.test.get().log(Status.INFO, "Organization Industry Verified");

		// verify type
		String verifytype = orginfo.getVerifytype();
		Assert.assertTrue(verifytype.contains(type), "validating the type DD");
		UtilityObjectClass.test.get().log(Status.INFO, "Organization Type Verified");

		// identify org link and click on it
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// delete the org created by clicking on delete link
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/../..//a[text()='del']")).click();
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
	public void createorgwithphone() throws InterruptedException, IOException {

		// Fetch the random int
		JavaUtilities jutil = new JavaUtilities();
		int randomint = jutil.fetchRandomNumber();
		UtilityObjectClass.test.get().log(Status.INFO, "Fetched Random Number");

		// Fetching the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("Organization", 7, 2) + randomint;
		String orgphno = exutil.fetchDataFromExcel("Organization", 7, 3);
		UtilityObjectClass.test.get().log(Status.INFO, "Fetch Data From Excel File");

		CrmHomePomPage home = new CrmHomePomPage(driver);

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.gethomeheader().contains("Home"), "validating the home page");
		UtilityObjectClass.test.get().log(Status.INFO, "Validating Home Page Using Soft Assert");

		// identify organizations link and click on it
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// identify org plus icon and click on it
		OrganizationPomPage orgplusicon = new OrganizationPomPage(driver);
		orgplusicon.getOrgPlusIcon();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Plus Icon");

		// identify org name TF and pass text in it
		CreateOrganizationPomPage createorg = new CreateOrganizationPomPage(driver);
		createorg.getOrgnameTF(orgname);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Organization Name");

		// identify phone TF and enter no into it
		createorg.getPhnoTF(orgphno);
		UtilityObjectClass.test.get().log(Status.INFO, "Entered Phone Number ");

		// identify save btn and click on it
		createorg.getSaveBtn();
		UtilityObjectClass.test.get().log(Status.INFO, "Saved Contact");

		// identify org info page header and verify org name
		OrgInfoPomPage orginfo = new OrgInfoPomPage(driver);
		String verifyorg = orginfo.getVerifyOrgName();
		Assert.assertTrue(verifyorg.contains(orgname), "validating the orgname in org");
		UtilityObjectClass.test.get().log(Status.INFO, "Organization Name Verified");

		// verify phoneno
		String verifyphone = orginfo.getVerifyPhno();
		Assert.assertTrue(verifyphone.contains(orgphno), "validating the org phno");
		UtilityObjectClass.test.get().log(Status.INFO, "Phone Number Verified");

		// identify org link and click on it
		home.getorganizationtab();
		UtilityObjectClass.test.get().log(Status.INFO, "Clicked On Organization tab");

		// delete the org created by clicking on delete link
		driver.findElement(By.xpath("//a[text()='" + orgname + "']/../..//a[text()='del']")).click();
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
