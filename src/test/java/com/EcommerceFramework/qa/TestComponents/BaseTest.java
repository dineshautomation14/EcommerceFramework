package com.EcommerceFramework.qa.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.EcommerceFramework.qa.page.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() {

		Properties prop = new Properties();
		FileInputStream fis = null;
		String browserName;
		try {
			fis = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\EcommerceFramework\\resources\\global.properties");
			prop.load(fis);
			browserName = System.getProperty("browser")!=null
					?System.getProperty("browser"):prop.getProperty("browser");
			
			if (browserName.equals("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equals("edge")) {
				driver = new EdgeDriver();
			} else if (browserName.equals("firefox")) {
				// to do
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult iTestResult) {

		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String pathName) throws IOException {

		String jsonContent = FileUtils.readFileToString(new File(pathName), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> list = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
		});
		return list;
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destFile = new File(System.getProperty("user.dir") + "\\ExtentReports\\screenshot\\" + testCaseName + "01.png");
		try {
			FileUtils.copyFile(scrFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getPath();
	}
}
