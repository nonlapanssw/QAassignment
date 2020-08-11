package Test.assignment;

/**
 * Hello world!
 *
 */
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;
//import io.github.bonigarcia.wdm.WebDriverManager;

public class QA_assignment {
	public static void main(String[] args){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\mangp\\Downloads\\chromedriver_win32\\chromedriver.exe");
		//ChromeDriverManager.getInstance().setup();
		WebDriver driver = new ChromeDriver();
 		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String emailToLogin = "tester@worldticket.net"; 
		
		
		//1. Access this URL: https://ecom-test-dat.worldticket.net/
		driver.get("https://ecom-test-dat.worldticket.net/");
        driver.manage().window().maximize();		
        
              
		//2. Check title name contains �WorldTicket�
		boolean title = driver.getTitle().contains("WorldTicket");
		if(title==true) {
			System.out.println("title name contains Worldticket");			
		}
				
		
		//3. Click the Profile icon at the top right of the page
		WebElement profile = driver.findElement(By.xpath("//div[@id='header-account']//i"));
		executor.executeScript("arguments[0].click();", profile);

		
		//4. Login by email
		WebElement email = driver.findElement(By.xpath("//input[@id='email']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		email.sendKeys(emailToLogin);
		password.sendKeys("atm1234");
		driver.findElement(By.xpath("//div[@id='step-registered-customers']//button[@type='submit']/span/span[.='Log in']")).click();

		driver.get("https://ecom-test-dat.worldticket.net/customer/account/");	
		WebElement cookie= driver.findElement(By.xpath("//div[@id='v-cookielaw']//a[@href='javascript:setCookieLaw(1);']"));
		executor.executeScript("arguments[0].click();", cookie);
		
		
		//5.Check the user name contains �Junior Tester�.
		String username = "Junior Tester";
		WebElement user=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root-wrapper']/div[@class='wrapper']/div[@class='page']/div[2]/div[2]//strong[.='Hello, Junior Tester!']")));
		boolean checkUser = user.getText().contains(username);
		if (checkUser==true) {
		System.out.println("user name contains �Junior Tester�");
		}
		
		//6. Click the account information tab. 		
 		WebElement accountEdit=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='account_edit']/a")));
		executor.executeScript("arguments[0].click();",accountEdit);
		
		
		//7. Check only the email address field is equal to the login email.
 		WebElement emaillAccount=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
 		String emailAddressField = emaillAccount.getAttribute("value");
 		if(emailAddressField.equals(emailToLogin)) {
 			System.out.println("email address field is equal to the login email");
 		};
	}
}
