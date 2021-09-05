package ru.stqa.pft.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.appmanager.ApplicationManager;

public class TestBase {

  //protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);  // переменная static будет доступна всем методам(тестам) в suite
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));  //

  @BeforeSuite  // аннотация suite позволяет запускать все тесты одного запуска(suite) в одном браузере
  public void setUp() throws Exception{
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

}
