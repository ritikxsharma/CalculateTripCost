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
	private Properties properties;
	protected static WebDriver driver;
	protected DriverManager driverManager;
	protected ScreenshotManager screenshotManager;
	protected String baseUrl;
	
	@BeforeTest
	public void setUp() throws IOException{
		ExtentReportManager.startReport();
		properties = PropertiesManager.loadProperties("config.properties");
		baseUrl = PropertiesManager.getProperty(properties, "website.url");
		
		driverManager = new DriverManager();
		driver = DriverManager.driver;

		driverManager.setUrl(baseUrl);
		driverManager.maximizeDriver();
		driverManager.setImplicitWait(10);
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		screenshotManager = new ScreenshotManager(driver, result.getName());
		ExtentReportManager.getResult(result);
	}
	
	@AfterTest
	public void tearDown() {
		ExtentReportManager.stopReport();
		driver.quit();
	}
	
}
