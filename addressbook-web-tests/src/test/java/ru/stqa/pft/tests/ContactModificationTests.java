package ru.stqa.pft.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.model.ContactData;
import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreConditions() {
    app.goTo().gotoHome();
    //if (!app.contact().isThereAContact()) {
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData().withFirstname("Ivana").withLastname("Ivanova").withEmail("iva@tut.by").withGroup("newgroup777"));
    }
  }

  @Test (enabled = true)
  public void testContactModification() {
    List<ContactData> before = app.contact().list();  //выгружаем список контактов
    int index = before.size()-2;
    ContactData contact = new ContactData()
            .withId(before.get(index).getId()).withFirstname("Sam").withLastname("Sadko").withEmail("sam@tut.by");
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();  // выгружаем лист для сравнения после модификации
    Assert.assertEquals(after.size(), before.size()); // проверка, что записей осталось столько же
    before.remove(index);  // из списка ДО модификации удаляем запись, которую модифицируем в тесте
    before.add(contact);  // добавляем запись со значениями модификации (в том числе и id)
    Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());  // сортируем списки ДО и ПОСЛЕ по id и сравниваем их
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    //app.getSessionHelper().logout();
  }

}
