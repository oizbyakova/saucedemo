package open_site;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class Lists {
    WebDriver driver;

    @BeforeMethod
    public void SetUp () {

        System.setProperty("webdriver.chrome.driver", "C:\\dev\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("http://saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    public void Test1 () {
        new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText("Price (low to high)");

        List<WebElement> items = driver.findElements(By.className("inventory_item_price"));
        List<String> price = items.stream().map(WebElement::getText).collect(Collectors.toList());
        price = price.stream().map(s -> s.replace("$", ""))
                .collect(Collectors.toList());
        List <Double> int_price = price.stream().map(s -> Double.parseDouble(s))
                .collect(Collectors.toList());

        Assert.assertTrue(Ordering.natural().isOrdered(int_price));
    }

    @Test
    public void Test2() {
        new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText("Price (high to low)");

        List<WebElement> products = driver.findElements(By.className("inventory_item_price"));
        List<String> goods = products.stream().map(WebElement::getText).collect(Collectors.toList());
        goods = goods.stream().map(s -> s.replace("$", ""))
                .collect(Collectors.toList());
        List <Double> double_price = goods.stream().map(s -> Double.parseDouble(s))
                .collect(Collectors.toList());

        Assert.assertTrue(Ordering.natural().reverse().isOrdered(double_price));
    }

    @Test
    public void Test3() {
        new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText("Name (A to Z)");

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> goods = products.stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertTrue(Ordering.natural().isOrdered(goods));
    }

    @Test
    public void Test4() {
       new Select(driver.findElement(By.className("product_sort_container"))).selectByVisibleText("Name (Z to A)");

        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> goods = products.stream().map(WebElement::getText).collect(Collectors.toList());

        Assert.assertTrue(Ordering.natural().reverse().isOrdered(goods));
    }

    @AfterMethod
    public void AfterTest(){
        driver.quit();
    }
}

