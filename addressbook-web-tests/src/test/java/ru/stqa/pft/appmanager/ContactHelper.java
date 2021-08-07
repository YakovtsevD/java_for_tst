package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    //click(By.cssSelector("input:nth-child(87)"));
    click(By.name("submit"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup()); //выбор элементов из выпадающено списка
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("ADD_NEW"));
  }

  public void closeAlert() {
    wd.switchTo().alert().accept();
  }

  public void deleteSelectedGroups() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModification() {
    click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));
  }

  public void createContact(ContactData contact, boolean b) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void returnToHomePage() {
    if (isElementPresent(By.id("maintable"))) {
      return;
    }
    click(By.linkText("home page"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
