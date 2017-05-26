/*
ID: zhm05251
LANG: JAVA
TASK: latin
*/

import java.io.*;
import java.util.*;

public class latin {

    private static final String INPUT_FILE_NAME = "latin.in";
    private static final String OUTPUT_FILE_NAME = "latin.out";

    private static boolean[][] row, column;
    private static int n, r;
    private static long s = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        row = new boolean[n][n];
        column = new boolean[n][n];

        r = 1;
        for (int i = 1; i < n; i++) {
            r = r * i;
        }

        search(1, 0, 0);

        out.println(String.valueOf(s));
        out.close();
    }

    private static void search(int x, int y, int rs) {
        if (x >= n - 1) {
            s += r;
            return;
        }

        if (y >= n - 1) {
            int i = (n - 1) * n / 2 - rs;
            if (i != y && !row[x][i] && !column[y][i]) {
                column[y][i] = true;
                search(x + 1, 0, 0);
                column[y][i] = false;
            }
            return;
        }

        if (x == y) {
            search(x, y + 1, rs);
            return;
        }

        if (x == 1 && y == 0) {
            int i = 1;
            row[x][i] = true;
            column[y][i] = true;
            search(x, y + 1, rs + i);
            row[x][i] = false;
            column[y][i] = false;

            i = 2;
            if (i < n) {
                long t = s;
                row[x][i] = true;
                column[y][i] = true;
                search(x, y + 1, rs + i);
                row[x][i] = false;
                column[y][i] = false;
                s = t + (s - t) * (n - 2);
            }
            return;
        }

        for (int i = 1; i < n; i++) {
            if (i != y && !row[x][i] && !column[y][i]) {
                row[x][i] = true;
                column[y][i] = true;
                search(x, y + 1, rs + i);
                row[x][i] = false;
                column[y][i] = false;
            }
        }
    }
}