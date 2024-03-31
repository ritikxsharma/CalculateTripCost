package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HotelsSearchPage extends BasePage {
	
	public HotelsSearchPage(WebDriver driver) throws IOException{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
}
