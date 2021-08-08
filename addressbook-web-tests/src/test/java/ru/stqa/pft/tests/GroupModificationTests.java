package ru.stqa.pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

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
    app.getGroupHelper().fillGroupForm(new GroupData("newgroup6", "header6", "footer6"));
    //app.getGroupHelper().fillGroupForm(new GroupData("newgroup2", null, null));     //если не надо заполнять все значения
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    // проверка количества групп после ввода новой
    //int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //Assert.assertEquals(after, before);
    Assert.assertEquals(after.size(), before.size());
    app.getNavigationHelper().gotoHome();
    app.getSessionHelper().logout();
  }
}
