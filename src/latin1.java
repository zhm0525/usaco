/*
ID: zhm05251
LANG: JAVA
TASK: latin
*/

import java.io.*;
import java.util.*;

public class latin1 {

    private static final String INPUT_FILE_NAME = "latin.in";
    private static final String OUTPUT_FILE_NAME = "latin.out";

    private static boolean[][] row, column;
    private static int n;
    private static long s = 0;
    private static int[][] a;

    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        row = new boolean[n][n];
        column = new boolean[n][n];

        a = new int[n][n];
        search(0, 0);

        out.println(s);
        out.close();
    }

    private static void search(int x, int y) {
        if (x >= n) {
            s++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    out.print("" + a[i][j] + " ");
                }
                out.println();
            }
            out.println();
            return;
        }

        if (y >= n) {
            search(x + 1, 0);
            return;
        }

        if (x == y) {
            a[x][y] = 1;
            search(x, y + 1);
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!row[x][i] && !column[y][i]) {
                row[x][i] = true;
                column[y][i] = true;
                a[x][y] = i + 1;
                search(x, y + 1);
                row[x][i] = false;
                column[y][i] = false;
            }
        }
    }
}
