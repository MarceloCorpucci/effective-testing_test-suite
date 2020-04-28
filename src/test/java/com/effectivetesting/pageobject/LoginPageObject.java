package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public HomePageObject login(String email, String password) {
		driver.findElement(By.id("login")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.id("btn-submit")).click();
		
		return new HomePageObject(driver);
	}
}
