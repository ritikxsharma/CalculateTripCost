package tests;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.CruisesSearchPage;
import pages.HomePage;
import utilities.PropertiesManager;

public class CruisesSearchPageTest extends BaseTest{
	
	private Properties properties;
	private CruisesSearchPage cruisesSearchPage;
	
	@BeforeClass
	public void loading() throws IOException {
		cruisesSearchPage = new CruisesSearchPage(driver);
		properties = PropertiesManager.loadProperties("CruisesSearchPage.properties");
	}
	
	@Test(priority = 1)
	public void moveToCruisePage() throws IOException {
		HomePage homePage = new HomePage(driver);
		homePage.goToCruiseSection();
	}
	
	@Test(priority = 2)
	public void testWebPage() {
		String expTitle = PropertiesManager.getProperty(properties, "expected.title");
		String actTitle = driver.getTitle();
		
		Assert.assertEquals(actTitle, expTitle);
	}
	
	@Test(priority = 3)
	public void testCruiseSelection() {
		cruisesSearchPage.selectRandomCruise();
	}
	
	@Test(priority = 4)
	public void findShipDetails() {
		cruisesSearchPage.clickShipDetailsLink();
	}
	
	@Test(priority = 5)
	public void getShipDetails() {
		cruisesSearchPage.getShipDetails();
	}

}
