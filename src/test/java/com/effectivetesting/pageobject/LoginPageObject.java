package com.effectivetesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObject {
	private WebDriver driver;
	
	private Header header;
	
	private By emailField = By.id("email");
	private By passField = By.id("password");
	private By submitButton = By.id("btn-submit");
	private By notification = By.id("notification");

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		this.header = new Header(driver);
	}
	
	public HomePageObject login(String mail, String pass) {
		this.header.getLoginButton().click();
		driver.findElement(emailField).sendKeys(mail);
		driver.findElement(passField).sendKeys(pass);
		driver.findElement(submitButton).click();
		
		return new HomePageObject(driver);

	}
	
	public HomePageObject logOut() {
		this.header.getLogoutButton().click();
		
		WebDriverWait waitForLogout = new WebDriverWait(driver, 20);
		waitForLogout.until(ExpectedConditions.presenceOfElementLocated(notification));

		return new HomePageObject(driver);
	}

	public String getEmailOrPassError() {
		return driver.findElement(By.xpath("//*[@id=\"content_title\"]/form/div[1]/div/span[2]")).getText();
	}

	public String getDeactivatedUserError() {
		return driver.findElement(By.xpath("//*[@id=\"notification\"]/span")).getText();
	}

	public String getRequiredPassError() {
		return driver.findElement(By.xpath("//*[@id=\"content_title\"]/form/div[2]/div/span[2]")).getText();
	}

}
