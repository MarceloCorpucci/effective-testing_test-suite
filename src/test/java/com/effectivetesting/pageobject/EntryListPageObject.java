package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntryListPageObject {
	private WebDriver driver;
	
	public EntryListPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getEntries() {
		return driver.findElement(By.id("content_title")).getText();
	}

}
