package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected static WebDriver driver;
	protected static JavascriptExecutor jse;
	protected static WebDriverWait wait;
	
	public BasePage(WebDriver driver){
		BasePage.driver = driver;
		BasePage.jse = (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
}
