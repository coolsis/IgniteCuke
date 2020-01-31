package com.ignite.eif_pages;

import com.ignite.utilities.BrowserUtils;
import com.ignite.utilities.ConfigReader;
import com.ignite.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Introduction_Pg {

    public Introduction_Pg() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy( id= "acceptTerms")
    public WebElement acceptButton;

    @FindBy(linkText = "Next")
    public WebElement next_Btn;

    @FindBy(linkText = "Previous")
    public WebElement previous_Btn;


    public void clickAccept() {
        BrowserUtils.waitForClickablility(acceptButton, 10);
        acceptButton.click();
    }

    public void byPassIntroPage (){
        Driver.getDriver().get(ConfigReader.getKey("noIntro"));
    }

    public WebElement pageVerify(String pageName) {
        String name= "(//span[.='"+pageName +"'])[1]" ;
        WebElement page = Driver.getDriver().findElement(By.xpath(name));
        return page;

    }



}



