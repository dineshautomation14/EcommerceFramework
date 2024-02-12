package com.EcommerceFramework.qa.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.EcommerceFramework.qa.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	@FindBy(css=".mb-3")	List<WebElement> productsBy;
	@FindBy(css=".ngx-spinner-overlay")	WebElement loadingSpinner;
	@FindBy(id="products")	WebElement productSection;

	By prodBy =  By.cssSelector("b");
	By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
	By toast = By.id("toast-container");
	
	//page actions or methods.
	public List<WebElement> getProductCards() {
		return productsBy;
	}
	
	
	public MyCartPage addToCart(String productName) {
		waitForElementVisibility(productSection);
		WebElement productCard =  getProductCards().stream().filter(product -> 
		product.findElement(prodBy).getText().equals(productName)).findFirst().orElse(null);
		doClick(productCard.findElement(addToCartBtn));
		
		//waiting for loading to be invisible and post that waiting for toast message like product added to cart
		waitForElementInVisibility(loadingSpinner);
	//	waitForElementVisibility(driver.findElement(toast));
		return new MyCartPage(driver);
	}

}
