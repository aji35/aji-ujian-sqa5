package com.juaracoding.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object Model dengan Selenium PageFactory untuk Halaman Keranjang Belanja (Cart).
 */
public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> cartItemNames;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isProductPresentInCart(String productName) {
        wait.until(ExpectedConditions.visibilityOf(checkoutButton));
        for (WebElement item : cartItemNames) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clickCheckoutButton() {
        if (!driver.getCurrentUrl().contains("cart.html")) {
            driver.get("https://www.saucedemo.com/cart.html");
        }
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        btn.click();
    }
}
