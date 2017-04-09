/*
ID: zhm05251
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.*;

public class numtri {
    private static final String INPUT_FILE_NAME = "numtri.in";
    private static final String OUTPUT_FILE_NAME = "numtri.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int r = Integer.parseInt(st.nextToken());

        int[][] a = new int[r][r];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j = 0; j < i + 1; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] s = new int[r][r];
        s[0][0] = a[0][0];
        for (int i = 0; i < r - 1; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (s[i][j] + a[i + 1][j] > s[i + 1][j]) {
                    s[i + 1][j] = s[i][j] + a[i + 1][j];
                }
                if (s[i][j] + a[i + 1][j + 1] > s[i + 1][j + 1]) {
                    s[i + 1][j + 1] = s[i][j] + a[i + 1][j + 1];
                }
            }
        }

        int max = 0;
        for (int i = 0; i < r; i++) {
            if (s[r - 1][i] > max) {
                max = s[r - 1][i];
            }
        }
        out.println(max);
        out.close();
    }

}
