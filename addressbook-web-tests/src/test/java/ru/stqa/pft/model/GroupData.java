package ru.stqa.pft.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("group")
@Entity  //объявляет, что класс привязан к базе (для hibernate)
@Table(name = "group_list")  //указываем таблицу

public class GroupData {
  @XStreamOmitField //аннотация НЕ выгружать поле id XML
  @Id //привязка атрибута Id из GroupData к колонке
  @Column(name="group_id") //указываем имя колонки
  private int id;

  @Expose
  @Column(name="group_name") //указываем имя колонки
  private String name;

  @Expose
  @Type(type="text")
  @Column(name="group_header") //указываем имя колонки
  private String header;

  @Expose
  @Type(type="text")
  @Column(name="group_footer") //указываем имя колонки
  private String footer;

  /*
  public GroupData(int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public GroupData(String name, String header, String footer) {
    this.id = 0;
    this.name = name;
    this.header = header;
    this.footer = footer;
  }
  */

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public int getId() {
    return id;
  }

  //---
  // переопределение стандартных методов под работу с объектами GroupData

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }  // чтоб читались логи
  //---

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
