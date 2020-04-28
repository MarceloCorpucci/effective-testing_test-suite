package com.effectivetesting.sanity;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.effectivetesting.pageobject.LoginPageObject;

public class TestCreateEntry {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	private String entryName;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/marcelocorpucci/Chromedriver/chromedriver");
		driver = new ChromeDriver();
		
		driver.get("http://effective-testing.herokuapp.com/");

		loginPage = new LoginPageObject(driver);
		
		entryName = "Primer post de Marcelo";
	}

	@Test
	public void entryShouldBeCreatedSuccessfully() {
		String currentName = loginPage
								.login("admin1@gmail.com", "admin1")
								.goToCreateEntry()
								.createNew(entryName, "test 123 123")
								.getSavedEntryName();
		
		assertTrue(currentName.contains(entryName));
	}
	
	@After
	public void tearDown() {
		driver.get("http://effective-testing.herokuapp.com/admin/entry/");
		
		String index = driver.findElement(By.xpath("/html/body/div/ul/li[1]/a")).getText().replaceAll("[^0-9]", "");
		
		for(int i = 1; i <= Integer.parseInt(index) ;i++) {
			if (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[" + i + "]/td[3]")).getText().equals(entryName)) {
				driver.findElement(By.xpath("/html/body/div/table/tbody/tr[" + i + "]/td[2]/form/button")).click();
				break;
			}
			
		}
		
	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		   
	    alert.accept();
	    
	    WebDriverWait waitForMessage = new WebDriverWait(driver, 10);
	    waitForMessage.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[2]")));
		
	}
}
