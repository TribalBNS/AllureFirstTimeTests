package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static qa.guru.allure.TestData.ISSUE;
import static qa.guru.allure.TestData.REPOSITORY;

public class StepsTest {

    @Test
    public void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открытие главной страницы", () -> open("https://github.com/"));
        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Открытие репозитория", () -> $(linkText(REPOSITORY)).click());
        step("Открытие вкладки Issues", () -> $("#issues-tab").click());
        step("Проверка наличия Issue под номером " + ISSUE, () -> {
            $("#issue_" + ISSUE + "_link").should(Condition.exist);
        });
    }

    @Test
    @Feature("Issue в репозитории")
    @Story("Проверка созданного Issue")
    @Owner("TribalBNS")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "TestedURL", url = "https://github.com/")
    @DisplayName("Проверка наличия Issue №" + ISSUE + " в репозитории " + REPOSITORY)
    public void annotatedStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepository(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
        steps.attachScreenshot();

    }
}
