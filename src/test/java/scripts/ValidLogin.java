package scripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import pages.ETTPage;
import pages.LoginPage;

public class ValidLogin extends BaseTest {
	
	@Test(priority = 1,groups={"smoke","login"})
	public void testValidLogin() {
		 String un=Excel.getData(XL_PATH, "ValidLogin", 1, 0);
		 String pw=Excel.getData(XL_PATH, "ValidLogin", 1, 1);
		 String failMSG=Excel.getData(XL_PATH, "ValidLogin", 1, 2);
		 
//	    1. Enter Valid User Name
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(un);
		
//	    2. Enter Valid Password
		loginPage.setPassword(pw);
		
//	    3. click on login button
		loginPage.clickLoginButton();
		
//	    4. Verify that Home Page is Displayed
		ETTPage ettPage=new ETTPage(driver);
		boolean result = ettPage.verifyWelcomeIsDisplayed(wait);
		Assert.assertTrue(result,failMSG);
	}

}
