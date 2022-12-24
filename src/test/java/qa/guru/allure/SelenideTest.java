package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static qa.guru.allure.TestData.ISSUE;
import static qa.guru.allure.TestData.REPOSITORY;

public class SelenideTest {

    @Test
    public void SearchIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();
        $(linkText("TribalBNS/AllureFirstTimeTests")).click();
        $("#issues-tab").click();
        $("#issue_" + ISSUE + "_link").should(Condition.exist);
    }
}
