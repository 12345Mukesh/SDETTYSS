package ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Vtiger.GenericLib.Baseclass;
import com.Vtiger.GenericLib.WebDriverUtility;



public class ContactInfopage extends Baseclass
{

	WebDriver sdriver;

	
	public ContactInfopage(WebDriver driver) 
	{
		this.driver = sdriver;
		PageFactory.initElements(sdriver, this);
	}


	@FindBy(xpath="//img[@title='Create Contact...']") private WebElement Createcont;
	
	@FindBy(xpath="//input[@class='txtBox']") private WebElement contactname;
	
	@FindBy(id="bas_searchfield") private WebElement searchDropdown;
	
	@FindBy(name="submit") private WebElement submit;
	
	public void createcont() 
	{
		Createcont.click();
		
	}
		
	public WebElement contactname()
	{
		return contactname;
	}
	
	
	public void selectdropdown(String text)
	{
	  WebDriverUtility wutil = new WebDriverUtility();
	wutil.selectelementfromDropdown(searchDropdown,text);
	}
	
	public void submit()
	{
		submit.click();
	}


	
	
	
}
