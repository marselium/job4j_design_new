package ru.job4j.io.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ScannerExample3 {
    public static void main(String[] args) throws IOException {
        var data = "A 1B FF 110";
        var file = File.createTempFile("data", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(data.getBytes());
        }
        try (var scan = new Scanner(file).useRadix(16)){
            while (scan.hasNextInt()) {
                System.out.print(scan.nextInt());
                System.out.print(" ");
            }
        }
    }
}
