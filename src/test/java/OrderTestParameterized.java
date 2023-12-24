import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.OrderPage1;
import pages.OrderPage2;
//Заказ самоката. Весь флоу позитивного сценария. Обрати внимание, что есть две точки входа в сценарий: кнопка «Заказать» вверху страницы и внизу.
//Из чего состоит позитивный сценарий:
//Нажать кнопку «Заказать». На странице две кнопки заказа.
//Заполнить форму заказа.
//Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
@RunWith(Parameterized.class)
public class OrderTestParameterized {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String comment;

    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage1 orderPage1;
    private OrderPage2 orderPage2;

    //Конструктор
    public OrderTestParameterized(String firstName, String lastName, String address, String phoneNumber, String deliveryDate, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Курт", "Кобейн", "Сиэтл", "79998887766", "28.12.2023", "With the lights out!"},
                {"Крист", "Новоселич", "Вашингтон", "79994445566", "31.12.2026", "Привет, меня зовут Крист!"},
                {"Дейв", "Грол", "Лос-Анджелес", "79998833766", "01.07.2024", "FooFighters"},
        };
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\WebDriver\\bin\\chromedriver.exe");
        driver = new ChromeDriver(); // Хром
        //System.setProperty("webdriver.gecko.driver", "D:\\Program Files\\WebDriver\\bin\\geckodriver.exe");
        //driver = new FirefoxDriver(); // Мозилла
        mainPage = new MainPage(driver);
        orderPage1 = new OrderPage1(driver);
        orderPage2 = new OrderPage2(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage.clickCookieButton();
        driver.manage().window().maximize();  //Расширение экрана

    }

    @Test

    public void testOrder1() {
        mainPage.clickOrderUpButton(); //Клик на верхнюю кнопку "Заказать" на главной странице

        orderPage1.fillOrderForm1(firstName, lastName, address, phoneNumber);  //Заполняется форма "Для кого самокат"
        orderPage1.nextButtonClick(); //Клик на кнопку "Далее"

        orderPage2.fillOrderForm2(deliveryDate, comment); // Заполняется форма "Про аренду"

        orderPage2.clickPlaceOrderButton(); // Клик на кнопку "Заказать"
        orderPage2.clickYesButton(); //Клик на кнопку "Да"
        //orderPage2.clickCheckStatusButton(); // Клик на кнопку "Оформить заказ", но пока её не трогаем.

        orderPage2.assertCheckStatusButtonVisible(); //Проверка, что кнопка "Оформить заказ" Появилась, значит тест пройден
    }

    @Test

    public void testOrder2() {
        mainPage.scrollToOrderDownButtonAndClick(); //Скролл и клик на нижнюю кнопку "Заказать" на главной странице

        orderPage1.fillOrderForm1(firstName, lastName, address, phoneNumber);  //Заполняется форма "Для кого самокат"
        orderPage1.nextButtonClick(); //Клик на кнопку "Далее"

        orderPage2.fillOrderForm2(deliveryDate, comment); // Заполняется форма "Про аренду"

        orderPage2.clickPlaceOrderButton(); // Клик на кнопку "Заказать"
        orderPage2.clickYesButton(); //Клик на кнопку "Да"
        //orderPage2.clickCheckStatusButton(); // Клик на кнопку "Оформить заказ", но пока её не трогаем.

        orderPage2.assertCheckStatusButtonVisible(); //Проверка, что кнопка "Оформить заказ" Появилась, значит тест пройден
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
