package ru.stqa.pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod  // перед каждым тестом в классе будет проверка есть ли группа (с созданием, если групп нет)
  public void ensurePreConditions() {
    app.goTo().GroupPage();
    //if (! app.group().isThereAGroup()) {
    if (app.group().list().size()==0) {
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
  }

  @Test
  public void testGroupModification() {

    app.goTo().GroupPage();

    //int before = app.getGroupHelper().getGroupCount();    // проверка количества групп до модификации
    List<GroupData> before = app.group().list();
    int index = before.size()-3;  // определяем номер элемента для модификации
    GroupData group = new GroupData().withId(before.get(index).getId()).withName("test6").withHeader("header6").withFooter("footer6");

    app.group().modify(group, index);

    // проверка количества групп после ввода новой
    //int after = app.getGroupHelper().getGroupCount();
    //Assert.assertEquals(after, before);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    //т.к. после модификации порядок групп может измениться из-за сортировки по наименования, сравнивать надо неупорядоченные множества
    before.remove(index);
    before.add(group);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // это сравнение неупорядоченных списков
    //сравнение сортированных списков
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

    app.goTo().gotoHome();
    //app.getSessionHelper().logout();
  }

}
