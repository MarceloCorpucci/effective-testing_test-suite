package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Header {
	private WebDriver driver;

	private By loginButton = By.id("login");
	private By logOutButton = By.id("logout");
	private By blogButton = By.id("blog");
	
	public Header(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getLoginButton() {
		return driver.findElement(loginButton);
	}

	public WebElement getLogoutButton() {
		return driver.findElement(logOutButton);
	}
	
	public WebElement getBlogButton() {
		return driver.findElement(blogButton);
	}
	
}
