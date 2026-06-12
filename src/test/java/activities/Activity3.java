package activities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity3 {
WebDriver driver ;
	
	@BeforeClass
	public void setUp() {
		//initialize the driver object 
		driver = new FirefoxDriver();
		// open the page 
		driver.get("http://hrm.local:3050");
		
	}
	
  @Test 
  public void verifyLogin () {
	  // assert the page title
	  
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		driver.findElement(By.id("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.id("welcome")).getText(),"Welcome Admin" );
		}
	 	
  
  @AfterClass
  public void tearDown() {
	  //close the browser 
	  driver.quit();
  }

}
