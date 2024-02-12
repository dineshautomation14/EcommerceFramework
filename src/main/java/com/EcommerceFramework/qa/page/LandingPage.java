package com.EcommerceFramework.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.EcommerceFramework.qa.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	
	private WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	//Locators
	@FindBy(id=("userEmail"))
	WebElement userEmail;
	@FindBy(id=("userPassword"))
	WebElement password;
	@FindBy(id=("login"))
	WebElement loginBtn;
	
	@FindBy (css ="[class*='spinner-overlay']")	WebElement loadingSpinner;
	@FindBy (css = "[class*='trigger-flyInOut']")	WebElement loginError;
	
	//Landing page action
	
	public ProductCatalogue loginApplication() {
		goTo();
		doSendKeys(userEmail, "ecommuser@gmail.com");
		doSendKeys(password, "12qw!@QW");
		doClick(loginBtn);
		waitForElementInVisibility(loadingSpinner);
		return new ProductCatalogue(driver);
		
	}
	public ProductCatalogue loginApplication(String username, String pwd) {
		goTo();
		doSendKeys(userEmail, username);
		doSendKeys(password, pwd);
		doClick(loginBtn);
		return new ProductCatalogue(driver);
		
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getLoginErrorMessage() {
		
		waitForElementVisibility(loginError);
		return loginError.getText();
		
	}

}
