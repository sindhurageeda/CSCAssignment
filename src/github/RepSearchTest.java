package github;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RepSearchTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\LENOVO\\OneDrive\\Desktop\\Jar Files\\chromedriver.exe");
		//Initiating WebDriver
		WebDriver driver = new ChromeDriver();
		//Setting common wait time for visibility of elements
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Opening website
		driver.get("https://github.com");
		driver.manage().window().maximize();
		
		//Searching on the home page
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.click();
		searchBox.sendKeys("react");
		searchBox.sendKeys(Keys.ENTER);
		
		//Search using Advanced search
		driver.findElement(By.linkText("Advanced search")).click();
		
		Select langSelect = new Select(driver.findElement(By.id("search_language")));
		langSelect.selectByValue("JavaScript");
		
		driver.findElement(By.id("search_stars")).sendKeys(">45");
		
		driver.findElement(By.id("search_followers")).sendKeys(">50");
		
		Select licenseSelect = new Select(driver.findElement(By.id("search_license")));
		licenseSelect.selectByVisibleText("Boost Software License 1.0");
		
		driver.findElement(By.xpath("(//button[text()='Search'])[2]")).click();
		
		//Verifying the number of repository results
		int noOfResults = (driver.findElement(By.className("repo-list")).findElements(By.tagName("li"))).size();
		assertEquals(1, noOfResults);
		
		//Verifying the repository name
		String resultRepName = driver.findElement(By.xpath("//ul[@class='repo-list']/li/div/div/a")).getText();
		assertEquals("mvoloskov/decider",resultRepName);
		driver.findElement(By.linkText("mvoloskov/decider")).click();
		
		//Displaying the 300 chars from readme file
		driver.findElement(By.linkText("README.md")).click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver,20);
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id='raw-url']"))));
		 */	
		driver.findElement(By.xpath("//*[@id='raw-url']")).click();		
		String readmeContent = driver.findElement(By.xpath("/html/body/pre")).getText();		
		for(int i=0;i<300;i++) 
		{
			System.out.print(readmeContent.charAt(i));
		}
		
		//Closing the driver 
		driver.quit();		
	}

}
