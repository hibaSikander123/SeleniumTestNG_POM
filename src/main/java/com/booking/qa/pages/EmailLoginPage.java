package com.booking.qa.pages;
import com.booking.qa.base.TestBase;
import org.openqa.selenium.support.PageFactory;


    public class EmailLoginPage extends TestBase {

        public EmailLoginPage() {


            PageFactory.initElements(driver, this);
        }

        public String getCurrentURL() {


            return driver.getCurrentUrl();
        }

    }

