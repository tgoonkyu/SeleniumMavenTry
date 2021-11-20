package assignment3;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CaptchaCalculations {
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://www.timesofindia.com/poll.cms");

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mathq2\"]")));

		WebElement pollQues = driver.findElement(By.xpath("//*[@id=\"pollform\"]/table/tbody/tr[1]/td"));
		System.out.println(pollQues.getText());
		WebElement radioButtonBlock = driver.findElement(By.xpath("//*[@id=\"pollform\"]/table/tbody/tr[2]/td"));
		boolean poll = true;
		if (poll)
			radioButtonBlock.findElement(By.xpath("//*[@type='radio' and @value='2']")).click();
		else
			radioButtonBlock.findElement(By.xpath("//*[@type='radio' and @value='1']")).click();
		System.out.println("Selected poll option:" + poll);

		WebElement captcha = driver.findElement(By.xpath("//*[@id=\"mathq2\"]"));
		String getCaptcha = captcha.getText();
		String[] part2 = null;
		char operand = 0;
		double result;
		if (getCaptcha.contains("+")) {
			operand = '+';
		} else if (getCaptcha.contains("*")) {
			operand = '*';
		} else if (getCaptcha.contains("x")) {
			operand = '*';
		} else if (getCaptcha.contains("X")) {
			operand = '*';
		} else if (getCaptcha.contains("-")) {
			operand = '-';
		} else if (getCaptcha.contains("\\")) {
			operand = '\\';
		} else if (getCaptcha.contains("^")) {
			operand = '^';
		}
		String[] captchaSplit = getCaptcha.split("\\+");
		for (int i = 0; i < captchaSplit.length; i++) {
			if (captchaSplit[i].contains("="))
				part2 = captchaSplit[i].split("=");
//			System.out.println(captchaSplit[i].trim());
		}
		String operand1, operand2;
//		for (int i = 0; i < part2.length; i++) {
//			System.out.println(part2[i]);
//		}
		operand1 = captchaSplit[0].trim();
		operand2 = part2[0].trim();
		switch (operand) {
		case '+':
			result = (Integer.parseInt(operand1) + Integer.parseInt(operand2));
			System.out.println("Result: " + operand1 + "+" + operand2 + "=" + result);
			WebElement resultBox = driver.findElement((By.xpath("//*[@id='mathuserans2']")));
			resultBox.sendKeys((String.valueOf((int) result)));
			break;
		default:
			System.out.println("Operator not identifiable!");
			break;
		}
		driver.findElement(By.xpath("//*[@id=\"pollform\"]/table/tbody/tr[4]/td/div")).click();

		if (driver.findElement(By.xpath("//*[@id=\"polldiv\"]/table/tbody/tr/td/table[1]/tbody/tr/td/font/b")).getText().contains("success")){
			System.out.println("Poll submitted successfully!");
			driver.quit();
		}

	}

}
