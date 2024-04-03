package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportManager {
	private static ExtentReports extentReport;
	private static ExtentTest test;
	
	public static void startReport(WebDriver driver) {
		String directoryPath = System.getProperty("user.dir");
		String relativePath = "/src/test/resources/test-results/reports";
		
		if(driver.getClass().getName().equals(ChromeDriver.class.getName())) {
			relativePath += "/chrome-report.html";
		}else {
			relativePath += "/edge-report.html";
		}
		
		extentReport = new ExtentReports(directoryPath + relativePath, true);
	}
	
	public static void getResult(ITestResult result) {
		
		test = extentReport.startTest(result.getName());
		
		if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Passed");
		}
		if(result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test Failed");
		}
	}
	
	public static void stopReport() {
		extentReport.flush();
	}
}
