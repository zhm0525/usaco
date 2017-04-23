/*
ID: zhm05251
LANG: JAVA
TASK: buylow
*/

import java.io.*;
import java.util.*;

public class buylow {

    private static final String INPUT_FILE_NAME = "buylow.in";
    private static final String OUTPUT_FILE_NAME = "buylow.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            a[i] = Integer.parseInt(st.nextToken());
        }
        a[n] = 0;

        int[] s = new int[n + 1];
        int[] x = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (s[i] == 0) {
                s[i] = 1;
                x[i] = 1;
            }
            for (int j = i + 1; j <= n; j++) {
                if (a[i] > a[j] && s[i] + 1 > s[j]) {
                    s[j] = s[i] + 1;
                    x[j] = x[i];
                } else if (a[i] > a[j] && s[i] + 1 == s[j]) {
                    x[j] += x[i];
                }
            }
        }

        out.println(String.valueOf(s[n] - 1) + " " + String.valueOf(x[n]));
        out.close();
    }
}