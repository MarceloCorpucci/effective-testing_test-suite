package com.effectivetesting.entry;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.delete;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.effectivetesting.pageobject.LoginPageObject;

import io.restassured.path.json.JsonPath;

public class TestDraftState {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private String DEFAULT_BASE_URL = "http://localhost:5000";
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(DEFAULT_BASE_URL);
		loginPage = new LoginPageObject(driver);
	}
	
	@Test
	public void DraftStateShouldNotBeVisibleForVisitors() {
		String entryList = loginPage
								.login("admin1@gmail.com", "admin1")
								.goToCreateEntry()
								.create("New entry", "this is a simple test", "Draft")
								.logOut()
								.goToBlog()
								.getEntries();

		assertFalse(entryList.contains("My newest post"));
	}
	
	@After
	public void teardDown() {
		JsonPath json = given()
							.contentType("application/json")
						.when()
							.get(DEFAULT_BASE_URL + "/api/entry")
						.thenReturn()
							.jsonPath();
		
		List<Integer> entryIds = json.getJsonObject("objects.id");

		delete(DEFAULT_BASE_URL + "/api/entry/" + entryIds.get(entryIds.size() - 1));
		
		driver.quit();
	}

}
