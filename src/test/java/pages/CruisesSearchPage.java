package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CruisesSearchPage extends BasePage{
	
	@FindBy(css = "div.home-content")
	WebElement popularCruisesElement;
	
	@FindAll(@FindBy(css = "li.popular-item"))
	List<WebElement> popularCruises;
	
	@FindBy(css = "div.detail-product")
	WebElement cruiseDetailPanel;
	
	@FindBy(linkText = "Ship Details")
	WebElement shipDetailsButtonElement;
	
	@FindBy(css = "div.ship-panel-box")
	WebElement shipDetailsElement;
	
	@FindBy(css = "div.ship-panel-title")
	WebElement shipTitle;
	
	@FindAll(@FindBy(css = "span.parame-item"))
	List<WebElement> shipDetails;
	
	public CruisesSearchPage(WebDriver driver) throws IOException {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean selectRandomCruise() throws InterruptedException {
		jse.executeScript("arguments[0].scrollIntoView(true)", popularCruisesElement);
		Thread.sleep(2000);
		
		int totalCruises = popularCruises.size();

		double idx = Math.random()*totalCruises;
		jse.executeScript("arguments[0].click()", popularCruises.get((int)idx));
		
		String parentWinHandle = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()) {
			if(!parentWinHandle.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
		
		WebElement cruisePanelElement = wait.until(ExpectedConditions.visibilityOf(cruiseDetailPanel));
		return cruisePanelElement.isDisplayed();
		
	}
	
	public boolean clickShipDetailsLink() throws InterruptedException {
		jse.executeScript("arguments[0].scrollIntoView(true)", shipDetailsButtonElement);
		
		Thread.sleep(2000);
		
		jse.executeScript("arguments[0].click()", shipDetailsButtonElement);
		String parentWinHandle = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()) {
			if(!parentWinHandle.equals(winHandle)) {
				driver.switchTo().window(winHandle);
			}
		}
		
		WebElement detailsElement = wait.until(ExpectedConditions.visibilityOf(shipDetailsElement));
		
		return detailsElement.isDisplayed();
	}
	
	public void getShipDetails() {
		jse.executeScript("arguments[0].scrollIntoView(true)", shipDetailsElement);
		
		System.out.println("--------------------------");
		
		System.out.println("Cruise Title: " + shipTitle.getText());
		
		int count = 1;
		for(WebElement shipDetail : shipDetails) {
			String info = shipDetail.getText();
			if(info.matches("(?i)(Renovated|Crew|Passengers|Decks|Entered Service|Guest).*")) {
				System.out.println(count++ + "." + info);
			}
		}
		System.out.println("--------------------------");
		
	}
}
