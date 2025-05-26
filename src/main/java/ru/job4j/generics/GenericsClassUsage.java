package ru.job4j.generics;

public class GenericsClassUsage {
    public static void main(String[] args) {
        GenericsClass<String, String> first = new GenericsClass<>("First key", "First val");
        System.out.println(first);

        GenericsClass<Integer, String> sec = new GenericsClass<>(123, "Second val");
        System.out.println(sec);
    }
}
