package executableClass;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Login.Login_test.Login_Test;
import Utilties.GetScreenShot;

public class TestLoginExecutable extends Login_Test{ 
	
	@BeforeTest
	public static void Initialization() {
		
		initialization();
	}
	@Test(priority = 1)
	public static void LoginTRic() {
		test = Report.startTest("Nash");
		Login();
	}
	//Thread
	@AfterMethod
	 public void getResult(ITestResult result) throws IOException
	    {
	        if(result.getStatus() == ITestResult.FAILURE)
	        {
	            String screenShotPath = GetScreenShot.capture(driver);
	            test.log(LogStatus.FAIL, result.getThrowable());
	            test.log(LogStatus.FAIL, "Snapshot below: " + test.addScreenCapture(screenShotPath));
	        }
	        Report.endTest(test);
	    }

	@AfterTest
	 public static void Generate() {
		 
		generateReport();
	 }

}
