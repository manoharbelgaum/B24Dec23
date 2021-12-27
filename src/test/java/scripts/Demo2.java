package scripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Demo2 {
	
	public static void main(String[] args) {
		//Extent Report
		ExtentReports report=new ExtentReports();
		//Report Format/template
		ExtentSparkReporter format=new ExtentSparkReporter("./target/MyReport.html");
		report.attachReporter(format);
		
		ExtentTest test1 = report.createTest("Test1");
		test1.log(Status.PASS,"This is step1");
		test1.log(Status.PASS,"This is step2");
		test1.log(Status.PASS,"This is step3");
		test1.log(Status.PASS,"This is step4");
		
		ExtentTest test2 = report.createTest("Test2");
		test2.log(Status.INFO,"This is info");
		test2.log(Status.SKIP,"This is SKIP");
		test2.log(Status.PASS,"This is FAIL");
		test2.log(Status.FAIL,"This is FAIL");

		
		//Publish the report
		report.flush();
	}

}
