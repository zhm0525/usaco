/*
ID: zhm05251
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {

    private static final String INPUT_FILE_NAME = "agrinet.in";
    private static final String OUTPUT_FILE_NAME = "agrinet.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < n; j++) {
                if (!st.hasMoreTokens()) {
                    st = new StringTokenizer(f.readLine());
                }
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] d = new int[n];
        boolean[] flags = new boolean[n];
        for (int i = 0; i < n; i++) {
            d[i] = a[0][i];
            flags[i] = true;
        }
        flags[0] = false;

        int s = 0;
        for (int i = 1; i < n; i++) {
            int m = Integer.MAX_VALUE;
            int k = -1;
            for (int j = 0; j < n; j++) {
                if (flags[j] && d[j] < m) {
                    m = d[j];
                    k = j;
                }
            }
            flags[k] = false;
            s = s + m;
            for (int j = 0; j < n; j++) {
                if (a[k][j] < d[j]) {
                    d[j] = a[k][j];
                }
            }
        }
        out.println(s);
        out.close();
    }
}
