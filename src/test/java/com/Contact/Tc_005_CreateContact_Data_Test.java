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
import com.Vtiger.ObjectRepo.ContactInfopage;
import com.Vtiger.ObjectRepo.CreateNewContactPage;
import com.Vtiger.ObjectRepo.HomePage;
import com.Vtiger.ObjectRepo.LoginPage;

public class Tc_005_CreateContact_Data_Test 
{
   WebDriver driver;
   
	@Test
	public void Tc_CreateContactDatawithAddress() throws Throwable
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
				String d1 = Elib.readDatafromExcel(5, 0, "Sheet1");
				CreateNewContactPage cnp= new CreateNewContactPage(driver);
				cnp.firstdropdown(d1);
		
				//selecting the first name
				String d2 = Elib.readDatafromExcel(5, 1, "Sheet1");
				cnp.firstname().sendKeys(d2);
		
				//Entering the lastname
				String d3 = Elib.readDatafromExcel(5, 2, "Sheet1");
				cnp.lastname().sendKeys(d3);
				
		//selecting the organisation using window handle
				WebElement abc = cnp.text();
				abc.click();

				Set<String> windows = driver.getWindowHandles();
				Iterator<String> window = windows.iterator();

				String parentWindow = window.next();
				String childWindow = window.next();

				driver.switchTo().window(childWindow);

				driver.findElement(By.id("search_txt")).sendKeys(Elib.readDatafromExcel(1, 0, "Sheet1"));

				driver.findElement(By.name("search")).click();

				//selecting from the list in whole table, select 7th table and all the rows and select 1st column
				List<WebElement> names=	driver.findElements(By.xpath("(//table)[7]//tr[*]//td//a"));
				//In that what the element id is there give in the get
				names.get(4).click();
					driver.switchTo().window(parentWindow);   
				
				
				//clicking the dropdown of leadsource
				String d5 = Elib.readDatafromExcel(5, 3, "Sheet1");
				cnp.seconddropdown(d5);
				
				
				//Entering mobile number
				String d6 = Elib.readDatafromExcel(5, 4, "Sheet1");
				cnp.mobile().sendKeys(d6);
				
				
				//click checkbox of Do not call
				cnp.donotcall().click();
				
				//scrolldown
				WebElement element= cnp.mailingStreet();
				Point loc= element.getLocation();
				JavascriptExecutor jse= (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy"+loc);
	
				//Entering address 
				
				//Street
				String d7 = Elib.readDatafromExcel(5, 5, "Sheet1");
				cnp.mailingStreet().sendKeys(d7);

				//city
				String d8 = Elib.readDatafromExcel(5, 6, "Sheet1");
				driver.findElement(By.id("mailingcity")).sendKeys(d8);
				
				//State
				String d9 = Elib.readDatafromExcel(5, 7, "Sheet1");
				driver.findElement(By.id("mailingstate")).sendKeys(d9);
				
				//country
				String d10 = Elib.readDatafromExcel(5, 8, "Sheet1");
				driver.findElement(By.id("mailingcountry")).sendKeys(d10);
				
				
				//clicking on save button
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
	
				
//				//clicking on again contacts
				driver.findElement(By.xpath("//a[text()='Contacts']")).click();
				
				//Entering data into textbox
				driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(d3);
			
				
				
				//selecting value from dropdown
				String abc3 = Elib.readDatafromExcel(0, 6, "Sheet1");
				WebElement Indropdown = driver.findElement(By.id("bas_searchfield"));
				Select Indd = new Select(Indropdown);
				Indd.selectByVisibleText(abc3);

				//click on submit button
				driver.findElement(By.xpath("//input[@name='submit']")).click();

			WebElement contactname = driver.findElement(By.xpath("//a[@title='Contacts' and text()='" + d3+ "']"));

			if (contactname.isDisplayed()) 
			{
				Assert.assertTrue(true);
			}
		       
			driver.close();
	}	
	
	
	
	
}
