package ru.job4j.io;

import java.io.*;

public class DataStream {
    public static void main(String[] args) throws Exception {
        String path = "data/dataOutput.txt";
        String[] names = {"unit1", "unit2", "unit3"};
        int[] amounts = {5, 7, 2};
        boolean[] checked = {true, false, true};

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(path));
             DataInputStream inp = new DataInputStream(new FileInputStream(path))){
            for (int i = 0; i < names.length; i++) {
                out.writeUTF(names[i]);
                out.writeInt(amounts[i]);
                out.writeBoolean(checked[i]);
            }
            while (true) {
                String name = inp.readUTF();
                int amount = inp.readInt();
                boolean check = inp.readBoolean();
                System.out.println("Наименование: " + name
                        + ", количество: " + amount
                        + ", проверен: " + check);
            }
        } catch (EOFException e) {
            System.out.println("Достигнут конец файла");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
