package com.EcommerceFramework.qa.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.EcommerceFramework.qa.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents {

	private WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// locators
	@FindBy(css = ".details__user [placeholder='Select Country']")
	WebElement selectCountryField;

	@FindBy(css = ".user__address button")
	List<WebElement> selectCountryDropdown;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	// page methods
	public void selectCountry(String name) {
		doSendKeys(selectCountryField, name);
		
		WebElement country = selectCountryDropdown.stream()
				.filter(tempCountry -> waitForElementVisibility(tempCountry).getText().trim().equals(name)).findFirst().orElse(null);
		doClick(country);
	}

	public ConfirmationPage placeOrder() {
		selectCountry("India");
		doClick(placeOrderBtn);
		return new ConfirmationPage(driver);

	}
}
