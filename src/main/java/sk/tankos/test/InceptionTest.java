package sk.tankos.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InceptionTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/inception.php");
    }

    @Test
    public void testDeeper() throws InterruptedException {
        String parrentWindow = driver.getWindowHandle();
        driver.findElement(By.id("letsGoDeeper")).click();
        new WebDriverWait(driver,5)
                .until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        driver.findElement(By.xpath("//input[1]")).sendKeys("Dusan Cinkota");
        Thread.sleep(3000);

        driver.close();
        driver.switchTo().window(parrentWindow);
        driver.findElement(By.id("letsGoDeeper")).click();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
