package activities;

import org.openqa.selenium.By;

//Goal: Print the url of the header image to the console

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity2 {
	// Declare WebDriver
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		// Initialize the driver object
		driver = new FirefoxDriver();
		
		// Open the page
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");
	}
	
	@Test
	public void imageURL() {
		WebElement logo = driver.findElement(By.id("divLogo"));
		Assert.assertTrue(logo.isDisplayed(), "Logo is NOT displayed");
	}
	
	@AfterClass
	public void tearDown() {
		// Close the browser
		driver.quit();
	}

}
