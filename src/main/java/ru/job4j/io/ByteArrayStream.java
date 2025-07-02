package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.println((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream stream1 = new ByteArrayInputStream(bytes1, 0, 4);
        while ((data = stream1.read()) != -1) {
            System.out.println((char) data);
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("data/message.txt")) {
            byte[] bytes2 = "Message".getBytes();
            fileOutputStream.write(bytes2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
