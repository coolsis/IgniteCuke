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
        Driver.getDriver().get(ConfigReader.getKey("noIntro"));
    }

    @Then("user completes a happy path")
    public void user_fill_all_pages() {
        ppObj.fillParentForm(myParent);
        spObj.fillStudentForm(myStudent);
        sclpObj.selectSchool("Ignite 101");
        fpObj.finish_Btn.click();
        waitFor(2);

        Assert.assertTrue(cpObj.confirmation_no.isDisplayed());
    }
}
