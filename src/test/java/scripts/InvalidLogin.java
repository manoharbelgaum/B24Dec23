package scripts;

import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import pages.LoginPage;

public class InvalidLogin extends BaseTest {
	
	@Test(priority = 2,groups={"login"})
	public void testInvalidLogin() {
		
		 String un=Excel.getData(XL_PATH, "InvalidLogin", 1, 0);
		 String pw=Excel.getData(XL_PATH, "InvalidLogin", 1, 1);
		 String failMSG=Excel.getData(XL_PATH, "InvalidLogin", 1, 2);
		 
		 
//		   1. Enter Invalid User Name -abcd
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setUserName(un);
//		    2. Enter Invalid Password- xyz
			loginPage.setPassword(pw);
//		    3. click on login button
			loginPage.clickLoginButton();
//		    4. Verify that Error Msg is Displayed
			boolean result = loginPage.verifyErrMsgDisplayed(wait);
			softAssert.assertTrue(result, failMSG);
			softAssert.assertAll();
	}
}
