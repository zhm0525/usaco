/*
ID: zhm05251
LANG: JAVA
TASK: buylow
*/

import java.io.*;
import java.util.*;

public class buylow {

    private static final String INPUT_FILE_NAME = "buylow.in";
    private static final String OUTPUT_FILE_NAME = "buylow.out";

    private static final int MAX_DIGIT_INTERVAL = 16;
    private static final int MAX_PER_INTERVAL = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            a[i] = Integer.parseInt(st.nextToken());
        }
        a[n] = 0;

        int[] s = new int[n + 1];
        int[][] r = new int[n + 1][MAX_DIGIT_INTERVAL];
        for (int i = 0; i <= n; i++) {
            s[i] = 1;
            r[i][0] = 1;
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] < a[j]) {
                    if (s[i] < s[j] + 1) {
                        s[i] = s[j] + 1;
                        for (int k = 0; k < MAX_DIGIT_INTERVAL; k++) {
                            r[i][k] = r[j][k];
                        }
                    } else if (s[i] == s[j] + 1) {
                        for (int k = 0; k < MAX_DIGIT_INTERVAL; k++) {
                            r[i][k] = r[i][k] + r[j][k];
                            if (r[i][k] > MAX_PER_INTERVAL) {
                                r[i][k + 1] += r[i][k] / MAX_PER_INTERVAL;
                                r[i][k] = r[i][k] % MAX_PER_INTERVAL;
                            }
                        }
                    }
                }
            }
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j] && s[i] == s[j]) {
                    for (int k = 0; k < MAX_DIGIT_INTERVAL; k++) {
                        r[j][k] = 0;
                    }
                }
            }
        }

        int l = MAX_DIGIT_INTERVAL - 1;
        while (l >= 0 && r[n][l] == 0) {
            l--;
        }

        StringBuffer stringBuffer = new StringBuffer();
        if (l < 0) {
            stringBuffer.append("0");
        } else {
            stringBuffer.append(String.valueOf(r[n][l]));
            for (int i = l - 1; i >= 0; i--) {
                stringBuffer.append(String.valueOf(r[n][i] + MAX_PER_INTERVAL).substring(1));
            }
        }

        out.println(String.valueOf(s[n] - 1) + " " + stringBuffer.toString());
        out.close();
    }
}