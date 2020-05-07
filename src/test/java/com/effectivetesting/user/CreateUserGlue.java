package com.effectivetesting.user;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.given;

import com.effectivetesting.helpers.UserHelper;

import cucumber.api.java.After;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import io.restassured.specification.RequestSpecification;

public class CreateUserGlue {
	private static final String DEFAULT_BASE_URL = "http://effective-testing.herokuapp.com/api";
	private static final String ID = "23";
	RequestSpecification request;
	
	@Dado("^el usuario \"([^\"]*)\"$")
	public void el_usuario(String arg1) throws Throwable {
		//TODO: Enviar nombre por parametro al helper.
		UserHelper userHelper = new UserHelper();
		
         request = given()
        	.contentType("application/json")
        	.body(userHelper.createTestUser(ID));
	}

	@Cuando("^es creado$")
	public void es_creado() throws Throwable {
		request.when()
			.post(DEFAULT_BASE_URL + "/user");
	}

	@Entonces("^obtengo estado (\\d+)$")
	public void obtengo_estado(int arg1) throws Throwable {
		request.then()
			.statusCode(arg1);
	}
	
	@After
    public void tearDown() {
        delete(DEFAULT_BASE_URL + "/user/" + ID);
    }
}
