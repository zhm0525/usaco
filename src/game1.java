/*
ID: zhm05251
LANG: JAVA
TASK: game1
*/

import java.io.*;
import java.util.*;

public class game1 {

    private static final String INPUT_FILE_NAME = "game1.in";
    private static final String OUTPUT_FILE_NAME = "game1.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = i == 0 ? a[i] : s[i - 1] + a[i];
        }

        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j + i < n) {
                    if (i == 0) {
                        d[i][j] = a[j];
                    } else {
                        int r1 = s[j + i] - s[j];
                        int r2 = j == 0 ? s[j + i - 1] : s[j + i - 1] - s[j - 1];
                        int t1 = a[j] + r1 - d[i - 1][j + 1];
                        int t2 = a[j + i] + r2 - d[i - 1][j];
                        d[i][j] = t1 > t2 ? t1 : t2;
                    }
                }
            }
        }

        out.println(String.valueOf(d[n - 1][0]) + " " + String.valueOf(s[n - 1] - d[n - 1][0]));
        out.close();
    }
}
