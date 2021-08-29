package ru.stqa.pft.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTests extends TestBase {

    @Test (enabled = true)
    public void testContactPhones() {
        app.goTo().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        // метод обратных проверок
        // склеиваем телефоны в карточной форме и сравниваем с тем, что в таблице (актуально если один из тел. отсутсвует в карте)
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
        // прямая проверка, сравниваниваем каждый тел. из карточки с куском из общего (сработает только если в карточке все телефоны заполнены)
        //assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        //assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        //assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
        }

    @Test
    public void testContactDetails() {
        app.goTo().gotoHome();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

        assertThat(contact, equalTo(contactInfoFromDetailsForm)); // equals в contactData использует при сравнении только id firstname lastname - так мы сравнили правильность этих 3 полей
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromDetailsForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromDetailsForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromDetailsForm.getAddress()));

        }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())  //формируем массив-лист из телефонов
                .stream().filter((s) -> !s.equals("")) //формируем поток с фильтром, исключающим пустые ячейки
                .map(ContactPhoneTests::cleaned) //с помощью функции очищаем от лишних пробелов-табуляций-переводов строк("\\s") и других символов("[-()]")
                .collect(Collectors.joining("\n")); //склеиваем все элементы потока с разделителем "\n"
    }

    // очистка строк от ненужных символов с помощью регулярных выражений
    public static String cleaned (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "")
                    .replaceAll("H.", "").replaceAll("M.", "").replaceAll("W.", "");
    }
    // чистим строки только от пробелов
    public static String cleanSpaces (String phone) {
        return phone.replaceAll("\\s", "");
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())  //формируем массив-лист из телефонов
                .stream().filter((s) -> !s.equals("")) //формируем поток с фильтром, исключающим пустые ячейки
                .map(ContactPhoneTests::cleanSpaces) //с помощью функции очищаем от лишних пробелов-табуляций-переводов строк("\\s")
                .collect(Collectors.joining("\n")); //склеиваем все элементы потока с добавлением разделителя "\n"
    }

}
