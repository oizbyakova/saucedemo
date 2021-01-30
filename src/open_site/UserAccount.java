package open_site;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAccount {
    WebDriver driver;

    @BeforeMethod
    public void SetUp () {

            System.setProperty("webdriver.chrome.driver", "C:\\dev\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get("http://saucedemo.com");
            //Enter account
                driver.findElement(By.id("user-name")).sendKeys("standard_user");
                driver.findElement(By.id("password")).sendKeys("secret_sauce");
                driver.findElement(By.id("login-button")).click();
            }

            @Test
            public void Test1 () throws InterruptedException {
            //Add to cart
            new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText("Name (Z to A)");
            driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[2]/div[3]/button")).click();
            driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[1]/div[3]/button")).click();
            driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[4]/div[3]/button")).click();
            driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[4]/div[3]/button")).click();
            //Cart
            driver.findElement(By.id("shopping_cart_container")).click();
            driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[1]")).click();
            driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[1]/div[3]/button")).click();
            driver.findElement(By.xpath("//*[@id='inventory_container']/div/div[4]/div[3]/button")).click();
            driver.findElement(By.id("shopping_cart_container")).click();
            driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[1]/div[5]/div[2]/div[2]/button")).click();
            //Checkout
            driver.findElement(By.xpath("//*[@id='cart_contents_container']/div/div[2]/a[2]")).click();
            driver.findElement(By.id("first-name")).sendKeys("John");
            driver.findElement(By.id("last-name")).sendKeys("Brown");
            driver.findElement(By.id("postal-code")).sendKeys("20950");
            driver.findElement(By.xpath("//*[@id='checkout_info_container']/div/form/div[2]/input")).click();
            driver.findElement(By.cssSelector("#checkout_summary_container > div > div.summary_info > div.cart_footer > a.btn_action.cart_button")).click();
                Assert.assertEquals("THANK YOU FOR YOUR ORDER", driver.findElement
                        (By.xpath("/html/body/div/div[2]/div[3]/h2")).getText());
        }

        @Test
        public void Test2 ()  {
            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[1]/div[3]/button")).click();
            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[3]/div[3]/button")).click();
            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div/div[5]/div[3]/button")).click();
            Assert.assertEquals (driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/a/span")).getText(),
                    "3");
        }
        @AfterMethod
    public void AfterTest(){
        driver.quit();
    }
}