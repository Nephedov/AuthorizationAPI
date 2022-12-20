package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateLogin(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }

    public static String generatePassword(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale, String status) {
            val user = new UserInfo(generateLogin(locale), generatePassword(locale), status);
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String login;
        String password;
        String status;
    }
}
