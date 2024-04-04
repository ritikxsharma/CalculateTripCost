package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HotelsSearchPage;
import utilities.PropertiesManager;

public class HotelsSearchPageTest extends BaseTest {

	private HotelsSearchPage hotelsSearchPage;

	@BeforeClass
	public void loading() throws IOException {
		hotelsSearchPage = new HotelsSearchPage(driver);
	}

	@Test(priority = 1, groups = "smokeTest")
	public void testHotelSearchPageTitle() {
		String expTitle = PropertiesManager.getProperty(properties, "expected.hotelSearchPage.title");
		String expDestination = PropertiesManager.getProperty(properties, "expected.destination");
		expTitle = String.format(expTitle, expDestination, expDestination);
		
		String actTitle = driver.getTitle();

		Assert.assertEquals(actTitle, expTitle);
	}

	@Test(priority = 2, groups = "smokeTest")
	public void sortHotels() {
		String expHotelSortOption = PropertiesManager.getProperty(properties, "expected.hotelSort");
		String actHotelSortOption = hotelsSearchPage.sortHotelResults(expHotelSortOption);
		
		Assert.assertEquals(expHotelSortOption, actHotelSortOption);
	}

	@Test(priority = 3, groups = "smokeTest")
	public void selectPropertyType() {
		String expPropertyType = PropertiesManager.getProperty(properties, "expected.propertyType");
		String actPropertyType = hotelsSearchPage.selectPropertyType(expPropertyType);
		
		Assert.assertEquals(actPropertyType, expPropertyType);
	}

	@Test(priority = 4, groups = "smokeTest")
	public void selectAmenity() {
		String expAmenity = PropertiesManager.getProperty(properties, "expected.amenity");
		String actAmenity = hotelsSearchPage.selectAmeneties(expAmenity);
		
		Assert.assertEquals(actAmenity, expAmenity);
		
	}

	@Test(priority = 5, groups = "smokeTest")
	public void getHotelsDetails() throws InterruptedException {
		hotelsSearchPage.displayPrices();
	}

	@Test(priority = 5)
	public void moveToHomePage() {
		driver.navigate().back();
	}

}
