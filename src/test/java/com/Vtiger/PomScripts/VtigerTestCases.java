package com.Vtiger.PomScripts;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.Vtiger.GenericLib.Baseclass;
import com.Vtiger.GenericLib.ExcelUtility;
import com.Vtiger.GenericLib.JavaUtility;
import com.Vtiger.GenericLib.WebDriverUtility;

import ObjectRepo.ContactInfopage;
import ObjectRepo.CreateNewContactPage;
import ObjectRepo.CreateNewOrg;
import ObjectRepo.HomePage;
import ObjectRepo.OrgInfoPage;



public class VtigerTestCases extends Baseclass {
	
	@Test(groups="smoke")
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

		cip.submit();

		WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + abc4+ "']"));

		if (contactname.isDisplayed()) {
			Assert.assertTrue(true);
			Reporter.log(orgname+"SuccessfullyEntered");
		}
		
	}
		@Test(groups="sanity")
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
			
			Reporter.log(orgname+"Successfully Entered", true);
		}

		
		@Test(groups="regression")
		public void CreateContactTestWindowHandle() throws Throwable 
		{

			ExcelUtility Elib= new ExcelUtility();
			//open the browser


			hp =new HomePage(driver);
			hp.getContactslink().click();

			ContactInfopage cip= new ContactInfopage(driver);
			cip.createcont();  

			//clicking on dropdown

			String abc1 = Elib.readDatafromExcel(0, 4, "Sheet1");
			CreateNewContactPage cnp= new CreateNewContactPage(driver);
			cnp.firstdropdown(abc1);

			String abc4 = Elib.readDatafromExcel(1, 1, "Sheet1");
			String abc5 = Elib.readDatafromExcel(1, 2, "Sheet1");
			cnp.lastname().sendKeys(abc4);
			cnp.mobile().sendKeys(abc5);

			//Window Handling
			WebElement abc = driver.findElement(By.xpath("(//img[@title='Select'])[1]"));
			abc.click();

			Set<String> windows = driver.getWindowHandles();
			Iterator<String> window = windows.iterator();

			String parentWindow = window.next();
			String childWindow = window.next();

			driver.switchTo().window(childWindow);

			driver.findElement(By.id("search_txt")).sendKeys(Elib.readDatafromExcel(1, 0, "Sheet1"));

			driver.findElement(By.name("search")).click();

			Thread.sleep(2000);

			driver.findElement(By.xpath("//a[@id='1' and text()='" + Elib.readDatafromExcel(1, 0, "Sheet1") + "']"))
			.click();

			driver.switchTo().window(parentWindow);

			String abc2 = Elib.readDatafromExcel(0, 5, "Sheet1");
			cnp.seconddropdown(abc2);

			cnp.getSavecontbtn().click();

			//Again clicking on Contacts
			hp.getContactslink().click();		
			//sending data in text boz
			cip.contactname().sendKeys(abc4);

			String abc3 = Elib.readDatafromExcel(0, 6, "Sheet1");
			cip.selectdropdown(abc3);

			cip.submit();

			WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + abc4+ "']"));

			if (contactname.isDisplayed()) {
				Assert.assertTrue(true);
			}
		   Reporter.log(abc4+"Contact got inserted", false);
		
		
		
		}
		
}
