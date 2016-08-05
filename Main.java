package com.company;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String f1_name = "1.txt";
        String f2_name = "2.txt";
        String f3_name = "3.txt";
        String text;
        Scanner sc = new Scanner(System.in);

        if (!(new File(f1_name).exists())) newFail(f1_name);

        if (!(new File(f2_name).exists())) newFail(f2_name);

        concatFile(f1_name, f2_name, f3_name);
        System.out.println("Введите искомый текст");
        text = sc.nextLine();
        scanFile(f3_name,text);

    }

    public static void newFail(String name) {
        Random rand = new Random();

        try (FileOutputStream file = new FileOutputStream(name)) {
            for (int i = 0; i < 100; i++) {
                file.write((rand.nextInt(25) + 97));
                file.flush();
            }
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка чтения файла");
        }
    }

    public static void concatFile(String name1, String name2, String name3) {
        FileInputStream f1 = null;
        FileInputStream f2 = null;
        FileOutputStream f3 = null;
        int i;

        try {
            f1 = new FileInputStream(name1);
            f3 = new FileOutputStream(name3);

            do {
                i = f1.read();
                if (i != -1) f3.write(i);
                f3.flush();
            } while (i != -1);
            if (f1 != null) f1.close();
            f3.write(10);

            f2 = new FileInputStream(name2);
            do {
                i = f2.read();
                if (i != -1) f3.write(i);
                f3.flush();
            } while (i != -1);
            if (f2 != null) f2.close();
            if (f3 != null) f3.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка чтения файла");
        }
    }

    public static void scanFile(String name, String text) {
        char[] array = text.toCharArray();
        char ch;
        FileInputStream f = null;
        int s, i, count;
        i = 0;
        count = 0;
        try {
            f = new FileInputStream(name);
            do {
                s = f.read();
                if ((char)s == array[i]){
                    i++;
                    if (i == array.length){
                        count++;
                        i = 0;
                    }
                }else i=0;
                //System.out.println((char)i);
            } while (s != -1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Произошла ошибка чтения файла");
        }
        System.out.println("Строка нашлась " + count +" раз.");
    }
}
