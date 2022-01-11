package tests;

import dto.Car;
import dto.Credentials;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import pages.BasePage;
import pages.HomeSearchPage;
import pages.LetTheCarWorkPage;

public class TestBase extends BasePage{
    int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

    Credentials login = Credentials.builder().email("slavka.lenetz@gmail.com").password("Ilcarro123").build();

    Car car = Car.builder()
            .address("Tel Aviv, Israel")
            .make("Audi")
            .model("A3")
            .year("2017")
            .engine("2.0")
            .fuel("Petrol")
            .gear("MT")
            .wheelsDrive("FWD")
            .doors("3")
            .seats("4")
            .carClass("C")
            .fuelConsumption("10")
            .carRegistrationNumber("52-"+i+"52")
            .price("30")
            .distanceIncluded("500")
            .photoPath("C:\\Qa29\\CarRental_POM\\DGT_110592_AudiNewSite_c11-1.jpg")
            .build();

    LetTheCarWorkPage carWorkPage;
    HomeSearchPage homeSearch;

    @BeforeSuite
    public void setUp(){
        Assert.assertTrue(goToHomePage(), "An error occurred while navigating to the homepage");

        homeSearch  = new HomeSearchPage();
        carWorkPage  = new LetTheCarWorkPage();

    }

    @AfterSuite
    public void tearDown(){
        closeBrowser();
    }
}
