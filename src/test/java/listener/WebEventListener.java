package listener;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.TestUtil;

public class WebEventListener extends AbstractWebDriverEventListener {
    Logger logger = LoggerFactory.getLogger(WebEventListener.class);

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        logger.info("Before navigating to URL: '" + url + "'");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.info("Navigated to URL: '" + url + "'");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        logger.info("trying to click on: " + element.toString());
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.info("Clicked on: " + element.toString());
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        logger.info("Navigating back to previous page");
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        logger.info("Actually navigated back to previous page");
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        logger.info("Navigating forward to next page");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        logger.info("Actually navigated back to next page");
    }

    @Override
    public void onException(Throwable error, WebDriver driver) {
        logger.info("Exception occurred: " + error.toString());
        TestUtil.takeScreenShot();
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Trying to find Element By: " + by.toString());
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("Found Element By: " + by.toString());
    }


}
