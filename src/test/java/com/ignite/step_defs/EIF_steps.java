package com.ignite.step_defs;

import com.github.javafaker.Faker;
import com.ignite.eif_pages.*;
import com.ignite.pojos.Parent;
import com.ignite.pojos.Student;
import com.ignite.utilities.ConfigReader;
import com.ignite.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;

import static com.ignite.utilities.BrowserUtils.waitFor;

public class EIF_steps {
    Confirmation_Pg cpObj = new Confirmation_Pg();
    Parent_Pg ppObj = new Parent_Pg();
    Student_Pg spObj = new Student_Pg();
    Parent myParent = new Parent();
    Student myStudent = new Student();
    Schools_Pg sclpObj = new Schools_Pg();
    Finish_Pg fpObj = new Finish_Pg();


    @Given("user get EnrollmentInquiryForm page")
    public void user_get_EnrollmentInquiryForm_page() {
        Driver.getDriver().get(ConfigReader.getKey("url"));
    }

    @Then("user completes a happy path")
    public void user_fill_all_pages() {
        ppObj.fillParentForm(myParent);
        spObj.fillStudentForm(myStudent);
        sclpObj.selectSchool("Ignite 101");
        fpObj.finish_Btn.click();
        waitFor(2);

        Assert.assertTrue(cpObj.confirmation_no.isDisplayed());

        String confirmationCode = cpObj.confirmation_no.getText();
    }



    @Given("User is in the {string} page")
    public void user_is_in_the_page(String page) {
        Driver.getDriver().get(ConfigReader.getKey("noIntro"));
        navigateTo(page);
    }

    @Given("user select the {string} school")
    public void user_select_the(String school) {
        sclpObj.selectSchool(school);
        sclpObj.next_Btn.click();
    }

    @Given("user select the <order> school")
    public void userSelectTheOrderSchool(int order) {
        sclpObj.selectSchool(order).click();
        sclpObj.next_Btn.click();
    }

    @Given("user select the {int} school")
    public void user_select_the_school(Integer order) {
        sclpObj.selectSchool(order).click();
        sclpObj.next_Btn.click();
    }

    @Then("user completes the registration")
    public void userCompletesTheRegistration() {
        fpObj.finish_Btn.click();
    }

    private void navigateTo(String page) {
        switch (page){
            case "student":
                ppObj.fillParentForm(myParent);
                break;
            case "map":
                ppObj.fillParentForm(myParent);
                spObj.fillStudentForm(myStudent);
                waitFor(2);
                break;
            case "finish":
                ppObj.fillParentForm(myParent);
                spObj.fillStudentForm(myStudent);
                sclpObj.selectSchool(1);
                break;
            default:
                // default page is parent page
        }
    }
}
