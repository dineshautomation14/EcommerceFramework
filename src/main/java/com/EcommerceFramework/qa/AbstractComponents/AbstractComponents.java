package com.EcommerceFramework.qa.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.EcommerceFramework.qa.page.OrderPage;

public class AbstractComponents {
	public WebDriver driver;
	
	@FindBy (css="[routerlink*='myorders']")
	WebElement orderHeaderLnk;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementInVisibility(WebElement ele) {

		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		//wait.until(ExpectedConditions.invisibilityOf(ele));


		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.pollingEvery(Duration.ofMillis(300))
				.withTimeout(Duration.ofSeconds(3))
				.ignoring(ElementClickInterceptedException.class, NoSuchElementException.class);
		try {
			 fluentWait.until(ExpectedConditions.invisibilityOf(ele));
		}
		catch (Exception e) {
			System.out.println("******************************************************");
			e.printStackTrace();
			System.out.println("******************************************************");
		}
	

	}

	public WebElement waitForElementVisibility(WebElement ele) {

		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.pollingEvery(Duration.ofMillis(500))
				.withTimeout(Duration.ofSeconds(4))
				.ignoring(ElementClickInterceptedException.class, NoSuchElementException.class);
		try {
			 fluentWait.until(ExpectedConditions.visibilityOf(ele));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//wait.until();
		return ele;

	}
	
	public WebElement waitForElementClickable(WebElement ele) {

		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.pollingEvery(Duration.ofSeconds(2))
				.withTimeout(Duration.ofSeconds(10))
				.ignoring(ElementClickInterceptedException.class, StaleElementReferenceException.class);
		try {
			 ele = fluentWait.until(ExpectedConditions.elementToBeClickable(ele));
			 System.err.println("inside waitForElementClickable");
		}
		catch (ElementClickInterceptedException e) {
			System.err.println("******************************************************");
			e.printStackTrace();
			System.err.println("******************************************************");
		}catch (TimeoutException e) {
			System.err.println("******************************************************");
			e.printStackTrace();
			System.err.println("******************************************************");
		}
		//wait.until();
		return ele;

	}
	public OrderPage goToOrderPage() {
		waitForElementVisibility(orderHeaderLnk);
		doClick(orderHeaderLnk);
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}

	public void doClick(WebElement ele) {
		waitForElementClickable(ele).click();

	}

	public void doSendKeys(WebElement ele, String text) {
		waitForElementVisibility(ele);
		ele.sendKeys(text);

	}
}
