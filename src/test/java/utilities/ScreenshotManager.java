package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotManager {
	private TakesScreenshot screenShot;
	
	public ScreenshotManager(WebDriver driver, String fileName)  {
		try {
			takeScreenshot(driver, fileName);
		}catch(Exception e) {
			System.out.println("Cannot capture screenshot : " + e.getMessage());
		}
	}
	
	private void takeScreenshot(WebDriver driver, String fileName) throws IOException  {
		screenShot = (TakesScreenshot) driver;
		File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
		
		String directoryPath = System.getProperty("user.dir");
		String relativePath = "/src/test/resources/test-results/screenshots/" + fileName + ".png";
		
		File destFile = new File(directoryPath + relativePath);
		
		FileUtils.copyFile(srcFile, destFile);
	}
	
}
