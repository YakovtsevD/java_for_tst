package ru.stqa.pft.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    //app.getGroupHelper().fillGroupForm(new GroupData("newgroup2", "header2", "footer2"));
    //если не надо заполнять все значения
    app.getGroupHelper().fillGroupForm(new GroupData("newgroup2", null, null));
    app.getGroupHelper().submitGroupModification();
    app.getNavigationHelper().gotoGroupPage();
    app.getNavigationHelper().gotoHomePage();
  }
}
