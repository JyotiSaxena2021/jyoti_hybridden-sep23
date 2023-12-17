package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;
import utility.ExcelOperations;

public class LoginTest {
	LoginPage loginPage;

	@BeforeMethod
	public void setup() {
		ControlActions.launchBrowser();
		loginPage = new LoginPage();
	}

	@Test
	public void verifyLogin() {

		loginPage.login("jyotisaxena2006@gmail.com", "Welcome@1234");
		boolean loginFlag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(loginFlag);
	}

	@Test
	public void verifyErrorMessages() {
		System.out.println("Step : Click onLogin button");
		loginPage.clickOnLoginBtn();

		System.out.println("Step : Email required, Error message displayed");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);

		System.out.println("Step : Verify Password required ,Error message displayed");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertTrue(passwordErrorMessageFlag);

	}

	@Test
	public void verifyPasswordErrorMessage() {
		System.out.println("Step : Enter valid user email");
		loginPage.enterUserEmail("jyotisaxena2006@gmail.com");

		System.out.println("Step : Click onLogin button");
		loginPage.clickOnLoginBtn();

		System.out.println("Verify Email required ,Error message displayed");
		boolean emailErrorMessageFlag = loginPage.isEmailRequiredElementDisplayed();
		Assert.assertFalse(emailErrorMessageFlag);

		System.out.println("Step : Verify Password,Error message displayed");
		boolean passwordMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertFalse(passwordMessageFlag);

	}

	@Test
	public void verifyEmailErrorMessageDisplayed() {
		System.out.println("Step : Enter Valid password");
		loginPage.enterPassword("Welcome@1234");

		System.out.println("Step : Click Onlogin button");
		loginPage.clickOnLoginBtn();

		System.out.println("Step : Verify Email Required,Error Message Displayed");
		boolean emailErrorMessageFlag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(emailErrorMessageFlag);

		System.out.println("Step : Verify Password Required Element");
		boolean passwordErrorMessageFlag = loginPage.isPasswordRequiredElementDisplayed();
		Assert.assertFalse(passwordErrorMessageFlag);

	}

	@Test(dataProvider = "LoginDataProvider")
	public void verifyloginUsingDataDriven(String username, String password, String loginStatus) {
		System.out.println("Step - Login with given Credentials");
		loginPage.login(username, password);
		String currentURL = "";
		boolean loginFlag;

		if (loginStatus.equalsIgnoreCase("pass")) {
			System.out.println("Step : Verify Login Succesful toast message displayed");
			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertTrue(loginFlag, "Login successfully Message was not displayed");

			System.out.println("Verify Incorrect Email displayed");
			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
			Assert.assertFalse(loginFlag, "Incorrect email or password message was displayed");
			currentURL = loginPage.getCurrentURL();
			System.out.println("VERIFY - Application should redirect to dahsboard page");
			Assert.assertTrue(currentURL.endsWith("dashboard/dash"), "Current URL :" + currentURL);
		} else {
			loginFlag = loginPage.isLoginUnsuccessfulElementDisplayed();
			Assert.assertTrue(loginFlag, "Incorrect email or password message was not displayed");

			loginFlag = loginPage.isLoginSuccessFullyDisplayed();
			Assert.assertFalse(loginFlag, "Login successfully Message was displayed");

			currentURL = loginPage.getCurrentURL();
			Assert.assertTrue(currentURL.endsWith("auth/login"));
		}
	}

	@DataProvider(name = "LoginDataProvider")
	public Object[][] getLoginData() throws IOException {
		return ExcelOperations.getAllRows(".//testData/LoginData.xlsx", "Login");
	}

	@AfterMethod
	public void tearDown() {
		ControlActions.closeBrowser();
	}
}
