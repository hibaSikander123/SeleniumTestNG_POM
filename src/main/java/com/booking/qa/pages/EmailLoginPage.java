package com.booking.qa.pages;
import com.booking.qa.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


    public class EmailLoginPage {
        private WebDriver driver;

        public EmailLoginPage(WebDriver driver) {
            this.driver = driver;


            PageFactory.initElements(driver, this);
        }

        public String getCurrentURL() {


            return driver.getCurrentUrl();
        }

    }

