package kotest

import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class TestMainPage : StringSpec() {

    private val driver: WebDriver = ChromeDriver()
    private val mainPage = MainPage(driver)
    private val pageUrl = "https://qiwi.com/"

    init {
        driver.manage()?.timeouts()?.implicitlyWait(2, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Должна открыться главная страница" {
            mainPage.run {
                open(pageUrl)
                verifyUrl(pageUrl)
            }
        }

        "Проверка текста футера" {
            mainPage.run {
                open(pageUrl)
                checkText("© 2020, КИВИ Банк (АО), лицензия ЦБ РФ № 2241")
            }
        }

        "Открытие поп-апа авторизации" {
            mainPage.run {
                open(pageUrl)
                signUpButton.click()
                signUpPopup.isDisplayed
                closeBrowser()
            }
        }
    }
}