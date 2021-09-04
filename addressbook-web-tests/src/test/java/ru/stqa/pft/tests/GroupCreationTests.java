package ru.stqa.pft.tests;// Generated by Selenium IDE

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;
import ru.stqa.pft.model.Groups;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    /*
    //csv
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/recourses/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
    */

    //xml
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/recourses/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }

  @Test (dataProvider = "validGroups")
  //public void testGroupCreation(String name, String header, String footer) {
  public void testGroupCreation(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all(); // список групп до создания новой
    //GroupData group = new GroupData().withName(name).withHeader(header).withFooter(footer);
    app.group().create(group); // создание группы
    Groups after = app.group().all(); // список групп после создания новой
    assertThat(after.size(), equalTo(before.size()+1)); // сравнение количества до и после вариант 2
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    //app.goTo().gotoHome();
  }

  @Test (enabled = false)
  public void testBadGroupCreation() {
    app.goTo().groupPage();

    Groups before = app.group().all(); // список групп до создания новой
    GroupData group = new GroupData().withName("newgroup'505").withHeader("header505").withFooter("foottter505"); // в названии группы '
    app.group().create(group); // создание группы
    assertThat(app.group().count(), equalTo(before.size())); // хэширование, вместо загрузки полного списка after
    // просто считаем количество и сравниваем с before (который в кэше), т.е. можем быстрее найти ошибку количества записей
    Groups after = app.group().all(); // список групп после создания новой
    //assertThat(after.size(), equalTo(before.size())); // сравнение количества до и после вариант 2
    assertThat(after, equalTo(before));

    app.goTo().gotoHome();
  }




  //Set<GroupData> before = app.group().all(); // список групп до создания новой
    /*
    for (int i = 404; i < 444; i++) {
      GroupData group = new GroupData().withName("newgroup"+i).withHeader("header"+i).withFooter("foottter"+i);
      app.group().create(group); // создание группы
    }
    */
  //Set<GroupData> after = app.group().all(); // список групп после создания новой
  //assertEquals(after.size(), before.size()+1); // сравнение количества до и после вариант 1

  //group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
  //group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
  //before.add(group);
  //assertEquals(before, after); // сравнение множеств до и после вариант 1
  //app.getSessionHelper().logout();





}
