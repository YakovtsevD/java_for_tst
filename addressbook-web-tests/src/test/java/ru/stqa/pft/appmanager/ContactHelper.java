package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.model.ContactData;
import ru.stqa.pft.model.Contacts;

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

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void initContactModification(int index) {
    wd.findElements(By.cssSelector("td:nth-child(8)")).get(index).click();
   //click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));
  }

  private void initContactModificationById(int id) {
    //1 т.к. ссылка href содержит идентификатор, ищем по ней
    //String sel = "edit.php?id="+id;
    //wd.findElement(By.cssSelector("a[href='"+sel+"']")).click();
    //2 то же самое одной строкой
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    /*
    //3 в функции selectContactById ищем по тэгу input значение value, от него на 2 уровня вверх, находим 8ую колонку td(8) и в ней анкер a
    wd.findElement(By.xpath(String.format("//input[@value='&s']/../../td(8)/a", id))).click();
    //4 ищем внутри строки tr подзапросом тэг input параметром value равным id(передан в функцию)
    wd.findElement(By.xpath(String.format("//tr[.//input[@value='&s']]/td(8)/a", id))).click();
    //5 последовательный поиск в несколько строк для примера
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id))); //нашли чекбокс
    WebElement row = checkbox.findElement(By.xpath("./../..")); // переходим на нужный уровень - на строку
    List<WebElement> cells = row.findElements(By.tagName("td")); // загружаем все колонки(td) строки
    cells.get(7).findElement(By.tagName("a")).click(); // кликаем элемент a
    */
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());  // модифицируем предпоследний по порядку
    fillContactForm(contact, false);
    submitContactModification(); // подтвердили модификацию
    contactCache = null;
    returnToHomePage(); // на страницу home
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContacts();
    //assertThat(driver.switchTo().alert().getText(), is("Delete 1 addresses?"));
    closeAlert();
    gotoHome();
  }

  public void delete(ContactData deletedContact) {
    selectContactById(deletedContact.getId());
    deleteSelectedContacts();
    contactCache = null;
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

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
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

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    //Contacts contacts = new Contacts();
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry")); //найти все элементы с тэгом span и классом group
    for (WebElement element : elements) {

      /*
      //моя реализация
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
      String[] phones = allPhones.split("\n"); // строку "все телефоны" порезали по признаку перевода строки на фрагменты и загнали в массив
      */

      // реализация из курса
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      String[] phones = cells.get(5).getText().split("\n"); // строку "все телефоны" порезали по признаку перевода строки на фрагменты и загнали в массив

      contactCache.add(new ContactData()
              .withId(id).withFirstname(firstname).withLastname(lastname)
              .withHomePhone(phones[0]).withMobilePhone(phones[1]).withWorkPhone(phones[2]));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);

  }

  /*
  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry")); //найти все элементы с тэгом span и классом group
    for (WebElement element : elements) {
      String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

 */

}
