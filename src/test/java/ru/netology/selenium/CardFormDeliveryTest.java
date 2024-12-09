package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CardFormDeliveryTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() { WebDriverManager.chromedriver().setup(); }

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");  // загрузка страницы

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCheck() throws InterruptedException {

        // поиск элементов и взаимодействие с ними, 1й вариант:
    /*  WebElement form = driver.findElement(By.cssSelector("#root"));
        form.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Белодуб Юлия");
        form.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        form.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        form.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());  */

        // 2й вариант:
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Белодуб Юлия");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
        Thread.sleep(5000);

    }



}
