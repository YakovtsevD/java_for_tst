package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  //private FirefoxDriver wd = new FirefoxDriver();
  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}
