package open_site;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;

public class Login {
    WebDriver driver;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\chromedriver_win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("http://saucedemo.com");
    }

    @Test(dataProvider = "loginDataProvider")
    public void loginTest(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        String act_result1 = "https://www.saucedemo.com/";
        String act_result2 = "https://www.saucedemo.com/inventory.html";
        String error_message1 = "Epic sadface: Sorry, this user has been locked out.";
        String error_message2 = "Epic sadface: Username and password do not match any user in this service";
        String error_message3 = "Epic sadface: Username is required";
        String error_message4 = "Epic sadface: Password is required";
        String exp_result = driver.getCurrentUrl();

        if (exp_result.equals(act_result2)) {
            Assert.assertEquals(act_result2, exp_result);
        } else {
            if (driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/h3")).getText().
                    equals(error_message1)) {
                Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/form/h3")).getText(),
                        error_message1);
            }
                else if (driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/h3")).getText().
                        equals(error_message2)) {
                    Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/form/h3")).getText(),
                            error_message2);
            }
            else if (driver.findElement(By.xpath("//*[@id=\'login_button_container\']/div/form/h3")).getText().
                    equals(error_message3)) {
                Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/form/h3")).getText(),
                        error_message3);
            }
                else {
                Assert.assertEquals(driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/form/h3")).getText(),
                        error_message4);
            }
        }
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] getLoginProviderData() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();

        FileReader reader = new FileReader("test_data\\test_data.json");
        JSONArray usersList = (JSONArray) jsonParser.parse(reader);

        Object[][] accounts = new Object[usersList.size()][2];

        for (int i = 0; i < usersList.size(); i++) {
            JSONObject user = (JSONObject) usersList.get(i);

            accounts[i][0] = user.get("username");
            accounts[i][1] = user.get("password");
        }

        return accounts;
    }
}




