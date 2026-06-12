package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity4 {

	WebDriver driver;
	WebDriverWait wait;
	Actions actions;

	String fname = "fname";
	String lname = "lname";
	String empId;

	@BeforeClass
	public void setUp() {
		// Initialize the driver object
		driver = new FirefoxDriver();
		actions = new Actions(driver);

		// Open page
		driver.get("http://hrm.local:3050");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 0)
	public void validateLogin() {
		// Username
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin"))).click();

		String str = driver.findElement(By.id("welcome")).getText();
		Assert.assertEquals(str, "Welcome Admin");
	}

	@Test(dependsOnMethods = { "validateLogin" })
	public void addEmp() {

		// Select PIM
//		  wait.until(
//				    ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule"))
//				    ).click();
		actions.moveToElement(driver.findElement(By.id("menu_pim_viewPimModule"))).perform();
		driver.findElement(By.id("menu_pim_viewEmployeeList")).click();

		// Click on Add btn
		driver.findElement(By.id("btnAdd")).click();

		// Enter emp data
		driver.findElement(By.id("firstName")).sendKeys(fname);
		driver.findElement(By.id("lastName")).sendKeys(lname);
		empId = driver.findElement(By.id("employeeId")).getText();

		// Save data
		driver.findElement(By.id("btnSave")).click();

		// Verify info
		String result = driver.findElement(By.id("personal_txtEmployeeId")).getText();
		Assert.assertEquals(result, empId);
	}

	@Test(dependsOnMethods = { "addEmp" })
	public void searchEmp() throws InterruptedException {
		// Click the Employee List menu item
		actions.moveToElement(driver.findElement(By.id("menu_pim_viewPimModule"))).pause(2000).click().perform();
		
		// Wait for the page to load
		wait.until(ExpectedConditions.urlContains("viewEmployeeList"));

		// Search by name
		WebElement nameSearchField = driver.findElement(By.cssSelector("input.inputFormatHint"));
		actions.sendKeys(nameSearchField, fname + " " + lname).pause(2000).sendKeys(Keys.ENTER)
				.click(driver.findElement(By.id("searchBtn"))).pause(2000).build().perform();

		// Verify employee appears in results
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//table/tbody/tr[1]/td[3]/a"), fname));
		String firstName = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]/a")).getText();
		String lastName = driver.findElement(By.xpath("//table/tbody/tr[1]/td[4]/a")).getText();

		Assert.assertEquals(firstName, fname);
		Assert.assertEquals(lastName, lname);

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}