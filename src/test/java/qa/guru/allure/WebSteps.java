package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Открытие главной страницы")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Поиск репозитория {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Открытие репозитория {repo}")
    public void openRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открытие вкладки Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверка наличия Issue под номером {issue}")
    public void shouldSeeIssueWithNumber(int issue) {
        $("#issue_" + issue + "_link").should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] attachScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}

