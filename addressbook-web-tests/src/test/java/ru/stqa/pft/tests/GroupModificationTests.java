package ru.stqa.pft.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod  // перед каждым тестом в классе будет проверка есть ли группа (с созданием, если групп нет)
  public void ensurePreConditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
    }
  }

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();

    //int before = app.getGroupHelper().getGroupCount();    // проверка количества групп до модификации
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-3;  // определяем номер элемента для модификации
    GroupData group = new GroupData(before.get(index).getId(),"newgroup6", "header6", "footer6");

    app.getGroupHelper().modifyGroup(group, index);

    // проверка количества групп после ввода новой
    //int after = app.getGroupHelper().getGroupCount();
    //Assert.assertEquals(after, before);
    List<GroupData> after = app.getGroupHelper().getGroupList();
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

    app.getNavigationHelper().gotoHome();
    //app.getSessionHelper().logout();
  }

}
