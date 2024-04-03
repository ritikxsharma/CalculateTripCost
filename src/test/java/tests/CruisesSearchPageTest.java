package tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CruisesSearchPage;
import pages.HomePage;
import utilities.PropertiesManager;

public class CruisesSearchPageTest extends BaseTest{
	
	private CruisesSearchPage cruisesSearchPage;
	
	@BeforeClass
	public void loading() throws IOException {
		cruisesSearchPage = new CruisesSearchPage(driver);
	}
	
	@Test(priority = 1)
	public void moveToCruisePage() throws IOException {
		HomePage homePage = new HomePage(driver);
		homePage.goToCruiseSection();
		String expTitle = PropertiesManager.getProperty(properties, "expected.cruiseSearchPage.title");
		String actTitle = driver.getTitle();
		
		Assert.assertEquals(actTitle, expTitle);
	}
	
	@Test(priority = 2)
	public void testCruiseSelection() {
		boolean result = cruisesSearchPage.selectRandomCruise();
		
		Assert.assertTrue(result);
	}
	
	@Test(priority = 3)
	public void findShipDetails() {
		boolean result = cruisesSearchPage.clickShipDetailsLink();
		
		Assert.assertTrue(result);
	}
	
	@Test(priority = 4)
	public void getShipDetails() {
		cruisesSearchPage.getShipDetails();
	}

}
