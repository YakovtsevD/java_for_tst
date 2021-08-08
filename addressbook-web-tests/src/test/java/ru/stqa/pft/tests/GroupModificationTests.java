package ru.stqa.pft.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    // проверка количества групп до ввода новой
    int before = app.getGroupHelper().getGroupCount();

    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "header1", "footer1"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("newgroup2", "header22", "footer2"));
    //app.getGroupHelper().fillGroupForm(new GroupData("newgroup2", null, null));     //если не надо заполнять все значения
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    // проверка количества групп после ввода новой
    int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after, before);

    app.getNavigationHelper().gotoHome();
    app.getSessionHelper().logout();
  }
}
