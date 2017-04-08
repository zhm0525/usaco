/*
ID: zhm05251
LANG: JAVA
TASK: palsquare
*/

import java.io.*;
import java.util.*;

public class palsquare {
    private static final String INPUT_FILE_NAME = "palsquare.in";
    private static final String OUTPUT_FILE_NAME = "palsquare.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int base = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= 300; i++) {
            int square = i * i;
            String squareInTargetBase = convertBase(square, base);
            if (isPalindrome(squareInTargetBase)) {
                out.println(convertBase(i, base) + " " + squareInTargetBase);
            }
        }
        out.close();
    }

    private static String convertBase(int number, int base) {
        StringBuffer stringBuffer = new StringBuffer();
        while (number > 0) {
            int reminder = number % base;
            if (reminder > 9) {
                stringBuffer.insert(0, (char) ('A' + reminder - 10));
            } else {
                stringBuffer.insert(0, String.valueOf(reminder));
            }
            number = number / base;
        }
        return stringBuffer.toString();
    }

    private static boolean isPalindrome(String target) {
        int length = target.length();
        for (int i = 0, half = length / 2; i < half; i++) {
            if (target.charAt(i) != target.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
