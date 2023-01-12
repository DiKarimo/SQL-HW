package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {
    }

    private static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String generateRandomLog() {

        return faker.name().username();
    }

    public static String generateRandomPass() {

        return faker.internet().password();
    }

    public static AuthInfo generateRandomUser() {

        return new AuthInfo(generateRandomLog(), generateRandomPass());
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

}
