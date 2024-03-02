package pages;

import data.communicatMetod.CommunicationMethod;
import data.cities.ICityData;
import data.english.EnglishLevelData;
import data.gender.Gender;
import data.work.Work;
import data.fieldData.InputFieldData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import data.workgraf.WorkGraf;

public class PersonalAccountOtus extends BasePage {
    public PersonalAccountOtus(WebDriver driver) {
        super(driver);
    }

    private String buttonSaveSelector = "[title='Сохранить и заполнить позже']";

    // Ожидания Элемента
    public PersonalAccountOtus personalWait() {
        WebElement personal = driver.findElement(By.xpath("//h3[contains(text(),'Персональные данные')]"));
        wait.until(ExpectedConditions.visibilityOf(personal));
        return this;
    }


    // Очистить поля "Имя"
    public PersonalAccountOtus addNewNameFieldsClear(InputFieldData... inputFieldData) {
        for (InputFieldData fieldData : InputFieldData.values()) {
            driver.findElement(By.cssSelector(String.format("input[name='%s']",
                    fieldData.getFname()))).clear();
        }
        return this;
    }

    //метод заполнения полей [Имя, имя латинице, фамилия, фамилия латинице]
    public PersonalAccountOtus addNewNameFields(InputFieldData inputFieldData, String data) {
        driver.findElement(By.cssSelector(String.format("input[name='%s']",
                        inputFieldData.getFname())))
                .sendKeys(data);
        System.out.println(data);
        return this;
    }


    // метод по заполнения страны и города
    public PersonalAccountOtus selectCountry(ICityData cityData) {
        WebElement russiaSelectElement = driver.findElement
                (By.cssSelector("[data-slave-selector='.js-lk-cv-dependent-slave-city']"));
        russiaSelectElement.click(); ///// кликнули по элементу страна
        //нашли элемент выпадающего списка
        WebElement cityListContainer = driver.findElement(By.xpath("//*[@name='country']//following::div[2]"));
        //ожидания видимости элемента список
        wait.until(ExpectedConditions.visibilityOf(cityListContainer));
        driver.findElement(By.cssSelector(String.format("[title='%s']", cityData.countriesData().getName()))).click();

        return this;

    }


    // метод по заполнению города
    public PersonalAccountOtus selectCity(ICityData cityData) {

        //проверка что при выборе страны пропадет нужный атрибут
        WebElement attribute = driver.findElement(By.cssSelector("[name='city']"));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(attribute, "disabled", "disabled")));
        // нашли поля город
        WebElement moscowSelectElement = driver.findElement(By.xpath("//*[@name='city']//following::div[1]"));
        // ожидания поля город
        wait.until(ExpectedConditions.elementToBeClickable(moscowSelectElement));
        //кликнуть по полю город
        moscowSelectElement.click();
        // найти элемент выпадающего списка
        WebElement listSelectElement = driver.findElement(By.xpath("//*[@name='city']//following::div[2]"));
        // ожидания видимости списка
        wait.until(ExpectedConditions.visibilityOf(listSelectElement));
        // Выбор города
        driver.findElement(By.cssSelector(String.format("[title='%s']", cityData.getName()))).click();

        return this;
    }


    // метод по заполнению уровня языка
    public PersonalAccountOtus selectLanguage(EnglishLevelData englishLevelData) {
        //Кликнуть на поле выбор английского языка
        WebElement languageSelect = driver.findElement(By.xpath("//*[@name='english_level']//following::div[1]"));
        languageSelect.click();
        //нашли элемент выпадающего списка
        WebElement listLanguage = driver.findElement(By.xpath("//*[@name='english_level']//following::div[2]"));
        // ожидания элемента выпадающего списка
        wait.until(ExpectedConditions.visibilityOf(languageSelect));
        // Выбор языка
        driver.findElement(By.cssSelector(String.format("[title='%s']", englishLevelData.getName()))).click();

        return this;
    }


    // метод выбрать чекбокс true false
    public PersonalAccountOtus readyToMove(boolean isSelected) {
        String relocate = isSelected ? "Да" : "НЕТ";
        driver.findElement(By.xpath(String.format("//span[@class='radio__label'and text()='%s']", relocate))).click();

        return this;
    }


    // метод выбрать чекбокс true false формат работы
    public PersonalAccountOtus workGraf(boolean isSelected, WorkGraf... workGrafs) {

        for (WorkGraf workGraf : workGrafs) {
            WebElement inputSelect = driver.findElement(By.cssSelector(String.format("input[title='%s']~span", workGraf.getName())));
            if (inputSelect.isSelected() != isSelected) {
                inputSelect.click();
            }
        }
        return this;
    }


    // метод на выбор способа связи
    public PersonalAccountOtus communicationMethod(CommunicationMethod communicationMethod) {
        WebElement communicatSelect = driver.findElement(By.xpath("//*[@name='contact-0-service']//following::div[1]"));
        communicatSelect.click();
        //ожидания выпадающего списка
        WebElement listCommunicat = driver.findElement(By.xpath("//*[@name='contact-0-service']//following::div[2]"));
        wait.until(ExpectedConditions.visibilityOf(listCommunicat));
        driver.findElement(By.cssSelector(String.format("[title='%s']", communicationMethod.getName()))).click();
        return this;
    }


    // выбор связи
    public PersonalAccountOtus connection() {
        WebElement connection = driver.findElement(By.xpath("//*[@id='id_contact-0-preferable']/.."));
        connection.click();
        return this;
    }


    //выбор пола
    public PersonalAccountOtus gender(Gender gender) {
        // находим поля
        WebElement selectInputGender = driver.findElement(By.cssSelector("[id='id_gender']"));
        // кликаем на поле
        selectInputGender.click();
        // Получаем список
        WebElement listGender = driver.findElement(By.cssSelector("[id='id_gender']"));
        //ожидания видимости списка
        wait.until(ExpectedConditions.visibilityOf(listGender));
        // выбор из списка пола
        driver.findElement(By.cssSelector(String.format("[value ='%s']", gender.getName()))).click();
        return this;

    }

    // очистка поля по работе
    public PersonalAccountOtus workClear(Work... work) {
        for (Work work1 : Work.values()) {
            driver.findElement(By.cssSelector(String.format("[name = '%s']", work1.getName()))).clear();
        }

        return this;
    }


    // ввод работы и должности
    public PersonalAccountOtus workFill(Work work, String data) {
        driver.findElement(By.cssSelector(String.format("[name = '%s']", work.getName()))).sendKeys(data);

        return this;
    }

    // Нажимаем на кнопку [Сохранить и заполнить позже]
    public PersonalAccountOtus buttonSaveСontinue() {
        // Нашли элемент кнопки
        WebElement buttonSave = driver.findElement(By.cssSelector("[title='Сохранить и заполнить позже']"));
        //Ожидания элемента кнопка
        wait.until(ExpectedConditions.visibilityOf(buttonSave));
        //кликнуть на кнопку
        buttonSave.click();
        return this;
    }

    //проверка элемента
    public PersonalAccountOtus elementPercentage() {
        Assertions.assertFalse(waitTools.waitNotElementPresent
                (By.cssSelector(buttonSaveSelector)), "The button is missing");
        return this;
    }

    // нажать на раздел "Персональные данные"
    public PersonalAccountOtus personalDataSection() {
        // найти элемент раздел персональные данные
        WebElement sectionPersonalData = driver.findElement(By.xpath("//*[@class='nav-sidebar']/descendant::a[1]"));
        // Ожидания видимости элемента
        wait.until(ExpectedConditions.visibilityOf(sectionPersonalData));
        //кликнуть на раздел
        sectionPersonalData.click();
        return this;
    }


    /**
     * -------------------- checkPersonalAccountOtus ---------------
    */
    // метод проверки поля [Имя, имя латинице, фамилия, фамилия латинице]
    public PersonalAccountOtus assertFieldsData(InputFieldData inputFieldData, String expectedData) {
        WebElement actualResult = driver.findElement(By.cssSelector(String.format("input[name='%s']",
                inputFieldData.getFname())));
        Assertions.assertEquals(expectedData, actualResult.getAttribute("value"));
        System.out.println("Поле  заполенно значением = " + expectedData);
        return this;
    }

    //метод проверки поля [Страны]
    public PersonalAccountOtus assertFieldsDataCountry(ICityData iCityData) {
        WebElement actualResult = driver.findElement(By.cssSelector("[name='country']~div"));
        Assertions.assertEquals(iCityData.countriesData().getName(), actualResult.getText());
        System.out.println("Поле  заполенно значением = " + iCityData.countriesData().getName());
        return this;
    }

    //метод проверки поля [Город]
    public PersonalAccountOtus assertFieldsDataCity(ICityData iCityData) {
        WebElement actualResult = driver.findElement(By.cssSelector("[name='city']~div"));
        Assertions.assertEquals(iCityData.getName(), actualResult.getText());
        System.out.println("Поле  заполенно значением = " + iCityData.getName());
        return this;
    }

    //метод проверки поля [Уровень английского]
    public PersonalAccountOtus assertFieldsDataLanguage(EnglishLevelData englishLevelData) {
        WebElement actualResult = driver.findElement(By.cssSelector("[name='english_level']~div"));
        Assertions.assertEquals(englishLevelData.getName(), actualResult.getText());
        System.out.println("Поле  заполенно значением = " + englishLevelData.getName());
        return this;
    }

    //метод проверки поля [Готов к переезду]
    public PersonalAccountOtus assertFieldsReadyToMove(boolean isSelected) {
        String relocate = isSelected ? "Да" : "НЕТ";
        WebElement actualResult = driver.findElement
                (By.xpath(String.format("//span[@class='radio__label'and text()='%s']", relocate)));
        Assertions.assertEquals(relocate, actualResult.getText());
        System.out.println("Поле  заполенно значением = " + isSelected);
        return this;
    }

    //метод проверки поля [Формат работы]
        public PersonalAccountOtus assertWorkGraf(boolean isSelected, WorkGraf... workGrafs) {

        for (WorkGraf workGraf : workGrafs) {
            WebElement inputSelect = driver.findElement(By.xpath(String.format("//*[@title='%s']/..", workGraf.getName())));
            Assertions.assertEquals(workGraf.getName(), inputSelect.getText());
            System.out.println("Поле  заполенно значением = " + workGraf.getName());
        }
        return this;

    }

    //метод проверки поля [Способ связи]
    public PersonalAccountOtus assertСommunicationMethod(CommunicationMethod communicationMethod) {
        WebElement actualResult = driver.findElement(By.cssSelector("[name='contact-0-service']"));
        Assertions.assertEquals(communicationMethod.getName(), actualResult.getAttribute("value"));
        System.out.println("Поле  заполенно значением = " + communicationMethod.getName());
        return this;
    }

    //метод проверки поля [Выбор пола]
    public PersonalAccountOtus assertGender(Gender gender) {
        WebElement actualResult = driver.findElement(By.cssSelector(String.format("[value ='%s']", gender.getName())));
        Assertions.assertEquals(gender.getName(), actualResult.getAttribute("value"));
        System.out.println("Поле  заполенно значением = " + gender.getName());
        return this;
    }

    public PersonalAccountOtus assertWorkFill(Work work, String expectedData) {
        WebElement actualResult = driver.findElement(By.cssSelector(String.format("input[name='%s']",
                work.getName())));
        Assertions.assertEquals(expectedData, actualResult.getAttribute("value"));
        System.out.println("Поле  заполенно значением = " + expectedData);
        return this;
    }

}
