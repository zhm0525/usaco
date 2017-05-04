/*
ID: zhm05251
LANG: JAVA
TASK: hidden
*/

import java.io.*;
import java.util.*;

public class hidden {

    private static final String INPUT_FILE_NAME = "hidden.in";
    private static final String OUTPUT_FILE_NAME = "hidden.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        char[] a = new char[n];
        String input = f.readLine();
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (k >= input.length()) {
                input = f.readLine();
                k = 0;
            }
            a[i] = input.charAt(k);
            k++;
        }

        int s = 0, t = 1;

        while (t < n) {
            int flag = 0, r = 1;
            for (int i = 0; i < n; i++) {
                if (a[(s + i) % n] < a[(t + i) % n]) {
                    flag = 1;
                    break;
                } else if (a[(s + i) % n] > a[(t + i) % n]) {
                    flag = 2;
                    break;
                } else if (s + i >= t) {
                    r = i;
                }
            }
            if (flag == 0) {
                break;
            } else if (flag == 1) {
                t += r;
            } else if (flag == 2) {
                s = t;
                t += r;
            }
        }

        out.println(String.valueOf(s));
        out.close();
    }
}
