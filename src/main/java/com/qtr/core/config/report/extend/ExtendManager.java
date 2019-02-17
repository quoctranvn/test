package com.qtr.core.config.report.extend;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtendManager {

    private static ExtentReports extentReport;
    private static ExtendManager extendManager;

    public static synchronized ExtendManager instance() {
        if (extendManager == null)
            extendManager = new ExtendManager();
        return extendManager;
    }

    public void getReporter() {

    }

    public static ExtentReports createReporter(String reportPath) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(reportPath);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(reportPath);

        extentReport = new ExtentReports();
        extentReport.attachReporter(htmlReporter);
        extentReport.setAnalysisStrategy(AnalysisStrategy.SUITE);
        extentReport.setReportUsesManualConfiguration(true);
        return extentReport;
    }
}
