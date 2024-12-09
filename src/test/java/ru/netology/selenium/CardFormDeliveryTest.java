package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    //    options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");  // загрузка страницы

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldCheckPositiveCaseIfCorrectSymbols() throws InterruptedException {

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
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());
        Thread.sleep(5000);

    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasMinSymbolsAmount() throws InterruptedException {
                // 2 варианта
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("В");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());
        Thread.sleep(5000);
    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasManySymbols() throws InterruptedException {
        // 2 варианта
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Викторов-Петров-Склифосовский Андрей-Пьер-Веньямин Александрович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());
        Thread.sleep(5000);
    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasUppercaseSymbols() throws InterruptedException {
        // 2 варианта
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("РОМАНОВСКИЙ СЕРГЕЙ ПЕТРОВИЧ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());
        Thread.sleep(5000);
    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasLowercaseSymbols() throws InterruptedException {
        // 2 варианта
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("придорожный иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());
        Thread.sleep(5000);
    }

    @Test
    void shouldCheckNegativeCaseIfLatinSymbolIsAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ivanov Petr");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfApostrofIsAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Д'артаньян Ризо");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfSymbolIsAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов! Петр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfIncorrectRussianSymbolIsAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ёжик Туманный");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfArabianSymbolIsAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ивﺙн Малахов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfChineseSymbolIsAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("О描摹га Малая");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfNumbersAreAtFirstInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("5354 34233");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

    //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfEmptyFirstInputField() throws InterruptedException {
//      driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys(""); // 2й вариант
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        //    WebElement answer = driver.findElement(By.className("input__sub")); // 2й вариант (только для массива длиной 0)
        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id='name'] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Поле обязательно для заполнения", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
     void shouldCheckNegativeCaseIfAlphabeticSymbolHasSecondInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+791А0000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id=phone] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfNumbersWithoutSymbolPlusSecondInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна Козлова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id=phone] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfAmountNumbersUnderBoundaryAtSecondInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7910000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id=phone] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfAmountNumbersUpperBoundaryAtSecondInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7910000000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id=phone] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfEmptySecondInputField() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
    //     driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");  // 2й вариант
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id=phone] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Поле обязательно для заполнения", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfCheckboxOff() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78920000000");
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input[data-test-id=phone] span.input__inner span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());
    }

}
