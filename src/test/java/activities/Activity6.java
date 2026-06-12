package activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Activity6 {
	WebDriver driver = new FirefoxDriver();
    WebDriverWait wait;
    @Test(priority=1)
    public void verifyDirectoryMenu() {
    	wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.get("http://hrm.local:3050");
        driver.findElement(By.id("txtUsername")).sendKeys("orange");
        driver.findElement(By.name("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
        driver.findElement(By.id("btnLogin")).click();
        wait.until(ExpectedConditions.urlContains("http://hrm.local:3050/symfony/web/index.php/dashboard"));
        driver.findElement(By.id("menu_directory_viewDirectory")).click();
        //WebElement heading = driver.findElement(By.xpath("//h6[text()='Search Directory']")).getText();
        Assert.assertEquals(driver.getCurrentUrl(),"http://hrm.local:3050/symfony/web/index.php/directory/viewDirectory/reset/1");
    }
    @Test(priority=2)
    public void verify() {
    	
        
        String heading= driver.findElement(By.xpath("//div[@class='head']/h1[text()='Search Directory']")).getText();

        Assert.assertEquals(heading, "Search Directory");

        driver.quit();
    }
}