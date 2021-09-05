package ru.stqa.pft.tests;// Generated by Selenium IDE

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.model.ContactData;
import ru.stqa.pft.model.Contacts;
import ru.stqa.pft.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsCsv() throws IOException {
    //csv
    List<Object[]> list = new ArrayList<Object[]>();
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/recourses/contact.csv")))) {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsXml() throws IOException {
    //xml
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/recourses/contact.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsJson() throws IOException {
    //json
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/recourses/contact.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {}.getType());
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @Test (dataProvider = "validContactsCsv")
  public void testContactCreation(ContactData contact) {
    app.goTo().gotoHome();
    Contacts before = app.contact().all();  //выгружаем список контактов ДО
    //File photo = new File("src/test/recourses/stru.png");
    //ContactData contact = new ContactData().withFirstname("Aon").withLastname("Aarobensson").withEmail("aon@tut.by").withPhoto(photo); // создаем объекти контакта который добавляем
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()+1)); // сравниваем количество записей (count) до загрузки списка повторно, ловим ошибку раньше
    Contacts after = app.contact().all();  // выгружаем лист для сравнения после создания
    //assertThat(after.size(), equalTo(before.size()+1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    //app.getSessionHelper().logout();
  }

  @Test (enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/recourses/stru.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test (enabled = false)
  public void testBadContactCreation() {

    app.goTo().gotoHome();
    Contacts before = app.contact().all();  //выгружаем список контактов ДО
    ContactData contact = new ContactData().withFirstname("Ben").withLastname("Bensson'").withEmail("ben@tut.by").withGroup("newgroup777"); // создаем объекти контакта который добавляем
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size())); // сравниваем количество записей (count) до загрузки списка повторно, ловим ошибку раньше
    Contacts after = app.contact().all();  // выгружаем лист для сравнения после создания
    //assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
    //app.getSessionHelper().logout();
  }


}

/*
  String[] langs = {"Java","Ci","Python","PHP","Go","CiSharp","Delfi","Fortran",
          "Assembler","Basic","Pascal","Perl","JavaScript","Ruby","Swift","Kotlin","Sql","Vala","Groovy","Ada"};

    for (String l : langs) {
    ContactData contact = new ContactData().withFirstname("Ben").withLastname(l).withEmail(l+"@tut.by").withGroup("newgroup777"); // создаем объекти контакта который добавляем
    app.contact().create(contact);
  }
*/
