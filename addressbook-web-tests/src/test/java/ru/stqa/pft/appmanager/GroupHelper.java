package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.model.GroupData;

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

  public void selectGroup() {
    click(By.name("selected[]"));
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void returnToGroupPage() {
    if (isElementPresent(By.tagName("h1"))   //если есть тэг заголовок h1
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")   //и если есть тэг заголовок h1 с названием Groups
            && isElementPresent(By.name("new")))   //и если есть элемент new
    {
      return;
    }
    click(By.linkText("groups"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

    public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
    }
}
