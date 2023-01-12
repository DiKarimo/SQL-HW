package ru.netology.pages;

import static com.codeborne.selenide.Condition.*;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeInput = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $("[data-test-id='error-notification']");

    public void verifyPage() {
        codeInput.shouldBe(visible);
    }

    public void verifyErrorNotification() {
        errorNotification.shouldBe(visible);
    }

    public void verify(String verificationCode) {
        codeInput.setValue(verificationCode);
        verifyButton.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }
}
