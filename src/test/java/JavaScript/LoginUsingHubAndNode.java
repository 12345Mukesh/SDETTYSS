package JavaScript;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginUsingHubAndNode 
{

	 static WebDriver driver;
		
		@Test(priority=1)
		public void setup() throws MalformedURLException
		{
			String nodeurl="http://192.168.226.1:4444/wd/hub";
			
			DesiredCapabilities cap= DesiredCapabilities.firefox();
			cap.setBrowserName("Firefox");
			cap.setPlatform(Platform.WIN10);
			
			WebDriverManager.firefoxdriver().setup();
			driver=new RemoteWebDriver(new URL(nodeurl),cap);
				
		}

	  @Test(priority=2)
	 public void run()
	 {
		  driver.get("http://www.google.com");
          System.out.println("Golgole opened");
	 }
	
	
	
	
	
}
