package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageObject {
	private WebDriver driver;
	private By createPostButton = By.id("create_post");
	private By blogButton = By.id("blog");
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public EntryCreationPageObject goToCreateEntry() {
		driver.findElement(createPostButton).click();
		
		return new EntryCreationPageObject(driver);
	}

	public EntryListPageObject goToEntryList() {
		driver.findElement(blogButton).click();
		
		return new EntryListPageObject(driver);
	}
}
