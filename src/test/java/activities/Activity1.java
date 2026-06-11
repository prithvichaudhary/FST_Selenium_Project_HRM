package activities;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Activity1 {

	public class FirstTest {
		//Declare WebDriver
		WebDriver driver;
		
		@BeforeClass
		public void setUp() {
			//Initialize the driver object
			driver = new FirefoxDriver();
			
			//Open page
			driver.get("https://training-support.net/");
		}
		
	  @Test(priority = 1)
	  public void verifyPageTitle() {
		  //Asset the page title
		  Assert.assertEquals(driver.getTitle(), "Training Support");
	  }
	  
	  @Test(priority = 2)
	  public void clickAboutBtn() {
		  //Asset the page title
		  driver.findElement(By.linkText("About Us")).click();
		  Assert.assertEquals(driver.getTitle(), "About Training Support");
	  }

	  @AfterClass
	  public void tearDown() {
		  driver.quit();
	  }

	}
}
