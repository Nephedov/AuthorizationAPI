package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @Test
    void positiveAuthorizationTest() {
        DataGenerator.UserInfo user = DataGenerator.Registration.generateUser("ru", "active");
        UserRegistration.regUser(user);
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("h2").shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Личный кабинет"));
    }

    @Test
    void nonExistentUserTest() {
        DataGenerator.UserInfo user = DataGenerator.Registration.generateUser("ru", "active");
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void invalidLoginFieldTest() {
        DataGenerator.UserInfo user = DataGenerator.Registration.generateUser("ru", "active");
        UserRegistration.regUser(user);
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue("login");
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void invalidPasswordFieldTest() {
        DataGenerator.UserInfo user = DataGenerator.Registration.generateUser("ru", "active");
        UserRegistration.regUser(user);
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue("password");
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void blockedUserTest() {
        DataGenerator.UserInfo user = DataGenerator.Registration.generateUser("ru", "blocked");
        UserRegistration.regUser(user);
        open("http://localhost:9999/");
        $("[data-test-id='login'] input").setValue(user.getLogin());
        $("[data-test-id='password'] input").setValue(user.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Ошибка! Пользователь заблокирован"));

    }
}
