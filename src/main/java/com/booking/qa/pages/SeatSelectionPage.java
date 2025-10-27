package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SeatSelectionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locatorCheckSeat  = By.xpath("//div[@class = \"Frame-module__flex-direction_column___ms2of\"]//button[1]");
    private By locatorAllSeats  = By.xpath("//button[@class = \"Actionable-module__root___Gwqe- SeatSelector-module__wrapper___xQ1Wy\"]//button[1]");
    private By locatorCheckoutNextBtn2 = By.xpath("//div[@data-testid = \"checkout_extras_inner_next\"]/button");
    private By locatorSeatSelected = By.xpath("//h2[contains(@class, 'Text-module__root--variant-headline_2___kgmLC Text-module__root--color-neutral___M43S9 Title-module__title') and contains(., 'Check and pay')]\n");

    // Initializing page factory / page object
    public SeatSelectionPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void selectFirstAvailableSeat() {
        WebElement checkSeat = wait.until(ExpectedConditions.elementToBeClickable(locatorCheckSeat));
        checkSeat.click();
        System.out.println("checkSeat clicked");

        List<WebElement> allSeats = driver.findElements(locatorAllSeats);

        for (WebElement seat : allSeats) {
            if (seat.isEnabled()) {
                String seatNumber = seat.getAttribute("data-seat");
                System.out.println("The available seat is : " + seatNumber);
                seat.click();
                System.out.println("seat clicked");
                driver.findElement(locatorCheckoutNextBtn2).click();
                System.out.println("checkpoutNextBtn2 clicked");
                break;
            }
        }
    }

    public String fetchSeatSelection()
    {
        WebElement seatSelected = wait.until(ExpectedConditions.visibilityOfElementLocated(locatorSeatSelected));
        return seatSelected.getText();
    }
}
