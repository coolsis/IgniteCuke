package com.ignite.eif_pages;

import com.ignite.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Schools_Pg {

    public Schools_Pg() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    // radio buttons
    @FindBy(id = "school_1_2")
    public WebElement radio_button1;

    @FindBy(id = "school_1_3")
    public WebElement radio_button2;

    @FindBy(id = "school_1_4")
    public WebElement radio_button3;

    @FindBy(id = "school_1_5")
    public WebElement radio_button4;

    @FindBy(id = "school_1_6")
    public WebElement radio_button5;

    @FindBy(id = "school_1_7")
    public WebElement radio_button6;

    @FindBy(id = "school_1_8")
    public WebElement radio_button7;

    @FindBy(id = "studentageinfo1")
    public WebElement student_age;

    @FindBy(linkText = "Next")
    public WebElement next_Btn;

    @FindBy(linkText = "Previous")
    public WebElement previous_Btn;


    public WebElement selectSchool(int nth){
        return Driver.getDriver().findElement(By.id("school_1_"+(nth+1)));
    }


    public WebElement schoolName(String schoolName) {
        return Driver.getDriver().findElement(By.xpath("//label[text()='" + schoolName + "']"));
    }


    //  =========  METHODS  =========


    public int getAge() {
        return Integer.parseInt(student_age.getText().split(" ")[0]);
    }

    public boolean isAgeBiggerThanZero() {
        return getAge() > 0;
    }


    public void selectSchool(String s) {
        schoolName(s).click();
        next_Btn.click();
    }
}
