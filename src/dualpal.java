/*
ID: zhm05251
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.*;

public class dualpal {
    private static final String INPUT_FILE_NAME = "dualpal.in";
    private static final String OUTPUT_FILE_NAME = "dualpal.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        while (n > 0) {
            s++;
            int match = 0;
            for (int i = 2; i <= 10; i++) {
                if (isPalindrome(convertBase(s, i))) {
                    match++;
                }
                if (match >= 2) {
                    break;
                }
            }
            if (match >= 2) {
                out.println(String.valueOf(s));
                n--;
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
