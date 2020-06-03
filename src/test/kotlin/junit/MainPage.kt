package junit

import io.qameta.allure.Step
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.FindBys
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

class MainPage(private val driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBys(FindBy(tagName = "button"), FindBy(xpath = ".//*[text() = 'Создать кошелек']"))
    lateinit var signUpButton: WebElement

    @FindBy(xpath = ".//*[contains(@class, 'auth-form-self')][//*[contains(text(), 'СОЗДАЙТЕ СВОЙ КОШЕЛЕК')]]")
    lateinit var signUpPopup: WebElement;

    @Step("Открыть {url}")
    fun open(url: String) = driver.get(url)

    @Step("Проверка открытия {url}")
    fun verifyUrl(url: String) {
        WebDriverWait(driver, 1).until { it.currentUrl == url }
    }

//    @Step("Проверка текста на странице")
//    fun checkText(text: String) {
//        driver.pageSource.shouldContain(text)
//    }

    @Step("Закрыть браузер")
    fun closeBrowser() {
        driver.quit()
    }
}