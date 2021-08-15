package ru.stqa.pft.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod  // перед каждым тестом в классе будет проверка есть ли группа (с созданием, если групп нет)
  public void ensurePreConditions() {
    app.goTo().GroupPage();
    //if (! app.group().isThereAGroup()) {
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
  }

  @Test
  public void testGroupDeletion() {
    //int before = app.getGroupHelper().getGroupCount(); // проверка количества групп до удаления
    Set<GroupData> before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    //int index = before.size()-2;

    app.group().delete(deletedGroup);

    //int after = app.getGroupHelper().getGroupCount(); // проверка количества групп после удаления
    Set<GroupData> after = app.group().all();
    //Assert.assertEquals(after, before-1); // проверка что групп стало меньше на 1
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(deletedGroup); // удаляем из списка before элемент, который удалили в тесте, чтобы списки до и после стали одинаковыми
    //теперь проверка что списки действительно одинаковые
    /*
    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i)); //сравниваем соответсвующие элементы списка
    }
    */
    //для сравнения объектов цикл не нужен, тестовый фреймворк умеет сравнивать списки целиком
    Assert.assertEquals(before, after);

    app.goTo().gotoHome(); // на страницу хоум
    //app.getSessionHelper().logout(); // разлогин
  }

}
