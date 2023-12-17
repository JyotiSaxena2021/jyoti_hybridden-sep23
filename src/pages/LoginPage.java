package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ControlActions;
import constants.ConstantPath;

public class LoginPage extends ControlActions {
	String assesmentname;

	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement userEmailElement;

	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement userPasswordElement;

	@FindBy(xpath = "//input[@id='login']")
	WebElement loginBtnElement;

	@FindBy(xpath = "//div[@aria-label='Login Successfully']")
	WebElement loginSuccessfulElement;

	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement loginUnSuccessfulElement;

	@FindBy(xpath = "//div[@id='Maulik']")
	List<WebElement> temp;

	@FindBy(xpath = "//div[text()='*Email is required']")
	WebElement emailIsRequiredElement;

	@FindBy(xpath = "//div[text()='*Password is required']")
	WebElement passwordIsRequiredElement;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void login(String email, String password) {
		/*
		 * System.out.println("STEP: Entered Email address");
		 * driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(email);
		 */
		enterUserEmail(email);

		/*
		 * System.out.println("STEP: Entered Password");
		 * driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys(password
		 * );
		 */

		enterPassword(password);

		/*
		 * System.out.println("STEP: Clicked on Login Button");
		 * driver.findElement(By.xpath("//input[@id='login']")).click();
		 */
		clickOnLoginBtn();
	}

	public void enterUserEmail(String email) {
		System.out.println("Step : Enter email");
		userEmailElement.sendKeys(email);
	}

	public void enterPassword(String password) {
		System.out.println("Step : Enter Password");
		userPasswordElement.sendKeys(password);
	}

	public void clickOnLoginBtn() {
		System.out.println("Step : Click on the Login Button");
		loginBtnElement.click();
	}

	public boolean isLoginSuccessFullyDisplayed() {
		/*
		 * WebElement loginSuccessfulElement = getElement("XPATH",
		 * "//div[@aria-label='Login Successfully']", true); return
		 * loginSuccessfulElement.isDisplayed();
		 */
		return isElementDisplayedWithWait(loginSuccessfulElement, ConstantPath.FAST_WAIT);
	}

	public boolean isLoginUnsuccessfulElementDisplayed() {
		return isElementDisplayedWithWait(loginUnSuccessfulElement, ConstantPath.FAST_WAIT);
	}

	public boolean isEmailRequiredElementDisplayed() {
		return isElementDisplayed(emailIsRequiredElement);
	}

	public boolean isPasswordRequiredElementDisplayed() {
		return isElementDisplayed(passwordIsRequiredElement);
	}

	public String getCurrentURL() {
		return super.getCurrentURL();
	}
}
