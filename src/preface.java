/*
ID: zhm05251
LANG: JAVA
TASK: preface
*/

import java.io.*;
import java.util.*;

public class preface {
    private static final String INPUT_FILE_NAME = "preface.in";
    private static final String OUTPUT_FILE_NAME = "preface.out";

    // I 1  V 5  X 10  L 50  C 100  D 500  M 1000
    private static int[] sum = new int[7];

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            count(i);
        }
        for (int i = 0;i<7;i++) {
            if (sum[i] != 0) {
                out.println(getWord(i) + " " + String.valueOf(sum[i]));
            }
        }
        out.close();
    }

    private static String getWord(int i) {
        switch (i) {
            case 0:
                return "I";
            case 1:
                return "V";
            case 2:
                return "X";
            case 3:
                return "L";
            case 4:
                return "C";
            case 5:
                return "D";
            case 6:
                return "M";
        }
        return "";
    }

    private static void count(int x) {
        if (x > 0) {
            count1Digit(x % 10);
            x = x / 10;
        }
        if (x > 0) {
            count2Digits(x % 10);
            x = x / 10;
        }
        if (x > 0) {
            count3Digits(x % 10);
            x = x / 10;
        }
        if (x > 0) {
            count4Digits(x % 10);
            x = x / 10;
        }
    }

    private static void count1Digit(int x) {
        countDigits(x, 0);
    }

    private static void count2Digits(int x) {
        countDigits(x, 2);
    }

    private static void count3Digits(int x) {
        countDigits(x, 4);
    }

    private static void count4Digits(int x) {
        countDigits(x, 6);
    }

    private static void countDigits(int x, int offset) {
        if (x <= 3) {
            sum[0 + offset] += x;
        } else if (x <= 5) {
            sum[0 + offset] += 5 - x;
            sum[1 + offset] += 1;
        } else if (x <= 8) {
            sum[0 + offset] += x - 5;
            sum[1 + offset] += 1;
        } else if (x == 9) {
            sum[0 + offset] += 1;
            sum[2 + offset] += 1;
        }
    }
}
