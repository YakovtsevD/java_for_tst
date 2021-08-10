package ru.stqa.pft.model;

public class ContactData {
  private int id;

  private final String firstname;
  private final String lastname;
  private final String email;
  private final String group;

  public ContactData(int id, String firstname, String lastname, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.group = group;
  }

  public ContactData(String firstname, String lastname, String email, String group) {
    this.id = 0;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() { return group; }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
