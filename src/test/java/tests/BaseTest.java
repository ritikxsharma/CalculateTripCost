package tests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.DriverManager;
import utilities.ExtentReportManager;
import utilities.PropertiesManager;
import utilities.ScreenshotManager;

public class BaseTest {
	protected static Properties properties;
	protected static WebDriver driver;
	protected DriverManager driverManager;
	protected ScreenshotManager screenshotManager;
	protected String baseUrl;
	
	@BeforeTest
	@Parameters("browser")
	public void setUp(@Optional("chrome") String browser) throws IOException{
		properties = PropertiesManager.loadProperties("tests.properties");
		baseUrl = PropertiesManager.getProperty(properties, "website.url");
		
		driverManager = new DriverManager(browser);
		driver = DriverManager.driver;

		driverManager.setUrl(baseUrl);
		driverManager.maximizeDriver();
		driverManager.setImplicitWait(10);
		
		ExtentReportManager.startReport(driver);
		
		System.out.println("\nAutomation and testing in " + browser);
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		System.out.println("\nRunning test: " + testName);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		screenshotManager = new ScreenshotManager(driver, result.getName());
		ExtentReportManager.getResult(result);
		
		if(result.isSuccess()) {
			System.out.println("Result: PASSED");
		}else {
			System.out.println("Result: FAILED");
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		ExtentReportManager.stopReport();
		driver.quit();
	}
	
}
