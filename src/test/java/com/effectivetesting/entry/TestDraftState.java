package com.effectivetesting.entry;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.effectivetesting.pageobject.LoginPageObject;

public class TestDraftState {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("localhost:5000");
		loginPage = new LoginPageObject(driver);
	}
	
	@Test
	public void DraftStateShouldNotBeVisibleForVisitors() {
		String entryList = loginPage
								.login("admin1@gmail.com", "admin1")
								.goToCreateEntry()
								.create("New entry", "this is a simple test", "Draft")
								.logOut()
								.goToBlog()
								.getEntries();

		assertFalse(entryList.contains("My newest post"));
	}
	
	@After
	public void teardDown() {
//		driver.get("http://localhost:5000");
//		driver.findElement(By.id("login")).click();
//		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
//		driver.findElement(By.id("password")).sendKeys("admin1");
//		driver.findElement(By.id("btn-submit")).click();
//		
//		driver.get("http://localhost:5000/admin/entry/");
//		
//		driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/form")).click();
//		
//	    WebDriverWait wait = new WebDriverWait(driver, 10);
//	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//		   
//	    alert.accept();
//	    
//	    WebDriverWait waitForMessage = new WebDriverWait(driver, 10);
//	    waitForMessage.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]")));
//	    
//	    driver.quit();
	}

}
