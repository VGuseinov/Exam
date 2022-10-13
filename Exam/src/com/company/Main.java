package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Task 1");
        System.out.println(caesarCipher("NEZE ETTPMGEXMSRW EVI XCTMGEPPC GSQTMPIH XS FCXIGSHI XLEX GER VYR SR ERC NEZE ZMVXYEP QEGLMRI VIKEVHPIWW SJ XLI YRHIVPCMRK GSQTYXIV EVGLMXIGXYVI"));
        System.out.println();
        System.out.println("Task 2");
        int[] arr = { 0, -1, 22, -1, 22, 73, -1, -1, 99, 100};
        sort(arr);
        for (int e : arr) {
            System.out.println(e);
        }
        
        System.out.println();
        System.out.println("Task 3");
        Random random = new Random();

        int player1Score = 0;
        int player2Score = 0;

        while (player1Score < 10 && player2Score < 10) {
            File file = new File("results.txt");
            FileWriter fileWriter = new FileWriter("results.txt");
            FileReader fileReader = new FileReader("results.txt");
            Thread player1 = new Thread(() -> {
                synchronized (fileWriter) {
                    String string = "";
                    int rand = random.nextInt(3) + 1;
                    if (rand == 1) {
                        string = "P";
                    }
                    if (rand == 2) {
                        string = "S";
                    }
                    if (rand == 3) {
                        string = "R";
                    }
                    try {
                        fileWriter.write("1" + string);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread player2 = new Thread(() -> {
                synchronized (fileWriter) {
                    String string = "";
                    int rand = random.nextInt(3) + 1;
                    if (rand == 1) {
                        string = "P";
                    }
                    if (rand == 2) {
                        string = "S";
                    }
                    if (rand == 3) {
                        string = "R";
                    }
                    try {
                        fileWriter.write("2" + string);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            player1.start();
            player1.join();
            player2.start();
            player2.join();

            fileWriter.flush();
            fileWriter.close();

            char[] chars = new char[4];
            fileReader.read(chars);
            fileReader.close();
            System.out.println(chars);

            if (chars[0] == '1') {
                if (chars[1] == 'P' && chars[3] == 'S') {
                    player1Score++;
                }
                if (chars[1] == 'S' && chars[3] == 'P') {
                    player1Score++;
                }
                if (chars[1] == 'S' && chars[3] == 'R') {
                    player1Score++;
                }
                if (chars[1] == 'R' && chars[3] == 'S') {
                    player1Score++;
                }
                if (chars[1] == 'P' && chars[3] == 'R') {
                    player1Score++;
                }
                if (chars[1] == 'R' && chars[3] == 'P') {
                    player1Score++;
                }
            }
            if (chars[0] == '1') {
                if (chars[1] == 'P' && chars[3] == 'S') {
                    player2Score++;
                }
                if (chars[1] == 'S' && chars[3] == 'P') {
                    player2Score++;
                }
                if (chars[1] == 'S' && chars[3] == 'R') {
                    player2Score++;
                }
                if (chars[1] == 'R' && chars[3] == 'S') {
                    player2Score++;
                }
                if (chars[1] == 'P' && chars[3] == 'R') {
                    player2Score++;
                }
                if (chars[1] == 'R' && chars[3] == 'P') {
                    player2Score++;
                }
            }
        }
        if (player1Score == 10) {
            System.out.println("Player #1 wins");
            return;
        }
        if (player2Score == 10) {
            System.out.println("Player #2 wins");
        }
    }

    public static String caesarCipher(String s) {
        int key = 4;
        StringBuilder stringBuilder = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                c -= key;
                if (c < 'A') {
                    c += 26;
                }
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static void sort(int[] toSort) {
        for (int i = 0; i < toSort.length - 1; i++) {
            if (toSort[i] != -1) {
                for (int j = i + 1; j < toSort.length; j++) {
                    if (toSort[i] > toSort[j] && toSort[j] != -1) {
                        int tmp = toSort[i];
                        toSort[i] = toSort[j];
                        toSort[j] = tmp;
                    }
                }
            }
        }
    }
}




