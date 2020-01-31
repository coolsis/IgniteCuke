package com.ignite.eif_pages;

import com.ignite.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Confirmation_Pg {

    public Confirmation_Pg() { PageFactory.initElements(Driver.getDriver(), this); }

    @FindBy(xpath = "//span[contains(text(),'Thank you')]")
    public WebElement confirmation_msg;

    @FindBy(xpath = "//p[@id='application-no-wrap']")
    public WebElement confirmation_no;

    @FindBy(id = "application-completed-text")
    public WebElement confirmation_status;


}
