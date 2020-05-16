package com.ignite.step_defs;

import com.ignite.pojos.Parent;
import com.ignite.pojos.Student;
import com.ignite.utilities.ExcelUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExcelReader {

    public String path = "test_resources/EIF-TestCases.xlsx";
    public String sheetName = "users";
    public String application = "application1";

    public Parent myParent;
    public Student myStudent;

    public ExcelUtils data = new ExcelUtils(path, sheetName);

    @Test
    public void test1_get_DATA () {
        myParent = createParentFromData(data);
        myStudent = createStudentFromData(data);

        System.out.println("\n==============  test GET DATA  =================="
                + myParent.toString()
                + myStudent.toString());
    }


    @Test
    public void test2_2D_Array(){
        application = "application2";

        String wholeTable[][] = data.getDataArray();

        myParent =  createParentFrom2DArray(wholeTable);
        myStudent = createStudentFrom2DArray(wholeTable);

        System.out.println("\n\n==============  test 2D ARRAY  =================="
                + myParent.toString()
                + myStudent.toString());
    }

    @Test
    public void test3_List_Map(){
        application = "application3";

        List<Map<String, String>> wholeMap = data.getDataList();

        // to print List items
        //wholeMap.get(0).forEach((key, value) -> System.out.println(key + ":" + value));

        myParent = createParentFromListMap(wholeMap);
        myStudent = createStudentFromListMap(wholeMap);

        System.out.println("\n==============  test LIST<MAP<String,String>>  =================="
                + myParent.toString()
                + myStudent.toString());
    }

    @Test
    public void test4_Map(){
        application = "application4";
        int idxApplication = getApplicationNumber()-1;

        List<Map<String, String>> wholeRowMap = data.getDataMapRow();

        // to print List items
        //wholeMap.get(0).forEach((key, value) -> System.out.println(key + ":" + value));

        myParent = createParentFromMap(wholeRowMap.get(idxApplication));
        myStudent = createStudentFromMap(wholeRowMap.get(idxApplication));

        System.out.println("\n==============  test MAP<String,String>  =================="
                + myParent.toString()
                + myStudent.toString());

    }


    // EXCEL READ METHODS

    // Create Parent

    public Parent createParentFrom2DArray(String[][] data) {
        Parent myParent = new Parent();
        int applicationColumn = Integer.parseInt(application.substring(application.length() - 1));

        myParent.firstName = data[1][applicationColumn];
        myParent.lastName = data[2][applicationColumn];
        myParent.hdyhau = data[3][applicationColumn];
        myParent.street = data[4][applicationColumn];
        myParent.city = data[5][applicationColumn];
        myParent.state = data[6][applicationColumn];
        myParent.zipCode = Integer.parseInt(data[7][applicationColumn].substring(0, 4));
        myParent.email = data[8][applicationColumn];
        myParent.homeNumber = data[9][applicationColumn].replaceAll("[^0-9]", "");
        return myParent;
    }


    public Parent createParentFromData(ExcelUtils data) {
        Parent myParent = new Parent();
        int idxApplication = getApplicationNumber();

        myParent.firstName = data.getCellData(1, idxApplication);
        myParent.lastName = data.getCellData(2, idxApplication);
        myParent.hdyhau = data.getCellData(3, idxApplication);
        myParent.street = data.getCellData(4, idxApplication);
        myParent.city = data.getCellData(5, idxApplication);
        myParent.state = data.getCellData(6, idxApplication);
        myParent.zipCode = Integer.parseInt(data.getCellData(7, idxApplication).substring(0, 4));
        myParent.email = data.getCellData(8, idxApplication);
        myParent.homeNumber = data.getCellData(9, idxApplication).replaceAll("[^0-9]", "");
        return myParent;
    }


    public Parent createParentFromListMap(List<Map<String, String>> data) {
        Parent myParent = new Parent();

        myParent.firstName = data.get(0).get(application);
        myParent.lastName = data.get(1).get(application);
        myParent.hdyhau = data.get(2).get(application);
        myParent.street = data.get(3).get(application);
        myParent.city = data.get(4).get(application);
        myParent.state = data.get(5).get(application);
        myParent.zipCode = Integer.parseInt(data.get(6).get(application).substring(0, 4));
        myParent.email = data.get(7).get(application);
        myParent.homeNumber = data.get(8).get(application).replaceAll("[^0-9]", "");
        return myParent;
    }

    private Parent createParentFromMap(Map<String, String> data) {
        Parent myParent = new Parent();

        myParent.firstName = data.get("parentFirstName");
        myParent.lastName = data.get("parentLastName");
        myParent.hdyhau = data.get("hdyhau");
        myParent.street = data.get("street");
        myParent.city = data.get("city");
        myParent.state = data.get("state");
        myParent.zipCode = Integer.parseInt(data.get("zipcode").substring(0, 4));
        myParent.email = data.get("email");
        myParent.homeNumber = data.get("homeNumber").replaceAll("[^0-9]", "");
        return myParent;

    }


    // Create Student

    public Student createStudentFromData(ExcelUtils data) {
        int applicationColumn = getApplicationNumber();

        Student myStudent = new Student();

        myStudent.parentRelation = data.getCellData(10, applicationColumn);
        myStudent.firstName = data.getCellData(11, applicationColumn);
        myStudent.lastName = data.getCellData(12, applicationColumn);
        myStudent.dateOfBird = data.getCellData(13, applicationColumn);
        myStudent.gender = data.getCellData(14, applicationColumn);
        myStudent.currentAcademicYear = Boolean.parseBoolean(data.getCellData(15, applicationColumn));
        myStudent.gradeLevel = data.getCellData(16, applicationColumn);
        return myStudent;
    }

    public Student createStudentFrom2DArray(String[][] data) {
        Student myStudent = new Student();

        myStudent.parentRelation = data[10][getApplicationNumber()];
        myStudent.firstName = data[11][getApplicationNumber()];
        myStudent.lastName = data[12][getApplicationNumber()];
        myStudent.dateOfBird = data[13][getApplicationNumber()];
        myStudent.gender = data[14][getApplicationNumber()];
        myStudent.currentAcademicYear = Boolean.parseBoolean(data[14][getApplicationNumber()]);
        myStudent.gradeLevel = data[15][getApplicationNumber()];
        return myStudent;
    }

    public Student createStudentFromListMap(List<Map<String, String>> data) {
        int applicationColumn = Integer.parseInt(application.substring(application.length() - 1));
        Student myStudent = new Student();

        myStudent.parentRelation = data.get(9).get(application);
        myStudent.firstName = data.get(10).get(application);
        myStudent.lastName = data.get(11).get(application);
        myStudent.dateOfBird = data.get(12).get(application);
        myStudent.gender = data.get(13).get(application);
        myStudent.currentAcademicYear = Boolean.parseBoolean(data.get(15).get(application));
        myStudent.gradeLevel = data.get(14).get(application);
        return myStudent;
    }

    private Student createStudentFromMap(Map<String, String> data) {

        Student myStudent = new Student();

        myStudent.parentRelation = data.get(parentRelation);
        myStudent.firstName = data.get(studentFirstName);
        myStudent.lastName = data.get(studentLastName);
        myStudent.dateOfBird = data.get(dateOfBirth);
        myStudent.gender = data.get(gender);
        myStudent.currentAcademicYear = Boolean.parseBoolean(data.get(isCurrentAcademicYear));
        myStudent.gradeLevel = data.get(gradeLevel);
        return myStudent;
    }


    // OTHER METHODS

    public int getApplicationNumber() {
        return Integer.parseInt(application.substring(application.length() - 1));
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
    public String parentRelation = "parentRelation";
    public String studentFirstName = "studentFirstName";
    public String studentLastName = "studentLastName";
    public String dateOfBirth = "dateOfBirth";
    public String gender = "gender";
    public String isCurrentAcademicYear = "isCurrentAcademicYear";
    public String gradeLevel = "gradeLevel";

}
