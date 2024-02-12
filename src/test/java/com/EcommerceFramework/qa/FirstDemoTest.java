package com.EcommerceFramework.qa;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class FirstDemoTest {

	// class variables
	static WebDriver driver = null;

	// class methods
	public static void main(String[] args) {

		try {

			driver = new ChromeDriver();
			String productName = "ZARA COAT 3";

			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();

			driver.get("https://rahulshettyacademy.com/client");
			driver.findElement(By.id("userEmail")).sendKeys("ecommuser@gmail.com");
			driver.findElement(By.id("userPassword")).sendKeys("12qw!@QW");
			driver.findElement(By.id("login")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			
			List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
			WebElement prod =  products.stream().filter(product -> 
			product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			
			prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
			driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
			
			List<WebElement> carProducts = driver.findElements(By.cssSelector(".cartSection h3"));
			
			boolean found = carProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("ZARA COAT 3"));
			Assert.assertTrue(found);
			
			driver.findElement(By.cssSelector(".totalRow button")).click();
			driver.findElement(By.cssSelector(".details__user [placeholder='Select Country']")).sendKeys("India");
			
			List<WebElement> countries = driver.findElements(By.cssSelector(".user__address button"));
			WebElement selectedCountry = countries.stream().
					filter(country->country.getText().trim().equals("India")).findFirst().orElse(null);
			selectedCountry.click();
			
			driver.findElement(By.cssSelector(".action__submit")).click();
			String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
			Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
