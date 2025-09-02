package ru.job4j.io;

import java.io.ByteArrayInputStream;

public class ByteArrStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[] {'J', 'a', 'v', 'a'};
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = inputStream.read()) != -1) {
            System.out.println((char) data);
        }
    }
}
