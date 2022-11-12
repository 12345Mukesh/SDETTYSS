package com.Vtiger.GenericLib;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ListenerImplementation implements ITestListener
{

	public void onTestSuccess(ITestResult result)
	{
		 Screenshot screen= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(Baseclass.sdriver);
			
		  
			try {
				ImageIO.write(screen.getImage(), "PNG", new File("./screenshot/FullPagePassedScreenshot1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void onTestFailure(ITestResult result) 
	{
		String failedTestCase = result.getMethod().getMethodName();
		String currentDate = new Date().toString().replace(":", "_").replace(" ", "_");
		
		/*
		EventFiringWebDriver ed=new EventFiringWebDriver(Baseclass.sdriver);
		File src = ed.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshot/"+failedTestCase+currentDate+".PNG");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		  Screenshot screen= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(Baseclass.sdriver);
			
		  
			try {
				ImageIO.write(screen.getImage(), "PNG", new File("./ScreenShot"+failedTestCase+currentDate+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		   Reporter.log("Screenshot of Failed Testscript is stored in Screenshot Folder");
		
		
	}
    
}
