package com.mystore.testcases;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AccountCreationPage;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class AccountCreationPageTest extends BaseClass{
          private IndexPage indexPage;
          private LoginPage loginPage;
          private AccountCreationPage accountCreationPage;
          private HomePage homePage;
          
          @Parameters("browser")
          @BeforeMethod(groups = {"Smoke","Sanity","Regression"})
          public void setup(String browser) {
        	  launchApp(browser);
          }
          
          @AfterMethod(groups = {"Smoke","Sanity","Regression"})
          //public void tearDown() {
        	//  getDriver().quit();
          //}
          
          @Test(groups="Sanity",dataProvider = "email", dataProviderClass = DataProviders.class)
          public void  verifyCreateAccountPageTest(String email) throws Throwable{
        	  Log.startTestCase("verifyCreateAccountPageTest");
        	  indexPage = new IndexPage();
        	  loginPage = indexPage.clickOnSignIn();
        	  accountCreationPage = loginPage.createNewAccount(email);
        	  boolean result = accountCreationPage.validateAcountCreatePage();
        	  Assert.assertTrue(result);
        	  Log.endTestCase("verifyCreateAccountPageTest");
          }
          
          @Test(groups="Regression",dataProvider="newAccountDetailsData", dataProviderClass=DataProviders.class)
          public void createAccountTest(HashMap<String,String> hashMapValue) throws Throwable{
        	  Log.startTestCase("createAccountTest");
        	  indexPage = new IndexPage();
        	  loginPage = indexPage.clickOnSignIn();
        	  accountCreationPage = loginPage.createNewAccount(hashMapValue.get("Email"));
        	  accountCreationPage.createAccount(
        			  hashMapValue.get("Gender"),
        			  hashMapValue.get("FirstName"),
        			  hashMapValue.get("LastName"),
        			  hashMapValue.get("SetPassword"),
        			  hashMapValue.get("Day"),
        			  hashMapValue.get("Month"),
        			  hashMapValue.get("Year"),
        			  hashMapValue.get("Company"),
        			  hashMapValue.get("Address"),
        			  hashMapValue.get("City"),
        			  hashMapValue.get("State"),
        			  hashMapValue.get("Zipcode"),
        			  hashMapValue.get("Country"),
        			  hashMapValue.get("MobilePhone")
        			  );
        	  System.out.println("hello");
        	  homePage = accountCreationPage.validateRegistration();
        	  Assert.assertEquals("http://www.automationpractice.pl/index.php?controller=authentication&back=my-account#account-creation", homePage.getCurrURL());
        	  Log.endTestCase("createAccountTest");
          }
}
