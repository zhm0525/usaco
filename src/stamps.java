/*
ID: zhm05251
LANG: JAVA
TASK: stamps
*/

import java.io.*;
import java.util.*;

public class stamps {
    private static final String INPUT_FILE_NAME = "stamps.in";
    private static final String OUTPUT_FILE_NAME = "stamps.out";

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] a = new int[n];
        int t = 0;
        st = new StringTokenizer(f.readLine());
        for (int i = 0; i < n; i++) {
            if (!st.hasMoreTokens()) {
                st = new StringTokenizer(f.readLine());
            }
            a[i] = Integer.parseInt(st.nextToken());
            if (a[i] > t) {
                t = a[i];
            }
        }

        int[] s = new int[t * k + 1];
        s[0] = 0;
        for (int i = 1; i < t * k; i++) {
            s[i] = k + 1;
        }
        int r = 0;
        while (r <= t*k && s[r] <= k) {
            for (int x : a) {
                if (r + x <= t * k && s[r] + 1 < s[r + x]) {
                    s[r + x] = s[r] + 1;
                }
            }
            r++;
        }
        out.println(r - 1);
        out.close();
    }
}
