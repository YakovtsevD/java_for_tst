package ru.stqa.pft.generators;

import org.checkerframework.framework.qual.DefaultQualifier;
import ru.stqa.pft.model.GroupData;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {
    public static void main(String[] args) throws IOException {

        int count = Integer.parseInt(args[0]);  //количество групп для генерации
        File file = new File(args[1]);  //путь к файлу

        List<GroupData> groups = generateGroups(count);
        save(groups, file);
    }

    private static void save(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (GroupData group : groups){
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++){
            groups.add(new GroupData().withName(String.format("aaa_group_%s", i))
                    .withHeader(String.format("aaa_group_header_%s", i))
                    .withFooter(String.format("aaa_group_footer_%s", i)));
        }
        return groups;
    }
}
