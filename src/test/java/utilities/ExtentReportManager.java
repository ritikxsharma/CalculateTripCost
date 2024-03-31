package utilities;

import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportManager {
	private static ExtentReports extentReport;
	private static ExtentTest test;
	
	public static void startReport() {
		String directoryPath = System.getProperty("user.dir");
		String relativePath = "/src/test/resources/test-results/reports/report.html";
		
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
