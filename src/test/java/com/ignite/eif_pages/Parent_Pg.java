package com.ignite.eif_pages;

import com.ignite.pojos.Parent;
import com.ignite.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.ignite.utilities.BrowserUtils.selectFromDropdown;

public class Parent_Pg {

    public Parent_Pg() { PageFactory.initElements(Driver.getDriver(), this); }


    @FindBy(name = "pfirstname")
    public WebElement parentFirstName;

    @FindBy(id = "pmiddlename")
    public WebElement parentMidName;

    @FindBy(id = "plastname")
    public WebElement parentLastName;

    @FindBy(id = "pHowDidYouHearUs")
    public WebElement howDidYouHear;

    @FindBy(id = "pemail")
    public WebElement email;

    @FindBy(id = "phomephone")
    public WebElement homePhone;

    @FindBy(id = "phomephone")
    public WebElement cellPhone;

    @FindBy(id = "pworkphone")
    public WebElement workPhone;

    @FindBy(id = "pstreet")
    public WebElement pStreet;

    @FindBy(id = "pcity")
    public WebElement pCity;

    @FindBy(id = "pstate")
    public WebElement pState;

    @FindBy(id = "pzipcode")
    public WebElement pZip;

    @FindBy(css = "[data-langkey='pemailMsg']")
    public WebElement pEmailMsg;

    @FindBy(linkText = "Next")
    public WebElement next_Btn;

    @FindBy(linkText = "Previous")
    public WebElement previous_Btn;


    public void fillParentForm(Parent parent) {
        System.out.println(parent.toString());

        parentFirstName.sendKeys(parent.firstName);
        parentLastName.sendKeys(parent.lastName);

        selectFromDropdown(howDidYouHear, parent.hdyhau);

        homePhone.sendKeys(parent.homeNumber);
        email.sendKeys(parent.email);
        pStreet.sendKeys(parent.street);
        pCity.sendKeys(parent.city);

        selectFromDropdown(pState, parent.state);

        String zipCode = Integer.toString(parent.zipCode);
        pZip.sendKeys(zipCode);
        next_Btn.click();
    }


}
