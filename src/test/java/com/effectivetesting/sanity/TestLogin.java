package com.effectivetesting.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/marcelocorpucci/Chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://effective-testing.herokuapp.com/");

		driver.findElement(By.id("login")).click();
		
		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		
		driver.findElement(By.id("btn-submit")).click();
	}
	
	@Test
	public void messageShouldAppear() {
		String currentMessage = driver.findElement(By.xpath("//*[@id=\"notification\"]/span")).getText();
		assertEquals(currentMessage, "Successfully logged in as admin1@gmail.com.");
	}
	
	@Test
	public void createPostShouldBeAvailable() {
		driver.findElement(By.id("create_post")).click();
		String currentUrl = driver.getCurrentUrl();
		assertTrue(currentUrl.endsWith("/entries/create/"));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
}
