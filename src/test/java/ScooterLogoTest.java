import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;
import page.OrderPage1;

//Проверить: если нажать на логотип «Самоката», попадёшь на главную страницу «Самоката».
public class ScooterLogoTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage1 orderPage1;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\WebDriver\\bin\\chromedriver.exe");
        driver = new ChromeDriver(); // Хром
        //System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\WebDriver\\bin\\chromedriver.exe");
        //driver = new FirefoxDriver();
        mainPage = new MainPage(driver);
        orderPage1 = new OrderPage1(driver);
        //Открываем сайт
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //Клик на куки
        mainPage.clickCookieButton();
        //Клик на верхнюю кнопку "Заказать"
        mainPage.clickOrderUpButton();
    }

    @Test
    public void testScooterUrl() {

        mainPage.clickScooterLogo(); // Нажимаем на логотип Самоката

        // Проверяем, что текущий URL соответствует ожидаемому URL
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }

    @After
    public void teardown() {
        driver.quit();
    }

}
