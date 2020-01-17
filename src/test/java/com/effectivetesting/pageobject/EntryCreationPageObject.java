package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntryCreationPageObject {
	private WebDriver driver;
	
	private Header header;
	
	private By titleField = By.id("title");
	private By bodyField = By.id("body");
	private By stateDropDown = By.id("status");
	private By submitButton = By.xpath("/html/body/div[2]/div[1]/div[1]/form/div[5]/div/button");
	private By notification = By.xpath("/html/body/div[2]/div[1]/div[1]/div/span");
	
	public EntryCreationPageObject(WebDriver driver) {
		this.driver = driver;
		this.header = new Header(driver);
	}
	
	public EntryCreationPageObject create(String title, String body, String state) {
		driver.findElement(titleField).sendKeys(title);
		driver.findElement(bodyField).sendKeys(body);
		
		Select dropdown = new Select(driver.findElement(stateDropDown));
		dropdown.selectByVisibleText(state);
		
		driver.findElement(submitButton).click();
		
		WebDriverWait waitForMessage = new WebDriverWait(driver, 20);
		waitForMessage.until(ExpectedConditions.presenceOfElementLocated(notification));
	
		return this;
	}
	
	public EntryCreationPageObject logOut() {
		this.header.getLogoutButton().click();
		
		return this;
	}
	
	public EntryListPageObject goToBlog() {
		this.header.getBlogButton().click();
		
		return new EntryListPageObject(driver);
	}
	
}
