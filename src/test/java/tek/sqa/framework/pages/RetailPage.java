package tek.sqa.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tek.sqa.framework.utilities.CommonUtility;

public class RetailPage extends CommonUtility {
	
	public RetailPage() {
		PageFactory.initElements(getDriver(), this);
	}
	@FindBy(id ="signinLink")
	public WebElement signInOption;
	
	@FindBy(id ="email")
	public WebElement emailField;
	
	@FindBy(css = "#password")
	public WebElement passwordField;
	
	@FindBy(xpath ="//button[text()='Login']")
	public WebElement loginButton;
	@FindBy(linkText = "TEKSCHOOL") // this is same as driver.findElement() 
	public WebElement tekschoolLogo;
	
	@FindBy(id = "searchInput")
	public WebElement searchBarInput;
	
	@FindBy(id = "searchBtn")
	public WebElement searchButton;
	
	@FindBy(xpath ="//img[contains(@alt,'Pokemon')]")
	public WebElement pokemonImage;
	
	@FindBy(xpath ="//a[text()='Account']")
	public WebElement accountOption;
	
	@FindBy(id ="cardNumberInput" )
	public WebElement cardInputField;
	
	@FindBy(id = "nameOnCardInput")
	public WebElement nameOnCardInputField;
	
	@FindBy(id ="expirationMonthInput")
	public WebElement expirationMonth;
	
	@FindBy(id ="expirationYearInput")
	public WebElement expirationYear;
	
	@FindBy(id ="securityCodeInput")
	public WebElement securityCodeInput;
	
	@FindBy(id="paymentSubmitBtn")
	public WebElement addYourCardButton;
	
	
	
	public void signIn(String emailValue, String passwordValue) {
		click(signInOption);
		emailField.sendKeys(emailValue);
		passwordField.sendKeys(passwordValue);
		click(loginButton);
	}
	
	public void clickOnAccountOption() {
		click(accountOption);
	}
	
	
	
	
}
