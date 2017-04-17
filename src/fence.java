/*
ID: zhm05251
LANG: JAVA
TASK: fence
*/

import java.io.*;
import java.util.*;

public class fence {

    private static final String INPUT_FILE_NAME = "fence.in";
    private static final String OUTPUT_FILE_NAME = "fence.out";

    private static int[][] a = new int[501][501];
    private static int[] s;
    private static int index;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] d = new int[501];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            a[p][q]++;
            a[q][p]++;
            d[p]++;
            d[q]++;
        }
        int start = 0;
        for (int i = 1; i < 501; i++) {
            if (start == 0 && d[i] > 0) {
                start = i;
            }
            if (d[i] % 2 == 1) {
                start = i;
                break;
            }
        }

        s = new int[m + 1];
        index = 0;
        search(start);


        for (int i = m; i >= 0; i--) {
            out.println(String.valueOf(s[i]));
        }
        out.close();
    }

    private static void search(int x) {
        for (int i = 1; i < 501; i++) {
            if (a[x][i] > 0) {
                a[x][i]--;
                a[i][x]--;
                search(i);
            }
        }
        s[index++] = x;
    }
}
