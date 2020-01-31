package com.ignite.eif_pages;

import com.ignite.pojos.Student;
import com.ignite.utilities.BrowserUtils;
import com.ignite.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static com.ignite.utilities.BrowserUtils.selectFromDropdown;
import static com.ignite.utilities.BrowserUtils.waitFor;

public class Student_Pg {

    @FindBy(id = "parentRelation_1")
    public WebElement parentRelation;

    @FindBy(id = "firstname_1")
    public WebElement firstName;

    @FindBy(id = "lastname_1")
    public WebElement lastName;

    @FindBy(id = "dateofbirth_1")
    public WebElement dateOfBirth;

    @FindBy(id = "gender_1")
    public WebElement genderSelect;

    @FindBy(id = "lbl_acadYear_1_1")
    public WebElement currentAcademicYear;

    @FindBy(id = "lbl_acadYear_1_2")
    public WebElement nextAcademicYear;

    @FindBy(id = "gradeLevel_1")
    public WebElement gradeLevel;


    @FindBy(linkText = "Next")
    public WebElement next_Btn;

    @FindBy(linkText = "Previous")
    public WebElement previous_Btn;

    public Student_Pg() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    public void fillStudentForm(Student myStudent) {

        System.out.println(myStudent.toString());
        waitFor(2);

        selectFromDropdown(parentRelation, myStudent.parentRelation);

        firstName.sendKeys(myStudent.firstName);
        lastName.sendKeys(myStudent.lastName);
        dateOfBirth.sendKeys(myStudent.dateOfBird);
        lastName.click();
        selectFromDropdown(genderSelect, myStudent.gender);

        if (myStudent.currentAcademicYear) {
            currentAcademicYear.click();
        } else {
            nextAcademicYear.click();
        }

        selectFromDropdown(gradeLevel, myStudent.gradeLevel);
        next_Btn.click();
    }

}
