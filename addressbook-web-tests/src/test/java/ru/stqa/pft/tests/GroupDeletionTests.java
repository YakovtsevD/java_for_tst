package ru.stqa.pft.tests;// Generated by Selenium IDE
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;


import org.testng.annotations.Test;
import ru.stqa.pft.appmanager.SessionHelper;


public class GroupDeletionTests extends TestBase {
  private SessionHelper sessionHelper;
  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getNavigationHelper().gotoGroupPage();
    app.getNavigationHelper().gotoHomePage();
    //sessionHelper.logout();
  }

}
