package ru.job4j.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUsage {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        result.add("one");
        result.add("two");
        result.add("three");
        result.add(1, "four");
        List<String> list =L  ist.of("Five", "Six");
        result.addAll(list);
       /*for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }*/
        result.set(2, "two and three");
        Iterator<String> iterator = result.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}