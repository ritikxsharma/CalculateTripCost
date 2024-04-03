package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HotelsSearchPage extends BasePage {
		
	@FindBy(id = "list_hotel_sort")
	WebElement hotelsSortDropdownElement;
	
	@FindAll(@FindBy(css = "span.drop-options"))
	List<WebElement> hotelsSortOptions;
	
	@FindBy(css = "span.active")
	WebElement hotelSortSelectedOption;
	
	@FindBy(css = "div.category-content")
	WebElement propertyTypeElement;
	
	@FindBy(css = "div.amenty-content")
	WebElement amenityElement;
	
	@FindAll(@FindBy(css = "div.choosen-item-v8"))
	List<WebElement> selectedFilters;
	
	@FindAll(@FindBy(css = "div.hotel-info"))
	List<WebElement> allHotelsInfo;

	public HotelsSearchPage(WebDriver driver) throws IOException{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
		
	public String sortHotelResults(String option) {
		hotelsSortDropdownElement.click();

		String selectedOption = null;
		for(WebElement ele : hotelsSortOptions) {
			if(ele.getText().equals(option)) {
				selectedOption = ele.getText();
				ele.click();
				break;
			}
		}
		
		return selectedOption;
	}
	
	public String selectPropertyType(String type) {
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
		
		return getSelectedFilter(type);
		
	}
	
	public String selectAmeneties(String type) {
		jse.executeScript("arguments[0].scrollIntoView(true)", amenityElement);
		amenityElement.findElement(By.id("showAll")).click();
		
		List<WebElement> allTypes = amenityElement.findElements(By.cssSelector("li.m-checkbox_item"));
		for(WebElement typeElement : allTypes) {
			String currType = typeElement.getText();
			if(currType.equalsIgnoreCase(type)) {
				typeElement.findElement(By.tagName("i")).click();
				break;
			}
		}
		
		return getSelectedFilter(type);
	}
	
	public void displayPrices() {

		if(allHotelsInfo.size() == 0) {
			System.out.println("No properties avaiable for the given requirements.");
			return;
		}
		
		System.out.println("-------------------------");
		System.out.println("Hotels Details");
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
		System.out.println("-------------------------");
	}
	
	private String getSelectedFilter(String filter) {
		String selectedFilter = null;
		for(WebElement filterElement : selectedFilters) {
			String filterText = filterElement.getText();
			if(filterText.equals(filter)) {
				selectedFilter = filterText;
				break;
			}
		}
		
		return selectedFilter;
	}
	
}
