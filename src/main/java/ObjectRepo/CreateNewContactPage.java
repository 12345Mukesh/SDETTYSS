package ObjectRepo;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Vtiger.GenericLib.ExcelUtility;
import com.Vtiger.GenericLib.WebDriverUtility;



public class CreateNewContactPage 
{

	WebDriver driver;


	public CreateNewContactPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

    @FindBy(name="salutationtype") private WebElement saluation;
    
    @FindBy(name="firstname") private WebElement firstname;
	@FindBy(name="lastname") private WebElement lastname;
    
	@FindBy(id="mobile") private WebElement mobile;
	
	@FindBy(id="title") private WebElement title;
	
	@FindBy(id="department") private WebElement department;
	
	@FindBy(id="email") private WebElement email;
	
	@FindBy(name="emailoptout") private WebElement emailcheckbox;
	
	@FindBy(name="reference") private WebElement reference;
	
	@FindBy(id="phone") private WebElement phone;
	
	@FindBy(id="otherphone") private WebElement otherphone;
	
	@FindBy(name="donotcall") private WebElement dontcall;
	
	@FindBy(xpath="(//img[@title='Select'])[1]") private WebElement text;
	
	@FindBy(xpath="(//img[@title='Select'])[2]") private WebElement text1;
	
	@FindBy(xpath="(//textarea[@class='detailedViewTextBox'])[3]") private WebElement detailedview;
	
	@FindBy(name="portal") private WebElement portal;
	
	@FindBy(id="secondaryemail") private WebElement secondary;
	
	@FindBy(id="fax") private WebElement fax;
	
	@FindBy(xpath="//textarea[@name='mailingstreet']") private WebElement mailingStreet;
	
	@FindBy(name="notify_owner") private WebElement notifyowner;
	
	@FindBy(name="assigntype") private WebElement radiobutton;
	
	@FindBy(name="leadsource") private WebElement leadsource;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	
   public WebElement firstdropdown(String text)
   {
	 WebDriverUtility wutil= new WebDriverUtility();  
	 wutil.selectelementfromDropdown(saluation, text);
	return saluation;   
   }
	
   public WebElement firstname()
	{
	  return firstname;	
	}
   
   public WebElement lastname()
   {
	return lastname;
   }
   
   
   public WebElement mobile()
   {
	return mobile;
	   
   }
   
   public WebElement title()
   {
	   return title;
   }
   
   public WebElement department()
   {
	  return department;   
   }
   
   public WebElement email()
   {
	   return email;
   }
   
   public WebElement portal()
   {
	   return portal;
   }
   
   public WebElement emailcheckbox()
   {
	   return emailcheckbox;
   }
   
   public WebElement reference()
   {
	   return reference;
   }
   
   public WebElement officephone()
   {
	   return phone;
   }
   
   public WebElement notifyowner()
   {
	 return notifyowner;   
   }
   
 
   
   public WebElement otherphone()
   {
	   return otherphone;
   }
   
   public WebElement donotcall()
   {
	   return dontcall;
   }
   
   
   public WebElement secondaryEmail()
   {
	   return secondary;
   }
   
   
   public WebElement fax()
   {
	   return fax;
   }
   
   public WebElement detailedview()
   {
	   return detailedview;
   }
   
   
   public  WebElement radiobtn()
   {
	   return radiobutton;
   }
   
   public WebElement text()
    {
	      return text;
    }
   
   public WebElement text1()
   {
	   return text1;
   }
   
   
   public void WindowHandle() throws Throwable
   {
	   ExcelUtility Elib = new ExcelUtility();
	   Set<String> windows = driver.getWindowHandles();
		Iterator<String> window=windows.iterator();
		String parentWindow = window.next();
		String childWindow = window.next();

		driver.switchTo().window(childWindow);
		driver.findElement(By.id("search_txt")).sendKeys(Elib.readDatafromExcel(0, 0, "Sheet1"));
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[@id='1' and text()='" + Elib.readDatafromExcel(0, 0, "Sheet1") + "']"))
		.click();

		driver.switchTo().window(parentWindow);
   }
   
   
   public void WindowHandle1() throws Throwable
   {
	   ExcelUtility Elib = new ExcelUtility();
	   Set<String> allwindows = driver.getWindowHandles();
		Iterator<String> onewindow = allwindows.iterator();
		String parentWindow1 = onewindow.next();
		String childWindow1 = onewindow.next();

		driver.switchTo().window(childWindow1);
		String a=Elib.readDatafromExcel(1, 3, "Sheet1");
		driver.findElement(By.id("search_txt")).sendKeys(a);
		driver.findElement(By.name("search")).click();
		//selecting from the list in whole table, select 7th table and all the rows and select 1st column
	    List<WebElement> names=	driver.findElements(By.xpath("(//table)[7]//tr[*]//td[1]//a"));
	//In that what the element id is there give in the get
	    names.get(2).click();
		driver.switchTo().window(parentWindow1);
   }
   
   public void seconddropdown(String text1)
	{
		WebDriverUtility wutil= new WebDriverUtility();  
		 wutil.selectelementfromDropdown(leadsource, text1);	
	}
  
	public WebElement getSavecontbtn() {
		return savebtn;
	}

	

	public WebElement mailingStreet() 
	{
		return mailingStreet;  
	}
	
}
