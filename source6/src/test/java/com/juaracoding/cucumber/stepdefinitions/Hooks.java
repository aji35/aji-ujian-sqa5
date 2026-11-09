package com.juaracoding.cucumber.stepdefinitions;

import com.aventstack.extentreports.Status;
import com.juaracoding.cucumber.driver.DriverManager;
import com.juaracoding.cucumber.reports.ExtentManager;
import com.juaracoding.cucumber.reports.ExtentTestManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Cucumber Hooks terintegrasi dengan ExtentReports v5.1.2.
 * Mengelola siklus hidup browser, telemetri laporan HTML interaktif,
 * serta penangkapan screenshot Base64 otomatis saat skenario mengalami kegagalan.
 */
public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("==================================================================");
        System.out.println("🚀 [CUCUMBER SCENARIO START] " + scenario.getName());
        System.out.println("==================================================================");

        // 1. Inisialisasi WebDriver
        DriverManager.initDriver();

        // 2. Inisialisasi node ExtentTest untuk skenario saat ini
        String tags = String.join(", ", scenario.getSourceTagNames());
        ExtentTestManager.startTest(scenario.getName(), "Tags: " + tags);

        // Pasangkan kategori tag pada laporan Extent
        for (String tag : scenario.getSourceTagNames()) {
            ExtentTestManager.getTest().assignCategory(tag.replace("@", ""));
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            if (scenario.isFailed()) {
                // Tangkap bukti screenshot Base64 untuk ExtentReports
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                byte[] byteScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                // Sematkan ke ExtentReports HTML
                ExtentTestManager.getTest().log(Status.FAIL, "❌ Skenario GAGAL: " + scenario.getName());
                ExtentTestManager.getTest().addScreenCaptureFromBase64String(base64Screenshot, "Bukti_Kegagalan_" + scenario.getName());

                // Sematkan juga ke laporan resmi bawaan Cucumber
                scenario.attach(byteScreenshot, "image/png", "Failed_Screenshot_" + scenario.getName());
                System.err.println("❌ [FAILED EVIDENCE] Screenshot Base64 berhasil disematkan ke ExtentReports & Cucumber.");
            } else {
                ExtentTestManager.getTest().log(Status.PASS, "✅ Skenario BERHASIL: " + scenario.getName());
                System.out.println("✅ [SCENARIO PASSED] " + scenario.getName());
            }

            // Tutup sesi browser per skenario untuk menjamin isolasi state pengujian
            DriverManager.quitDriver();
        }
        System.out.println("🏁 [CUCUMBER SCENARIO FINISH] " + scenario.getName());
    }

    @AfterAll
    public static void flushReports() {
        System.out.println("==================================================================");
        System.out.println("📊 [FLUSHING EXTENT REPORT] Menyimpan laporan ke direktori reports/ ...");
        System.out.println("==================================================================");
        if (ExtentManager.getInstance() != null) {
            ExtentManager.getInstance().flush();
        }
    }
}

