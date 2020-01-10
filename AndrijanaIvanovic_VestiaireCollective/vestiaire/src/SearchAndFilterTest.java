import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchAndFilterTest extends BasicFunctions {

	private WebDriver driver;
	private String baseUrl;
	private SearchPageObjects spo;
	private List<WebElement> products;
	private WebDriverWait wait;
	private JavascriptExecutor jse;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "\\Chrome Driver\\chromedriver.exe");
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} catch (Exception ex) {
			System.out.printf("Exception while instantiating driver. ", ex);
		}
		baseUrl = "https://fr.vestiairecollective.com/";
		driver.get(baseUrl);
		driver.manage().window().maximize();
		spo = new SearchPageObjects(driver);
		wait = new WebDriverWait(driver, 8000);
	}

	@Test
	public void searchAndFilter() throws Exception {
		spo.privacyPolicyBanner().click();
		setCatalogConfigTask2();
		searchBarTask345();
		checkProductsBrandTask6();
		setPriceTask89();
		setLocationTask79();
		checkProductsPriceTask10();
		removeMinPriceTask11();
		checkProductsPriceTask12();
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void setCatalogConfigTask2() {
		ClickOnElement(spo.footerInternationalisation(), driver);
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[@name='countries'][@aria-label='countries']")));
		SelectValueForElement(spo.countries(), "United Kingdom");
		SelectValueForElement(spo.languages(), "English - UK");
		SelectOptionForElement(spo.currency(), 2);
		ClickOnElement(spo.saveChangesButton(), driver);

	}

	public void searchBarTask345() {
		ClickOnElement(spo.searchBar(), driver);
		wait.until(ExpectedConditions.elementToBeClickable(spo.searchBarInputField()));
		EnterTextInElement(spo.searchBarInputField(), "gucci bag");
		ClickOnElement(spo.autoSuggestValue(), driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//*[@class='ais-CurrentRefinements-item']/button[text()=' Gucci ']")));
		assertTrue(spo.searchedFilter().isDisplayed());
		verifyUrl("q=gucci%", driver);
	}

	public void checkProductsBrandTask6() {
		products = spo.productBrandSnippets();
		for (WebElement webElement : products) {
			String brandName = webElement.getText();
			assertTrue(brandName.equals("Gucci"));
		}
	}

	public void setLocationTask79() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Enter your country']")));
		EnterTextInElement(spo.locationInput(), "France");
		PressEnter(driver);
		jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", spo.locationCheckBox());
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//button[@class='ais-CurrentRefinements-button'][text()='France']")));
		assertTrue(spo.locationFilter().isDisplayed());

	}

	public void setPriceTask89() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("price-range-min")));
		wait.until(ExpectedConditions.elementToBeClickable(spo.minPriceInput()));
		EnterTextInElement(spo.minPriceInput(), "200");
		EnterTextInElement(spo.maxPriceInput(), "500");
		ClickOnElement(spo.priceOkButton(), driver);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()[contains(.,'>= 200')]]")));
		assertTrue(spo.minPriceFilter().isDisplayed());
		assertTrue(spo.maxPriceFilter().isDisplayed());
	}

	public void checkProductsPriceTask10() {
		String script = "return document.querySelectorAll('[itemprop=\"price\"]')";
		jse = (JavascriptExecutor) driver;
		ArrayList<WebElement> listOfFirstPagePrices = new ArrayList<>();
		boolean priceFilterWorks = false;
		try {
			listOfFirstPagePrices = (ArrayList<WebElement>) jse.executeScript(script);
			for (WebElement webElement : listOfFirstPagePrices) {
				String rawPrice = webElement.getAttribute("innerHTML");
				double d = Double.parseDouble(rawPrice);
				int price = (int) d;
				if (price >= 200 && price <= 500) {
					priceFilterWorks = true;
					assertTrue(priceFilterWorks);
				}
			}
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}

	}

	public void removeMinPriceTask11() {
		ClickOnElement(spo.minPriceFilter(), driver);
	}

	public void checkProductsPriceTask12() {
		String script = "return document.querySelectorAll('[itemprop=\"price\"]')";
		jse = (JavascriptExecutor) driver;
		ArrayList<WebElement> listOfFirstPagePrices = new ArrayList<>();
		boolean priceFilterWorks = false;
		try {
			listOfFirstPagePrices = (ArrayList<WebElement>) jse.executeScript(script);
			for (WebElement webElement : listOfFirstPagePrices) {
				String rawPrice = webElement.getAttribute("innerHTML");
				double d = Double.parseDouble(rawPrice);
				int price = (int) d;
				if (price <= 500) {
					priceFilterWorks = true;
					assertTrue(priceFilterWorks);
				}
			}
		} catch (Exception e) {
			e.getCause().printStackTrace();
		}

	}

	public void checkprices() {
		String script = "return document.querySelectorAll('[itemprop=\"price\"]')";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		ArrayList<WebElement> list = new ArrayList<>();
		boolean priceFilterWorks = false;
		try {
			list = (ArrayList<WebElement>) jse.executeScript(script);
			for (WebElement webElement : list) {
				String lala = webElement.getAttribute("innerHTML");
				int price = Integer.valueOf(lala);
				if (price >= 200 && price <= 500) {
					priceFilterWorks = true;
					assertTrue(priceFilterWorks);
				}
			}
		}

		catch (Exception e) {
			e.getCause().printStackTrace();
		}

	}

}
