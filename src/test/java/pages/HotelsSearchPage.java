package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelsSearchPage extends BasePage {
		
	@FindBy(id = "list_hotel_sort")
	WebElement hotelsSortDropdownElement;
	
	@FindBy(xpath = "//div[@id = 'list_hotel_sort']/div/span[contains(text(), 'Star Rating')]")
	WebElement hotelsSortOptionElement;
	
	@FindBy(css = "div.category-content")
	WebElement propertyTypeElement;
	
	@FindBy(css = "div.amenty-content")
	WebElement amentyElement;
	
	@FindAll(@FindBy(css = "div.hotel-info"))
	List<WebElement> allHotelsInfo;
	
	//private Properties properties;

	public HotelsSearchPage(WebDriver driver) throws IOException{
		super(driver);
		loadProperties("hotelsSearchPage.properties");
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	private void loadProperties(String fileName) {
		try {
			//properties = PropertiesManager.loadProperties(fileName);
		} catch(Exception e) {
			System.out.println("Error reading properties file: " + e.getMessage());
		}
	}
		
	public void sortHotelResults(String option) {
		hotelsSortDropdownElement.click();
		hotelsSortOptionElement.click();
	}
	
	public void selectPropertyType(String type) {
		jse.executeScript("arguments[0].scrollIntoView(true)", propertyTypeElement);
		propertyTypeElement.findElement(By.id("showAll")).click();
		
		List<WebElement> allTypes = propertyTypeElement.findElements(By.cssSelector("li.m-checkbox_item"));
		for(WebElement typeElement : allTypes) {
			String currType = typeElement.getText();
			if(currType.equalsIgnoreCase(type)) {
				typeElement.findElement(By.tagName("i")).click();
				break;
			}
		}
	}
	
	public void selectAmeneties(String type) {
		jse.executeScript("arguments[0].scrollIntoView(true)", amentyElement);
		amentyElement.findElement(By.id("showAll")).click();
		
		List<WebElement> allTypes = amentyElement.findElements(By.cssSelector("li.m-checkbox_item"));
		for(WebElement typeElement : allTypes) {
			String currType = typeElement.getText();
			if(currType.equalsIgnoreCase(type)) {
				typeElement.findElement(By.tagName("i")).click();
				break;
			}
		}
	}
	
	public void displayPrices() {

		if(allHotelsInfo.size() == 0) {
			System.out.println("No properties avaiable for the given requirements.");
			return;
		}
		
		int count = 0;
		for(WebElement hotel : allHotelsInfo) {
			if(count == 3) {
				break;
			}
			System.out.println(++count + ") Name: " + hotel.findElement(By.cssSelector("div.list-card-title")).getText());
			try {
				driver.findElement(By.xpath("//div[@class = 'sold-wrap-meta']"));
				System.out.println("   Hotel sold out");
				continue;
			} catch(Exception e) {
				String priceForADay = driver.findElement(By.cssSelector("div#meta-real-price")).getText();
				System.out.println("   Price for a day: " + priceForADay);
				String priceForWholeStay = driver.findElement(By.cssSelector("span.not-break")).getText();
				System.out.println("   Price for whole stay: " + priceForWholeStay);
			}
		}
	}
	
}
