package assignment1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllLinksW2A {
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http:\\www.google.com");

		WebElement queryBox = driver.findElement(By.xpath("//div/div[2]/input"));
		queryBox.sendKeys("way to automation");
		WebElement searchButton = driver.findElement(By.xpath("//div[3]/center/input[1]"));
		searchButton.click();

		WebElement resultBlock = driver.findElement(By.id("search"));
		resultBlock.findElement(By.tagName("a")).click();

		Actions mouseMove = new Actions(driver);
//		mouseMove.moveByOffset(50, 10);
		mouseMove.moveToElement(driver.findElement(By.xpath("//*[@id='post-17']")));
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='elementor-popup-modal-26600']/*")));
		List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println("Total links in the page: " + links.size());

		for (WebElement link : links)
			System.out.println("--a--" + link.getAttribute("href"));
		
		driver.quit();

	}

}
