package activities;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity8 {

	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();

		
		// Open OrangeHRM site
		driver.get("http://hrm.local:3050");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@Test(priority = 1)
	public void verifyLogin() {
		// Step A: Login
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		driver.findElement(By.id("btnLogin")).click();

		// Verify Dashboard heading
		WebElement dashboard = driver.findElement(By.xpath("//h1[text()='Dashboard']"));
		//System.out.println(dashboard.getText());
		assertEquals(dashboard.getText(),"Dashboard");
	}

	@Test(priority = 2)
	public void applyLeaveTest() throws InterruptedException {
		//Navigate to Apply Leave
		driver.findElement(By.id("menu_leave_viewLeaveModule")).click();
		driver.findElement(By.id("menu_leave_applyLeave")).click();

		// Select Leave Type from dropdown
		WebElement dropdown = driver.findElement(By.id("applyleave_txtLeaveType"));
		Select leaveTypeDropDown = new Select(dropdown);
		leaveTypeDropDown.selectByVisibleText("Sick leaves");

		// Enter From Date
		WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
		fromDate.clear();
		fromDate.sendKeys("2026-10-13");

		// Enter To Date
		WebElement toDate = driver.findElement(By.id("applyleave_txtToDate"));
		toDate.clear();
		toDate.sendKeys("2026-10-15");

		// Click Apply
		driver.findElement(By.id("applyBtn")).click();
		driver.findElement(By.id("applyBtn")).click();

	}

	@Test(priority = 3)
	    public void verifyLeaveRequest() {
			wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_viewMyLeaveList"))).click();
			String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[6]")))
					.getText();

//			System.out.println("Leave Status: " + status);
			// Verify the leave status
			
			Assert.assertEquals(status,"Pending Approval(3.00)");
			
	    }

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
