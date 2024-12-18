package ru.netology.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
    void shouldCheckPositiveCaseIfCorrectSymbols() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Белодуб Юлия");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasMinSymbolsAmount() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("В");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasManySymbols() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Викторов-Петров-Склифосовский Андрей-Пьер-Веньямин Александрович");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasUppercaseSymbols() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("РОМАНОВСКИЙ СЕРГЕЙ ПЕТРОВИЧ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckPositiveCaseIfFirstInputFieldHasLowercaseSymbols() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("придорожный иван");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector("[data-test-id='order-success']"));
        String actualAnswer = answer.getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfLatinSymbolIsAtFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ivanov Petr");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfApostrofIsAtFirstInputField() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Д'артаньян Ризо");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());
    }

    @Test
    void shouldCheckNegativeCaseIfSymbolIsAtFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Иванов! Петр");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfIncorrectRussianSymbolIsAtFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ёжик Туманный");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfArabianSymbolIsAtFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Ивﺙн Малахов");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfChineseSymbolIsAtFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("О描摹га Малая");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfNumbersAreAtFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("5354 34233");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfEmptyFirstInputField() {

        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id='name'] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Поле обязательно для заполнения", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

    @Test
     void shouldCheckNegativeCaseIfAlphabeticSymbolHasSecondInputField() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+791А0000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfNumbersWithoutSymbolPlusSecondInputField() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Анна Козлова");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("79100000000");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.className("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfAmountNumbersUnderBoundaryAtSecondInputField() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7910000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfAmountNumbersUpperBoundaryAtSecondInputField() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7910000000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfEmptySecondInputField() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub"));
        String actualAnswer = answer.getText().trim();

        assertEquals("Поле обязательно для заполнения", actualAnswer);
        Assertions.assertTrue(answer.isDisplayed());

    }

    @Test
    void shouldCheckNegativeCaseIfCheckboxOff() {

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Римская Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78920000000");
        driver.findElement(By.cssSelector("button")).click();

        WebElement answer = driver.findElement(By.cssSelector(".input_invalid[data-test-id=agreement]"));
        String actualAnswer = answer.getText().trim();

        Assertions.assertEquals("Поле обязательно для заполнения", actualAnswer);
        assertTrue(answer.isDisplayed());

    }

}
