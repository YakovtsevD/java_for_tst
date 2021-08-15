package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }

  public void gotoHome() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("HOME"));
  }

  public void gotoGroupPage() {

    if (isElementPresent(By.tagName("h1"))   //если есть тэг заголовок h1
            //&& wd.findElement(By.tagName("h1")).getText().equals("GROUPS")   //и если есть тэг заголовок h1 с названием GROUPS
            //&& wd.findElement(By.cssSelector("#content")).getText().equals("GROUPS")   //и если есть тэг заголовок h1 с названием GROUPS
            && isElementPresent(By.name("new")))   //и если есть элемент new
            //&& isElementPresent(By.name("delete"))
            //&& isElementPresent(By.name("edit")))   //и если есть элемент new
    {
      return;
    }
    click(By.linkText("GROUPS"));
  }
}

