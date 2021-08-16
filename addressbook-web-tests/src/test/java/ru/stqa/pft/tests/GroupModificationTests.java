package ru.stqa.pft.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;
import ru.stqa.pft.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase {

  @BeforeMethod  // перед каждым тестом в классе будет проверка есть ли группа (с созданием, если групп нет)
  public void ensurePreConditions() {
    app.goTo().GroupPage();
    //if (! app.group().isThereAGroup()) {
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("test1").withHeader("header1").withFooter("footer1"));
    }
  }

  @Test
  public void testGroupModification() {

    app.goTo().GroupPage();

    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("modgroup6").withHeader("header6").withFooter("footer6");
    app.group().modify(group);
    Assert.assertEquals(app.group().Count(), before.size()); // быстрая проверка количества без загрузки списка групп after
    Groups after = app.group().all();

    //Assert.assertEquals(after.size(), before.size());

    //т.к. после модификации порядок групп может измениться из-за сортировки по наименования, сравнивать надо неупорядоченные множества
    //before.remove(modifiedGroup);
    before.add(group);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after)); // это сравнение неупорядоченных списков
    //сравнение сортированных списков
    //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    //before.sort(byId);
    //after.sort(byId);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup)));

    app.goTo().gotoHome();
    //app.getSessionHelper().logout();
  }

}
