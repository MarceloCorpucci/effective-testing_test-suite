package com.effectivetesting.sanity;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPhpTravelsDemo {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.phptravels.net/index.php");
	}
	
	@Test
	public void myAccountButtonShouldBePresent() {
		boolean buttonIsPresent = driver.findElement(By.xpath("//*[@id=\"header-waypoint-sticky\"]/div[1]/div/div/div[2]/div/ul/li[3]/div")).isDisplayed();
		assertTrue(buttonIsPresent);
	}
	
	@Test
	public void navigation() {
		driver.findElement(By.xpath("//*[@id=\"mobileMenuMain\"]/nav/ul/li[2]")).click();
		String currentUrl = driver.getCurrentUrl();
		
		assertTrue(currentUrl.endsWith("/blog"));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
