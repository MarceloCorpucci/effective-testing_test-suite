package com.effectivetesting.injector;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

import java.util.List;

import com.effectivetesting.global.TestGlobal;

import io.restassured.path.json.JsonPath;

public class EntryInjector {
	public static void erase() {
		int entryId = findLastEntryId();
		delete(TestGlobal.DEFAULT_BASE_URL + "/api/entry/" + entryId);
	}
	
	private static int findLastEntryId() {
		JsonPath json = given()
				.contentType("application/json")
			.when()
				.get(TestGlobal.DEFAULT_BASE_URL + "/api/entry")
			.thenReturn()
				.jsonPath();

		List<Integer> entryIds = json.getJsonObject("objects.id");
	
		return entryIds.get(entryIds.size() - 1);
	}
}
