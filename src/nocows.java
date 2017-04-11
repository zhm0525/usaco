/*
ID: zhm05251
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.*;

public class nocows {
    private static final String INPUT_FILE_NAME = "nocows.in";
    private static final String OUTPUT_FILE_NAME = "nocows.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] s = new int[k + 1][n + 1];
        s[1][1] = 1;
        for (int i = 2; i <= k; i++) {
            for (int j = 3; j <= n; j++) {
                for (int m = 1; m < j - 1; m++) {
                    int l = 0, r = 0;
                    for (int x = 1; x < i - 1; x++) {
                        l += s[x][m];
                        r += s[x][j - 1 - m];
                    }
                    s[i][j] = (s[i][j] + s[i - 1][m] * r) % 9901;
                    s[i][j] = (s[i][j] + s[i - 1][j - 1 - m] * l) % 9901;
                    s[i][j] = (s[i][j] + s[i - 1][m] * s[i - 1][j - 1 - m]) % 9901;
                }
            }
        }
        out.println(s[k][n]);
        out.close();
    }
}
