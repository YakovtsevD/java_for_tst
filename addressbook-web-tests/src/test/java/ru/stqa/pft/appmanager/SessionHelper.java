package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver wd) {
    super(wd);
    //this.wd = wd;
  }

  public void login(String username, String password) {
    type(By.name("user"),username);
    type(By.name("pass"),password);
    click(By.cssSelector("input:nth-child(7)"));
  }

  public void logout() {
    click(By.linkText("LOGOUT"));
  }

}
