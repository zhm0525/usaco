/*
ID: zhm05251
LANG: JAVA
TASK: subset
*/

import java.io.*;
import java.util.*;

public class subset {
    private static final String INPUT_FILE_NAME = "subset.in";
    private static final String OUTPUT_FILE_NAME = "subset.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = (1 + n) * n / 2;

        if (s % 2 == 0) {
            long[] g = new long[s / 2 + 1];
            g[0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = g.length - 1 - i; j >= 0; j--) {
                    g[j + i] = g[j + i] + g[j];
                }
            }
            out.println(g[s / 2] / 2);
        } else {
            out.println(0);
        }
        out.close();
    }
}
