package activities;

//Goal: Login and retrieve the emergency contacts for the user 

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Activity9 {
	// Declare WebDriver
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void setup() {
		// Initialize the driver object
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Open the page
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");
	}

	@Test
	@Parameters({ "username", "password" })
	public void login(String username, String password) {
		// Find input fields
		WebElement usernameField = driver.findElement(By.id("txtUsername"));
		WebElement passwordField = driver.findElement(By.id("txtPassword"));

		// Enter credentials
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);

		// Cicking Login Button
		driver.findElement(By.id("btnLogin")).click();

		// Waiting for Welcome Pannel
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));

		// Assert confirming login
		WebElement welcomeMessage = driver.findElement(By.className("panelTrigger"));

		// Get text of that Web Element
		String message = welcomeMessage.getText();

		Assert.assertTrue(message.contains("Welcome"));
	}

	@Test(dependsOnMethods = "login")
	public void navigateToMyInfo() {
		WebElement myInfo = wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewMyDetails")));
		myInfo.click();
		WebElement myInfoContainer = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.id("pdMainContainer")));
		Assert.assertTrue(myInfoContainer.isDisplayed(), "Navigation to My Info failed");
	}

	@Test(dependsOnMethods = "navigateToMyInfo")
	public void navigateToEmergencyContact() {
		driver.findElement(By.linkText("Emergency Contacts")).click();
		WebElement EmergencyCont = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listEmegrencyContact")));
		Assert.assertTrue(EmergencyCont.isDisplayed(), "Emergency Contact section is not visible");
	}
	
	public static class DataClass {
		@DataProvider
		public Object[][] contactData() {
			return new Object[][] { 
					{ "Abc", "Brother", "11111", "22222", "33333" }, // All fields are filled
					{ "Efg", "Mom", "11111", "", "" }, // Partial Fill
					{ "Hij", "", "11111", "", "" } // Should fail
			};
		}
	}

	@Test(dependsOnMethods = "navigateToEmergencyContact", dataProvider = "contactData", dataProviderClass = DataClass.class, enabled = false)
	public void addEmergencyContactDetails(String name, String relation, String home, String mobile, String work) {
		// Click add button
		WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnAddContact")));
		addButton.click();

		// Add Data
		driver.findElement(By.id("emgcontacts_name")).sendKeys(name);
		driver.findElement(By.id("emgcontacts_relationship")).sendKeys(relation);
		driver.findElement(By.id("emgcontacts_homePhone")).sendKeys(home);
		driver.findElement(By.id("emgcontacts_mobilePhone")).sendKeys(mobile);
		driver.findElement(By.id("emgcontacts_workPhone")).sendKeys(work);

		// Submit data
		driver.findElement(By.id("btnSaveEContact")).click();

		// Verify it was saved successfully
		WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message.success")));
		String message = successMessage.getText();
		Assert.assertTrue(message.contains("Saved"));
	}
	
	@Test(dependsOnMethods = "navigateToEmergencyContact")
	public void printingEmergCont() {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emgcontact_list")));
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='emgcontact_list']/tbody/tr"));
		for (WebElement row: rows) {
			System.out.println(row.getText());
		}
	}

	@AfterClass
	public void tearDown() {
		// Close the browser
		driver.quit();
	}

}
