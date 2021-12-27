package scripts;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import generic.BaseTest;
import generic.Excel;
import generic.WebUtil;
import pages.LoginPage;

public class InvalidLogin extends BaseTest {
	
	@Test(priority = 2,groups={"login"})
	public void testInvalidLogin() {
		ExtentTest test = report.createTest("InvalidLogin");
		test.log(Status.INFO,"reading test data from xl");
		
		 String un=Excel.getData(XL_PATH, "InvalidLogin", 1, 0);
		 String pw=Excel.getData(XL_PATH, "InvalidLogin", 1, 1);
		 String failMSG=Excel.getData(XL_PATH, "InvalidLogin", 1, 2);
		 
		 
//		   1. Enter Invalid User Name -abcd
		 test.log(Status.INFO,"Enter invalid User Name");
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setUserName(un);
			
			
//		    2. Enter Invalid Password- xyz
			 test.log(Status.INFO,"Enter invalid Password");
			loginPage.setPassword(pw);
			
//		    3. click on login button
			 test.log(Status.INFO,"click on login button");
			loginPage.clickLoginButton();
			
//		    4. Verify that Error Msg is Displayed
			boolean result = loginPage.verifyErrMsgDisplayed(wait);
			softAssert.assertTrue(result, failMSG);

			if(result)
			{
				 test.log(Status.PASS,"Error Msg is Displayed");
			}
			else
			{
				test.log(Status.FAIL,"Error Msg  is NOt Displayed");
			}
			
			WebUtil.getScreenShot(driver,"./target/page1.png");
			test.addScreenCaptureFromPath("page1.png");
			
			softAssert.assertAll();
			
	}
}
