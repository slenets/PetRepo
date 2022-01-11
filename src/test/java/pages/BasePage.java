package pages;
import listener.WebEventListener;
import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected static WebDriver driver;
    public String browser;
    public String baseUrl;
    public Properties properties;
    EventFiringWebDriver eventFiringWebDriver;
    WebDriverWait wait;

    // 1) Load properties file
    private void loadProperties(){
        FileInputStream fis = null;
        try{
            properties = new Properties();
            fis = new FileInputStream("C:\\Qa29\\CarRental_POM\\src\\test\\java\\configuration\\config.properties");
            properties.load(fis);

            browser = System.getProperty("browser", properties.getProperty("browser"));//properties.getProperty("browser");
            baseUrl = properties.getProperty("baseUrl");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            //log these type of errors
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 2) initialize WebDriver and WebDriverWait
    private void openBrowser(){

        if(browser.equals("chrome")){
            driver = new ChromeDriver();
        }
        if(browser.equals("firefox")){
            driver = new FirefoxDriver();
        }

        eventFiringWebDriver = new EventFiringWebDriver(driver);
        WebEventListener listener = new WebEventListener();
        eventFiringWebDriver.register(listener);
        driver = eventFiringWebDriver;

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    // 3) close WebDriver
    public final void closeBrowser(){
        //driver.quit();
    }
    // 4) Open website home page
    public final boolean goToHomePage(){
        try{
            loadProperties();
            openBrowser();
            driver.navigate().to(baseUrl);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
        }catch (Exception e){
            System.out.println("Unable to navigate to home page");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // ============== Helper methods =====================

    public void click(By locator){
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    public WebElement wait(By locator){
       return new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(getElement(locator)));
    }

    public WebElement waitToBeClickable(By locator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return el;
    }

    Logger logger = LoggerFactory.getLogger(BasePage.class);

    By yallaBtn = By.cssSelector("button[type='submit']");

    public boolean isYallaBtnPresent(){
        return getElement(yallaBtn).isDisplayed();
    }

    public void clickYallaButton(){
        click(yallaBtn);
    }

    public void typeSend(By locator, String key){
        if(key != null){
            getElement(locator).click();
            getElement(locator).clear();
            getElement(locator).sendKeys(key);
        }
    }

//    public void typeSend(WebElement element, String key) {
//        if (key != null) {
//            element.click();
//            element.clear();
//            element.sendKeys(key);
//        }
//    }

    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementDisplayed(WebElement element){
        try{
            return element.isDisplayed();
        }catch (NoSuchElementException e){
            e.getMessage();
        }
        return false;
    }

}


