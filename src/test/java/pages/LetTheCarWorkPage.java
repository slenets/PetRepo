package pages;

import dto.Car;
import kotlin.NoWhenBranchMatchedException;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class LetTheCarWorkPage extends BasePage {
    public static String errorMessage;

    By addressField = By.id("pickUpPlace");
    By makeField = By.id("make");
    By modelField = By.id("model");
    By yearField = By.id("year");
    By engineField = By.id("engine");
    By fuelField = By.id("fuel");
    By gearField = By.id("gear");
    By wdField = By.id("wheelsDrive");
    By doorsField = By.id("doors");
    By seatsField = By.id("seats");
    By carClassField = By.id("class");
    By fuelConsumptionField = By.id("fuelConsumption");
    By carRegistrationNumber = By.id("serialNumber");
    By priceField = By.id("price");
    By distanceIncludedField = By.id("distance");
    By featuresField = By.className("feature-input");
    By addFeatureButton = By.cssSelector("button[type='button']");
    By aboutField = By.id("about");
    By addPhotosField = By.id("photos");
    By submit = By.cssSelector("button[type='submit']");

    By pacItem = By.cssSelector(".pac-item");
    By carAddedMessage = By.cssSelector("h1.title");


    public void addCarRequiredFields(Car car) {
        typeAddress(addressField, car.getAddress());
        typeSend(makeField, car.getMake());
        typeSend(modelField, car.getModel());
        typeSend(yearField, car.getYear());
        typeSend(engineField, car.getEngine());
        //select tags
        select(fuelField, car.getFuel());
        select(gearField, car.getGear());
        select(wdField, car.getWheelsDrive());
        //end select tags
        typeSend(doorsField, car.getDoors());
        typeSend(seatsField, car.getSeats());
        typeSend(carClassField, car.getCarClass());
        typeSend(carClassField, car.getCarClass());
        typeSend(fuelConsumptionField, car.getFuelConsumption());
        typeSend(carRegistrationNumber, car.getCarRegistrationNumber());
        typeSend(priceField, car.getPrice());
        typeSend(distanceIncludedField, car.getDistanceIncluded());
        //attach photo
        attachPhoto(addPhotosField, car.getPhotoPath());

    }

    public LetTheCarWorkPage addCarSubmit() {
        submit();
        return this;
    }

    public void addCarFailEmptyField(Car car) {
        addCarRequiredFields(car);
    }

    public boolean isFieldErrorDisplayed() {
        WebElement element = driver.findElement(By.xpath(String.format("//div[contains(text(),%s)]", errorMessage)));
        return isElementDisplayed(element);
    }

    public boolean isSubmitButtonActive() {
        return getElement(submit).isEnabled();
    }

    public boolean isCarAddedMessageDisplayed() {
        return isElementPresent(carAddedMessage);
    }

    private void typeAddress(By addressField, String address) {
        typeSend(addressField, address);
        if (isElementPresent(pacItem)) {
            click(pacItem);
            logger.info("element " + pacItem + " is present");
        }
    }

    public void select(By locator, String option) {
        if(option == null || option.length() == 0){
            click(locator);
            click(locator);
        }else {
            Select select = new Select(getElement(locator));
            select.selectByVisibleText(option);
        }

    }

    public void attachPhoto(By locator, String photo) {
        try{
            getElement(locator).sendKeys(photo);
        }catch (InvalidArgumentException e){
            e.printStackTrace();
        }

    }

    public void submit() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(submit));
        System.out.println(el.isEnabled());
        el.click();


        //waitToBeClickable(submit).click();
    }


}
