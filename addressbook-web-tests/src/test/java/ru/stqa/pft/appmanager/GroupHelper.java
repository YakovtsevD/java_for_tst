package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.model.GroupData;
import ru.stqa.pft.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends  HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  private void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void returnToGroupPage() {
    if (isElementPresent(By.tagName("h1"))   //если есть тэг заголовок h1
            //&& wd.findElement(By.tagName("h1")).getText().equals("GROUPS")   //и если есть тэг заголовок h1 с названием GROUPS
            && isElementPresent(By.name("new")))   //и если есть элемент new
    {
      return;
    }
    click(By.linkText("group page"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCash = null;
    returnToGroupPage();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCash = null;
    returnToGroupPage();
  }

  public void delete(int index) {
    selectGroup(index);  // выбираем группу номер (по поряд. номеру) 3
    deleteSelectedGroups(); // удаляем
    returnToGroupPage(); // на страницу групп
  }
  public void delete(GroupData group) {
    selectGroupById(group.getId());  // выбираем группу номер (по поряд. номеру) 3
    deleteSelectedGroups(); // удаляем
    groupCash = null;
    returnToGroupPage(); // на страницу групп

  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
    }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //найти все элементы с тэгом span и классом group
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

  private Groups groupCash = null;

  public Groups all() {
    if (groupCash != null) {
      return new Groups(groupCash);
    }
    //Groups groups = new Groups();
    groupCash = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group")); //найти все элементы с тэгом span и классом group
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCash.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCash);
  }

}
