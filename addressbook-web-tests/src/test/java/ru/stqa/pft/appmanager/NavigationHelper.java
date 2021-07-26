package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {

  private FirefoxDriver wd = new FirefoxDriver();
  public NavigationHelper(FirefoxDriver wd) {
    this.wd = wd;
  }


  public void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
