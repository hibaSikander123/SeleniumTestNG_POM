package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ExtrasPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locatorFlexTcktBtn = By.xpath("//input[@value='flexible']/parent::div//span[contains(@class , 'InputRadio-module__field')]");
    private By locatorTravelProtectionBtn = By.xpath("//input[@value='insurance']/parent::div//span[contains(@class , 'InputRadio-module__field')]");
    private By locatorNextBtn2 = By.xpath("//div[@data-testid = \"checkout_extras_inner_next\"]/button");

    @FindBy(xpath = "//div[@data-testid='upb_title-extra-travelinsurance']")
    WebElement travelProtectionBtn;
    @FindBy(xpath = "//div[@data-testid='upb_title-extra-flexibleticket']")
    WebElement flexTicket;
    @FindBy(xpath = "//h2[contains(@class, 'Text-module__root--variant-headline') and contains(., 'Select your seat')]")
    WebElement seatSelectionStep;

    // Initializing page factory / page object
    public ExtrasPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Select Ticket Type as Flexible Ticket
    public String selectTicketType(){
        List<WebElement> flexTickteBtn = driver.findElements(locatorFlexTcktBtn);
        if (!flexTickteBtn.isEmpty()){
            flexTickteBtn.get(0).click();
            return "FlexTicketAdded";
        } else  {
            return "FlexTicketnotAdded";
        }
    }

    public String fetchFlexTcktText() {
        return flexTicket.getText();
    }

    // Avail the Travel Protection Insurance
    public String selectTravelProtection(){
        List<WebElement> travelInsBtn = driver.findElements(locatorTravelProtectionBtn);
        if (!travelInsBtn.isEmpty()) {
            travelInsBtn.get(0).click();
            return "TravelProtectionAdded";
        } else  {
            return "TravelProtectionNotAdded";
        }

    }

    public String fetchTravelPRocText() {
        return travelProtectionBtn.getText();
    }

    // Click next button to proceed to the seat selection page
    public void goToSeatSelectionPage(){
        WebElement nextBtn2 = wait.until(ExpectedConditions.elementToBeClickable(locatorNextBtn2));
        nextBtn2.click();
    }

    public String fetchSeatSelectionText() {
        return seatSelectionStep.getText();
    }
}
