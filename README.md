# Дипломный проект. Автоматизация тестирования мобильного приложения «Мобильный хоспис»

## Описание
В проекте реализованы UI-автотесты мобильного приложения «Мобильный хоспис».

Автотесты написаны на Java с использованием:
- JUnit4
- Espresso
- UI Automator
- Allure Kotlin
- Page Object Model

## Среда запуска
- ОС: Windows 11
- IDE: IntelliJ IDEA Community Edition 2025.2.3 / Android Studio
- JDK: 17
- Gradle: 7.6
- Эмулятор для итогового прогона: Pixel 4, Android 10 (API 29)

## Автотесты
В проекте реализованы следующие тестовые классы:
- `AuthorizationPositiveTest`
- `AuthorizationNegativeTest`
- `AboutScreenTest`
- `OurMissionTest`
- `NewsCrudTest`

Покрытые сценарии:
- успешная авторизация;
- негативная авторизация с пустыми полями;
- открытие экрана About;
- открытие раздела Our Mission и раскрытие первой цитаты;
- открытие раздела News и раскрытие новости;
- создание новости;
- редактирование новости.

## Подготовка к запуску
1. Открыть проект в IDE.
2. Убедиться, что используется JDK 17.
3. Убедиться, что Gradle wrapper настроен на совместимую версию проекта.
4. Запустить эмулятор **Pixel 4 API 29**.
5. Отключить анимации на эмуляторе командами:

```bash
adb shell settings put global window_animation_scale 0
adb shell settings put global transition_animation_scale 0
adb shell settings put global animator_duration_scale 0
```

## Запуск тестов из IDE
Тесты можно запускать:
- по одному тестовому классу;
- всем набором `androidTest`.

Рекомендуемый итоговый прогон:
- `AuthorizationPositiveTest`
- `AuthorizationNegativeTest`
- `AboutScreenTest`
- `OurMissionTest`
- `NewsCrudTest`

## Запуск тестов через Gradle
Из корня проекта:

```bash
./gradlew connectedDebugAndroidTest
```

Для Windows PowerShell:

```powershell
.\gradlew connectedDebugAndroidTest
```

## Allure
Результаты Allure для инструментальных Android-тестов сохраняются во внутренней файловой директории приложения, например в `/data/data/<package>/files/allure-results`. Официальный README `allure-kotlin` также рекомендует вытаскивать их через `adb exec-out run-as ... tar ...`. citeturn764078search0

Для данного проекта package приложения:

```text
ru.iteco.fmhandroid
```

Команда для выгрузки результатов:

```bash
adb exec-out run-as ru.iteco.fmhandroid sh -c 'cd /data/data/ru.iteco.fmhandroid/files && tar cf - allure-results' > allure-results.tar
```

Распаковка архива с результатами:

```bash
tar -xf allure-results.tar
```

После этого папку `allure-results` нужно упаковать в архив `allure-results.zip` и положить в корень репозитория.

## Структура автотестов
- `data` — тестовые данные;
- `helper` — вспомогательные классы;
- `screen` — Page Objects;
- `test` — тестовые классы по функциональным блокам.

## Примечание
Найденные дефекты оформлены в GitHub Issues.
