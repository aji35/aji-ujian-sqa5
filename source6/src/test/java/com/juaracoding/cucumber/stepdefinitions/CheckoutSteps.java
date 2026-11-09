package com.juaracoding.cucumber.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.juaracoding.cucumber.driver.DriverManager;
import com.juaracoding.cucumber.pages.CartPage;
import com.juaracoding.cucumber.pages.CheckoutPage;
import com.juaracoding.cucumber.reports.ExtentTestManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final CheckoutPage checkoutPage = new CheckoutPage(driver);
    private final CartPage cartPage = new CartPage(driver);

    @When("Pengguna mengklik tombol checkout")
    public void penggunaMengklikTombolCheckout() {
        cartPage.clickCheckoutButton();
        ExtentTestManager.getTest().log(Status.INFO, "Mengklik tombol checkout menuju halaman input identitas");
    }

    @When("Pengguna mengisi nama depan {string}")
    public void penggunaMengisiNamaDepan(String firstName) {
        checkoutPage.enterFirstName(firstName);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input nama depan: " + firstName);
    }

    @When("Pengguna mengisi nama belakang {string}")
    public void penggunaMengisiNamaBelakang(String lastName) {
        checkoutPage.enterLastName(lastName);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input nama belakang: " + lastName);
    }

    @When("Pengguna mengisi kode pos {string}")
    public void penggunaMengisiKodePos(String postalCode) {
        checkoutPage.enterPostalCode(postalCode);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input kode pos: " + postalCode);
    }

    @When("Pengguna mengosongkan kode pos")
    public void penggunaMengosongkanKodePos() {
        checkoutPage.clearPostalCode();
        ExtentTestManager.getTest().log(Status.INFO, "Mengosongkan kolom input kode pos (Postal Code)");
    }

    @When("Pengguna mengklik tombol Continue")
    public void penggunaMengklikTombolContinue() {
        checkoutPage.clickContinueButton();
        ExtentTestManager.getTest().log(Status.INFO, "Mengklik tombol Continue pada halaman Checkout");
    }

    @Then("Sistem menampilkan pesan kesalahan checkout {string}")
    public void sistemMenampilkanPesanKesalahanCheckout(String expectedErrorMessage) {
        String actualError = checkoutPage.getErrorMessageText();
        Assert.assertTrue(actualError.contains(expectedErrorMessage),
                "Pesan error aktual '" + actualError + "' tidak memuat '" + expectedErrorMessage + "'");
        ExtentTestManager.getTest().log(Status.PASS,
                "Verifikasi pesan error checkout berhasil: " + actualError);
    }

    @Then("Sistem menampilkan pesan kesalahan kode pos {string}")
    public void sistemMenampilkanPesanKesalahanKodePos(String expectedErrorMessage) {
        String actualError = checkoutPage.getErrorMessageText();
        Assert.assertTrue(actualError.contains(expectedErrorMessage),
                "Pesan error aktual '" + actualError + "' tidak memuat '" + expectedErrorMessage + "'");
        ExtentTestManager.getTest().log(Status.PASS,
                "Verifikasi pesan error kode pos berhasil: " + actualError);
    }
}
