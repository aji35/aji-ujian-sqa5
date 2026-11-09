package com.juaracoding.cucumber.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * Pengelola Inisialisasi Laporan Interaktif ExtentReports v5.1.2.
 */
public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            String reportDir = System.getProperty("user.dir") + File.separator + "reports";
            File dir = new File(reportDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String reportPath = reportDir + File.separator + "Cucumber-ExtentReport-Day25.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("JuaraCoding SQA - Cucumber BDD & PageFactory Extent Report");
            spark.config().setReportName("Enterprise Cucumber BDD Automation Execution Dashboard");
            spark.config().setTimeStampFormat("EEEE, dd MMMM yyyy, HH:mm:ss (zzz)");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Application", "SauceDemo E-Commerce Portal");
            extent.setSystemInfo("Environment", "Staging / Live Web Sandbox");
            extent.setSystemInfo("JDK Version", System.getProperty("java.version"));
            extent.setSystemInfo("Framework", "Cucumber 7.20.1 + Selenium 4.26.0 + PageFactory + TestNG 7.10.2");
        }
        return extent;
    }
}

