/*
ID: zhm05251
LANG: JAVA
TASK: schlnet
*/

import java.io.*;
import java.util.*;

public class schlnet {

    private static final String INPUT_FILE_NAME = "schlnet.in";
    private static final String OUTPUT_FILE_NAME = "schlnet.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            while (st.hasMoreTokens()) {
                int t = Integer.parseInt(st.nextToken());
                if (t != 0) {
                    map[i][t - 1] = true;
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = map[i][j] || (map[i][k] && map[k][j]);
                }
            }
        }

        boolean[] available = new boolean[n];
        for (int i = 0; i < n; i++) {
            available[i] = true;
        }
        for (int i = 0; i < n; i++) {
            if (available[i]) {
                for (int j = i + 1; j < n; j++) {
                    if (available[j] && map[i][j] && map[j][i]) {
                        available[j] = false;
                    }
                }
            }
        }

        int[] in = new int[n];
        int[] ou = new int[n];
        for (int i = 0; i < n; i++) {
            if (available[i]) {
                for (int j = 0; j < n; j++) {
                    if (available[j]) {
                        if (i != j) {
                            if (map[i][j]) {
                                ou[i]++;
                            }
                            if (map[j][i]) {
                                in[i]++;
                            }
                        }
                    }
                }
            }
        }

        int s1 = 0, s2 = 0, t = 0;
        for (int i = 0; i < n; i++) {
            if (available[i]) {
                if (in[i] == 0) {
                    s1++;
                }
                if (ou[i] == 0) {
                    s2++;
                }
                t++;
            }
        }
        if (s1 == 1 && t == 1) {
            s2 = 0;
        } else {
            s2 = s1 < s2 ? s2 : s1;
        }

        out.println(String.valueOf(s1));
        out.println(String.valueOf(s2));
        out.close();
    }
}
