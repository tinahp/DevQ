package LoginTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login {

    private WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://deliveryqs.com/login");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        System.out.println(driver.getTitle());
     // Input username Field
        driver.findElement(By.xpath("/html/body/section[1]/div/form/div[1]/div[1]/div/input")).sendKeys("rufusnedum@gmail.com");
        Thread.sleep(3000);
        //input password Field
       driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234567890");
        Thread.sleep(3000);
       //Click on the Login
        driver.findElement(By.xpath("//button[@class='btn btn-block btn-primary']")).click();
        Thread.sleep(1000);
    }

  @Test
    public void logoutTest() throws InterruptedException {
        //click on the logout button
        driver.findElement(By.xpath("//a[normalize-space()='Logout']")).click();
        //printout response based on status
        if (driver.getCurrentUrl().contains("https://deliveryqs.com/login"))
            //Pass
            System.out.println("The Login page URL contains com/login");
        else
            //Fail
            System.out.println("The Login URL does not contain com/login");
    }

    @Test

    public void testNegativeLogin() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/section[1]/div/form/div[1]/div[1]/div/input")).sendKeys("rufusnedul@gmail.com");
       Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456789076");
        //Click on log in
        driver.findElement(By.xpath("//button[@class='btn btn-block btn-primary']")).click();
        //provide the error message for failed Login
        String expectedErrorMessage = "Incorrect email or password!";
        //Provide the actual message xpath
        String actualErrorMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-block']//strong")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

        // initiate the test run command
        public static  void main(String args[]) throws InterruptedException{
        Login test = new Login();
            test.setup();


        }
        @AfterTest
        public void closeBrowser() throws InterruptedException {
            Thread.sleep(10000);
            driver.quit();
        }


        }
