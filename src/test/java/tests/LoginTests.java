package tests;
import dto.Credentials;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomeSearchPage;
import pages.LoginPage;

public class LoginTests extends TestBase{

    @Test
    public void loginValidData(){
        homeSearch.loginPage();
        LoginPage p = homeSearch.loginPage();
        p.fillSubmitLoginForm(login);
        Assert.assertTrue(homeSearch.isDeletePresent());
    }
}
