package com.juaracoding.cucumber.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.juaracoding.cucumber.driver.DriverManager;
import com.juaracoding.cucumber.pages.CartPage;
import com.juaracoding.cucumber.pages.InventoryPage;
import com.juaracoding.cucumber.reports.ExtentTestManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartSteps {

    private final WebDriver driver = DriverManager.getDriver();
    private final InventoryPage inventoryPage = new InventoryPage(driver);
    private final CartPage cartPage = new CartPage(driver);

    @When("Pengguna memilih produk {string} untuk ditambahkan ke keranjang")
    public void penggunaMemilihProdukUntukDitambahkanKeKeranjang(String productName) {
        inventoryPage.addProductToCart(productName);
        ExtentTestManager.getTest().log(Status.INFO, "Memilih produk untuk ditambahkan ke keranjang: " + productName);
    }

    @When("Pengguna menambahkan produk {string} ke keranjang belanja")
    public void penggunaMenambahkanProdukKeKeranjangBelanja(String productName) {
        inventoryPage.addProductToCart(productName);
        ExtentTestManager.getTest().log(Status.INFO, "Menambahkan produk ke keranjang belanja: " + productName);
    }

    @Then("Ikon keranjang belanja menampilkan angka {string}")
    public void ikonKeranjangBelanjaMenampilkanAngka(String expectedCount) {
        String actualCount = inventoryPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount, "Jumlah badge keranjang belanja tidak sesuai");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi angka ikon keranjang belanja: " + actualCount);
    }

    @Then("Ikon keranjang belanja menampilkan jumlah {string}")
    public void ikonKeranjangBelanjaMenampilkanJumlah(String expectedCount) {
        String actualCount = inventoryPage.getCartItemCount();
        Assert.assertEquals(actualCount, expectedCount, "Jumlah badge keranjang belanja tidak sesuai");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi jumlah ikon keranjang belanja: " + actualCount);
    }

    @When("Pengguna membuka halaman keranjang belanja")
    public void penggunaMembukaHalamanKeranjangBelanja() {
        inventoryPage.openCart();
        ExtentTestManager.getTest().log(Status.INFO, "Membuka halaman keranjang belanja");
    }

    @Then("Produk {string} terdaftar di dalam daftar keranjang belanja")
    public void produkTerdaftarDiDalamDaftarKeranjangBelanja(String productName) {
        Assert.assertTrue(cartPage.isProductPresentInCart(productName),
                "Produk '" + productName + "' tidak terdaftar di dalam keranjang belanja");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi produk terdaftar di keranjang: " + productName);
    }

    @Then("Produk {string} tercantum di dalam keranjang belanja")
    public void produkTercantumDiDalamKeranjangBelanja(String productName) {
        Assert.assertTrue(cartPage.isProductPresentInCart(productName),
                "Produk '" + productName + "' tidak tercantum di dalam keranjang belanja");
        ExtentTestManager.getTest().log(Status.PASS, "Verifikasi produk tercantum di keranjang: " + productName);
    }
}

