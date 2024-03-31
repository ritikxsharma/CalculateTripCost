package tests;

import org.testng.Assert;
import org.testng.annotations.*;

public class HotelsSearchPageTest extends BaseTest{
	
	@Test
	public void validateTitle() {
		String expTitle = "Nairobi Hotels - Where to stay in Nairobi | Trip.com";
		String actTitle = driver.getTitle();
		
		Assert.assertEquals(actTitle, expTitle);
	}
	
}
