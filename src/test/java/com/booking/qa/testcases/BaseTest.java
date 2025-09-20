package com.booking.qa.testcases;

import com.booking.qa.base.TestBase;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends TestBase {

    @BeforeSuite
    public void setUp() {
        initialization();
    }

    @AfterSuite
    public void tearDown() {
        quitDriver();
    }
}