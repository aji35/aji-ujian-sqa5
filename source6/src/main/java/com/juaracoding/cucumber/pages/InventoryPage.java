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
 * Page Object Model dengan Selenium PageFactory untuk Halaman Katalog Produk.
 */
public class InventoryPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        try {
            wait.until(ExpectedConditions.visibilityOf(pageTitle));
            return pageTitle.getText().equalsIgnoreCase("Products");
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitleText() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public void addProductToCart(String productName) {
        // Dinamis XPath untuk mencari tombol Add to Cart pada kartu produk spesifik
        String dynamicButtonXPath = String.format(
                "//div[@class='inventory_item'][.//div[@data-test='inventory-item-name' and text()='%s']]//button",
                productName
        );
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dynamicButtonXPath)));
        btn.click();
    }

    public String getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void openCart() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
        } catch (Exception ignored) {
        }
        if (!driver.getCurrentUrl().contains("cart.html")) {
            driver.get("https://www.saucedemo.com/cart.html");
        }
    }
}
