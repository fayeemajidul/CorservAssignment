package ViewBusiness;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;
import Pages.ViewBuisnessPages;

public class CreditCardsView {
	
	public static Duration timeout = Duration.ofSeconds(40);
	public static WebDriverWait wait;
	public static ViewBuisnessPages busniessPage;
	
 	
	public static void main(String[] args) throws InterruptedException {
		
	
		WebDriver driver;	
		WebDriverManager.chromedriver().setup();
		
		//initialize browser 
		driver = new ChromeDriver();
		
		//add time
		wait = new WebDriverWait(driver, timeout);
		
		busniessPage = new ViewBuisnessPages(driver);
		
		//check Internet connectivity 
		try {
			  	URL url = new URL("http://www.google.com");
			  	URLConnection connection = url.openConnection();
			  	connection.connect();
			  	System.out.println("Internet is connected");
				} catch (IOException e) {
					System.out.println("Internet is not connected");
				}

		//open web link
		driver.get("https://pnfp.myapexcard.com/info");
		driver.manage().window().maximize();
		System.out.print("Opening Browser");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(busniessPage.productSelector));
		System.out.print("\n Element is visible");
		
		//click on View Business Credit Cards
		driver.findElement(busniessPage.categorySwitch).click();	
		System.out.print("\n Clicked on category switch");
		
		WebElement element = driver.findElement(busniessPage.termsConditions);
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(5000);
		
		String winHandleBefore = driver.getWindowHandle();
		
		element.click();
		
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(busniessPage.termsConditionsTable));
		System.out.print("\n You are on terms and condition page");
		
		//get APR value from table
		String getAPRValue = driver.findElement(busniessPage.valueAPRTable).getText();
		
		//get int value from APR variable
		 String numberOnly= getAPRValue.replaceAll("[^0-9^.]", "");
		 float getAPRNo = Float.parseFloat(numberOnly); 
		 System.out.print("\n number only APR value=" + getAPRNo);
		
		float expectValue = (float) 24.0;
		
		//check APR cash advance percentage
		
		if (getAPRNo < expectValue) {
			System.out.print("\n APR for Cash Advances is less than 24%");
		}else {
			System.out.print("\n APR for Cash Advances is greater than 24%");
		}
		//close browser
		driver.quit();
	}

}
