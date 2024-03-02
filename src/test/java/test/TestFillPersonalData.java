package test;

import com.github.javafaker.Faker;
import data.communicatMetod.CommunicationMethod;
import components.DropDownList;
import data.cities.ICityData;
import data.cities.RussiaCityData;
import data.english.EnglishLevelData;
import data.gender.Gender;
import data.work.Work;
import factory.DriverFactory;
import data.fieldData.InputFieldData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.MainPageOtus;
import pages.PersonalAccountOtus;
import data.workgraf.WorkGraf;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestFillPersonalData {

       private WebDriver driver;
       private MainPageOtus mainPageOtus;
       private PersonalAccountOtus personalAccountOtus;

       private DropDownList dropDownList;
       Faker faker = new Faker();


       @BeforeEach
       public void beforeEach() {
           this.driver = new DriverFactory().create();

       }

       @org.junit.jupiter.api.Test
       public void testOtusAuthorization() {
           ICityData[] cityData = RussiaCityData.values();
           ICityData city = faker.options().nextElement(cityData);


             new  MainPageOtus(driver).goTo() //вход на сайт
                                      .thereIsNotPopupElement() //Проверка что PopupElement нету, - "Войдите в свой акаунт"
                                      .implicitlyWaitButton() // ожидания элемента кнопка "Войти"
                                      .clickElementEnter() // клик по элементу кнопки "Войти"
                                      .untilWait() // ожидания попапа
                                      .popupElementDisplayed() //Проверка что PopupElement есть - "Войдите в свой акаунт"
                                      .authorizationEmail() // ввод Email
                                      .authorizationPassword() // ввод Password
                                      .clickElementEnterEnter() // клик по кнопки войти
                                      .thereIsNoButton() //Проверка что нет кнопки "Войти"
                                      .getCookies(); // вывод в лог куков

              new DropDownList(driver).implicitlyWaitButtonName() //Ожидание элемента Авторизованного имени
                                      .clickButtonName() // Клик по элементу "Авторизованного имени"
                                      .implicitlyWaitButtonMyProfile() //Ожидание элемента "Мой профиль"
                                      .clickButtonMyProfile(); //Кликнуть по элементу "Мой профиль"

              String firstName = faker.name().firstName();
              String firstNameLatin = faker.name().firstName();
              String lastName = faker.name().lastName();
              String lastNameLatin = faker.name().lastName();
              String blogName = faker.name().firstName();
              String dateOfBrith = faker.date().birthday().toInstant().
                      atZone(ZoneId.systemDefault()).
                      toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
              String companyName = faker.name().lastName();
              String workName = faker.name().lastName();

           new PersonalAccountOtus(driver).personalWait()
                                             .addNewNameFieldsClear(InputFieldData.FNAME)
                                             .addNewNameFields(InputFieldData.FNAME,firstName)
                                             .addNewNameFields(InputFieldData.FNAMELATIN,firstNameLatin)
                                             .addNewNameFields(InputFieldData.LNAME,lastName)
                                             .addNewNameFields(InputFieldData.LNAMELATIN,lastNameLatin)
                                             .addNewNameFields(InputFieldData.BLOGNAME,blogName)
                                             .addNewNameFields(InputFieldData.DATEOFBRITH,dateOfBrith)
                      .selectCountry(RussiaCityData.MOSSCOW) // заполнения поля странна
                      .selectCity(RussiaCityData.MOSSCOW) // заполнения поля город
                      .selectLanguage(EnglishLevelData.BEGINNER) //знания английского
                      .readyToMove(true) // Готовность к переезду
                      .workGraf(true, WorkGraf.REMOTELY) // выбор форма работы
                      .communicationMethod(CommunicationMethod.TELEGRAM)
                      .connection() // кликнтуь на выбор связи
                      .gender(Gender.MALE) // выбор пола
                      .workClear(Work.COMPANY,Work.WORK) // заполнения поля компания
                      .workFill(Work.COMPANY,companyName) // заполнения поля должность
                      .workFill(Work.WORK,workName)
                      .buttonSaveСontinue()//кликнуть по кнопки [Сохранить и заполнить позже]
                      .elementPercentage() //проверка элемента
                      .personalDataSection()//нажать на раздел "Персональные данные"
                   /**
                      -- Проверка заполенных значений--
                   */
                      .assertFieldsData(InputFieldData.FNAME,firstName) // проверка имени
                      .assertFieldsData(InputFieldData.FNAMELATIN,firstNameLatin)// проверка имени на латинском
                      .assertFieldsData(InputFieldData.LNAME,lastName) // проверка Фамилии
                      .assertFieldsData(InputFieldData.LNAMELATIN,lastNameLatin) // проверка Фамилии на Латинице
                      .assertFieldsData(InputFieldData.BLOGNAME,blogName) //проверка имени в блоге
                      .assertFieldsData(InputFieldData.DATEOFBRITH,dateOfBrith) //проверка даты рождения
                      .assertFieldsDataCountry(RussiaCityData.MOSSCOW) //проверка значения в поле страна
                      .assertFieldsDataCity(RussiaCityData.MOSSCOW) //проверка значения в поле город
                      .assertFieldsDataLanguage(EnglishLevelData.BEGINNER) //проверка значения в поле уровень языка
                      .assertFieldsReadyToMove(true)// проверка поля готовность к переезду
                      .assertWorkGraf(true,WorkGraf.REMOTELY) // проверка поля Формат работы
                      //.assertСommunicationMethod(CommunicationMethod.TELEGRAM) //метод проверки поля [Способ связи]
                      .assertGender(Gender.MALE) // проверка поля пол
                      .assertWorkFill(Work.COMPANY,companyName) // проверка поля компания
                      .assertWorkFill(Work.WORK,workName); // проверка поля должность
       }

       @AfterEach
       public void finish() {
              if (driver != null){
                  driver.quit();
              }
       }


}

