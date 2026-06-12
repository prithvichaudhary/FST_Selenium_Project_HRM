package activities;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.List;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity7 {
	
	//Declare Webdriver
	WebDriver driver;
	//Declare Explicitwait
	WebDriverWait wait;
	//Declare Action
	Actions builder;
	
	@BeforeClass
	public void setUp() {
		//Initialize object for the driver
		driver = new FirefoxDriver();
		//Initialize object for the wait		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//Initialize object for the action	
		builder = new Actions(driver);
	}
	
	@Test(priority = 1)
	public void Login_Page() {
		//Open the url
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login#/Login");
		//Checks its on the right page
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
		//Locate user id element and passing user id
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		//Locate password element and passing user id
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		//click the login button
		driver.findElement(By.id("btnLogin")).click();
		//Checks redirected to the welcome page
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// Assert confirming login
		WebElement welcomeMessage = driver.findElement(By.className("panelTrigger"));

		// Get text of that Web Element
		String message = welcomeMessage.getText();

		Assert.assertTrue(message.contains("Welcome"));
	}
	
	@Test(priority=2)
	public void myInfo() {
		//locate My_Info element and click it
		WebElement my_info= driver.findElement(By.xpath("//b[text()='My Info']"));
		builder.click(my_info).pause(2000).build().perform();
		
		//Checks it is on Myinfo Page
		String Personal_details=driver.findElement(By.xpath("//h1[text()='Personal Details']")).getText();
		Assert.assertEquals(Personal_details, "Personal Details");
	}
	
	@Test(priority=3)
	public void qualification() {
		//locate  qualification and click it
		driver.findElement(By.linkText("Qualifications")).click();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Assert.assertEquals(driver.getCurrentUrl(),"http://hrm.local:3050/symfony/web/index.php/pim/viewQualifications/empNumber/1");
	}
	
	@Test(priority=4)
	public void addWork() {
		//locate AddWork button and click it
		driver.findElement(By.id("addWorkExperience")).click();
		// checks it showing the fields to fill
		assertTrue(driver.findElement(By.id("experience_employer")).isDisplayed());
		//locate company field and enter value
		driver.findElement(By.id("experience_employer")).sendKeys("google");
		//locate job title field and enter value
		driver.findElement(By.id("experience_jobtitle")).sendKeys("software developer");
		//locate Date from field and enter value
		driver.findElement(By.id("experience_from_date")).clear();
		driver.findElement(By.id("experience_from_date")).sendKeys("2024-10-02");
		//locate Date to field and enter value
		driver.findElement(By.id("experience_to_date")).clear();
		driver.findElement(By.id("experience_to_date")).sendKeys("2026-04-15");
		//locate comment field enter value
		driver.findElement(By.id("experience_comments")).sendKeys("good years of experience ");
		//locate save button and click it
		driver.findElement(By.id("btnWorkExpSave")).click();
		//check it is added to the work experience table
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Boolean present = false;
		List<WebElement> worklist = driver.findElements(By.xpath("//table/tbody/tr/td"));
		for(WebElement row :worklist) {
			if(row.getText().contains("google")) {
				present = true;
			}
		}
		
		Assert.assertTrue(present);
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}