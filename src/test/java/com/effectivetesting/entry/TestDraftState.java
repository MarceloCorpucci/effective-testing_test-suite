package com.effectivetesting.entry;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.effectivetesting.entity.User;
import com.effectivetesting.global.TestGlobal;
import com.effectivetesting.helper.UserHelper;
import com.effectivetesting.injector.EntryInjector;
import com.effectivetesting.injector.UserInjector;
import com.effectivetesting.pageobject.LoginPageObject;

public class TestDraftState {
	private WebDriver driver;
	private LoginPageObject loginPage;
	
	@Before
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
	
	@Test
	public void DraftStateShouldNotBeVisibleForVisitors() {
		String entryList = loginPage
								.login(TestGlobal.USER_EMAIL, TestGlobal.USER_PASSWORD)
								.goToCreateEntry()
								.create("New entry", "this is a simple test", "Draft")
								.logOut()
								.goToBlog()
								.getEntries();

		assertFalse(entryList.contains("My newest post"));
	}
	
	@After
	public void teardDown() {
		EntryInjector.erase();
		UserInjector.erase(TestGlobal.USER_ID);
		
		driver.quit();
	}

}
