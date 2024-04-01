package tests;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import utilities.PropertiesManager;

public class HomePageTest extends BaseTest{
	
	private Properties properties;
	private HomePage homePage;
	
	@BeforeClass
	public void loading() throws IOException {
		properties = PropertiesManager.loadProperties("homePageTest.properties");
		homePage = new HomePage(driver);
	}

	@Test(priority = 1, groups = "smokeTest")
	public void testOpenBrowser() {
		String expTitle = PropertiesManager.getProperty(properties, "website.title");
		String actTitle = driver.getTitle();

		Assert.assertEquals(actTitle, expTitle, "Failed to load the correct website.");
	}

	@Test(priority = 2, groups = "smokeTest")
	public void testSelectedDestination() throws IOException {
		String expDestination = PropertiesManager.getProperty(properties, "expected.destination");
		homePage.selectDestination(expDestination);

		String actDestination = homePage.destinationInputElement.getAttribute("value");

		Assert.assertEquals(actDestination, expDestination);
	}

	@Test(priority = 3, groups = "smokeTest")
	public void testCheckInDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");

		LocalDate expCheckInDate = LocalDate.parse(LocalDate.now().plusDays(1).format(dtf), dtf);
		LocalDate expCheckOutDate = expCheckInDate.plusDays(6);

		homePage.selectDate(expCheckInDate);
		homePage.selectDate(expCheckOutDate);

//		LocalDate actCheckInDate = LocalDate.parse(
//				homePage.getDriver().findElement(By.cssSelector("div.checkin input")).getAttribute("value"), dtf);
//		LocalDate actCheckOutDate = LocalDate.parse(
//				homePage.getDriver().findElement(By.cssSelector("div.checkout input")).getAttribute("value"), dtf);
//		
//		System.out.println(actCheckInDate);
//		System.out.println(actCheckOutDate);
		
//		Assert.assertEquals(actCheckInDate, expCheckInDate);
//		Assert.assertEquals(actCheckOutDate, expCheckOutDate);
		
	}
	
	@Test(priority = 4)
	public void testGuestsAndRooms() {
		int expRooms = Integer.parseInt(PropertiesManager.getProperty(properties, "expected.rooms"));
		int expAdults = Integer.parseInt(PropertiesManager.getProperty(properties, "expected.adults"));
		int expChildren = Integer.parseInt(PropertiesManager.getProperty(properties, "expected.children"));
		homePage.selectGuests(expRooms, expAdults, expChildren);
	}
	
	@Test(priority = 5, groups = "smokeTest")
	public void testSearchButton() {
		homePage.clickSearch();
	}

}
