
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPageObjects {

	private WebDriver driver;
	private WebElement el;
	private List<WebElement> list;

	public SearchPageObjects(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement privacyPolicyBanner() {
		el = getElement(By.xpath("//*[@class='privacyPolicyBanner__actions']/button"));
		return el;
	}

	public List<WebElement> productBrandSnippets() {
		return driver.findElements(By.xpath("//*[@class='productSnippet__text--brand'][@itemprop='brand']"));
		
	}

	public List<WebElement> productPriceSnippets() {
		list = driver.findElements(By.xpath("//*[@itemprop='offers']"));
		return list;
	}

	public WebElement footerInternationalisation() {
		el = getElement(By.className("footer__internationalisation"));
		return el;
	}

	public WebElement countries() {
		el = getElement(By.xpath("//*[@name='countries'][@aria-label='countries']"));
		return el;
	}

	public WebElement languages() {
		el = getElement(By.xpath("//*[@name='languages'][@aria-label='languages']"));
		return el;
	}

	public WebElement currency() {
		return driver.findElement(By.xpath("//*[@name='currencies'][@aria-label='currencies']"));
	}

	public WebElement saveChangesButton() {
		el = getElement(By.xpath("//button[text()='Save Changes']"));
		return el;
	}

	public WebElement searchBar() {
		el = getElement(By.id("headerSearch"));
		return el;
	}

	public WebElement searchBarInputField() {
		el = getElement(By.xpath("//input[@name='searchValue'][@placeholder='Search by brand, article...']"));
		return el;
	}

	public WebElement autoSuggestValue() {
		el = getElement(By.xpath("//a[@class='suggestions__itemLink'][@href='/search/?q=gucci%20bag']"));
		return el;
	}

	public WebElement searchedFilter() {
		el = getElement(By.xpath("//*[@class='ais-CurrentRefinements-item']/button[text()=' Gucci ']"));
		return el;
	}

	public WebElement locationInput() {
		el = getElement(By.xpath("//input[@placeholder='Enter your country']"));
		return el;
	}

	public WebElement locationCheckBox() {
		 el = getElement(By.xpath("//*[text()='France'][@class='filters_checkbox_label']"));
		return el;
	}

	public WebElement locationFilter() {
		el = getElement(By.xpath("//button[@class='ais-CurrentRefinements-button'][text()='France']"));
		return el;
	}

	public WebElement minPriceInput() {
		el = getElement(By.id("price-range-min"));
		return el;
	}

	public WebElement maxPriceInput() {
		el = getElement(By.id("price-range-max"));
		return el;
	}

	public WebElement priceOkButton() {
		el = getElement(By.xpath("//button[text()='OK'][@class='vc-btn vc-btn--secondary vc-btn--small']"));
		return el;
	}

	public WebElement minPriceFilter() {
		el = getElement(By.xpath("//button[text()[contains(.,'>= 200')]]"));
		return el;
	}

	public WebElement maxPriceFilter() {;
		el = getElement(By.xpath("//button[text()[contains(.,'<= 500')]]"));
		return el;
	}


	public WebElement getElement(By selector) {

		WebElement element = driver.findElement(selector);
		Integer i = 0;
		while (i < 5) {
			try {
				return element = driver.findElement(selector);
			} catch (NullPointerException e) {
				System.out.print("NullPointerException Caught");
				i++;
			} catch (StaleElementReferenceException ex) {
				i++;
			}
		}
		return element;
	}
}
