package com.effectivetesting.sanity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWelcomeMessage {
	private WebDriver driver;
	 
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/Users/marcelocorpucci/Chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://effective-testing.herokuapp.com/");
	}
	
	@Test
	public void pageTitleShouldAppear() {
		String pageTitle = driver.getTitle();		  
		assertEquals("Effective Testing", pageTitle);	    
	}
	
	@Test
	public void blogSectionShouldBeAvailable() {
		driver.findElement(By.id("blog")).click();
		String currentUrl = driver.getCurrentUrl();
		
		assertTrue(currentUrl.endsWith("/entries/"));
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
}


