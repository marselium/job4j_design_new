package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class MultiplexFile {
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("data/multipleTable.txt")) {
            int i = 1;
            int j = 1;
            StringBuilder builder = new StringBuilder();
            while (i < 10 && j < 10) {
                builder = builder.append(i * j).append(" ");
                if (j == 9) {
                    outputStream.write(builder.toString().getBytes());
                    outputStream.write(System.lineSeparator().getBytes());
                    i++;
                    j = 0;
                    builder.setLength(0);
                }
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
