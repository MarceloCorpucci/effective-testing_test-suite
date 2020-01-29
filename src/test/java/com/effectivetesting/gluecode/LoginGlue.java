package com.effectivetesting.gluecode;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.effectivetesting.entity.User;
import com.effectivetesting.global.TestGlobal;
import com.effectivetesting.helper.UserHelper;
import com.effectivetesting.injector.UserInjector;
import com.effectivetesting.pageobject.HomePageObject;
import com.effectivetesting.pageobject.LoginPageObject;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class LoginGlue {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private String email;
	private String pass;
	
	@After("@wip")
	public void teardDown() {
		UserInjector.erase(TestGlobal.USER_ID);
		
		driver.quit();
	}
	
	@Dado("^el usuario con email \"([^\"]*)\" con pass \"([^\"]*)\"$")
	public void el_usuario_con_email_con_pass(String email, String pass) throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(TestGlobal.DEFAULT_BASE_URL);
		loginPage = new LoginPageObject(driver);
		
		this.email = email;
		this.pass = pass;
		
		User user = UserHelper.define(TestGlobal.USER_ID, 
									  TestGlobal.USER_EMAIL, 
									  TestGlobal.USER_PASSWORD, 
									  TestGlobal.USER_NAME);
		UserInjector.create(user);
	}

	@Cuando("^se loguea al blog$")
	public void se_loguea_al_blog() throws Throwable {
		homePage = loginPage.login(this.email, this.pass);			
	}

	@Entonces("^debería obtener el resultado \"([^\"]*)\"$")
	public void debería_obtener_el_resultado(String expectedResult) throws Throwable {
		String currentResult = "";
		
		//TODO Make a strategy for every permutation.
		if(this.email == "user99@gmail.com") {
    		currentResult = homePage.getLoginResult();
		}
		
		if(this.email == "not_exists@gmail.com") {
    		currentResult = loginPage.getEmailOrPassError();
		}
		
		if(this.email == "deactivated@gmail.com") {
    		currentResult = loginPage.getDeactivatedUserError();
		}
		
		if(this.email == "user99@gmail.com" & this.pass == "") {
    		currentResult = loginPage.getRequiredPassError();
		}
		
		if(this.email == "" & this.pass == "user99") {
    		currentResult = loginPage.getEmailOrPassError();
		}
		
		assertTrue(expectedResult.contains(currentResult));
	}
}
