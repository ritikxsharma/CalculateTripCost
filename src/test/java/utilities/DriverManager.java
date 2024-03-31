package utilities;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
	public static WebDriver driver;
	
	public DriverManager() throws IOException {
		DriverManager.driver = initializeDriver();
	}
	
	private WebDriver initializeDriver() {
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	public void maximizeDriver() {
		driver.manage().window().maximize();
	}
	
	public void setUrl(String url) {
		driver.get(url);
	}
	
	public void setImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
		
}
