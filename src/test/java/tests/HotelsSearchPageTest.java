package tests;

import java.io.IOException;
import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.HotelsSearchPage;
import utilities.PropertiesManager;

public class HotelsSearchPageTest extends BaseTest{
	
	private Properties properties;
	private HotelsSearchPage hotelsSearchPage;
	
	@BeforeClass
	public void loading() throws IOException {
		properties = PropertiesManager.loadProperties("hotelsSearchPageTest.properties");
		hotelsSearchPage = new HotelsSearchPage(driver);
	}
	
	@Test(priority = 1)
	public void validateTitle() {
		String expTitle = "Nairobi Hotels - Where to stay in Nairobi | Trip.com";
		String actTitle = driver.getTitle();
		
		Assert.assertEquals(actTitle, expTitle);
	}
	
	@Test(priority = 2)
	public void sortHotels() {
		String expHotelSortOption = PropertiesManager.getProperty(properties, "expected.hotelSort.option");
		hotelsSearchPage.sortHotelResults(expHotelSortOption);
	}
	
	@Test(priority = 3)
	public void selectPropertyType() {
		String expPropertyType = PropertiesManager.getProperty(properties, "expected.propertyType.option");
		hotelsSearchPage.selectPropertyType(expPropertyType);
	}
	
	@Test(priority = 4)
	public void selectAmenty() {
		String expAmenty = PropertiesManager.getProperty(properties, "expected.amenty.option");
		hotelsSearchPage.selectAmeneties(expAmenty);
	}
	
	@Test(priority = 5)
	public void displayPrices() {
		hotelsSearchPage.displayPrices();
	}
	
	@Test(priority = 5)
	public void moveToHomePage() {
		driver.navigate().back();
	}
	
	
}
