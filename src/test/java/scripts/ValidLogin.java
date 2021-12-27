package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import generic.BaseTest;
import generic.Excel;
import pages.ETTPage;
import pages.LoginPage;


public class ValidLogin extends BaseTest {
	
	@Test(priority = 1,groups={"smoke","login"})
	public void testValidLogin() {
		ExtentTest test = report.createTest("ValidLogin");
		test.log(Status.INFO,"reading test data from xl");
		//this is to get data from xl
		 String un=Excel.getData(XL_PATH, "ValidLogin", 1, 0);
		 String pw=Excel.getData(XL_PATH, "ValidLogin", 1, 1);
		 String failMSG=Excel.getData(XL_PATH, "ValidLogin", 1, 2);
		 
//	    1. Enter Valid User Name
		 test.log(Status.INFO,"Enter Valid User Name");
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
		
//	    2. Enter Valid Password
		 test.log(Status.INFO,"Enter Valid Password");
		loginPage.setPassword(pw);
		
//	    3. click on login button
		 test.log(Status.INFO,"click on login button");
		loginPage.clickLoginButton();
		
//	    4. Verify that Home Page is Displayed
		ETTPage ettPage=new ETTPage(driver);
		boolean result = ettPage.verifyWelcomeIsDisplayed(wait);
		if(result)
		{
			 test.log(Status.PASS,"Home Page is Displayed");
		}
		else
		{
			test.log(Status.FAIL,"Home Page is NOt Displayed");
			Assert.fail(failMSG);
		}
	}
}