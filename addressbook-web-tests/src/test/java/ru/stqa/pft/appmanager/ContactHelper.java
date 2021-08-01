package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.cssSelector("input:nth-child(87)"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("email"), contactData.getEmail());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedGroups() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }

  public void selectContact() {
    click(By.id("9"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModification() {
    click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));
  }

}
