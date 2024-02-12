package com.EcommerceFramework.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	
	public static ExtentReports getReportObj() {
		
		File filePath = new File(System.getProperty("user.dir")
				+ "\\ExtentReports\\report.html");
		ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
		
		 ExtentReports extent = new ExtentReports();
		 extent.attachReporter(spark);
		 
		 spark.config().setReportName("Ecommerce test results");
		 spark.config().setDocumentTitle("Ecommerce Extent TEst report");
		 return extent;
		
	}

}
