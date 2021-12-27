package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConst{
	public WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert softAssert;
	public String xlfile_path;
	public static ExtentReports report;
	static 
	{
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
	}
	
	
	@BeforeSuite
	public void initReport() {
		report=new ExtentReports();
		ExtentSparkReporter format=new ExtentSparkReporter(REPORT_PATH);
		report.attachReporter(format);
	}
	
	@AfterSuite
	public void endReport() {
		report.flush();
	}
	
	@Parameters({"xlfile","useGrid","hubURL","browser"})
	@BeforeMethod(alwaysRun = true)
	public void openApp(@Optional("input_qa.xlsx") String xlfile,@Optional("no") String useGrid,@Optional("") String hubURL,@Optional("chrome") String browser) throws MalformedURLException {
		xlfile_path=XL_PATH+xlfile;
		
		if(useGrid.equalsIgnoreCase("yes"))
		{
			Reporter.log("Using the Grid",true);
			URL remoteAddress=new URL(hubURL);
			
			DesiredCapabilities capability=new DesiredCapabilities();
			capability.setBrowserName(browser);
			driver=new RemoteWebDriver(remoteAddress, capability);
		}
		else
		{
			Reporter.log("Using the Local System;Browser:"+browser,true);
			
			if (browser=="chrome") 
			{
				
				driver=new ChromeDriver();
				
			}
			else
			{
				driver=new FirefoxDriver();
				
	         }
				
//			switch (browser)
//			{
//				case "chrome":
//								driver=new ChromeDriver();
//								break;
//	
//				default:
//								driver=new FirefoxDriver();
//								break;
//			}
			
		}
			
	
		
		softAssert=new SoftAssert();
		long ETO=Long.parseLong(Property.getProperty(PPT_PATH,"ETO"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(ETO));
		Reporter.log("Setting ETO:"+ETO,true);
		
		long ITO=Long.parseLong(Property.getProperty(PPT_PATH,"ITO"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		
		String App_URL=Property.getProperty(PPT_PATH,"URL");
		driver.get(App_URL);
		
		driver.manage().window().maximize();

	}
	
	@AfterMethod(alwaysRun = true)
	public void closeApp() {
		Reporter.log("Closing the Browser",true);
		driver.quit();
	}
 
}