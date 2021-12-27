package generic;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class WebUtil {

	public static void getScreenShot(WebDriver driver,String imgPath) {
		TakesScreenshot t=(TakesScreenshot)driver;
		File srcImg = t.getScreenshotAs(OutputType.FILE);
		File dstImg=new File(imgPath);
		try 
		{
			FileUtils.copyFile(srcImg, dstImg);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
