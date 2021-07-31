package ru.stqa.pft.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.model.GroupData;

public class GroupHelper extends  HelperBase {
  public GroupHelper(FirefoxDriver wd) {
    super(wd);
  }


  public void submitGroupCreation() {
    //wd.findElement(By.cssSelector("form:nth-child(2)")).click();
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
    click(By.cssSelector("input:nth-child(8)"));
  }

  public void selectGroup() {
    click(By.name("selected[]"));
  }
}
