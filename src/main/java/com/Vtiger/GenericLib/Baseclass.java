package com.Vtiger.GenericLib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import ObjectRepo.HomePage;
import ObjectRepo.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;



public class Baseclass implements IAutoConstant 
{
	public static WebDriver driver;
	public FileUtility fileutility= new FileUtility();
	public HomePage hp;
	public static WebDriver sdriver;
	
	@BeforeSuite(groups={"smoke","sanity","regression"})
	public void startconnection()
	{
		System.out.println("Started the connection with Database");
	}
	
	@AfterSuite(groups={"smoke","sanity","regression"})
	public void closeconnection()
	{
		System.out.println("Close the Database Connection");
	}
	
	
	/*
	 * For cross browser and distributed parallel  @parameters tag should be added and browser should be included in the tag,
	 * and also we should add String browsername in  public void launchbrowser(String browsername)
	 * And also we need to disable String browsername in the method which is linking to propertyfile
	 * 
	 * But for normal testing we should disable the parameter tag and also should remove the String browser name in the method
	 * String browser name linking to property file should be enabled
	 */
	
	
   //	@Parameters("browser")
	
    @BeforeClass(groups={"smoke","sanity","regression"})
    public void launchbrowser() throws Throwable
    {
		
    	String browsername=fileutility.readDatafromPropfile("browser");
		
    	if
    	(browsername.equalsIgnoreCase("chrome"))
    	{
    		WebDriverManager.chromedriver().setup();
    		driver = new ChromeDriver();
    	}
    	else if
    	(browsername.equalsIgnoreCase("Firefox"))
    	{
    		WebDriverManager.firefoxdriver().setup();
    		driver = new FirefoxDriver();
    	}
    	else if
    	(browsername.equalsIgnoreCase("safari"))
    	{
    		WebDriverManager.safaridriver().setup();
    		driver = new SafariDriver();
    	}
    	
    	else if
    	(browsername.equalsIgnoreCase("edge"))
    	{
    		WebDriverManager.edgedriver().setup();
    		driver = new EdgeDriver();
    	}
    	
    	else
    	{
    		System.out.println("No browser attached");
    	}
    	
    	sdriver=driver;
    	driver.get(fileutility.readDatafromPropfile("URL"));
    	driver.manage().window().maximize();
    }
	
	
    @BeforeMethod(groups={"smoke","sanity","regression"})
    public void logindetails() throws Throwable
    {
    	
    	LoginPage lp= new LoginPage(driver);
    	lp.logintoApp();
    	
    }
    
    
    @AfterMethod(groups={"smoke","sanity","regression"})
    public void logout()
    {
    	hp = new HomePage(driver);
    	hp.logoutfromApp();
    }
    
    
	@AfterClass(groups={"smoke","sanity","regression"})
	public void closebrowser()
	{
		driver.close();
	}
	
	
	
}
