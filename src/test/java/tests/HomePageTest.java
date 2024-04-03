package tests;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import utilities.PropertiesManager;

public class HomePageTest extends BaseTest {

	private HomePage homePage;
	DateTimeFormatter dtf;
	LocalDate expCheckInDate;
	LocalDate expCheckOutDate;
	
	@BeforeClass
	public void loading() throws IOException {
		homePage = new HomePage(driver);

		dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
		expCheckInDate = LocalDate.parse(LocalDate.now().plusDays(1).format(dtf), dtf);
		expCheckOutDate = expCheckInDate.plusDays(6);
	}

	@Test(priority = 1, groups = "smokeTest")
	public void testHomePageTitle() {
		String expTitle = PropertiesManager.getProperty(properties, "expected.website.title");
		expTitle = expTitle.replaceAll("[^a-zA-Z0-9 ]", "");
		
		String actTitle = driver.getTitle();
		actTitle = actTitle.replaceAll("[^a-zA-Z0-9 ]", "");

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

		homePage.selectDate(expCheckInDate);

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E, MMM d yyyy");

		String checkInDate = driver.findElement(By.cssSelector("div.checkin input")).getAttribute("value");
		checkInDate += " " + LocalDate.now().getYear();

		LocalDate actCheckInDate = LocalDate.parse(checkInDate, dtf1);

		Assert.assertEquals(actCheckInDate, expCheckInDate);

	}
	
	@Test(priority = 4, groups = "smokeTest")
	public void testCheckOutDate() {

		homePage.selectDate(expCheckOutDate);

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E, MMM d yyyy");

		String checkOutDate = driver.findElement(By.cssSelector("div.checkout input")).getAttribute("value");
		checkOutDate += " " + LocalDate.now().getYear();

		LocalDate actCheckOutDate = LocalDate.parse(checkOutDate, dtf1);

		Assert.assertEquals(actCheckOutDate, expCheckOutDate);
	}

	@Test(priority = 5, groups = "smokeTest")
	public void testGuestsAndRooms() {
		int expRooms = Integer.parseInt(PropertiesManager.getProperty(properties, "expected.rooms"));
		int expAdults = Integer.parseInt(PropertiesManager.getProperty(properties, "expected.adults"));
		int expChildren = Integer.parseInt(PropertiesManager.getProperty(properties, "expected.children"));
		homePage.selectGuests(expRooms, expAdults, expChildren);
	}

	@Test(priority = 6, groups = "smokeTest")
	public void testSearchButton() {
		homePage.clickSearch();
	}

}
