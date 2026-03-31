package ru.iteco.fmhandroid.data;

public final class TestData {
    public static final String VALID_LOGIN = "login2";
    public static final String VALID_PASSWORD = "password2";
    public static final String INVALID_LOGIN = "wrongLogin";
    public static final String INVALID_PASSWORD = "wrongPassword";
    public static final String NEWS_CATEGORY_ADVERTISEMENT = "Объявление";
    public static final String NEWS_CATEGORY_GRATITUDE = "Благодарность";

    private TestData() {
    }

    public static String uniqueTitle(String prefix) {
        return prefix + "_" + System.currentTimeMillis();
    }

    public static String uniqueDescription(String prefix) {
        return prefix + "_" + System.currentTimeMillis();
    }
}
