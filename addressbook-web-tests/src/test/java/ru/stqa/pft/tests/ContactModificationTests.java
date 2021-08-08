package ru.stqa.pft.tests;// Generated by Selenium IDE

import org.testng.annotations.Test;
import ru.stqa.pft.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivana", "Ivanova", "iva@tut.by", "newgroup777"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Ivanko", "Ivanovsky", "ivanko@tut.by", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }

}