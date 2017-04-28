/*
ID: zhm05251
LANG: JAVA
TASK: theme
*/

import java.io.*;
import java.util.*;

public class theme {

    private static final String INPUT_FILE_NAME = "theme.in";
    private static final String OUTPUT_FILE_NAME = "theme.out";

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

        int[] d = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            d[i] = a[i + 1] - a[i];
        }

        int[][] b = new int[2][n - 1];
        int x = 0, s = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                b[x][j] = 0;
            }
            for (int j = i + 1; j < n - 1; j++) {
                if (d[j] == d[i]) {
                    if (i == 0) {
                        b[x][j] = 1;
                    } else {
                        b[x][j] = b[1 - x][j - 1] + 1 >= j - i ? j - i - 1 : b[1 - x][j - 1] + 1;
                        s = b[x][j] > s ? b[x][j] : s;
                    }
                }
            }
            x = 1 - x;
        }
        out.println(s + 1 < 5 ? "0" : String.valueOf(s + 1));
        out.close();
    }
}
