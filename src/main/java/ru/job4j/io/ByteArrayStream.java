package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'J', 'a', 'v', 'a'};
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.print((char) data);
        }
        System.out.println();
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream stream1 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        while ((data1 = stream1.read()) != -1) {
            System.out.print((char) data1);
        }
        System.out.println();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes2 = "Message".getBytes();
        outputStream.writeBytes(bytes2);
        System.out.println(outputStream);
    }
}
