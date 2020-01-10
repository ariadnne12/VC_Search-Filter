import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasicFunctions {

	private WebDriverWait wait;

	public void verifyUrl(String parameter, WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains(parameter));
	}

	public void PressEnter(WebDriver driver) {
		Actions builder = new Actions(driver);
		builder.sendKeys(Keys.ENTER).build().perform();
	}

	public boolean VerifyElementIsDisplayed(WebElement element, WebDriver driver) {
		wait = new WebDriverWait(driver, 110000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.isDisplayed();
	}

	public void SelectValueForElement(WebElement element, String Value) {
		Select selectElement = new Select(element);
		selectElement.selectByVisibleText(Value);
	}

	public void SelectOptionForElement(WebElement element, Integer number) {
		Select selectElement = new Select(element);
		selectElement.selectByIndex(number);
	}
	
	
	public void EnterTextInElement(WebElement webElement, String text) {
		Integer i = 0;
		while (i < 5) {
			try {
				if (webElement.isDisplayed() && webElement.isEnabled()) {
					webElement.sendKeys(text);
					break;
				}

			} catch (NullPointerException e) {
				System.out.print("NullPointerException Caught");
				i++;
			} catch (StaleElementReferenceException ex) {
				i++;
			}

		}
	}

	public void ClickOnElement(WebElement webElement, WebDriver driver) {
		wait = new WebDriverWait(driver, 8000);	
		Integer i = 0;
		while (i < 5) {
			try {
				wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();
				break;
			} catch (NullPointerException e) {
				System.out.print("NullPointerException Caught");
				i++;
			} catch (StaleElementReferenceException ex) {
				i++;
			}

		}}
}
