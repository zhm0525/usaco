/*
ID: zhm05251
LANG: JAVA
TASK: range
*/

import java.io.*;
import java.util.*;

public class range {

    private static final String INPUT_FILE_NAME = "range.in";
    private static final String OUTPUT_FILE_NAME = "range.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][n];
        int[][] rows = new int[n][n];
        int[][] columns = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = f.readLine();
            for (int j = 0; j < n; j++) {
                a[i][j] = input.charAt(j) - '0';

                if (j == 0) {
                    rows[i][j] = a[i][j];
                } else {
                    rows[i][j] = rows[i][j - 1] + a[i][j];
                }

                if (i == 0) {
                    columns[i][j] = a[i][j];
                } else {
                    columns[i][j] = columns[i - 1][j] + a[i][j];
                }
            }
        }

        int[][] s = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = a[i][j];
                if (i > 0 && j > 0 && s[i][j] > 0) {
                    int t = s[i - 1][j - 1];
                    while (t > 0) {
                        int r = j == t ? rows[i][j - 1] : rows[i][j - 1] - rows[i][j - 1 - t];
                        int c = i == t ? columns[i - 1][j] : columns[i - 1][j] - columns[i - 1 - t][j];
                        if (r == t && c == t) {
                            break;
                        }
                        t--;
                    }
                    s[i][j] = t + 1;
                }
            }
        }

        int[] x = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= s[i][j]; k++) {
                    x[k]++;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (x[i] > 0) {
                out.println(String.valueOf(i) + " " + String.valueOf(x[i]));
            }
        }

        out.close();
    }
}
