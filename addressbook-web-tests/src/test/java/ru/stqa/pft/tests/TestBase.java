package ru.stqa.pft.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.appmanager.ApplicationManager;
import ru.stqa.pft.model.GroupData;
import ru.stqa.pft.model.Groups;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class); // создаем логгер

  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);  // переменная static будет доступна всем методам(тестам) в suite
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));  //

  @BeforeSuite  // аннотация suite позволяет запускать все тесты одного запуска(suite) в одном браузере
  public void setUp() throws Exception{
    app.init();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + "with parameters " + Arrays.asList(p));
  }

  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUI() {

    if (Boolean.getBoolean("verifyUI")) {
      Groups uiGroups = app.group().all();
      Groups dbGroups = app.db().groups();
      assertThat(uiGroups, equalTo(dbGroups
              .stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }



}


