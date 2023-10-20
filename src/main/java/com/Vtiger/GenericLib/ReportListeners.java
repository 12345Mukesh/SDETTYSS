package com.Vtiger.GenericLib;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportListeners implements ITestListener
	{

		public static ExtentReports extent;
		public static ExtentTest test;
		public static WebDriver driver;
		
		
		public void onTestStart(ITestResult result) {
			test=extent.createTest(result.getName());
			test.log(Status.INFO, result.getName()+" Method Started");

		}
		
		public void onTestSuccess(ITestResult result)
		{
			test=extent.createTest(result.getName());
			test.pass(MarkupHelper.createLabel(result.getName()+" case has PASSED", ExtentColor.GREEN));
			WebDriverUtility wlib = new WebDriverUtility();
			 
			try {
				wlib.getFullPageScreenshot(result.getName());
				test.addScreenCaptureFromPath(wlib.getFullPageScreenshot(result.getName()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		public void onTestFailure(ITestResult result) 
		{
			test=extent.createTest(result.getName());
			test.fail(MarkupHelper.createLabel(result.getName()+" case has FAILED because of following reasons: ", ExtentColor.RED));
			test.fail(result.getThrowable());
			
				String failedTestCase = result.getMethod().getMethodName();
				String currentDate = new Date().toString().replace(":", "_").replace(" ", "_");
				EventFiringWebDriver ed=new EventFiringWebDriver(Baseclass.sdriver);
				File src = ed.getScreenshotAs(OutputType.FILE);
				File dest = new File("./Screenshot/"+failedTestCase+currentDate+".PNG");
				try {
					FileUtils.copyFile(src, dest);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			  
			
		}
		public void onTestSkipped(ITestResult result) {
			test=extent.createTest(result.getName());
			test.skip(MarkupHelper.createLabel(result.getName()+" case has SKIPPED because of following reasons: ", ExtentColor.PINK));
			test.skip(result.getThrowable());
		}

		public void onStart(ITestContext context) 
		{
			ExtentSparkReporter spark = new ExtentSparkReporter("C:\\Users\\Mukesh\\eclipse-workspace\\SDETTYSS\\Tc_001_CreateOrg_DD_Test.html");
			//Customising report view
			spark.config().setDocumentTitle("VTiger Reports");
			spark.config().setReportName("Extent Reports 5");
			spark.config().setTheme(Theme.DARK);
			extent = new ExtentReports();
			extent.attachReporter(spark);
			//setting system info
			extent.setSystemInfo("Author", "Mukesh");
			extent.setSystemInfo("OS", "Windows 11");
			extent.setSystemInfo("Browser", "Chrome");
			extent.setSystemInfo("Test Name", context.getName());
			
		}

		public void onFinish(ITestContext context) 
		{
			//Writing all logs into report
			extent.flush();
		}

	

	}

	
	
	
	
	
	
	
	

