/*
ID: zhm05251
LANG: JAVA
TASK: charrec
*/

import java.io.*;
import java.util.*;

public class charrec {

    private static final String FONT_FILE_NAME = "font.in";
    private static final String INPUT_FILE_NAME = "charrec.in";
    private static final String OUTPUT_FILE_NAME = "charrec.out";

    private static int fontN;
    private static int[][][] font;

    public static void main(String[] args) throws IOException {
        BufferedReader ff = new BufferedReader(new FileReader(FONT_FILE_NAME));
        StringTokenizer st = new StringTokenizer(ff.readLine());
        fontN = Integer.parseInt(st.nextToken()) / 20;
        font = new int[fontN][20][20];
        for (int i = 0; i < fontN; i++) {
            for (int j = 0; j < 20; j++) {
                String input = ff.readLine();
                for (int k = 0; k < 20; k++) {
                    font[i][j][k] = input.charAt(k) - '0';
                }
            }
        }

        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] a = new int[n][20];
        for (int i = 0; i < n; i++) {
            String input = f.readLine();
            for (int j = 0; j < 20; j++) {
                a[i][j] = input.charAt(j) - '0';
            }
        }

        int[][][] diff = new int[fontN][20][n];
        for (int i = 0; i < fontN; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < 20; l++) {
                        diff[i][j][k] += font[i][j][l] == a[k][l] ? 0 : 1;
                    }
                }
            }
        }

        int[][] cost = new int[n][3]; // 0 for 19, 1 for 20, 2 for 21
        int[][] from = new int[n][3]; // which char
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i + 19 <= n) {
                for (int j = 0; j < fontN; j++) {
                    int t = 0;
                    for (int k = 0; k < 19; k++) {
                        t += diff[j][k + 1][i + k];
                    }
                    if (t < cost[i][0]) {
                        cost[i][0] = t;
                        from[i][0] = j;
                    }
                    for (int k = 0; k < 19; k++) {
                        t -= diff[j][k + 1][i + k];
                        t += diff[j][k][i + k];

                        if (t < cost[i][0]) {
                            cost[i][0] = t;
                            from[i][0] = j;
                        }
                    }
                }
            }

            if (i + 20 <= n) {
                for (int j = 0; j < fontN; j++) {
                    int t = 0;
                    for (int k = 0; k < 20; k++)
                        t += diff[j][k][i + k];
                    if (t < cost[i][1]) {
                        cost[i][1] = t;
                        from[i][1] = j;
                    }
                }
            }

            if (i + 21 <= n) {
                for (int j = 0; j < fontN; j++) {
                    int t = diff[j][0][i];
                    for (int k = 0; k < 20; k++) {
                        t += diff[j][k][i + k + 1];
                    }
                    if (t < cost[i][2]) {
                        cost[i][2] = t;
                        from[i][2] = j;
                    }
                    for (int k = 0; k < 19; k++) {
                        t -= diff[j][k][i + k + 1];
                        t += diff[j][k + 1][i + k + 1];

                        if (t < cost[i][2]) {
                            cost[i][2] = t;
                            from[i][2] = j;
                        }
                    }
                }
            }
        }

        int[] s = new int[n + 1];
        int[] r = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            s[i] = Integer.MAX_VALUE / 2;
        }

        s[0] = 0;
        for (int i = 19; i <= n; i++) {
            if (i >= 19 && s[i - 19] + cost[i - 19][0] < s[i]) {
                s[i] = s[i - 19] + cost[i - 19][0];
                r[i] = 19;
            }
            if (i >= 20 && s[i - 20] + cost[i - 20][1] < s[i]) {
                s[i] = s[i - 20] + cost[i - 20][1];
                r[i] = 20;
            }
            if (i >= 21 && s[i - 21] + cost[i - 21][2] < s[i]) {
                s[i] = s[i - 21] + cost[i - 21][2];
                r[i] = 21;
            }
        }

        List<Integer> results = new ArrayList<>();
        int x = n;
        while (x > 0) {
            results.add(from[x - r[x]][r[x] - 19]);
            x -= r[x];
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));
        for (int i = results.size() - 1; i >= 0; i--) {
            int t = results.get(i);
            if (t == 0) {
                out.print(" ");
            } else {
                out.print((char) ('a' + results.get(i) - 1));
            }
        }
        out.println();
        out.close();
    }

}
