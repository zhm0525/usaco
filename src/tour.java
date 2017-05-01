/*
ID: zhm05251
LANG: JAVA
TASK: tour
*/

import java.io.*;
import java.util.*;

public class tour {

    private static final String INPUT_FILE_NAME = "tour.in";
    private static final String OUTPUT_FILE_NAME = "tour.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> nameMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            nameMap.put(st.nextToken(), i);
        }

        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            int p = nameMap.get(st.nextToken());
            int q = nameMap.get(st.nextToken());
            a[p][q] = true;
            a[q][p] = true;
        }

        int[][] s = new int[n][n];
        s[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < j; k++) {
                    if (a[k][j] && s[i][k] > 0 &&
                            s[i][k] + 1 > s[i][j]) {
                        s[i][j] = s[i][k] + 1;
                    }
                }
                s[j][i] = s[i][j];
            }
        }
        int ans = 1;
        for (int i = 0; i < n; i++) {
            if (a[i][n - 1] && s[i][n - 1] > ans) {
                ans = s[i][n - 1];
            }
        }

        out.println(String.valueOf(ans));
        out.close();
    }

}
