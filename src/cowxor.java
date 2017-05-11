/*
ID: zhm05251
LANG: JAVA
TASK: cowxor
*/

import java.io.*;
import java.util.*;

public class cowxor {

    private static final String INPUT_FILE_NAME = "cowxor.in";
    private static final String OUTPUT_FILE_NAME = "cowxor.out";

    private static final int MAX_BIT = 21;
    private static final int MAX = 4194304; // pow(2, 22)

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        // assert n > 0

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        int[] r = new int[n];
        r[0] = a[0];
        for (int i = 1; i < n; i++) {
            r[i] = r[i - 1] ^ a[i];
        }

        int[] t = new int[MAX];
        boolean[] b = new boolean[MAX];
        for (int i = 0; i < MAX; i++) {
            t[i] = -1;
            b[i] = false;
        }

        int s = r[0], p = 1, q = 1;
        for (int i = 0; i < n; i++) {
            // divide
            int[] d = new int[MAX_BIT];
            int x = r[i], l = 0;
            while (x > 0) {
                d[l++] = x % 2;
                x = x / 2;
            }
            // query
            l = 0;
            for (int j = MAX_BIT - 1; j >= 1; j--) {
                if (d[j] == 0) {
                    if (b[l + 1]) {
                        l++;
                    } else {
                        // nothing
                    }
                } else {
                    if (b[l]) {
                        // nothing
                    } else {
                        l++;
                    }
                }
                l = (l + 1) * 2;
            }
            if (d[0] == 0) {
                if (b[l + 1]) {
                    l++;
                } else {
                    // nothing
                }
            } else {
                if (b[l]) {
                    // nothing
                } else {
                    l++;
                }
            }
            int k = t[l];

            // compare
            if (k >= 0) {
                int z = r[k] ^ r[i];
                if (z > s) {
                    s = z;
                    p = k + 1 + 1;
                    q = i + 1;
                }
            }

            // insert
            l = 0;
            for (int j = MAX_BIT - 1; j >= 1; j--) {
                if (d[j] == 0) {
                    b[l] = true;
                } else {
                    b[++l] = true;
                }
                l = (l + 1) * 2;
            }
            if (d[0] == 0) {
                b[l] = true;
                t[l] = i;
            } else {
                b[l + 1] = true;
                t[l + 1] = i;
            }
        }
        out.println("" + s + " " + p + " " + q);
        out.close();
    }
}
