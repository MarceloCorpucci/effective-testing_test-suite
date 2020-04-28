package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateEntryPageObject {
	private WebDriver driver;

	public CreateEntryPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public EntryPageObject createNew(String title, String body) {
		driver.findElement(By.id("title")).sendKeys(title);
		driver.findElement(By.id("body")).sendKeys(body);
		driver.findElement(By.id("post")).click();
		
		return new EntryPageObject(driver);
	}
}
