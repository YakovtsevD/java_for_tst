package ru.stqa.pft.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.model.ContactData;
import ru.stqa.pft.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter (names = "-f", description = "Target file")
    public String file;
    @Parameter (names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);

        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXML(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJSON(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format");
        }
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {  // конструкция try открывает файл пишет в него и закрывает
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class); //или можно реализовать через аннотации в GroupData @XStreamAlias("group") и @XStreamOmitField для id
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData()
                    .withFirstname(String.format("%s_FirstName_%s",format, i))
                    .withLastname(String.format("%s_LastName_%s",format, i))
                    .withAddress(String.format("%s_Address_%s",format, i)));
        }
        return contacts;
    }
}
