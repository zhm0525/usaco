/*
ID: zhm05251
LANG: JAVA
TASK: shuttle
*/

import java.io.*;
import java.util.*;

public class shuttle {

    private static final String INPUT_FILE_NAME = "shuttle.in";
    private static final String OUTPUT_FILE_NAME = "shuttle.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] a = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            int t = i % 2 == 0 ? -2 : 2;
            a[i][0] = i % 2 == 0 ? n + i + 1 : n - i + 1;
            for (int j = 1; j < i + 1; j++) {
                a[i][j] = a[i][j - 1] + t;
            }
        }
        int t = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (t >= 20) {
                    out.println();
                    t = 0;
                } else if (t > 0) {
                    out.print(" ");
                }
                out.print(String.valueOf(a[i][j]));
                t++;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i + 1; j++) {
                if (t >= 20) {
                    out.println();
                    t = 0;
                } else if (t > 0) {
                    out.print(" ");
                }
                out.print(String.valueOf(a[i][j]));
                t++;
            }
        }
        if (t > 0) {
            out.println();
        }
        out.close();
    }
}

