package com.effectivetesting.gluecode;

import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.effectivetesting.entity.User;
import com.effectivetesting.global.TestGlobal;
import com.effectivetesting.helper.UserHelper;
import com.effectivetesting.injector.EntryInjector;
import com.effectivetesting.injector.UserInjector;
import com.effectivetesting.pageobject.EntryCreationPageObject;
import com.effectivetesting.pageobject.HomePageObject;
import com.effectivetesting.pageobject.LoginPageObject;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

public class DraftEntryGlue {	
	private WebDriver driver;
	private LoginPageObject loginPage;
	private HomePageObject homePage;
	private EntryCreationPageObject entryCreationPage;
	private String createdEntry;
	
	@Before("~@wip")
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(TestGlobal.DEFAULT_BASE_URL);
		loginPage = new LoginPageObject(driver);
		
		User user = UserHelper.define(TestGlobal.USER_ID, 
									  TestGlobal.USER_EMAIL, 
									  TestGlobal.USER_PASSWORD, 
									  TestGlobal.USER_NAME);
		UserInjector.create(user);
	}
	
	@After("~@wip")
	public void teardDown() {
		EntryInjector.erase();
		UserInjector.erase(TestGlobal.USER_ID);
		
		driver.quit();
	}
	
	@Dado("^que me encuentro logueado$")
	public void que_me_encuentro_logueado() throws Throwable {
		homePage = loginPage.login(TestGlobal.USER_EMAIL, TestGlobal.USER_PASSWORD);
	}

	@Cuando("^guardo la entrada \"([^\"]*)\" con texto \"([^\"]*)\" y estado \"([^\"]*)\"$")
	public void guardo_la_entrada_con_texto_y_estado(String title, String body, String state) throws Throwable {
		this.createdEntry = title;
		
		entryCreationPage = homePage
								.goToCreateEntry()
								.create(title, body, state);
	}

	@Cuando("^me deslogueo$")
	public void me_deslogueo() throws Throwable {
	    entryCreationPage.logOut();
	}

	@Entonces("^no debería poder ver la entrada en el listado$")
	public void no_debería_poder_ver_la_entrada_en_el_listado() throws Throwable {
		String currentEntries = entryCreationPage
									.goToBlog()
									.getEntries();
		
		assertFalse(currentEntries.contains(createdEntry));
	}
	
}
