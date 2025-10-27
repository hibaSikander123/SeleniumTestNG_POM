package com.booking.qa.testcases;

import com.booking.qa.pages.SelectFlightPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectFlightTest extends BaseTest{
    private SelectFlightPage selectFlightPage;

    @BeforeMethod
    public void settingUp() {
        selectFlightPage = new SelectFlightPage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.FlightsTabTest.searchFlightTest")
    public void filterByCheapestTest () {
        selectFlightPage.filterByCheapest();
        String verifyCheapestFlightTag = selectFlightPage.fetchCheapestFlightTag();
        Assert.assertEquals(verifyCheapestFlightTag, "Cheapest", "Unable to Display cheapest flight");
    }

    @Test(dependsOnMethods = "filterByCheapestTest")
    public void selectCheapestFlightTest () {
        selectFlightPage.selectCheapestFlight();
        String verifyCheapestTicketType = selectFlightPage.fetchCheapestTicketType();
        Assert.assertEquals(verifyCheapestTicketType, "Berlin to Paris", "Unable to select cheapest flight");
    }

    @Test(dependsOnMethods = "selectCheapestFlightTest")
    public void selectTicketTypeTest () {
        selectFlightPage.selectTicketType();
        String travellerDetailPage = selectFlightPage.fetchFlexTcktConfirm();
        Assert.assertEquals(travellerDetailPage, "Flexible ticket", "Unable to select Flexible Ticket");
    }
}
