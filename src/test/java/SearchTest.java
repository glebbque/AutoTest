import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

    private WebDriver driver;

    @BeforeClass
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions().addArguments("--incognito", "--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeMethod
    public void openPage() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void verifySearchingByBookName() {
        driver.findElement(By.xpath("//input[@name='keyword']")).sendKeys("The Alchemist");
        driver.findElement(By.xpath("//input[@value='Search']")).click();
        String nameBook = driver.findElement(By.xpath("//p[text()='The Alchemist ']")).getText();
        Assert.assertEquals(nameBook, "The Alchemist");
    }

    @Test
    public void verifyRegistration() {
        driver.findElement(By.xpath("//a[text()='Sign up']")).click();
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@mail.com");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String accountIsCreated = driver.findElement(By.xpath("//span[@class='confirmation_message']")).getText();
        Assert.assertEquals(accountIsCreated, "Account is created!");
    }
}
