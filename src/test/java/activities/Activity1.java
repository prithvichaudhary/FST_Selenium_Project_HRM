package activities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {
	WebDriver driver;

	//Setup browser
	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();

		//Open URL
		driver.get("http://hrm.local:3050");
	}

	//Verify title
	@Test
	public void verifyWebsiteTitle() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "OrangeHRM";

	}

	//Close browser
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
