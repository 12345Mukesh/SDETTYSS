package com.Contact;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Vtiger.GenericLib.ExcelUtility;
import com.Vtiger.GenericLib.FileUtility;
import com.Vtiger.GenericLib.IAutoConstant;

import ObjectRepo.ContactInfopage;
import ObjectRepo.CreateNewContactPage;
import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;


@Listeners(com.Vtiger.GenericLib.ListenerImplementation.class)
public class Tc_001_CreateContact_Email_Test
{     
	WebDriver driver;
	
	@Test
	public void Tc001_CreateContactwithEmail() throws Throwable
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
	     
	     //selecting the dropdown
	     String abc1 = Elib.readDatafromExcel(0, 4, "Sheet1");
			CreateNewContactPage cnp= new CreateNewContactPage(driver);
			cnp.firstdropdown(abc1);
		
		
		String abc4 = Elib.readDatafromExcel(1, 3, "Sheet1");
		String abc5 = Elib.readDatafromExcel(1, 2, "Sheet1");
		cnp.lastname().sendKeys(abc4);
		cnp.mobile().sendKeys(abc5);
		
		//selecting title and department
		String abc6 = Elib.readDatafromExcel(1, 4, "Sheet1");
		cnp.title().sendKeys(abc6);
		String abc7 = Elib.readDatafromExcel(1, 5, "Sheet1");
		cnp.department().sendKeys(abc7);
		
		//selecting Email id and Emailoptout
		
		String abc8 = Elib.readDatafromExcel(1, 6, "Sheet1");
		cnp.email().sendKeys(abc8);
		
		cnp.emailcheckbox().click();
		
		//clicking on Reference checkbox
	           cnp.reference().click();
		
		//clicking on save button
	           cnp.getSavecontbtn().click();
	           
	   		//Again clicking on Contacts
	   		 hp.getContactslink().click();		
	         //sending data in text boz
	   		 cip.contactname().sendKeys(abc4);
	   	   //selecting the dropdowntype
	   		String abc3 = Elib.readDatafromExcel(0, 6, "Sheet1");
	   	    cip.selectdropdown(abc3);
          //click on submit button
	   	    cip.submit();

	   		WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + abc4+ "']"));

	   		if (contactname.isDisplayed()) {
	   			Assert.assertTrue(true);
	   		}

	   		//logout from app
	   				hp.logoutfromApp();

	       
	   				driver.close();
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
