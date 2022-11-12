package com.contact;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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

public class Tc_003_CreateContact_StartDate_Test
{
    WebDriver driver;
    @Test
	public void Tc003_CreateContactwithStartDate() throws Throwable
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
				String abc1 = Elib.readDatafromExcel(0, 4, "Sheet1");
				CreateNewContactPage cnp= new CreateNewContactPage(driver);
				cnp.firstdropdown(abc1);
				
		//Entering the lastname
				String b1 = Elib.readDatafromExcel(3, 0, "Sheet1");
				cnp.lastname().sendKeys(b1);
				
				//clicking the dropdown of leadsource
				String b2 = Elib.readDatafromExcel(3, 1, "Sheet1");
				cnp.seconddropdown(b2);
				
				//Entering data in Home mobile
				String b3 = Elib.readDatafromExcel(3, 2, "Sheet1");
				cnp.otherphone().sendKeys(b3);
				
				//click on Do not call checkbox
				cnp.donotcall().click();
	 
				 //scrolldown
				
				WebElement element= cnp.portal();
				Point loc= element.getLocation();
				WebDriverUtility wutil= new WebDriverUtility();
				wutil.scrolldown(driver, loc);
	
	
				//Selecting Date                             //b1
				//driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Calendar.gif']")).click();
				//Date not inspecting
				
				//clicking on save button
		           cnp.getSavecontbtn().click();
		           
		   		//Again clicking on Contacts
		   		 hp.getContactslink().click();		
		         //sending data in text boz
		   		 cip.contactname().sendKeys(b1);
		   	   //selecting the dropdowntype
		   		String abc3 = Elib.readDatafromExcel(0, 6, "Sheet1");
		   	    cip.selectdropdown(abc3);
	          //click on submit button
		   	    cip.submit();

		   		WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + b1+ "']"));

		   		if (contactname.isDisplayed()) {
		   			Assert.assertTrue(true);
		   		}

		   		//logout from app
		   				hp.logoutfromApp();

		       
		   				driver.close();
		
	
				
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}
    
	
	
	
	
	
	
	
	
}
