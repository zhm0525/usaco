/*
ID: zhm05251
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.*;

public class money {
    private static final String INPUT_FILE_NAME = "money.in";
    private static final String OUTPUT_FILE_NAME = "money.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int v = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int[] a = new int[v];
        for (int i = 0; i < v; i++) {
            if (st.hasMoreTokens()) {
                a[i] = Integer.parseInt(st.nextToken());
            } else {
                st = new StringTokenizer(f.readLine());
                a[i] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] s = new long[v + 1][n + 1];
//        for (int i = 0; i <= v; i++) {
//            s[i][0] = 1;
//        }
        s[0][0] = 1;
        for (int i = 1; i <= v; i++) {
            for (int j = 0; j <= n; j++) {
                if (j - a[i - 1] >= 0) {
                    s[i][j] = s[i - 1][j] + s[i][j - a[i - 1]];
                } else {
                    s[i][j] = s[i - 1][j];
                }
            }
        }
        out.println(s[v][n]);
        out.close();
    }

}
