package com.ignite.step_defs;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SaturdayAPI {

    RequestSpecification request;
    Response response;

    String body = "{\"username\": \"demo\",\"password\": \"teacher1\"}";

    Header contentType = new Header("Content-Type","application/json");
    Header xAppVersion = new Header("X-App-Version","2.1.0");
    Header xClientType = new Header("X-Client-Type","ASA");
    Header cookieHeader;

    Cookie sessionID;
    Cookie aspxAuth;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://ignite.sis.cool/api/";
    }


    @Test
    public void test2(){
        loginAndStoreCookieHeader();

        getApplication("5902002580");
    }


    @Test
    public void test3(){
        loginAndStoreCookieHeader();

        getApplication("5902002579");
    }

    @Test
    public void test4(){
        loginAndStoreCookieHeader();

        getApplication("1231242002579");
    }









    public void loginAndStoreCookieHeader(){
        response = given()
                .header(contentType).header(xAppVersion).header(xClientType)
                .body(body)
                .post( "account/login");

        getCookies(response);

        cookieHeader = new Header("Cookie",aspxAuth +";"+sessionID);
    }

    public void getCookies(Response response){
        sessionID = response.getDetailedCookie("CoolSIS_SessionId");
        aspxAuth = response.getDetailedCookie(".ASPXAUTH");
    }

    public void getApplication(String applicationNo){
        response = given()
                .header(cookieHeader)
                .header(xAppVersion)
                .header(xClientType)
                .get("academic/applications/" + applicationNo);

        response.then().log().all();
    }




}
