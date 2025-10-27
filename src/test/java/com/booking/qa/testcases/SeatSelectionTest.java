package com.booking.qa.testcases;

import com.booking.qa.pages.SeatSelectionPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeatSelectionTest extends BaseTest {
    private SeatSelectionPage seatSelectionPage;

    @BeforeMethod
    public void settingUp() {
        seatSelectionPage = new SeatSelectionPage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.ExtrasTest.goToNextPageTest")
    public void selectFirstAvailableSeatTest()
    {
        if ("seatSelection".equals(ExtrasTest.nextPageType)) {
            seatSelectionPage.selectFirstAvailableSeat();
            String verifySeatSelection = seatSelectionPage.fetchSeatSelection();
            Assert.assertEquals(verifySeatSelection, "Check and pay", "Seat selection failed");
        } else {
            System.out.println("Skipping seat selection test - seat selection page not present");
        }
    }
}
