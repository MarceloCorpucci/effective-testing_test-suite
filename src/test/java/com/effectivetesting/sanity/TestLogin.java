package com.effectivetesting.sanity;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {
	private WebDriver driver;
	
	@Test
	public void successfulLoginMessageShouldAppear() {
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();

		String expectedMessage = "Successfully logged in as admin1@gmail.com.";
		String currentMessage = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div/span")).getText();
		assertEquals(expectedMessage, currentMessage);
	}

	@Test
	public void createEntryUrlShouldAppear() {
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("admin1@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
		driver.findElement(By.id("create_post")).click();
		
		assertEquals("http://localhost:5000/entries/create/", driver.getCurrentUrl());
	}
	
	@Test
	public void InvalidEmailThrowsError() {
		driver.findElement(By.id("login")).click();
		driver.findElement(By.id("email")).sendKeys("adminTEST@gmail.com");
		driver.findElement(By.id("password")).sendKeys("admin1");
		driver.findElement(By.id("btn-submit")).click();
		
		String currentErrorMessage = driver.findElement(By.xpath("//*[@id=\"content_title\"]/form/div[1]/div/span[2]")).getText();
		assertEquals("Invalid email or password.", currentErrorMessage);
	}
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("localhost:5000");
	}

	@After
	public void teardDown() {
		driver.quit();
	}
	
}
