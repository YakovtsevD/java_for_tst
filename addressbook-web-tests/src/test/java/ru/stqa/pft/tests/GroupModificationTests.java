package ru.stqa.pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
    }
    //int before = app.getGroupHelper().getGroupCount();    // проверка количества групп до модификации
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(2);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(2).getId(),"newgroup6", "header6", "footer6");
    app.getGroupHelper().fillGroupForm(group);
    //app.getGroupHelper().fillGroupForm(new GroupData("newgroup2", null, null));     //если не надо заполнять все значения
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    // проверка количества групп после ввода новой
    //int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());

    //т.к. после модификации порядок групп может измениться из-за сортировки по наименования, сравнивать надо неупорядоченные множества
    before.remove(2);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    app.getNavigationHelper().gotoHome();
    app.getSessionHelper().logout();
  }
}
