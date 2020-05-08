package com.ignite.step_defs;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SampleAPI {
    String applicationNO = "5902002579";
    String body = "{\"username\": \"demo\",\"password\": \"teacher1\"}";
    String sessionID;
    String aspxAuth;
    String cookieHeader;

    Header xAppVersion_Header = new Header("X-App-Version","2.1.0");
    Header xClientType_Header = new Header("X-Client-Type","ISA");
    Header contentType = new Header("Content-Type","application/json");
    Header cookie_Header;

    Cookie cookie_ASPX;
    Cookie cookie_SESSION_ID;

    RequestSpecification request;
    Response response;

    @BeforeClass
    public static void setUp(){
        RestAssured.baseURI = "https://ignite.sis.cool/api/";
    }

    @Test
    public void loginAndStoreCookies_1(){

        // without logAll() and without assigning Request
        response = given()
                .header("Content-Type","application/json")
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .body(body)
                .post("account/login");

        //===============   CREATING COOKIE HEADER WITH DIFFERENT METHODS   ================
        // WAY 1   -> assigning cookies separately and then assigning to cookieHeader
        getCookiesSeparately(response);

        // WAY2   ->   assigning to cookieHeader without assigning cookies separately
        createCookieHeaderFromResponse(response);


    }

    @Test
    public void loginAndStoreCookies_2(){
        // creating Request object
        request = given()
                .header("Content-Type","application/json")
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA");

        request.body(body);

        // creating response
        response = request.post("https://ignite.sis.cool/api/account/login");

        // creating  Cookie Header and assigning to cookieHeader
        createCookieHeaderFromResponse(response);

    }


    @Test
    public void loginAndStoreCookies_3(){
        // with logAll()  -> if we use anything after post(), we have to use extract().response()
        response = given()
                .header("Content-Type","application/json")
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .body(body)
                .post("account/login")
                .then()
                .log()
                .all().extract().response();

        //===============   CREATING COOKIE HEADER WITH DIFFERENT METHODS   ================
        // WAY 1   -> assigning cookies separately and then assigning to cookieHeader
        getCookiesSeparately(response);

        // WAY2   ->   assigning to cookieHeader without assigning cookies separately
        createCookieHeaderFromResponse(response);


    }

    @Test
    public void getApplication_1(){
        // login call
        loginAndStoreCookies_1();

        // application call
        System.out.println("\n========================\n APPLICATION CALL -> GET \n========================\n");

        // make get Call to get the application
        Response appResponse = given()
                .header("Cookie", cookieHeader)
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .get("academic/applications/" + applicationNO)
                .then()
                .log()
                .all()
                .extract()
                .response();

        String key = "applicationsList";

        List<HashMap> appList = appResponse.jsonPath().getList(key);
        HashMap<String, String> application = appList.get(0);

        String stdFullName = application.get("studentFullName");
        System.out.println("==============\n Student FullName: " + stdFullName);

        // We can create Headers separately and use
        Header xAppVersion = new Header("X-App-Version","2.1.0");
        Header xClientType = new Header("X-Client-Type","ISA");
    }



    @Test
    public void using_COOKIE_Instead_Of_String_1(){
        response = given()
                .header("Content-Type","application/json")
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .body(body)
                .post("account/login");

        cookie_ASPX = response.getDetailedCookie(".ASPXAUTH");
        cookie_SESSION_ID = response.getDetailedCookie("CoolSIS_SessionId");

        System.out.println(cookie_ASPX.toString());


        Response appResponse = given()
                .header("Cookie", (cookie_ASPX +";"+ cookie_SESSION_ID))
                .header(xAppVersion_Header)
                .header(xClientType_Header)
                .get("academic/applications/" + applicationNO)
                .then()
                .log()
                .all()
                .extract()
                .response();

    }


    @Test
    public void using_HEADER_COOKIE_Instead_Of_String(){
        response = given()
                .header("Content-Type","application/json")
                .header("X-App-Version","2.1.0")
                .header("X-Client-Type","ISA")
                .body(body)
                .post("account/login");

        cookie_ASPX = response.getDetailedCookie(".ASPXAUTH");
        cookie_SESSION_ID = response.getDetailedCookie("CoolSIS_SessionId");

        System.out.println(cookie_ASPX.toString());


        //assigning cookie_Header
        cookie_Header = new Header("Cookie", (cookie_ASPX +";"+ cookie_SESSION_ID));

        Response appResponse = given()
                .header(cookie_Header)
                .header(xAppVersion_Header)
                .header(xClientType_Header)
                .get("academic/applications/" + applicationNO)
                .then()
                .log()
                .all()
                .extract()
                .response();

    }


    @Test
    public void using_HEADERS_List(){
        // create a Header List and add headers
        List<Header> headerList = new ArrayList<Header>();
        headerList.add(contentType);
        headerList.add(xAppVersion_Header);
        headerList.add(xClientType_Header);

        // create Headers from Header List
        Headers headers = new Headers(headerList);

        response = given()
                .headers(headers)
                .body(body)
                .post("account/login");

        // getCookies
        cookie_ASPX = response.getDetailedCookie(".ASPXAUTH");
        cookie_SESSION_ID = response.getDetailedCookie("CoolSIS_SessionId");

        //assigning cookie_Header
        cookie_Header = new Header("Cookie", (cookie_ASPX +";"+ cookie_SESSION_ID));

        // add  new header to headersList
        headerList.add(cookie_Header);  // if you want you can remove content type
        headers = new Headers(headerList);

        Response appResponse = given()
                .headers(headers)
                .get("academic/applications/" + applicationNO)
                .then()
                .log()
                .all()
                .extract()
                .response();

    }



    @Test
    public void using_HEADERS_Map(){

        // WAY 1 ==>> create a Header Map and put headers
        Map<String,String> loggingHeaders = new HashMap<String,String>();

        loggingHeaders.put("Content-Type","application/json");
        loggingHeaders.put("X-App-Version","2.1.0");
        loggingHeaders.put("X-Client-Type","ISA");

        response = given()
                .headers(loggingHeaders)
                .body(body)
                .post("account/login");

        // getCookies
        cookie_ASPX = response.getDetailedCookie(".ASPXAUTH");
        cookie_SESSION_ID = response.getDetailedCookie("CoolSIS_SessionId");

        //assigning cookie_Header
        cookie_Header = new Header("Cookie", (cookie_ASPX +";"+ cookie_SESSION_ID));


        // WAY 2 ==>> create a Header Map and put DEFINED headers
        Map<String,String> appHeaders = new HashMap<String,String>();

        appHeaders.put(cookie_Header.getName(),cookie_Header.getValue());
        appHeaders.put(xAppVersion_Header.getName(),xAppVersion_Header.getValue());
        appHeaders.put(xClientType_Header.getName(),xClientType_Header.getValue());

        Response appResponse = given()
                .headers(appHeaders)
                .get("academic/applications/" + applicationNO)
                .then()
                .log()
                .all()
                .extract()
                .response();

    }

    // ========================================================================
    // ==                                                                    ==
    // ==                          GET COOKIES METHODS                       ==
    // ==                                                                    ==
    // ========================================================================


    public void getCookiesSeparately(Response response){
        // WAY 1   -> assigning cookies separately and then assigning to cookieHeader
        aspxAuth = ".ASPXAUTH=" + response.getCookie(".ASPXAUTH");
        sessionID = "CoolSIS_SessionId=" + response.getCookie("CoolSIS_SessionId");
        cookieHeader = sessionID + "; " + aspxAuth;

        System.out.println(aspxAuth);
        System.out.println(sessionID);
        System.out.println("WAY 1: " + cookieHeader);
    }


    public void createCookieHeaderFromResponse(Response response){
        // WAY2   ->   assigning to cookieHeader without assigning cookies separately
        cookieHeader = response.getCookies().toString().replaceAll("[{}]","").replace(",",";");

        System.out.println("WAY 2: " + cookieHeader);
    }
}
