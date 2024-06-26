package pages;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
	
	@FindBy(linkText = "Hotels")
	WebElement hotelsSectionElement;
	
	@FindBy(linkText = "Cruise")
	WebElement cruiseSectionElement;
	
	@FindBy(id = "hotels-destination")
	public WebElement destinationInputElement;
	
	@FindBy(css = "div#dropList div.associative-item:nth-child(1)")
	WebElement destinationListElement;
	
	@FindBy(css = "div.c-calendar-month:nth-child(1) div.c-calendar-month__title")
	WebElement currMonthYearElement;
	
	@FindAll(@FindBy(xpath = "//div[@class = 'c-calendar-month'][1]//span[@class = 'day']//ancestor::li[1][contains(@class, 'is-allow-hover')]"))
	List<WebElement> calendarDatesElement;
	
	@FindBy(css = "span.c-calendar-icon-next")
	WebElement calendarNextButtonElement;
	
	@FindBy(css = "div.search-btn-wrap")
	WebElement searchButtonElement;
	
	@FindBy(css = "div.child-kid:nth-child(1)")
	WebElement roomsElement;
	
	@FindBy(css = "div.child-kid:nth-child(2)")
	WebElement adultsElement;
	
	@FindBy(css = "div.child-kid:nth-child(3)")
	WebElement childrenElement;
	
	@FindBy(css = "i.u-icon-ic_plus")
	WebElement roomsAndGuestPlusButton;
	
	@FindBy(css = "i.u-icon-ic_minus")
	WebElement roomsAndGuestMinusButton;
	
	public HomePage(WebDriver driver) throws IOException{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void goToCruiseSection() {
		jse.executeScript("arguments[0].click()", cruiseSectionElement);
	}
	
	public void selectDestination(String destination) {
		destinationInputElement.sendKeys(destination);
		jse.executeScript("arguments[0].click()", destinationListElement);
	}
	
	public void selectDate(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
		String expDate = date.format(dtf);
		String expDay = expDate.split(" ")[0];
		String expMonth = expDate.split(" ")[1];
		String expYear = expDate.split(" ")[2];
		
		while(true) {
			String[] currMonthYear = this.currMonthYearElement.getText().split(" ");
			String currMonth = currMonthYear[0];
			String currYear = currMonthYear[1];
			
			if(currMonth.equals(expMonth) && currYear.equals(expYear)) {
				for(WebElement dateElement : calendarDatesElement) {
					int currDay = Integer.parseInt(dateElement.getText());
					if(Integer.parseInt(expDay) == currDay) {
						jse.executeScript("arguments[0].click()", dateElement);
						break;
					}
				}
				break;
			}
			jse.executeScript("arguments[0].click()", calendarNextButtonElement);						
		}
	}
	
	public void selectGuests(int expRooms, int expAdults, int expChildren) {
		
		selectCount(roomsElement, expRooms);
		selectCount(adultsElement, expAdults);
		selectCount(childrenElement, expChildren);
	}
	
	public void clickSearch() {
		jse.executeScript("arguments[0].click()", searchButtonElement);
	}
	
	private void selectCount(WebElement element, int expCount) {
		while(true) {
			String str_actCount = element.findElement(By.cssSelector("span.count")).getText();
			int actCount = Integer.parseInt(str_actCount);
			
			if(expCount == actCount) {
				break;
			}
			else if(expCount > actCount) {
				WebElement plusBtnElement = element.findElement(By.cssSelector("i.u-icon-ic_plus"));
				jse.executeScript("arguments[0].click()", plusBtnElement);
			}
			else {
				WebElement minusBtnElement = element.findElement(By.cssSelector("i.u-icon-ic_minus"));
				jse.executeScript("arguments[0].click()", minusBtnElement);
			}
		}
	}
	
}
