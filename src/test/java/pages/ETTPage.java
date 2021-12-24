package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ETTPage {

	@FindBy(xpath = "//td[text()='Enter Time-Track']")
	private WebElement welcomeMSg;
	
	public ETTPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public boolean verifyWelcomeIsDisplayed(WebDriverWait wait) {
		
		try
		{
			wait.until(ExpectedConditions.visibilityOf(welcomeMSg));
			return true;
		}
		catch (Exception e) 
		{
			return false;
		}
		
	}
}
