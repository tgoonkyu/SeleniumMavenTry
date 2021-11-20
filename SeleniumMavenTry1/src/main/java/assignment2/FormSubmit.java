package assignment2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FormSubmit {
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http:\\qa.way2automation.com");

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[4]/div/div/div/div")));

		// finding elements
		WebElement resultBlock = driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div"));
		WebElement name = resultBlock.findElement(By.xpath("//*[@name='name']"));
		WebElement phone = resultBlock.findElement(By.xpath("//*[@name='phone']"));
		WebElement email = resultBlock.findElement(By.xpath("//*[@name='email']"));
		WebElement countryDropdown = resultBlock.findElement(By.xpath("//*[@name='country']"));
		WebElement city = resultBlock.findElement(By.xpath("//*[@name='city']"));
		WebElement username = resultBlock
				.findElement(By.cssSelector("#load_form > fieldset:nth-child(10) > input[type=text]"));
		WebElement password = resultBlock
				.findElement(By.cssSelector("#load_form > fieldset:nth-child(11) > input[type=password]"));
		WebElement submitButton = driver
				.findElement(By.cssSelector("#load_form > div:nth-child(12) > div.span_1_of_4 > input"));

		// variables declaration
		String nameValue, emailValue, phoneValue, countryValue, cityValue, usernameValue, paswordValue;
		boolean countryFlag = false;
		Select country = new Select(countryDropdown);
		List<WebElement> countryOptions = country.getOptions();

		// entering the values
		usernameValue = "rooniec";
		nameValue = "Roonie";
		emailValue = "roonie.c@yahoo.com";
		phoneValue = "+919211420420";
		countryValue = "Sweden";
		cityValue = "Lucknow";
		paswordValue = "testingfield";

		name.sendKeys(nameValue);
		phone.sendKeys(phoneValue);
		email.sendKeys(emailValue);

		try {
			for (WebElement option : countryOptions) {
				if (option.getText().equals(countryValue))
					countryFlag = true;
			}
			if (countryFlag)
				System.out.println("Setting country as: " + countryValue);
			country.selectByValue(countryValue);
		} catch (Throwable t) {
			System.out.println("Invalid country option!");
			country.selectByIndex(0);
		}
		city.sendKeys(cityValue);
		username.sendKeys(usernameValue);
		password.sendKeys(paswordValue);
		try {
			submitButton.click();
			System.out.println("Clicked on \"Submit Button\"!");

		} catch (Throwable t) {
			System.out.println("Click on \"Submit Button\" failed!");
			System.out.println(t.getMessage());
		}
		
		// wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[@id='alert']")));
		wait.until(ExpectedConditions.attributeToBeNotEmpty(resultBlock.findElement(By.xpath("//p[@id='alert']")), "innerHTML"));
		//WebElement submitSuccess = resultBlock.findElement(By.xpath("//*[@id='alert']/p"));
		//WebElement submitSuccess = resultBlock.findElement(By.cssSelector("p#alert"));
		WebElement submitSuccess = resultBlock.findElement(By.tagName("p"));
		//if (submitSuccess.isDisplayed()) {
//			System.out.println("Alert visible!");
//			System.out.println(submitSuccess.getText());
//			System.out.println(submitSuccess.getAttribute("value"));
//			System.out.println(submitSuccess.getAttribute("id"));
			if (submitSuccess.getText().trim().contains(("This is just a dummy form, you just clicked SUBMIT BUTTON").trim()))
				System.out.println("Successfully submitted!");
			else
				System.out.println("Alert validation failed!");
		//}
		
			driver.quit();
	}
}
