package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Activity5 {
	WebDriver driver;

	WebDriverWait wait;
    
	@BeforeClass()
	public void setUp() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("http://hrm.local:3050/");
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 1)
	public void loginPage() {
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		driver.findElement(By.id("btnLogin")).click();
		//check if on dashboard page
		// Waiting for Welcome Pannel
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));

		// Assert confirming login
		WebElement welcomeMessage = driver.findElement(By.className("panelTrigger"));

		// Get text of that Web Element
		String message = welcomeMessage.getText();

		Assert.assertTrue(message.contains("Welcome"));
	}

	@Test(priority = 2)
	public void openMyInfo() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("menu_pim_viewMyDetails")).click();
		wait.until(ExpectedConditions.urlContains("viewMyDetails"));
		Assert.assertTrue(driver.getCurrentUrl().contains("viewMyDetails"));
	}

	@Test(priority = 3)
	public void editPersonalInfo() {

		driver.findElement(By.id("btnSave")).click();
		WebElement ftName = driver.findElement(By.id("personal_txtEmpFirstName"));
		ftName.sendKeys(Keys.CONTROL + "a");
		ftName.sendKeys(Keys.DELETE);
		ftName.sendKeys("John");
		WebElement ltName = driver.findElement(By.id("personal_txtEmpLastName"));
		ltName.sendKeys(Keys.CONTROL + "a");
		ltName.sendKeys(Keys.DELETE);
		ltName.sendKeys("Doe");
		WebElement maleRadio = driver.findElement(By.xpath("//input[@id='personal_optGender_1']"));
		WebElement femaleRadio = driver.findElement(By.xpath("//input[@id='personal_optGender_2']"));
		maleRadio.click();

		WebElement dropdown = driver.findElement(By.id("personal_cmbNation"));
		Select select = new Select(dropdown);
		select.selectByVisibleText("Indian");

		driver.findElement(By.id("btnSave")).click();

		String message = driver.findElement(By.cssSelector("div.message.success.fadable")).getText();
		Assert.assertTrue(message.contains("Successfully Saved"));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();

	}
}
