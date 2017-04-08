/*
ID: zhm05251
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.*;

public class combo {
    private static final String INPUT_FILE_NAME = "combo.in";
    private static final String OUTPUT_FILE_NAME = "combo.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int z1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int z2 = Integer.parseInt(st.nextToken());

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (isMatch(x1, y1, z1, i, j, k, n) ||
                            isMatch(x2, y2, z2, i, j, k, n)) {
                        result++;
                    }
                }
            }
        }
        out.println(result);
        out.close();
    }

    private static boolean isMatch(int x, int y, int z, int i, int j, int k, int n) {
        if (!isMatch(x, i, n)) {
            return false;
        }
        if (!isMatch(y, j, n)) {
            return false;
        }
        if (!isMatch(z, k, n)) {
            return false;
        }
        return true;
    }

    private static boolean isMatch(int x, int i, int n) {
        for (int r = -2; r <= 2; r++) {
            int t = i + r;
            t = t > n ? t - n : t;
            t = t <= 0 ? t + n : t;
            if (t == x) {
                return true;
            }
        }
        return false;
    }
}
