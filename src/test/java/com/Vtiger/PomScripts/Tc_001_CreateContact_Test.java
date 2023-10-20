package com.Vtiger.PomScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Vtiger.GenericLib.Baseclass;
import com.Vtiger.GenericLib.ExcelUtility;
import com.Vtiger.GenericLib.JavaUtility;

import ObjectRepo.ContactInfopage;
import ObjectRepo.CreateNewContactPage;
import ObjectRepo.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


@Listeners(com.Vtiger.GenericLib.ReportListeners.class)
public class Tc_001_CreateContact_Test extends Baseclass
{
	@Test()
	@Description("Creating new contact in the VTiger Application")
	@Epic("EP005")
	@Feature("Feature 1: New Contact Creating")
	@Story("Adding New contact and verifying in Home page")
	@Step("Contact Creating with Random number")
	@Severity(SeverityLevel.CRITICAL)
	public void TC001_CreateContact() throws Throwable
	{

		JavaUtility jv = new JavaUtility();	
		int randomnumber =jv.generateRandomNo();

		String orgname="QSPHYD"+randomnumber;
		System.out.println(orgname);



		ExcelUtility Elib= new ExcelUtility();	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		hp=new HomePage(driver);
		hp.getContactslink().click();

		ContactInfopage cip= new ContactInfopage(driver);
		cip.createcont();  

		//selecting the dropdown
		String abc1 = Elib.readDatafromExcel(0, 4, "Sheet1");
		CreateNewContactPage cnp= new CreateNewContactPage(driver);
		cnp.firstdropdown(abc1);

		String abc2=Elib.readDatafromExcel(3, 0, "Sheet1");
		cnp.firstname().sendKeys(abc2);


		//Entering the last name
		String abc4 = Elib.readDatafromExcel(1, 1, "Sheet1");
		cnp.lastname().sendKeys(abc4);

		cnp.getSavecontbtn().click();

		//Again clicking on Contacts
		hp.getContactslink().click();		
		//sending data in text boz
		cip.contactname().sendKeys(abc4);

		String abc3 = Elib.readDatafromExcel(0, 6, "Sheet1");
		cip.selectdropdown(abc3);
		
        Assert.assertTrue(true);
		cip.submit();

		WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + abc4+ "']"));

		if (contactname.isDisplayed()) {
			Assert.assertTrue(true);
		}













	}

}
