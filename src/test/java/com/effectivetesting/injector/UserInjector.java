package com.effectivetesting.injector;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

import com.effectivetesting.entity.User;
import com.effectivetesting.global.TestGlobal;

public class UserInjector {
	public static void create(User user) {
		given()
			.contentType("application/json")
			.body(user)
		.when()
			.post(TestGlobal.DEFAULT_BASE_URL + "/api/user");
	}
	
	public static void erase(String id) {
		delete(TestGlobal.DEFAULT_BASE_URL + "/api/user/" + id);
	}
}
