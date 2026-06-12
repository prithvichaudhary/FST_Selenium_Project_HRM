package activities;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity3 {
WebDriver driver ;
WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		//initialize the driver object 
		driver = new FirefoxDriver();
		// open the page 
		driver.get("http://hrm.local:3050");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	}
	
  @Test 
  public void verifyLogin () {
	  // assert the page title
	  
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		driver.findElement(By.id("btnLogin")).click();
		// Waiting for Welcome Pannel
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));

		// Assert confirming login
		WebElement welcomeMessage = driver.findElement(By.className("panelTrigger"));

		// Get text of that Web Element
		String message = welcomeMessage.getText();

		Assert.assertTrue(message.contains("Welcome"));
		}
	 	
  
  @AfterClass
  public void tearDown() {
	  //close the browser 
	  driver.quit();
  }

}
