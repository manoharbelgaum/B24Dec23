package generic;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IAutoConst{
	public WebDriver driver;
	public WebDriverWait wait;
	public SoftAssert softAssert;
	static 
	{
		WebDriverManager.chromedriver().setup();
	}
	@BeforeMethod (alwaysRun = true)
	public void openApp() {
		softAssert=new SoftAssert();
		
		driver=new ChromeDriver();
		
		long ETO=Long.parseLong(Property.getProperty(PPT_PATH,"ETO"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(ETO));
		Reporter.log("Setting ETO:"+ETO,true);
		
		long ITO=Long.parseLong(Property.getProperty(PPT_PATH,"ITO"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ITO));
		Reporter.log("Setting ITO:"+ITO,true);
		
		String App_URL=Property.getProperty(PPT_PATH,"URL");
		driver.get(App_URL);
		Reporter.log("URL:"+App_URL,true);
		
		driver.manage().window().maximize();

	}
	
	@AfterMethod (alwaysRun = true)
	public void closeApp() {
		Reporter.log("Closing the Browser",true);
		driver.quit();
	}
	
}