/*
ID: zhm05251
LANG: JAVA
TASK: fence8
*/

import java.io.*;
import java.util.*;

public class fence8 {

    private static final String INPUT_FILE_NAME = "fence8.in";
    private static final String OUTPUT_FILE_NAME = "fence8.out";

    private static int n, m;
    private static int[] a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME)));

        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        a = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(f.readLine());
        m = Integer.parseInt(st.nextToken());
        b = new int[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(f.readLine());
            b[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b);

        int sumA = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] >= b[0]) {
                sumA += a[i];
            }
        }

        int sumB = 0;
        for (int i = 0; i < m; i++) {
            sumB += b[i];
        }

        int s = 0;
        for (int i = m - 1; i >= 0; i--) {
            if (sumB <= sumA) {
                if (find(i, 0)) {
                    s = i + 1;
                    break;
                }
            }
            sumB -= b[i];
        }

        out.println(String.valueOf(s));
        out.close();
    }

    private static boolean find(int x, int lastI) {
        if (x < 0) {
            return true;
        }

        int sumA = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] >= b[0]) {
                sumA += a[i];
            }
        }

        int sumB = 0;
        for (int i = 0; i <= x; i++) {
            sumB += b[i];
        }

        if (sumA < sumB) {
            return false;
        }

        int start = 0;
        if (x + 1 < m && b[x] == b[x + 1]) {
            start = lastI;
        }

        for (int i = start; i < n; i++) {

            boolean flag = true;
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                continue;
            }

            boolean result = false;
            if (b[x] <= a[i]) {
                a[i] -= b[x];
                result = find(x - 1, i);
                a[i] += b[x];
            }
            if (result) {
                return true;
            }
        }

        return false;
    }

}