package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntryPageObject {
	private WebDriver driver;

	public EntryPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getSavedEntryName() {
		return driver.findElement(By.id("content_title")).getText();
	}
}
