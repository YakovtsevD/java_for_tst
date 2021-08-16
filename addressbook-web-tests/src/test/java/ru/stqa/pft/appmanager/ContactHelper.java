package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.model.ContactData;
import java.util.ArrayList;
import java.util.List;


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

  public void deleteSelectedContacts() {
    click(By.cssSelector(".left:nth-child(8) > input"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("td:nth-child(8)")).get(index).click();

    //click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));

  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    returnToHomePage();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);  // модифицируем предпоследний по порядку
    fillContactForm(contact, false);
    submitContactModification(); // подтвердили модификацию
    returnToHomePage(); // на страницу home
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContacts();
    //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    closeAlert();
    gotoHome();
  }

  public void returnToHomePage() {
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

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry")); //найти все элементы с тэгом span и классом group
    for (WebElement element : elements) {
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

}
