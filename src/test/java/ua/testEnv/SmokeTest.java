package ua.testEnv;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class SmokeTest extends WebDriverSettings {
    //Constants have been added so the test code will be clear and understandable
    static final String WEBSITE_URL = "https://www.decathlon.ua/uk/";
    static final String PASSWORD_EXAMPLE = "Password1";
    static final String SELECTOR_HEADER_ICON_ACCOUNT = "#header > div > div > div:nth-child(1) > div.col-md-6.text-right.nav--secondary > div.btn.btn--account.js-btn--account.hidden-md-down";
    static final String SELECTOR_REGISTRATION_BUTTON = "#customerSigninModal > div > div > div.modal-body.js-block-log-out.text-center > div > a";
    static final String SELECTOR_CONFIRM_REGISTRATION_BUTTON = "#app > div > div.sign-in.m-sign-in.l-card-block > div:nth-child(5) > div.m-sign-in__signup > button";
    static final String SELECTOR_PASSWORD_SIGN_IN = "#input-password";
    static final String SELECTOR_REGISTRATION_CONF_BUTTON = "button[type=\"submit\"]";
    static final String SELECTOR_CUSTOMER_SIGN_IN_MODAL = "#customerSigninModal > div > div";
    static final String SELECTOR_EMAIL_SIGN_IN = "#input-email";
    static final String SELECTOR_PERSONAL_ACCOUNT = "module-oneshop_member-identity";


    @Test
    public void signUpTest() //Main test steps execution
    {
        // open website and click sign up button
        driver.get(WEBSITE_URL);
        driver.findElement(By.cssSelector(SELECTOR_HEADER_ICON_ACCOUNT)).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SELECTOR_CUSTOMER_SIGN_IN_MODAL)));
        var signUpUrl= driver.findElement(By.cssSelector(SELECTOR_REGISTRATION_BUTTON)).getAttribute("href");
        driver.navigate().to(signUpUrl);
       // generate random email and fill out sign up data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SELECTOR_EMAIL_SIGN_IN)));
        var randomEmail = generateRandomEmail();
        driver.findElement(By.cssSelector(SELECTOR_EMAIL_SIGN_IN)).sendKeys(randomEmail);
        driver.findElement(By.cssSelector(SELECTOR_CONFIRM_REGISTRATION_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SELECTOR_PASSWORD_SIGN_IN)));
        driver.findElement(By.cssSelector(SELECTOR_PASSWORD_SIGN_IN)).sendKeys(PASSWORD_EXAMPLE);
        driver.findElement(By.cssSelector(SELECTOR_REGISTRATION_CONF_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SELECTOR_PERSONAL_ACCOUNT)));

    }
   // Copied from stackoverflow
   // https://stackoverflow.com/questions/45841500/generate-random-emails
   // variable to generate random email automatically
   protected String generateRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        String randomEmail = saltStr +"@gmail.com";
        return randomEmail;
    }
}
