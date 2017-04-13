/*
ID: zhm05251
LANG: JAVA
TASK: kimbits
*/

import java.io.*;
import java.util.*;

public class kimbits {

    private static final String INPUT_FILE_NAME = "kimbits.in";
    private static final String OUTPUT_FILE_NAME = "kimbits.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        int[][] a = new int[n + 1][l + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= l; j++) {
                if (i == 0 || j == 0) {
                    a[i][j] = 1;
                } else {
                    a[i][j] = a[i - 1][j - 1] + a[i - 1][j];
                }
            }
        }

        int m = l;
        for (int i = 0; i < n; i++) {
            if (x > a[n - 1 - i][m]) {
                out.print("1");
                x = x - a[n - 1 - i][m];
                m--;
            } else {
                out.print("0");
            }
        }
        out.println();
        out.close();
    }
}
