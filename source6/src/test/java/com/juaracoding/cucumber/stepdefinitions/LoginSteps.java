package com.juaracoding.cucumber.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.juaracoding.cucumber.driver.DriverManager;
import com.juaracoding.cucumber.pages.InventoryPage;
import com.juaracoding.cucumber.pages.LoginPage;
import com.juaracoding.cucumber.reports.ExtentTestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final LoginPage loginPage = new LoginPage(driver);
    private final InventoryPage inventoryPage = new InventoryPage(driver);

    @Given("Pengguna membuka portal login {string}")
    public void penggunaMembukaPortalLogin(String url) {
        loginPage.navigateToLoginPage(url);
        ExtentTestManager.getTest().log(Status.INFO, "Membuka halaman portal login: " + url);
    }

    @When("Pengguna mengisi username {string}")
    public void penggunaMengisiUsername(String username) {
        loginPage.enterUsername(username);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input username: " + username);
    }

    @When("Pengguna mengisi password {string}")
    public void penggunaMengisiPassword(String password) {
        loginPage.enterPassword(password);
        ExtentTestManager.getTest().log(Status.INFO, "Mengisi input password: [PROTECTED]");
    }

    @When("Pengguna mengklik tombol Login")
    public void penggunaMengklikTombolLogin() {
        loginPage.clickLoginButton();
        ExtentTestManager.getTest().log(Status.INFO, "Mengklik tombol Login (PageFactory)");
    }

    @Then("Pengguna berhasil diarahkan ke halaman katalog dengan judul {string}")
    public void penggunaBerhasilDiarahkanKeHalamanKatalogDenganJudul(String expectedTitle) {
        Assert.assertTrue(inventoryPage.isPageLoaded(), "Halaman katalog produk gagal dimuat");
        Assert.assertEquals(inventoryPage.getPageTitleText(), expectedTitle, "Judul halaman katalog tidak sesuai");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi judul halaman katalog: " + expectedTitle);
    }

    @Then("Sistem menampilkan pesan kesalahan login {string}")
    public void sistemMenampilkanPesanKesalahanLogin(String expectedErrorMessage) {
        String actualError = loginPage.getErrorMessageText();
        Assert.assertTrue(actualError.contains(expectedErrorMessage),
                "Pesan error aktual '" + actualError + "' tidak memuat '" + expectedErrorMessage + "'");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi pesan error login berhasil: " + actualError);
    }
}

