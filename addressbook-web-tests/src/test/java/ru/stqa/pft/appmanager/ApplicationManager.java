package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.time.Duration;

public class ApplicationManager {
//  FirefoxDriver wd = new FirefoxDriver();
  WebDriver wd;

  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private SessionHelper sessionHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    //String browser = BrowserType.FIREFOX;
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    wd.get("http://localhost/addressbook/");
    wd.manage().window().setSize(new Dimension(800, 640));
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
