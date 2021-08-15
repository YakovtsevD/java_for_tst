package ru.stqa.pft.tests;// Generated by Selenium IDE

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().GroupPage();
    // проверка количества групп до ввода новой
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("newgroup505").withHeader("header505").withFooter("foottter505");
    //int before = app.getGroupHelper().getGroupCount();

    app.group().create(group);

    // проверка количества групп после ввода новой
    List<GroupData> after = app.group().list();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size()+1);

    //т.к. после создания порядок групп может измениться из-за сортировки по наименования, сравнивать надо неупорядоченные множества
    //чтобы сравнить до и после, в списке before мы должны добавить значение аналогичное тому, что добавляем в тесте
    // предварительно вычисляем id - считаем его максимальным в списке after
    /*
    int max = 0;
    for (GroupData g : after) {
      if (g.getId()>max) {
        max=g.getId();
      }
    }
    */
    //Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
    // лямбда выражения поиска максимального id
    //int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

    group.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    //сортировка и сравнение упорядоченных списков
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
    //если сравнение идентифиактора id не важно, можно убрать его из equals, а в конструкторе исправить int id = Integer.MAX_VALUE,
    // тогда при сортировке всегда будет в конце списка и максимум вычислять не надо

    app.goTo().gotoHome();
    //app.getSessionHelper().logout();
  }

}
