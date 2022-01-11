package tests;

import dto.Car;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestUtil;

public class LetTheCarWorkTest extends TestBase {

    @BeforeMethod
    public void login() {
        if (!homeSearch.isLogoutPresent()) {
            System.out.println(homeSearch.isLogoutPresent());
            homeSearch.loginPage().fillSubmitLoginForm(login);

        }

    }

    // hello
    @Test
    public void addCarRequiredFieldsValid() throws InterruptedException {
        //Thread.sleep(1000);
        homeSearch.carWordPage().addCarRequiredFields(car);
        boolean result = carWorkPage.addCarSubmit().isCarAddedMessageDisplayed();
        Assert.assertTrue(result);
    }

    @Test(dataProvider = "getTestingData", dataProviderClass = TestUtil.class)
    public void unsuccessfulAddCarEmptyField (Car car) throws InterruptedException {
        Thread.sleep(2000);
        homeSearch.carWordPage();
        carWorkPage.addCarFailEmptyField(car);
        carWorkPage.isFieldErrorDisplayed();
        driver.navigate().refresh();

    }
}
