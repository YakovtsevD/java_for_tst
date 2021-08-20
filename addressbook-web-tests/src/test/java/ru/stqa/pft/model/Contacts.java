package ru.stqa.pft.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    //конструктор для копии объекта
    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate=new HashSet<ContactData>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this);  // создаем копию объекта чтобы в нее допавить новый контакт
        contacts.add(contact); // добавляем контакт
        return contacts;  // возвращаем измененный список контактов
    }

    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this);  //создаем копию объекта чтобы в нее допавить новый контакт
        contacts.remove(contact); // удаляем контакт
        return contacts; // возвращаем измененный список контактов

    }

}
