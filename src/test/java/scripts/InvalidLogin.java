package scripts;

import org.testng.annotations.Test;

import generic.BaseTest;
import pages.LoginPage;

public class InvalidLogin extends BaseTest {
	
	@Test(priority = 2,groups={"login"})
	public void testInvalidLogin() {
//		   1. Enter Invalid User Name -abcd
			LoginPage loginPage=new LoginPage(driver);
			loginPage.setUserName("abcd");
//		    2. Enter Invalid Password- xyz
			loginPage.setPassword("xyz");
//		    3. click on login button
			loginPage.clickLoginButton();
//		    4. Verify that Error Msg is Displayed
			boolean result = loginPage.verifyErrMsgDisplayed(wait);
			softAssert.assertTrue(result);
			softAssert.assertAll();
	}
}
