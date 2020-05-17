package com.ignite.step_defs;

import com.ignite.pojos.Parent;
import com.ignite.pojos.Student;
import com.ignite.utilities.ExcelUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ExcelTests {
    public String path = "test_resources/EIF-TestCases.xlsx";
    public String sheetName = "users";
    public String application = "application1";
    public int idxApplication;

    public ExcelUtils data = new ExcelUtils(path, sheetName);

    public Parent myParent;
    public Student myStudent;


    @Test
    public void test1() {

        data = new ExcelUtils(path, sheetName);

        myParent = createParent(data, idxApplication);

        System.out.println(myParent.toString());

    }

    @Test
    public void test2() {
        application = "application2";
        idxApplication = getIdxApp(application);
        String wholeTable[][] = data.getDataArray();

        myParent = createParent(wholeTable);
        System.out.println(myParent.toString());

    }


    @Test
    public void test3() {
        application = "application3";

        List<Map<String,String>> wholeMap = data.getDataList();
//        wholeMap.get(1).forEach((key,value) ->System.out.println(key + ": " + value));

        myParent = createParent(wholeMap);

        System.out.println(myParent.toString());
    }


    @Test
    public void test4() {
        application = "application4";
        idxApplication = getIdxApp(application);

        List<Map<String,String>> wholeRowMap = data.getDataMapRow();

        myParent = createParent(wholeRowMap.get(idxApplication-1));

        System.out.println(myParent.toString());


    }


    public Parent createParent(Map<String,String> data){
        Parent parent = new Parent();
        parent.firstName = data.get(parentFirstName);
        parent.lastName = data.get(parentLastName);
        parent.hdyhau = data.get(hdyhau);
        parent.street = data.get(street);
        parent.city = data.get(city);
        parent.state = data.get(state);
        parent.zipCode = Integer.parseInt(data.get(zipcode).substring(0, 5));
        parent.email = data.get(state);
        parent.homeNumber = data.get(homeNumber).replaceAll("[^0-9]", "");

        return parent;
    }





    // METHODS

    public Parent createParent(ExcelUtils data, int applicationNo) {
        Parent parent = new Parent();
        parent.firstName = data.getCellData(1, applicationNo);
        parent.lastName = data.getCellData(2, applicationNo);
        parent.hdyhau = data.getCellData(3, applicationNo);
        parent.street = data.getCellData(4, applicationNo);
        parent.city = data.getCellData(5, applicationNo);
        parent.state = data.getCellData(6, applicationNo);
        parent.zipCode = Integer.parseInt(data.getCellData(7, applicationNo).substring(0, 5));
        parent.email = data.getCellData(8, applicationNo);
        parent.homeNumber = data.getCellData(9, applicationNo).replaceAll("[^0-9]", "");
        return parent;
    }
    
    
    public Parent createParent(String data[][]){
        Parent parent = new Parent();

        parent.firstName = data[1][getIdxApp(application)];
        parent.lastName = data[2][idxApplication];
        parent.hdyhau = data[3][idxApplication];
        parent.street = data[4][idxApplication];
        parent.city = data[5][idxApplication];
        parent.state = data[6][idxApplication];
        parent.zipCode = Integer.parseInt(data[7][idxApplication].substring(0, 5));
        parent.email = data[8][idxApplication];
        parent.homeNumber = data[9][idxApplication].replaceAll("[^0-9]", "");
        return parent;
    }


    public Parent createParent(List<Map<String,String>> wholeMap){
        Parent parent = new Parent();

        parent.firstName = wholeMap.get(0).get(application);
        parent.lastName = wholeMap.get(1).get(application);
        parent.hdyhau = wholeMap.get(2).get(application);
        parent.street = wholeMap.get(3).get(application);
        parent.city = wholeMap.get(4).get(application);
        parent.state = wholeMap.get(5).get(application);
        parent.zipCode = Integer.parseInt(wholeMap.get(6).get(application).substring(0, 5));
        parent.email = wholeMap.get(7).get(application);;
        parent.homeNumber = wholeMap.get(8).get(application).replaceAll("[^0-9]", "");

        return parent;
    }



    public int getIdxApp(String applicationName){
        return Integer.parseInt(applicationName.substring(applicationName.length() - 1));
    }

    public String parentFirstName = "parentFirstName";
    public String parentLastName = "parentLastName";
    public String hdyhau = "hdyhau";
    public String street = "street";
    public String city = "city";
    public String state = "state";
    public String zipcode = "zipcode";
    public String email = "email";
    public String homeNumber = "homeNumber";

}
