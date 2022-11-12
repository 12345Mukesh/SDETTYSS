package ObjectRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Vtiger.GenericLib.WebDriverUtility;



public class OrgInfoPage 
{

	WebDriver driver;
	
	public OrgInfoPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//img[@title='Create Organization...']") private WebElement Createorg;
	
	@FindBy(xpath="//input[@class='txtBox']") private WebElement orgname;
	
	@FindBy(name="search_field") private WebElement searchfldDropdown;
	
	@FindBy(name="submit") private WebElement submit;
	
	
	
	public void createorg()
	{
		Createorg.click();
	}
	
	
	public WebElement orgname()
	{
		return orgname;
	}
	
	
	public void selectdropdown(String text)
	{
	  WebDriverUtility wutil = new WebDriverUtility();
	wutil.selectelementfromDropdown(searchfldDropdown,text);
	}
	
	public void submit()
	{
		submit.click();
	}
	
	
	
	
	
	
	
}
