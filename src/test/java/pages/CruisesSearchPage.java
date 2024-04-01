package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CruisesSearchPage extends BasePage{
	
	@FindBy(css = "div.home-content")
	WebElement popularCruisesElement;
	
	@FindAll(@FindBy(css = "li.popular-item"))
	List<WebElement> popularCruises;
	
	@FindBy(linkText = "Ship Details")
	WebElement shipDetailsButtonElement;
	
	@FindBy(css = "div.ship-panel-box")
	WebElement shipDetailsElement;
	
	@FindBy(css = "div.ship-panel-title")
	WebElement shipTitle;
	
	@FindAll(@FindBy(css = "span.parame-item"))
	List<WebElement> shipDetails;
	
	//private Properties properties;
	
	public CruisesSearchPage(WebDriver driver) throws IOException {
		super(driver);
		//properties = PropertiesManager.loadProperties("cruisesSearchPage.properties");
		PageFactory.initElements(driver, this);
	}
	
	public void selectRandomCruise() {
		jse.executeScript("arguments[0].scrollIntoView(true)", popularCruisesElement);
		int totalCruises = popularCruises.size();

		double idx = Math.random()*totalCruises;
		jse.executeScript("arguments[0].click()", popularCruises.get((int)idx));
		
		String parentWinHandle = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()) {
			if(!parentWinHandle.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
	}
	
	public void clickShipDetailsLink() {
		jse.executeScript("arguments[0].scrollIntoView(true)", shipDetailsButtonElement);
		jse.executeScript("arguments[0].click()", shipDetailsButtonElement);
		String parentWinHandle = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()) {
			if(!parentWinHandle.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
	}
	
	public void getShipDetails() {
		jse.executeScript("arguments[0].scrollIntoView(true)", shipDetailsElement);
		
		System.out.println("Cruise Title: " + shipTitle.getText());
		
		int count = 1;
		for(WebElement shipDetail : shipDetails) {
			String info = shipDetail.getText();
			if(info.matches("(?i)(Renovated|Crew|Passengers|Decks|Entered Service|Guest).*")) {
				System.out.println(count++ + "." + info);
			}
		}
		
	}
}
