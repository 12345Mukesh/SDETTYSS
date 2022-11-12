package com.contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Vtiger.Generic.ExcelUtility;
import com.Vtiger.Generic.FileUtility;
import com.Vtiger.Generic.WebDriverUtility;
import com.Vtiger.ObjectRepo.ContactInfopage;
import com.Vtiger.ObjectRepo.CreateNewContactPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.LoginPage;

public class Tc_002_CreateContact_Reports_Test
{
   WebDriver driver;
   @Test
   public void Tc002_CreateContactwithReports() throws Throwable
   {
	   FileUtility flib= new FileUtility();
		ExcelUtility Elib= new ExcelUtility();
		//open the browser
		String browsername = flib.readDatafromPropfile("browser");
		if (browsername.equals("chrome")) {
			driver = new ChromeDriver();
			System.out.println("chrome is opened");
		} else if (browsername.equals("ff")) {

		} else {
			System.out.println("please enter proper browser name");
		}
		
		//Enter the url
		driver.get(flib.readDatafromPropfile("url"));

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);           
		driver.manage().window().maximize();                                       
		
      //Giving username and password
		//Step 2 Login to app
		LoginPage lp= new LoginPage(driver);
		lp.logintoApp();
		
		 HomePage hp =new HomePage(driver);
	     hp.getContactslink().click();
			
	     ContactInfopage cip= new ContactInfopage(driver);
	     cip.createcont();  
	     
		//selecting first name in dropdown
				String a1 = Elib.readDatafromExcel(2, 0, "Sheet1");
				CreateNewContactPage cnp= new CreateNewContactPage(driver);
				cnp.firstdropdown(a1);
				
				String a2 = Elib.readDatafromExcel(2, 1, "Sheet1");
				cnp.firstname().sendKeys(a2);
				
	   //selecting the last name in the dropdown
				String a3 = Elib.readDatafromExcel(2, 2, "Sheet1");
				cnp.lastname().sendKeys(a3);
	   
		//selecting the organisation using window handle
				WebElement abc = cnp.text();
				abc.click();
				cnp.WindowHandle();
	            //Entering the office phone
				String a4 = Elib.readDatafromExcel(2, 3, "Sheet1");
				cnp.officephone().sendKeys(a4);
	   
				
	           //Reports to Window Handle
				
				WebElement abc1 = cnp.text1();
				abc1.click();
                WebDriverUtility wutil= new WebDriverUtility();
                wutil.switchTowindow(driver, "specificcontactaddress");
				
	   
	            //selecting the secondary email	
				String a5 = Elib.readDatafromExcel(2, 4, "Sheet1");
				cnp.secondaryEmail().sendKeys(a5);
				
				//Assign to Group click radio button   
				cnp.radiobtn().click();                 
				
				//clicking on save button
		           cnp.getSavecontbtn().click();
		   		//Again clicking on Contacts
		   		 hp.getContactslink().click();		
		         //sending data in text boz
		   		 cip.contactname().sendKeys(a3);
		   	   //selecting the dropdowntype
		   		String abc3 = Elib.readDatafromExcel(0, 6, "Sheet1");
		   	    cip.selectdropdown(abc3);
	          //click on submit button
		   	    cip.submit();

		   		WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + a3+ "']"));

		   		if (contactname.isDisplayed()) {
		   			Assert.assertTrue(true);
		   		}

		   		//logout from app
		   				hp.logoutfromApp();

		       
		   				driver.close();
			
   }
	
	
	
	
	
	
	
}
