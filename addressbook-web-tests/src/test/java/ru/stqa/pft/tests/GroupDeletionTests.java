package ru.stqa.pft.tests;// Generated by Selenium IDE

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;
import ru.stqa.pft.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod  // перед каждым тестом в классе будет проверка есть ли группа (с созданием, если групп нет)
  public void ensurePreConditions() {
    if (app.db().groups().size() == 0) {
    app.goTo().groupPage();
    //if (! app.group().isThereAGroup()) {
    //if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    //Groups before = app.group().all();
    app.goTo().groupPage();
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertEquals(app.group().count(), before.size()-1);
    //Groups after = app.group().all();
    Groups after = app.db().groups();
    //before.remove(deletedGroup); // удаляем из списка before элемент, который удалили в тесте, чтобы списки до и после стали одинаковыми
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(deletedGroup)));

    verifyGroupListInUI();

    app.goTo().gotoHome(); // на страницу хоум
    //app.getSessionHelper().logout(); // разлогин
  }

}
