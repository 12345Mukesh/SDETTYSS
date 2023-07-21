package com.Vtiger.PomScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Vtiger.GenericLib.Baseclass;
import com.Vtiger.GenericLib.JavaUtility;
import com.Vtiger.GenericLib.WebDriverUtility;

import ObjectRepo.CreateNewOrg;
import ObjectRepo.HomePage;
import ObjectRepo.OrgInfoPage;

@Listeners(com.Vtiger.GenericLib.ReportListeners.class)

public class Tc_001_CreateOrg_DD_Test extends Baseclass
{

	@Test
	public void Tc001_CreateOrgDDTest() throws Throwable
	{
		JavaUtility jv = new JavaUtility();	
		int randomnumber =jv.generateRandomNo();

		String orgname="QSPHYD"+randomnumber;
		System.out.println(orgname);


		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		//step 3 click on org link
		hp = new HomePage(driver);
		hp.getOrglink().click();

		//step 4 click on + btn
		OrgInfoPage oip= new OrgInfoPage(driver);
		oip.createorg();


		//Step 5 Fill the details and select value from DD
		// organaization name
		CreateNewOrg cno= new CreateNewOrg(driver);
		cno.getOrgname().sendKeys(orgname);

		cno.getIndustry("Education");

		WebDriverUtility webulity = new WebDriverUtility();
		WebElement ratingDropdown=driver.findElement(By.name("rating"));
		webulity.selectelementfromDropdown(ratingDropdown, "Active");


		WebElement typeDropdown=driver.findElement(By.name("accounttype"));
		webulity.selectelementfromDropdown(typeDropdown, 3);

		cno.getSaveorgbtn().click();

		Thread.sleep(3000);

		//Click on org link
		hp.getOrglink().click();
		//sending data in text box
		oip.orgname().sendKeys(orgname);

		//clicking on dropdown and selecting organization name
		oip.selectdropdown("Organization Name");

		//click on submit button
		oip.submit();

		WebElement actualelement=driver.findElement(By.xpath("//a[@title='Organizations' and text()='"+orgname+"']"));

		System.out.println(actualelement.isDisplayed());

		Assert.assertEquals(actualelement.isDisplayed(), true);






	}


}
