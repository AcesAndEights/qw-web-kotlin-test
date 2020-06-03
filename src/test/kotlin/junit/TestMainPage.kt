package junit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

@DisplayName("Проверки главной страницы")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestMainPage {

    private val driver: WebDriver = ChromeDriver()
    private val mainPage = MainPage(driver)
    private val pageUrl = "https://qiwi.com/"

    @BeforeEach
    fun onStart() {
        driver.manage()?.timeouts()?.implicitlyWait(2, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()
    }

    @Test
    @Order(1)
    @DisplayName("Открытие главной страницы")
    fun testOpenPage() {
        mainPage.open(pageUrl)
        mainPage.verifyUrl(pageUrl)
    }

    @Test
    @Order(2)
    @DisplayName("Открытие поп-апа регистрации")
    fun testOpenSignUpPopup() {
        mainPage.open(pageUrl)
        mainPage.signUpButton.click()
        assertThat(mainPage.signUpPopup.isDisplayed)
    }

    @AfterAll
    fun quit() {
        driver.close()
    }
}