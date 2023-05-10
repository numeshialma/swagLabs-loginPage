import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test1 {

    WebDriver driver;

    @DataProvider(name= "data-set")
    public static Object[][] DataSet(){
        Object[][] obj={
                {"valid","standard_user","secret_sauce"},
                {"valid","problem_user","secret_sauce"},
                {"valid","performance_glitch_user","secret_sauce"},
                {"invalid","standarduser","secret_sauce"},
                {"invalid","standarduser","123"},
                {"invalid","",""}
        };
        return obj;
    }

    @Test(dataProvider = "data-set")
    public void DataProvTest(String type,String username,String password ) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        System.out.println(type+ " " +username+ " " +password);
        driver=new ChromeDriver();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));



        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

        if(type.equals("valid")){
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("[class='title']")));
            Thread.sleep(3000);
        }else{
            wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("[data-test='error']")));
        }
        Thread.sleep(3000);
        driver.quit();

    }

}
