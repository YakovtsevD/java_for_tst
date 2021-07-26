package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.model.GroupData;

import java.time.Duration;

public class ApplicationManager {
  FirefoxDriver wd;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    wd.get("http://localhost/addressbook/");
    wd.manage().window().setSize(new Dimension(800, 640));
    login("admin", "secret");
  }

  public void login(String username, String password) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.cssSelector("input:nth-child(7)")).click();
  }

  public void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void gotoHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  public void submitGroupCreation() {
    wd.findElement(By.cssSelector("form:nth-child(2)")).click();
    wd.findElement(By.name("submit")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  public void stop() {
    wd.quit();
  }

  public void deleteSelectedGroups() {
    wd.findElement(By.cssSelector("input:nth-child(8)")).click();
  }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
}
