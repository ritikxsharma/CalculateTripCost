package tests;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;

public class HomePageTest extends BaseTest{
	
	private HomePage homePage;

	@Test(priority = 1, groups = "smokeTest")
	public void testOpenBrowser() {
		String expTitle = propertiesManager.getProperty("website.title");
		String actTitle = driver.getTitle();

		Assert.assertEquals(actTitle, expTitle, "Failed to load the correct website.");
	}

	@Test(priority = 2, groups = "smokeTest")
	public void testSelectedDestination() throws IOException {
		
		homePage = new HomePage(driver);
		
		String expDestination = propertiesManager.getProperty("expected.destination");
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
		
	}
	
	@Test(priority = 5, groups = "smokeTest")
	public void testSearchButton() {
		homePage.clickSearch();
	}

}
