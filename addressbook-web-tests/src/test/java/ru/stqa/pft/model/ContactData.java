package ru.stqa.pft.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String email;

  public ContactData(String firstname, String lastname, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
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
}
