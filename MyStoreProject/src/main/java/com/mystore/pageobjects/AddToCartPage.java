/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * 
 */
public class AddToCartPage extends BaseClass{
     Action action = new Action();
     @FindBy(xpath="//")
     private WebElement quantity;
     
     @FindBy(css = "#block_top_menu > ul > li:nth-child(1) > ul > li:nth-child(2) > ul > li:nth-child(3) > a[title='Women']")
     private WebElement size;
     
     @FindBy(xpath="//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")
     private WebElement productHover;
     
     @FindBy(xpath="//*[@id=\"center_column\"]/ul/li[2]/div/div[3]/div/a")
     private WebElement addToCartBtn;
     
     @FindBy(xpath="//*[@id=\"center_column\"]/div[3]/div/form/button/span")
     private WebElement addToCartMessage;
     
     @FindBy(xpath="//*[@id=\"product_comparison\"]/tbody/tr[1]/td[2]/div[5]/div/div/a[1]/span")
     private WebElement proceedToCheckOutBtn;
     
     @FindBy(xpath="//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a/span")
     private WebElement CheckOutBtn;
     
     public AddToCartPage() {
    	 PageFactory.initElements(getDriver(), this);
     }
     public void mouseHover() throws Throwable {
    	  action.mouseHoverByJavaScript(quantity);
     }
     public void click() throws Throwable {
    	 action.click(getDriver(), size);
     }
     public void productHover() throws Throwable {
    	 action.mouseHoverByJavaScript(productHover);
     }
     public void clickOnCompare() throws Throwable{
    	 action.click(getDriver(),addToCartBtn);
     }
     public void clickOnCompareBtn() throws Throwable{
    	 action.click(getDriver(), addToCartMessage);
     }
     public void clickOnAddToCartBtn() throws Throwable{
    	 action.click(getDriver(), proceedToCheckOutBtn);
     }
     public void clickOnCheckOutBtn() throws Throwable{
    	 action.click(getDriver(), CheckOutBtn);
     }
     public boolean validateAddtoCart() throws Throwable {
    	 action.fluentWait(getDriver(), addToCartMessage, 10);
    	 return action.isDisplayed(getDriver(),addToCartMessage );
     }
     public OrderPage clickOnCheckOut() throws Throwable{
    	 action.fluentWait(getDriver(), CheckOutBtn, 10);
    	 action.JSClick(getDriver(), CheckOutBtn);
    	 return new OrderPage();
     }
     
     
}
