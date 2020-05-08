package com.ignite.step_defs.API_SQL_Samples;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SampleAPI {

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://ignite.sis.cool/api/";
    }

    @Test
    public void loginCall(){
        String appNo = "5902000548";
        String body = "{\"username\": \"demo\",\"password\": \"teacher1\"}";

        Response response = given()
                .body(body)
                .header("Content-Type","application/json")
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .post("account/login").then().log().all()
                .extract()
                .response();


        String coolsisSessionID = response.getCookie("CoolSIS_SessionId");
        String aspxAuthCookie = response.getCookie(".ASPXAUTH");


        System.out.println( "SessionID: " + coolsisSessionID + "\nASPXAUTH: " + aspxAuthCookie);

        String cookieHeader = ".ASPXAUTH="+ aspxAuthCookie+"; path=/; HttpOnly;CoolSIS_SessionId="+coolsisSessionID+"; path=/; HttpOnly";

        Response applicationResponse = given()
                .header("Cookie", cookieHeader)
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .get("academic/applications/" + appNo)
                .then().log().all()
                .extract().response();

        String key = "applicationsList";
        List<HashMap<String,String>> applicationList = applicationResponse.jsonPath().getList(key);

        HashMap<String,String> application = applicationList.get(0);

        System.out.println("Student LastName: " + application.get("studentLastName"));

    }
}
