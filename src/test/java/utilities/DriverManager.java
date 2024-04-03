package utilities;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {
	public static WebDriver driver;
	
	public DriverManager(String browser) throws IOException {
		DriverManager.driver = initializeDriver(browser);
	}
	
	private WebDriver initializeDriver(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else {
			driver = new EdgeDriver();
		}
		return driver;
	}
	
	public void maximizeDriver() {
		driver.manage().window().maximize();
	}
	
	public void setUrl(String url) {
		driver.navigate().to(url);
	}
	
	public void setImplicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
		
}
