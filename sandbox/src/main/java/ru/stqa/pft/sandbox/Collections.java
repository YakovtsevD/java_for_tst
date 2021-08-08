package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main (String[] args) {

        /* инициализация и заполнение массива []
        String[] langs = new String[4];
        langs[0]="Java";
        langs[1]="C#";
        langs[2]="Python";
        langs[3]="PHP";
        */
        // заменяется 1 строкой
        String[] langs = {"Java","C#","Python","PHP"};

        /* инициализация и заполнение List
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("PHP");
        languages.add("Python");
        */
        // заменяется 1 строкой
        List<String> languages = Arrays.asList("Java","Python","PHP","C#");

        //for (int i = 0; i<langs.length; i++) {
        //специальная конструкция цикла для массивов и листов
        for (String l : langs) {
            System.out.println("I what to know " + l);
        }

        for (String l : languages) {
            System.out.println("I what to know " + l);
        }

        for (int i = 0; i<languages.size(); i++) {
            System.out.println("I what to know " + languages.get(i));
        }
    }
}
